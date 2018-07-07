package com.best.wechat.ai;

import com.alibaba.fastjson.JSONObject;
import com.best.common.HttpsUtils;

import java.util.*;

public class TxtMassage {

    //智能闲聊
    public static String scene(String msg,String toUser) {
        Map<String,Object> params = new HashMap<>();
        params.put("app_id",AIconfig.TXTMSGAPPID);
        params.put("nonce_str",HttpsUtils.getRandomString(32));
        params.put("question",msg);
        params.put("session",toUser);
        params.put("time_stamp",HttpsUtils.getSecondTimestamp(new Date()));
        params.put("sign",AIconfig.getSign(params,AIconfig.TXTMSGAPPKET));
        JSONObject jsonObject = HttpsUtils.doPost("https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat", params);
        Map<String,String> data = (Map<String,String>)jsonObject.get("data");
        String answer = data.get("answer");
        return answer;
    }

    public static void main(String[] args) {
        String scene = TxtMassage.scene("456","abc");
        System.out.println(scene);
    }
}
