package com.hwua.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hwua.Db.JDBCUtils;
import com.hwua.Db.Tools;
import com.hwua.dao.ISalesRecord;
import com.hwua.pojo.Census;
import com.hwua.pojo.SalesRecord;
import com.hwua.pojo.Tickets;

public class SalesRecordImpl implements ISalesRecord{
	//增加记录
	@Override
	public boolean add(SalesRecord sr) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "insert into sales_record values(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, sr.getS_id());
			ps.setLong(1, sr.getS_id());
			ps.setString(2, sr.getPrice_id());
			ps.setDouble(3, sr.getPrice_price());
			ps.setInt(4, sr.getS_number());
			ps.setInt(5, sr.getS_addnumber());
			ps.setDouble(6, sr.getS_money());
			ps.setString(7, sr.getS_worker());
			ps.setString(8, sr.getS_usestate());
			ps.setString(9, sr.getS_state());
			ps.setTimestamp(10, sr.getS_date());
			int i = ps.executeUpdate();
			if(i>0)flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	//查询全部订单
	@Override
	public List<SalesRecord> querySalesAll() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_addnumber=0";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//分页查询订单
	@Override
	public List<SalesRecord> querySalespage(int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_addnumber=0 limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据订单编号查询数据
	@Override
	public SalesRecord quertSalesById(long id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		SalesRecord sr = new SalesRecord();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_addnumber=0 and s_id=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sr;
	}
	//撤单
	@Override
	public boolean SaleRecord(SalesRecord sr) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "UPDATE sales_record SET s_worker=?,s_state=?,s_date=? where s_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sr.getS_worker());
			ps.setString(2, sr.getS_state());
			ps.setTimestamp(3, sr.getS_date());
			ps.setLong(4, sr.getS_id());
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//查询当前登录工号处理的全部订单
	@Override
	public List<SalesRecord> quertMySalesAll() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_worker=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Tools.username);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询全部订单（分页）
	@Override
	public List<SalesRecord> quertMySalespage(int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_addnumber=0 and s_worker=? limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Tools.username);
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据日期分组统计订单
		@Override
		public List<Census> quertSalespageByDayAll(String w_id) {
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			List<Census> list = new ArrayList<Census>();
			try {
				conn = JDBCUtils.getConn();
				String sql = "select year(s_date),month(s_date),day(s_date),SUM(s_number),SUM(s_money) from sales_record where s_worker=? GROUP BY year(s_date) ,month(s_date),day(s_date)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, w_id);
				rs = ps.executeQuery();
				while(rs.next()) {
					Census cs = new Census();
					cs.setDate(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
					cs.setNum(rs.getInt(4));
					cs.setMoney(rs.getDouble(5));
					list.add(cs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	//根据日期分组统计订单
	@Override
	public List<Census> quertSalespageByDay(String w_id, int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),day(s_date),SUM(s_number),SUM(s_money) from sales_record where s_worker=? GROUP BY year(s_date) ,month(s_date),day(s_date) limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, w_id);
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
				cs.setNum(rs.getInt(4));
				cs.setMoney(rs.getDouble(5));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计某天某账号的订单
	@Override
	public List<Census> censusSalespageAll(String date, String w_id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select price_id,SUM(s_number),SUM(s_money) from sales_record where s_worker=? and s_date like '"+date+"%' GROUP BY price_id";
			ps = conn.prepareStatement(sql);
			ps.setString(1, w_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setType(rs.getString(1));
				cs.setNum(rs.getInt(2));
				cs.setMoney(rs.getDouble(3));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计某天某账号的订单(分页)
	@Override
	public List<Census> censusSalespage(String date, String w_id, int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select price_id,SUM(s_number),SUM(s_money) from sales_record where s_worker=? and s_date like '"+date+"%' GROUP BY price_id limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, w_id);
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setType(rs.getString(1));
				cs.setNum(rs.getInt(2));
				cs.setMoney(rs.getDouble(3));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据工号、订单号、门票分类查询
	@Override
	public List<SalesRecord> adminfindSale(String find) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_id like '%"+find+"%' or price_id like '%"+find+"%' or s_worker like '%"+find+"%'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据工号、订单号、门票分类查询(分页)
	@Override
	public List<SalesRecord> adminfindSalepage(String find, int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_id like '%"+find+"%' or price_id like '%"+find+"%' or s_worker like '%"+find+"%' limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询入库记录
	@Override
	public List<SalesRecord> Putinstorage() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_number=0";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询入库记录(分页)
	@Override
	public List<SalesRecord> Putinstoragepage(int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record where s_number=0 limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询全部订单（入库+出售）
	@Override
	public List<SalesRecord> adminquerySaleAll() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询全部订单（入库+出售）分页
	@Override
	public List<SalesRecord> adminquerySaleAllpage(int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from sales_record limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesRecord sr = new SalesRecord();
				sr.setS_id(rs.getLong(1));
				sr.setPrice_id(rs.getString(2));
				sr.setPrice_price(rs.getDouble(3));
				sr.setS_number(rs.getInt(4));
				sr.setS_addnumber(rs.getInt(5));
				sr.setS_money(rs.getDouble(6));
				sr.setS_worker(rs.getString(7));
				sr.setS_usestate(rs.getString(8));
				sr.setS_state(rs.getString(9));
				sr.setS_date(rs.getTimestamp(10));
				list.add(sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计全部订单
	@Override
	public List<Census> admincensusAll() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),day(s_date),SUM(s_number),SUM(s_money) from sales_record GROUP BY year(s_date) ,month(s_date),day(s_date)";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
				cs.setNum(rs.getInt(4));
				cs.setMoney(rs.getDouble(5));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计全部订单（分页）
	@Override
	public List<Census> admincensusAllpage(int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),day(s_date),SUM(s_number),SUM(s_money) from sales_record GROUP BY year(s_date) ,month(s_date),day(s_date) limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
				cs.setNum(rs.getInt(4));
				cs.setMoney(rs.getDouble(5));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计全部订单（按月）
	@Override
	public List<Census> censusMsalesAllByMonth() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),SUM(s_number),SUM(s_money) from sales_record GROUP BY year(s_date) ,month(s_date)";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2));
				cs.setNum(rs.getInt(3));
				cs.setMoney(rs.getDouble(4));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计全部订单（按月）（分页）
	@Override
	public List<Census> censusMsalesAllByMonthpage(int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),SUM(s_number),SUM(s_money) from sales_record GROUP BY year(s_date) ,month(s_date) limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2));
				cs.setNum(rs.getInt(3));
				cs.setMoney(rs.getDouble(4));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计输入日期的订单
	@Override
	public List<Census> admincensusBydate(String date) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select price_id,SUM(s_number) from sales_record where s_date like '"+date+"%' GROUP BY price_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setType(rs.getString(1));
				cs.setNum(rs.getInt(2));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//统计输入日期的订单(分页)
	@Override
	public List<Census> admincensusBydatepage(String date, int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select price_id,SUM(s_number) from sales_record where s_date like '"+date+"%' GROUP BY price_id limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setType(rs.getString(1));
				cs.setNum(rs.getInt(2));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//按月份统计订单
	@Override
	public List<Census> censalesByWorkermonth(String w_id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),SUM(s_number),SUM(s_money) from sales_record where s_worker=? GROUP BY year(s_date) ,month(s_date)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, w_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2));
				cs.setNum(rs.getInt(3));
				cs.setMoney(rs.getDouble(4));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//按月份统计订单（分页）
	@Override
	public List<Census> censalesByWorkermonthpage(String w_id, int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Census> list = new ArrayList<Census>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select year(s_date),month(s_date),SUM(s_number),SUM(s_money) from sales_record where s_worker=? GROUP BY year(s_date) ,month(s_date) limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, w_id);
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Census cs = new Census();
				cs.setDate(rs.getString(1)+"-"+rs.getString(2));
				cs.setNum(rs.getInt(3));
				cs.setMoney(rs.getDouble(4));
				list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
