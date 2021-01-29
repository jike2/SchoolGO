package com.hwua.dao;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Process;

public interface ProcessDao {

	
	public int addprocess(Process pro);
	
	public Process querymyrefundsell(@Param("prosid")long prosid);
	
	public int uppro(Process pro);
}
