$(document).ready(function() {

    validation();


    $("#userName").on('blur', function() {
        if ($(this).val() === 'undefine' || $(this).val() == "" || !$(this).val().trim().match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
            $("#userNmae_error").text("Please enter valid user name.");

        }
    })

    $("#userName").on('keypress', function() {
        $("#userNmae_error").text("");
        $("#nameError").text("");
    })

    $("#passWord").on('blur', function() {
        if ($(this).val() === 'undefine' || $(this).val() == "") {
            $("#passWord_error").text("Please enter password.");

        }
    })

    $("#passWord").on('keypress', function() {
        $("#passWord_error").text("");
        $("#passError").text("");
    })


})



function loginValidation() {
    var status = true;
    
    if($("#passWord").val() == "" && $("#userName").val() == ""){
    	$("#userNmae_error").text("Please enter valid user name and password.");
    	status = false;
    }else{

    if ($("#passWord").val() === 'undefine' || $("#passWord").val() == "") {
        $("#passWord_error").text("Please enter password.");
        status = false;
    }

    if ($("#userName").val() === 'undefine' || $("#userName").val() == "" || !$("#userName").val().trim().match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
        $("#userNmae_error").text("Please enter valid user name.");
        status = false;
    }
    
    }
    return status;
}

/********************************  training validation 	************************************/

