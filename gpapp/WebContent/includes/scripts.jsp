<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>







<script>
$(document).ready(function() {
  $('form').submit(function(event) {
    var form = $(this);
	var infoBox = $("#message_box");							//TO ADD
	var postAction = "";
	//log(postAction);
    $.ajax({
      type: form.attr('method'),
      url: form.attr('action'),
      data: form.serialize(),
      success: function(response){								//in this case it is returning the strings
    	  postAction = response.action;
    	  //console.log(postAction);
      		if(response.status == "OK"){
      		infoBox.removeClass("alert-danger");
      		infoBox.addClass("alert-success");
      		$("#form").trigger('reset');
      		infoBox.html(response.message);
      	}else{
			infoBox.removeClass("alert-success");
			infoBox.addClass("alert-danger");
			infoBox.html(response.message);
		
		}
     }
      
      
    }).done(function() {
    	//log(postAction);
    	
    	if(postAction == "reload"){
    		location.reload();
    	}
    // Optionally alert the user of success here...
    }).fail(function() {
    // Optionally alert the user of an error here...
		infoBox.removeClass("alert-success");
		infoBox.addClass("alert-danger");
		infoBox.html("Your request cannot be completed at this time");
    
    
    });
    event.preventDefault(); 									// Prevent the form from submitting via the browser.
   });
  });
</script>


<script type="text/javascript">
$(document).ready(function() {
	//$("#bname").hide();
	$('#account-types .btn').click(function() {
	    $(this).addClass('active').siblings().removeClass('active');
	    //$(this).first().prop("checked", true);
	    if($(this).children().first().attr("id") == "opt-cons"){
	    	$("#bname").slideUp('slow');
	    	$("#bname").empty();
	    	//$("#opt-biz").prop("checked", false);
	    	//alert("btn 1 selected");
	    }else{
	    	$("#bname").slideDown('slow');
	    }
	});
	
});


</script>
