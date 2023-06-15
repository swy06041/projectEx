package sec03.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/sess")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession hses = request.getSession();
		System.out.println(hses.isNew());
		System.out.println(hses.getId());
//		hses.setMaxInactiveInterval(3);
//		System.out.println(hses);
//		System.out.println(hses.getMaxInactiveInterval());
		hses.invalidate();
//		
		HttpSession hses2 = request.getSession();
		System.out.println(hses2.isNew());
		System.out.println(hses2.getId());
//		
		System.out.println("------------------");
	}

}
