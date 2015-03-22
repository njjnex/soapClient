var session;

$("#login").click(
		function() {

			var email = $("#email").val();
			var passwd = $("#password").val();
			var params = {
				email : email,
				password : passwd
			};

			$.ajax({
				url : "/login",
				type : "post",
				data : JSON.stringify(params),
				contentType : "application/json",
				success : function(data) {
					session = data;
					$("#loginForm").html(
							"<h3>Welcome back </h3> " + email
									+ "<br><br>Sesson opened: " + data);
					$('#getCitiesButton').attr("class",
							"btn btn-info btn-block");
				},
				error : function(data) {
					alert("Incorrect login or password.")
				},
			});
		});

$("#getCitiesButton").click(function() {

	$.ajax({
		url : "/citiesList",
		type : "post",
		data : session,
		contentType : "application/json",
		success : function(data) {
			printResult(data);
		},
		error : function(data) {
			alert("Cannot connect to server.")
		},
	});
});

var printResult = function(data) {
	$("#result").html("");
	var t = $("<ul class='list-unstyled' style='line-height: 5' id='resultList'></ul>");
	$("#result").append(t);

	var i = 0;
	while (i < data.length) {
		var city = $("<li><h4><span class='fa fa-check text-success'></span>" + data[i] + "</h4></li>");
		$("#resultList").append(city);
		i++;
	}
}
