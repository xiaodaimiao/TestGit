package cn.sz.gl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {

	private static String [] arr = {
			"finddata","bc/showbook","bc/download","uc/login"
	};
	
	public static boolean checkUrl(String requestname) {
		
		if(requestname==null||requestname.equals("")) {
			return true;
		}
		
		if(requestname.endsWith(".js")||requestname.endsWith(".css")||requestname.endsWith(".jpg")||requestname.endsWith(".png")) {
			return true;
		}
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(requestname)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 在适配器适配成功之后，控制器目标方法执行之前，要调用preHandle方法;
	 * 返回一个boolean值:
	 * 	如果为true: 表示请求可以继续，接下来会执行控制器的目标方法;
	 * 	如果为false: 请求到此为止，不会继续，包括控制器的方法也不会被调用；
	 * 
	 * 
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("执行了preHandle方法");
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("myuser");
		
		//获取当前请求的名字
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		String requestname = uri.substring(contextpath.length()+1);
		
		if(obj==null) {
			
			if(checkUrl(requestname)) {
				return true;
			}
			
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return false;
		}else {
			return true;
		}
		
		
		
	}

	
	/**
	 * 控制器的目标方法执行之后，要调用的内容
	 * 该方法执行，有一个前提，就是preHandle方法，必须返回true,这里才会被调用
	 * 
	 * 
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("执行postHandle方法");

	}

	/**
	 * 当完成请求处理的时候，在响应之前，要调用afterCompletion方法;
	 * 该方法能够调用的前提，是preHandle方法必须返回true
	 * 一般用来释放资源
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("执行afterCompletion方法");

	}

}
