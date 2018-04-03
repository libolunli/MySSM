package com.myproject.pub.cache.po;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myproject.pub.util.ArrayUtil;
import com.myproject.pub.util.BeanUtil;
import com.myproject.pub.util.ListUtil;
import com.myproject.pub.util.MapUtil;
import com.myproject.pub.util.StringUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
public class CacheConfigManager implements InitializingBean {

	private String cacheName = "CACHEINFO";

	private String separator = ",";

	private static final Logger log = Logger.getLogger(CacheConfigManager.class);

	private List<CacheConfigInfo> cacheTempleList;

	@Autowired
	private SqlSessionTemplate sqlSessionTemple;

	@Autowired
	private CacheManager cacheManager = CacheManager.getInstance();

	public void afterPropertiesSet() throws Exception {
		init();
	}

	private void init() {

		List<CacheConfigInfo> cacheList = getCacheTempleList();
		if (ListUtil.isEmpty(cacheList)) {
			return;
		}
		for (CacheConfigInfo cacheConfigInfo : cacheList) {
			List<Map<String, Object>> mapList = getDate(cacheConfigInfo);
			for (CacheConfigTemple cacheConfigTemple : cacheConfigInfo.getCacheConfigTempleList()) {
				Map<String, Object> mapValue = parseDate(mapList, cacheConfigTemple);
				log.debug("INFO:" + mapValue.toString());
				put(cacheConfigInfo.getName()+"."+cacheConfigTemple.getKey(),mapValue);
			}
		}
	}

	public List<Map<String, Object>> getDate(CacheConfigInfo cacheConfigInfo) {
		log.debug("\n" + cacheConfigInfo.getSql().toString() + "\n");
		System.out.println("\n" + cacheConfigInfo.getSql().toString() + "\n");
		return sqlSessionTemple.selectList("SqlUtil.sql", cacheConfigInfo.getSql().toString());
	}

	public void put(String key, Object value) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	public Object get(String key) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public Map<String, Object> parseDate(List<Map<String, Object>> datas, CacheConfigTemple cacheConfigTemple) {

		String[] keyArr = cacheConfigTemple.getKey().split(separator);

		Map<String, Object> valueMap = new HashMap<String, Object>();

		switch (cacheConfigTemple.getValueType()) {
		case "Map":
			mapCache(keyArr, datas, cacheConfigTemple.getClazz(), valueMap);
			break;
		case "List":
			listCache(keyArr, datas, cacheConfigTemple.getClazz(), valueMap);
			break;
		default:
			log.debug("不符合格式!");
			break;
		}

		return valueMap;

	}

	@SuppressWarnings("unchecked")
	private void listCache(String[] keyArr, List<Map<String, Object>> datas, String objectName,
			Map<String, Object> valueMap) {
		for (Map<String, Object> obj : datas) {
			String valuekey = buildString(keyArr, obj);
			Object value = valueMap.get(valuekey);
			if (value == null) {
				value = new ArrayList<>();
				valueMap.put(valuekey, value);
			}
			if (value instanceof List<?>) {
				List list = (List) value;
				Object listValue = obj;
				if (StringUtil.isNotEmpty(objectName)) {
					try {
						Class<?> c = Class.forName(objectName);
						listValue = BeanUtil.convertMap4OPbject(c, obj);
					} catch (ClassNotFoundException e) {
						log.error(e.getMessage());
						return;
					} catch (IllegalAccessException e) {
						log.error(e.getMessage());
						return;
					} catch (InstantiationException e) {
						log.error(e.getMessage());
						return;
					} catch (InvocationTargetException e) {
						log.error(e.getMessage());
						return;
					} catch (IntrospectionException e) {
						log.error(e.getMessage());
						return;
					}
				}
				list.add(listValue);
				valueMap.put(valuekey, list);
			}
		}
	}

	private void mapCache(String[] keyArr, List<Map<String, Object>> datas, String objectName,
			Map<String, Object> valueMap) {
		for (Map obj : datas) {
			String valueKey = buildString(keyArr, obj);
			if (StringUtil.isNotEmpty(objectName)) {
				try {
					Class<?> c = Class.forName(objectName);
					Object o = BeanUtil.convertMap4OPbject(c, obj);
					valueMap.put(valueKey, o);
					break;
				} catch (IllegalAccessException e) {
					log.error(e.getMessage());
					return;
				} catch (InstantiationException e) {
					log.error(e.getMessage());
					return;
				} catch (InvocationTargetException e) {
					log.error(e.getMessage());
					return;
				} catch (IntrospectionException e) {
					log.error(e.getMessage());
					return;
				} catch (ClassNotFoundException e) {
					log.error(e.getMessage());
					return;
				}
			}
			valueMap.put(valueKey, obj);
		}
	}

	private String buildString(String[] keyArr, Map obj) {
		String valuekey = "";
		for (int i = 0; i < keyArr.length; i++) {
			valuekey += obj.get(keyArr[i].toUpperCase());
			if (i != keyArr.length - 1) {
				valuekey += separator;
			}
		}
		return valuekey;
	}

	public static <T>T getByIndex(String itemName , String indexName , Class<T> clazz , String ... keys){
		String key = ArrayUtil.getStringFromArray(keys, ",");
		return getByIndex(itemName , indexName , clazz , key);
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T getByIndex(String itemName , String indexName , Class<T> clazz, String key){
		CacheConfigManager cacheConfigManager = new CacheConfigManager();
 		Map<String , Object> valueMap = (Map<String , Object>)cacheConfigManager.get(itemName+"."+indexName);
		if(MapUtil.isNotEmpty(valueMap)){
			return (T)valueMap.get(key);
		}
		return null;
	}
	
	public List<CacheConfigInfo> getCacheTempleList() {
		return cacheTempleList;
	}

	public void setCacheTempleList(List<CacheConfigInfo> cacheTempleList) {
		this.cacheTempleList = cacheTempleList;
	}

}
