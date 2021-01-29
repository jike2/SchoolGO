package com.hwua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.ProcessDao;
import com.hwua.dao.SellrecordsDao;
import com.hwua.pojo.Process;
import com.hwua.pojo.Sellrecords;

@Service("ProcessService")
public class ProcessService implements com.hwua.service.ProcessService{

	@Autowired
	private ProcessDao processdao;
	
	
	@Autowired
	private SellrecordsDao sellrecorddao;
	
	
	@Override
	public boolean addprocess(Process pro) {
		Sellrecords sell = new Sellrecords();
		sell.setSellID(pro.getProsid());
		sell.setSellState("退款中");
		sellrecorddao.upsell(sell);
		pro.setProstate("退款中");
		int i = processdao.addprocess(pro);
		return i>0;
	}


	@Override
	public Process querymyrefundsell(long prosid) {
		Process process = processdao.querymyrefundsell(prosid);
		return process;
	}


	@Override
	public boolean uppro(Process pro) {
		int i = processdao.uppro(pro);
		return i>0;
	}

}
