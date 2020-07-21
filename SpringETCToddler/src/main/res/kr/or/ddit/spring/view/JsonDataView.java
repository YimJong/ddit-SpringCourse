package kr.or.ddit.spring.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("jsonView")
public class JsonDataView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> params,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonData = (String) params.get("jsonData");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(jsonData);
	}
	
}
