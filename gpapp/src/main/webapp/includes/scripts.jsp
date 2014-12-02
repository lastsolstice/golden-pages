<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="com.gp.users.UserDTO,com.gp.calendar.CalendarBean"%>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

<!-- Generic AJAX call for any form  -->
<script>
	$(document).ready(function() {
		$('form').submit(function(event) {
			var form = $(this);
			var method = form.attr('method');
			var action = form.attr('action');
			var data = form.serialize();
			ajaxGeneric(action, method, data, "#message_box");
			event.preventDefault(); // Prevent the form from submitting via the browser.
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
			if (text == "Edit") { //we want to edit
				text = "Save";
			} else { //text == "Save"
				text = "Edit"; //we want to call ajax and save
				/*	names = ["action","fullname","username","email"];
				vals = ["USER_INFO_EDIT", 
				        $("#info-fullname").children().first().val(),
				        $("#info-username").children().first().val(),
				        $("#info-email").children().first().val()];
				
				 */
				var action = "MainController";
				var params = {
					action : 'USER_INFO_EDIT',
					fullname : $("#info-fullname").children().first().val(),
					username : $("#info-username").children().first().val(),
					email : $("#info-email").children().first().val()
				};
				ajaxGeneric(action, "POST", params, "#message_box");
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
	function ajaxGeneric(action, method, params, response_target) {
		var infoBox = $(response_target);
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
			if (postAction == "reload-slow") {
				timedRefresh(5000);
			}
		}).fail(function() {
			infoBox.removeClass("alert-success");
			infoBox.addClass("alert-danger");
			infoBox.html("Your request cannot be completed at this time");

		});

	}
</script>
<script>
	function timedRefresh(timeoutPeriod) {
		setTimeout("location.reload(true);", timeoutPeriod);
	}
</script>


<script>
	//Keep selected tab on page refresh
	$(function() {
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			//save the latest tab using a cookie:
			$.cookie('last_tab', $(e.target).attr('href'));
		});
		//activate latest tab, if it exists:
		var lastTab = $.cookie('last_tab');
		if (lastTab) {
			$('a[href=' + lastTab + ']').tab('show');
		} else {
			// Set the first tab if cookie do not exist
			$('a[data-toggle="tab"]:first').tab('show');
		}
	});
</script>
<script>
	$(function() {
		$(".datepicker").datepicker({
			minDate : 0,
			maxDate : "+1M +10D"
		});
	});
</script>

<script>
$(document).ready(function() {
	$('#add-cal-entry').click(function() {
		
			var action = "MainController";
			var params = {
				action : 'ADD_CAL_ENTRY',
				bizid : $("#invitee-add option:selected").val(),
				hour : $("#hour-add").val(),
				date : $("#date-add").val()
			};
			var response_target = ".modal_box";
			ajaxGeneric(action, "POST", params, response_target);
		});
});
</script>








