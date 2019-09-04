package com.tools;


import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer; 
import java.util.TimerTask;

import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.ordit.model.OrditService;
import com.ordit.model.OrditVO;


import javax.servlet.ServletContext;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

 
public class Schedule  extends HttpServlet{
	 String strDate = ""; 
	 Timer timer;
	 
	 public void destroy() {

			timer.cancel();
			
		}
	 
	 @Override 
	 public void init() { 
	  
	 Timer timer = new Timer(); 
	  	  
	  timer.schedule(new TimerTask(){ 
	  @Override 
	  public void run() { 
		   str_rank_do();
		   str_rank_do_week();
		   str_rank_do_month();
		   dish_rank_class01();
		   dish_rank_class02();
		   dish_rank_class03();
		   dish_rank_class05();
		   
		   
	  }  
	  }, new Date() , 60*10*1000); 
	  
	 
	  
	 } 
	 
	 public void dish_rank_class01(){
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 OrditService orditSvc = new OrditService();
		 
		 List<OrditVO> orditVO = orditSvc.getDish_class01_all();
		   for(int i=0;i<orditVO.size();i++){
   			   
			   String Dish_no = orditVO.get(i).getDish_no();
			   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
			   if(map.containsKey(Dish_no)){
					  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
				   }
				   
				   map.put(Dish_no, Qrdit_qua);
				   //System.out.println(Dish_no+"類別1");
				   //System.out.println(Qrdit_qua);	
			   
			   }
		   
		   List<Map.Entry<String, Integer>> rank_class1 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); 
			
