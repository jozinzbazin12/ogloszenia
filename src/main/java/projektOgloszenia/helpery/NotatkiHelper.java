/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package projektOgloszenia.helpery;

import java.util.List;

import javax.ejb.EJB;

import projektOgloszenia.jpa.dao.NotatkaDao;
import projektOgloszenia.models.Notatka;

public class NotatkiHelper {
	@EJB
	private NotatkaDao notatkaDao;

	public String getTresc(int id) {
		return notatkaDao.findById(id).getTresc();
	}

	public List<Notatka> getTresc() {
		return notatkaDao.findAll();
	}

	public void usun(Notatka current) {
		notatkaDao.delete(current);

	}

	public void add(Notatka a) {
		notatkaDao.save(a);
	}
}
