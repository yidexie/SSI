package com.weixin.core.dao.dialect;
public class SqlServerDialect implements Dialect {     
    	protected static final String SQL_END_DELIMITER = ";";     
     
       public String getLimitString(String sql, int offset, int limit) {  
            sql = trim(sql);     
            StringBuffer sb = new StringBuffer(sql.length() + 80);     
            sb.append("SELECT TOP ").append(limit).append(" A.* FROM( ").append(sql);   
            sb.append(" ) AS A WHERE A.ID NOT IN (SELECT TOP ").append(offset);   
            sb.append(" B.ID FROM ( ").append(sql).append(" ) AS B ORDER BY B.ID) ORDER BY A.ID");   
            return sb.toString();     
       }  
     
       public boolean supportsLimit() {  
           return true;  
       }  
     
       private String trim(String sql) {  
           sql = sql.trim();  
           if (sql.endsWith(SQL_END_DELIMITER)) {  
               sql = sql.substring(0, sql.length() - 1  
                       - SQL_END_DELIMITER.length());  
           }  
           return sql;  
       }  
}    