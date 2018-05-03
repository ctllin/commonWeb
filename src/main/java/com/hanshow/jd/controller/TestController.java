package com.hanshow.jd.controller;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: TestController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-05-03 10:55
 */
@RestController
@RequestMapping("/test")
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    //@RequestMapping(value = "/jsonData", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @RequestMapping(value = "/jsonData", consumes = "application/json;charset=utf-8")
    public @ResponseBody Map<String,Object> jsonData(String token, HttpServletRequest request) {
        Map<String ,Object> map=new HashMap<>();
        logger.info(token);
        //{"code":"0","msg":"success","data":""}
        map.put("code","200");
        map.put("msg","success");
        map.put("data","测试成功");
        return map;
    }
//    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    @RequestMapping(value = "/postData")
    public @ResponseBody Map<String,Object> postData(String token, HttpServletRequest request) {
        Map<String ,Object> map=new HashMap<>();
        logger.info(token);
        //{"code":"0","msg":"success","data":""}
        map.put("code","200");
        map.put("msg","success");
        map.put("data","测试成功");
        return map;
    }
    @RequestMapping(value = "/postData2")
    public void postData2(String token, HttpServletRequest request, HttpServletResponse response) {
        Map<String ,Object> map=new HashMap<>();
        logger.info(token);
        //{"code":"0","msg":"success","data":""}
        map.put("code","200");
        map.put("msg","success");
        map.put("data","测试成功");
        try {
            response.getWriter().write(JSONObject.fromObject(map).toString());
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
