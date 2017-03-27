package com.luolei.jdk.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author luolei
 * @Date 2015年12月25日
 * @Time 下午10:08:35
 */
public class FileUtil {

	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 
	 * @param pathname
	 * @returns
	 */
	public static String read(String pathname) {
		return read(pathname, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String read(File file) {
		return read(file, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * @param pathname
	 * @param charSet
	 * @return
	 */
	public static String read(String pathname, String charSet) {
		File file = new File(pathname);
		return read(file, charSet);
	}

	/**
	 * 
	 * @param file
	 * @param charSet
	 * @return
	 */
	public static String read(File file, String charSet) {
		if (!file.exists() || file.isDirectory())
			return "";
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					file), charSet));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		result.deleteCharAt(result.length()-1);
		return result.toString();
	}

	/**
	 * 
	 * @param pathname
	 * @return
	 */
	public static String readByByte(String pathname) {
		File file = new File(pathname);
		return readByByte(file);
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String readByByte(File file) {
		if (!file.exists() || file.isDirectory())
			return "";
		BufferedInputStream in = null;
		StringBuffer result = new StringBuffer();
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				String temp = new String(buffer, 0, len);
				result.append(temp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result.toString();
	}
	
	/**
	 * 
	 * @param filename
	 * @param data
	 */
	public static void write(String pathname,String data){
		write(pathname, data, DEFAULT_CHARSET, false);
	}
	
	/**
	 * 
	 * @param pathname
	 * @param data
	 * @param charSet
	 */
	public static void write(String pathname, String data, String charSet) {
		write(pathname, data, charSet, false);
	}
	
	/**
	 * 
	 * @param pathname
	 * @param data
	 * @param append
	 */
	public static void write(String pathname, String data, boolean append) {
		write(pathname, data, DEFAULT_CHARSET, append);
	}
	
	/**
	 * 
	 * @param pathname	路径名
	 * @param data	写入的数据
	 * @param charSet	写入用的字符集
	 * @param append	覆盖 还是在结尾添加
	 */
	public static void write(String pathname, String data, String charSet, boolean append){
		File file = new File(pathname);
		write(file, data, charSet, append);
	}
	
	/**
	 * 
	 * @param file
	 * @param data
	 * @param charSet
	 * @param append
	 */
	public static void write(File file, String data, String charSet, boolean append){
		BufferedWriter out = null;
		try {
			if(file.isDirectory())
				return;
			if(!file.exists())
				file.createNewFile();

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, append), charSet));
			out.write(data);

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 
	 * @param file
	 * @param data
	 * @param append
	 */
	public static void write(File file, String data, boolean append){
		write(file, data, DEFAULT_CHARSET, append);
	}
	
	/**
	 * 
	 * @param file
	 * @param data
	 * @param charSet
	 */
	public static void write(File file, String data, String charSet){
		write(file, data, charSet, false);
	}
	
	/**
	 * 
	 * @param file
	 * @param data
	 */
	public static void write(File file, String data){
		write(file, data, DEFAULT_CHARSET, false);
	}
	
	/**
	 * 
	 * @param pathname
	 * @param data
	 * @param append
	 */
	public static void writeByte(String pathname,byte[] data,boolean append){
		File file = new File(pathname);
		writeByte(file, data, append);
	}
	
	/**
	 * 
	 * @param file
	 * @param data
	 * @param append
	 */
	public static void writeByte(File file,byte[] data,boolean append){
		BufferedOutputStream out = null;
		try{
			if(file.isDirectory())
				return;
			if(!file.exists())
				file.createNewFile();
			out = new BufferedOutputStream(new FileOutputStream(file, append));
			out.write(data);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 
	 * @param pathname
	 * @param data
	 */
	public static void writeByte(String pathname,byte[] data){
		File file = new File(pathname);
		writeByte(file, data, false);
	}
	
	/**
	 * 
	 * @param file
	 * @param data
	 */
	public static void writeByte(File file,byte[] data){
		writeByte(file, data, false);
	}
	
	/**
	 * 复制文件
	 * @param filename	要复制的文件
	 * @param pathname	复制到哪个目录下，文件名为  原文件名+副本
	 */
	public static void copy(String filename,String pathname){
		File sourceFile = new File(filename);
		if(sourceFile.isDirectory()||!sourceFile.exists())
			return;
		File tar = new File(pathname);
		if(tar.isFile()||!tar.exists())
			return;
		
		String sourceFileName = sourceFile.getName();
		int point = sourceFileName.lastIndexOf('.');
		String name = sourceFileName.substring(0, point);
		String type = sourceFileName.substring(point+1);
		String target = null;
		if(pathname.endsWith("/"))
			target = pathname+name+"副本."+type;
		else
			target = pathname+"/"+name+"副本."+type;
		System.out.println(target);
		
		File targetFile = new File(target);
		BufferedInputStream fis = null;
		BufferedOutputStream fos = null;
		try{
			fis = new BufferedInputStream(new FileInputStream(sourceFile));
			fos = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer))!=-1){
				fos.write(buffer, 0, len);
			}
		}catch(IOException e){
			System.out.println("复制失败");
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null)
					fis.close();
				if(fos!=null)
					fos.close();
			}catch(IOException e){
				System.out.println("关闭流失败");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件复制
	 * @param filename	要复制的文件
	 * @param pathname	目标目录
	 * @param yourname	自己定义的名字，不要带上目录名 和 后缀
	 */
	public static void copy(String filename,String pathname,String yourname){
		File sourceFile = new File(filename);
		if(sourceFile.isDirectory()||!sourceFile.exists())
			return;
		File tar = new File(pathname);
		if(tar.isFile()||!tar.exists())
			return;
		
		String sourceFileName = sourceFile.getName();
		int point = sourceFileName.lastIndexOf('.');
//		String name = sourceFileName.substring(0, point);
		String type = sourceFileName.substring(point+1);
		String target = null;
		if(pathname.endsWith("/"))
			target = pathname+yourname+"."+type;
		else
			target = pathname+yourname+"."+type;
		System.out.println(target);
		
		File targetFile = new File(target);
		BufferedInputStream fis = null;
		BufferedOutputStream fos = null;
		try{
			fis = new BufferedInputStream(new FileInputStream(sourceFile));
			fos = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer))!=-1){
				fos.write(buffer, 0, len);
			}
		}catch(IOException e){
			System.out.println("复制失败");
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null)
					fis.close();
				if(fos!=null)
					fos.close();
			}catch(IOException e){
				System.out.println("关闭流失败");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param pathname
	 */
	public static void deleteFile(String pathname){
		File file = new File(pathname);
		if(file.isDirectory())
			return;
		if(file.exists())
			file.deleteOnExit();
	}
}

