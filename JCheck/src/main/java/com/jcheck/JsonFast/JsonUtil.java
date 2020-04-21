
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static void main(String[] args) throws Exception {
        String s = "{\"msg\": \"success\", \"data\": [{\"xm\": \"张三\", \"birthdate\": \"1990-01-18 11:10:41\"},{\"xm\": \"李四\", \"birthdate\": \"1991-01-18 11:10:41\"}]}";
        //json字符串转Map//
        Map<String,Object> jsonToMap = JSONObject.parseObject(s);
        System.out.println("jsonToMap："+jsonToMap);
        //Map转json字符串//
        String mapToJson = JSON.toJSONString(jsonToMap);
        System.out.println("mapToJson："+mapToJson);
        //json字符串转List
        List<Map> jsonToList = JSONArray.parseArray(jsonToMap.get("data").toString(),Map.class);
        System.out.println("jsonToList："+jsonToList);
        //List转json字符串
        String listToJson = JSON.toJSONString(jsonToList);
        System.out.println("listToJson："+listToJson);
    }
}
