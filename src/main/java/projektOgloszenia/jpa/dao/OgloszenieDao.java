package projektOgloszenia.jpa.dao;

import java.util.List;

import projektOgloszenia.models.Ogloszenie;

public interface OgloszenieDao extends GenericDao<Ogloszenie, Integer> {

	List<Ogloszenie> findAllUserOgloszenie(String user);

	List<Ogloszenie> createQuery(String pytanie);
 }