package com.myproject.pub.cache.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.datasource.domain.SaCodeName;
import com.myproject.datasource.domain.SaUserInfo;
import com.myproject.pub.cache.po.CacheConfigManager;
import com.myproject.pub.util.CacheUtil;

public class CacheServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*List saCodeName = CacheConfigManager.getByIndex("SA_CODE_NAME", "CODETYPE", List.class, "MAIN_URL_TYPE");
		//Map saUserInfo = CacheConfigManager.getByIndex("SA_USER_INFO", "ACCOUNT,PASSWORD", Map.class, "admin","21232f297a57a5a743894a0e4a801fc3");
		SaUserInfo saUserInfo2 = CacheConfigManager.getByIndex("SA_USER_INFO", "ACCOUNT,PASSWORD", SaUserInfo.class, "admin","21232f297a57a5a743894a0e4a801fc3");
		List<SaUserInfo> saUserInfoList = CacheConfigManager.getByIndex("SA_USER_INFO", "ACCOUNT", List.class, "admin");
		response.getWriter().append("----------------------------------------------------\n");
		response.getWriter().append(saCodeName.toString());
		response.getWriter().append("\n----------------------------------------------------\n");
		//response.getWriter().append(saUserInfo.toString());
		response.getWriter().append("\n----------------------------------------------------\n");
		response.getWriter().append(saUserInfo2.toString());
		response.getWriter().append("\n----------------------------------------------------\n");
		response.getWriter().append(saUserInfoList.toString());
		response.getWriter().append("\n----------------------------------------------------\n");*/
		List<SaCodeName> saCodeNameList = CacheUtil.getSaCodeName("MAIN_URL_TYPE");
		response.getWriter().append("\n----------------------------------------------------\n");
		response.getWriter().append(saCodeNameList.toString());
		response.getWriter().append("\n----------------------------------------------------\n");

	}
}
