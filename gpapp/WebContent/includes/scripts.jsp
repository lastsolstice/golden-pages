<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>







<script>
$(document).ready(function() {
  $('form').submit(function(event) {
   // alert("hey");
    var form = $(this);
	var infoBox = $("#message_box");							//TO ADD
	
    $.ajax({
      type: form.attr('method'),
      url: form.attr('action'),
      data: form.serialize(),
      success: function(message){								//in this case it is returning the strings
      	infoBox.addClass("alert");
      	if(message.substring(0,3) == "Con"){//congratulations
			infoBox.removeClass("alert-danger");
			infoBox.addClass("alert-success");
			infoBox.html(message);
			$("#form").trigger('reset');
		}else{
			infoBox.removeClass("alert-success");
			infoBox.addClass("alert-danger");
			infoBox.html(message);
		
		}
     }
      
      
    }).done(function() {
    // Optionally alert the user of success here...
    }).fail(function() {
    // Optionally alert the user of an error here...
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
	    if($(this).children().first().attr("id") == "option1"){
	    	$("#bname").slideUp('slow');
	    	$("#bname").empty();
	    	//alert("btn 1 selected");
	    }else{
	    	$("#bname").slideDown('slow');
	    }
	});
	
});


</script>
