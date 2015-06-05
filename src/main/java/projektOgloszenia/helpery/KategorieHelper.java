package projektOgloszenia.helpery;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KategoriaDao;
import projektOgloszenia.models.Kategoria;

@Named
@ApplicationScoped
public class KategorieHelper implements Serializable{

	private static final long serialVersionUID = -7197392888867684805L;
	@Inject
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
