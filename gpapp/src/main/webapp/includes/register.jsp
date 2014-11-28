
<form id="register" role="form" action="MainController" method="POST">
	<div class="register-container">
		<h3>Sign Up</h3>
		<h5>It's free and always will be.</h5>
	</div>
	<div class="im-centered row">
		<div id="account-types" class="btn-group" data-toggle="buttons">

			 <label class="btn btn-success active"> <input type="radio"
				name="options" id="opt-biz" value="opt-biz"autocomplete="off" checked> As Business
			</label>
			<label class="btn btn-success"> <input type="radio"
				name="options" id="opt-cons" value="opt-cons" autocomplete="off"> As
				Consumer</label>

		</div>



	</div>
	<div class="row">
		<input type="text" hidden=true name="action" value="REGISTER">
		<div class="form-group">
			<input type="text" hidden=true class="form-control" name="bname" id="bname"
				placeholder="Business Name">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="full-name"
				id="full-name" placeholder="Full name">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="username" id="username"
				placeholder="Username">
		</div>


		<div class="form-group">
			<input type="email" class="form-control" name="email" id="email"
				placeholder="Email">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" name="password"
				id="password" placeholder="Password">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" name="password-confirm"
				id="password-confirm" placeholder="Re-enter password">
		</div>
		<div class="register-container">
			<button id="" type="submit" class="btn btn-success">Submit</button>
		</div>


	</div>

</form>