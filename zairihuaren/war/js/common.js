//$("#rate").html("good");
// $.getJSON("http://rate-exchange.appspot.com/currency?from=JPY&to=CNY&q=100",
// function(data) {
 $("#rate").load("http://rate-exchange.appspot.com/currency?from=JPY&to=CNY&q=100");
// });

//$.ajax({
//	url : "http://rate-exchange.appspot.com/currency?from=JPY&to=CNY&q=100",
//	dataType : 'jsonp',
//	success : function(json) {
//		$("#rate").html("good");
//		// handle the json response
//	},
//});}