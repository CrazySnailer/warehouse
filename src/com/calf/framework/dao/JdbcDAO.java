package com.calf.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.calf.framework.vo.Page;

public class JdbcDAO extends NamedParameterJdbcDaoSupport  {
	private final static Logger LOGGER = Logger.getLogger(JdbcDAO.class);
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public List queryForList(String sql){
		logger.debug(sql);
		return getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 查询顶层记录
	 * @param sql
	 * @param params
	 * @return
	 */
	public List queryTopList(String sql,Map params,int max){
		StringBuffer newSql = new StringBuffer(" SELECT temp.* ,ROWNUM num FROM ( ");
		newSql.append(sql);
		newSql.append("　) temp where ROWNUM <= ").append(max);
		newSql.toString();
		return this.queryForList(newSql.toString(),params);
	}
	
	/**
	 * 
	 * @param sql
	 * @param rowMapper
	 * @return
	 */
	public List queryForList(String sql,RowMapper rowMapper){
		logger.debug(sql);
		return this.getJdbcTemplate().query(sql, rowMapper);
	}
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List queryForList(String sql,Object[] args){
		logger.debug(sql);
		return this.getJdbcTemplate().queryForList(sql, args);
	}
	
	/**
	 * 参数hashMap
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List queryForList(String sql,Map params){
		logger.debug(sql);
		return this.getNamedParameterJdbcTemplate().queryForList(sql, params);
	}
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @return
	 */
	public List queryForList(String sql,Object[] args,RowMapper rowMapper){
		logger.debug(sql);
		return this.getJdbcTemplate().query(sql, args, rowMapper);
	}
	
	/**
	 * 
	 * @param sql
	 * @param paramMap
	 * @param rowMapper
	 * @return
	 */
	public List queryForList(String sql,Map params,RowMapper rowMapper){
		logger.debug(sql);
		return this.getNamedParameterJdbcTemplate().query(sql, params, rowMapper);
	}
	
	/**
	 * 查询分页数据
	 * @param sql
	 * @param paramMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryForPage(String sql,Map paramMap,int pageNo, int pageSize){
		String countSql = OracleSqlGetter.getInstance().getCountSQL(sql);
		int totalCount = this.getNamedParameterJdbcTemplate().queryForInt(countSql, paramMap);
		
		if (totalCount < 1)
			return new Page();
		
		int startIndex = getStartOfPage(pageNo, pageSize);		
		
		String pageSql = OracleSqlGetter.getInstance().getPaginationSQL(sql, startIndex, pageSize);
		logger.debug(pageSql);
		
		List list = this.getNamedParameterJdbcTemplate().queryForList(pageSql, paramMap);
		
		return new Page(list,pageNo,pageSize,totalCount);
	}
	
	private int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	/**
	 * 查询分页数据
	 * @param sql
	 * @param paramMap
	 * @param rowMapper
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryForPage(String sql,Map paramMap,int pageNo, int pageSize,RowMapper rowMapper){
		String countSql = OracleSqlGetter.getInstance().getCountSQL(sql);
		int totalCount = this.getNamedParameterJdbcTemplate().queryForInt(countSql, paramMap);		
		if (totalCount < 1)
			return new Page();
		
		int startIndex = getStartOfPage(pageNo, pageSize);		
		
		String pageSql = OracleSqlGetter.getInstance().getPaginationSQL(sql, startIndex, pageSize);
		logger.debug(pageSql);
		
		List list = this.getNamedParameterJdbcTemplate().query(pageSql, paramMap, rowMapper);
		return new Page(list,pageNo,pageSize,totalCount);
	}	
}

class OracleSqlGetter {
	private static OracleSqlGetter instance = null;

	private final static Logger LOGGER = Logger
			.getLogger(OracleSqlGetter.class);

	public static OracleSqlGetter getInstance() {
		if (instance == null) {
			instance = new OracleSqlGetter();
		}
		return instance;
	}

	private String removeOrderBy(String originalSql) {
		String rsql = originalSql;
		int pos = originalSql.indexOf(" ORDER BY ");

		if (pos != -1) {
			rsql = originalSql.substring(0, pos);
		}

		return rsql;
	}

	public String getCountSQL(String originalSql) {
		StringBuffer sql = new StringBuffer(" SELECT count(*) FROM ( ");
		sql.append(removeOrderBy(originalSql));
		sql.append(" ) ");
		return sql.toString();
	}

	public String getPaginationSQL(String originalSql, int first, int pageSize) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM ( ");
		sql.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		sql.append(originalSql);
		int last = first + pageSize;
		sql.append("　) temp where ROWNUM <= ").append(last);
		sql.append(" ) WHERE　num > ").append(first);
		return sql.toString();
	}
}
