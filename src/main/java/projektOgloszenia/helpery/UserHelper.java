package projektOgloszenia.helpery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KontoDao;
import projektOgloszenia.models.Konto;

@SessionScoped
@Named("userController")
public class UserHelper implements Serializable{

	private static final long serialVersionUID = -1272893030565136766L;
	@Inject
	private KontoDao kontoDao;

	public Konto loguj(String login, String haslo) {
		Konto k = kontoDao.findById(login);
		if (k!=null && k.getHaslo().equals(haslo))
			return k;
		return null;
	}

	public boolean czyadmin(String login) {
		return kontoDao.findById(login).getUprawnienia() == 1;
	}

	public void update(Konto k) {
		kontoDao.update(k);
	}

}
