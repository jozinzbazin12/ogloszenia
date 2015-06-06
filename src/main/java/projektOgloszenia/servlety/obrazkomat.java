package projektOgloszenia.servlety;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projektOgloszenia.helpery.ImagesHelper;
import projektOgloszenia.models.Image;

public class obrazkomat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private ImagesHelper helper;
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nazwa=request.getParameter("id");
		Image img=helper.getimg(Integer.parseInt(nazwa));
		String mime=img.getTyp();  
		if(img!=null)
		{
			InputStream in=new ByteArrayInputStream(img.getImg());
			response.setContentType(mime);  
			ServletOutputStream out = response.getOutputStream();
	        int ch=0;
	        while ((ch=in.read()) != -1) 
	        {
	            out.write(ch);
	        }
	        out.close();
		}
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
