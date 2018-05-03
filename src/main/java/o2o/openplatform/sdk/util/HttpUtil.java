package o2o.openplatform.sdk.util;  
      
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreConnectionPNames;
      
/**
 * HTTP工具类
 * ClassName HttpUtil </br>
 * 2015年4月27日 下午7:15:59 </br>
 * @author zhoudeming </br>
 * @version 1.0.0
 */
public class HttpUtil {

	/**
	 * 默认编码为UTF-8
	 */
	private static final String HTTP_CONTENT_CHARSET = "UTF-8";


	public static final Integer MAX_TIME_OUT = 10000;
	public static final Integer MAX_IDLE_TIME_OUT = 20000;
	public static final Long MAX_MANAGER_TIME = 1000l;
	public static final Integer MAX_CONN = 100;
	
	public static final HttpClient httpClient = new HttpClient(); 

	static {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.closeIdleConnections(MAX_IDLE_TIME_OUT);
		connectionManager.getParams().setParameter(HttpConnectionManagerParams.MAX_TOTAL_CONNECTIONS, MAX_CONN);
		httpClient.setHttpConnectionManager(connectionManager);
		httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, MAX_TIME_OUT);
		httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, MAX_TIME_OUT);
		httpClient.getParams().setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, MAX_MANAGER_TIME);
	}

	/**
	 * 发送HTTP请求
	 * sendSimplePostRequest </br>
	 * @param url
	 * @param param
	 * @return </br>
	 * String
	 */
	public static String sendSimplePostRequest(String url, Map<String, Object> param) {
		String result=null;
		PostMethod post = new PostMethod(url);
		try {
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HTTP_CONTENT_CHARSET);
			if (param != null) {
				for (Entry<String, Object> entry : param.entrySet()) {
					if (entry.getValue() != null) {
						post.addParameter(entry.getKey(), (String)entry.getValue());
					}
				}
			}
			post.addRequestHeader(new Header("Connection", "close"));
			httpClient.executeMethod(post);
			if (post.getStatusCode() == HttpStatus.SC_OK) {
				return post.getResponseBodyAsString();
			} else {
				result = "{\"code\":\"" + post.getStatusCode() + "\", \"msg\":\"" + post.getStatusText() + "\"}";
				post.abort();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{\"code\":\"-1\", \"msg\":\"服务开小差，系统努力修复中\"}";
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		return result;
	}
	
	/**
	 * 发送HTTP请求
	 * sendSimpleGetRequest </br>
	 * @param url
	 * @param param
	 * @return </br>
	 * String
	 */
	public static String sendSimpleGetRequest(String url, Map<String, Object> param) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		GetMethod get = null;
		try {
			String paramStr = "";
			if (param != null) {
				for (Entry<String, Object> entry : param.entrySet()) {
					if (entry.getValue() != null) {
						sb.append(entry.getKey()).append("=").append(URLEncoder.encode((String)entry.getValue(), "utf-8")).append("&");
					}
				}
				paramStr = sb.toString();
				paramStr = paramStr.substring(0,paramStr.length()-1);
			}
			get = new GetMethod(url + "?" + paramStr);
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, HTTP_CONTENT_CHARSET);
			get.addRequestHeader(new Header("Connection", "close"));
			httpClient.executeMethod(get);
			if (get.getStatusCode() == HttpStatus.SC_OK) {
				return get.getResponseBodyAsString();
			} else {
				get.abort();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{\"code\":\"-1\", \"msg\":\"服务开小差，系统努力修复中\"}";
		} finally {
			if (get != null) {
				get.releaseConnection();
			}
		}
		return result;
	}
		
}  