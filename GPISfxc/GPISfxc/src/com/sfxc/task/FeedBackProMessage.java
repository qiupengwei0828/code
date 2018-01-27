package com.sfxc.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.base.utils.comm.Global;
import com.sfxc.entity.ProResultBxgxx;
import com.sfxc.entity.ProResultDlrzxx;
import com.sfxc.entity.ProResultJjxx;
import com.sfxc.entity.ProResultJyxx;
import com.sfxc.entity.ProResultPosxx;
import com.sfxc.entity.ProResultZhxx;

public class FeedBackProMessage {
	
	@SuppressWarnings("static-access")
	public void feedBackMessage(List<ProResultBxgxx> listBxgxxs, List<ProResultDlrzxx> listDlrzxx, 
			List<ProResultJjxx> listJjxx, List<ProResultPosxx> listPosxxs, List<ProResultZhxx> listZhxx,
			List<ProResultJyxx> listJyxx, String fileName){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = sdFormat.format(date);
		Global bal = new Global();
		String path = bal.getConfig("pro.return.file.bak.path");
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
			  content+="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><backinfo><zhxxList>";
			  if(listZhxx.size()>0){
				  for(int i=0;i<listZhxx.size();i++){
					  content+="<zhxx BDHM=\""+listZhxx.get(i).getBdhm()+"\" " 
					  		+ "ZHXH=\""+i+"\" KHZH=\""+listZhxx.get(i).getKhzh()+"\" YE=\""+listZhxx.get(i).getYe()+"\" "  
					  				+ "FKSJ=\""+dateString+"\" ZHLB=\""+listZhxx.get(i).getZhlb()+"\" ZHZT=\""+listZhxx.get(i).getZhzt()+"\" " 
					  						+ "KHWD=\""+listZhxx.get(i).getKhwd()+"\" BZ=\""+listZhxx.get(i).getBz()+"\" TXDZ=\""+listZhxx.get(i).getTxdz()+"\" " 
					  								+ "YZBM=\""+listZhxx.get(i).getYzbm()+"\" LXDH=\""+listZhxx.get(i).getLxdh()+"\" BEIZ=\""+listZhxx.get(i).getBeiz()+"\" " 
					  										+ "SFTZ=\""+listZhxx.get(i).getSftz()+"\" KHRQ=\""+listZhxx.get(i).getKhrq()+"\" XHRQ=\""+listZhxx.get(i).getXhrq()+"\"></zhxx>"; 
				  }
			  }else{
				  content+="<zhxx BDHM=\""+fileName+"\" ZHXH=\"1\" KHZH=\"查无开户信息\" YE=\"\"  FKSJ=\""+dateString+"\" ZHLB=\"\" ZHZT=\"\""
				  		+ " KHWD=\"\" BZ=\"\" TXDZ=\"\" YZBM=\"\" LXDH=\"\" BEIZ=\"\" SFTZ=\"\" KHRQ=\"\" XHRQ=\"\"></zhxx> ";
			  }
			  content+="</zhxxList><JYXXList>";
			  if(listJyxx.size()>0){
				  for(int i=0;i<listJyxx.size();i++){
					  content+="<JYXX  BDHM=\""+listJyxx.get(i).getBdhm()+"\" KHZH=\""+listJyxx.get(i).getKhzh()+"\" JYLSH=\""+listJyxx.get(i).getJylsh()+"\" "
					  		+ "JYLX=\""+listJyxx.get(i).getJylx()+"\" JYFS=\""+listJyxx.get(i).getJyfs()+"\" JYWD=\""+listJyxx.get(i).getJywd()+"\" "
					  				+ "JJBH=\""+listJyxx.get(i).getJjbh()+"\" JYSJ=\""+listJyxx.get(i).getJysj()+"\" DFJE=\""+listJyxx.get(i).getDfje()+"\" "
					  						+ "JFJE=\""+listJyxx.get(i).getJfje()+"\" BZ=\""+listJyxx.get(i).getBz()+"\" SFFMC=\""+listJyxx.get(i).getSffmc()+"\" "
					  								+ "SFFZH=\""+listJyxx.get(i).getSffzh()+"\" SFFLX=\""+listJyxx.get(i).getSfflx()+"\" SFFDW=\""+listJyxx.get(i).getSffdw()+"\" "
					  										+ "YE=\""+listJyxx.get(i).getYe()+"\" ZYXX=\""+listJyxx.get(i).getZyxx()+"\" FKSJ=\""+dateString+"\" ></JYXX>"; 
				  }
			  }else{
				  content+="<JYXX  BDHM=\""+fileName+"\" KHZH=\"1\" JYLSH=\"查无交易明细信息\" JYLX=\"\" JYFS=\"\" JYWD=\"\" JJBH=\"\" JYSJ=\"\" DFJE=\"\" JFJE=\"\" BZ=\"\" SFFMC=\"\" SFFZH=\"\" SFFLX=\"\" SFFDW=\"\" YE=\"\" ZYXX=\"\" FKSJ=\""+dateString+"\" ></JYXX> ";
			  }
			  content+="</JYXXList><BXGXXList> ";
			  if(listBxgxxs.size()>0){
				  for(int i=0;i<listBxgxxs.size();i++){
					  content+="<BXGXX BDHM=\""+listBxgxxs.get(i).getBdhm()+"\" BXGXH=\""+listBxgxxs.get(i).getBxgxh()+"\" BXGH=\""+listBxgxxs.get(i).getBxgh()+"\" FKSJ=\""+listBxgxxs.get(i).getFksj()+"\" "
					  		+ " BXGZT=\""+listBxgxxs.get(i).getBxgzt()+"\" SZWD=\""+listBxgxxs.get(i).getSzwd()+"\" ZYKSSJ =\""+listBxgxxs.get(i).getZykssj()+"\" "
					  				+ "ZYJSSJ =\""+listBxgxxs.get(i).getZyjssj()+"\" BEIZ=\""+listBxgxxs.get(i).getBeiz()+"\"></BXGXX>"; 
				  }
			  }else{
				  content+="<BXGXX BDHM=\""+fileName+"\" BXGXH=\"\" BXGH=\"查无保险柜信息\" FKSJ=\""+dateString+"\" BXGZT=\"\" SZWD=\"\" ZYKSSJ =\"\" ZYJSSJ =\"\" BEIZ=\"\"></BXGXX>";
			  }
			  content+="</BXGXXList><POSXXList> ";
			  
			  if(listPosxxs.size()>0){
				  for(int i=0;i<listPosxxs.size();i++){
					  content+="<POSXX BDHM=\""+listPosxxs.get(i).getBdhm()+"\" POSXH=\""+listPosxxs.get(i).getPosxh()+"\" SH=\""+listPosxxs.get(i).getSh()+"\" "
					  		+ "KHZH=\""+listPosxxs.get(i).getKhzh()+"\" DLDZ=\""+listPosxxs.get(i).getDldz()+"\" TXFS=\""+listPosxxs.get(i).getTxfs()+"\" "
					  				+ "TXSJ =\""+listPosxxs.get(i).getTxsj()+"\" HM =\""+listPosxxs.get(i).getHm()+"\" FKSJ=\""+dateString+"\"></POSXX>"; 
				  }
			  }else{
				  content+="<POSXX BDHM=\""+fileName+"\" POSXH=\"查无POS机信息\" SH=\"\" KHZH=\"\" DLDZ=\"\" TXFS=\"\" TXSJ =\"\" HM =\"\" FKSJ=\""+dateString+"\"></POSXX>";
			  }
			  content+="</POSXXList><WYRZXXList>";
			  
			  if(listDlrzxx.size()>0){
				  for(int i=0;i<listDlrzxx.size();i++){
					  content+="<WYRZXX BDHM=\""+listDlrzxx.get(i).getBdhm()+"\" RZXH=\""+listDlrzxx.get(i).getRzxh()+"\" DLSJ=\""+listDlrzxx.get(i).getDlsj()+"\" "
					  		+ "KHZH=\""+listDlrzxx.get(i).getKhzh()+"\" DLDZ=\""+listDlrzxx.get(i).getDldz()+"\" DLCZ=\""+listDlrzxx.get(i).getDlcz()+"\" "
					  				+ "DLMS =\""+listDlrzxx.get(i).getDlms()+"\" FKSJ=\""+dateString+"\"></WYRZXX>"; 
				  }
			  }else{
				  content+="<WYRZXX BDHM=\""+fileName+"\" RZXH=\"查无登录信息\" DLSJ=\"\" KHZH=\"\" DLDZ=\"\" DLCZ=\"\" DLMS =\"\" FKSJ=\""+dateString+"\"></WYRZXX>";
			  }
			  content+="</WYRZXXList><ZDJJXXList>";
			  if(listJjxx.size()>0){
				  for(int i=0;i<listJjxx.size();i++){
					  content+="<ZDJJXX BDHM=\""+listJjxx.get(i).getBdhm()+"\"  KHZH=\""+listJjxx.get(i).getKhzh()+"\" JJXH=\""+listJjxx.get(i).getJjxh()+"\" "
					  		+ "JJDZ=\""+listJjxx.get(i).getJjdz()+"\" JD=\""+listJjxx.get(i).getJd()+"\" WD=\""+listJjxx.get(i).getWd()+"\"   "
					  				+ "WDH=\""+listJjxx.get(i).getWdh()+"\" JGH=\""+listJjxx.get(i).getJgh()+"\" JJBH=\""+listJjxx.get(i).getJjbh()+"\" "
					  						+ "WDMC =\""+listJjxx.get(i).getWdmc()+"\" LXDH=\""+listJjxx.get(i).getLxdh()+"\" JJLX=\""+listJjxx.get(i).getJjlx()+"\"  "
					  								+ "FKSJ=\""+dateString+"\"></ZDJJXX>"; 
				  }
			  }else{
				  content+="<ZDJJXX BDHM=\""+fileName+"\"  KHZH=\"\" JJXH=\"无机具信息\" JJDZ=\"\" JD=\"\" WD=\"\"   WDH=\"\" JGH=\"\" JJBH=\"\" WDMC =\"\" LXDH=\"\" JJLX=\"\"  FKSJ=\""+dateString+"\"></ZDJJXX>";
			  }
			  content+="</ZDJJXXList></backinfo>";
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
