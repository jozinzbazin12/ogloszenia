package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.RegisterHelper;
import projektOgloszenia.models.Konto;

@SessionScoped
@Named("registerController")
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 5985792256146994765L;
	private Konto current;
	private String haslo;
	private DataModel<Konto> data;
	@Inject
	private RegisterHelper helper;
	@Inject
	private User usr;

	public DataModel<Konto> getData() {
		if (data == null)
			data = new ListDataModel<Konto>(helper.getKonta());
		return data;
	}

	private void recreate() {
		data = null;
		current = null;
	}

	public String zapisz() {
		helper.save(current);
		usr.setResponse("Pomyślnie edytowałeś konto " + current.getLogin());
		recreate();
		return "admin_konta";
	}

	public String prepareEdit() {
		current = getData().getRowData();
		return "admin_konta_edit";
	}

	public void usun() {
		current = getData().getRowData();
		usr.setResponse("Pomyślnie usunąłeś konto " + current.getLogin());
		helper.delete(current);
		recreate();
	}

	public String rejestracja() {
		if (current.getLogin() == null || helper.czyjest(current.getLogin())) {
			usr.setResponse("Brak loginu, lub konto już istnieje");
			return "rejestracja";
		}
		if (current.getHaslo() == null || haslo == null || !haslo.equals(current.getHaslo())) {
			usr.setResponse("Niepoprawne hasła");
			return "rejestracja";
		}
		if (current.getEmail() == null) {
			usr.setResponse("Podaj email");
			return "rejestracja";
		}
		if (current.getTelefon() == 0) {
			usr.setResponse("Podaj telefon");
			return "rejestracja";
		}
		if (current.getMiasto() == null) {
			usr.setResponse("Podaj miasto");
			return "rejestracja";
		}
		if (current.getKodPocztowy() == null) {
			usr.setResponse("Podaj kod pocztowy");
			return "rejestracja";
		}
		if (current.getUlica() == null) {
			usr.setResponse("Podaj ulicę");
			return "rejestracja";
		}
		helper.save(current);
		recreate();
		haslo = null;
		usr.setResponse("Pomyślnie założyłeś konto");
		return "home";
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public Konto getCurrent() {
		if (current == null)
			current = new Konto();
		return current;
	}

	public RegisterController() {
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}
}
