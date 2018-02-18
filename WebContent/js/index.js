(function(){


	function click1(){


		$.ajax({
		url: '../data/index.json?',
		type: 'get',
		dataType: 'json',
		data: {id: val},
	})
	.done(function(data) {
		xx(data);
		console.log(data[0].name);
		$('#app').html(data[0].name);
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
	}


	


	

	function xx(dd){
		$.each(dd,function(index, el) {
			console.log(el.name);
		});
	}



})()