<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">

	<div class="col-md-8 navs-container">

		<ul class="nav nav-tabs" id="panel-tabs">
			<li class="active"><a href="#messages" data-toggle="tab"><i
					class="fa fa-envelope"></i>&nbsp;Messages</a></li>
			<li><a href="#schedule" data-toggle="tab"><i
					class="fa fa-calendar"></i>&nbsp;Schedule</a></li>
			<li><a href="#following" data-toggle="tab"><i
					class="fa fa-users"></i>&nbsp;Following</a></li>
			<li><a href="#account" data-toggle="tab"><i
					class="fa fa-info-circle"></i>&nbsp;Account</a></li>
		</ul>
		<div class="tab-content">
			<div id="messages" class="tab-pane fade in active">
				<div class="btn-group" style="margin-bottom: 10px;">
					<button type="button" class="btn btn-default">New</button>
				</div>
				<div class="list-group">
					<a href="#" class="list-group-item"><span class="badge">1</span>
						Thread 1</a> <a href="#" class="list-group-item"><span
						class="badge">4</span> Thread 2</a> <a href="#"
						class="list-group-item"><span class="badge">3</span> Thread 3</a>
					<a href="#" class="list-group-item"><span class="badge">5</span>
						Thread 4</a>
				</div>

			</div>
			<div id="schedule" class="tab-pane fade">
				<div class="btn-group" style="margin-bottom: 10px;">
					<button id="add-calendar-entry" type="button"
						class="btn btn-default" data-toggle="modal" data-target="#myModal">Add
						Entry</button>
					<button type="button" class="btn btn-default">Edit</button>
					<button type="button" class="btn btn-default">Accept</button>
				</div>
				<div class="schedule-wrap">
					<h2>Schedule</h2>
					<ul class="list-group">
						<c:forEach var="appt" items="${calendar.apptList}">
							<li class="list-group-item">${appt.date}&nbsp;${appt.status}</li>
						</c:forEach>
					</ul>


				</div>

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Add Calendar
									Entry</h4>
							</div>
							<div class="modal-body">
								<div class="alert modal_box" role="alert"></div>
								<form class="form-horizontal " role="form">
									<div class="form-group">
										<label for="date-add" class="col-sm-3 control-label">Hour</label>
										<div class="col-sm-8">
											<input type="text" name="hour-add" id="hour-add"
												class="form-control sm-input">
										</div>
									</div>
									<div class="form-group">
										<label for="date-add" class="col-sm-3 control-label">Date</label>
										<div class="col-sm-8">
											<input type="text" name="date-add" id="date-add"
												class="datepicker form-control sm-input">
										</div>
									</div>
									<!--  
									<div class="form-group ">
										
										<div class="col-sm-8 ">
											<input type="text" 
												class="form-control ">
										</div>
									</div>
									-->
									<sql:query var="result" dataSource="jdbc/gpappdb">
										SELECT * FROM business
									</sql:query>


									<div class="form-group ">
										<label for="invitee-add" class="col-sm-3 control-label">Business
											Name</label> <select name="invitee-add" id="invitee-add"
											class="form-control sm-input">
											<c:forEach var="row" items="${result.rows}">
												<option value='${row.business_id}'>${row.business_name}</option>
											</c:forEach>
										</select>
									</div>
								</form>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button id="add-cal-entry" type="button" class="btn btn-primary">Add</button>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div id="following" class="tab-pane fade">
				<div class="list-group">
					<a href="#" class="list-group-item">Miami's Pet Grooming</a> <a
						href="#" class="list-group-item">Bob's Liquors</a> <a href="#"
						class="list-group-item">Guido's Botanica</a>
				</div>
			</div>
			<div id="account" class="tab-pane fade">


				<div id="user-info" style="margin-bottom: 10px;">
					<h2 id="info-fullname">${user.fullName}</h2>
					<h3 id="info-username">${user.username}</h3>
					<p id="info-email">${user.email}</p>
					<button id="edit-info" class="btn btn-default">Edit</button>
				</div>
			</div>
		</div>

	</div>
	<!-- end of tabs container -->
	<div class="col-md-4 info-box">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Account Stats</h3>
			</div>
			<div class="panel-body">Lorem ipsum dolor sit amet, consectetur
				adipisicing elit, sed do eiusmod tempor incididunt ut labore et
				dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
				exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit
				esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
				cupidatat non proident, sunt in culpa qui officia deserunt mollit
				anim id est laborum.</div>
		</div>
	</div>

</div>