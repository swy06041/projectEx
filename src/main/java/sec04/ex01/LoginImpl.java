package sec04.ex01;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//@WebListener
public class LoginImpl implements HttpSessionListener {

	String userId;
	String userPass;

	static int totalUser = 0;

	public LoginImpl() {

	}

	public LoginImpl(String userId, String userPass) {
		this.userId = userId;
		this.userPass = userPass;
	}

	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("세션 생성, 어떤 고객(클라이언트)이 접속함");
		++totalUser;
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("세션 종료 , 어떤 고객(클라이언트)이 빠져 나감");
		--totalUser;
	}

}
