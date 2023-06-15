package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext; // 웹 애플리케이션
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/set")
public class SetAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String ctxMsg = "컨텍스트 범위에 바인딩 됨";
		String sessMsg = "세선 범위에 바인딩 됨";
		String reqMsg = "리퀘스트범위에 바인딩 됨";

		ServletContext context = getServletContext();
		HttpSession sses = request.getSession();

		context.setAttribute("ctx", ctxMsg);
		sses.setAttribute("sses", sessMsg);
		request.setAttribute("request", reqMsg);
	}

}
