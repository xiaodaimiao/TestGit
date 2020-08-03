package cn.sz.gl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.gl.dao.IAccountDAO;
import cn.sz.gl.pojo.Account;
import cn.sz.gl.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDAO accdao;
	
	public List<Account> findAccByUserid(Integer userid) {
		if(userid==null) {
			return null;
		}
		return accdao.findAccByUserid(userid);
	}

	public Double findBalanceByAccid(Integer accid) {
		if(accid==null) {
			return 0.0;
		}
		return accdao.findBalanceByAccid(accid);
	}

}
