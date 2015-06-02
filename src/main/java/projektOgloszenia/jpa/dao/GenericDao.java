package projektOgloszenia.jpa.dao;

import java.util.List;

public interface GenericDao<T, K> {
	void save(T t);

	void delete(T t);

	void update(T t);

	T findById(K id);
	
	List<T> findAll();
}