package projektOgloszenia.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import projektOgloszenia.kontrollery.KategorieController;

@Entity
public class Ogloszenie implements Serializable {

	private static final long serialVersionUID = 644139899613883820L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String tytul;
	private String tresc;
	private int telefon;
	private String email;
	private String miasto;
	private String kodPocztowy;
	private String ulica;
	private String cena;
	@ManyToOne(cascade = CascadeType.ALL)
	private Konto user;
	private int wyswietlen;
	@ManyToOne
	private Kategoria kategoria;
	private String kategoria2;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Image> obrazki;

	@ManyToOne
	private Konto konto;

	public Konto getKonto() {
		return konto;
	}

	public void setKonto(Konto konto) {
		this.konto = konto;
	}

	public Ogloszenie() {
		wyswietlen = 0;
	}

	public Ogloszenie(String tytul, String tresc, int telefon, String email, String miasto, String kodPocztowy, String ulica, String cena, Konto user,
			int wyswietlen, int kategoria, KategorieController ctrl) {
		this.tytul = tytul;
		this.tresc = tresc;
		this.telefon = telefon;
		this.email = email;
		this.miasto = miasto;
		this.kodPocztowy = kodPocztowy;
		this.ulica = ulica;
		this.cena = cena;
		this.user = user;
		this.wyswietlen = wyswietlen;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTytul() {
		return this.tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public int getTelefon() {
		return this.telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCena() {
		return this.cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public Konto getUser() {
		return user;
	}

	public void setUser(Konto user) {
		this.user = user;
	}

	public Kategoria getKategoria() {
		return kategoria;
	}

	public void setKategoria(Kategoria kategoria) {
		this.kategoria = kategoria;
	}

	public void setKategoria2(String kategoria2) {
		this.kategoria2 = kategoria2;
	}

	public int getWyswietlen() {
		wyswietlen++;
		return this.wyswietlen;
	}

	public void setWyswietlen(int wyswietlen) {
		this.wyswietlen = wyswietlen;
	}

	public String getKategoria2() {
		return kategoria2;
	}

	public List<Image> getObrazki() {
		return obrazki;
	}

	public void setObrazki(List<Image> obrazki) {
		this.obrazki = obrazki;
	}

}
