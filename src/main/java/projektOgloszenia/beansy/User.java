package projektOgloszenia.beansy;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.helpery.UserHelper;
import projektOgloszenia.models.Konto;

@Named("user")
@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = -5529031154390935730L;
	@Inject
	private UserHelper ctrl;
	private String nick;
	private String response;
	private String password;
	private String skorka;
	private boolean logged;
	private Konto user;
	private int ile;
	private int stronicowanie;
	private boolean admin;

	public void zmienskorke() {
		user.setSkorka(skorka);
		ctrl.update(user);
	}

	public void zmienilosc() {
		user.setMaxPozycji(ile);
		ctrl.update(user);
	}

	public String getPassword() {
		return password;
	}

	public String getResponse() {
		String a = response;
		response = null;
		return a;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogged() {
		return logged;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSkorka() {
		return skorka;
	}

	public String login() {
		Konto res = ctrl.loguj(nick, password);
		if (res != null) {
			logged = true;
			ile = res.getMaxPozycji();
			response = "Pomyœlnie zalogowano";
			user = res;
			skorka = user.getSkorka();
			if (ctrl.czyadmin(nick))
				admin = true;
			return "home";
		}

		response = "B³êdne dane logowania";
		return "login";

	}

	public String logout() {
		zeruj();
		return "home";
	}

	private void zeruj() {
		nick = null;
		logged = false;
		admin = false;
		skorka = "sk1";
		ile = 20;
	}

	public User() {
		zeruj();
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Konto getUser() {
		return user;
	}

	public void setSkorka(String skorka) {
		this.skorka = skorka;
	}

	public int getIle() {
		return ile;
	}

	public void setIle(int ile) {
		this.ile = ile;
	}

	public int getStronicowanie() {
		return stronicowanie;
	}

}
