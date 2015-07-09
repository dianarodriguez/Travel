/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Viaje;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.Pasaje;
import java.util.ArrayList;
import java.util.List;
import datos.Reservacion;
import negocio.jpa.exceptions.IllegalOrphanException;
import negocio.jpa.exceptions.NonexistentEntityException;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class ViajeJpaController {

    public ViajeJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Viaje viaje) throws PreexistingEntityException, Exception {
        if (viaje.getPasajeList() == null) {
            viaje.setPasajeList(new ArrayList<Pasaje>());
        }
        if (viaje.getReservacionList() == null) {
            viaje.setReservacionList(new ArrayList<Reservacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pasaje> attachedPasajeList = new ArrayList<Pasaje>();
            for (Pasaje pasajeListPasajeToAttach : viaje.getPasajeList()) {
                pasajeListPasajeToAttach = em.getReference(pasajeListPasajeToAttach.getClass(), pasajeListPasajeToAttach.getNropasaje());
                attachedPasajeList.add(pasajeListPasajeToAttach);
            }
            viaje.setPasajeList(attachedPasajeList);
            List<Reservacion> attachedReservacionList = new ArrayList<Reservacion>();
            for (Reservacion reservacionListReservacionToAttach : viaje.getReservacionList()) {
                reservacionListReservacionToAttach = em.getReference(reservacionListReservacionToAttach.getClass(), reservacionListReservacionToAttach.getNroreserv());
                attachedReservacionList.add(reservacionListReservacionToAttach);
            }
            viaje.setReservacionList(attachedReservacionList);
            em.persist(viaje);
            for (Pasaje pasajeListPasaje : viaje.getPasajeList()) {
                Viaje oldViajeOfPasajeListPasaje = pasajeListPasaje.getViaje();
                pasajeListPasaje.setViaje(viaje);
                pasajeListPasaje = em.merge(pasajeListPasaje);
                if (oldViajeOfPasajeListPasaje != null) {
                    oldViajeOfPasajeListPasaje.getPasajeList().remove(pasajeListPasaje);
                    oldViajeOfPasajeListPasaje = em.merge(oldViajeOfPasajeListPasaje);
                }
            }
            for (Reservacion reservacionListReservacion : viaje.getReservacionList()) {
                Viaje oldViajeOfReservacionListReservacion = reservacionListReservacion.getViaje();
                reservacionListReservacion.setViaje(viaje);
                reservacionListReservacion = em.merge(reservacionListReservacion);
                if (oldViajeOfReservacionListReservacion != null) {
                    oldViajeOfReservacionListReservacion.getReservacionList().remove(reservacionListReservacion);
                    oldViajeOfReservacionListReservacion = em.merge(oldViajeOfReservacionListReservacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findViaje(viaje.getNroviaje()) != null) {
                throw new PreexistingEntityException("Viaje " + viaje + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Viaje viaje) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viaje persistentViaje = em.find(Viaje.class, viaje.getNroviaje());
            List<Pasaje> pasajeListOld = persistentViaje.getPasajeList();
            List<Pasaje> pasajeListNew = viaje.getPasajeList();
            List<Reservacion> reservacionListOld = persistentViaje.getReservacionList();
            List<Reservacion> reservacionListNew = viaje.getReservacionList();
            List<String> illegalOrphanMessages = null;
            for (Pasaje pasajeListOldPasaje : pasajeListOld) {
                if (!pasajeListNew.contains(pasajeListOldPasaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pasaje " + pasajeListOldPasaje + " since its viaje field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pasaje> attachedPasajeListNew = new ArrayList<Pasaje>();
            for (Pasaje pasajeListNewPasajeToAttach : pasajeListNew) {
                pasajeListNewPasajeToAttach = em.getReference(pasajeListNewPasajeToAttach.getClass(), pasajeListNewPasajeToAttach.getNropasaje());
                attachedPasajeListNew.add(pasajeListNewPasajeToAttach);
            }
            pasajeListNew = attachedPasajeListNew;
            viaje.setPasajeList(pasajeListNew);
            List<Reservacion> attachedReservacionListNew = new ArrayList<Reservacion>();
            for (Reservacion reservacionListNewReservacionToAttach : reservacionListNew) {
                reservacionListNewReservacionToAttach = em.getReference(reservacionListNewReservacionToAttach.getClass(), reservacionListNewReservacionToAttach.getNroreserv());
                attachedReservacionListNew.add(reservacionListNewReservacionToAttach);
            }
            reservacionListNew = attachedReservacionListNew;
            viaje.setReservacionList(reservacionListNew);
            viaje = em.merge(viaje);
            for (Pasaje pasajeListNewPasaje : pasajeListNew) {
                if (!pasajeListOld.contains(pasajeListNewPasaje)) {
                    Viaje oldViajeOfPasajeListNewPasaje = pasajeListNewPasaje.getViaje();
                    pasajeListNewPasaje.setViaje(viaje);
                    pasajeListNewPasaje = em.merge(pasajeListNewPasaje);
                    if (oldViajeOfPasajeListNewPasaje != null && !oldViajeOfPasajeListNewPasaje.equals(viaje)) {
                        oldViajeOfPasajeListNewPasaje.getPasajeList().remove(pasajeListNewPasaje);
                        oldViajeOfPasajeListNewPasaje = em.merge(oldViajeOfPasajeListNewPasaje);
                    }
                }
            }
            for (Reservacion reservacionListOldReservacion : reservacionListOld) {
                if (!reservacionListNew.contains(reservacionListOldReservacion)) {
                    reservacionListOldReservacion.setViaje(null);
                    reservacionListOldReservacion = em.merge(reservacionListOldReservacion);
                }
            }
            for (Reservacion reservacionListNewReservacion : reservacionListNew) {
                if (!reservacionListOld.contains(reservacionListNewReservacion)) {
                    Viaje oldViajeOfReservacionListNewReservacion = reservacionListNewReservacion.getViaje();
                    reservacionListNewReservacion.setViaje(viaje);
                    reservacionListNewReservacion = em.merge(reservacionListNewReservacion);
                    if (oldViajeOfReservacionListNewReservacion != null && !oldViajeOfReservacionListNewReservacion.equals(viaje)) {
                        oldViajeOfReservacionListNewReservacion.getReservacionList().remove(reservacionListNewReservacion);
                        oldViajeOfReservacionListNewReservacion = em.merge(oldViajeOfReservacionListNewReservacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = viaje.getNroviaje();
                if (findViaje(id) == null) {
                    throw new NonexistentEntityException("The viaje with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viaje viaje;
            try {
                viaje = em.getReference(Viaje.class, id);
                viaje.getNroviaje();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viaje with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pasaje> pasajeListOrphanCheck = viaje.getPasajeList();
            for (Pasaje pasajeListOrphanCheckPasaje : pasajeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Viaje (" + viaje + ") cannot be destroyed since the Pasaje " + pasajeListOrphanCheckPasaje + " in its pasajeList field has a non-nullable viaje field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Reservacion> reservacionList = viaje.getReservacionList();
            for (Reservacion reservacionListReservacion : reservacionList) {
                reservacionListReservacion.setViaje(null);
                reservacionListReservacion = em.merge(reservacionListReservacion);
            }
            em.remove(viaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Viaje> findViajeEntities() {
        return findViajeEntities(true, -1, -1);
    }

    public List<Viaje> findViajeEntities(int maxResults, int firstResult) {
        return findViajeEntities(false, maxResults, firstResult);
    }

    private List<Viaje> findViajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Viaje.class));
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

    public Viaje findViaje(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Viaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getViajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Viaje> rt = cq.from(Viaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
