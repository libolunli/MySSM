package com.myproject.web.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.alibaba.fastjson.JSONObject;
import com.myproject.datasource.domain.SaCodeName;
import com.myproject.datasource.domain.SaMainAccordion;
import com.myproject.datasource.domain.SaUserInfo;
import com.myproject.pub.util.CacheUtil;
import com.myproject.pub.util.ListUtil;
import com.myproject.web.Constant.WebConstant;
import com.myproject.web.login.service.UserService;
import com.myproject.web.login.util.MD5Util;


@Controller
@RequestMapping(value = "user")
public class UserController {

	@Resource
	private UserService userService;
	
	private static final Logger log = Logger.getLogger(UserController.class);// 日志文件

	@RequestMapping(value = "/test.do",method=RequestMethod.GET)
	public String test(ModelMap map,HttpServletRequest request) {
		Map<String, String> map_test = new HashMap<String, String>();

		map.put("1", "abc");

		map.put("2", "efg");
		JSONObject jsonmap = new JSONObject();
		jsonmap.put("1", "abc");
		jsonmap.put("2", "efg");
		JSONObject jsonObject = new JSONObject();
		//jsonObject.put(map);
		request.setAttribute("errorMsg", "请认真核对账号、密码！");
		return "forward:../module/main/jsp/test.jsp";
	}

	/**
	 * 登录校验
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login.do")
	public String login(SaUserInfo user, HttpServletRequest request) throws Exception {
		try {
			user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
			SaUserInfo resultUser = userService.loginCache(user.getuserName(), user.getPassword());
			log.info("request: user/login , user: " + user.toString());
			if (resultUser == null) {
				request.setAttribute("user", user);
				request.setAttribute("errorMsg", "请认真核对账号、密码！");
				System.out.println("test:"+request.getAttribute("errorMsg"));
				return "forward:..";
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", resultUser);
				MDC.put("userName", resultUser.getuserName());
				return "redirect:../module/main/jsp/main.jsp";
			}
		} catch (Exception e) {
			log.error("error:"+e.getMessage());
			throw new Exception(e.getMessage()); 
		}
	}
	
	/**
	 * 获取主页配置网址
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/geturl.do",method=RequestMethod.GET)
	public String getUrl(ModelMap map,HttpServletRequest request) {
		
		List<SaCodeName> urlList = CacheUtil.getSaCodeName(WebConstant.WEB_URL_TYPE);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("urlList", urlList);
		return jsonObject.toString();
	}
	/**
	 * 获取主页tab
	 * @param map
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTab.do",method=RequestMethod.GET)
	public String getAccordionTab(ModelMap map,HttpServletRequest request){
		List<SaMainAccordion> mainAccordionList = CacheUtil.getMainAccordion("Admin");
		List<String> tabList = getAllAccordionInfo(mainAccordionList);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tabList", tabList);
		return jsonObject.toString();
	}
	
	private List<String> getAllAccordionInfo(List<SaMainAccordion> saMainAccordionList){
		Map<String , String> map = new HashMap<String , String>();
		String tab = "";
		
		if(ListUtil.isEmpty(saMainAccordionList)){
			return null;
		}
		for(SaMainAccordion saMainAccordion : saMainAccordionList){
			map.put(saMainAccordion.getParent(), "");
		}
		//獲取所有ID集合進行篩選
		Set<String> idSet = map.keySet();
		
		for(SaMainAccordion saMainAccordion : saMainAccordionList){
			if(idSet.contains(saMainAccordion.getParent())||"0".equals(saMainAccordion.getParent())){
				tab = map.get(saMainAccordion.getId().toString());
				tab = map.get(saMainAccordion.getId().toString());
				tab += "<div title=\""
						+saMainAccordion.getAccordionname()
						+"\" data-options=\"selected:true,iconCls:'"
						+saMainAccordion.getIconcls()
						+"'\" style=\"padding: 10px; height: 10px;\"></div>";
				map.put(saMainAccordion.getId().toString(), tab	);
			}else{
				tab = map.get(saMainAccordion.getParent());
				tab = tab.substring(0, tab.length()-6);
				tab += "<a href=\"javascript:openTab('"
						+saMainAccordion.getAccordionname()+"','"+saMainAccordion.getUrl()
						+"?type=1&grade=1','"
						+saMainAccordion.getIconcls()
						+"')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"
						+saMainAccordion.getIconcls()
						+"'\" style=\"width: 150px;\"> "
						+saMainAccordion.getAccordionname()
						+"</a></div>";
				map.put(saMainAccordion.getParent(), tab);
			}
		}
		return map2List(map);
	}
	
	private List<String> map2List(Map<String,String> map){
		List<String> strList = new ArrayList<>();
		Set<String> idSet = map.keySet();
		Iterator<String> item = idSet.iterator();
		while(item.next() != null){
			strList.add(map.get(item));
		}
		return strList;
	}
}
