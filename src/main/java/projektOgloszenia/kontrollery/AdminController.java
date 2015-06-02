package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.UstawienieDao;
import projektOgloszenia.models.Ustawienie;

@SessionScoped
@Named("adminController")
public class AdminController implements Serializable {

	private static final long serialVersionUID = 6882748444224449657L;
	private int plikow;
	private int rozmiar;
	@Inject
	private UstawienieDao ustawienieDao;

	public int getPlikow() {
		return ustawienieDao.findById("ile_plikow").getWartosc();
	}

	public void setPlikow(int plikow) {
		this.plikow = plikow;
	}

	public int getRozmiar() {
		return ustawienieDao.findById("rozmiar").getWartosc();
	}

	public void setRozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}

	public void zmienpliki() {
		Ustawienie xd = new Ustawienie("ile_plikow", plikow);
		Ustawienie xd2 = new Ustawienie("rozmiar", rozmiar);
		ustawienieDao.save(xd);
		ustawienieDao.save(xd2);
	}
}
