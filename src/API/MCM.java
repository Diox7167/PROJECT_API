package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class MCM {
	
	String mkmAppToken = "y1kSsxDeoVVGW53y";
    String mkmAppSecret = "FCyi16fOAugkFeGHCLCp2mW3SBROtit3";
    String mkmAccessToken = "ub5GVxuW9CDR6Qqx5zr5yJ6W01ZSTrDY";
    String mkmAccessTokenSecret = "98J7ps40S3JdsnNcOvYR2DNmErnQrujO";	
               
    private String oauth_version = "1.0";  
    private String oauth_timestamp;
    private String oauth_nonce;
    private String oauth_consumer_key = "y1kSsxDeoVVGW53y";
    private String oauth_token = "ub5GVxuW9CDR6Qqx5zr5yJ6W01ZSTrDY";
    private String oauth_signature_method = "HMAC-SHA1";
    private String oauth_signature;        
    
    private String authorizationProperty;
    
    private String lastContent;
    private int lastCode;
    
    public MCM() {
    	this.oauth_timestamp = "" + (System.currentTimeMillis()/1000);
    	this.oauth_nonce = getRandomString();
    }
    
    public boolean request(String urlRequest) {
		try {
			URL url = new URL(urlRequest);
			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
			setOauthSignature(urlRequest);
			setAuthorizationProperty(urlRequest);
			uc.addRequestProperty("Authorization", this.authorizationProperty);
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
    
    //Permet de d�finir la variable de classe this.oauth_signature
    //qu'on devra ensuite renseigner dans la variable this.authorizationProperty
    private void setOauthSignature(String url) {
		String encodedRequestURL = urlEncode(url);
		String baseString = "GET&" + encodedRequestURL + "&";
        String paramString = "oauth_consumer_key=" + urlEncode(oauth_consumer_key) + "&" +
                             "oauth_nonce=" + urlEncode(oauth_nonce) + "&" +
                             "oauth_signature_method=" + urlEncode(oauth_signature_method) + "&" +
                             "oauth_timestamp=" + urlEncode(oauth_timestamp) + "&" +
                             "oauth_token=" + urlEncode(oauth_token) + "&" +
                             "oauth_version=" + urlEncode(oauth_version);
        baseString = baseString + urlEncode(paramString);
        String signingKey = urlEncode(mkmAppSecret) + "&" + urlEncode(mkmAccessTokenSecret) ;
        try{
        	Mac mac = Mac.getInstance("HmacSHA1");
        	SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
        	mac.init(secret);
        	byte[] digest = mac.doFinal(baseString.getBytes());
        	this.oauth_signature = DatatypeConverter.printBase64Binary(digest);
        }catch (Exception e) {
        	System.out.println(e);
        }
	}
    
    //Permet de d�finir la variable de classe this.authorizationProperty
    //qu'on devra ensuite renseigner dans le header de la requ�te
    private void setAuthorizationProperty(String urlRequest) {
		this.authorizationProperty = 
				"OAuth " +
                "realm=\"" + urlRequest + "\", " + 
                "oauth_version=\"" + this.oauth_version + "\", " +
                "oauth_timestamp=\"" + this.oauth_timestamp + "\", " +
                "oauth_nonce=\"" + this.oauth_nonce + "\", " +
                "oauth_consumer_key=\"" + this.oauth_consumer_key + "\", " +
                "oauth_token=\"" + this.oauth_token + "\", " +
                "oauth_signature_method=\"" + this.oauth_signature_method + "\", " +
                "oauth_signature=\"" + this.oauth_signature + "\"" ;
	}

    //Encode une string en version get url
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
	
	
	private String getRandomString() {
		String alphaNum = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String randomString = "";
		int randomNumber = 0;
		for (int i = 0; i < 13; i++) {
			Random rand = new Random();
			randomNumber = rand.nextInt(62);
			randomString += alphaNum.charAt(randomNumber);
		}
		return randomString;
	}
	
	
	//Getters publics
	public String getResponseContent() {
		return this.lastContent;
	}
	
	public int getResponseCode() {
		return this.lastCode;
	}
	
}
