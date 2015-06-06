package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.NotatkiHelper;
import projektOgloszenia.models.Notatka;

@Named("notatkiController")
@SessionScoped
public class NotatkiController implements Serializable {
        private final Logger log= Logger.getLogger(getClass().getName());
	private static final long serialVersionUID = 2606582761016486844L;
	private DataModel<Notatka> data;
	@Inject
	private NotatkiHelper helper;
	private String tresc;
	private Notatka current;
	@Inject
	private User usr;

	public void usun() {
                log.info("Wywo�ano metod� 'usun()' z klasy NotatkiController");
		current = getData().getRowData();
		usr.setResponse("Pomy�lnie usun��e� notatk�");
		helper.usun(current);
		recreate();
	}

	public void dodaj() {
                log.info("Wywo�ano metod� 'dodaj()' z klasy NotatkiController");
		Notatka a = new Notatka(tresc);
		helper.add(a);
		recreate();
	}

	public NotatkiController(int startId, int endId) {
	}

	public NotatkiController() {
	}

	public Notatka getSelected() {
		if (current == null) {
			current = new Notatka();
		}
		return current;
	}

	public DataModel<Notatka> getData() {
		if (data == null) {
			data = new ListDataModel<Notatka>(helper.getTresc());
		}
		return data;
	}

	void recreate() {
		data = null;
		current = null;
	}

	public String prepareView() {
		current = (Notatka) getData().getRowData();
		return "browse";
	}

	public String prepareList() {
		recreate();
		return "index";
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

}
