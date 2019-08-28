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
import java.util.Collection;
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
        if (usuario.getObjetivoCollection() == null) {
            usuario.setObjetivoCollection(new ArrayList<Objetivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Objetivo> attachedObjetivoCollection = new ArrayList<Objetivo>();
            for (Objetivo objetivoCollectionObjetivoToAttach : usuario.getObjetivoCollection()) {
                objetivoCollectionObjetivoToAttach = em.getReference(objetivoCollectionObjetivoToAttach.getClass(), objetivoCollectionObjetivoToAttach.getCodigoObjetivo());
                attachedObjetivoCollection.add(objetivoCollectionObjetivoToAttach);
            }
            usuario.setObjetivoCollection(attachedObjetivoCollection);
            em.persist(usuario);
            for (Objetivo objetivoCollectionObjetivo : usuario.getObjetivoCollection()) {
                Usuario oldCreadorObjetivoOfObjetivoCollectionObjetivo = objetivoCollectionObjetivo.getCreadorObjetivo();
                objetivoCollectionObjetivo.setCreadorObjetivo(usuario);
                objetivoCollectionObjetivo = em.merge(objetivoCollectionObjetivo);
                if (oldCreadorObjetivoOfObjetivoCollectionObjetivo != null) {
                    oldCreadorObjetivoOfObjetivoCollectionObjetivo.getObjetivoCollection().remove(objetivoCollectionObjetivo);
                    oldCreadorObjetivoOfObjetivoCollectionObjetivo = em.merge(oldCreadorObjetivoOfObjetivoCollectionObjetivo);
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
            Collection<Objetivo> objetivoCollectionOld = persistentUsuario.getObjetivoCollection();
            Collection<Objetivo> objetivoCollectionNew = usuario.getObjetivoCollection();
            List<String> illegalOrphanMessages = null;
            for (Objetivo objetivoCollectionOldObjetivo : objetivoCollectionOld) {
                if (!objetivoCollectionNew.contains(objetivoCollectionOldObjetivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivo " + objetivoCollectionOldObjetivo + " since its creadorObjetivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Objetivo> attachedObjetivoCollectionNew = new ArrayList<Objetivo>();
            for (Objetivo objetivoCollectionNewObjetivoToAttach : objetivoCollectionNew) {
                objetivoCollectionNewObjetivoToAttach = em.getReference(objetivoCollectionNewObjetivoToAttach.getClass(), objetivoCollectionNewObjetivoToAttach.getCodigoObjetivo());
                attachedObjetivoCollectionNew.add(objetivoCollectionNewObjetivoToAttach);
            }
            objetivoCollectionNew = attachedObjetivoCollectionNew;
            usuario.setObjetivoCollection(objetivoCollectionNew);
            usuario = em.merge(usuario);
            for (Objetivo objetivoCollectionNewObjetivo : objetivoCollectionNew) {
                if (!objetivoCollectionOld.contains(objetivoCollectionNewObjetivo)) {
                    Usuario oldCreadorObjetivoOfObjetivoCollectionNewObjetivo = objetivoCollectionNewObjetivo.getCreadorObjetivo();
                    objetivoCollectionNewObjetivo.setCreadorObjetivo(usuario);
                    objetivoCollectionNewObjetivo = em.merge(objetivoCollectionNewObjetivo);
                    if (oldCreadorObjetivoOfObjetivoCollectionNewObjetivo != null && !oldCreadorObjetivoOfObjetivoCollectionNewObjetivo.equals(usuario)) {
                        oldCreadorObjetivoOfObjetivoCollectionNewObjetivo.getObjetivoCollection().remove(objetivoCollectionNewObjetivo);
                        oldCreadorObjetivoOfObjetivoCollectionNewObjetivo = em.merge(oldCreadorObjetivoOfObjetivoCollectionNewObjetivo);
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
            Collection<Objetivo> objetivoCollectionOrphanCheck = usuario.getObjetivoCollection();
            for (Objetivo objetivoCollectionOrphanCheckObjetivo : objetivoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Objetivo " + objetivoCollectionOrphanCheckObjetivo + " in its objetivoCollection field has a non-nullable creadorObjetivo field.");
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
