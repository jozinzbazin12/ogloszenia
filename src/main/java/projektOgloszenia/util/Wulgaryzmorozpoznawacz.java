package projektOgloszenia.util;

import projektOgloszenia.jpa.dao.SlowoDao;
import projektOgloszenia.models.Slowo;

public class Wulgaryzmorozpoznawacz 
{
	private SlowoDao dao;
	
	public boolean szukaj(String tresc)
	{
		for(Slowo i:dao.findAll())
		{
			if(tresc.toLowerCase().contains(i.getSlowo())) return true;
		}
		return false;
	}
	

}
