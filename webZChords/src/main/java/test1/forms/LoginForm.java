package test1.forms;

import javax.servlet.http.HttpServletRequest;

import src.gescom.Employe;

public class LoginForm {
	private int loginId;
	private String message;
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void login(HttpServletRequest request) {
		try {
			this.loginId=Employe.login(request.getParameter("nom"), request.getParameter("password"));
			if(this.loginId==0) {
				this.message="Idendifiant incorrects";
			}
		} catch (Exception e) {
			this.message=e.getMessage();
		}
	}
	
}
