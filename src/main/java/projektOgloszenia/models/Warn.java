package projektOgloszenia.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Warn implements Serializable {
	private static final long serialVersionUID = -4516700210530370105L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Ogloszenie ogloszenie;
	private String czemu;

	public Warn() {
	}

	public Warn(Ogloszenie ogloszenie, String czemu) {
		this.ogloszenie = ogloszenie;
		this.czemu = czemu;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Ogloszenie getOgloszenie() {
		return ogloszenie;
	}

	public void setOgloszenie(Ogloszenie ogloszenie) {
		this.ogloszenie = ogloszenie;
	}

	public String getCzemu() {
		return this.czemu;
	}

	public void setCzemu(String czemu) {
		this.czemu = czemu;
	}

}
