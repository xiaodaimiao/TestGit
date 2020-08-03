package cn.sz.gl.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;
import cn.sz.gl.service.IStoreHouseService;
import cn.sz.gl.util.MoneyLessException;

@Controller
@RequestMapping("/bc")
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@Autowired
	private IStoreHouseService storehouseService;
	
	@RequestMapping(value="showbook",method=RequestMethod.GET)
	public String showBook(Integer bookid,Model model) {
		Book book = bookService.findBookByBookid(bookid);
		model.addAttribute("book", book);
		return "book_info";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add() {
		System.out.println("add...");
		return "book_add";
	}
	
	/**
	 * springMVC默认情况下， 是不能接收日期类型的；
	 * 要想使用Date来接收页面上传递过来的数据,需要做类型转换
	 * 处理办法：
	 * 1.在控制器的方法传入参数的时候，使用@DateTimeFormat(pattern="yyyy-MM-dd")Date publicDates
	 * 		这种办法，只是针对本次参数接收，其他方法里，要接收日期， 还得写;
	 * 2.可以在实体类中，对应的日期类型的属性前，加上@DateTimeFormat(pattern="yyyy-MM-dd")
	 * 		这种办法，是全局的，针对该属性所有的接收，都可以起作用
	 * 3.还可以自定义类型转换器
	 * 		
	 * 
	 * @param book
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	/*public String add(Book book,@DateTimeFormat(pattern="yyyy-MM-dd")Date publicDates) {
		System.out.println("book:"+book.getBookName()+",日期："+publicDates);*/
	public String add(Book book,@RequestParam MultipartFile pic,HttpServletRequest request) {
		System.out.println("book:"+book.getBookName()+",日期："+book.getPublicDate());
		System.out.println("接收到文件："+pic.getOriginalFilename());
		
		/*MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile pic2 = req.getFile("pic");*/
		
		OutputStream os = null;
		
		try {
			String realpath = request.getSession().getServletContext().getRealPath("/images/upload");
			String uuid = UUID.randomUUID().toString();
			File file = new File(realpath+"/"+uuid+pic.getOriginalFilename());
			os = new FileOutputStream(file);
			FileCopyUtils.copy(pic.getInputStream(), os);
			book.setImgPath("images/upload/"+uuid+pic.getOriginalFilename());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		bookService.addBook(book);
		return "redirect:/finddata";
	}
	
	@RequestMapping(value="download",method=RequestMethod.GET)
	public ResponseEntity<byte[]> downloadfile(String filename,HttpServletRequest request,HttpServletResponse response) {
		/*try {
			ServletOutputStream sos = response.getOutputStream();
			sos.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		String realpath = request.getSession().getServletContext().getRealPath(filename);
		File file = new File(realpath);
		
		HttpHeaders headers = new HttpHeaders();
		//设置在客户端浏览器打开保存弹框组件
		headers.setContentDispositionFormData("attachment", filename);
		
		//三个参数：第一个，写入要响应的内容，这里要做下载，所以响应内容应该是文件内容，这里准备一个byte[]来保存文件的字节;
		//第二个参数，用来描述响应头
		//第三个参数，表示响应的状态
		try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@RequestMapping(value="buybook",method=RequestMethod.GET)
	public String buybook(Integer bookid,Model model) {
		System.out.println("buybook方法，get处理...");
		//根据bookid，查询书籍信息
		Book book = bookService.findBookByBookid(bookid);
		//根据bookid,查询书籍的库存的数量
		int count = storehouseService.findCountByBookid(bookid);
		//这里也可以查出来当前用户的账号列表,这里先不写，留到页面上，用ajax来查询
		
		model.addAttribute("book", book);
		model.addAttribute("count", count);
		return "book_order";
	}
	
	
	@RequestMapping(value="buybook",method=RequestMethod.POST)
	public String buybook(Integer bookid,Integer accid) {
		bookService.buybook(bookid, accid);
		return "redirect:/finddata";
	}
	
	/*@ExceptionHandler
	public String handlerError(Exception e,Model model) {
		if(e instanceof MoneyLessException) {
			model.addAttribute("msg", "钱不够了");
		}
		return "error_moneyless";
	}*/
	
}
