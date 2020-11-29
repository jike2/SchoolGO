package com.hwua.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hwua.Db.JDBCUtils;
import com.hwua.dao.ITickDao;
import com.hwua.pojo.Tickets;
import com.hwua.pojo.Worker;

public class TickDaoImpl implements ITickDao{
	//查询全部门票
	@Override
	public List<Tickets> queryAll(int page,int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Tickets> list = new ArrayList<Tickets>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from price ORDER BY p_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Tickets Tick = new Tickets();
				Tick.setP_id(rs.getInt(1));
				Tick.setP_type(rs.getString(2));
				Tick.setP_price(rs.getDouble(3));
				Tick.setP_number(rs.getInt(4));
				list.add(Tick);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//门票出售后更新门票数量
	@Override
	public boolean saleTick(int p_id, int number) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Tickets> list = new ArrayList<Tickets>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "update price set p_number=? where p_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, number);
			ps.setInt(2, p_id);
			int i = ps.executeUpdate();
			if(i>0)flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}
	//根据编号查询门票信息
	@Override
	public Tickets query(int p_id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Tickets Tick = new Tickets();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from price where p_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Tick.setP_id(rs.getInt(1));
				Tick.setP_type(rs.getString(2));
				Tick.setP_price(rs.getDouble(3));
				Tick.setP_number(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tick;
	}
	//根据门票分类查询门票信息
	@Override
	public Tickets queryByName(String p_type) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Tickets Tick = new Tickets();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from price where p_type=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, p_type);
			rs = ps.executeQuery();
			while(rs.next()) {
				Tick.setP_id(rs.getInt(1));
				Tick.setP_type(rs.getString(2));
				Tick.setP_price(rs.getDouble(3));
				Tick.setP_number(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tick;
	}
	//修改门票信息
	@Override
	public boolean uptick(Tickets tk) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Tickets> list = new ArrayList<Tickets>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "update price set p_type=?,p_price=?,p_number=? where p_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tk.getP_type());
			ps.setDouble(2, tk.getP_price());
			ps.setInt(3, tk.getP_number());
			ps.setInt(4, tk.getP_id());
			int i = ps.executeUpdate();
			if(i>0)flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}
	//删除门票
	@Override
	public boolean deltick(int p_id) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Tickets> list = new ArrayList<Tickets>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "delete from price where p_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,p_id);
			int i = ps.executeUpdate();
			if(i>0)flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}
	//添加门票
	@Override
	public boolean addtick(Tickets tk) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Tickets> list = new ArrayList<Tickets>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "insert into price(p_type,p_price,p_number) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tk.getP_type());
			ps.setDouble(2, tk.getP_price());
			ps.setInt(3, tk.getP_number());
			int i = ps.executeUpdate();
			if(i>0)flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}
	
}
