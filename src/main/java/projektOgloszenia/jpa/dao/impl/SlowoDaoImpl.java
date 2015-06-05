package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.SlowoDao;
import projektOgloszenia.models.Slowo;

@Named
@ApplicationScoped
public class SlowoDaoImpl extends GenericJpaDao<Slowo, String> implements SlowoDao, Serializable {

	private static final long serialVersionUID = 7952527345182855356L;

	public SlowoDaoImpl() {
		super(Slowo.class);
	}
}