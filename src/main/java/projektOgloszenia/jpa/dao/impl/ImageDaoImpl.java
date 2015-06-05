package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import projektOgloszenia.jpa.dao.ImageDao;
import projektOgloszenia.models.Image;
import projektOgloszenia.models.Ogloszenie;

@Named
@ApplicationScoped
public class ImageDaoImpl extends GenericJpaDao<Image, Integer> implements ImageDao, Serializable {

	private static final long serialVersionUID = -6948105639773035438L;


	@Override
	public List<Image> findImagesByOgloszenie(Ogloszenie o) {
		EntityManager em = getEntityManager();
		TypedQuery<Image> query = em.createQuery("SELECT i from Image join Ogloszenie o where o=" + o, Image.class);
		em.close();
		return query.getResultList();

	}


	public ImageDaoImpl() {
		super(Image.class);
	}
}