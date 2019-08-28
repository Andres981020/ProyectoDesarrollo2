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
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        if (objetivo.getIndicadorCollection() == null) {
            objetivo.setIndicadorCollection(new ArrayList<Indicador>());
        }
        if (objetivo.getIniciativaCollection() == null) {
            objetivo.setIniciativaCollection(new ArrayList<Iniciativa>());
        }
        if (objetivo.getMetaCollection() == null) {
            objetivo.setMetaCollection(new ArrayList<Meta>());
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
            Collection<Indicador> attachedIndicadorCollection = new ArrayList<Indicador>();
            for (Indicador indicadorCollectionIndicadorToAttach : objetivo.getIndicadorCollection()) {
                indicadorCollectionIndicadorToAttach = em.getReference(indicadorCollectionIndicadorToAttach.getClass(), indicadorCollectionIndicadorToAttach.getCodigoIndicador());
                attachedIndicadorCollection.add(indicadorCollectionIndicadorToAttach);
            }
            objetivo.setIndicadorCollection(attachedIndicadorCollection);
            Collection<Iniciativa> attachedIniciativaCollection = new ArrayList<Iniciativa>();
            for (Iniciativa iniciativaCollectionIniciativaToAttach : objetivo.getIniciativaCollection()) {
                iniciativaCollectionIniciativaToAttach = em.getReference(iniciativaCollectionIniciativaToAttach.getClass(), iniciativaCollectionIniciativaToAttach.getCodigoIniciativa());
                attachedIniciativaCollection.add(iniciativaCollectionIniciativaToAttach);
            }
            objetivo.setIniciativaCollection(attachedIniciativaCollection);
            Collection<Meta> attachedMetaCollection = new ArrayList<Meta>();
            for (Meta metaCollectionMetaToAttach : objetivo.getMetaCollection()) {
                metaCollectionMetaToAttach = em.getReference(metaCollectionMetaToAttach.getClass(), metaCollectionMetaToAttach.getCodigoMeta());
                attachedMetaCollection.add(metaCollectionMetaToAttach);
            }
            objetivo.setMetaCollection(attachedMetaCollection);
            em.persist(objetivo);
            if (creadorObjetivo != null) {
                creadorObjetivo.getObjetivoCollection().add(objetivo);
                creadorObjetivo = em.merge(creadorObjetivo);
            }
            for (Indicador indicadorCollectionIndicador : objetivo.getIndicadorCollection()) {
                Objetivo oldIndicadorObjetivoOfIndicadorCollectionIndicador = indicadorCollectionIndicador.getIndicadorObjetivo();
                indicadorCollectionIndicador.setIndicadorObjetivo(objetivo);
                indicadorCollectionIndicador = em.merge(indicadorCollectionIndicador);
                if (oldIndicadorObjetivoOfIndicadorCollectionIndicador != null) {
                    oldIndicadorObjetivoOfIndicadorCollectionIndicador.getIndicadorCollection().remove(indicadorCollectionIndicador);
                    oldIndicadorObjetivoOfIndicadorCollectionIndicador = em.merge(oldIndicadorObjetivoOfIndicadorCollectionIndicador);
                }
            }
            for (Iniciativa iniciativaCollectionIniciativa : objetivo.getIniciativaCollection()) {
                Objetivo oldIniciativaObjetivoOfIniciativaCollectionIniciativa = iniciativaCollectionIniciativa.getIniciativaObjetivo();
                iniciativaCollectionIniciativa.setIniciativaObjetivo(objetivo);
                iniciativaCollectionIniciativa = em.merge(iniciativaCollectionIniciativa);
                if (oldIniciativaObjetivoOfIniciativaCollectionIniciativa != null) {
                    oldIniciativaObjetivoOfIniciativaCollectionIniciativa.getIniciativaCollection().remove(iniciativaCollectionIniciativa);
                    oldIniciativaObjetivoOfIniciativaCollectionIniciativa = em.merge(oldIniciativaObjetivoOfIniciativaCollectionIniciativa);
                }
            }
            for (Meta metaCollectionMeta : objetivo.getMetaCollection()) {
                Objetivo oldMetaObjetivoOfMetaCollectionMeta = metaCollectionMeta.getMetaObjetivo();
                metaCollectionMeta.setMetaObjetivo(objetivo);
                metaCollectionMeta = em.merge(metaCollectionMeta);
                if (oldMetaObjetivoOfMetaCollectionMeta != null) {
                    oldMetaObjetivoOfMetaCollectionMeta.getMetaCollection().remove(metaCollectionMeta);
                    oldMetaObjetivoOfMetaCollectionMeta = em.merge(oldMetaObjetivoOfMetaCollectionMeta);
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
            Collection<Indicador> indicadorCollectionOld = persistentObjetivo.getIndicadorCollection();
            Collection<Indicador> indicadorCollectionNew = objetivo.getIndicadorCollection();
            Collection<Iniciativa> iniciativaCollectionOld = persistentObjetivo.getIniciativaCollection();
            Collection<Iniciativa> iniciativaCollectionNew = objetivo.getIniciativaCollection();
            Collection<Meta> metaCollectionOld = persistentObjetivo.getMetaCollection();
            Collection<Meta> metaCollectionNew = objetivo.getMetaCollection();
            List<String> illegalOrphanMessages = null;
            for (Indicador indicadorCollectionOldIndicador : indicadorCollectionOld) {
                if (!indicadorCollectionNew.contains(indicadorCollectionOldIndicador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Indicador " + indicadorCollectionOldIndicador + " since its indicadorObjetivo field is not nullable.");
                }
            }
            for (Iniciativa iniciativaCollectionOldIniciativa : iniciativaCollectionOld) {
                if (!iniciativaCollectionNew.contains(iniciativaCollectionOldIniciativa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Iniciativa " + iniciativaCollectionOldIniciativa + " since its iniciativaObjetivo field is not nullable.");
                }
            }
            for (Meta metaCollectionOldMeta : metaCollectionOld) {
                if (!metaCollectionNew.contains(metaCollectionOldMeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Meta " + metaCollectionOldMeta + " since its metaObjetivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creadorObjetivoNew != null) {
                creadorObjetivoNew = em.getReference(creadorObjetivoNew.getClass(), creadorObjetivoNew.getCedula());
                objetivo.setCreadorObjetivo(creadorObjetivoNew);
            }
            Collection<Indicador> attachedIndicadorCollectionNew = new ArrayList<Indicador>();
            for (Indicador indicadorCollectionNewIndicadorToAttach : indicadorCollectionNew) {
                indicadorCollectionNewIndicadorToAttach = em.getReference(indicadorCollectionNewIndicadorToAttach.getClass(), indicadorCollectionNewIndicadorToAttach.getCodigoIndicador());
                attachedIndicadorCollectionNew.add(indicadorCollectionNewIndicadorToAttach);
            }
            indicadorCollectionNew = attachedIndicadorCollectionNew;
            objetivo.setIndicadorCollection(indicadorCollectionNew);
            Collection<Iniciativa> attachedIniciativaCollectionNew = new ArrayList<Iniciativa>();
            for (Iniciativa iniciativaCollectionNewIniciativaToAttach : iniciativaCollectionNew) {
                iniciativaCollectionNewIniciativaToAttach = em.getReference(iniciativaCollectionNewIniciativaToAttach.getClass(), iniciativaCollectionNewIniciativaToAttach.getCodigoIniciativa());
                attachedIniciativaCollectionNew.add(iniciativaCollectionNewIniciativaToAttach);
            }
            iniciativaCollectionNew = attachedIniciativaCollectionNew;
            objetivo.setIniciativaCollection(iniciativaCollectionNew);
            Collection<Meta> attachedMetaCollectionNew = new ArrayList<Meta>();
            for (Meta metaCollectionNewMetaToAttach : metaCollectionNew) {
                metaCollectionNewMetaToAttach = em.getReference(metaCollectionNewMetaToAttach.getClass(), metaCollectionNewMetaToAttach.getCodigoMeta());
                attachedMetaCollectionNew.add(metaCollectionNewMetaToAttach);
            }
            metaCollectionNew = attachedMetaCollectionNew;
            objetivo.setMetaCollection(metaCollectionNew);
            objetivo = em.merge(objetivo);
            if (creadorObjetivoOld != null && !creadorObjetivoOld.equals(creadorObjetivoNew)) {
                creadorObjetivoOld.getObjetivoCollection().remove(objetivo);
                creadorObjetivoOld = em.merge(creadorObjetivoOld);
            }
            if (creadorObjetivoNew != null && !creadorObjetivoNew.equals(creadorObjetivoOld)) {
                creadorObjetivoNew.getObjetivoCollection().add(objetivo);
                creadorObjetivoNew = em.merge(creadorObjetivoNew);
            }
            for (Indicador indicadorCollectionNewIndicador : indicadorCollectionNew) {
                if (!indicadorCollectionOld.contains(indicadorCollectionNewIndicador)) {
                    Objetivo oldIndicadorObjetivoOfIndicadorCollectionNewIndicador = indicadorCollectionNewIndicador.getIndicadorObjetivo();
                    indicadorCollectionNewIndicador.setIndicadorObjetivo(objetivo);
                    indicadorCollectionNewIndicador = em.merge(indicadorCollectionNewIndicador);
                    if (oldIndicadorObjetivoOfIndicadorCollectionNewIndicador != null && !oldIndicadorObjetivoOfIndicadorCollectionNewIndicador.equals(objetivo)) {
                        oldIndicadorObjetivoOfIndicadorCollectionNewIndicador.getIndicadorCollection().remove(indicadorCollectionNewIndicador);
                        oldIndicadorObjetivoOfIndicadorCollectionNewIndicador = em.merge(oldIndicadorObjetivoOfIndicadorCollectionNewIndicador);
                    }
                }
            }
            for (Iniciativa iniciativaCollectionNewIniciativa : iniciativaCollectionNew) {
                if (!iniciativaCollectionOld.contains(iniciativaCollectionNewIniciativa)) {
                    Objetivo oldIniciativaObjetivoOfIniciativaCollectionNewIniciativa = iniciativaCollectionNewIniciativa.getIniciativaObjetivo();
                    iniciativaCollectionNewIniciativa.setIniciativaObjetivo(objetivo);
                    iniciativaCollectionNewIniciativa = em.merge(iniciativaCollectionNewIniciativa);
                    if (oldIniciativaObjetivoOfIniciativaCollectionNewIniciativa != null && !oldIniciativaObjetivoOfIniciativaCollectionNewIniciativa.equals(objetivo)) {
                        oldIniciativaObjetivoOfIniciativaCollectionNewIniciativa.getIniciativaCollection().remove(iniciativaCollectionNewIniciativa);
                        oldIniciativaObjetivoOfIniciativaCollectionNewIniciativa = em.merge(oldIniciativaObjetivoOfIniciativaCollectionNewIniciativa);
                    }
                }
            }
            for (Meta metaCollectionNewMeta : metaCollectionNew) {
                if (!metaCollectionOld.contains(metaCollectionNewMeta)) {
                    Objetivo oldMetaObjetivoOfMetaCollectionNewMeta = metaCollectionNewMeta.getMetaObjetivo();
                    metaCollectionNewMeta.setMetaObjetivo(objetivo);
                    metaCollectionNewMeta = em.merge(metaCollectionNewMeta);
                    if (oldMetaObjetivoOfMetaCollectionNewMeta != null && !oldMetaObjetivoOfMetaCollectionNewMeta.equals(objetivo)) {
                        oldMetaObjetivoOfMetaCollectionNewMeta.getMetaCollection().remove(metaCollectionNewMeta);
                        oldMetaObjetivoOfMetaCollectionNewMeta = em.merge(oldMetaObjetivoOfMetaCollectionNewMeta);
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
            Collection<Indicador> indicadorCollectionOrphanCheck = objetivo.getIndicadorCollection();
            for (Indicador indicadorCollectionOrphanCheckIndicador : indicadorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Indicador " + indicadorCollectionOrphanCheckIndicador + " in its indicadorCollection field has a non-nullable indicadorObjetivo field.");
            }
            Collection<Iniciativa> iniciativaCollectionOrphanCheck = objetivo.getIniciativaCollection();
            for (Iniciativa iniciativaCollectionOrphanCheckIniciativa : iniciativaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Iniciativa " + iniciativaCollectionOrphanCheckIniciativa + " in its iniciativaCollection field has a non-nullable iniciativaObjetivo field.");
            }
            Collection<Meta> metaCollectionOrphanCheck = objetivo.getMetaCollection();
            for (Meta metaCollectionOrphanCheckMeta : metaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Meta " + metaCollectionOrphanCheckMeta + " in its metaCollection field has a non-nullable metaObjetivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario creadorObjetivo = objetivo.getCreadorObjetivo();
            if (creadorObjetivo != null) {
                creadorObjetivo.getObjetivoCollection().remove(objetivo);
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
