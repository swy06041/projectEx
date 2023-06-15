package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userId = "", userPass = "";
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);

		if (session != null) {
			isLogon = (Boolean) session.getAttribute("isLogon");
			if (isLogon == true) {
				userId = (String) session.getAttribute("login.userId");
				userPass = (String) session.getAttribute("login.userPass");
				out.print("<html><body>");
				out.print("아이디: " + userId + "<br>");
				out.print("비밀번호: " + userPass + "<br>");
				out.print("</body></html>");
			} else {
				response.sendRedirect("login3.html");
			}
		} else {
			response.sendRedirect("login3.html");
		}
	}
}
