package projektOgloszenia.jpa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projektOgloszenia.jpa.dao.GenericDao;
import projektOgloszenia.util.JpaFactory;


public abstract class GenericJpaDao<T, K> implements GenericDao<T, K> {

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericJpaDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
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
		EntityManager em = getEntityManager();
		T dto = em.find(type, id);
		em.close();
		return dto;
	}
	

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		EntityManager em = getEntityManager();
		Query dto = em.createQuery("SELECT x from "+this.getClass().toString(), this.getClass());
		em.close();
		return dto.getResultList();
	}
	
	
}
