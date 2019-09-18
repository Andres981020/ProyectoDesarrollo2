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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Indicador;
import modelo.Objetivo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class IndicadorJpaController implements Serializable {

    public IndicadorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Desarrollo2PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Indicador indicador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo indicadorObjetivo = indicador.getIndicadorObjetivo();
            if (indicadorObjetivo != null) {
                indicadorObjetivo = em.getReference(indicadorObjetivo.getClass(), indicadorObjetivo.getCodigoObjetivo());
                indicador.setIndicadorObjetivo(indicadorObjetivo);
            }
            em.persist(indicador);
            if (indicadorObjetivo != null) {
                indicadorObjetivo.getIndicadorList().add(indicador);
                indicadorObjetivo = em.merge(indicadorObjetivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Indicador indicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador persistentIndicador = em.find(Indicador.class, indicador.getCodigoIndicador());
            Objetivo indicadorObjetivoOld = persistentIndicador.getIndicadorObjetivo();
            Objetivo indicadorObjetivoNew = indicador.getIndicadorObjetivo();
            if (indicadorObjetivoNew != null) {
                indicadorObjetivoNew = em.getReference(indicadorObjetivoNew.getClass(), indicadorObjetivoNew.getCodigoObjetivo());
                indicador.setIndicadorObjetivo(indicadorObjetivoNew);
            }
            indicador = em.merge(indicador);
            if (indicadorObjetivoOld != null && !indicadorObjetivoOld.equals(indicadorObjetivoNew)) {
                indicadorObjetivoOld.getIndicadorList().remove(indicador);
                indicadorObjetivoOld = em.merge(indicadorObjetivoOld);
            }
            if (indicadorObjetivoNew != null && !indicadorObjetivoNew.equals(indicadorObjetivoOld)) {
                indicadorObjetivoNew.getIndicadorList().add(indicador);
                indicadorObjetivoNew = em.merge(indicadorObjetivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = indicador.getCodigoIndicador();
                if (findIndicador(id) == null) {
                    throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicador;
            try {
                indicador = em.getReference(Indicador.class, id);
                indicador.getCodigoIndicador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.", enfe);
            }
            Objetivo indicadorObjetivo = indicador.getIndicadorObjetivo();
            if (indicadorObjetivo != null) {
                indicadorObjetivo.getIndicadorList().remove(indicador);
                indicadorObjetivo = em.merge(indicadorObjetivo);
            }
            em.remove(indicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Indicador> findIndicadorEntities() {
        return findIndicadorEntities(true, -1, -1);
    }

    public List<Indicador> findIndicadorEntities(int maxResults, int firstResult) {
        return findIndicadorEntities(false, maxResults, firstResult);
    }

    private List<Indicador> findIndicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Indicador.class));
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

    public Indicador findIndicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Indicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndicadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Indicador> rt = cq.from(Indicador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Indicador adquirirIndicador(String descripcion){
        String consulta = "select i from Indicador i "
                + "where i.getDescripcionIndicador()= '"+descripcion+"'";
        
        try{
        EntityManager em = getEntityManager();
        Query query = em.createQuery(consulta);
        return (Indicador) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
