package th.go.motorcycles.web.enjoy.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EnjoyStandardItf {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
