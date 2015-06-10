package projektOgloszenia.kontrollery;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import projektOgloszenia.beansy.EmailSender;
import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.OgloszeniaHelper;
import projektOgloszenia.jpa.dao.KategoriaDao;
import projektOgloszenia.models.Image;
import projektOgloszenia.models.Ogloszenie;
import projektOgloszenia.models.Warn;
import projektOgloszenia.util.Wulgaryzmorozpoznawacz;

@SessionScoped
@Named("ogloszeniaController")
@MultipartConfig
public class OgloszeniaController implements Serializable {
	private final Logger log = Logger.getLogger(getClass().getName());
	private static final long serialVersionUID = 6567350708626777316L;
	private Ogloszenie current;
	private DataModel<Ogloszenie> data;
	private List<Ogloszenie> pom;
	private DataModel<Ogloszenie> wlasne;
	private DataModel<Image> img;
	@Inject
	private KategoriaDao kategoriaDao;
	@Inject
	private OgloszeniaHelper helper;
	private String warn;
	private Part plik1;
	private Part plik2;
	private String opis1;
	private String opis2;
	private String kategoria;
	private int wybkategoria;
	private String wybmiasto;
	private String wybfraza;
	@Inject
	private User usr;
	private int strona;
	@Inject
	private KategorieController kat;
	private int size;
	@EJB
	private EmailSender email;

	@Inject
	private Wulgaryzmorozpoznawacz wulgaryzmorozpoznawacz;

	public void next() {
		strona++;
		recreate();
	}

	public void previous() {
		strona--;
		recreate();
	}

	public boolean isHasNextPage() {
		if ((strona + 1) * usr.getIle() < size) {
			return true;
		}
		return false;
	}

	public boolean isHasPreviousPage() {
		if (strona > 0) {
			return true;
		}
		return false;
	}

	public void szukaj() {
		log.info("Wywoï¿½ano metodï¿½ 'szukaj()' z klasy Ogï¿½oszeniaController");
		recreate();
		System.out.println("dupa " + wybfraza + " " + wybmiasto);
		pom = helper.getOgloszenia(wybkategoria, wybmiasto, wybfraza);
		size = pom.size();
		data = new ListDataModel<Ogloszenie>(pom);
	}

	public OgloszeniaController() {
		strona = 0;
		wybkategoria = -1;
		wybmiasto = "";
		wybfraza = "";
	}

	private void recreate() {
		data = null;
		wlasne = null;
		current = null;
		plik1 = null;
		plik2 = null;
		opis1 = null;
		opis2 = null;
	}

	public String prepareDelete() {
		current = getWlasne().getRowData();
		helper.delete(current);
		recreate();
		usr.setResponse("Pomyœlnie usuniêto og³oszenie");
		return "user";
	}

	public String edytuj() {
		log.info("Wywo³ano metodê 'edytuj()' z klasy OgloszeniaController");
		String res = sprawdz();
		if (res != "") {
			return "edit";
		}

		current.setKategoria(kategoriaDao.findById(wybkategoria));
		helper.update(current);
		current = null;
		usr.setResponse("Pomyœlnie edytowano og³oszenie");
		recreate();
		return "user";
	}

	public void wyslijwarn() {
		log.info("Wywo³ano metodê 'wyslijwarn()' z klasy OgloszeniaController");
		helper.warn(new Warn(current, warn));
		usr.setResponse("Pomyœlnie wys³ano skargê");
		warn = "";
	}

	public DataModel<Image> getImg() {
		if (current != null)
			img = new ListDataModel<Image>(current.getObrazki());
		for (Image i : current.getObrazki()) {
			System.out.println(i.getTyp());
		}
		return img;
	}

	public DataModel<Ogloszenie> getData() {
		pom = helper.getOgloszenia(wybkategoria, wybmiasto, wybfraza);
		size = pom.size();
		if (strona * usr.getIle() > size)
			strona = 0;
		int last = (strona + 1) * usr.getIle();
		if (last > size)
			last = size;
		if (last < 0)
			last = 0;
		if (data == null)
			data = new ListDataModel<Ogloszenie>(pom.subList(strona * usr.getIle(), last));
		return data;
	}

	public DataModel<Ogloszenie> getWlasne() {
		if (wlasne == null)
			wlasne = new ListDataModel<Ogloszenie>(helper.getOgloszenia(usr.getUser()));
		return wlasne;
	}

	public String prepareView() {
		current = getData().getRowData();
		return "wyswietl";
	}

	public String prepareNew() {
		current = new Ogloszenie();
		current.setTelefon(usr.getUser().getTelefon());
		current.setEmail(usr.getUser().getEmail());
		current.setMiasto(usr.getUser().getMiasto());
		current.setUlica(usr.getUser().getUlica());
		current.setKodPocztowy(usr.getUser().getKodPocztowy());
		return "dodaj";
	}

	public String prepareEdit() {
		current = getWlasne().getRowData();
		return "edit";
	}

	public String prepareView2() {
		current = getWlasne().getRowData();
		return "wyswietl";
	}

