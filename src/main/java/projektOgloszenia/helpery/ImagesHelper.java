package projektOgloszenia.helpery;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.ImageDao;
import projektOgloszenia.models.Image;

@Named
@ApplicationScoped
public class ImagesHelper implements Serializable {

	private static final long serialVersionUID = 1906782605194618331L;
	@Inject
	private ImageDao imageDao;

	public Image getimg(Integer id) {
		return imageDao.findById(id);
	}

	public void add(Image i) {
		imageDao.save(i);
	}

}
