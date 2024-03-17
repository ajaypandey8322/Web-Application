package com.mySpringboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	
	@RequestMapping("say-hello")
	@ResponseBody//return msg as it is in browser
	public String sayHello() {
		return "Hello , How are You";
	}
	

	@RequestMapping("say-hello-html")
	@ResponseBody//return msg as it is in browser
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>\r\n"
				+ "	<head>\r\n"
				+ "		<title> My 1st html page </title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>My html page body</body>\r\n"
				+ "</html>\r\n"
				+ "");
		
		return sb.toString();
	}
	
	
	/* /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	 * /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	 * /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	 * /src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
	 * */
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}	

}
