package projektOgloszenia.kontrollery;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

import projektOgloszenia.beansy.User;
import projektOgloszenia.helpery.SkargiHelper;
import projektOgloszenia.models.Warn;

@SessionScoped
@Named("skargiController")
public class SkargiController implements Serializable{
        private final Logger log= Logger.getLogger(getClass().getName());
	private static final long serialVersionUID = -197856375422387184L;
	private Warn current;
	private DataModel<Warn> data;
	@Inject
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
	
	public void usun() {
                log.info("Wywo³ano metodê 'usun()' z klasy SkargiController");
		current = getData().getRowData();
		usr.setResponse("Pomyœ›lnie usun¹³eœ skargê");
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
