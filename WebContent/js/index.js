$().ready(function() {
	$('.filter').hide();  //令所有筛选条件隐藏
	getAllInfo();    //获取所有房子信息
	getNoticeNum();  //获取消息数量
});


toPage = "1"; //声明一个全局变量，用于保存当前的页数
var filterChooser; //用于保存当前选中的筛选方式对象
var sorterTag; //用于保存当前选中的排序对象，默认初始化排序方式为timeDown

	
/*以下为页面初始化即请求的内容，包括请求所有房源信息和消息数量*/
// 异步请求后台第一页的所有数据
function getAllInfo() {
	getHouseByNoFilter("1","timeDown");
}

//不使用筛选来获取房源,但是使用排序
function getHouseByNoFilter(toPage,sortWay) {
	$.ajax({
		// url: 'data/house.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all",
			"toPage":toPage,
			"filter":"none",
			"sort":sortWay
		}),
	})
	.done(function(obj) {
		// 封装当获取首页客房信息时，返回的响应样例，并对html页面进行渲染的函数
		renderPage(obj);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}


// 异步请求消息数量
function getNoticeNum() {
	$.ajax({
		// url: 'data/notice.json',
		url: './notice',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"count",
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			$("#countNotice").append("("+obj.data+")");
		}else{
			alert("消息数量获取失败原因："+obj.reason);
		}
	})
	.fail(function() {
		alert("消息数量获取失败");
	})
	.always(function() {
		// console.log("complete");
	});
}
/*以上为页面初始化即请求的内容，包括请求所有房源信息和消息数量*/


/*公用的函数，以下为对返回的房源信息进行渲染的封装*/
// 封装当获取首页客房信息时，返回的响应样例，并对html页面进行渲染的函数
function renderPage(obj) {
	toPage = obj.nowPage;  //保存当前页号,字符串形式
	$("#houseInfo").children().remove();  // 移除原来的房源信息
	if(obj.ret == 'true'){
		// 插入所返回的房源信息
		$.each(obj.data, function(index, val) {
			var link = "<li><a href='houseDetail.html?id="+val.id+"' target='_blank'>";
			var pic = "<div class='housePic'><img src='"+val.pic[0]+"' alt='被出租的房屋图片''></div>";
			var text = "<div class='aboutText'><div class='text'><p class='contentTitle'>"+val.title+"</p><p class='contentComment'>"+val.comment+"</p>";
			var address = "<p class='city'><span><img src='images/address.png'></span><span>"+val.province+">"+val.city+">"+val.region+"</span></p><p class='adress'>详细地址：<span>"+val.address+"</span></p></div>";
			var other = "<div class='otherInfo'><p class='price'>月租：<span>"+val.price+"/月</span></p><p class='count'>剩余套间：<span>"+val.count+"</span></p></div></div></a></li>";
			$("#houseInfo").append(link+pic+text+address+other);
		});
		// 修改切换页面的按钮
		$('#switchPage').children().remove();
		var page = parseInt(obj.nowPage);
		var pageText = "";
		// 首先判断是否插入上一页按钮
		if(obj.nowPage != '1'){
			pageText += "<span class='page' id='lastPage'>上一页</span>";
		}
		pageText += "<span class='page' id='choosePage'>"+page+"</span>";
		for(var i = page+1;i < (page+3);i++){
			if(i<parseInt(obj.sumPage)){
				pageText += "<span class='page'>"+i+"</span>";
			}else{
				break;
			}
		}
		pageText += "<span class='page' id='morePage'>...</span><span class='page'>"+parseInt(obj.sumPage)+"</span>";
		if(page != (parseInt(obj.sumPage) - 1) && page != parseInt(obj.sumPage)){
			pageText += "<span class='page' id='morePage'>...</span><span class='page'>"+parseInt(obj.sumPage)+"</span>";
		}else{
			if(page != parseInt(obj.sumPage)){
				pageText += "<span class='page'>"+parseInt(obj.sumPage)+"</span>";
			}
		}
		// 当前页不是最后一页时，插入下一页按钮
		if(page != parseInt(obj.sumPage)){
			pageText += "<span class='page' id='nextPage'>下一页</span>";
		}
		$("#switchPage").append(pageText);
		//给页面按钮绑定事件
		/*以下为分页按钮切换内容*/
		$('#switchPage').find('.page').click(function(event) {
			var clickPage;
			// 判断选中的分页按钮是哪个
			if($(this).is('#lastPage')){        // 点击上一页
				clickPage = parseInt(toPage) - 1;
				clickPage += '';  //转换成字符串
				timePriceSort(clickPage,getNowSortWay()); //调用函数,判断是否存在筛选条件的对象
			}
			else if($(this).is('#nextPage')){   //点击下一页
				clickPage = parseInt(toPage) + 1;
				clickPage += '';  //转换成字符串
				timePriceSort(clickPage,getNowSortWay()); //调用函数,判断是否存在筛选条件的对象
			}
			else if($(this).is('#choosePage')){ //点击当前选中页
				// 不刷新页面
			}
			else if($(this).is('#morePage')){   //点击更多页
				clickPage = parseInt(toPage) + 3;
				clickPage += '';  //转换成字符串
				timePriceSort(clickPage,getNowSortWay()); //调用函数,判断是否存在筛选条件的对象
			}
			else{  
				clickPage = $(this).text() + '';  //获取该页的数值
				alert(clickPage);
				timePriceSort(clickPage,getNowSortWay()); //调用函数,判断是否存在筛选条件的对象
			}
		});
/*以上为分页按钮切换内容*/


	}else{
		$("#houseInfo").append('<p>抱歉，页面加载出了点小故障，请尝试重新刷新页面</p>');
	}
}
/*公用的函数，以上为对返回的房源信息进行渲染的封装*/


