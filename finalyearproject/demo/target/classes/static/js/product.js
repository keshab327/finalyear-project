   $(document).ready(function() {
    $('#loader').hide();
    $("#submit").on("click", function() {
    	$("#submit").prop("disabled", true);
    	var name = $("#name").val();
        var file = $("#image").val(); 
         var file_tax = $("#image_tax_report").val();
        var adress = $("#adress").val();
        var description = $("#description").val();
         var owner_name = $("#owner_name").val();
          var pan_number = $("#pan_number").val();
         var phone_number = $("#phone_number").val();
         var email = $("#email").val();
         
        var form = $("#form").serialize();
    	var data = new FormData($("#form")[0]);
    	data.append('name', name);
    	data.append('email', email);
    	data.append('adress', adress);
    	
    	data.append('owner_name', owner_name);;
    	data.append('description', description); 
    	//alert(data);
        $('#loader').show();
        if (email === "" ||phone_number === "" ||owner_name === "" || pan_number === "" || name === "" || file === "" || description === ""||file_tax ===""|| adress === "") {
        	$("#submit").prop("disabled", false);
            $('#loader').hide();
            
            if(name===""){
            $("#name").css("border-color", "red");
             $("#error_name").html("Please fill the required field.");
            }
            
              else if(owner_name===""){
            $("#owner_name").css("border-color", "red");
            $("#error_owner_name").html("Please fill the required field.");
            }
            
           else if(description===""){
            $("#description").css("border-color", "red");
               $("#error_description").html("Please fill the required field.");
            }
           
            
              else if(pan_number===""){
            $("#pan_number").css("border-color", "red");
             $("#error_pan_number").html("Please fill the required field.");
            }
            
               else if(phone_number===""){
            $("#phone_number").css("border-color", "red");
             $("#error_phone_number").html("Please fill the required field.");
            }
            
               else if(email===""){
            $("#email").css("border-color", "red");
             $("#error_email").html("Please fill the required field.");
            }
            
          
            
            else if(file===""){
            $("#image").css("border-color", "red");
              $("#error_file").html("Please fill the required field.");
            }
            
              else if(file_tax===""){
            $("#image_tax_report").css("border-color", "red");
              $("#error_image_tax_report").html("Please fill the required field.");
            }
            
          
            
          
            else if(adress==""){
             $("#adress").css("border-color", "red");
               $("#error_adress").html("Please fill the required field.");
             }
           
          
        } 
        
        else {
            $("#name").css("border-color", "");
            $("#image").css("border-color", "");
             $("#image_tax_report").css("border-color", "");
            $("#adress").css("border-color", "");
            $("#description").css("border-color", "");
            $('#error_name').css('opacity', 0);
            $('#error_file').css('opacity', 0);
            $('#error_price').css('opacity', 0);
            $('#error_description').css('opacity', 0);
                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: data,
                        url: "/image/saveImageDetails", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(data, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                        	$('#loader').hide(); 
                        	$("#form")[0].reset();
                        	$('#success').css('display','block');
                            $("#error").text("");
                            $("#success").html("Product Inserted Succsessfully.");
                            $('#success').delay(2000).fadeOut('slow');
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            location.reload();
                        }
                    });
        }
            });
        });
