package projektOgloszenia.filtry;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projektOgloszenia.beansy.User;
public class auth implements Filter 
{
	
	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
     	String uri = req.getRequestURI();
     	HttpSession session = req.getSession(true);
     	User usr = (User ) session.getAttribute("user");
			if(usr!=null && usr.isLogged() && ( uri.endsWith("rejestracja.xhtml") || uri.endsWith("login.xhtml")))
			{
				usr.setResponse("Już jesteś zalogowany");
				res.sendRedirect("home.xhtml");
				return;
			}
			
			if((usr==null || !usr.isLogged()) &&  (uri.endsWith("dodaj.xhtml") || uri.endsWith("user.xhtml") || uri.endsWith("edit.xhtml") ))
			{
			//	usr.setResponse("Najpierw się zaloguj");
				res.sendRedirect("login.xhtml");
				return;
			}
			
			if((usr==null || !usr.isAdmin()) && (uri.endsWith("admin.xhtml")|| uri.contains("admin_")))
			{
				//usr.setResponse("Tylko administrator ma dostęp do panelu administracyjnego");
				res.sendRedirect("home.xhtml");
				return;
			}
     	
	    chain.doFilter(req, res);	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
