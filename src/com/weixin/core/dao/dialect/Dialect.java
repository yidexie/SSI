package com.weixin.core.dao.dialect;

public interface Dialect {
	public boolean supportsLimit();

	public String getLimitString(String sql, int offset, int limit);
}
