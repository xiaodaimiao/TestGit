package cn.sz.gl.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.sz.gl.util.MoneyLessException;
import cn.sz.gl.util.StoreHouseLessException;

public class MyExceptionHander implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error_simple");
		
		if(ex instanceof MoneyLessException) {
			mav.addObject("msg", "余额不够");
		}else if(ex instanceof StoreHouseLessException) {
			mav.addObject("msg", "没库存了");
		}
		
		return mav;
	}

}
