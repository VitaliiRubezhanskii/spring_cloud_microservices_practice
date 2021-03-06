  $( document ).ready(function() {
	
	var url = window.location;
	
	// GET REQUEST
	$("#getBtn").click(function(event){
		event.preventDefault();
		ajaxGet();
	});
	
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : url + "/getallcustomer",
			success: function(result){
				if(result.status == "Done"){
					$('#getResultDiv .list-group li').remove();
					var custList = "";
					$.each(result.data, function(i, customer){


						var customer = "Customer " + i + ": FirstName=" + customer.firstname + " ,LastName=" + customer.lastname + "<br\>";
						$('#getResultDiv .list-group').append('<li><h4 class="list-group-item">'+customer+'</h4></li>')


					});
					console.log("Success: ", result);
				}else{
					$("#getResultDiv").html("<strong>Error</strong>");
					console.log("Fail: ", result);
				}
			},
			error : function(e) {
				$("#getResultDiv").html("<strong>Error</strong>");
				console.log("ERROR: ", e);
			}
		});	
	}
})