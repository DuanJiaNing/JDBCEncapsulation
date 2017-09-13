package com.yccz.jdbcencapsulation.db;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.yccz.jdbcencapsulation.FieldToken;
import com.yccz.jdbcencapsulation.bean.Commodity;

/**
 * ���ݿ⹤����
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DBDepotController {

	private static final String DATABASE = "depot";
	private static final String TABLE_MONITOR = "monitor";
	private static final String TABLE_MONITOR_DETAIL = "monitor_detail";
	
	private Connection conn = null;
	
	public DBDepotController() {
		init();
	}
	
	// ������ݿ�����
	private void init() {
		try {
			conn = DBHelper.getInstance().getConnection(DATABASE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <T extends Commodity> T[] select(Class<T> clasz) {
		String sql = "select * from ";
		return select(clasz,sql);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Commodity> T[] select(Class<T> clasz,String sql) {

		PreparedStatement stat = null;
		ResultSet set = null;
		List<T> result = null;
		
		try {
			// ʹ������ɹ����������ȡ�������С
			stat = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			set = stat.executeQuery();
			
			// ��ȡ��С
			int size = getResultSetSize(set);
			if (size <= 0) {
				return null;
			}
			
			// ��ȡӳ���ϵ
			FieldToken<T> token = new FieldToken<>(clasz);
			List<FieldToken.FieldHolder> fh = token.getFieldHolder();
			result = new ArrayList<>();
			
			// ���÷��佫��ѯ�������Ŀ������
			while (set.next()) {
				T item = clasz.newInstance();
				for (FieldToken.FieldHolder h : fh) {
					Field field = h.field;
					String label = h.name;
					Class<?> type = field.getClass();
					
					Object value = set.getObject(label, type);
					field.setAccessible(true);
					field.set(item, value);
				}
				result.add(item);
			}
			
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			Utils.closeAutoCloseable(set);
			Utils.closeAutoCloseable(stat);
		}
		
		return (T[]) result.toArray();
	}
	
	public <T extends Commodity> T[] select(Class<T> clasz,String[] whereCase,String[] whereValues, String editTable){
		String where = constructConditions(whereCase,whereValues);
		String sql = "select * from "+ editTable+" "+ where;
		return select(clasz,sql);
	}
	
	// ��ȡ�������С������ǰ��Ϊ prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
	private int getResultSetSize(ResultSet set) throws SQLException {
		int size = -1;
		if (set != null) {
			set.last();
			size = set.getRow();
			set.beforeFirst();
		}
		return size;
	}

	private String constructConditions(String[] whereCase, String[] whereValues) {
		if (whereCase == null || whereCase.length == 0 || whereValues == null || whereValues.length == 0) {
			return "";
		}
		
		return null;
	}
	
}
