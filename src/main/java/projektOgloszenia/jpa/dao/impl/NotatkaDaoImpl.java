package projektOgloszenia.jpa.dao.impl;

import javax.ejb.Stateless;

import projektOgloszenia.jpa.dao.NotatkaDao;
import projektOgloszenia.models.Notatka;
@Stateless
public class NotatkaDaoImpl extends GenericJpaDao<Notatka, Integer> implements NotatkaDao {

	
}