package sec04.ex01;

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

//@WebServlet("/login")
public class LoginTest extends HttpServlet {

	List userList = new ArrayList();
	ServletContext ctx = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		ctx = getServletContext();

		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");

		LoginImpl loginmem = new LoginImpl(userId, userPass);

		HttpSession sess = request.getSession();

		if (sess.isNew()) {
			sess.setAttribute("loginmem", loginmem);
			userList.add(userId);
			ctx.setAttribute("userList", userList);
		}
		out.print("<html><body>");
		out.print("안녕하세요 " + userId + "님!!!<br>");
		out.print("접속인원 " + LoginImpl.totalUser + "명!!!<br>");

		List list = (ArrayList) ctx.getAttribute("userList");

		for (int i = 0; i < list.size(); i++) {
			out.print(list.get(i) + "<br>");
		}
		out.print("<a href='logout?userId=" + userId + "'>로그아웃 </a>");
		out.print("</body></html>");
	}

}
