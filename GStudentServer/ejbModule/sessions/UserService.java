package sessions;

import dao.IDao;
import dao.IDaoLocal;
import entities.User;
import jakarta.annotation.security.PermitAll;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

/**
 * Session Bean implementation class UserService
 */
@Stateless(name = "us")
public class UserService implements IDao<User>,IDaoLocal<User> {

    
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@PermitAll
    public void delete(User o) {
        em.remove(o);
    }

	@Override
	@PermitAll
    public List<User> findAll() {
    	Query query = em.createQuery("select u from User u");
		return query.getResultList();
    }

	@Override
	@PermitAll
    public User create(User o) {
		em.persist(o);
		return o;
    }

	@Override
	@PermitAll
    public User update(User o) {
    	em.persist(o);
		 return o;
    }

	@Override
	@PermitAll
	public User findById(int id) {
	    Query query = em.createQuery("select u from User u where u.id = :id");
	    query.setParameter("id", id);
	    List<User> resultList = query.getResultList();
	    if (resultList != null && !resultList.isEmpty()) {
	        return resultList.get(0); 
	    } else {
	        return null;
	    }
	}


}
