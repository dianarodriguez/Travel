/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Passwords;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.jpa.exceptions.NonexistentEntityException;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class PasswordsJpaController {

    public PasswordsJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Passwords passwords) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(passwords);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasswords(passwords.getId()) != null) {
                throw new PreexistingEntityException("Passwords " + passwords + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Passwords passwords) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            passwords = em.merge(passwords);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = passwords.getId();
                if (findPasswords(id) == null) {
                    throw new NonexistentEntityException("The passwords with id " + id + " no longer exists.");
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
            Passwords passwords;
            try {
                passwords = em.getReference(Passwords.class, id);
                passwords.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The passwords with id " + id + " no longer exists.", enfe);
            }
            em.remove(passwords);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Passwords> findPasswordsEntities() {
        return findPasswordsEntities(true, -1, -1);
    }

    public List<Passwords> findPasswordsEntities(int maxResults, int firstResult) {
        return findPasswordsEntities(false, maxResults, firstResult);
    }

    private List<Passwords> findPasswordsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Passwords.class));
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

    public Passwords findPasswords(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Passwords.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasswordsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Passwords> rt = cq.from(Passwords.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