function validation() {
	
	


    $("#product-category").on('change', function() {
        $("#error_traingType").text("");
        $("#typeError").text("");
    })

    /*Venue Validatin*/
    $("#venueDetails").on('blur', function() {
        if ($(this).val() == "" || $(this).val() === 'undefine' || !$(this).val().trim().match(/^[a-zA-Z0-9\s-.,]{1,100}$/)) {
            $("#error_venue").text("Enter proper venue");
           
        }
    })

    $("#venueDetails").on('keydown', function() {
        $("#error_venue").text("");
        $("#venueError").text("");
    })

    /*Venue Validatin Ends*/

    /*Title Validatin*/
    $("#ttitle").on('blur', function() {


        if ($(this).val() == "" || $(this).val() === 'undefine' || !$(this).val().trim().match(/^[A-Za-z][a-zA-Z0-9\s-.,]{1,100}$/)) {
            $("#error_topic").text("Please enter proper title.");
            
        }


    })

    $("#ttitle").on('keydown', function() {

        $("#error_topic").text("");
        $("#titleError").text("");

    })

    /*title Validatin Ends*/


    /*Seat Validatin*/
    $("#totalavailSeats").on('blur', function() {


        if (!$(this).val().trim().match(/^[\d]{1,3}$/) || $(this).val() == 0 || $(this).val() === 'undefine') {
            $("#error_seat_capacity").text("Enter valid numbers");
          
        }
    })

    $("#totalavailSeats").on('keydown', function() {

        $("#error_seat_capacity").text("");
        $("#seatError").text("");
    })

    /*Seat Validatin Ends*/

    /*Days Validatin*/
    $("#trainingDays").on('blur', function() {

        if (!$(this).val().trim().match(/^[0-9]{1,3}$/) || $(this).val() == 0 || $(this).val() === 'undefine') {
            $("#error_training_days").text("Enter valid days in number");
            
        }
    })

    $("#trainingDays").on('keydown', function() {

        $("#error_training_days").text("");
        $("#dayError").text("");
    })

    /*Days Validatin Ends*/

    //acStartDate

    /*Date Validatin*/
    $("#acStartDate").on('change', function() {



        if (!$("#acStartDate").val().trim().match(/^(((0)[1-9])|((1)[0-2]))(\/)([0-2][1-9]|(3)[0-1])(\/)(1[6-9]|[2-9]\d)?[0-9]{4}$/) || $(this).val() == 0 || $(this).val() === 'undefine') {
            $("#error_schedule_date").text("Choose valid date");
           
        }


    })

    $("#acStartDate").on('click', function() {

        $("#error_schedule_date").text("");
        $("#startDateError").text("");
    })

    /*Date Validatin Ends*/

    /*EndDate Validatin*/
    $("#acendDate").on('change', function() {



        if (!$("#acendDate").val().trim().match(/^(((0)[1-9])|((1)[0-2]))(\/)([0-2][1-9]|(3)[0-1])(\/)(1[6-9]|[2-9]\d)?[0-9]{4}$/) || $(this).val() == 0 || $(this).val() === 'undefine') {

            $("#error_schedule_endDate").text("Choose valid date");
            
        }


    })

    $("#acendDate").on('click', function() {

        $("#error_schedule_endDate").text("");
        $("#endDateError").text("");
    })

    /*EndDate Validatin Ends*/

    /*StartTime Validatin*/
    $("#ctstartime").on('blur', function() {

        console.log($(this).val());
        if (!$(this).val().trim().match(/^([0-1]?[1-9]|[0-2]{1}[0-4])[:][0-9]{2}(am|pm)$/) || $(this).val() === 'undefine') {
            $("#error_time_start").text("Select valid time");
            
        }


    })
    
     $("#ctstartime").on('change',function(){
    	 if (!$(this).val().trim().match(/^([0-1]?[1-9]|[0-2]{1}[0-4])[:][0-9]{2}(am|pm)$/) || $(this).val() === 'undefine') {
             $("#error_time_end").text("Select valid time");
             
         }else{
        	 $("#error_time_end").text("");
             $("#endTimeError").text("");
         }
     })

    $("#ctstartime").on('click', function() {

        $("#error_time_start").text("");
        $("#startTimeError").text("");
    })
    
    $("#ctstartime").on('change', function() {

        $("#error_time_start").text("");
        $("#startTimeError").text("");
    })

    /*StartTime Validatin Ends*/

    /*EndTime Validatin*/
    $("#ctendtime").on('blur', function() {

        console.log($(this).val());
        if (!$(this).val().trim().match(/^([0-1]?[1-9]|[0-2]{1}[0-4])[:][0-9]{2}(am|pm)$/) || $(this).val() === 'undefine') {
            $("#error_time_end").text("Select valid time");
            
        }


    })

    $("#ctendtime").on('click', function() {

        $("#error_time_end").text("");
        $("#endTimeError").text("");
    })

     $("#ctendtime").on('change', function() {

        $("#error_time_end").text("");
        $("#endTimeError").text("");
    })
    /*EndTime Validatin Ends*/

    /*Name Validatin*/
    $("#cntName").on('blur', function() {


        if (!$(this).val().trim().match(/^[A-Za-z][a-zA-Z\s]{1,100}$/) || $(this).val() == "" || $(this).val() === 'undefine') {
            $("#error_trainer_name").text("Enter proper name.");
            
        }


    })

    $("#cntName").on('keydown', function() {

        $("#error_trainer_name").text("");
        $("#nameError").text("");
    })

    /*Name Validatin Ends*/


    /*Profile Validatin*/
    $("#cntPD").on('blur', function() {


        if (!$(this).val().trim().match(/^[a-zA-Z][a-zA-Z\s,\(\)\-_?.{}\[\]]{1,500}$/) || $(this).val() == "" || $(this).val() === 'undefine') {
            $("#error_trainer_profile").text("Only alphabets with (, -_?.{}[]) this special character allowed.");
            
        }


    })

    $("#cntPD").on('keydown', function() {

        $("#error_trainer_profile").text("");
        $("#profileError").text("");
    })

    /*Profile Validatin Ends*/


    $(".se-wrapper-inner").on('keydown', function() {

        $("#error_agenda").text("");
        $("#agendaError").text("");
    })


    $("input[name='team']").on('click', function() {
        $("#error_department").text("");
        $("#deptError").text("");
    })


    /* file validation */
    $("#fileElem").on('change', function() {
        var array = [];

        var filename = $('#fileElem').val();
        array = filename.split(".");

        if($("#fileElem").prop("files").length<1 || ($("#fileElem").prop("files")[0].size/1024/1024)>2){
    		$("#error_attachment").text('Upload valid file less than 2 MB').show();
    		var p = $("#fileElem");
    		var offset = p.offset();
    		$('html, body').animate({scrollTop: $('#drop-area').offset().top -100 }, 'slow');
    		status = false;
    		//return false;
    		}
        
        
        if (array[1] != "docx" && array[1] != "pdf" && array[1] != "zip" && array[1] != "doc" && array[1] != "docm" && array[1] != "dot") {
            $("#error_attachment").text("Wrong file format.");
            

            $("#progress-bar").val("");
            $("#gallery").hide();
            $("#fileName span").text('');
            $("#fileElem").val("");
            $("#progress-bar").hide();
            $(".cancel-btn").hide();
        }

    })
    
 $('#drop-area').on('drop', function (event){

    	 var array = [];

         var filename = $('#fileElem').val();
         array = filename.split(".");
console.log("file extenstion    "+array);
         if($("#fileElem").prop("files").length<1 || ($("#fileElem").prop("files")[0].size/1024/1024)>2){
     		$("#error_attachment").text('Upload valid file less than 2 MB').show();
     		var p = $("#fileElem");
     		var offset = p.offset();
     		$('html, body').animate({scrollTop: $('#drop-area').offset().top -100 }, 'slow');
     		status = false;
     		//return false;
     		}else if (array[1] != "docx" && array[1] != "pdf" && array[1] != "zip" && array[1] != "doc" && array[1] != "docm" && array[1] != "dot") {
             $("#error_attachment").text("Wrong file format.");
             document.training.attachment.focus();

             $("#progress-bar").val("");
             $("#gallery").hide();
             $("#fileName span").html('');
             $("#fileElem").val("");
             $("#progress-bar").hide();
             $(".cancel-btn").hide();
         }else{
$("#error_attachment").text("");
}

			})

    $("#fileElem").on('click', function() {
        $("#gallery").hide();
        $("#error_attachment").text("");
        $("#attachmentError").text("");
    })

    /* file validation Ends*/



}

