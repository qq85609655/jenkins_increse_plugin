/**
 * RecFileSys FTPClientUtil.java
 * 
 * @Author Allen.Z
 * @Date 2011-11-1
 * @Email allenzou86@gmail.com Desc ftp 工具类 通过FTP的方式上传或下载文件
 */
package com.unicompayment.fip.common.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FTPClientUtil{
	private static Logger logger = LoggerFactory.getLogger(FTPClientUtil.class);
	
	private static FTPClient ftpClient = new FTPClient();
    private static String encoding = System.getProperty("file.encoding");
    
	public static boolean downloadFile(String hostName,int port,String userName,String password,String remotefilename,String localfilename) throws Exception{
		int reply;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostName, port);
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				throw new Exception("ftp连接失败,请检查地址和端口");
			}
		} catch (ConnectException e) {
			throw new Exception("ftp连接失败,请检查地址和端口");
		} catch (Exception e) {
			logger.error("---->>登录异常", e);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception f) {
					return false;
				}
			}
			throw new Exception("ftp连接失败,请检查地址和端口");
		}
		try {
			if (!ftpClient.login(userName, password)) {
				ftpClient.logout();
				throw new Exception("ftp登陆失败,请检查用户名和口令");
			}
			ftpClient.setSoTimeout(30000);//设置30秒超时
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			File desFile = new File(localfilename);
			String path = localfilename.substring(0, localfilename.lastIndexOf('/'));
			File dirFile = new File(path);
			if (!(dirFile.exists()) || !(dirFile.isDirectory())) {
				boolean creadok = dirFile.mkdirs();
				if (creadok) {
					logger.info("文件存放目录不在在或者不是目录,创建路径成功!");
				} else {
					logger.error("文件存放目录不在在或者不是目录,创建路径失败!");
					throw new Exception("FTP下载失败,文件存放路径不在在或者不是目录,且创建路径失败!");
				}
			}
			FileOutputStream out = new FileOutputStream(desFile);
			// 遍历出登录后目录下得所有文件
			logger.info("------->>FTP下载文件【"+remotefilename+"】");
			boolean isSuccess = ftpClient.retrieveFile(remotefilename, out);
			out.close();
			ftpClient.logout();
			if (!isSuccess) {
				logger.info("------->>FTP下载失败,文件可能不存在或者其他原因!");
				throw new Exception("FTP下载失败,文件可能不存在或者其他原因");
			}
			return isSuccess;
		} catch (FTPConnectionClosedException e) {
			logger.error("---->>FTP连接关闭异常", e);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
			return false;
		} catch (Exception ex) {
			logger.error("---->>FTP下载异常", ex);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception f) {
					return false;
				}
			}
			return false;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
		}
	}
	/**
	 * 复制单个文件
	 * 
	 * @param oldPath String 原文件路径 如：c:/fqf.txt
	 * @param newPath String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath,String newPath,String accountDate){
		logger.info("开始复制对账文件");
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					logger.debug(bytesum+"");
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			logger.error("复制单个文件操作出错");
			e.printStackTrace();
		}
	}
	
	/**
     * Description: 向FTP服务器上传文件
     *
     * @Version1.0
     * @param host
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录,如果是根目录则为“/”
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param input
     *            本地文件输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username,
            String password, String path, String filename, InputStream input) {
        boolean result = false;
 
        try {
            int reply;
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            //ftpClient.connect(url);
            ftpClient.connect(host, port);// 连接FTP服务器
            // 登录
            ftpClient.login(username, password);
            ftpClient.setControlEncoding(encoding);
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                logger.error("连接失败");
                ftpClient.disconnect();
                return result;
            }
 
            // 转移工作目录至指定目录下
            boolean change = ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (change) {
                result = ftpClient.storeFile(new String(filename.getBytes(encoding),"utf-8"), input);
                if (result) {
                    logger.info("上传成功!");
                }
            }
            input.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }
 
    /**
     * 将本地文件上传到FTP服务器上
     *
     */
    public void testUpLoadFromDisk() {
        try {
            FileInputStream in = new FileInputStream(new File("E:/号码.txt"));
            boolean flag = uploadFile("127.0.0.1", 21, "zlb","123", "/", "哈哈.txt", in);
            logger.debug(flag+"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args){
		try {
			System.out.println(FTPClientUtil.downloadFile("reportftp.yeepay.com", 21, "trx10011674237", "eeb38a38", "20120604.csv", "D:/sd.csv"));
			// System.out.println(FTPClientUtil.downloadFile("192.168.3.247", 21, "tp01026", "tp01026", "pos/01026_01_20120521.txt", "D:/abc.txt"));
			// System.out.println(FTPClientUtil.downloadFile("123.125.97.241", 21, "unicompay", "unicompay", "jfb/abc.txt", "D:/111.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
