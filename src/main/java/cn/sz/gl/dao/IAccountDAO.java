package cn.sz.gl.dao;

import java.util.List;

import cn.sz.gl.pojo.Account;

public interface IAccountDAO {

	public List<Account> findAccByUserid(Integer userid);
	
	public Double findBalanceByAccid(Integer accid);
	
	public void updateAccount(Account acc);
}
