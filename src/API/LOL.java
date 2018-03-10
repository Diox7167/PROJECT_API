package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LOL {
    
    private String lastContent;
    private int lastCode; 
    
    public LOL() {
    }
    
    public boolean request(String urlRequest) {
        try {
                URL url = new URL(urlRequest);
                HttpURLConnection uc = (HttpURLConnection)url.openConnection();
                uc.connect();

                this.lastCode = uc.getResponseCode();
                this.lastContent = getLastContent(uc);
        } catch (Exception e) {
                e.printStackTrace();
        }
        if(this.lastCode == 200) {
                return true;
        }else {
                return false;
        }
    }
    

	private String urlEncode(String str) {
		String strEncoded = "";
		try {
			strEncoded = URLEncoder.encode(str, "UTF-8");
		}catch (Exception e) {
			System.out.println(e);
		}
	return strEncoded;
	}
	
	
	private String getLastContent(HttpURLConnection uc) throws Exception{
		String lastContent = "";
		if (200 == this.lastCode || 401 == this.lastCode || 404 == this.lastCode) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(this.lastCode==200?uc.getInputStream():uc.getErrorStream()));  
            StringBuffer sb = new StringBuffer();  
            String line;  
            while ((line = rd.readLine()) != null) {  
                sb.append(line);  
            }
            rd.close();
            lastContent = sb.toString();
        }
        return lastContent;
	}

        //Getters publics
	public String getResponseContent() {
		return this.lastContent;
	}
	
	public int getResponseCode() {
		return this.lastCode;
	}
    
}