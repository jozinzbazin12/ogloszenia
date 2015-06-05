package projektOgloszenia.util;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.SlowoDao;
import projektOgloszenia.models.Slowo;

@ApplicationScoped
@Named
public class Wulgaryzmorozpoznawacz implements Serializable {

	private static final long serialVersionUID = 3801231279891258117L;
	@Inject
	private SlowoDao dao;

	public boolean szukaj(String tresc) {

		List<Slowo> slowa = dao.findAll();
		if (slowa == null)
			return false;
		for (Slowo i : slowa) {
			if (tresc.toLowerCase().contains(i.getSlowo()))
				return true;
		}
		return false;
	}

}
