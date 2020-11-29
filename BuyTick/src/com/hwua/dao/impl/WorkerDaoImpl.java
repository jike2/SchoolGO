package com.hwua.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hwua.Db.JDBCUtils;
import com.hwua.Db.Tools;
import com.hwua.dao.IWorkerDao;
import com.hwua.pojo.Worker;


public class WorkerDaoImpl implements IWorkerDao{
	//登录功能
	@Override
	public Worker login(String username) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Worker w = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from worker where w_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()) {
				w = new Worker();
				w.setW_name(rs.getString(1));
				w.setW_id(rs.getString(2));
				w.setW_pwd(rs.getString(3));
				w.setW_position(rs.getString(4));
				w.setW_permissions(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return w;
	}
	//修改自己的密码
	@Override
	public boolean upWorker(Worker worker) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Worker w = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "update worker set w_pwd=?,w_name=?,w_position=? where w_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, worker.getW_pwd());
			ps.setString(2, worker.getW_name());
			ps.setString(3, worker.getW_position());
			ps.setString(4, worker.getW_id());
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//查询全部员工
	@Override
	public List<Worker> quertWorkerAll(int permissions) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Worker> list = new ArrayList<Worker>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from worker where w_permissions<?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, permissions);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Worker w = new Worker();
				w.setW_name(rs.getString(1));
				w.setW_id(rs.getString(2));
				w.setW_pwd("******");
				w.setW_position(rs.getString(4));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询全部员工（分页）
	@Override
	public List<Worker> quertWorkerpage(int permissions,int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Worker> list = new ArrayList<Worker>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from worker where w_permissions<? limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, permissions);
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Worker w = new Worker();
				w.setW_name(rs.getString(1));
				w.setW_id(rs.getString(2));
				w.setW_pwd("******");
				w.setW_position(rs.getString(4));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据工号或姓名查询员工
	@Override
	public List<Worker> findWorkByID(String find) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Worker> list = new ArrayList<Worker>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from worker where w_name like '%"+find+"%' or w_id like '%"+find+"%'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Worker w = new Worker();
				w.setW_name(rs.getString(1));
				w.setW_id(rs.getString(2));
				w.setW_pwd("******");
				w.setW_position(rs.getString(4));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据工号或姓名查询员工(分页)
	@Override
	public List<Worker> findWorkByIDpage(String find, int page, int limit) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Worker> list = new ArrayList<Worker>();
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from worker where w_name like '%"+find+"%' or w_id like '%"+find+"%' limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()) {
				Worker w = new Worker();
				w.setW_name(rs.getString(1));
				w.setW_id(rs.getString(2));
				w.setW_pwd("******");
				w.setW_position(rs.getString(4));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据工号查询员工
	@Override
	public Worker findWorkOne(String find) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Worker w = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "select *from worker where w_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, find);
			rs = ps.executeQuery();
			while(rs.next()) {
				w = new Worker();
				w.setW_name(rs.getString(1));
				w.setW_id(rs.getString(2));
				w.setW_pwd("******");
				w.setW_position(rs.getString(4));
				w.setW_permissions(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return w;
	}
	//删除员工
	@Override
	public boolean delWorker(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Worker w = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "delete from worker where w_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//添加员工
	@Override
	public boolean addWorker(Worker worker) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Worker w = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "insert into worker values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, worker.getW_name());
			ps.setString(2, worker.getW_id());
			ps.setString(3, worker.getW_pwd());
			ps.setString(4, worker.getW_position());
			ps.setInt(5, worker.getW_permissions());
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
