package cn.sz.gl.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sz.gl.pojo.Account;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IAccountService;
import cn.sz.gl.service.IUserService;

@Controller
//@RestController
@RequestMapping("/uc")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccountService accService;
	
	@RequestMapping("login")
	public String islogin(Users u,HttpServletRequest request) {
		Users user = userService.checklogin(u);
		if(user==null) {
			return "login";
		}else {
			request.getSession().setAttribute("myuser", user);
			return "redirect:/finddata";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="showaccount",method=RequestMethod.POST)
	public List<Account> showAccount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users u = (Users) session.getAttribute("myuser");
		List<Account> acclist = accService.findAccByUserid(u.getUserid());
		return acclist;
	}
	
	@ResponseBody
	@RequestMapping(value="showbalance",method=RequestMethod.POST)
	public Double showbalance(Integer accid) {
		return accService.findBalanceByAccid(accid);
	}
	
}
