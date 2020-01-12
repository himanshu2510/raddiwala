jQuery(document).ready(function($) {
	tab = $('.tabs h3 a');

	tab.on('click', function(event) {
		event.preventDefault();
		tab.removeClass('active');
		$(this).addClass('active');

		tab_content = $(this).attr('href');
		$('div[id$="tab-content"]').removeClass('active');
		$(tab_content).addClass('active');
	});
});

function register() {
			console.log("came");

			var data = {
				"name": document.getElementById("name").value,
				"phoneNumber": document.getElementById("phoneNumber").value,
				"city": document.getElementById("city").value,
				"area": document.getElementById("area").value,
				"pincode": document.getElementById("pincode").value,
				"username": document.getElementById("username").value,
				"password": document.getElementById("password").value,

			};
			$.ajax({
	            url:  "http://localhost:8080/user-api/signup",
	            type: 'POST',
	            dataType: 'json',
	            data:  JSON.stringify(data),
	            contentType: 'application/json; charset=utf-8',
	            success: function (result) {
	               console.log("hihi", result);
	               window.location.href = "home1.html";
	            },
	            error: function (error) {
	                
	            }
        	});
		}

		function login() {
			//console.log("came");

			var data = {
				"username": document.getElementById("username1").value,
				"password": document.getElementById("password1").value

			};
			console.log("data", data);

			$.ajax({
	            url:  "http://localhost:8080/user-api/login",
	            type: 'POST',
	            dataType: 'json',
	            data:  JSON.stringify(data),
	            contentType: 'application/json; charset=utf-8',
	            success:function(result){
	            	console.log(result);
        			console.log("hi");
        			window.location.href = "home1.html";
	            },
	            error: function (error) {
	            // console.log(error);
	            console.log("error here");    
	            }
        	});
		}