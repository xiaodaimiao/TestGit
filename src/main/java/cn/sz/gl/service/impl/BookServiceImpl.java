package cn.sz.gl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sz.gl.dao.IAccountDAO;
import cn.sz.gl.dao.IBookDAO;
import cn.sz.gl.dao.IStoreHouseDAO;
import cn.sz.gl.pojo.Account;
import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;
import cn.sz.gl.util.MoneyLessException;
import cn.sz.gl.util.StoreHouseLessException;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDAO bookdao;
	
	@Autowired
	private IStoreHouseDAO storehousedao;
	
	@Autowired
	private IAccountDAO accdao;
	
	public List<Book> findBookSplitAndCondition(Map<String, Object> map) {
		return bookdao.findBookSplitAndCondition(map);
	}

	public Integer countBook(Map<String, Object> map) {
		return bookdao.countBook(map);
	}

	public Book findBookByBookid(Integer bookid) {
		if(bookid==null) {
			return null;
		}
		return bookdao.findBookByBookid(bookid);
	}

	public void addBook(Book book) {
		if(book==null) {
			return;
		}
		bookdao.addBook(book);
	}

	/**
	 * 实现买一本书
	 * 要求：
	 * 	1.库存数量要减一;
	 * 	2.用户的账户余额要减少
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor= {StoreHouseLessException.class,MoneyLessException.class})
	public void buybook(Integer bookid, Integer accid) {
		
		
		//先查询库存现有的数量
		int count = storehousedao.findCountByBookid(bookid);
		if(count<=0) {
			throw new StoreHouseLessException("库存不足了");
		}
		
		//减库存
		storehousedao.updateStorehouse(bookid);
		
		//减余额
		Account acc = new Account();
		acc.setAccid(accid.longValue());
		//这里需要查询现在的余额和书籍的价格，然后相减
		Double old_balance = accdao.findBalanceByAccid(accid);
		Book book = bookdao.findBookByBookid(bookid);
		Double price = book.getPrice();
		acc.setBalance(old_balance-price);
		
		if(old_balance<book.getPrice()) {
			throw new MoneyLessException("余额不够了");
		}
		
		accdao.updateAccount(acc);
	}

}
