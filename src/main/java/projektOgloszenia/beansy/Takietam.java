package projektOgloszenia.beansy;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;

import org.apache.log4j.xml.DOMConfigurator;

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
		String absoluteDiskPath = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("WEB-INF/classes/META-INF/log4j.xml");
		DOMConfigurator.configure(absoluteDiskPath);
	}

	public String getWebPath() {
		return webPath;
	}

}
