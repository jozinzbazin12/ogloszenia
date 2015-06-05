package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.NotatkaDao;
import projektOgloszenia.models.Notatka;
@Named
@ApplicationScoped
public class NotatkaDaoImpl extends GenericJpaDao<Notatka, Integer> implements NotatkaDao, Serializable {


	private static final long serialVersionUID = -6725701484559850279L;

	public NotatkaDaoImpl() {
		super(Notatka.class);
	}
	
}