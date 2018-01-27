package com.sfxc.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.base.utils.comm.Global;
import com.sfxc.entity.CourtResultDjxx;
import com.sfxc.entity.CourtResultGlzhxx;
import com.sfxc.entity.CourtResultZhxx;
import com.sfxc.entity.CourtResultZjwlxx;

public class FeedBackCourtMessage {
	
	@SuppressWarnings("static-access")
	public void feedBackMessage(List<CourtResultDjxx> listDjxx, List<CourtResultZjwlxx> listZjwlxx, 
			List<CourtResultGlzhxx> listGlzhxx, List<CourtResultZhxx> listZhxx, String fileName){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = sdFormat.format(date);
		Global bal = new Global();
		String path = bal.getConfig("court.return.file.bak.path");
		File file = new File(path+fileName+".txt");
		boolean flag=false;  
		  try{  
		   if(!file.exists()){  
			   file.createNewFile();  
		       flag=true;  
		   }  
		  }catch(Exception e){  
		   e.printStackTrace();  
		  }  
		  if(flag){
			  String content = "";
			  content+="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><zhxxList>";
			  if(listZhxx.size()>0){
				  for(int i=0;i<listZhxx.size();i++){
					  content+="<zhxx BDHM=\""+listZhxx.get(i).getBdhm()+"\" " 
					  		+ "ZHXH=\""+i+"\" KHZH=\""+listZhxx.get(i).getKhzh()+"\" YE=\""+listZhxx.get(i).getYe()+"\" "  
					  				+ "FKSJ=\""+dateString+"\" CCLB=\""+listZhxx.get(i).getCclb()+"\" ZHZT=\""+listZhxx.get(i).getZhzt()+"\" " 
					  						+ "KHWD=\""+listZhxx.get(i).getKhwd()+"\" BZ=\""+listZhxx.get(i).getBz()+"\" TXDZ=\""+listZhxx.get(i).getTxdz()+"\" " 
					  								+ "YZBM=\""+listZhxx.get(i).getYzbm()+"\" LXDH=\""+listZhxx.get(i).getLxdh()+"\" BEIZ=\""+listZhxx.get(i).getBeiz()+"\" " 
					  										+ "SFTZ=\""+listZhxx.get(i).getSftz()+"\">"; 
				  }				  
				  if(listDjxx.size()>0){
					  content+="<djxxList>";
					  for(int i=0;i<listDjxx.size();i++){
						  content+="<djxx CSXH=\""+listDjxx.get(i).getCsxh()+"\" SFBDJ=\""+listDjxx.get(i).getSfbdj()+"\" "
						  		+ "DJJZRQ=\""+listDjxx.get(i).getDjjzrq()+"\" DJJG=\""+listDjxx.get(i).getDjjg()+"\" "
						  				+ "DJWH=\""+listDjxx.get(i).getDjwh()+"\" DJJE=\""+listDjxx.get(i).getDjje()+"\"></djxx>"; 
					  }
					  content+="<djxxList>";
				  }
				  if(listZjwlxx.size()>0){
					  content+="<wlxxList>";
					  for(int i=0;i<listZjwlxx.size();i++){
						  content+="<wlxx WLXH=\""+listZjwlxx.get(i).getWlxh()+"\" ZJLX=\""+listZjwlxx.get(i).getZjlx()+"\" "
						  		+ "ZCKZH=\""+listZjwlxx.get(i).getZckzh()+"\" ZCKZXM=\""+listZjwlxx.get(i).getZckzxm()+"\" "
						  				+ "BZ=\""+listZjwlxx.get(i).getBdhm()+"\" JE=\""+listZjwlxx.get(i).getJe()+"\" "
						  						+ "JYSJ=\""+listZjwlxx.get(i).getJysj()+"\"></wlxx>"; 
					  }
					  content+="<wlxxList>";
				  }
				  if(listGlzhxx.size()>0){
					  content+="<glxxList>";
					  for(int i=0;i<listGlzhxx.size();i++){
						  content+="<glxx GLXH=\""+listGlzhxx.get(i).getGlxh()+"\" GLZHLB=\""+listGlzhxx.get(i).getGlzhlb()+"\" "
						  		+ "GLZHHM=\""+listGlzhxx.get(i).getGlzhhm()+"\" GLZHMC=\""+listGlzhxx.get(i).getGlzhmc()+"\"></glxx>"; 
					  }
					  content+="<glxxList>";
				  }
				  content+="</zhxx>";
			  }else{
				  content+="<zhxx BDHM=\""+fileName+"\" ZHXH=\"1\" KHZH=\"查无开户信息\" YE=\"\"  FKSJ=\""+dateString+"\" CCLB=\"\" ZHZT=\"\""
				  		+ " KHWD=\"\" BZ=\"\" TXDZ=\"\" YZBM=\"\" LXDH=\"\" BEIZ=\"\" SFTZ=\"\" KHRQ=\"\" XHRQ=\"\"></zhxx> ";
			  }
			  content+="</ZDJJXXList>";
			  try {
				writeTxtFile(content,file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		  }
	}
	public  boolean writeTxtFile(String content,File fileName)throws Exception{  
		  RandomAccessFile mm=null;  
		  boolean flag=false;  
		  FileOutputStream o=null;  
		  try {  
		   o = new FileOutputStream(fileName);  
		      o.write(content.getBytes("UTF-8"));  
		      o.close();  
		   flag=true;  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }finally{  
		   if(mm!=null){  
		    mm.close();  
		   }  
		  }  
		  return flag;  
		 }
}
