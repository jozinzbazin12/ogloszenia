package projektOgloszenia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Kategoria implements java.io.Serializable {

	private static final long serialVersionUID = -98683059059666898L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String nazwa;
	@OneToOne
	private Kategoria ojciec;

	public Kategoria() {
	}

	public Kategoria(String nazwa, Kategoria ojciec) {
		this.nazwa = nazwa;
		this.ojciec = ojciec;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Kategoria getOjciec() {
		return ojciec;
	}

	public void setOjciec(Kategoria ojciec) {
		this.ojciec = ojciec;
	}

}
