package at.mxerp.structures;

import javax.servlet.http.HttpSession;

public class SUserInfo {
	public String user;
	public HttpSession session;
	public String clientIP;
	public String timestamp;
	
	public SUserInfo(String user, HttpSession session, String clientIP) {
		this.user = user;
		this.session = session;
		this.clientIP = clientIP;
	}
}
