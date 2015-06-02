package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.SkargiHelper;
import projektOgloszenia.models.Warn;

@SessionScoped
@Named("skargiController")
public class SkargiController implements Serializable{

	private static final long serialVersionUID = -197856375422387184L;
	private Warn current;
	DataModel<Warn> data;
	private SkargiHelper helper;
	@Inject
	private User usr;

	public DataModel<Warn> getData() {
		if (data == null)
			data = new ListDataModel<Warn>(helper.getWarny());
		return data;
	}

	private void recreate() {
		data = null;
		current = null;
	}

	public SkargiController() {
		helper = new SkargiHelper();
	}

	public void usun() {
		current = getData().getRowData();
		usr.setResponse("Pomyślnie usunąłeś skargę");
		helper.delete(current);
		recreate();
	}

	public Warn getCurrent() {
		return current;
	}

	public void setCurrent(Warn current) {
		this.current = current;
	}

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}
}
