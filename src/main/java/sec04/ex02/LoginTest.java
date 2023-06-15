package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginTest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession();

		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");

		LoginImpl loginmem = new LoginImpl(userId, userPass);

		if (sess.isNew()) {
			sess.setAttribute("loginmem", loginmem);
		}
		System.out.println(LoginImpl.totalUser);
		out.print("<html><head><script>");
		out.print("setTimeout('history.go(0)', 3000)");
		out.print("</script></head><body>");
		out.print("안녕하세요 " + loginmem.userId + "님!!!<br>");
		out.print("접속인원 " + LoginImpl.totalUser + "명!!!<br>");
		out.print("</body></html>");
	}

}