			Collections.sort(rank_class1, new Comparator<Map.Entry<String, Integer>>() {  
			    @Override  
			    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	
			        return o2.getValue().compareTo(o1.getValue()); // 降序  
			    }  
			});  
			
			
			
			ServletContext context = getServletContext();
			context.setAttribute("rank_class1", rank_class1);
	 }
	 
	 public void dish_rank_class02(){
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 OrditService orditSvc = new OrditService();
		 
		 List<OrditVO> orditVO = orditSvc.getDish_class02_all();
		   for(int i=0;i<orditVO.size();i++){
   			   
			   String Dish_no = orditVO.get(i).getDish_no();
			   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
			   if(map.containsKey(Dish_no)){
					  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
				   }
				   
				   map.put(Dish_no, Qrdit_qua);
				   //System.out.println(Dish_no+"類別2");
				   //System.out.println(Qrdit_qua);	
			   
			   }
		   
		   List<Map.Entry<String, Integer>> rank_class2 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); 
			
			Collections.sort(rank_class2, new Comparator<Map.Entry<String, Integer>>() {  
			    @Override  
			    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	
			        return o2.getValue().compareTo(o1.getValue()); // 降序  
			    }  
			});  
			
			ServletContext context = getServletContext();
			context.setAttribute("rank_class2", rank_class2);
	 }
	 
	 public void dish_rank_class03(){
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 OrditService orditSvc = new OrditService();
		 
		 List<OrditVO> orditVO = orditSvc.getDish_class03_all();
		   for(int i=0;i<orditVO.size();i++){
   			   
			   String Dish_no = orditVO.get(i).getDish_no();
			   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
			   if(map.containsKey(Dish_no)){
					  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
				   }
				   
				   map.put(Dish_no, Qrdit_qua);
				  // System.out.println(Dish_no+"類別3");
				   //System.out.println(Qrdit_qua);	
			   
			   }
		   
		   List<Map.Entry<String, Integer>> rank_class3 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); 
			
			Collections.sort(rank_class3, new Comparator<Map.Entry<String, Integer>>() {  
			    @Override  
			    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	
			        return o2.getValue().compareTo(o1.getValue()); // 降序  
			    }  
			});  
			
			ServletContext context = getServletContext();
			context.setAttribute("rank_class3", rank_class3);
	 }
	 
	 public void dish_rank_class05(){
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 OrditService orditSvc = new OrditService();
		 
		 List<OrditVO> orditVO = orditSvc.getDish_class05_all();
		   for(int i=0;i<orditVO.size();i++){
   			   
			   String Dish_no = orditVO.get(i).getDish_no();
			   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
			   if(map.containsKey(Dish_no)){
					  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
				   }
				   
				   map.put(Dish_no, Qrdit_qua);
				   //System.out.println(Dish_no+"類別5");
				   //System.out.println(Qrdit_qua);	
			   
			   }
		   
		   List<Map.Entry<String, Integer>> rank_class5 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); 
			
			Collections.sort(rank_class5, new Comparator<Map.Entry<String, Integer>>() {  
			    @Override  
			    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	
			        return o2.getValue().compareTo(o1.getValue()); // 降序  
			    }  
			});  
			
			ServletContext context = getServletContext();
			context.setAttribute("rank_class5", rank_class5);
	 }

	 
	 public void str_rank_do_month(){
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 Map<String, Integer> str_map = new HashMap<String, Integer>();
		 OrditService orditSvc = new OrditService();
		 DishService dishSvc = new DishService();
			  
	   
	   
		   List<OrditVO> orditVO = orditSvc.getAll_month();
		   for(int i=0;i<orditVO.size();i++){
			   			   
			   String Dish_no = orditVO.get(i).getDish_no();
			   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
			   
			   //System.out.println(Dish_no);
			   //System.out.println(Qrdit_qua);
			   
			   if(map.containsKey(Dish_no)){
				  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
			   }
			   
			   map.put(Dish_no, Qrdit_qua);
			   
		   	}
		   
			//System.out.println(map+"所有加總");
			
			
			for (Object key : map.keySet()) {
				
				DishVO dishVO = dishSvc.getDishForStrAll(key.toString());
				String str_no = dishVO.getStr_no();				
				int str_qua = map.get(key);
	
				System.out.println(str_no);
				
				if(str_map.containsKey(str_no)){
					 str_qua = str_qua + str_map.get(str_no); 
					
				   }
				
				str_map.put(str_no,str_qua);
				
			}
		
			//System.out.println(str_map+"店家所有加總");
			 	
			List<Map.Entry<String, Integer>> rank_str = new ArrayList<Map.Entry<String, Integer>>(str_map.entrySet()); 
			
			Collections.sort(rank_str, new Comparator<Map.Entry<String, Integer>>() {  
			    @Override  
			    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	
			        return o2.getValue().compareTo(o1.getValue()); // 降序  
			    }  
			});  
			
			
			//System.out.println(rank_str+"month");
			ServletContext context = getServletContext();
			context.setAttribute("rank_month", rank_str);
	 }
	 
	 public void str_rank_do_week(){
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 Map<String, Integer> str_map = new HashMap<String, Integer>();
		 OrditService orditSvc = new OrditService();
		 DishService dishSvc = new DishService();
			  
	   
	   
		   List<OrditVO> orditVO = orditSvc.getAll_week();
		   for(int i=0;i<orditVO.size();i++){
			   			   
			   String Dish_no = orditVO.get(i).getDish_no();
			   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
			   
			   //System.out.println(Dish_no);
			  // System.out.println(Qrdit_qua);
			   
			   if(map.containsKey(Dish_no)){
				  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
			   }
			   
			   map.put(Dish_no, Qrdit_qua);
			   
		   	}
		   
			//System.out.println(map+"所有加總");
			
			
			for (Object key : map.keySet()) {
				
				DishVO dishVO = dishSvc.getDishForStrAll(key.toString());
				String str_no = dishVO.getStr_no();				
				int str_qua = map.get(key);
	
				//System.out.println(str_no);
				
				if(str_map.containsKey(str_no)){
					 str_qua = str_qua + str_map.get(str_no); 
					
				   }
				
				str_map.put(str_no,str_qua);
				
			}
		
			//System.out.println(str_map+"店家所有加總");
			 	
			List<Map.Entry<String, Integer>> rank_str = new ArrayList<Map.Entry<String, Integer>>(str_map.entrySet()); 
			
			Collections.sort(rank_str, new Comparator<Map.Entry<String, Integer>>() {  
			    @Override  
			    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	
			        return o2.getValue().compareTo(o1.getValue()); // 降序  
			    }  
			});  
			
			//System.out.println(rank_str+"WEEK");
			
			ServletContext context = getServletContext();
			context.setAttribute("rank_week", rank_str);
	 }
	 
	 public void str_rank_do(){
	   Map<String, Integer> map = new HashMap<String, Integer>();
	   Map<String, Integer> str_map = new HashMap<String, Integer>();
	   OrditService orditSvc = new OrditService();
	   DishService dishSvc = new DishService();
		  
   
   
	   List<OrditVO> orditVO = orditSvc.getAll();
	   for(int i=0;i<orditVO.size();i++){
		   			   
		   String Dish_no = orditVO.get(i).getDish_no();
		   int Qrdit_qua = orditVO.get(i).getOrdit_qua();
		   

		   
		   if(map.containsKey(Dish_no)){
			  Qrdit_qua = Qrdit_qua + map.get(Dish_no); 
		   }
		   
		   map.put(Dish_no, Qrdit_qua);
		   //System.out.println(Dish_no);
		   //System.out.println(Qrdit_qua);
		   
	   	}
	   
		//System.out.println(map+"所有加總");
		
		
		for (Object key : map.keySet()) {
			
			DishVO dishVO = dishSvc.getDishForStrAll(key.toString());
			String str_no = dishVO.getStr_no();				
			int str_qua = map.get(key);
			              
			 if(str_map.containsKey(str_no)){
				 str_qua = str_qua + str_map.get(str_no); 
				
			   }
			
			str_map.put(str_no,str_qua);
			
		}
	
		//System.out.println(str_map+"店家所有加總all");
		 	
		List<Map.Entry<String, Integer>> rank_str = new ArrayList<Map.Entry<String, Integer>>(str_map.entrySet()); 
		
		Collections.sort(rank_str, new Comparator<Map.Entry<String, Integer>>() {  
		    @Override  
		    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  

		        return o2.getValue().compareTo(o1.getValue()); // 降序  
		    }  
		});  
		
		
		
		ServletContext context = getServletContext();
		context.setAttribute("rank_str", rank_str);
	 }
	 
	 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
	  res.setContentType("text/plain"); 
	  PrintWriter out = res.getWriter(); 
	 
	 } 
	}
