package com.example.push.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.push.model.AuthorInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 实体类和JSON对象之间相互转化（依赖包jackson-all-1.7.6.jar、jsoup-1.5.2.jar）
 * @author wck
 *
 */
public class JSONUtil {
  private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);
  /**
   * 将json转化为实体POJO
   * @param jsonStr
   * @param obj
   * @return
   */
  public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
    T t = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      t = objectMapper.readValue(jsonStr,
          obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return t;
  }
  /**
   * 将实体POJO转化为JSON
   * @param obj
   * @return
   * @throws JSONException
   * @throws IOException
   */
  public static<T> JSONObject objectToJson(T obj) throws JSONException, IOException {
    ObjectMapper mapper = new ObjectMapper(); 
    // Convert object to JSON string 
    String jsonStr = "";
    try {
       jsonStr = mapper.writeValueAsString(obj);
    } catch (IOException e) {
      throw e;
    }

    JSONObject jsonObject = JSON.parseObject(jsonStr);
    return jsonObject;
  }

  /**
   * json转AuthorInfo实体类
   * @param json
   * @return
   */
  public static AuthorInfo jsonToAuthorInfo(JSONObject json){
    AuthorInfo a=new AuthorInfo();
    boolean isStar= (boolean) json.get("is_star");
    if(!isStar){
      logger.info("isNoStart:{}",String.valueOf(json.get("nick_name"))+":"+String.valueOf( json.get("short_id")));
      return  null;
    }
    a.setNickName(String.valueOf(json.get("nick_name")));
    a.setShortId(String.valueOf( json.get("short_id")));
    a.setInfoId(String.valueOf(json.get("id") ));
    String city=String.valueOf(json.get("province"))+String.valueOf(json.get("city"));
    a.setCity(city);
    String tags=String.valueOf(json.get("tags"));
    a.setTags(UnicodeUtil.unicodetoString(tags));
    a.setFollower(String.valueOf(json.get("follower")));
    a.setExpectedCpm(String.valueOf(json.get("expected_cpm")));
    a.setExpectedPlayNum(String.valueOf(json.get("expected_play_num")));
    a.setPersonalInterateRate(String.valueOf(json.get("personal_interate_rate")));
    List<JSONObject> price_info = (List<JSONObject>) json.get("price_info");
    String price1 = String.valueOf(price_info.get(0).get("price")) ;
    String price2 = String.valueOf(price_info.get(1).get("price"));
    a.setPrice1(price1);
    a.setPrice2(price2);
    String homePage="https://www.iesdouyin.com/share/user/"+String.valueOf(json.get("short_id"));
    a.setHomepage(homePage);
    String gender="";
    if("1".equals(String.valueOf(json.get("gender")))){
      gender="男";
    }else {
      gender="女";
    }
    a.setGender(gender);
//    a.setFansDistribute(String.valueOf(json.get("")));
//    a.setSexDistribute(String.valueOf(json.get("")));
//    a.setAgeDistribute(String.valueOf(json.get("")));
//    a.setActiveDistribute(String.valueOf(json.get("")));
//    a.setPhoneDistribute(String.valueOf(json.get("")));
    return a;
  }

}