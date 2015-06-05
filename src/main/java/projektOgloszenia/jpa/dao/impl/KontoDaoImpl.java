package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KontoDao;
import projektOgloszenia.models.Konto;
@Named
@ApplicationScoped
public class KontoDaoImpl extends GenericJpaDao<Konto, String> implements KontoDao, Serializable {

	private static final long serialVersionUID = -6124512888144159807L;
	public KontoDaoImpl() {
		super(Konto.class);
	}
	
}