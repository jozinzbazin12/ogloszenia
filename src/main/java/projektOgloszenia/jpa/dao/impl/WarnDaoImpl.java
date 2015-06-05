package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.WarnDao;
import projektOgloszenia.models.Warn;

@Named
@ApplicationScoped
public class WarnDaoImpl extends GenericJpaDao<Warn, Integer> implements WarnDao, Serializable {

	private static final long serialVersionUID = 1930988143223701312L;

	public WarnDaoImpl() {
		super(Warn.class);
	}
}