package com.nlp.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReHelper {
	
	public  static Map<Integer ,String> getReferenceInfo(String text){
		Map<Integer ,String> map = new HashMap<Integer ,String>();
		if(text== null) {
			map.put(1,Constans.NoReference);
			return map;
			
		}
		//引用信息
		String referenees = null;
		//替换多余空格或制表符
		text = text.replaceAll("\\p{Zs}|\t|\r|\n", " ");
		//替换不规则引用符号
		text = text.replaceAll("\\［|\\【", "[");
		text = text.replaceAll("\\］|\\】", "]");
		
		Pattern patthoncn=Pattern.compile("\\参\\s*\\考\\s*\\文\\s*\\献[\\S\\s]*"); 
		Pattern pattcnen=Pattern.compile("((?i)R)eferenees[\\S\\s]*"); 
		Matcher matcher = patthoncn.matcher(text);
	    while(matcher.find()){		
	    	referenees = matcher.group();
		}
	    if(referenees == null) {
	    	matcher = pattcnen.matcher(text);
		    while(matcher.find()){			
		    referenees = matcher.group();
			}
	    	
	    }
	    if(referenees == null) {
	    	map.put(1,Constans.NoReference);
			return map;
	    }
		Pattern p=Pattern.compile("\\[\\s*\\d*\\s*\\]"); 
		String[] str=p.split(referenees); 
		
		if(str ==null&&str.length==0) {
			map.put(1,Constans.NoReference);
			return map;
		}
		for(int i = 1; i <str.length;i++) {
			map.put(i, str[i]);
			//System.out.println(String.valueOf(i)+": "+str[i]); 
		}
		return map;
		
	}

}
