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
import modelo.Usuario;
import modelo.Indicador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class ObjetivoJpaController implements Serializable {

    public ObjetivoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Desarrollo2PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivo objetivo) throws PreexistingEntityException, Exception {
        if (objetivo.getIndicadorList() == null) {
            objetivo.setIndicadorList(new ArrayList<Indicador>());
        }
        if (objetivo.getIniciativaList() == null) {
            objetivo.setIniciativaList(new ArrayList<Iniciativa>());
        }
        if (objetivo.getMetaList() == null) {
            objetivo.setMetaList(new ArrayList<Meta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadorObjetivo = objetivo.getCreadorObjetivo();
            if (creadorObjetivo != null) {
                creadorObjetivo = em.getReference(creadorObjetivo.getClass(), creadorObjetivo.getCedula());
                objetivo.setCreadorObjetivo(creadorObjetivo);
            }
            List<Indicador> attachedIndicadorList = new ArrayList<Indicador>();
            for (Indicador indicadorListIndicadorToAttach : objetivo.getIndicadorList()) {
                indicadorListIndicadorToAttach = em.getReference(indicadorListIndicadorToAttach.getClass(), indicadorListIndicadorToAttach.getCodigoIndicador());
                attachedIndicadorList.add(indicadorListIndicadorToAttach);
            }
            objetivo.setIndicadorList(attachedIndicadorList);
            List<Iniciativa> attachedIniciativaList = new ArrayList<Iniciativa>();
            for (Iniciativa iniciativaListIniciativaToAttach : objetivo.getIniciativaList()) {
                iniciativaListIniciativaToAttach = em.getReference(iniciativaListIniciativaToAttach.getClass(), iniciativaListIniciativaToAttach.getCodigoIniciativa());
                attachedIniciativaList.add(iniciativaListIniciativaToAttach);
            }
            objetivo.setIniciativaList(attachedIniciativaList);
            List<Meta> attachedMetaList = new ArrayList<Meta>();
            for (Meta metaListMetaToAttach : objetivo.getMetaList()) {
                metaListMetaToAttach = em.getReference(metaListMetaToAttach.getClass(), metaListMetaToAttach.getCodigoMeta());
                attachedMetaList.add(metaListMetaToAttach);
            }
            objetivo.setMetaList(attachedMetaList);
            em.persist(objetivo);
            if (creadorObjetivo != null) {
                creadorObjetivo.getObjetivoList().add(objetivo);
                creadorObjetivo = em.merge(creadorObjetivo);
            }
            for (Indicador indicadorListIndicador : objetivo.getIndicadorList()) {
                Objetivo oldIndicadorObjetivoOfIndicadorListIndicador = indicadorListIndicador.getIndicadorObjetivo();
                indicadorListIndicador.setIndicadorObjetivo(objetivo);
                indicadorListIndicador = em.merge(indicadorListIndicador);
                if (oldIndicadorObjetivoOfIndicadorListIndicador != null) {
                    oldIndicadorObjetivoOfIndicadorListIndicador.getIndicadorList().remove(indicadorListIndicador);
                    oldIndicadorObjetivoOfIndicadorListIndicador = em.merge(oldIndicadorObjetivoOfIndicadorListIndicador);
                }
            }
            for (Iniciativa iniciativaListIniciativa : objetivo.getIniciativaList()) {
                Objetivo oldIniciativaObjetivoOfIniciativaListIniciativa = iniciativaListIniciativa.getIniciativaObjetivo();
                iniciativaListIniciativa.setIniciativaObjetivo(objetivo);
                iniciativaListIniciativa = em.merge(iniciativaListIniciativa);
                if (oldIniciativaObjetivoOfIniciativaListIniciativa != null) {
                    oldIniciativaObjetivoOfIniciativaListIniciativa.getIniciativaList().remove(iniciativaListIniciativa);
                    oldIniciativaObjetivoOfIniciativaListIniciativa = em.merge(oldIniciativaObjetivoOfIniciativaListIniciativa);
                }
            }
            for (Meta metaListMeta : objetivo.getMetaList()) {
                Objetivo oldMetaObjetivoOfMetaListMeta = metaListMeta.getMetaObjetivo();
                metaListMeta.setMetaObjetivo(objetivo);
                metaListMeta = em.merge(metaListMeta);
                if (oldMetaObjetivoOfMetaListMeta != null) {
                    oldMetaObjetivoOfMetaListMeta.getMetaList().remove(metaListMeta);
                    oldMetaObjetivoOfMetaListMeta = em.merge(oldMetaObjetivoOfMetaListMeta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetivo(objetivo.getCodigoObjetivo()) != null) {
                throw new PreexistingEntityException("Objetivo " + objetivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivo objetivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo persistentObjetivo = em.find(Objetivo.class, objetivo.getCodigoObjetivo());
            Usuario creadorObjetivoOld = persistentObjetivo.getCreadorObjetivo();
            Usuario creadorObjetivoNew = objetivo.getCreadorObjetivo();
            List<Indicador> indicadorListOld = persistentObjetivo.getIndicadorList();
            List<Indicador> indicadorListNew = objetivo.getIndicadorList();
            List<Iniciativa> iniciativaListOld = persistentObjetivo.getIniciativaList();
            List<Iniciativa> iniciativaListNew = objetivo.getIniciativaList();
            List<Meta> metaListOld = persistentObjetivo.getMetaList();
            List<Meta> metaListNew = objetivo.getMetaList();
            List<String> illegalOrphanMessages = null;
            for (Indicador indicadorListOldIndicador : indicadorListOld) {
                if (!indicadorListNew.contains(indicadorListOldIndicador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Indicador " + indicadorListOldIndicador + " since its indicadorObjetivo field is not nullable.");
                }
            }
            for (Iniciativa iniciativaListOldIniciativa : iniciativaListOld) {
                if (!iniciativaListNew.contains(iniciativaListOldIniciativa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Iniciativa " + iniciativaListOldIniciativa + " since its iniciativaObjetivo field is not nullable.");
                }
            }
            for (Meta metaListOldMeta : metaListOld) {
                if (!metaListNew.contains(metaListOldMeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Meta " + metaListOldMeta + " since its metaObjetivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creadorObjetivoNew != null) {
                creadorObjetivoNew = em.getReference(creadorObjetivoNew.getClass(), creadorObjetivoNew.getCedula());
                objetivo.setCreadorObjetivo(creadorObjetivoNew);
            }
            List<Indicador> attachedIndicadorListNew = new ArrayList<Indicador>();
            for (Indicador indicadorListNewIndicadorToAttach : indicadorListNew) {
                indicadorListNewIndicadorToAttach = em.getReference(indicadorListNewIndicadorToAttach.getClass(), indicadorListNewIndicadorToAttach.getCodigoIndicador());
                attachedIndicadorListNew.add(indicadorListNewIndicadorToAttach);
            }
            indicadorListNew = attachedIndicadorListNew;
            objetivo.setIndicadorList(indicadorListNew);
            List<Iniciativa> attachedIniciativaListNew = new ArrayList<Iniciativa>();
            for (Iniciativa iniciativaListNewIniciativaToAttach : iniciativaListNew) {
                iniciativaListNewIniciativaToAttach = em.getReference(iniciativaListNewIniciativaToAttach.getClass(), iniciativaListNewIniciativaToAttach.getCodigoIniciativa());
                attachedIniciativaListNew.add(iniciativaListNewIniciativaToAttach);
            }
            iniciativaListNew = attachedIniciativaListNew;
            objetivo.setIniciativaList(iniciativaListNew);
            List<Meta> attachedMetaListNew = new ArrayList<Meta>();
            for (Meta metaListNewMetaToAttach : metaListNew) {
                metaListNewMetaToAttach = em.getReference(metaListNewMetaToAttach.getClass(), metaListNewMetaToAttach.getCodigoMeta());
                attachedMetaListNew.add(metaListNewMetaToAttach);
            }
            metaListNew = attachedMetaListNew;
            objetivo.setMetaList(metaListNew);
            objetivo = em.merge(objetivo);
            if (creadorObjetivoOld != null && !creadorObjetivoOld.equals(creadorObjetivoNew)) {
                creadorObjetivoOld.getObjetivoList().remove(objetivo);
                creadorObjetivoOld = em.merge(creadorObjetivoOld);
            }
            if (creadorObjetivoNew != null && !creadorObjetivoNew.equals(creadorObjetivoOld)) {
                creadorObjetivoNew.getObjetivoList().add(objetivo);
                creadorObjetivoNew = em.merge(creadorObjetivoNew);
            }
            for (Indicador indicadorListNewIndicador : indicadorListNew) {
                if (!indicadorListOld.contains(indicadorListNewIndicador)) {
                    Objetivo oldIndicadorObjetivoOfIndicadorListNewIndicador = indicadorListNewIndicador.getIndicadorObjetivo();
                    indicadorListNewIndicador.setIndicadorObjetivo(objetivo);
                    indicadorListNewIndicador = em.merge(indicadorListNewIndicador);
                    if (oldIndicadorObjetivoOfIndicadorListNewIndicador != null && !oldIndicadorObjetivoOfIndicadorListNewIndicador.equals(objetivo)) {
                        oldIndicadorObjetivoOfIndicadorListNewIndicador.getIndicadorList().remove(indicadorListNewIndicador);
                        oldIndicadorObjetivoOfIndicadorListNewIndicador = em.merge(oldIndicadorObjetivoOfIndicadorListNewIndicador);
                    }
                }
            }
            for (Iniciativa iniciativaListNewIniciativa : iniciativaListNew) {
                if (!iniciativaListOld.contains(iniciativaListNewIniciativa)) {
                    Objetivo oldIniciativaObjetivoOfIniciativaListNewIniciativa = iniciativaListNewIniciativa.getIniciativaObjetivo();
                    iniciativaListNewIniciativa.setIniciativaObjetivo(objetivo);
                    iniciativaListNewIniciativa = em.merge(iniciativaListNewIniciativa);
                    if (oldIniciativaObjetivoOfIniciativaListNewIniciativa != null && !oldIniciativaObjetivoOfIniciativaListNewIniciativa.equals(objetivo)) {
                        oldIniciativaObjetivoOfIniciativaListNewIniciativa.getIniciativaList().remove(iniciativaListNewIniciativa);
                        oldIniciativaObjetivoOfIniciativaListNewIniciativa = em.merge(oldIniciativaObjetivoOfIniciativaListNewIniciativa);
                    }
                }
            }
            for (Meta metaListNewMeta : metaListNew) {
                if (!metaListOld.contains(metaListNewMeta)) {
                    Objetivo oldMetaObjetivoOfMetaListNewMeta = metaListNewMeta.getMetaObjetivo();
                    metaListNewMeta.setMetaObjetivo(objetivo);
                    metaListNewMeta = em.merge(metaListNewMeta);
                    if (oldMetaObjetivoOfMetaListNewMeta != null && !oldMetaObjetivoOfMetaListNewMeta.equals(objetivo)) {
                        oldMetaObjetivoOfMetaListNewMeta.getMetaList().remove(metaListNewMeta);
                        oldMetaObjetivoOfMetaListNewMeta = em.merge(oldMetaObjetivoOfMetaListNewMeta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = objetivo.getCodigoObjetivo();
                if (findObjetivo(id) == null) {
                    throw new NonexistentEntityException("The objetivo with id " + id + " no longer exists.");
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
            Objetivo objetivo;
            try {
                objetivo = em.getReference(Objetivo.class, id);
                objetivo.getCodigoObjetivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Indicador> indicadorListOrphanCheck = objetivo.getIndicadorList();
            for (Indicador indicadorListOrphanCheckIndicador : indicadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Indicador " + indicadorListOrphanCheckIndicador + " in its indicadorList field has a non-nullable indicadorObjetivo field.");
            }
            List<Iniciativa> iniciativaListOrphanCheck = objetivo.getIniciativaList();
            for (Iniciativa iniciativaListOrphanCheckIniciativa : iniciativaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Iniciativa " + iniciativaListOrphanCheckIniciativa + " in its iniciativaList field has a non-nullable iniciativaObjetivo field.");
            }
            List<Meta> metaListOrphanCheck = objetivo.getMetaList();
            for (Meta metaListOrphanCheckMeta : metaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Meta " + metaListOrphanCheckMeta + " in its metaList field has a non-nullable metaObjetivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario creadorObjetivo = objetivo.getCreadorObjetivo();
            if (creadorObjetivo != null) {
                creadorObjetivo.getObjetivoList().remove(objetivo);
                creadorObjetivo = em.merge(creadorObjetivo);
            }
            em.remove(objetivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivo> findObjetivoEntities() {
        return findObjetivoEntities(true, -1, -1);
    }

    public List<Objetivo> findObjetivoEntities(int maxResults, int firstResult) {
        return findObjetivoEntities(false, maxResults, firstResult);
    }

    private List<Objetivo> findObjetivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivo.class));
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

    public Objetivo findObjetivo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivo> rt = cq.from(Objetivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
