package projektOgloszenia.jpa.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import projektOgloszenia.jpa.dao.KontoDao;
import projektOgloszenia.models.Konto;
@Named("kontoDao")
@Stateless
public class KontoDaoImpl extends GenericJpaDao<Konto, String> implements KontoDao {

	
}