/*以下为筛选条件部分内容*/
// 判断筛选条件选中的是哪一个
$(function() {
	//判断筛选条件时执行以下内容
	$("#filterBg").find('li').click(function(event) {
		$('.filter').hide();  // 当点击按筛选按钮时，令其他的筛选条件消失
		$("#filterBg").find('li').removeClass('choose');
		filterChooser = $(this); // 保存当前选中的筛选方式对象
		//调用函数判断选中的是哪个选筛选条件，并进行相关操作
		judgeObj($(this));
	});
})

// 该函数判断选中的是哪个选筛选条件，并进行相关操作
function judgeObj(obj) {
	if(obj.is('#priceFilter')){
		$('#priceFilter').addClass('choose');
		$('#price').show();
		// 当点击筛选条件的查询按钮时
		$('#sendPrice').click(function(event) {
			var minPrice = $('#minPrice').val() + '';
			var maxPrice = $('#maxPrice').val() + '';
			if(minPrice != '' || maxPrice != ''){
				// 调用函数priceFilter(),传入最低价格和最高价格
				priceFilter(minPrice,maxPrice,'1',getNowSortWay());
			}
		});
	}
	else if(obj.is('#keywordFilter')){
		$('#keywordFilter').addClass('choose');
		$('#keyword').show();
		$('#sendKeyword').click(function(event) {
			var keyword = $('#keyword').find('input').val() + '';
			if(keyword != ''){
				// 调用函数keywordFilter,根据关键字搜索
				keywordFilter(keyword,'1',getNowSortWay());
			}
		});
	}
	else if(obj.is('#houseIdFilter')){
		$('#houseIdFilter').addClass('choose');
		$('#houseId').show();
		$('#sendHouseId').click(function(event) {
			var houseId = $('#houseId').find('input').val() + '';
			if(houseId != ''){
				// 调用函数houseIdFilter(),根据房东id号搜索
				houseIdFilter(houseId,'1',getNowSortWay());
			}
		});
	}
}

//获取当前的选中的排序方式
function getNowSortWay() {
	//当点击其他排序方式时，产生sorterTag对象，判断当前选中的排序方式
	if(sorterTag != undefined){
		if(sorterTag.is('#timeUp')){
			return "timeUp";
		}
		else if(sorterTag.is('#timeDown')){
			return "timeDown";
		}
		else if(sorterTag.is('#priceUp')){
			return "priceUp";
		}
		else if(sorterTag.is('#priceDown')){
			return "priceDown";
		}
	}else{
		return "timeDown";
	}
}
/*以上为筛选条件部分内容*/


