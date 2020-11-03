package com.br.tvlicai.firstchinanet.business.service.IpRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.br.tvlicai.firstchinanet.commom.Const.Const;
import com.br.tvlicai.firstchinanet.web.controller.user.UserCenterController;
import com.neusoft.drm.DBPersistenceManager;
import com.neusoft.unieap.util.DBAccessHelper;

public class IpRecordService {

	static final Logger _LOG = LoggerFactory.getLogger(IpRecordService.class);
	
	public int ipRecord(String ip) {
		int result = 0;
		DBPersistenceManager pm=null;
		try {
			pm=DBAccessHelper.getPMByName("LINGKE");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sql = "insert into ip_record (ip,time) values ('"+ip+"','"+sdf.format(new Date())+"')";
			result = pm.executeUpdate(sql);
		}catch(Exception e) {
			_LOG.debug("上传IP出现异常：{}",e.getMessage());
			return 0;
		}
		return result;
	}
}