function isAlphaNumeric(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if ((charCode < 48 || charCode > 57) && (charCode < 97 || charCode > 122) && (charCode < 65 || charCode > 90) && charCode != 45 && charCode != 46 && charCode != 32 && charCode != 44) { //charCode > 31 && 
        return false;
    }
    return true;
}

function isAlphabet(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if ((charCode < 97 || charCode > 122) && (charCode < 65 || charCode > 90) && charCode != 45 && charCode != 46 && charCode != 32) { //charCode > 31 && 
        return false;
    }
    return true;
}

function isTextArea(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    console.log("charCode  " + charCode);
    if ((charCode < 97 || charCode > 122) && (charCode < 65 || charCode > 90) && charCode != 45 && charCode != 46 && charCode != 32 && charCode != 41 && charCode != 44 && charCode != 40 && charCode != 95 && charCode != 63 && charCode != 91 && charCode != 93 && charCode != 123 && charCode != 125) { //charCode > 31 &&  40



        return false;
    }
    return true;
}

function isDate(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if ((charCode < 48 || charCode > 57) && charCode != 47) { //charCode > 31 && 
        return false;
    }
    return true;
}

function isTime(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if ((charCode < 48 || charCode > 57) && charCode != 97 && charCode != 58 && charCode != 109 && charCode != 112) { //charCode > 31 && 
        return false;
    }
    return true;
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function formvalidation() {

    var status = true;


    console.log($("#venueDetails").val());

    if (!$("#venueDetails").val().trim().match(/^[a-zA-Z0-9\s-.,]{1,100}$/) || $("#venueDetails").val() == "" || $("#venueDetails").val() === 'undefine') {
        $("#error_venue").text("Enter proper venue");
      //  $("#venueDetails").css("border","1px solid red");
        document.training.venue.focus();
        status = false;
    }
    if ($("#ttitle").val() == "" || $("#ttitle").val() === 'undefine' || !$("#ttitle").val().trim().match(/^[A-Za-z][a-zA-Z0-9\s-.,]{1,100}$/)) {
        $("#error_topic").text("Please Enter proper title");
        document.training.topic.focus();
        status = false;
    }

    if (!$("#totalavailSeats").val().trim().match(/^[\d]{1,3}$/) || $("#totalavailSeats").val() == 0 || $("#totalavailSeats").val() === 'undefine') {
        $("#error_seat_capacity").text("Enter valid no of seats");
        document.training.seat_capacity.focus();
        status = false;
    }

    if (!$("#trainingDays").val().trim().match(/^[0-9]{1,3}$/) || $("#trainingDays").val() == 0 || $("#trainingDays").val() === 'undefine') {
        $("#error_training_days").text("Enter days in number");
        document.training.training_days.focus();
        status = false;
    }




    if (!$("#acStartDate").val().trim().match(/^(((0)[1-9])|((1)[0-2]))(\/)([0-2][1-9]|(3)[0-1])(\/)(1[6-9]|[2-9]\d)?[0-9]{4}$/) || $("#acStartDate").val() == 0 || $("#acStartDate").val() === 'undefine') {
        $("#error_schedule_date").text("Choose valid date");
        document.training.schedule_date.focus();
        status = false;
    }

    if (!$("#acendDate").val().trim().match(/^(((0)[1-9])|((1)[0-2]))(\/)([0-2][1-9]|(3)[0-1])(\/)(1[6-9]|[2-9]\d)?[0-9]{4}$/) || $("#acendDate").val() == 0 || $("#acendDate").val() === 'undefine') {
        $("#error_schedule_endDate").text("Choose valid date");
        document.training.schedule_endDate.focus();
        status = false;
    }

    if (!$("#ctstartime").val().trim().match(/^([0-1]?[1-9]|[0-2]{1}[0-4])[:][0-9]{2}(am|pm)$/) || $("#ctstartime").val() === 'undefine') {
        $("#error_time_start").text("Select start time");
        document.training.time_start.focus();
        status = false;
    }

    if (!$("#ctendtime").val().trim().match(/^([0-1]?[1-9]|[0-2]{1}[0-4])[:][0-9]{2}(am|pm)$/) || $("#ctendtime").val() === 'undefine') {
        $("#error_time_end").text("Select end time");
        document.training.time_end.focus();
        status = false;
    }else{
    	 $("#error_time_end").text("");
         $("#endTimeError").text("");
    }

    if (!$("#cntName").val().trim().match(/^[A-Za-z][a-zA-Z\s]{1,100}$/) || $("#cntName").val() == "" || $("#cntName").val() === 'undefine') {
        $("#error_trainer_name").text("Enter proper trainer name");
        document.training.trainer_name.focus();
        status = false;
    }



    if (!$("#cntPD").val().trim().match(/^[a-zA-Z][a-zA-Z\s,\(\)\-_?.{}\[\]]{1,500}$/) || $("#cntPD").val() == "" || $("#cntPD").val() === 'undefine') {
        $("#error_trainer_profile").text("Enter Valid details including special character (, -_?.{}[])");
        document.training.trainer_profile.focus();
        status = false;
    }


    if ($("#editorCNT").val() == "" || $("#editorCNT").val() === 'undefine') {
        $("#error_agenda").text("Description should not be empty.");
        document.training.agenda.focus();
        status = false;
    }


    var filename = $('#fileElem').val();
    array = filename.split(".");
   
    if ($("#flag").val()=="true") {
        console.log("file"+$('#fileElem').val()+"name");

        if ($('#fileElem').val() == "" || $('#fileElem').val() === 'undefine') {
            $("#error_attachment").text("Please upload valid file");
        }else if($("#fileElem").prop("files").length<1 || ($("#fileElem").prop("files")[0].size/1024/1024)>2){
    		$("#error_attachment").text('Upload valid file less than 2 MB').show();
    		var p = $("#fileElem");
    		var offset = p.offset();
    		$('html, body').animate({scrollTop: $('#drop-area').offset().top -100 }, 'slow');
    		status = false;
    		//return false;
    		} else if (array[1] != "docx" && array[1] != "pdf" && array[1] != "zip" && array[1] != "doc" && array[1] != "docm" && array[1] != "dot") {
            $("#error_attachment").text("Wrong file format.");
            document.training.attachment.focus();
            $("#progress-bar").val("");
            $("#gallery").hide();
            $("#fileName span").text('');
            $("#fileElem").val("");
            $("#progress-bar").hide();
            $(".cancel-btn").hide();
            status = false;
        }
        
        
        
    }
    if(!$("#editorCNT").val().match(/^.{1,2000}$/)){ 
    	$("#error_agenda").text("Character length should be less than 2000");
    	  document.training.agenda.focus();
    	status = false;
    }
   
    
    if ($("#department").val().trim() == "") {
        $("#error_department").text("Please choose eligible department");
        document.training.department.focus();
        status = false;
    }

    if ($("#eligible_band").val().trim() == "") {
        $("#error_eligible_band").text("Please choose eligible band");
        document.training.eligible_band.focus();
        status = false;
    }

    console.log("status : " + status);
    $('html, body').animate({
        scrollTop: $(".page-head-box").offset().top
    }, 2000);
    
    $("#acendDate").datepicker("hide");//ctendtime
    $("#ctendtime").timepicker("hide");
    
    return status;

}

/***************************              training validation  Ends	************************************/