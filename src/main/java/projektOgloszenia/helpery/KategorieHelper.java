package projektOgloszenia.helpery;

import java.util.List;

import javax.ejb.EJB;

import projektOgloszenia.jpa.dao.KategoriaDao;
import projektOgloszenia.models.Kategoria;

public class KategorieHelper {

	@EJB
	private KategoriaDao kategoriaDao;

	public void add(Kategoria k) {
		kategoriaDao.save(k);
	}

	public void delete(Kategoria k) {
		kategoriaDao.delete(k);
	}

	public List<Kategoria> getKategorie() {
		return kategoriaDao.findAll();
	}
}
