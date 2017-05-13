package cn.ac.yangge.tool;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

import cn.ac.yangge.pojo.ArraryHelp;



public class ObjectAndJson {
	public static String ObjectToJson(ArraryHelp arraryHelp){
		/*
		Gson gson=new Gson();
		return gson.toJson(obj);*/
		return JsonStream.serialize(arraryHelp);
	}
	public static ArraryHelp JsonToObject(String json){
		//Gson gson=new Gson();
		//ArraryHelp arr=gson.fromJson(json,ArraryHelp.class);
		return JsonIterator.deserialize(json, ArraryHelp.class);
	}
}
