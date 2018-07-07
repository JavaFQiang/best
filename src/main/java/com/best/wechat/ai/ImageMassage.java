package com.best.wechat.ai;

import com.alibaba.fastjson.JSONObject;
import com.best.common.HttpsUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ImageMassage {

    public static void colorScore(String picUrl) {
        String image = Base64Img.GetImageStrFromUrl(picUrl);
       // String s = Base64Img.GetImageStrFromPath("C:\\Users\\Administrator\\Pictures\\IMG_20170806_182725.jpg");
       /// System.out.println(s);
        //System.out.println(image);
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", AIconfig.IMGAPPID);
        params.put("nonce_str", HttpsUtils.getRandomString(32));
        params.put("image", image);
        params.put("time_stamp", HttpsUtils.getSecondTimestamp(new Date()));
        params.put("sign", AIconfig.getSign(params, AIconfig.IMGAPPKEY));
        JSONObject jsonObject = HttpsUtils.doPost("https://api.ai.qq.com/fcgi-bin/ptu/ptu_faceage", params);
        //Map<String,String> data = (Map<String,String>)jsonObject.get("data");
        //String answer = data.get("answer");
        //return answer;
        System.out.println(jsonObject.toJSONString());
    }

    public static void main(String[] args) throws Exception {
        String imgUrl = "http://mmbiz.qpic.cn/mmbiz_jpg/yibhhVZmDENxrCrsUV07stSia7vGxavh0MRZMpkNMic5lIibCNiaFBhmkn5VmU5XywyvcatKsZjiaezZC0amiaibKPb7cA/0";
        colorScore(imgUrl);
//        String s = Base64Img.GetImageStrFromUrl(imgUrl);
//        System.out.println(s);

        //new一个URL对象
        /*URL url = null;
        try {
            url = new URL("http://mmbiz.qpic.cn/mmbiz_jpg/yibhhVZmDENxrCrsUV07stSia7vGxavh0MSaYNOoN7D7SPCAeH2VENysVfhDK70KfggPTH7J0HeyOOPNOerA64zg/0");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = Base64Img.readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File("123.jpg");
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();*/

    }
}
