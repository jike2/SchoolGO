package com.hwua.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hwua.pojo.Goods;
import com.hwua.pojo.Process;
import com.hwua.pojo.User;
public interface ProcessService {

	public boolean addprocess(Process pro);
	
	public Process querymyrefundsell(long prosid);
	
	public boolean uppro(Process pro);
}