	public String sprawdz() {
		log.info("Wywo³ano metodê 'sprawdz()' z klasy OgloszeniaController");
		if (wybkategoria == -1) {
			usr.setResponse("Wybierz kategoriê!");
			return "dodaj";
		}
		if (current.getTytul() == null || current.getTytul().equals("")) {
			usr.setResponse("Tytu³ nie moze byc pusty");
			return "dodaj";
		}
		if (current.getTresc() == null || current.getTresc().equals("")) {
			usr.setResponse("Treœæ nie mo¿e byc pusta");
			return "dodaj";
		}
		if (current.getCena() == null || current.getCena().equals("")) {
			usr.setResponse("Cena jakaœ musi byæ");
			return "dodaj";
		}
		if (current.getCena() == null || current.getCena().equals("")) {
			usr.setResponse("Email nie mo¿e byc pusty");
			return "dodaj";
		}
		if (current.getTelefon() == 0) {
			usr.setResponse("Telefon nie mo¿e byc pusty");
			return "dodaj";
		}
		if (current.getMiasto() == null || current.getMiasto().equals("")) {
			usr.setResponse("Miasto nie mo¿e byæ");
			return "dodaj";
		}
		if (current.getKodPocztowy() == null || current.getKodPocztowy().equals("")) {
			usr.setResponse("Kod pocztowy nie mo¿e byc pusty");
			return "dodaj";
		}
		if (current.getUlica() == null || current.getUlica().equals("")) {
			usr.setResponse("Ulica nie mo¿e byc pusta");
			return "dodaj";
		}
		if (wulgaryzmorozpoznawacz.szukaj(current.getTytul()) || wulgaryzmorozpoznawacz.szukaj(current.getTresc())
				|| wulgaryzmorozpoznawacz.szukaj(current.getCena()) || wulgaryzmorozpoznawacz.szukaj(current.getEmail())
				|| wulgaryzmorozpoznawacz.szukaj(current.getKodPocztowy()) || wulgaryzmorozpoznawacz.szukaj(current.getUlica())) {
			usr.setResponse("Ladnie to tak przeklinaæ?");
			return "dodaj";
		}
		return "";
	}

	public String getFileName(String header) {
		for (String headerPart : header.split(";")) {
			if (headerPart.trim().startsWith("filename")) {
				return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	public String typ(String header) {
		String nazwa = getFileName(header);
		if (nazwa.endsWith(".swf"))
			return "application/x-shockwave-flash";
		if (nazwa.endsWith(".mp3") || nazwa.endsWith(".wav") || nazwa.endsWith(".aac") || nazwa.endsWith(".wma"))
			return "audio";
		if (nazwa.endsWith(".jpg") || nazwa.endsWith(".jpeg") || nazwa.endsWith(".bmp") || nazwa.endsWith(".png") || nazwa.endsWith(".gif"))
			return "image";
		if (nazwa.endsWith(".wmv") || nazwa.endsWith(".mp4") || nazwa.endsWith(".avi") || nazwa.endsWith(".webm") || nazwa.endsWith(".mpg"))
			return "video";
		return "application/octet-stream";
	}

	public String dodaj() {
		log.info("Wywo³ano metodê 'dodaj()' z klasy OgloszeniaController");
		String res = sprawdz();
		if (res != "") {
			return "dodaj";
		}
		current.setKategoria(kategoriaDao.findById(wybkategoria));
		current.setUser(usr.getUser());

		if (plik1 != null) {
			byte[] data = new byte[(int) plik1.getSize()];
			try {
				plik1.getInputStream().read(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image a1 = new Image(data, current, opis1, typ(plik1.getHeader("content-disposition")));
			current.getObrazki().add(a1);
		}

		if (plik2 != null) {
			byte[] data = new byte[(int) plik2.getSize()];
			try {
				plik2.getInputStream().read(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image a2 = new Image(data, current, opis2, typ(plik2.getHeader("content-disposition")));
			current.getObrazki().add(a2);
		}
		helper.add(current);
		plik1 = null;
		plik2 = null;
		current = null;
		recreate();
		email.sendMail(usr.getUser().getEmail(), "Nowe og³oszenia", "Dodano nowe og³oszenie.");
		usr.setResponse("Pomyœlnie doda³eœ og³oszenie");
		return "home";
	}

	public Ogloszenie getCurrent() {
		if (current == null)
			current = new Ogloszenie();
		return current;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public Part getPlik1() {
		return plik1;
	}

	public void setPlik1(Part plik1) {
		this.plik1 = plik1;
	}

	public Part getPlik2() {
		return plik2;
	}

	public void setPlik2(Part plik2) {
		this.plik2 = plik2;
	}

	public String getOpis1() {
		return opis1;
	}

	public void setOpis1(String opis1) {
		this.opis1 = opis1;
	}

	public String getOpis2() {
		return opis2;
	}

	public void setOpis2(String opis2) {
		this.opis2 = opis2;
	}

	public KategorieController getKat() {
		return kat;
	}

	public void setKat(KategorieController kat) {
		this.kat = kat;
	}

	public String getKategoria() {
		kategoria = kat.getKat(current.getKategoria());
		return kategoria;
	}

	public int getWybkategoria() {
		return wybkategoria;
	}

	public void setWybkategoria(int wybkategoria) {
		this.wybkategoria = wybkategoria;
	}

	public String getWybmiasto() {
		return wybmiasto;
	}

	public void setWybmiasto(String wybmiasto) {
		this.wybmiasto = wybmiasto;
	}

	public String getWybfraza() {
		return wybfraza;
	}

	public void setWybfraza(String wybfraza) {
		this.wybfraza = wybfraza;
	}
}
