package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.ImageDao;
import projektOgloszenia.models.Image;

@Named
@ApplicationScoped
public class ImageDaoImpl extends GenericJpaDao<Image, Integer> implements ImageDao, Serializable {

	private static final long serialVersionUID = -6948105639773035438L;
	
	public ImageDaoImpl() {
		super(Image.class);
	}
}