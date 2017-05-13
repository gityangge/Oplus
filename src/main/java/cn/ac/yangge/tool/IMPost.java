package cn.ac.yangge.tool;

import org.json.JSONObject;

public class IMPost {
	public static int newIMUser(String userName,String NickName,String HeadURL){
		String postURL="https://console.tim.qq.com/v4/im_open_login_svc/account_import?"
				+ "usersig="+GetUserSig.getUserSigOld()
				+ "&apn=1&identifier=yangge&sdkappid=1400024582&contenttype=json";
		String postBody="{\"Identifier\":\""+userName+"\","
				+ "\"Nick\":\""+NickName+"\","
				+ "\"FaceUrl\":\""+HeadURL+"\"}";
		String result=PostHelper.post(postURL, postBody);
		JSONObject jsonObject=new JSONObject(result);
		return jsonObject.getInt("ErrorCode");
	}
}
