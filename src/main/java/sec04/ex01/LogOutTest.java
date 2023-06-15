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

//@WebServlet("/logout")
public class LogOutTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletContext ctx;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ctx = getServletContext();
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");

		session.invalidate();

		List userList = (ArrayList) ctx.getAttribute("userList");
		userList.remove(userId);
		ctx.removeAttribute("userList");
		ctx.setAttribute("list", userList);
		out.print("<br>로그아웃 했습니다.");
	}

}
