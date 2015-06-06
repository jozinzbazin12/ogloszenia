package projektOgloszenia.helpery;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KontoDao;
import projektOgloszenia.models.Konto;

@Named
@ApplicationScoped
public class RegisterHelper implements Serializable {

	private static final long serialVersionUID = 3513373871957880353L;
	@Inject
	private KontoDao kontoDao;

	public boolean czyjest(String login) {
		return kontoDao.findById(login) != null;
	}

	public List<Konto> getKonta() {
		return kontoDao.findAll();
	}

	public void delete(Konto k) {
		kontoDao.delete(k);

	}

	public void save(Konto k) {
		kontoDao.update(k);
	}

}
