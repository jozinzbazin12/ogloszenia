package projektOgloszenia.jpa.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import projektOgloszenia.jpa.dao.OgloszenieDao;
import projektOgloszenia.models.Ogloszenie;

@Stateless
public class OgloszenieDaoImpl extends GenericJpaDao<Ogloszenie, Integer> implements OgloszenieDao {

	@Override
	public List<Ogloszenie> findAllUserOgloszenie(String user) {
		EntityManager em = getEntityManager();
		TypedQuery<Ogloszenie> query = em.createQuery("SELECT o from Ogloszenie o where o.user.login=" + user, Ogloszenie.class);
		em.close();
		return query.getResultList();
	}

}