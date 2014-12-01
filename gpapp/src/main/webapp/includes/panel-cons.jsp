

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
					<button type="button" class="btn btn-default">Add Entry</button>
					<button type="button" class="btn btn-default">Edit</button>
					<button type="button" class="btn btn-default">Accept</button>
				</div>
				<div class="schedule-wrap">
					<h2>Schedule Here</h2>
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
				
				
				<div id="user-info"  style="margin-bottom: 10px;">
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