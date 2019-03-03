package com.hanshow.jd.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/commonReq")
public class CommonReqController {
	static Logger logger = LoggerFactory.getLogger(CommonReqController.class);
	@RequestMapping(value = "/json")
	public void json(@RequestBody final JSONObject json, HttpServletResponse response){
		response.setContentType("text/json;charset=UTF-8");
		logger.info("json请求:"+json.toString());
		PrintWriter out = null;
		try {
			out = response.getWriter();//mobile
			out.write(json.toString());  
			out.flush();
			logger.info("json请求结束");
			return;
		}catch(Exception e){
			logger.error("获取求参数失败"+json,e);
		}
	}
	
}
