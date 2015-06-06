package projektOgloszenia.helpery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.OgloszenieDao;
import projektOgloszenia.jpa.dao.WarnDao;
import projektOgloszenia.models.Kategoria;
import projektOgloszenia.models.Konto;
import projektOgloszenia.models.Ogloszenie;
import projektOgloszenia.models.Warn;

@Named
@ApplicationScoped
public class OgloszeniaHelper implements Serializable {

	private static final long serialVersionUID = 4622473872387122079L;
	@Inject
	private KategorieHelper kathelp;
	@Inject
	private OgloszenieDao ogloszenieDao;
	@Inject
	private WarnDao warnDao;

	public void add(Ogloszenie o) {
		ogloszenieDao.save(o);
	}

	public List<Ogloszenie> getOgloszenia() {
		return ogloszenieDao.findAll();
	}

	public List<Ogloszenie> getOgloszenia(int kategoria, String miasto, String fraza) {
		String pytanie = "SELECT o from Ogloszenie o join o.kategoria k";
		List<Ogloszenie> lista2 = new ArrayList<Ogloszenie>();
		if (kategoria != -1) {
			pytanie += " where k.id=" + kategoria;
			List<Kategoria> kategorie = kathelp.getKategorie();
			Stack<Integer> s = new Stack<Integer>();
			do {
				for (Kategoria i : kategorie) {
					if (i.getOjciec() != null && i.getOjciec().getId() == kategoria) {
						s.push(i.getId());
					}
				}

				if (!s.empty()) {
					kategoria = s.pop();
					pytanie += " or k.id=" + kategoria;
				} else
					break;
			} while (true);
			while (!s.empty())
				pytanie += " or k.id=" + s.pop() + " ";
		}
		List<Ogloszenie> filmList = ogloszenieDao.createQuery(pytanie);
		if (filmList != null)
			for (Ogloszenie i : filmList) {
				if (empty(fraza)&&empty(miasto)) {
					lista2.add(i);
					System.out.println(i.getId());
					continue;
				}
				if (!empty(fraza) && (i.getTytul().toLowerCase().contains(fraza.toLowerCase()) || i.getTresc().toLowerCase().contains(fraza.toLowerCase()))) {
					lista2.add(i);
					continue;
				}

				if (!empty(miasto) && i.getMiasto().toLowerCase().contains(miasto.toLowerCase())) {
					lista2.add(i);
				}
			}
		return lista2;
	}
	private boolean empty(String a){
		return a==null || a=="" ||a.equals("");
	}
	public List<Ogloszenie> getOgloszenia(Konto konto) {
		return ogloszenieDao.findAllUserOgloszenie(konto);
	}

	public void warn(Warn w) {
		warnDao.update(w);
	}

	public void update(Ogloszenie o) {
		ogloszenieDao.update(o);
	}

	public void delete(Ogloszenie o) {
		ogloszenieDao.delete(o);
	}

}
