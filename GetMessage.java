package Main;

import java.net.*;
import java.util.regex.*;

public class GetMessage {
	public String GetMessage(String i) {
		String c1,c2,ancode1,ancode2;
		c1 = i.substring(0,2);
		c2 = i.substring(2,3);
		
		
		ancode1 = getDNumber(c1);
		if (ancode1.equals("")) {
			return "宿舍不存在1  "+c1;
		}else {
			ancode2 = getSNumber(c2);
			if (ancode2.equals("")) {
				return "宿舍不存在2  "+c2;
			} else {
				String Gcut[] = Cut(Post(ancode1,i));
				String Getcut = "";
				for (int j = 0; j < Gcut.length; j++) {
					switch (j) {
					case 0:
						Getcut += "宿舍号：";
						break;
					case 1:
						Getcut += " 使用电量：";
						String a1 = Gcut[j];
						Gcut[j] = ZhengZe(a1,"\\d+\\.\\d+");
						break;
					case 2:
						Getcut += " 剩余电量：";
						String a2 = Gcut[j];
						Gcut[j] = ZhengZe(a2,"-\\d+\\.\\d+|\\d+\\.\\d");
						break;
					case 3:
						Getcut += " 时间：";
						String a3 = Gcut[j];
						Gcut[j] = ZhengZe(a3,"\\d+/\\d/\\d+");
						Gcut[j] +=" " + ZhengZe(a3,"[\\u4e00-\\u9fa5]*\\d+:\\d+:\\d+");
						break;
					}
					Getcut += Gcut[j];
					System.out.println(Gcut[j]);
				}
				return Getcut;
			}
		}

	}
	private String getDNumber(String a) {
		switch (a) {
		case "10":
			return"48";
			
		case "11":
			return"39";
			
		case "12":
	        return"1";

		case "13":
	        return"10";

		case "14":
	        return"18";

		case "15":
			return"32";
	
		case "16":
			return"56";
			
		case "17":
	        return"63";
	        
		case "18":
	        return"70";

		case "19":
	        return"77";

		case "20":
	        return"88";

		case "21":
	        return"95";

		case "22":
	        return"102";

		case "23":
	        return"109";

		case "24":
	        return"116";

		case "27":
	        return"84";

		case "28":
	        return"123";

		case "2":
			return"149";

		case "34":
	        return"185";

		case "40":
	        return"157";

		case "43":
	        return"158";

		case "45":
	        return"132";

		case "46":
	        return"133";

		case "47":
	        return"159";

		case "48":
	        return"160";
			
		default:
			return "";
		}
	}
	private String getSNumber(String a) {
		switch (a) {

		case "1":
			return "50";
		
		case "2":
			return "51";
		
		case "3":
			return "52";
		
		case "4":
			return "53";
		
		case "5":
			return "54";
		
		case "6":
			return "55";

		default:
			return"";
			
		}
	}
	private String Post(String a,String b) {
	Post request = new Post();
	try{
        //Proxy proxy = request.setProxy(InetAddress.getLocalHost().getHostAddress() , 8888 );
        //System.out.println(proxy );
        
        String url ="http://dian.gdlgxy.com/RoomSelect/GetRoomInfo?";
        String formData = "Apartid="+ a +"&Roomname=" + b;
        String rs = request.sendPost(url, null , formData );
        //System.out.println(a);
        //System.out.println(b);
        byte[] rs_bytecode = rs.getBytes();
        String rs_encode = new String(rs_bytecode,"utf-8");
        return rs_encode;
    }catch(Exception e ){
        e.printStackTrace();
        return "";
    }
	
	}
	private String[] Cut(String A) {
		A = A.replace("\r", "");
		A = A.replace("\n", "");
		A = A.replace(" ", "");
		System.out.println(A);
		A = A.replaceAll("[?]", "");
		System.out.println(A);
		A = A.replace("星期", " ");
		
		String[] C = A.split("<labelclass=\"infolab\">|</label>");
		String[] Chun = new String[4];
		//System.out.println(A);
		int j = 0;
		for (int i = 0; i < C.length-1; i++) {
			if(i%2 == 0) {
				Chun[j++] = C[i+1];
			}
		}
		
		//for (int i = 0; i < Chun.length; i++) {
			//System.out.println(Chun[i]);
		//}
		//System.out.println(Chun.length);
		return Chun;
	}
	private String ZhengZe(String insize,String BiaoDaShiCode) {
		Pattern pattern = Pattern.compile(BiaoDaShiCode);
		Matcher matcher = pattern.matcher(insize);
		matcher.find();
		String string = matcher.group();
	    System.out.println(string);   
		return string;
		
	}
}

