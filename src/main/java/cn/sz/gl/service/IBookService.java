package cn.sz.gl.service;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Book;

public interface IBookService {

	public List<Book> findBookSplitAndCondition(Map<String, Object> map);

	public Integer countBook(Map<String, Object> map);
	
	public Book findBookByBookid(Integer bookid);
	
	public void addBook(Book book);
	
	public void buybook(Integer bookid,Integer accid);
}
