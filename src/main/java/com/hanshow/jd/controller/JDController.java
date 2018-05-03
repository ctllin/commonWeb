package com.hanshow.jd.controller;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: JDController</p>
 * <p>Description: 京东回调测试接口</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-05-03 14:09
 */
@RestController
@RequestMapping("/jd")
public class JDController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value = "/jdCallback")
    public void jdCallback(String token, HttpServletRequest request, HttpServletResponse response) {
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
           logger.error("京东回调接口调用失败",e);
        }
    }
}
