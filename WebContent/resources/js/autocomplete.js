var globalId;

window.onload = function(e){ 
	var DemoName = $('#search1').val();
	if(!DemoName.match(/\S/)) {
		document.getElementById("search").disabled = true;
	}
}

$(document).ready(function() {"#search"
	$(function() { 

		$("#search").autocomplete({     
			source : function(request, response) {
				$.ajax({
					url : "searched",

					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data)
					{


						response($.map(data, function(v,i){
							return {
								label: v.associateId + " - " + v.associateName,
								value: v.associateId,
							};
						}));
					}
				});
			}
		});
	});
});

$( document ).ready(function() {
	var flagAjax = $('#FlagForajaxcall').val ();
	var demoId = $('#demoIdname').val ();

	if(flagAjax=="true"){
		madeCall(demoId);
	}

	if(demoId.trim().length != 0){
		enableTextBox();
	}

});




//code for autocomplete demo by shailja
$(document).ready(function() {"#search1"
	$(function() { 

		$("#search1").autocomplete({     
			source : function(request, response) {
				$.ajax({
					url : "addAttendance1",

					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data)
					{
						response($.map(data, function(v,i){
							return {
								label: v.demoName+"-"+v.demoId,
								value: v.demoName,
							};
						}));

					}
				});
			},
			select: function (e, i) {
				var id=i.item.label.split('-')[1];
				$("#demoIdV").val(id)
				getDates(id);///viral added id
				madeCall(id); 

			}

		});
	});

});


//function for viewing associate data
function madeAjaxCall(){
	$.ajax({
		type: "post",
		url: "employee",
		cache: false,				
		data:'AssociateId=' + $("#search").val() ,
		success: function(response){
			$('#result1').html("");
			var arr = [];
			var arrUnique;
			var AssociateId = $('#search').val();
			for(var x in response){
				arr.push(response[x]);
			}
			var check = checkAssociateIdPresent(arr,AssociateId);
			if(check==0){
				document.getElementById("error").innerHTML="Associate With this Id is not Available";
				arrUnique = functionForDisplayData(arr);
			}else{
				arrUnique = unique(arr);
			}
			var size = arrUnique.length;
			var appender="<thead class =addAttendanceHead> <tr><th>Id </th><th>Name</th><th>Contact</th><th>Email</th></tr></thead>  <tbody class=scrollAddAttendance>";	for(var i = 0; i < size ; i++){
				appender+= "<tr> <td>"+ arrUnique[i].associateId +"</td>"+"<td>"+ arrUnique[i].associateName+"</td>" + "<td>"+arrUnique[i].contactNumber+"</td>"+ "<td>"+ arrUnique[i].emailId+"</td></tr>"; 
			}
			appender+="</tbody>";
			globalId =$('#search').val();
			$('#search').val(""); 
			$('#result1').html(appender);
		},
		error: function(){	
			var DemoName = $('#search1').val();
			if(!DemoName.match(/\S/)) {
				if(isNaN(globalId)){
					document.getElementById("error").innerHTML="Please Enter Required enteries";
					return false;
				}
			}
			else{
				if(isNaN(globalId)){
					document.getElementById("error").innerHTML="Please Enter Associate Id";
					return false;
				}
			}
		},
		error: function(){
			if($('#search1').val() != document.getElementById("#search1")) {
				document.getElementById("error").innerHTML="Please Enter valid demo name";
				return false;
			}}
	});
} 

function checkvalue() { 
	var DemoName = $('#search1').val(); 
	if(!DemoName.match(/\S/)) {
		document.getElementById("error").innerHTML="Please Enter Valid Demo Name";
		return false;
	} 
	else{ 
		if((isNaN(globalId))||(globalId==0)){
			document.getElementById("error").innerHTML="Please Enter Associate Id";
			var s = '';
			globalId= parseInt(s) || 0;
			return false;
		}
		else{
			document.getElementById("error").innerHTML="Attendance added successfully";
			return true;
		}
	}
}


function unique(origArr) {
	var newArr = [],
	origLen = origArr.length,
	found, x, y;
	for (x = 0; x < origLen; x++) {
		found = undefined;
		for (y = 0; y < newArr.length; y++) {
			if (origArr[x].associateId==newArr[y].associateId) {
				found = true;
				document.getElementById("error").innerHTML="Associate is alredy present in Attendance Table";
			}
		}
		if (!found) {
			document.getElementById("error").innerHTML="";
			newArr.push(origArr[x]);
		}
	}
	return newArr;
}


function madeCall(id){
	$.ajax({
		type: "post",
		url: "sendingDemoId",
		cache: false,				
		data:'demoId=' + id ,
		success: function(response){
			$('#result1').html("");
			/*if(arr = []){
				document.getElementById("error").innerHTML="Associate with this Id is not Present";
			}*/
			var arr = [];
			var AssociateId = $('#search').val();
			for(var x in response){
				arr.push(response[x]);
			}
			var arrUnique = unique(arr);
			var size = arrUnique.length;
			var appender="<thead class =addAttendanceHead> <tr><th>Id </th><th>Name</th><th>Contact</th><th>Email</th></tr></thead>  <tbody class=scrollAddAttendance>";for(var i = 0; i < size ; i++){
				appender+= "<tr> <td>"+ arrUnique[i].associateId +"</td>"+"<td>"+ arrUnique[i].associateName+"</td>" + "<td>"+arrUnique[i].contactNumber+"</td>"+ "<td>"+ arrUnique[i].emailId+"</td></tr>"; 
			}
			appender+="</tbody>";
			$('#search').val(""); 
			$('#result1').html(appender);
		},
		error: function(){						
			document.getElementById("error").innerHTML="Please Enter Associate Id";
		}
	});
} 


function getDates(id){
	$('#demoIdname').val(id);
	$('#FlagForajaxcall').val("1");

	$('#sendingDates_form').submit();
}

function enableTextBox(){
	document.getElementById("search").disabled = false;
}

function checkAssociateIdPresent(arr,AssociateId){
	for (arrSize = 0; arrSize < arr.length; arrSize++){
		if(arr[arrSize].associateId == AssociateId){
			return AssociateId ;
		}
	} 
	return 0;
}


function functionForDisplayData(origArr){
	var newArr = [],
	origLen = origArr.length,
	found, origArrSize, newArrSize;
	for (origArrSize = 0; origArrSize < origLen; origArrSize++) {
		found = undefined;
		for (newArrSize = 0; newArrSize < newArr.length; newArrSize++) {
			if (origArr[origArrSize].associateId == newArr[newArrSize].associateId) {
				found = true;
			}
		}
		if (!found) {
			newArr.push(origArr[origArrSize]);
		}
	}
	return newArr;
} 