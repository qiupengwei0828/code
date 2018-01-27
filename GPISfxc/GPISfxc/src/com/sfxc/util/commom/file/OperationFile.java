package com.sfxc.util.commom.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class OperationFile {
	public void delFile(String filePathAndName) {
	    try {
	      String filePath = filePathAndName;
	      filePath = filePath.toString();
	      java.io.File myDelFile = new java.io.File(filePath);
	      if (myDelFile.exists() && myDelFile.isFile()) {  
	         myDelFile.delete();
	      }

	    }
	    catch (Exception e) {
	      e.printStackTrace();

	    }

	}
	@SuppressWarnings({ "resource", "unused" })
	public void copyFile(String oldPath, String newPath) {
	    try {
	      int bytesum = 0;
	      int byteread = 0;
	      File oldfile = new File(oldPath);
	      if (oldfile.exists()) { //文件存在时
	        InputStream inStream = new FileInputStream(oldPath); //读入原文件
	        FileOutputStream fs = new FileOutputStream(newPath);
	        byte[] buffer = new byte[1444];
	        int length;
	        while ( (byteread = inStream.read(buffer)) != -1) {
	          bytesum += byteread; //字节数 文件大小
	          fs.write(buffer, 0, byteread);
	        }
	        inStream.close();
	      }
	    }
	    catch (Exception e) {
	      e.printStackTrace();

	    }

	} 
	// 复制文件夹
    public  void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
