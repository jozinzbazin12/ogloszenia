package projektOgloszenia.beansy;

import java.io.Serializable;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("info")
public class Takietam implements Serializable {
		
	private static final long serialVersionUID = 8231977590402640947L;
	private String nazwa;

	public String getNazwa() {
		return nazwa;
	}

	public Takietam() {
		nazwa = "Serwis Og³oszeniowy";
	}

}
