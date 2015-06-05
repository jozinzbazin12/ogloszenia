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
import projektOgloszenia.models.Ogloszenie;
import projektOgloszenia.models.Warn;
import projektOgloszenia.util.JpaFactory;

@Named
@ApplicationScoped
public class OgloszeniaHelper implements Serializable {

	private static final long serialVersionUID = 4622473872387122079L;
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
		String pytanie = "SELECT o from Ogloszenia o";
		List<Ogloszenie> lista2 = new ArrayList<Ogloszenie>();
		if (kategoria != -1) {
			pytanie += " where o.kategoria=" + kategoria;
			List<Kategoria> kategorie = new KategorieHelper().getKategorie();
			Stack<Integer> s = new Stack<Integer>();
			do {
				for (Kategoria i : kategorie) {
					if (i.getOjciec().getId() == kategoria) {
						s.push(i.getId());
					}
				}

				if (!s.empty()) {
					kategoria = s.pop();
					pytanie += " or o.kategoria=" + kategoria;
				} else
					break;
			} while (true);
			while (!s.empty())
				pytanie += " or o.kategoria=" + s.pop() + " ";
		}
		List<Ogloszenie> filmList = JpaFactory.getEntityManager().createQuery(pytanie, Ogloszenie.class).getResultList();

		if (filmList != null)
			for (Ogloszenie i : filmList) {
				if (fraza == "" && miasto == "") {
					lista2.add(i);
					continue;
				}
				if (fraza != "" && (i.getTytul().toLowerCase().contains(fraza.toLowerCase()) || i.getTresc().toLowerCase().contains(fraza.toLowerCase()))) {

					lista2.add(i);
					continue;
				}

				if (miasto != "" && i.getMiasto().toLowerCase().contains(miasto.toLowerCase())) {
					lista2.add(i);
				}
			}
		return lista2;
	}

	public List<Ogloszenie> getOgloszenia(String login) {
		return ogloszenieDao.findAllUserOgloszenie(login);
	}

	public void warn(Warn w) {
		warnDao.save(w);
	}

	public void update(Ogloszenie o) {
		ogloszenieDao.save(o);
	}

	public void delete(Ogloszenie o) {
		ogloszenieDao.delete(o);
	}

}
