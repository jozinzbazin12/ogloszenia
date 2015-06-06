package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.SlowaHelper;
import projektOgloszenia.models.Slowo;

@SessionScoped
@Named("slowaController")
public class SlowaController implements Serializable{
        private final Logger log= Logger.getLogger(getClass().getName());
	private static final long serialVersionUID = -7450395269684097480L;
	private String slowo;
	private DataModel<Slowo> data;
	@Inject
	private SlowaHelper helper;
	private Slowo current;
	@Inject
	private User usr;

	void recreate() {
		data = null;
		current = null;
	}

	public void usun() {
                log.info("Wywo³ano metodê 'usun()' z klasy SlowaController");
		current = getData().getRowData();
		usr.setResponse("Pomyœlnie usun¹³eœ notatkê");
		helper.usun(current);
		recreate();
	}

	public void dodaj() {
                log.info("Wywo³ano metodê 'dodaj()' z klasy SlowaController");
		Slowo a = new Slowo(slowo);
		helper.add(a);
		recreate();
	}

	public SlowaController() {
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
