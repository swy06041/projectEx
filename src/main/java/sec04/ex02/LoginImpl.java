package sec04.ex02;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@WebListener
public class LoginImpl implements HttpSessionBindingListener {

	String userId;
	String userPass;
	static int totalUser = 0;

	public LoginImpl() {

	}

	public LoginImpl(String userId, String userPass) {
		this.userId = userId;
		this.userPass = userPass;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("세션 생성, 어떤 고객(클라이언트)이 접속함");
		++totalUser;
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("세션 종료 , 어떤 고객(클라이언트)이 빠져 나감");
		--totalUser;
	}
}
