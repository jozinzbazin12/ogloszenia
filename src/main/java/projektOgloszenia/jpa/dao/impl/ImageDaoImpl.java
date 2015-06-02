package projektOgloszenia.jpa.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import projektOgloszenia.jpa.dao.ImageDao;
import projektOgloszenia.models.Image;
import projektOgloszenia.models.Ogloszenie;

@Stateless
public class ImageDaoImpl extends GenericJpaDao<Image, Integer> implements ImageDao {

	@Override
	public List<Image> findImagesByOgloszenie(Ogloszenie o) {
		EntityManager em = getEntityManager();
		TypedQuery<Image> query = em.createQuery("SELECT i from Image join Ogloszenie o where o=" + o, Image.class);
		em.close();
		return query.getResultList();

	}

}