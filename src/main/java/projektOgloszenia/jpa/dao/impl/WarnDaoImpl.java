package projektOgloszenia.jpa.dao.impl;

import javax.ejb.Stateless;

import projektOgloszenia.jpa.dao.WarnDao;
import projektOgloszenia.models.Warn;
@Stateless
public class WarnDaoImpl extends GenericJpaDao<Warn, Integer> implements WarnDao {

	
}