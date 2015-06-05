package projektOgloszenia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Konto implements Serializable {

	private static final long serialVersionUID = 7789613374619671628L;
	
	@Id
	@Column(length=60)
	private String login;
	private String haslo;
	private String email;
	private int telefon;
	private String miasto;
	private String kodPocztowy;
	private String ulica;
	private int uprawnienia;
	private int stan;
	private String skorka;
	private int maxPozycji;

	public Konto() {
		uprawnienia=0;
		skorka="sk1";
		maxPozycji=20;
	}

	public Konto(String login, String haslo, String email, int telefon,
			String kodPocztowy, int uprawnienia, int stan, String skorka,
			int maxPozycji) {
		this.login = login;
		this.haslo = haslo;
		this.email = email;
		this.telefon = telefon;
		this.kodPocztowy = kodPocztowy;
		this.uprawnienia = uprawnienia;
		this.stan = stan;
		this.skorka = skorka;
		this.maxPozycji = maxPozycji;
	}

	public Konto(String login, String haslo, String email, int telefon,
			String miasto, String kodPocztowy, String ulica, int uprawnienia,
			int stan, String skorka, int maxPozycji) {
		this.login = login;
		this.haslo = haslo;
		this.email = email;
		this.telefon = telefon;
		this.miasto = miasto;
		this.kodPocztowy = kodPocztowy;
		this.ulica = ulica;
		this.uprawnienia = uprawnienia;
		this.stan = stan;
		this.skorka = skorka;
		this.maxPozycji = maxPozycji;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefon() {
		return this.telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getMiasto() {
		return this.miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getKodPocztowy() {
		return this.kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getUlica() {
		return this.ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getUprawnienia() {
		return this.uprawnienia;
	}

	public void setUprawnienia(int uprawnienia) {
		this.uprawnienia = uprawnienia;
	}

	public int getStan() {
		return this.stan;
	}

	public void setStan(int stan) {
		this.stan = stan;
	}

	public String getSkorka() {
		return this.skorka;
	}

	public void setSkorka(String skorka) {
		this.skorka = skorka;
	}

	public int getMaxPozycji() {
		return this.maxPozycji;
	}

	public void setMaxPozycji(int maxPozycji) {
		this.maxPozycji = maxPozycji;
	}

}
