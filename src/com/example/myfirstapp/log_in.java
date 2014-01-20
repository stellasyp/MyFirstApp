package com.example.myfirstapp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class log_in extends Activity{    //extends askPPassActivity{
	@SuppressLint("NewApi")
	WebView webView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    // Get the message from the intent
	    Intent intent = getIntent();
	    final String name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);	  //上一个界面的信息即用户名和密码传进来  
	    final String password = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
	    setContentView(R.layout.log_in);
	    
//	    ListView list = (ListView) getListView();  
//
//	    ArrayList<HashMap<String,String>> mylist = new ArrayList<HashMap<String,String>>();
//	    
//	    for(int i=0;i<30;i++)
//	    {
//	    	HashMap<String,String> map=new HashMap<String,String>();
//	    	//map.put("ItemImage", );
//	    	map.put("ItemTitle", name);  
//            map.put("ItemText", password); 
//	    	mylist.add(map);
//	    }
//	    
//	    SimpleAdapter mSchedule = new SimpleAdapter (this, mylist,R.layout.for_log_in,
//	    		new String[]{"ItemTitle","ItemText"},new int []{R.id.ItemTitle,R.id.ItemText});
//	    
//	    list.setAdapter(mSchedule); 
	     

	    
	    final TextView textView = (TextView) findViewById(R.id.textconnect);
	    final String temp = "http://jwas3.nju.edu.cn:8080/jiaowu/";//was3.nju.edu.cn:8080/jiaowu/";
	    
	    class connectp implements Runnable             //第一次尝试 用输入输出流
	    {
	    	
	    	public byte[] readStream(InputStream inputStream) throws Exception 
			{
				byte[] buffer = new byte[1024];
				int len = -1;
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	
				while ((len = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, len);
				}
	
				inputStream.close();
				byteArrayOutputStream.close();
				return byteArrayOutputStream.toByteArray();
			}
	    	
	    	public String testGetHtml(String urlpath) throws Exception 
			{
				
				URL url = new URL(urlpath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent","Mozilla/5.0(Windows;U;Window NT 5.1;zh-CN;rv:1.9.0.15)Gecko/2009101601Firefox/3.0.15(.NET CLR 3.5.30729)");
				conn.setConnectTimeout(6 * 1000);
				conn.setRequestMethod("GET");
				conn.connect();
//				InputStream inputStream = conn.getInputStream();
//				return "sdf";
				

				if (conn.getResponseCode() == 200) 
				{
//				return "something13";
					InputStream inputStream = conn.getInputStream();
					byte[] data = readStream(inputStream);
					String html = new String(data);
					return html;
				}
				else
				{
					String wrongString = "wrong";
					return wrongString;
				}
				
			}

			@Override
			public void run() {
				String dataString = null;
				try {
			    	dataString=testGetHtml(temp);
			    	 //temp = "hgfjghfj";
			    	// System.out.println("asdlkfhakj");
			    	} catch (Exception e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    	}

			    textView.setText(dataString);
			    // Set the text view as the activity layout
			    setContentView(textView);
				
			}
	    	
	    }
	    
	    class connectp2 implements Runnable      //第二次尝试
	    {
	    	public byte[] readStream(InputStream inputStream) throws Exception 
			{
				byte[] buffer = new byte[1024];
				int len = -1;
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	
				while ((len = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, len);
				}
	
				inputStream.close();
				byteArrayOutputStream.close();
				return byteArrayOutputStream.toByteArray();
			}
	    	@SuppressWarnings("null")
			public String testGetHtml(String urlpath) throws Exception 
			{
				
				URL url = new URL(urlpath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//				conn.setRequestProperty("User-Agent","Mozilla/5.0(Windows;U;Window NT 5.1;zh-CN;rv:1.9.0.15)Gecko/2009101601Firefox/3.0.15(.NET CLR 3.5.30729)");
//				conn.setConnectTimeout(6 * 1000);
//				conn.setRequestMethod("POST");
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connectionn","keep-Alive");
				conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible;MSIE 6.0; Window NT 5.1;SV1)");
				conn.setRequestProperty("Cookie", "cookiename1=cookievalue1; cookiename2=cookiename2");
				conn.setDoOutput(true);
				conn.setDoInput(true);
			
				OutputStream outputStream = conn.getOutputStream();
				PrintWriter out = new PrintWriter(outputStream);
				out.print("name="+ URLEncoder.encode("111220104", "UTF-8")+"&pass=" + URLEncoder.encode("199382", "UTF-8"));//"name ="+name +"&pass="+password);
				out.flush();	
				InputStream inputStream=conn.getInputStream();
			//	Log.d("stella","here");
				byte[] data = readStream(inputStream);
				if(data==null)
					return "nothing";
				else
					return data.toString();
			}

			@SuppressLint("SetJavaScriptEnabled")
			@Override
			public void run() {
				String dataString = null;
				try {
			    	dataString=testGetHtml(temp);
			    	} catch (Exception e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    	}
//				WebView webView = (WebView) findViewById(R.id.webview01);
//				
//				//webView.setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
//				webView.loadUrl("http://www.baidu.com/");
//				webView.getSettings().setJavaScriptEnabled(true);  
//				webView.requestFocus();
			    textView.setText(dataString);
			    setContentView(textView);
				
			}
	    	
	    }
	    
	    class connectp3 extends Activity  implements Runnable //第三次尝试
	    {

			@Override
			public void run() {
				
				 String requestUrl = "http://jwas3.nju.edu.cn:8080/jiaowu/";  
		         // 创建HttpClient实例  
		         HttpClient client = new DefaultHttpClient();  
		         // 根据URL创建HttpPost实例  
		         HttpPost post = new HttpPost(requestUrl);  
		   
		         ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		         // 设置需要传递的参数  
		         params.add(new BasicNameValuePair("userName",name));
		         params.add(new BasicNameValuePair("password", password));
		         try {  
		             // 设置URL编码  
		             post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
		             // 发送请求并获取反馈  
		             HttpResponse response = client.execute(post);  
		             Log.d("text","here");    
		             // 判断请求是否成功处理  
		             if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
		                 // 解析返回的内容  
		                 String result = EntityUtils.toString(response.getEntity());     //结果是原网页源码 没有看到参数传过去的作用？？？、
		                 TextView textView = new TextView(this);
		                 textView.setText(result);
		                 setContentView(textView);
		               
		                 
		                 List<Cookie> cookies = ((AbstractHttpClient) client).getCookieStore().getCookies();  
		                 if (cookies.isEmpty())
		                 {  
		                	 Log.i("TAG none", "-------Cookie NONE---------");  
		                 } 
		                 else 
		                 {                 
		                	 for (int i = 0; i < cookies.size(); i++ )
		                	 {  
			                 //保存cookie  
			                 Cookie cookie = cookies.get(i);  
			                 Log.d("hi", cookies.get(i).getName()+"="+cookies.get(i).getValue() );  
		                	 }
		                 }
		                 HttpPost httpPost = new HttpPost(requestUrl);
		                 httpPost.setHeader("Cookie", "JSESSIONID=" + cookies.get(0).getValue());
		                 HttpResponse httpResponse = client.execute(httpPost);
//		                 HttpGet request = new HttpGet(requestUrl+"?"+ params);
//		         		 request.setHeader("Cookie",cookies.get(0).getValue());
		                 
		             } 
		            
		          
		         	
		         	
		         } catch (Exception e) {  
		             e.printStackTrace();  
		         }  
				
			}
	    	
	    }
//	    Runnable connectpnjuRunnable5 = new connectp3();
//	    Thread connThread = new Thread(connectpnjuRunnable5);
//	    connThread.start();
	   
	    WebView webView = (WebView) findViewById(R.id.webview01);         //这个是模拟浏览器 可以将页面登陆
		webView.loadUrl( temp );
		webView.getTextDirection();
	    
		//Log.d("text",webView.getTextDirection());
	}
	
	

		
		
	}



