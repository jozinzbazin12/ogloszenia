package projektOgloszenia.servlety;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projektOgloszenia.helpery.ImagesHelper;
import projektOgloszenia.models.Image;

public class podgladarka extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext cntxt;
	private ImagesHelper helper;

	public void init() throws ServletException {
		helper = new ImagesHelper();
		cntxt = this.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nazwa = request.getParameter("id");
		Image img = helper.getimg(Integer.parseInt(nazwa));
		String mime = img.getTyp();
		response.setContentType("image");
		ServletOutputStream out = response.getOutputStream();
		InputStream in;

		if (mime.equals("image")) {
			in = new ByteArrayInputStream(img.getImg());
			wypisz(in, out);
			return;
		}
		if (mime.equals("audio")) {
			in = cntxt.getResourceAsStream("/Content/muzyczka.png");
			wypisz(in, out);
			return;
		} else if (mime.equals("video")) {
			in = cntxt.getResourceAsStream("/Content/video.png");
			wypisz(in, out);
			return;
		} else if (mime.equals("application/x-shockwave-flash")) {
			in = cntxt.getResourceAsStream("/Content/flash.png");
			wypisz(in, out);
			return;
		} else {
			in = cntxt.getResourceAsStream("/Content/error.png");
			wypisz(in, out);
			return;
		}

	}

	private final void wypisz(InputStream in, ServletOutputStream out) {
		int ch = 0;
		try {
			while ((ch = in.read()) != -1) {
				out.write(ch);
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
