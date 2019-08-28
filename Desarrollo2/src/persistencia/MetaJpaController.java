/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Meta;
import modelo.Objetivo;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class MetaJpaController implements Serializable {

    public MetaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Desarrollo2PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Meta meta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo metaObjetivo = meta.getMetaObjetivo();
            if (metaObjetivo != null) {
                metaObjetivo = em.getReference(metaObjetivo.getClass(), metaObjetivo.getCodigoObjetivo());
                meta.setMetaObjetivo(metaObjetivo);
            }
            em.persist(meta);
            if (metaObjetivo != null) {
                metaObjetivo.getMetaCollection().add(meta);
                metaObjetivo = em.merge(metaObjetivo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMeta(meta.getCodigoMeta()) != null) {
                throw new PreexistingEntityException("Meta " + meta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Meta meta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meta persistentMeta = em.find(Meta.class, meta.getCodigoMeta());
            Objetivo metaObjetivoOld = persistentMeta.getMetaObjetivo();
            Objetivo metaObjetivoNew = meta.getMetaObjetivo();
            if (metaObjetivoNew != null) {
                metaObjetivoNew = em.getReference(metaObjetivoNew.getClass(), metaObjetivoNew.getCodigoObjetivo());
                meta.setMetaObjetivo(metaObjetivoNew);
            }
            meta = em.merge(meta);
            if (metaObjetivoOld != null && !metaObjetivoOld.equals(metaObjetivoNew)) {
                metaObjetivoOld.getMetaCollection().remove(meta);
                metaObjetivoOld = em.merge(metaObjetivoOld);
            }
            if (metaObjetivoNew != null && !metaObjetivoNew.equals(metaObjetivoOld)) {
                metaObjetivoNew.getMetaCollection().add(meta);
                metaObjetivoNew = em.merge(metaObjetivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = meta.getCodigoMeta();
                if (findMeta(id) == null) {
                    throw new NonexistentEntityException("The meta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meta meta;
            try {
                meta = em.getReference(Meta.class, id);
                meta.getCodigoMeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The meta with id " + id + " no longer exists.", enfe);
            }
            Objetivo metaObjetivo = meta.getMetaObjetivo();
            if (metaObjetivo != null) {
                metaObjetivo.getMetaCollection().remove(meta);
                metaObjetivo = em.merge(metaObjetivo);
            }
            em.remove(meta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Meta> findMetaEntities() {
        return findMetaEntities(true, -1, -1);
    }

    public List<Meta> findMetaEntities(int maxResults, int firstResult) {
        return findMetaEntities(false, maxResults, firstResult);
    }

    private List<Meta> findMetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Meta.class));
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

    public Meta findMeta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Meta.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Meta> rt = cq.from(Meta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
