package projektOgloszenia.kontrollery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.KategorieHelper;
import projektOgloszenia.jpa.dao.KategoriaDao;
import projektOgloszenia.models.Kategoria;

@SessionScoped
@Named("kategorieController")
public class KategorieController implements Serializable{
	
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
		Kategoria ociec = kategoriaDao.findById(ojciec);
		if (ociec != null) {
			Kategoria a = new Kategoria(nazwa, ociec);

			helper.add(a);
			usr.setResponse("Pomyślnie dodałeś kategorie");
			recreate();
		}
		else
			usr.setResponse("Niepoprawne dane");
	}

	public void usun() {
		current = getData().getRowData();
		helper.delete(current);
		usr.setResponse("Pomyślnie dodałeś kategorie");
		recreate();
	}

	public DataModel<Kategoria> getData() {
		if (data == null) {
			data = new ListDataModel<Kategoria>(helper.getKategorie());
		}
		return data;
	}

	public void daj_kategorie() {

		List<Kategoria> kategorie = kategoriaDao.findAll();

		int ociec;
		String nazwa;
		int ociec_id;
		kat_list = new ArrayList<Kategoria>();
		for (int i = 0; i < kategorie.size(); i++) {
			nazwa = "";
			ociec = kategorie.get(i).getOjciec().getId();
			ociec_id = kategorie.get(i).getOjciec().getId();
			while (ociec != 0) {
				for (Kategoria j : kategorie) {
					if (j.getId() == ociec) {
						ociec = j.getOjciec().getId();
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
		xd.setNazwa("/Wybierz kategorię\\");
		kat_list.add(0, xd);
	}

	public List<Kategoria> getKat_list() {
		if (kat_list == null)
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
