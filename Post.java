package Main;

import java.io.*;
import java.net.*;

public class Post {

    public Proxy setProxy(String proxyIp , int proxyPort ){
        try{
            InetSocketAddress socketAddress 
                = new InetSocketAddress(proxyIp , proxyPort );
            Proxy proxy = new Proxy(Proxy.Type.HTTP , socketAddress );
            return proxy;
        }catch(Exception e ){
            e.printStackTrace();
        }
        return null;
    }
    
    public String sendPost(String url , String params  , String formData) throws Exception{

        StringBuilder builder = new StringBuilder();

        if(!(params == null || params.length() == 0) ){
            url += ("?" + params ); 
        }

        URL Url = new URL(url );
        URLConnection conn = Url.openConnection();

        //������ô��� , �ͷ���GETһ��.
        conn.setRequestProperty("accept", "*/*" );
        conn.setRequestProperty("Connection", "Keep-Alive" );
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36");

        //����֮��Ϳ��Է���POST������
        conn.setDoInput(true );
        conn.setDoOutput(true );


        //��ȡ��������� , ֱ��д��post����
        PrintWriter writer = new PrintWriter(conn.getOutputStream() );
        writer.print(formData );                                    
        writer.flush();


        //��ȡ������ķ�������
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream() ) );
        String line = reader.readLine();
        line = new String(line.getBytes() , "UTF-8" );  //������������
        while(line != null ){
            //System.out.println(line );
            builder.append(line + "\r\n" );
            line = reader.readLine();
        }
        reader.close();
        writer.close();


        return builder.toString();
    }
}
