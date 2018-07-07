package com.best.wechat.ai;
 
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
/**
 * 
 * 版权所有：2016 项目名称：ImgeBase64
 *
 * 类描述：将图片转化为Base64字符串 
 * 类名称：cn.sanishan.util.Base64Img 
 * 创建人：
 * 创建时间：2016年10月27日
 * 下午3:25:49 
 * 修改人： 
 * 修改时间：2016年10月27日 下午3:25:49 
 * 修改备注：
 * 
 * @version V1.0
 */
public class Base64Img {
	/**
	 * @Title: GetImageStrFromUrl
	 * @Description: TODO(将一张网络图片转化成Base64字符串)
	 * @param imgURL 网络资源位置
	 * @return Base64字符串
	 */
	public static String GetImageStrFromUrl(String imgURL) {
		byte[] data = null;
		try {
			// 创建URL
			URL url = new URL(imgURL);
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
            try {
                data = readInputStream(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
        //"data:image/jpg;base64,"
		return "data:image/jpg;base64,"+encoder.encode(data);
	}
 
	/**
	 * @Title: GetImageStrFromPath
	 * @Description: TODO(将一张本地图片转化成Base64字符串)
	 * @param imgPath
	 * @return
	 */
	public static String GetImageStrFromPath(String imgPath) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgPath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
		return encoder.encode(data);
	}
 
	/**
	 * @Title: GenerateImage
	 * @Description: TODO(base64字符串转化成图片)
	 * @param imgStr
	 * @return
	 */
	public static boolean GenerateImage(String imgStr) {
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = "d://222.jpg";
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
