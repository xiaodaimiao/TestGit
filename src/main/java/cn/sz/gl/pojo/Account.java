package cn.sz.gl.pojo;

import java.io.Serializable;

public class Account implements Serializable {

	private Long accid;//账号
	private String accName;//账户名
	private Double balance;//余额
	private Integer userid;//所属的用户编号
	public Long getAccid() {
		return accid;
	}
	public void setAccid(Long accid) {
		this.accid = accid;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
}
