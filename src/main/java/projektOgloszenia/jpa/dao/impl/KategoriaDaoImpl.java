package projektOgloszenia.jpa.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KategoriaDao;
import projektOgloszenia.models.Kategoria;

@Named
@ApplicationScoped
public class KategoriaDaoImpl extends GenericJpaDao<Kategoria, Integer> implements KategoriaDao, Serializable {

	private static final long serialVersionUID = -5931988376388660944L;

	public KategoriaDaoImpl() {
		super(Kategoria.class);
	}
}