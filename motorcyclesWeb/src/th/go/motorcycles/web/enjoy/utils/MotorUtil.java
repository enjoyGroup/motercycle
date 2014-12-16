package th.go.motorcycles.web.enjoy.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class MotorUtil extends EnjoyUtils {
	private HttpServletRequest request;
	private HttpServletResponse response;

	public MotorUtil() {

	}

	public MotorUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void writeMSG(String msg) {
		PrintWriter print = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			print = this.response.getWriter();
			print.write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeJsonMSG(String[] msg) {
		PrintWriter print = null;
		JSONArray arrayObj = new JSONArray();

		try {
			this.response.setContentType("text/html; charset=UTF-8");
			this.response.setHeader("Cache-control", "no-cache, no-store");
			this.response.setHeader("Pragma", "no-cache");
			this.response.setHeader("Expires", "-1");

			for (int i = 0; i < msg.length; i++) {
				arrayObj.add(msg[i]);
			}

			print = this.response.getWriter();
			print.write(arrayObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
