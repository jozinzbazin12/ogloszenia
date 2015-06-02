package projektOgloszenia.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Slowo implements Serializable {

	private static final long serialVersionUID = 6326966183468762840L;
	@Id
	private String slowo;

	public Slowo() {
	}

	public Slowo(String slowo) {
		this.slowo = slowo;
	}

	public String getSlowo() {
		return this.slowo;
	}

	public void setSlowo(String slowo) {
		this.slowo = slowo;
	}

}
