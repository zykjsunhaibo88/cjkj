package com.neusoft.unieap.ria33demo.action.form;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.unieap.action.EAPDispatchAction;
import com.neusoft.unieap.util.DBAccessHelper;

public class Upload_DownLoad_File extends EAPDispatchAction {

	public ActionForward commonDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		InputStream in = null;
		DBPersistenceManager pm=null;
        Connection con = null;
 	    PreparedStatement ps = null;
 	    
		OutputStream out = response.getOutputStream();
		try {
			String name = request.getParameter("filename");
			String downname = null;
			String fileName = getUploadPath(request) + name;
			File file = new File(fileName);
			pm = DBAccessHelper.getPMByName(Const.DataSourceName);
			
			 con = pm.getConnection();
             String sql = "select ATTACHMENT_NAME,ATTACHMENT_DATA from brb_attachment_mg_t where ATTACHMENT_ID='"+name+"'";
             ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
            	    downname = rs.getString(1);
                    Blob files = rs.getBlob(2);//得到Blob对象
                    //开始读入文件
                    in = files.getBinaryStream();
                  
             }
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;"+ "filename=" +  java.net.URLEncoder.encode(downname, "UTF-8").replaceAll("\\+", "%20"));

			byte[] bb = new byte[1024*10];
			int a = -1;
	
			while ((a = in.read(bb)) != -1) {
				out.write(bb, 0, a);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			if(con!=null){
        		try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}

        	if(ps!=null){
        		try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
			try{
				in.close();
			}catch(IOException ex){}
			try{
				out.close();
			}catch(IOException ex){}
		}
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("filename");
		DBPersistenceManager pm = null;
		try {
			pm = DBAccessHelper.getPMByName(Const.DataSourceName);
		    pm.executeUpdate(" delete from from brb_attachment_mg_t where ATTACHMENT_ID='"+name+"' ");
		
		} catch (Exception e) {
			
		} finally {
			if (pm != null) {
				pm.close();
			}
		}
		return mapping.findForward("success");
	}

	private String getUploadPath(HttpServletRequest request) {
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = null;
		if (contextPath.endsWith("\\")) {
			uploadPath = contextPath 	+ "/page/export/upload/";
		} else {
			uploadPath = contextPath + "\\" + "/page/export/upload/";
		}
		File file = new File(uploadPath);
		if(!file.exists()){
			file.mkdir();
		}
		return uploadPath;
	}

}
