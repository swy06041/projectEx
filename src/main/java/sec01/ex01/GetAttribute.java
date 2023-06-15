package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/get")
public class GetAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = getServletContext();
		System.out.println(context.getServletContextName());
		
		HttpSession sess = request.getSession();
		
		String ctxMsg = (String) context.getAttribute("ctx");
		String sessMsg = (String) sess.getAttribute("sses");
		String reqMsg = (String) request.getAttribute("request");
		
		System.out.println(ctxMsg);
		System.out.println(sessMsg);
		System.out.println(reqMsg);
		
	}

}
