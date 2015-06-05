package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KontoDao;
import projektOgloszenia.models.Konto;

@SessionScoped
@Named("userController")
public class UserController implements Serializable{

	private static final long serialVersionUID = -1272893030565136766L;
	@Inject
	private KontoDao kontoDao;

	public Konto loguj(String login, String haslo) {
		System.out.println("\ndddudpa\n "+kontoDao);
		Konto k = kontoDao.findById(login);
		if (k.getHaslo().equals(haslo))
			return k;
		return null;
	}

	public boolean czyadmin(String login) {
		return kontoDao.findById(login).getUprawnienia() == 1;
	}

	public void update(Konto k) {
		kontoDao.save(k);
	}

}
