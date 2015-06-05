package projektOgloszenia.beansy;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("info")
public class Takietam implements Serializable {

	private static final long serialVersionUID = 8231977590402640947L;
	private String nazwa;
	private String webPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

	public String getNazwa() {
		return nazwa;
	}

	public Takietam() {
		nazwa = "Serwis Og³oszeniowy ";
		webPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	public String getWebPath() {
		return webPath;
	}

}
