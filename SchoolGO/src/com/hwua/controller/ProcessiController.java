package com.hwua.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Process;
import com.hwua.pojo.Sellrecords;
import com.hwua.service.ProcessService;
import com.hwua.service.SellrecordsService;
import com.hwua.util.AlipayConfig;

@CrossOrigin
@Controller
public class ProcessiController {

	@Autowired 
	private ProcessService processservice;

	@Autowired 
	private SellrecordsService sellrecordsService;

	@RequestMapping(value = "/refunedmoney",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String refunedmoney(@RequestParam("file") MultipartFile[] file,Process pro,HttpServletRequest request)
			throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		String img=null;
		//循环下载图片
		Sellrecords sellrecords = sellrecordsService.querymysellbysellid(pro.getProsid());
		if(sellrecords.getSellState().equals("待收货")||sellrecords.getSellState().equals("待发货")) {
			for(int i=0;i<file.length;i++) {
				Date date = new Date();
				long time = date.getTime();
				String imgs=time+".jpg";
				if(i==0) {
					img=imgs;
				}else {
					img=img+","+imgs;
				}
				//			String newFileName = UUID.randomUUID() + file[i].getOriginalFilename();
				file[i].transferTo(new File("E:\\2020年实训\\毕业设计\\校园GO电子商城\\web\\images",imgs));
			}
			pro.setProimg(img);
			boolean addprocess = processservice.addprocess(pro);
			map.put("flag", true);
			map.put("msg","提交成功，等待商家审核");
		}else if(sellrecords.getSellState().equals("待付款")) {
			map.put("flag", false);
			map.put("msg","该商品未付款，无法申请退款");
		}else  if(sellrecords.getSellState().equals("已完成")){
			map.put("flag", false);
			map.put("msg","该订单已结束，无发申请退款");
		}else  if(pro.getProrefunmoney()>sellrecords.getSellMoney()){
			map.put("flag", false);
			map.put("msg","申请退款金额不得大于购买金额");
		}

		return JSON.toJSONString(map);
	}
	@RequestMapping(value = "/querymyrefundsell",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymyrefundsell(long prosid){
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("请求编号"+prosid);
		Process process = processservice.querymyrefundsell(prosid);

		return JSON.toJSONString(process);
	}
	@RequestMapping(value = "/disposerefund",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String disposerefund(long prosid,String mychose,int chose){
		System.out.println(prosid+"============="+chose);
		Map<String,Object> map = new HashMap<String, Object>();
		Process process = processservice.querymyrefundsell(prosid);
		Sellrecords sellrecords = sellrecordsService.querymysellbysellid(prosid);
		if(chose==1) {
			Sellrecords sell = new Sellrecords();
			sell.setSellID(prosid);
			sell.setSellState("拒接退款");
			sellrecordsService.upsell(sell);
			Process pro=new Process();
			pro.setProsid(prosid);
			pro.setProstate("拒绝退款");
			pro.setProrefused(mychose);
			processservice.uppro(pro);
		}else {
			
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
			//设置请求参数
			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			 //商户订单号，必填
			 String out_trade_no = String.valueOf(sellrecords.getSellalipayid());
			 //需要退款的金额，该金额不能大于订单金额，必填
			 String refund_amount = String.valueOf(process.getProrefunmoney());
			 //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
			 String out_request_no = new String(UUID.randomUUID().toString());
			 alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
					   + "\"refund_amount\":\""+ refund_amount +"\","
					   + "\"out_request_no\":\""+ out_request_no +"\"}");
			AlipayTradeRefundResponse response = null;
			try {
				response = alipayClient.execute(alipayRequest);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
			if(response.isSuccess()){
			process.setProstate("已退款");
			processservice.uppro(process);
			Sellrecords sell = new Sellrecords();
			sell.setSellID(sellrecords.getSellID());
			sell.setSellState("已完成");
			sellrecordsService.upsell(sell);
			map.put("flag", true);
			map.put("msg", "退款成功");
			} else {
			map.put("flag", false);
			map.put("msg", "退款失败");
			}
		}
		return JSON.toJSONString(map);
	}
}
