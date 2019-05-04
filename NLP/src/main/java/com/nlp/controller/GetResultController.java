package com.nlp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nlp.tools.Constans;
import com.nlp.tools.FileHelper;
import com.nlp.tools.TextRankSummary;
import com.nlp.tools.pdfToString;

@Controller
public class GetResultController {
	@RequestMapping(value="/GetResult",method=RequestMethod.GET)
	public void getResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		/**
		 * 当多线程并发访问这个方法里面的代码时，会存在线程安全问题吗
		 * i变量被多个线程并发访问，但是没有线程安全问题，因为i是doGet方法里面的局部变量，
		 * 当有多个线程并发访问doGet方法时，每一个线程里面都有自己的i变量， 各个线程操作的都是自己的i变量，所以不存在线程安全问题
		 * 多线程并发访问某一个方法的时候，如果在方法内部定义了一些资源(变量，集合等) 那么每一个线程都有这些东西，所以就不存在线程安全问题了
		 */
		String text =new String( request.getParameter("value").getBytes("ISO-8859-1"),"UTF-8");
		String output = null;
		if(text == Constans.NoReference) {
			output = Constans.NoReference;
			
		}else {
			String filepath =FileHelper.getFileURL(text);
			if(filepath == null) {
				output =Constans.NosuchFile;				
			}else {
				String pdfInfo = pdfToString.getPdfText(filepath);				
				List<String> listStr =  TextRankSummary.getTopSentenceList(pdfInfo,text, 10);
				output = String.join(",", listStr);		
			}		
		}
		response.getWriter().write(output);
		
	}
	
	@RequestMapping(value="/GetTitle",method=RequestMethod.GET)
	public void getTitle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		/**
		 * 当多线程并发访问这个方法里面的代码时，会存在线程安全问题吗
		 * i变量被多个线程并发访问，但是没有线程安全问题，因为i是doGet方法里面的局部变量，
		 * 当有多个线程并发访问doGet方法时，每一个线程里面都有自己的i变量， 各个线程操作的都是自己的i变量，所以不存在线程安全问题
		 * 多线程并发访问某一个方法的时候，如果在方法内部定义了一些资源(变量，集合等) 那么每一个线程都有这些东西，所以就不存在线程安全问题了
		 */
		String Query =new String( request.getParameter("query").getBytes("ISO-8859-1"),"UTF-8");
		String refer =new String( request.getParameter("reference").getBytes("ISO-8859-1"),"UTF-8");
		
		refer = refer.trim();
		String output = null;
		if(Query == Constans.NoReference) {
			output = Constans.NoReference;
			
		}else {
			String filepath =FileHelper.getFileURL(refer);
			if(filepath == null) {
				output =Constans.NosuchFile;				
			}else {
				String pdfInfo = pdfToString.getPdfText(filepath);				
				List<String> listStr =  TextRankSummary.getTopSentenceList(pdfInfo,Query, 10);
				output = String.join(",", listStr);		
			}		
		}
		//System.out.println(output);
		response.getWriter().write(output);
		
	}
	

}
