package projektOgloszenia.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notatka implements Serializable {

	private static final long serialVersionUID = 3255791008672448417L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String tresc;

	public Notatka() {
	}

	public Notatka(String tresc) {
		this.tresc = tresc;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

}
