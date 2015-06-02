package projektOgloszenia.helpery;

import java.util.List;

import javax.ejb.EJB;

import projektOgloszenia.jpa.dao.ImageDao;
import projektOgloszenia.models.Image;
import projektOgloszenia.models.Ogloszenie;

public class ImagesHelper {
	@EJB
	private ImageDao imageDao;
	public List<Image> getimgs(Ogloszenie o) {
    	return imageDao.findImagesByOgloszenie(o);
	}
	
	public Image getimg(Integer id) {
    	return imageDao.findById(id);
	}
	
	public void add(Image i) {
		imageDao.save(i);
	}
	
}
