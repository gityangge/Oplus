package cn.ac.yangge.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostHelper {
	   public static String post(String strURL, String params) {   
	        try {  
	            URL url = new URL(strURL);// ��������  
	            HttpURLConnection connection = (HttpURLConnection) url  
	                    .openConnection();  
	            connection.setDoOutput(true);  
	            connection.setDoInput(true);  
	            connection.setUseCaches(false);  
	            connection.setInstanceFollowRedirects(true);  
	            connection.setRequestMethod("POST"); // ��������ʽ  
	            connection.setRequestProperty("Accept", "application/json"); // ���ý������ݵĸ�ʽ  
	            connection.setRequestProperty("Content-Type", "application/json"); // ���÷������ݵĸ�ʽ  
	            connection.connect();  
	            OutputStreamWriter out = new OutputStreamWriter(  
	                    connection.getOutputStream(), "UTF-8"); // utf-8����  
	            out.append(params);  
	            out.flush();  
	            out.close();  
	            // ��ȡ��Ӧ  
	            int length = (int) connection.getContentLength();// ��ȡ����  
	            InputStream is = connection.getInputStream();  
	            if (length != -1) {  
	                byte[] data = new byte[length];  
	                byte[] temp = new byte[512];  
	                int readLen = 0;  
	                int destPos = 0;  
	                while ((readLen = is.read(temp)) > 0) {  
	                    System.arraycopy(temp, 0, data, destPos, readLen);  
	                    destPos += readLen;  
	                }  
	                String result = new String(data, "UTF-8"); // utf-8����  
	                System.out.println(result);  
	                return result;  
	            }  
	        } catch (IOException e) {  	             
	            e.printStackTrace();  
	        }  
	        return "error"; // �Զ��������Ϣ  
	   }
	   
	   /*   http获取文件 */
	    public static InputStream getInputStreamByGet(String url) {
	        try {
	            HttpURLConnection conn = (HttpURLConnection) new URL(url)
	                    .openConnection();
	            conn.setReadTimeout(5000);
	            conn.setConnectTimeout(5000);
	            conn.setRequestMethod("GET");

	            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                InputStream inputStream = conn.getInputStream();
	                return inputStream;
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
