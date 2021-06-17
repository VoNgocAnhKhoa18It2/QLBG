<script>
	$(document).ready(function(){
		$('.alert').hide();
		$("input[name='signup']").on("click",function(){
			$("input[type='text'],input[type='date'],input[type='password']").each(function() {
				if($(this).val().trim() == "") {
					$("input[name='signup']").prop('disabled',true);
					$(this).css({"box-shadow":"inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgb(251, 67, 94)"});
					$('.alert').text("Please enter your information");
					$('.alert').show();
				}
			})
		});
		$('form input').blur(function()
		{
			if(!$(this).val()) 
			{
				$("input[name='signup']").prop('disabled',false);
				$(this).css({"box-shadow":"inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgb(251, 67, 94)"});
			}
			else
			{
				$(this).css({"box-shadow":"rgba(0, 0, 0, 0.075) 0px 1px 1px inset, rgba(103, 239, 126, 0.91) 0px 0px 8px"});
			}
		});

		$("input[type='text'],input[type='date'],input[type='password']").click(function(){
			$("input[name='signup']").prop('disabled',false);
			$(this).css({"box-shadow":"inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102,175,233,.6)"});
		});
		
		$("input[name='email']").blur(function(){
			var email = $(this).val();
			if(validateEmail(email) == false)
			{
				$("input[name='signup']").prop('disabled',false);
				$(this).css({"box-shadow":"inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgb(251, 67, 94)"});
				$('.alert').text("Email don't exist");
				$('.alert').show();
			}
			$.post("./mvc/core/xuly_ajax.php",
			{
				email_signup : email,
			},
			function(data,status){
				if (status == 'success') {
					if (data == 1) 
					{
						$("input[name='signup']").prop('disabled',false);
						$("input[name='email']").css({"box-shadow":"inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgb(251, 67, 94)"});
						$('.alert').text("Email already exists");
						$('.alert').show();
					}
				}
			});
		});
		$("input[name='gender']").blur(function(){
			var gender = capitalizeFirstLetter($(this).val());
			if (gender.trim() == "Male" || gender.trim() == "Female") {
			}
			else
			{
				$("input[name='signup']").prop('disabled',false);
				$(this).css({"box-shadow":"inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgb(251, 67, 94)"});
			}
		});
	});
</script>
<div class="head-bread">
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="./">Home</a></li>
			<li><a href="login">LOGIN</a></li>
			<li class="active">REGISTER</li>
		</ol>
	</div>
</div>
<!-- reg-form -->
<div class="reg-form">
	<div class="container">
		<div class="reg">
			<h3>Register Now</h3>
			<div class="alert alert-danger m-3">
			</div>
			<p>Welcome, please enter the following details to continue.</p>
			<p>If you have previously registered with us, <a href="#">click here</a></p>
			<form action="register" method="POST">
				<ul>
					<li class="text-info">Full Name: </li>
					<li><input type="text" name="name"></li>
				</ul>
				<ul>
					<li class="text-info">Birth: </li>
					<li><input type="date" name="birth"></li>
				</ul>				 
				<ul>
					<li class="text-info">Gender (Male or Female): </li>
					<li><input type="text" name="gender" ></li>
				</ul>
				<ul>
					<li class="text-info">Email: </li>
					<li><input type="text" name="email" placeholder="Email"></li>
				</ul>
				<ul>
					<li class="text-info">Re-enter Password:</li>
					<li><input type="password" name="pass" placeholder="Password"></li>
				</ul>						
				<input type="submit" name="signup" value="Register Now">
				<p class="click">By clicking this button, you are agree to my  <a href="#">Policy Terms and Conditions.</a></p> 
			</form>
		</div>
	</div>
</div>