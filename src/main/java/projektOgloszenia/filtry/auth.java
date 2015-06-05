package projektOgloszenia.filtry;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projektOgloszenia.beansy.User;

public class auth implements Filter {

	@Inject
	private User usr;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		if (usr != null && usr.isLogged() && (uri.endsWith("rejestracja.xhtml") || uri.endsWith("login.xhtml"))) {
			usr.setResponse("Ju¿ jesteœ zalogowany");
			res.sendRedirect("home.xhtml");
			return;
		}

		if ((usr == null || !usr.isLogged()) && (uri.endsWith("dodaj.xhtml") || uri.endsWith("user.xhtml") || uri.endsWith("edit.xhtml"))) {
			usr.setResponse("Najpierw siê zaloguj");
			res.sendRedirect("login.xhtml");
			return;
		}

		if ((usr == null || !usr.isAdmin()) && (uri.endsWith("admin.xhtml") || uri.contains("admin_"))) {
			 usr.setResponse("Tylko administrator ma dostêp do panelu administracyjnego");
			res.sendRedirect("home.xhtml");
			return;
		}

		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
