import cn.ac.yangge.pojo.ArraryHelp;
import cn.ac.yangge.tool.ObjectAndJson;

public class test1 {
	public static void main(String[] args) {
		
//		JsonIterator iter=JsonIterator.parse("{'id':0,'err':'my errors'}".replace('\'', '"'));
//		try {
//			ErrorMessage err=iter.read(ErrorMessage.class);
//			System.out.println(err.getErr());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		/*JsonIterator iter = JsonIterator.parse("[123, {'id': 0,'err':'this error'}]".replace('\'', '"'));
		try {
			iter.readArray();
			System.out.println(iter.readInt());
			iter.readArray();
			ErrorMessage err= iter.read(ErrorMessage.class);
			iter.readArray(); // end of array
			System.out.println(err.getErr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		/*ArraryHelp arr=new ArraryHelp();
		List<String> list=new ArrayList<String>();
		list.add("ss");
		arr.setList(list);
		String result=ObjectAndJson.ObjectToJson(arr);
		System.out.println(result);*/
		String resource="{\"list\":[\"ss\"]}";
		ArraryHelp arr=ObjectAndJson.JsonToObject(resource);
		System.out.println(arr.getList().toString());
	}
}
