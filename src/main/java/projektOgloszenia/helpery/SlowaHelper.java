package projektOgloszenia.helpery;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.SlowoDao;
import projektOgloszenia.models.Slowo;

@Named
@ApplicationScoped
public class SlowaHelper implements Serializable{

	private static final long serialVersionUID = 486023682187539120L;
	@Inject
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
