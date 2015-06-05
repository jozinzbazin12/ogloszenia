package projektOgloszenia.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projektOgloszenia.jpa.dao.GenericDao;
import projektOgloszenia.util.JpaFactory;

public abstract class GenericJpaDao<T, K> implements GenericDao<T, K> {

	private Class<T> type = null;

	public GenericJpaDao(Class<T> type) {
		this.type = type;
	}

	public GenericJpaDao() {
	}

	protected EntityManager getEntityManager() {
		return JpaFactory.getEntityManager();
	}

	public void save(T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();

	}

	public void delete(T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		t = em.merge(t);
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}

	public void update(T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	public T findById(K id) {
		try {
			EntityManager em = getEntityManager();
			T dto = em.find(type, id);
			em.close();
			return dto;
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			EntityManager em = getEntityManager();
			Query dto = em.createQuery("SELECT x from " + type.getSimpleName()+" x", type);
			List<T> resultList = dto.getResultList();
			em.close(); 
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
