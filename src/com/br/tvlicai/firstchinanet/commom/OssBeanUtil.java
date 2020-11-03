package com.br.tvlicai.firstchinanet.commom;

/**
 *
 * Created by 胡浩 on 2017/2/4.
 */
public class OssBeanUtil {
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	/**
	 * 根据内容类型判断文件类型
	 *
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileType(String contentType) {
		String fileType = "";
		if ("image/jpeg".equals(contentType))
			fileType = "IMAGE";
		else if ("image/bmp".equals(contentType))
			fileType = "IMAGE";
		else if ("image/gif".equals(contentType))
			fileType = "IMAGE";
		else  fileType = "FILE";
		return fileType;
	}
	/**
	 * 判断OSS服务文件上传时文件的contentType
	 * @param filenameExtension
	 * @return String
	 */
	public static String contentType(String filenameExtension){
		if(filenameExtension.equals("BMP")
				||filenameExtension.equals("bmp")){
			return "image/bmp";
		}
		if(filenameExtension.equals("GIF")
				||filenameExtension.equals("gif")){
			return "image/gif";
		}
		if(filenameExtension.equals("JPEG")
				||filenameExtension.equals("jpeg")
				||filenameExtension.equals("JPG")
				||filenameExtension.equals("jpg")
				||filenameExtension.equals("PNG")
				||filenameExtension.equals("png")){
			return "image/jpeg";
		}
		if(filenameExtension.equals("HTML")
				||filenameExtension.equals("html")){
			return "text/html";
		}
		if(filenameExtension.equals("TXT")
				||filenameExtension.equals("txt")){
			return "text/plain";
		}
		if(filenameExtension.equals("VSD")
				||filenameExtension.equals("vsd")){
			return "application/vnd.visio";
		}
		if(filenameExtension.equals("PPTX")
				||filenameExtension.equals("pptx")
				||filenameExtension.equals("PPT")
				||filenameExtension.equals("ppt")){
			return "application/vnd.ms-powerpoint";
		}
		if(filenameExtension.equals("DOCX")
				||filenameExtension.equals("docx")
				||filenameExtension.equals("DOC")
				||filenameExtension.equals("doc")){
			return "application/msword";
		}
		if(filenameExtension.equals("XML")
				||filenameExtension.equals("xml")){
			return "text/xml";
		}
		if(filenameExtension.equals("XLS")
				||filenameExtension.equals("XLSX")
				||filenameExtension.equals("xlsx")
				||filenameExtension.equals("xls")){
			return "application/x-xls";
		}
		if(filenameExtension.equals("PDF")
				||filenameExtension.equals("pdf")){
			return "application/pdf";
		}
		return "text/html";
	}
}
