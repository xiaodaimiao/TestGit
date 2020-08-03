package cn.sz.gl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.gl.dao.IUserDAO;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userdao;
	
	public Users checklogin(Users user) {
		if(user==null) {
			return null;
		}
		if(user.getLoginname()==null||user.getLoginpwd()==null) {
			return null;
		}
		return userdao.findUserByNameAndPwd(user);
	}

}
