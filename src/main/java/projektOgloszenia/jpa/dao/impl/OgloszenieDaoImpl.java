package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import projektOgloszenia.jpa.dao.OgloszenieDao;
import projektOgloszenia.models.Ogloszenie;

@Named
@ApplicationScoped
public class OgloszenieDaoImpl extends GenericJpaDao<Ogloszenie, Integer> implements OgloszenieDao, Serializable {

	private static final long serialVersionUID = -1554759164624362309L;

	@Override
	public List<Ogloszenie> findAllUserOgloszenie(String user) {
		EntityManager em = getEntityManager();
		TypedQuery<Ogloszenie> query = em.createQuery("SELECT o from Ogloszenie o where o.user.login=" + user, Ogloszenie.class);
		return query.getResultList();
	}

	public OgloszenieDaoImpl() {
		super(Ogloszenie.class);
	}

	@Override
	public List<Ogloszenie> createQuery(String pytanie) {
		EntityManager em = getEntityManager();
		TypedQuery<Ogloszenie> query = em.createQuery(pytanie, Ogloszenie.class);
		return query.getResultList();
	}

}