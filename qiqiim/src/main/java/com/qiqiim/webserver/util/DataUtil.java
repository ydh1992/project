package com.qiqiim.webserver.util;


import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {

	private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	public static final char UNDERLINE = '_';

	// 生成指定位数的随机数字
	public static String createNums(int count) {
		String code = "";
		Random ram = new Random();
		for (int i = 0; i < count; i++) {
			code += ram.nextInt(10);
		}
		return code;
	}

	// 拼装Map
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> mapOf(Object... v) {
		Map<K, V> ret = new HashMap<K, V>();
		if (null == v) {
			return ret;
		}
		for (int i = 0; i < v.length; i++) {
			ret.put((K) v[i], (V) v[++i]);
		}
		return ret;
	}

	// 拼装json
	public static JSONObject jsonOf(Object... v) {
		JSONObject json=new JSONObject();
		if (null == v) {
			return json;
		}
		for (int i = 0; i < v.length; i++) {
			json.put(v[i], v[++i]);
		}
		return json;
	}

	// 拼装Map
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> setOf(Object... v) {
		Map<K, V> ret = new TreeMap<K, V>();
		if (null == v) {
			return ret;
		}
		for (int i = 0; i < v.length; i++) {
			ret.put((K) v[i], (V) v[++i]);
		}
		return ret;
	}

	// 删除多余Key
	public static <K, V> void removeKeys(Map<K, V> map, String[] keys) {
		for (String key : keys) {
			map.remove(key);
		}
	}

	// 生成指定位数的随机字母字符串
	public static String createLetters(int count) {
		String code = "";
		int index = 0;
		Random ram = new Random();
		for (int i = 0; i < count; i++) {
			index = ram.nextInt(chars.length);
			while (index < 10) {
				index = ram.nextInt(chars.length);
			}
			code += chars[index];
		}
		return code;
	}

	// 生成指定位数的随机字符串
	public static String createStrings(int count) {
		String code = "";
		Random ram = new Random();
		for (int i = 0; i < count; i++) {
			code += chars[ram.nextInt(chars.length)];
		}
		return code;
	}

	// 验证电话号码
	public static boolean isPhoneNum(String mdn) {
		String reg = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})"
				+ "-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
		return Pattern.matches(reg, null != mdn ? mdn : "");
	}

	// MD5 32位和16位加密
	public static String md5(String str, int count) {
		String result = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte[] bytes = md5.digest();
			int b = 0;
			StringBuffer mdString = new StringBuffer();
			for (int j = 0; j < bytes.length; j++) {
				b = bytes[j];
				if (b < 0) {
					b += 256;
				}
				if (b < 16) {
					mdString.append("0");
				}
				mdString.append(Integer.toHexString(b));
			}
			if (count == 16) {
				result = mdString.toString().substring(8, 24);
			} else if (count == 32) {
				result = mdString.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 验证图片格式
	public static boolean isImgFile(String imgName) {
		return Pattern.matches("^[\\w|\u4e00-\u9fa5]+\\.(gif|jpe?g|JPE?G|png)$", imgName);
	}

	// Map转换成Object
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		Object obj = beanClass.newInstance();
		if (null != map) {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				Method setter = property.getWriteMethod();
				if (setter != null) {
					setter.invoke(obj, map.get(property.getName()));
				}
			}
		}
		return obj;
	}

	// Object转换成Map
	public static Map<String, Object> objectToMap(Object obj) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (null != obj) {
				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					if (key.compareToIgnoreCase("class") == 0) {
						continue;
					}
					Method getter = property.getReadMethod();
					Object value = getter != null ? getter.invoke(obj) : null;
					map.put(key, value);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Object转换成Map
	public static Map<String, Object> objectToMap(Map<String, Object> map, Object obj) {
		try {
			if (null != obj) {
				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					if (key.compareToIgnoreCase("class") == 0) {
						continue;
					}
					Method getter = property.getReadMethod();
					Object value = getter != null ? getter.invoke(obj) : null;
					map.put(key, value);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 保留小数位
	public static float round(double a, int n) {
		return new BigDecimal(a).setScale(n, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	// 保留小数位
	public static float round(float a, int n) {
		return new BigDecimal(a).setScale(n, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	// 获取指定范围内的数字
	public static int getRandomNum(int minVal, int maxVal, boolean isRate) {
		Random r = new Random();
		int num;
		while (true) {
			num = r.nextInt(maxVal + 1);
			if (num < minVal) {
				continue;
			}
			if (isRate && num % 10 == 0) {
				break;
			}
		}
		return num;
	}

	// 格式化数字
	public static Double twoDigits(Double value) {
		if (null == value) {
			return null;
		}
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}

	// 格式化数字
	public static String twoDigits(BigDecimal value) {
		if (null == value) {
			return null;
		}
		return new DecimalFormat("#.##").format(value.doubleValue());
	}

	// 将对象存储在Map中返回
	public static HashMap<String, Object> saveObjectToMap(String key, Object value) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}

	// 数字校验
	public static boolean isNumber(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return Pattern.matches("\\d+", str);
		}
		return false;
	}

	// JSONObject校验
	public static boolean isJSONObject(Object str) {
		return null != str ? JSONUtils.mayBeJSON(str.toString()) : false;
	}

	// JSONArray校验
	public static boolean isJSONArray(String str) {
		return StringUtils.isNotEmpty(str) && JSONUtils.isArray(new JSONTokener(str).nextValue());
	}

	// UUID生成
	public static String buildUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 图片格式判断
	public static boolean isImg(String img) {
		return Pattern.matches("[^\\s]+\\.(jpg|gif|png|bmp)", img.toLowerCase());
	}

	// 视频格式判断
	public static boolean isVideo(String img) {
		return Pattern.matches("[^\\s]+\\.(mp4|avi|ts)", img.toLowerCase());
	}

	// 字符串是否未空
	public static boolean isEmpty(Object str) {
		return null == str || StringUtils.isEmpty(str.toString());
	}

	// 字符串不为空
	public static boolean isNotBlank(Object str) {
		return null != str && StringUtils.isNotBlank(str.toString());
	}

	// 获取客户端IP地址
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr().equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : request.getRemoteAddr();
	}

	// 驼峰格式字符串转换为下划线格式字符串
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// 下划线格式字符串转换为驼峰格式字符串
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// 请求地址解码
	public static String decode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	// 检查是否是数字
	public static boolean isDigit(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
	
	//判断图片路径，截取图片名称
	public static String getImgName(String img,String split){
		if(DataUtil.isNotBlank(img)){
			String[] file=img.split(split);
			List<String> imgList=new ArrayList<>();
			for(int i=0;i<file.length;i++){
				if(!file[i].startsWith("/templates/layui")){//本地资源
					imgList.add(file[i].lastIndexOf("/")>=0?file[i].substring(file[i].lastIndexOf("/")+1):file[i]);
				}else{
					imgList.add(file[i]);
				}
			}
			return StringUtils.join(imgList,split);
		}
		return null;
	}
	
	//全角转半角
	public static String ToDBC(String string) {
		char c[] = string.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}
	
	/**
	 * 获取两值中的随机浮点数
	 * [n1,n2]
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static double getRandomDouble(double n1,double n2){
		Random ra = new Random();
		return ra.nextDouble() * (n2-n1)+n1;
	}
	
	//判断是否是正数或者小数
	public static boolean isNumeric(String str){
		String reg = "^[0-9]+(.[0-9]+)?$";
		return str.matches(reg);
	}
	
	//隐藏电话号及用户名
	public static String formatString(String string){
		if(isDigit(string)){
			if(string.length()>2){
				int a=string.length()/3;
				int strLength=string.length()-(a*2);
				String expression="(\\d{"+a+"})\\d{"+strLength+"}(\\d{"+a+"})";
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < strLength; i++) {
					sb.append("*");
				}
				string=string.replaceAll(expression,"$1"+sb.toString()+"$2");
			}else{
				string=string.length()>0?string.substring(0,1)+"*":string;
			}
		}else if(!isDigit(string)){
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < string.length(); i++) {
				sb.append("*");
			}
			string=string.substring(0,1)+sb.toString();
		}
		return string;
	}
	
	// 过滤特殊字符  
	public static String StringFilter(String str) {
		// 清除掉所有特殊字符和空格
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
