package projektOgloszenia.kontrollery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.KategorieHelper;
import projektOgloszenia.jpa.dao.KategoriaDao;
import projektOgloszenia.models.Kategoria;

@SessionScoped
@Named("kategorieController")
public class KategorieController implements Serializable {
	private final Logger log = Logger.getLogger(getClass().getName());
	private static final long serialVersionUID = -6590221640560781844L;
	private List<Kategoria> kat_list;
	private DataModel<Kategoria> data;
	private String nazwa;
	private int ojciec;
	private Kategoria current;
	@Inject
	private KategorieHelper helper;

	@Inject
	private User usr;

	@Inject
	private KategoriaDao kategoriaDao;

	void recreate() {
		data = null;
		current = null;
	}

	public KategorieController() {
	}

	public void dodaj() {
		log.info("Wywo³ano metodê 'dodaj()' z klasy KategorieController");
		Kategoria ociec = kategoriaDao.findById(ojciec);
		if (ociec != null || ojciec == 0) {
			Kategoria a = new Kategoria(nazwa, ociec);

			helper.add(a);
			usr.setResponse("Pomyœlnie doda³eœ kategorie");
			recreate();
		} else
			usr.setResponse("Niepoprawne dane");
		nazwa = "";
		ojciec = 0;
	}

	public void usun() {
		log.info("Wywo³ano metodê 'usun()' z klasy KategorieController");
		current = getData().getRowData();
		helper.delete(current);
		usr.setResponse("Pomyœlnie doda³eœ kategorie");
		recreate();
	}

	public DataModel<Kategoria> getData() {
		data = new ListDataModel<Kategoria>(helper.getKategorie());
		return data;
	}

	public void daj_kategorie() {
		log.info("Wywo³ano metodê 'daj_kategorie()' (wybierz kategorie) z klasy KategorieController");
		List<Kategoria> kategorie = kategoriaDao.findAll();

		int ociec;
		String nazwa;
		int ociec_id;
		kat_list = new ArrayList<Kategoria>();
		if (kategorie == null)
			return;
		for (int i = 0; i < kategorie.size(); i++) {
			nazwa = "";
			Kategoria stary = kategorie.get(i).getOjciec();
			if (stary != null)
				ociec = stary.getId();
			else
				ociec = 0;
			Kategoria kapusta = kategorie.get(i).getOjciec();
			ociec_id = kapusta != null ? kapusta.getId() : 0;
			while (ociec != 0) {
				for (Kategoria j : kategorie) {
					if (j.getId() == ociec) {
						Kategoria zgred = j.getOjciec();
						ociec = zgred != null ? zgred.getId() : 0;
						nazwa += "-";
						break;
					}
				}
			}
			nazwa += kategorie.get(i).getNazwa();
			if (ociec_id == 0) {

				kat_list.add(kategorie.get(i));
			} else
				for (int j = 0; j < kat_list.size(); j++) {
					if (kat_list.get(j).getId() == ociec_id) {
						Kategoria a = new Kategoria();
						a.setId(kategorie.get(i).getId());
						a.setNazwa(nazwa);
						kat_list.add(j + 1, a);
						break;
					}
				}
		}
		Kategoria xd = new Kategoria();
		xd.setId(-1);
		xd.setNazwa("/Wybierz kategoriê\\");
		kat_list.add(0, xd);
	}

	public List<Kategoria> getKat_list() {
		daj_kategorie();
		return kat_list;
	}

	public String getKat(Kategoria kategoria) {
		return kategoriaDao.findById(kategoria.getId()).getNazwa();
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getOjciec() {
		return ojciec;
	}

	public void setOjciec(int ojciec) {
		this.ojciec = ojciec;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	public Kategoria getCurrent() {
		return current;
	}

}