/*以下为筛选和排序部分内容公共用到的函数*/
// 调用函数priceFilter(),传入最低价格和最高价格
function priceFilter(minPrice,maxPrice,toPage,sortWay) {
	$.ajax({
		// url: 'data/house.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all",
			"toPage":toPage,
			"filter":"price",
			"maxPrice":maxPrice,
			"minPrice":minPrice,
			"sort":sortWay
		}),
	})
	.done(function(obj) {
		// 封装当获取首页客房信息时，返回的响应样例，并对html页面进行渲染的函数
		renderPage(obj);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

// 调用函数keywordFilter,根据关键字搜索
function keywordFilter(keyword,toPage,sortWay){
	$.ajax({
		// url: 'data/house.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all",
			"toPage":toPage,
			"filter":"keyword",
			"keywordParam":keyword,
			"sort":sortWay
		}),
	})
	.done(function(obj) {
		// 封装当获取首页客房信息时，返回的响应样例，并对html页面进行渲染的函数
		renderPage(obj);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}		

// 调用函数houseIdFilter(),根据房东id号搜索
function houseIdFilter(houseId,toPage,sortWay){
	$.ajax({
		// url: 'data/house.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all",
			"toPage":toPage,
			"filter":"publisherId",
			"id":houseId,
			"sort":sortWay
		}),
	})
	.done(function(obj) {
		// 封装当获取首页客房信息时，返回的响应样例，并对html页面进行渲染的函数
		renderPage(obj);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}
/*以上为筛选和排序部分内容公共用到的函数*/


/*以下为排序部分内容*/
// 判断按照哪一种方式进行排序
$(function() {
	//判断排序方式时执行以下内容
	$("#houseTitle").find('.sort').click(function(event) {
		$("#houseTitle").find('.sort').removeClass('chooseSort'); // 当点击按钮时,令以前选中的按钮删除表示选中的类名chooseSort
		sorterTag = $(this);  // sorterTag记录这当前选中的排序方式的对象
		// 当点击按时间升序按钮时
		if($(this).is('#timeUp')){
			$('#timeUp').addClass('chooseSort');  // 对该对象添加类chooseSort，表示选中
			timePriceSort("1","timeUp");  //调用函数,判断是否存在筛选条件的对象
		}
		// 当点击按时间降序按钮时
		else if($(this).is('#timeDown')){		
			$('#timeDown').addClass('chooseSort');  // 对该对象添加类chooseSort，表示选中
			timePriceSort("1","timeDown"); //调用函数,判断是否存在筛选条件的对象
		}
		// 当点击按价格升序按钮时
		else if($(this).is('#priceUp')){
			
			$('#priceUp').addClass('chooseSort'); // 对该对象添加类chooseSort，表示选中		
			timePriceSort("1","priceUp");  //调用函数,判断是否存在筛选条件的对象
		}
		// 当点击按价格降序按钮时
		else if($(this).is('#priceDown')){
			$('#priceDown').addClass('chooseSort'); // 对该对象添加类chooseSort，表示选中		
			timePriceSort("1","priceDown"); //调用函数,判断是否存在筛选条件的对象
		}
	});
})

/*以上为排序部分内容*/

/*以下为排序和分页调用的公用函数*/
//调用函数,判断是否存在筛选条件的对象，再考虑是否设置筛选条件
function timePriceSort(toPage,sortWay) {
	//判断是否有输入筛选条件
	if(filterChooser == undefined){
		getHouseByNoFilter(toPage,sortWay);  //不使用筛选条件获取房源，仅进行排序
	}else{
		//判断选中的文本框的值是否为空，为空即不使用筛选条件
		if(((filterChooser.is('#priceFilter')) && ($('#minPrice').val() == '') && ($('#minPrice').val() == ''))
			|| (filterChooser.is('#keywordFilter') && $('#keyword').find('input').val() == '') 
			|| (filterChooser.is('#houseIdFilter') && $('#houseId').find('input').val() == '')){
			//当所有的筛选条件文本框都是空的时候，调用该函数，仅进行排序
			getHouseByNoFilter(toPage,sortWay);
		}else{
			// 调用该函数，筛选的同时进行选中的排序
			judgeSortObj(filterChooser,toPage,sortWay);
		}
	}
}

//有排序按钮触发，该函数判断选中的是哪个选筛选条件，并进行相关操作
function judgeSortObj(obj,toPage,sortWay) {
	if(obj.is('#priceFilter')){
		// 当点击筛选条件为价格范围时
		var minPrice = $('#minPrice').val() + '';
		var maxPrice = $('#maxPrice').val() + '';
		if(minPrice != '' || maxPrice != ''){
			// 调用函数priceFilter(),传入最低价格和最高价格
			priceFilter(minPrice,maxPrice,toPage,sortWay);
		}
	}
	else if(obj.is('#keywordFilter')){	
		// 当点击筛选条件为关键字搜索时	
		var keyword = $('#keyword').find('input').val() + '';
		if(keyword != ''){
			// 调用函数keywordFilter,根据关键字搜索
			keywordFilter(keyword,toPage,sortWay);
		}
	}
	else if(obj.is('#houseIdFilter')){
		// 当点击筛选条件为房东id搜索时	
		var houseId = $('#houseId').find('input').val() + '';
		if(houseId != ''){
			// 调用函数houseIdFilter(),根据房东id号搜索
			houseIdFilter(houseId,toPage,sortWay);
		}
	}
}
/*以上为排序和分页调用的公用函数*/

/*以下为点击注销按钮时，触发注销事件*/
$('#lgout').click(function(event) {
	$.ajax({
		url: './user',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"logout"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true')
			alert("注销成功");
		else
			alert("注销失败！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
});
/*以上为点击注销按钮时，触发注销事件*/

