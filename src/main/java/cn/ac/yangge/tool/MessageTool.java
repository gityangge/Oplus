package cn.ac.yangge.tool;

import org.json.JSONObject;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class MessageTool {
	public static int messageTool(String tel,int code){
		String serverUrl="http://gw.api.taobao.com/router/rest";
		String appKey="23635390";
		String appSecret="d4cc5a64d37101987208cc0c03fa595b";
		TaobaoClient client = new  DefaultTaobaoClient(serverUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( "零创Oplus" );
		req.setSmsParamString( "{code:'"+code+"'}" );
		req.setRecNum(tel);
		req.setSmsTemplateCode( "SMS_47125094" );
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
			return 0;
		}
		JSONObject jsonObj = new JSONObject(rsp.getBody());
		String errCode=jsonObj.getJSONObject("alibaba_aliqin_fc_sms_num_send_response").getJSONObject("result").getString("err_code");
		if(errCode.equals("0")){
			return 1;
		}else{
			System.out.println("rep:"+rsp.getBody());
			return 0;
		}
	
	}
}
