package projektOgloszenia.jpa.dao;

import java.util.List;

import projektOgloszenia.models.Image;
import projektOgloszenia.models.Ogloszenie;

public interface ImageDao extends GenericDao<Image, Integer> {

	List<Image> findImagesByOgloszenie(Ogloszenie o);
}