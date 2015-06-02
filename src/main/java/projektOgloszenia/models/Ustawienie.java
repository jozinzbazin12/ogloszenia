package projektOgloszenia.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ustawienie implements java.io.Serializable {

	private static final long serialVersionUID = 5432574963000836657L;
	@Id
	private String nazwa;
	private int wartosc;

	public Ustawienie() {
	}

	public Ustawienie(String nazwa, int wartosc) {
		this.nazwa = nazwa;
		this.wartosc = wartosc;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getWartosc() {
		return this.wartosc;
	}

	public void setWartosc(int wartosc) {
		this.wartosc = wartosc;
	}

}
