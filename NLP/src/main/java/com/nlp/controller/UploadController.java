package com.nlp.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nlp.tools.Constans;
import com.nlp.tools.ReHelper;
import com.nlp.tools.pdfToString;

@Controller 
public class UploadController {
	private static Logger log=LoggerFactory.getLogger(UploadController.class);
	@RequestMapping(value="/Upload")
	public String showMessage(HttpServletRequest request,Model model) throws IllegalStateException, IOException{
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
       CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
       //检查form中是否有enctype="multipart/form-data"
       if(multipartResolver.isMultipart(request))
       {
           //将request变成多部分request
           MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
          //获取multiRequest 中所有的文件名
           Iterator iter=multiRequest.getFileNames();
            
           while(iter.hasNext())
           {
               //一次遍历所有文件
               MultipartFile file=multiRequest.getFile(iter.next().toString());
               if(file!=null)
               {
                   String path="F:/springUpload"+File.separator+file.getOriginalFilename();
                   //上传
                   if(!new File(path).exists()){
                       new File(path).mkdirs();
                   }
                   file.transferTo(new File(path));
                   System.out.println(path);
                   String text = pdfToString.getPdfText(path);
                   Map<Integer ,String> map = new HashMap<Integer,String>();
                   if(text == null) {
                	   map.put(1,Constans.NoReference);
                	   model.addAttribute("message","文件上传成功!");
                       model.addAttribute("value",Constans.NoPdfText);
                       model.addAttribute("reference",map);
                	   
                   }else {
                	   map = ReHelper.getReferenceInfo(text);
                       model.addAttribute("message","文件上传成功!");
                       model.addAttribute("value",text);
                       model.addAttribute("reference",map);               	   
                   }                                     
               }
                
           }
          
       }
		return "message";  
		
	}
		
	   

}
