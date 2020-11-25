u_storage = {};

/**
 * 获取本地存储对象（兼容安卓）
 */
function uzStorage() {
	var ls = window.sessionStorage;
	var isAndroid = (/android/gi).test(window.navigator.appVersion);
	if (isAndroid) {
		ls = os.localStorage();
	}
	return ls;
}

/**
 * 存储数据（对象）到本地储存
 * @param {Object} key
 * @param {Object} value
 */
u_storage.set = function(key, value) {
	
	debugger
	var v = value;
	if (typeof v === 'object') {
		v = JSON.stringify(v);
		v = 'obj-' + v;
	}
	var ls = uzStorage();
	if (ls) {
		ls.setItem(key, v);
	}
}

/**
 * 根据名字获取本地储存中的数据
 * @param {Object} key
 */
u_storage.get = function(key) {
	var ls = uzStorage();
	if (ls) {
		var v = ls.getItem(key);
		if (!v) return;
		if (v.indexOf('obj-') === 0) {
			return JSON.parse(v.slice(4));
		}else{
			return v;
		}
	}
}

/**
 * 删除本地储存中的某些数据
 * @param {Object} key
 */
u_storage.remove = function(key){
	var ls = uzStorage();
	if(is && key) ls.removeItem(key);
}


/**
 * 清空本地储存所有数据
 */
u_storage.clear = function(){
	var ls = uzStorage();
	if(is) ls.clear();
}

