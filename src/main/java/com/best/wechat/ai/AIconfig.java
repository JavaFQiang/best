package com.best.wechat.ai;

import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AIconfig {

    //智能闲聊
    public static final String TXTMSGAPPID = "1107009890";
    public static final String TXTMSGAPPKET = "mnQDBJtGP295RErP";

    //图片识别
    public static final String IMGAPPID = "1106913607";
    public static final String IMGAPPKEY = "ZgHhPn3RHtDyPdFN";

    public static String getSign(Map<String, Object> params,String AppKey) {
        List<Map.Entry<String, Object>> entryList = new ArrayList<>(params.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());//根据key排序
            }
        });
        String sign ="";
        for (int i = 0; i < entryList.size(); i++) {
            try {
                sign+="&"+entryList.get(i).getKey()+"="+URLEncoder.encode(String.valueOf(entryList.get(i).getValue()),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            sign = sign.substring(1)+"&app_key="+URLEncoder.encode(AppKey,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return StringToMd5(sign).toUpperCase();
    }

    public static String StringToMd5(String str) {
        {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(str.getBytes("UTF-8"));
                byte[] encryption = md5.digest();

                StringBuffer strBuf = new StringBuffer();
                for (int i = 0; i < encryption.length; i++) {
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                    } else {
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));
                    }
                }

                return strBuf.toString();
            } catch (NoSuchAlgorithmException e) {
                return "";
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
    }

}
