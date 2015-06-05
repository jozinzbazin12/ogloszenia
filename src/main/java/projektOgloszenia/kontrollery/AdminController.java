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

	private static final String ROZMIAR = "rozmiar";
	private static final String ILE_PLIKOW = "ile_plikow";
	private static final long serialVersionUID = 6882748444224449657L;
	private int plikow;
	private int rozmiar;
	@Inject
	private UstawienieDao ustawienieDao;

	public int getPlikow() {
		return ustawienieDao.findById(ILE_PLIKOW).getWartosc();
	}

	public void setPlikow(int plikow) {
		this.plikow = plikow;
	}

	public int getRozmiar() {
		return ustawienieDao.findById(ROZMIAR).getWartosc();
	}

	public void setRozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}

	public void zmienpliki() {
		Ustawienie xd = new Ustawienie(ILE_PLIKOW, plikow);
		Ustawienie xd2 = new Ustawienie(ROZMIAR, rozmiar);
		ustawienieDao.save(xd);
		ustawienieDao.save(xd2);
	}
	public AdminController() {
		if(ustawienieDao.findById(ROZMIAR)==null) ustawienieDao.save(new Ustawienie(ROZMIAR, 1024));
		if(ustawienieDao.findById(ILE_PLIKOW)==null) ustawienieDao.save(new Ustawienie(ILE_PLIKOW, 3));
	}
}
