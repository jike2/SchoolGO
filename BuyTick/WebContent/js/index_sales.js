	//查询全部订单
	function querySaleAll(bt) {
		var userid = u_storage.get("userid");
		if (userid == undefined){
			alert("您还未登录，请登录")
			$(location).attr("href", "login.html");
			}else{
				$("body").removeClass("hid")
			};
		var num = parseInt($("#re").val());
		var num1 = parseInt($("#ne").val());
		if(bt==0 && num==1){
			alert("已经到第一页了")
			//$("#re").addClass("disabled");
		}else if(bt==-1 && num==num1){
			alert("已经到最后一页了")
			//$("#ne").addClass("disabled");
		}else if(bt==0 && num!=1){
			$("#re").val(num-1);
		}else if(bt==-1 && num!=num1){
			$("#re").val(num+1);
		}else{
			$("#re").val(bt);
		}
		var page=parseInt($("#re").val());
		$.ajax({
					url : "http://localhost:8080/BuyTick/SalesRecordServlet.do?method=querySaleAll",
					dataType : "json",
					type : "post",
					data:{
						page:$("#re").val()
					},
					//post方式请求后台的格式设置
					contentType : "application/x-www-form-urlencoded",
					beforeSend : function(request) {
						request.setRequestHeader("userid", userid);
					},
					async : false,
					xhrFields : {
						withCredentials : true
					},
					//是否需要缓冲
					cache : false,
					//成功的回调函数
					success : function(data) {
						//console.log(data)
						//alert("请求成功" + data.student2.name)
						if (data.flag) {
							alert("您还未登录，请登录")
							$(location).attr("href", "login.html");
						};
						var ul = "";
						ul += '<li id="re" value="'+page+'"><a href="javascript:querySaleAll(0);">&laquo;</a></li>';
						for(var j=1;j<data.count+1;j++){
							if(j==page){
							ul += '<li id="pag'+j+'" class="active"><a href="javascript:querySaleAll('+j+');">'+j+'</a></li>';
							}else{
								ul += '<li id="pag'+j+'"><a href="javascript:querySaleAll('+j+');">'+j+'</a></li>';
							}
							};
						ul += '<li id="ne" value="'+data.count+'"><a href="javascript:querySaleAll(-1);">&raquo;</a></li>';
						$("#pag").html(ul);
						var list = data.data;
						
						var tbody = "";
						console.log(list);
						for (var i = 0; i < 10; i++) {
							if(list[i] == undefined){
								tbody += '<tr style="height:51px"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
							}else{
							var unixTimestamp = new Date(list[i].s_date);
							commonTime = unixTimestamp.toLocaleString();
							if (list[i].s_state == '已处理'
									&& list[i].s_usestate == '未使用') {
								tbody += '<tr><td>'
										+ list[i].s_id
										+ '</td><td>'
										+ list[i].price_id
										+ '</td><td>'
										+ list[i].price_price
										+ '</td><td>'
										+ list[i].s_number
										+ '</td><td>'
										+ list[i].s_money
										+ '</td><td>'
										+ list[i].s_worker
										+ '</td><td>'
										+ list[i].s_usestate
										+ '</td><td>'
										+ commonTime
										+ '</td><td><button class="btnflo btn btn-success"  data-toggle="modal" data-target="#saleModal" onclick="saleModal('+i+')">立即撤单</button></td></tr>';
							} else {
								tbody += '<tr><td>'
										+ list[i].s_id
										+ '</td><td>'
										+ list[i].price_id
										+ '</td><td>'
										+ list[i].price_price
										+ '</td><td>'
										+ list[i].s_number
										+ '</td><td>'
										+ list[i].s_money
										+ '</td><td>'
										+ list[i].s_worker
										+ '</td><td>'
										+ list[i].s_usestate
										+ '</td><td>'
										+ commonTime
										+ '</td><td><button class="btnflo btn btn-success" style="background-color: #9e9e9e">不可撤单</button></td></tr>';
							}
						}
						}
						$("#tb").html(tbody);
					},
					//报错的回调函数
					error : function(request) {
						alert("服务器加载中......")
					}
				})
	}
	
	//根据订单编号查询
	function findSaleByID(bt) {
		var userid = u_storage.get("userid");
		if (userid == undefined){
			alert("您还未登录，请登录")
			$(location).attr("href", "login.html");
			}else{
				$("body").removeClass("hid")
			};
		var num = parseInt($("#re").val());
		var num1 = parseInt($("#ne").val());
		if (bt == 0 && num == 1) {
			alert("已经到第一页了")
			//$("#re").addClass("disabled");
		} else if (bt == -1 && num == num1) {
			alert("已经到最后一页了")
			//$("#ne").addClass("disabled");
		} else if (bt == 0 && num != 1) {
			$("#re").val(num - 1);
		} else if (bt == -1 && num != num1) {
			$("#re").val(num + 1);
		} else {
			$("#re").val(bt);
		}
		var page = parseInt($("#re").val());
		$.ajax({
					//请求的路径
					url : "http://localhost:8080/BuyTick/SalesRecordServlet.do?method=querySaleByID",
					//传输数据的类型
					dataType : "json",
					data : {
						findById : $("#inputid").val()
					},
					//get请求的方式
					//type : "GET",
					//post请求的方式
					type : "post",
					//get方式请求后台的格式设置
					//contentType : "application/json;charset=UTF-8",
					//post方式请求后台的格式设置
					contentType : "application/x-www-form-urlencoded",
					beforeSend : function(request) {
						request.setRequestHeader("userid", userid);
					},
					async : false,
					xhrFields : {
						withCredentials : true
					},
					//是否需要缓冲
					cache : false,
					//成功的回调函数
					success : function(data) {
						console.log(data)
						//alert("请求成功" + data.student2.name)
						if (data.flag) {
							alert("您还未登录，请登录")
							$(location).attr("href", "login.html");
						}
						;
						//渲染分页按钮
						var ul = "";
						ul += '<li id="re" value="'+page+'"><a href="javascript:findSaleByID(0);">&laquo;</a></li>';
						for (var j = 1; j < data.count + 1; j++) {
							if (j == page) {
								ul += '<li id="pag'+j+'" class="active"><a href="javascript:findSaleByID('
										+ j + ');">' + j + '</a></li>';
							} else {
								ul += '<li id="pag'+j+'"><a href="javascript:findSaleByID('
										+ j + ');">' + j + '</a></li>';
							}
						}
						;
						ul += '<li id="ne" value="'+data.count+'"><a href="javascript:findSaleByID(-1);">&raquo;</a></li>';
						$("#pag").html(ul);

						//渲染查询的数据
						var list = data.data;
						if (list.s_id != $("#inputid").val()) {
							alert("无此订单信息")
							$(location).attr("href", "index_sales.html");
						} else {
							var tbody = "";
							console.log(list);
							for (var i = 0; i < 10; i++) {
								if (i != 0) {
									tbody += '<tr style="height:51px"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
								} else {
									var unixTimestamp = new Date(list.s_date);
									commonTime = unixTimestamp.toLocaleString();
									if (list.s_state == '已处理'
											&& list.s_usestate == '未使用') {
										tbody += '<tr><td>'
												+ list.s_id
												+ '</td><td>'
												+ list.price_id
												+ '</td><td>'
												+ list.price_price
												+ '</td><td>'
												+ list.s_number
												+ '</td><td>'
												+ list.s_money
												+ '</td><td>'
												+ list.s_worker
												+ '</td><td>'
												+ list.s_usestate
												+ '</td><td>'
												+ commonTime
												+ '</td><td><button class="btnflo btn btn-success"  data-toggle="modal" data-target="#saleModal" onclick="saleModal('
												+ i
												+ ')">立即撤单</button></td></tr>';
									} else {
										tbody += '<tr><td>'
												+ list.s_id
												+ '</td><td>'
												+ list.price_id
												+ '</td><td>'
												+ list.price_price
												+ '</td><td>'
												+ list.s_number
												+ '</td><td>'
												+ list.s_money
												+ '</td><td>'
												+ list.s_worker
												+ '</td><td>'
												+ list.s_usestate
												+ '</td><td>'
												+ commonTime
												+ '</td><td><button class="btnflo btn btn-success" style="background-color: #9e9e9e">不可撤单</button></td></tr>';
									}
								}
							}
						}
						$("#tb").html(tbody);
					},
					//报错的回调函数
					error : function(request) {
						alert("服务器加载中......")
					}
				})
	}
	//将撤单数据打印到模态框
	function saleModal(i) {
		var s_id = document.getElementById("tb").rows[i].cells[0].innerText;
		var price_id = document.getElementById("tb").rows[i].cells[1].innerText;
		var price_price = document.getElementById("tb").rows[i].cells[2].innerText;
		var s_number = document.getElementById("tb").rows[i].cells[3].innerText;
		var s_money = document.getElementById("tb").rows[i].cells[4].innerText;
		var s_worker = document.getElementById("tb").rows[i].cells[5].innerText;
		var s_usestate = document.getElementById("tb").rows[i].cells[6].innerText;
		var commonTime = document.getElementById("tb").rows[i].cells[7].innerText;
		var tbody = "";
		tbody += '<tr><th id="salessid">' + s_id + '</th><th>' + price_id + '</th><th>' + price_price
				+ '</th><th>' + s_number + '</th><th>' + s_money + '</th><th>' + s_worker
				+ '</th><th>' + s_usestate + '</th><th>' + commonTime + '</th></tr>';
		$("#saletb").html(tbody);
	}
	//撤单
	function saleRecord() {
		var userid = u_storage.get("userid");
		if (userid == undefined){
			alert("您还未登录，请登录")
			$(location).attr("href", "login.html");
			}else{
				$("body").removeClass("hid")
			};
		$.ajax({
			//请求的路径
			url : "http://localhost:8080/BuyTick/SalesRecordServlet.do?method=salesRecordByID",
			//传输数据的类型
			dataType : "json",
			data : {
				salesID : $("#salessid").text()
			},
			//get请求的方式
			//type : "GET",
			//post请求的方式
			type : "post",
			//get方式请求后台的格式设置
			//contentType : "application/json;charset=UTF-8",
			//post方式请求后台的格式设置
			contentType : "application/x-www-form-urlencoded",
			beforeSend : function(request) {
				request.setRequestHeader("userid", userid);
			},
			async : false,
			xhrFields : {
				withCredentials : true
			},
			//是否需要缓冲
			cache : false,
			//成功的回调函数
			success : function(data) {
				console.log(data)
				//alert("请求成功" + data.student2.name)
				if (data.flag) {
					alert("您还未登录，请登录")
					$(location).attr("href", "login.html");
				};
				if(data == true){
					alert("撤单成功")
					$(location).attr("href", "index_sales.html");
				}else{
					alert("撤单失败")
					$(location).attr("href", "index_sales.html");
				}
				
			},
			//报错的回调函数
			error : function(request) {
				alert("服务器加载中......")
			}
		})
	}
	//查询该登录工号处理的订单
	function mysales(bt) {
		alert("mysales")
		var userid = u_storage.get("userid");
		if (userid == undefined){
			alert("您还未登录，请登录")
			$(location).attr("href", "login.html");
			}else{
				$("body").removeClass("hid")
			};
		var num = parseInt($("#re").val());
		var num1 = parseInt($("#ne").val());
		if (bt == 0 && num == 1) {
			alert("已经到第一页了")
			//$("#re").addClass("disabled");
		} else if (bt == -1 && num == num1) {
			alert("已经到最后一页了")
			//$("#ne").addClass("disabled");
		} else if (bt == 0 && num != 1) {
			$("#re").val(num - 1);
		} else if (bt == -1 && num != num1) {
			$("#re").val(num + 1);
		} else {
			$("#re").val(bt);
		}
		var page = parseInt($("#re").val());
		alert(page)
		$.ajax({
					//请求的路径
					url : "http://localhost:8080/BuyTick/SalesRecordServlet.do?method=mysales",
					//传输数据的类型
					dataType : "json",
					data:{
						page:$("#re").val()
					},
					//get请求的方式
					//type : "GET",
					//post请求的方式
					type : "post",
					//get方式请求后台的格式设置
					//contentType : "application/json;charset=UTF-8",
					//post方式请求后台的格式设置
					contentType : "application/x-www-form-urlencoded",
					beforeSend : function(request) {
						request.setRequestHeader("userid", userid);
					},
					async : false,
					xhrFields : {
						withCredentials : true
					},
					//是否需要缓冲
					cache : false,
					//成功的回调函数
					success : function(data) {
						console.log(data)
						//alert("请求成功" + data.student2.name)
						if (data.flag) {
							alert("您还未登录，请登录")
							$(location).attr("href", "login.html");
						}
						;
						//渲染分页按钮
						var ul = "";
						ul += '<li id="re" value="'+page+'"><a href="javascript:findSaleByID(0);">&laquo;</a></li>';
						for (var j = 1; j < data.count + 1; j++) {
							if (j == page) {
								ul += '<li id="pag'+j+'" class="active"><a href="javascript:findSaleByID('
										+ j + ');">' + j + '</a></li>';
							} else {
								ul += '<li id="pag'+j+'"><a href="javascript:findSaleByID('
										+ j + ');">' + j + '</a></li>';
							}
						}
						;
						ul += '<li id="ne" value="'+data.count+'"><a href="javascript:findSaleByID(-1);">&raquo;</a></li>';
						$("#pag").html(ul);

						//渲染查询的数据
						var list = data.data;
						if (list.s_id != $("#inputid").val()) {
							alert("无此订单信息")
							$(location).attr("href", "index_sales.html");
						} else {
							var tbody = "";
							console.log(list);
							for (var i = 0; i < 10; i++) {
								if (i != 0) {
									tbody += '<tr style="height:51px"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
								} else {
									var unixTimestamp = new Date(list.s_date);
									commonTime = unixTimestamp.toLocaleString();
									if (list.s_state == '已处理'
											&& list.s_usestate == '未使用') {
										tbody += '<tr><td>'
												+ list.s_id
												+ '</td><td>'
												+ list.price_id
												+ '</td><td>'
												+ list.price_price
												+ '</td><td>'
												+ list.s_number
												+ '</td><td>'
												+ list.s_money
												+ '</td><td>'
												+ list.s_worker
												+ '</td><td>'
												+ list.s_usestate
												+ '</td><td>'
												+ commonTime
												+ '</td><td><button class="btnflo btn btn-success"  data-toggle="modal" data-target="#saleModal" onclick="saleModal('
												+ i
												+ ')">立即撤单</button></td></tr>';
									} else {
										tbody += '<tr><td>'
												+ list.s_id
												+ '</td><td>'
												+ list.price_id
												+ '</td><td>'
												+ list.price_price
												+ '</td><td>'
												+ list.s_number
												+ '</td><td>'
												+ list.s_money
												+ '</td><td>'
												+ list.s_worker
												+ '</td><td>'
												+ list.s_usestate
												+ '</td><td>'
												+ commonTime
												+ '</td><td><button class="btnflo btn btn-success" style="background-color: red">不可撤单</button></td></tr>';
									}
								}
							}
						}
						$("#tb").html(tbody);
					},
					//报错的回调函数
					error : function(request) {
						alert("服务器加载中......")
					}
				})
	}