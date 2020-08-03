package cn.sz.gl.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;

@Controller
@RequestMapping("/")
public class FirstpageController {

	@Autowired
	private IBookService bookService;
	
	@RequestMapping(value="/")
	public String firstpage() {
		System.out.println("firstpage方法...");
		
		return "forward:finddata";
	}
	
	@RequestMapping("finddata")
	public String findDataForMain(String bookname,String deptname,String loprice,String hiprice,String auth,
			@RequestParam(defaultValue="1",required=false)Integer cp,
			@RequestParam(defaultValue="3",required=false)Integer ps,Model model) {
		System.out.println("findData...");
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("bookname:"+bookname);
		if(bookname!=null&&!bookname.equals("")) {
			map.put("bookname", bookname);
			model.addAttribute("bookname", bookname);
		}
		System.out.println("deptname:"+deptname);
		if(deptname!=null&&!deptname.equals("")) {
			map.put("deptname", deptname);
			model.addAttribute("deptname", deptname);
		}
		if(loprice!=null&&hiprice!=null&&!loprice.equals("")&&!hiprice.equals("")) {
			map.put("hiprice", hiprice);
			map.put("loprice", loprice);
			model.addAttribute("loprice", loprice);
			model.addAttribute("hiprice", hiprice);
		}
		
		if(auth!=null&&!auth.equals("")) {
			map.put("auth", auth);
			model.addAttribute("auth", auth);
		}
		map.put("start", (cp-1)*ps);
		map.put("end", cp*ps);
		List<Book> booklist = bookService.findBookSplitAndCondition(map);//数据
		int count = bookService.countBook(map);//满足条件的数据的总行数
		int allpage = (count-1)/ps+1;//总页码数
		
		model.addAttribute("cp", cp);
		model.addAttribute("ps", ps);
		model.addAttribute("booklist", booklist);
		model.addAttribute("count", count);
		model.addAttribute("allpage", allpage);
		
		
		return "main";
	}
	
}
