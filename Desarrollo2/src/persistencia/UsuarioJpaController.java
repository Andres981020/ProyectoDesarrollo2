/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Objetivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Desarrollo2PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getObjetivoList() == null) {
            usuario.setObjetivoList(new ArrayList<Objetivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Objetivo> attachedObjetivoList = new ArrayList<Objetivo>();
            for (Objetivo objetivoListObjetivoToAttach : usuario.getObjetivoList()) {
                objetivoListObjetivoToAttach = em.getReference(objetivoListObjetivoToAttach.getClass(), objetivoListObjetivoToAttach.getCodigoObjetivo());
                attachedObjetivoList.add(objetivoListObjetivoToAttach);
            }
            usuario.setObjetivoList(attachedObjetivoList);
            em.persist(usuario);
            for (Objetivo objetivoListObjetivo : usuario.getObjetivoList()) {
                Usuario oldCreadorObjetivoOfObjetivoListObjetivo = objetivoListObjetivo.getCreadorObjetivo();
                objetivoListObjetivo.setCreadorObjetivo(usuario);
                objetivoListObjetivo = em.merge(objetivoListObjetivo);
                if (oldCreadorObjetivoOfObjetivoListObjetivo != null) {
                    oldCreadorObjetivoOfObjetivoListObjetivo.getObjetivoList().remove(objetivoListObjetivo);
                    oldCreadorObjetivoOfObjetivoListObjetivo = em.merge(oldCreadorObjetivoOfObjetivoListObjetivo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCedula()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCedula());
            List<Objetivo> objetivoListOld = persistentUsuario.getObjetivoList();
            List<Objetivo> objetivoListNew = usuario.getObjetivoList();
            List<String> illegalOrphanMessages = null;
            for (Objetivo objetivoListOldObjetivo : objetivoListOld) {
                if (!objetivoListNew.contains(objetivoListOldObjetivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivo " + objetivoListOldObjetivo + " since its creadorObjetivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Objetivo> attachedObjetivoListNew = new ArrayList<Objetivo>();
            for (Objetivo objetivoListNewObjetivoToAttach : objetivoListNew) {
                objetivoListNewObjetivoToAttach = em.getReference(objetivoListNewObjetivoToAttach.getClass(), objetivoListNewObjetivoToAttach.getCodigoObjetivo());
                attachedObjetivoListNew.add(objetivoListNewObjetivoToAttach);
            }
            objetivoListNew = attachedObjetivoListNew;
            usuario.setObjetivoList(objetivoListNew);
            usuario = em.merge(usuario);
            for (Objetivo objetivoListNewObjetivo : objetivoListNew) {
                if (!objetivoListOld.contains(objetivoListNewObjetivo)) {
                    Usuario oldCreadorObjetivoOfObjetivoListNewObjetivo = objetivoListNewObjetivo.getCreadorObjetivo();
                    objetivoListNewObjetivo.setCreadorObjetivo(usuario);
                    objetivoListNewObjetivo = em.merge(objetivoListNewObjetivo);
                    if (oldCreadorObjetivoOfObjetivoListNewObjetivo != null && !oldCreadorObjetivoOfObjetivoListNewObjetivo.equals(usuario)) {
                        oldCreadorObjetivoOfObjetivoListNewObjetivo.getObjetivoList().remove(objetivoListNewObjetivo);
                        oldCreadorObjetivoOfObjetivoListNewObjetivo = em.merge(oldCreadorObjetivoOfObjetivoListNewObjetivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getCedula();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objetivo> objetivoListOrphanCheck = usuario.getObjetivoList();
            for (Objetivo objetivoListOrphanCheckObjetivo : objetivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Objetivo " + objetivoListOrphanCheckObjetivo + " in its objetivoList field has a non-nullable creadorObjetivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
