package projektOgloszenia.helpery;

import java.util.List;

import javax.ejb.EJB;

import projektOgloszenia.jpa.dao.OgloszenieDao;
import projektOgloszenia.jpa.dao.WarnDao;
import projektOgloszenia.models.Warn;

public class SkargiHelper {

	@EJB
	private WarnDao warnDao;

	@EJB
	private OgloszenieDao ogloszenieDao;

	public List<Warn> getWarny() {
		return warnDao.findAll();
	}

	public String getOglosznenieTitle(int id) {
		return ogloszenieDao.findById(id).getTytul();
	}

	public void delete(Warn w) {
		warnDao.delete(w);

	}
}
