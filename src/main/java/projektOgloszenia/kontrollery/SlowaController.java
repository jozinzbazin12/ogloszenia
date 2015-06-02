package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.SlowaHelper;
import projektOgloszenia.models.Slowo;

@SessionScoped
@Named("SlowaController")
public class SlowaController implements Serializable{

	private static final long serialVersionUID = -7450395269684097480L;
	private String slowo;
	DataModel<Slowo> data;
	SlowaHelper helper;
	private Slowo current;
	@Inject
	private User usr;

	void recreate() {
		data = null;
		current = null;
	}

	public void usun() {
		current = getData().getRowData();
		usr.setResponse("Pomyślnie usunąłeś notatkę");
		helper.usun(current);
		recreate();
	}

	public void dodaj() {
		Slowo a = new Slowo(slowo);
		helper.add(a);
		recreate();
	}

	public SlowaController() {
		helper = new SlowaHelper();
	}

	public String getSlowo() {
		return slowo;
	}

	public void setSlowo(String slowo) {
		this.slowo = slowo;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	public DataModel<Slowo> getData() {
		if (data == null) {
			data = new ListDataModel<Slowo>(helper.getSlowa());
		}
		return data;
	}

	public Slowo getCurrent() {
		return current;
	}

}
