package projektOgloszenia.helpery;

import java.util.List;

import javax.inject.Inject;

import projektOgloszenia.jpa.dao.KontoDao;
import projektOgloszenia.models.Konto;

public class RegisterHelper {

	@Inject
	private KontoDao kontoDao;

	public boolean czyjest(String login) {
		System.out.println("--------------------------------------------------\n\n\n\n"+kontoDao);
		return kontoDao.findById(login) != null;
	}

	public List<Konto> getKonta() {
		return kontoDao.findAll();
	}

	public void delete(Konto k) {
		kontoDao.delete(k);

	}

	public void save(Konto k) {
		kontoDao.save(k);
	}

}
