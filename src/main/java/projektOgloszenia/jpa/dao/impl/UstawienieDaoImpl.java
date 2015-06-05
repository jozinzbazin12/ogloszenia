package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.UstawienieDao;
import projektOgloszenia.models.Ustawienie;

@Named
@ApplicationScoped
public class UstawienieDaoImpl extends GenericJpaDao<Ustawienie, String> implements UstawienieDao, Serializable {

	private static final long serialVersionUID = 3253848084005959788L;

	public UstawienieDaoImpl() {
		super(Ustawienie.class);
	}

}