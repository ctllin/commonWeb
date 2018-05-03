package o2o.openplatform.sdk;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ctl.utils.DateUtil;
import net.sf.json.JSONObject;
import o2o.openplatform.sdk.dto.ReturnJson;
import o2o.openplatform.sdk.dto.WebRequestDTO;
import o2o.openplatform.sdk.util.HttpUtil;
import o2o.openplatform.sdk.util.SignUtils;

/**
 * SDK工具测试类
 * ClassName SDKTests </br>
 * 2016年8月4日 上午11:16:50 </br>
 * @author zhoudeming </br>
 * @version 1.0.0
 */
public class SDKTests  {
	public static void  main(String args[]) throws Exception {
		
		// 应用授权信息
		String appKey = "7fd1c34598924181b3ba295b41c63507";
		String appSecret = "a7182e7f06274e4ebcbb0c64213fcfa7";
		String token = "2f3da4db-a0d4-40a8-bf4e-22007b5603d5";
		String format = "json";
		String v = "1.0";
		String timestamp = DateUtil.sdfyyyy_MM_dd_HH_mm_ss.format(new Date());
		String jd_param_json = "{\"pageNo\":\"1\",\"pageSize\":\"20\",\"orderStartTime_begin\":\"2016-01-01 00:00:00\",\"orderStartTime_end\":\"2016-08-08 00:00:00\", \"orderStatus\":\"90000\"}";
		String url = "https://openo2o.jd.com/djapi/order/es/query";
		
		// 计算签名实体
		WebRequestDTO webReqeustDTO = new WebRequestDTO();
		webReqeustDTO.setApp_key(appKey);
		webReqeustDTO.setFormat(format);
		webReqeustDTO.setJd_param_json(jd_param_json);
		webReqeustDTO.setTimestamp(timestamp);
		webReqeustDTO.setToken(token);
		webReqeustDTO.setV(v);
		
		String sign = SignUtils.getSignByMD5(webReqeustDTO, appSecret);
		System.out.println("md5 sign:" + sign);	
		
		// 请求参数实体
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("token", token);
		param.put("app_key", appKey);
		param.put("timestamp", timestamp);
		param.put("sign", sign);
		param.put("format", format);
		param.put("v", v);		
		param.put("jd_param_json", jd_param_json);
		
		String result = HttpUtil.sendSimplePostRequest(url, param);
		ReturnJson returnJson=(ReturnJson) JSONObject.toBean(JSONObject.fromObject(result),ReturnJson.class);
		System.out.println("result:" + result);

	}
	
}
