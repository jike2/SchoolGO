//管理员查询全部门票
	function adminqueryAll() {
		var userid = u_storage.get("userid");
		if (userid == undefined)
			userid = "";
		$.ajax({
					//请求的路径
					url : "http://localhost:8080/BuyTick/TicketServlet.do?method=queryTickAll",
					//传输数据的类型
					dataType : "json",
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
						var list = data.data;
						var tbody = "";
						for (var i = 0; i < 10; i++) {
							if (list[i] == undefined) {
								tbody += '<tr><td style="height:51px"></td><td></td><td></td><td></td><td></td></tr>';
							} else {
								tbody += '<tr><td>'
										+ list[i].p_id
										+ '</td><td>'
										+ list[i].p_type
										+ '</td><td>'
										+ list[i].p_price
										+ '</td><td>'
										+ list[i].p_number
										+ '</td><td><button class="btn btn-success" data-toggle="modal" data-target="#myModal" onclick="addmytext('
										+ i
										+ ')">入库</button>&nbsp<button class="btn btn-success" data-toggle="modal" data-target="#deltype" onclick="addmytext('
										+ i
										+ ')">删除</button>&nbsp<button class="btn btn-success" data-toggle="modal" data-target="#uptype" onclick="addmytext('
										+ i + ')">修改</button></td></tr>';
							}
						}
						$("#tb").html(tbody);
						//js
						//window.location.href="aaa.jsp";
						//1、jq跳转页面
						//$(location).attr("href","aaa.jsp");
						//2、
						//$(window).attr("location","aaa.jsp");
						//3、
						//$(location).prop("href","aaa.jsp")
					},
					//报错的回调函数
					error : function(request) {
						alert("服务器加载中......")
					}
				})
	}
	//管理员根据id查询
	function adminfindByID() {
		var userid = u_storage.get("userid");
		if (userid == undefined)
			userid = "";
		$
				.ajax({
					//请求的路径
					url : "http://localhost:8080/BuyTick/TicketServlet.do?method=queryTickByID",
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

						var tbody = "";
						if (data.p_id != $("#inputid").val()) {
							alert("无此门票信息")
							$(location).attr("href", "index.html");
						} else {
							for (var i = 0; i < 10; i++) {
								if (i != 0) {
									tbody += '<tr><td style="height:51px"></td><td></td><td></td><td></td><td></td></tr>';
								} else {
									tbody += '<tr><td>'
											+ data.p_id
											+ '</td><td>'
											+ data.p_type
											+ '</td><td>'
											+ data.p_price
											+ '</td><td>'
											+ data.p_number
											+ '</td><td><button class="btn btn-success" data-toggle="modal" data-target="#myModal" onclick="addmytext('
											+ i
											+ ')">入库</button>&nbsp<button class="btn btn-success" data-toggle="modal" data-target="#deltype" onclick="addmytext('
											+ i
											+ ')">删除</button>&nbsp<button class="btn btn-success" data-toggle="modal" data-target="#uptype" onclick="addmytext('
											+ i + ')">修改</button></td></tr>';
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
	//将数据打印到模态框
	function addmytext(i) {
		var id = document.getElementById("tb").rows[i].cells[0].innerText;
		var type = document.getElementById("tb").rows[i].cells[1].innerText;
		var price = document.getElementById("tb").rows[i].cells[2].innerText;
		var num = document.getElementById("tb").rows[i].cells[3].innerText;
		$("input[name='p_id']").val(id);
		$("input[name='p_type']").val(type);
		$("input[name='p_price']").val(price);
		$("#delp_num").val(num);
		$("#upp_num").val(num);
	}
	
	//删除
	function delTick() {
		var userid = u_storage.get("userid");
		if (userid == undefined)
			userid = "";
		$.ajax({
					//请求的路径
					url : "http://localhost:8080/BuyTick/TicketServlet.do?method=deltick",
					//传输数据的类型
					dataType : "json",
					data : {
						p_id : $("#delp_id").val(),
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
						if (data == true) {
							alert("删除成功")
							$(location).attr("href", "adminindex.html");
						} else {
							alert("删除失败")
						}

					},
					//报错的回调函数
					error : function(request) {
						alert("服务器加载中......")
					}
				})
	}