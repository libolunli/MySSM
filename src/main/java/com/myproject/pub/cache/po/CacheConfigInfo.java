package com.myproject.pub.cache.po;

import java.util.List;

public class CacheConfigInfo {
	private String name;
	private String description;
	private String sql;
	private List<CacheConfigTemple> cacheConfigTempleList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<CacheConfigTemple> getCacheConfigTempleList() {
		return cacheConfigTempleList;
	}

	public void setCacheConfigTempleList(List<CacheConfigTemple> cacheConfigTempleList) {
		this.cacheConfigTempleList = cacheConfigTempleList;
	}
}
