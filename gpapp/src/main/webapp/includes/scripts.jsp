<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Generic AJAX call for any form  -->
<script>
	$(document).ready(function() {
		$('form').submit(function(event) {
			var form = $(this);
			var infoBox = $("#message_box"); //TO ADD
			var postAction = "";
			$.ajax({
				type : form.attr('method'),
				url : form.attr('action'),
				data : form.serialize(),
				success : function(response) {
					postAction = response.action;
					if (response.status == "OK") {
						infoBox.removeClass("alert-danger");
						infoBox.addClass("alert-success");
						$("#form").trigger('reset');
						infoBox.html(response.message);
					} else {
						infoBox.removeClass("alert-success");
						infoBox.addClass("alert-danger");
						infoBox.html(response.message);
					}
				}
			}).done(function() {
				if (postAction == "reload") {
					location.reload();
				}
			}).fail(function() {
				infoBox.removeClass("alert-success");
				infoBox.addClass("alert-danger");
				infoBox.html("Your request cannot be completed at this time");

			});
			// Prevent the form from submitting via the browser.
			event.preventDefault(); 
		});
	});
</script>

<!-- Shows and hides the #bname field after toggle on account type -->
<script type="text/javascript">
	$(document).ready(function() {	
		$('#account-types .btn').click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			if ($(this).children().first().attr("id") == "opt-cons") {
				$("#bname").slideUp('slow');
				$("#bname").empty();
			} else {
				$("#bname").slideDown('slow');
			}
		});

	});
</script>
<script>
	$(document).ready(function() {
		$('#edit-info').click(function() {
			var text = $('#edit-info').text();
			if(text == "Edit"){						//we want to edit
				text = "Save";
			}
			else{ //text == "Save"
				text = "Edit";						//we want to call ajax and save
				/*	names = ["action","fullname","username","email"];
				vals = ["USER_INFO_EDIT", 
				        $("#info-fullname").children().first().val(),
				        $("#info-username").children().first().val(),
				        $("#info-email").children().first().val()];
			
			*/
				var action = "MainController";
				var params = {
						action: 'USER_INFO_EDIT',
						fullname: $("#info-fullname").children().first().val(),
						username: $("#info-username").children().first().val(), 
						email:$("#info-email").children().first().val()
			    };
			    ajaxGeneric(params,action,"POST");
			}
			
			$('#edit-info').text(text);
			$("#user-info").children().not(".btn").each(function() {
				if ($(this).find('input').length) {
					$(this).text($(this).find('input').val());
				} else {
					var t = $(this).text();
					$(this).text('').append($('<input class="form-control" />', {
						'value' : t
					}).val(t));
				}
			});

		});
	});
</script>

<script>
function ajaxGeneric(params,action, method){
			var infoBox = $("#message_box");
			var postAction = "";			
			console.log(params);
			$.ajax({
				type : method,
				url : action,
				data : params,
				success : function(response) {
					postAction = response.action;
					if (response.status == "OK") {
						infoBox.removeClass("alert-danger");
						infoBox.addClass("alert-success");
						$("#form").trigger('reset');
						infoBox.html(response.message);
					} else {
						infoBox.removeClass("alert-success");
						infoBox.addClass("alert-danger");
						infoBox.html(response.message);
					}
				}
			}).done(function() {
				if (postAction == "reload") {
					location.reload();
				}
			}).fail(function() {
				infoBox.removeClass("alert-success");
				infoBox.addClass("alert-danger");
				infoBox.html("Your request cannot be completed at this time");

			});
			
}
</script>

