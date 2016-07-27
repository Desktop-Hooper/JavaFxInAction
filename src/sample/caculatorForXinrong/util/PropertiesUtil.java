package sample.caculatorForXinrong.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class PropertiesUtil {

	private static Properties pps = null;

	public static List<String> getPropertiesValueToList(String filePath) {
		List<String> result = new ArrayList<String>();
		try {
			pps = new OrderedProperties();
			pps.load(PropertiesUtil.class.getResourceAsStream(filePath));
			for(Object obj:pps.keySet()){
				String strKey = (String) obj;
				String strValue = pps.getProperty(strKey);
				result.add(strValue);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static List<String> getPropertiesKeyToList(String filePath) {
		List<String> result = new ArrayList<>();
		try {
			pps = new OrderedProperties();
			pps.load(PropertiesUtil.class.getResourceAsStream(filePath));
			for(Object obj:pps.keySet()){
				String strKey = (String) obj;
				result.add((String)obj);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Map<String, String> getPropertiesValueToMap(String filePath) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			pps = new OrderedProperties();
			pps.load(PropertiesUtil.class.getResourceAsStream(filePath));
			for(Object obj:pps.keySet()){
				String strKey = (String) obj;
				String strValue = pps.getProperty(strKey);
				result.put(strKey,strValue);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//保存之前的数据
	public static void modifyPropertiesValue(String filePath,
			Map<String, String> value) throws IOException {
		if (pps == null) {
			pps = new OrderedProperties();
			InputStream in = new FileInputStream(filePath);
			pps.load(in);
			in.close();
		}
		OutputStream fos = null;
		for (String key : value.keySet()) {
			pps.setProperty(key, value.get(key));
		}
		System.out.println(pps.toString());
		fos = new FileOutputStream(filePath);
		pps.store(fos, "set");

	}
	
	public static void modifyPropertiesValueCover(String filePath,
			Map<String, String> value) throws IOException {
        FileOutputStream out = new FileOutputStream(filePath);
        FileInputStream in = new FileInputStream(filePath);
        Properties props = new OrderedProperties();
        props.load(in);
        in.close();
        for(String key:value.keySet()){
         props.put(key, value.get(key));
        }
        props.store(out, null);
        out.close();
	}

	public static void modifyPropertiesValue(String filePath, String nameSalt,
			List<String> value) throws IOException {
		if (pps == null) {
			pps = new OrderedProperties();
			InputStream is = new FileInputStream(filePath);
			pps.load(is);
			is.close();

		}
		OutputStream fos = null;

		int i = 1;
		for (String val : value) {
			pps.put(nameSalt + i, val);
			i++;
		}
		fos = new FileOutputStream(filePath);
		pps.store(fos, "set");
		fos.close();

	}

	// public static void main(String[] args) {

	// System.out.println(PropertiesUtil.class.getClassLoader().getResourceAsStream("resource/yearRatio.properties"));

	// Map<String, String> map = new HashMap<String, String>();
	// map.put("ratio4", "1");
	// map.put("ratio5", "2");
	// map.put("ratio6", "3");

	// List<String> yearRatio = new ArrayList<String>();
	// yearRatio.add("0.168");
	// yearRatio.add("0.15");
	// yearRatio.add("0.138");
	//
	//
	// PropertiesUtil.modifyppsertiesValue(
	// "src/resource/yearRatio.properties", "yearRatio",yearRatio);
	//
	// PropertiesUtil.getppsertiesValue("src/resource/yearRatio.properties");
	// }
}
