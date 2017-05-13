package cn.ac.yangge.tool;

import com.tls.tls_sigature.tls_sigature;
import com.tls.tls_sigature.tls_sigature.GenTLSSignatureResult;

public class GetUserSig {
	private static String userSig="eJxFkFtvgjAYhv8L18vogYPZnUGdbJC6iAe8aYptWTUDVisiy-77GiLZ7fPky-u*34*TJetn1jSKU2Yo1tx5cYDzNGDRNUoLyqQR2mIMfRwAMNpW6IuqKysQgD5EGIB-qbiojJJqOLyzqizFw1xUaVE6z6P4Y7ZJlys3PPWlxrd*nhzSNtqc9nxddOUO7M6aL7*7*vb*RuJp-DklOSFyn4EQbSOFSJYUaEVy7LnXoO2KVzff9lAuYhnP0BjGz3QYZyOhZ*shz5*M0qgvMfBJiFDgBd6Ds*OxvlaGmnsjhm-8-gEQTFfE";
	public static String getUserSigOld(){
		return userSig;
	}
	public static String getUserSig(String identifier){
		try{			
		//Use pemfile keys to test
	    String privStr = "-----BEGIN PRIVATE KEY-----\n" +
		"MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgV2OXAoJ1gO+fovrvglaK\n" +
		"lc/FwosDHncFgjKgkpH11PShRANCAASHuyQl0etcezmk64EJBS8XfIDSvmpywT7l\n" +
		"C/2djskbwVcuMGBZGxutm1doIqtfYCaAT/jEfi1ArqjG3/ljw809\n" +
		"-----END PRIVATE KEY-----";
	    
	 	//change public pem string to public string
	 	/*String pubStr = "-----BEGIN PUBLIC KEY-----\n"+
	 	"MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE1OPUIbABFXyFSVJgekqVAugT2pLtYP3c\n"+
	 	"59DZe4iGO1u4NhaADVp6BedXhFG+pXGd4M34IPFRRk6Sa4sB0Fn+Uw==\n"+
	 	"-----END PUBLIC KEY-----";	*/		
        // generate signature
        GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(1400024582,identifier, privStr,3600*24*7);
        if (0 == result.urlSig.length()) {
            System.out.println("GenTLSSignatureEx failed: " + result.errMessage);
            return "err";
        }else{
        	return result.urlSig;
        }
        

	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "err";
	}
	}
}
