package cn.sz.gl.pojo;

import java.io.Serializable;

public class StoreHouse implements Serializable {

	private Integer bookid;//书籍编号
	private Integer count;//数量
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
