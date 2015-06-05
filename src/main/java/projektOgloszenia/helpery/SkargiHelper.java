package projektOgloszenia.helpery;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.OgloszenieDao;
import projektOgloszenia.jpa.dao.WarnDao;
import projektOgloszenia.models.Warn;

@Named
@ApplicationScoped
public class SkargiHelper implements Serializable {

	private static final long serialVersionUID = -8976909100251735875L;

	@Inject
	private WarnDao warnDao;

	@Inject
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
