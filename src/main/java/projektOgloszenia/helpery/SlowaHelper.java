package projektOgloszenia.helpery;

import java.util.List;

import javax.ejb.EJB;

import projektOgloszenia.jpa.dao.SlowoDao;
import projektOgloszenia.models.Slowo;

public class SlowaHelper {

	@EJB
	private SlowoDao slowoDao;

	public void usun(Slowo s) {
		slowoDao.delete(s);
	}

	public void add(Slowo s) {
		slowoDao.save(s);
	}

	public List<Slowo> getSlowa() {
		return slowoDao.findAll();
	}

}
