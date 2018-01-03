package com.cigna.cmc.checkin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class CheckInSender {
	public static void sendCheckIn() throws Exception {

		CookieStore cookieStore = new BasicCookieStore();
		// Cookies Sepc
		RequestConfig globalConfig = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.BEST_MATCH).build();
		RequestConfig localConfig = RequestConfig.copy(globalConfig)
				.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();

		// Http Context
		HttpClientContext context = HttpClientContext.create();
		context.setRequestConfig(localConfig);
		context.setCookieStore(cookieStore);

		// Http Client
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();

		// step1 post
		// HttpPost httpPost = new HttpPost("http://vmprncs02:8020/User/Login");
		HttpPost httpPost = new HttpPost(
				"http://attendance.cignacmb.com/login.aspx?action=login");
		
		// send check in
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user", SystemConfigUtils
				.getValue(Constants.USER_NAME)));
		// nvps.add(new BasicNameValuePair("SavePwd", "false"));
		nvps.add(new BasicNameValuePair("password", SystemConfigUtils
				.getValue(Constants.USER_PASSWORD)));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpclient.execute(httpPost, context);
		try {
			System.out.println(response.getStatusLine());
			System.out.println("<Content> ==>");
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
			System.out.println("<Cookies> ==>");
			Iterator<Cookie> cookieIter = cookieStore.getCookies().iterator();
			while (cookieIter.hasNext()) {
				Cookie cookieElement = (Cookie) cookieIter.next();
				System.out.println(cookieElement.getName() + "  :  "
						+ cookieElement.getValue());
			}
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}

		// step2 get
		// 可能要跳转才能成功
		// HttpGet httpGet = new HttpGet("http://vmprncs02:8020/");
		
		HttpGet httpGet = new HttpGet("http://attendance.cignacmb.com/user/");

		CloseableHttpResponse response2 = httpclient.execute(httpGet, context);
		try {
			System.out.println(response2.getStatusLine());
			System.out.println("<Content> ==>");
			HttpEntity entity = response2.getEntity();
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		} finally {
			response2.close();
		}

		// step3 get
		// check in

		HttpGet httpGet3 = new HttpGet(
				"http://attendance.cignacmb.com/user/action.aspx?action=UserPost&Type=1&_="
						+ System.currentTimeMillis());
		CloseableHttpResponse response3 = httpclient.execute(httpGet3, context);
		try {
			System.out.println(response3.getStatusLine());
			System.out.println("<Content> ==>");
			HttpEntity entity = response3.getEntity();
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		} finally {
			response3.close();
		}

		httpclient.close();
	}

	public static void sendCheckOut() throws Exception {
		CookieStore cookieStore = new BasicCookieStore();
		// Cookies Sepc
		RequestConfig globalConfig = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.BEST_MATCH).build();
		RequestConfig localConfig = RequestConfig.copy(globalConfig)
				.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();

		// Http Context
		HttpClientContext context = HttpClientContext.create();
		context.setRequestConfig(localConfig);
		context.setCookieStore(cookieStore);

		// Http Client
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();

		// step1 post
		// HttpPost httpPost = new HttpPost("http://vmprncs02:8020/User/Login");
		HttpPost httpPost = new HttpPost(
				"http://attendance.cignacmb.com/login.aspx?action=login");

		// send check in
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user", SystemConfigUtils
				.getValue(Constants.USER_NAME)));
		// nvps.add(new BasicNameValuePair("SavePwd", "false"));
		nvps.add(new BasicNameValuePair("password", SystemConfigUtils
				.getValue(Constants.USER_PASSWORD)));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpclient.execute(httpPost, context);
		try {
			System.out.println(response.getStatusLine());
			System.out.println("<Content> ==>");
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
			System.out.println("<Cookies> ==>");
			Iterator<Cookie> cookieIter = cookieStore.getCookies().iterator();
			while (cookieIter.hasNext()) {
				Cookie cookieElement = (Cookie) cookieIter.next();
				System.out.println(cookieElement.getName() + "  :  "
						+ cookieElement.getValue());
			}
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}

		// step2 get
		// 可能要跳转才能成功
		// HttpGet httpGet = new HttpGet("http://vmprncs02:8020/");
		HttpGet httpGet = new HttpGet("http://attendance.cignacmb.com/user/");

		CloseableHttpResponse response2 = httpclient.execute(httpGet, context);
		try {
			System.out.println(response2.getStatusLine());
			System.out.println("<Content> ==>");
			HttpEntity entity = response2.getEntity();
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		} finally {
			response2.close();
		}

		// step3 get
		// check out
		HttpGet httpGet3 = new HttpGet(
				"http://attendance.cignacmb.com/user/action.aspx?action=UserPost&Type=2&_="
						+ System.currentTimeMillis());
		CloseableHttpResponse response3 = httpclient.execute(httpGet3, context);
		
			
		try {
			System.out.println(response3.getStatusLine());
			System.out.println("<Content> ==>");
			HttpEntity entity = response3.getEntity();
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		} finally {
			response3.close();
		}

		httpclient.close();
	}

	public static void main(String[] args) throws Exception {
		SystemConfigUtils.load();
		CheckInSender.sendCheckIn();
	}
}
