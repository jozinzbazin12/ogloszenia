/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package projektOgloszenia.helpery;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.NotatkaDao;
import projektOgloszenia.models.Notatka;

@Named
@ApplicationScoped
public class NotatkiHelper implements Serializable{

	private static final long serialVersionUID = 794072425918695403L;
	@Inject
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
