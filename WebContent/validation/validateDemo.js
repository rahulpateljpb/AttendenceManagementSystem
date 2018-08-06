function validateDemo(){
	var demoName=document.getElementById("demoName").value;
	var startDate=document.getElementById("startDate").value;
	var endDate=document.getElementById("endDate").value;
	var startTime=document.getElementById("startTime").value;
	var endTime=document.getElementById("endTime").value;
	var t1 = new Date();
	var parts = startTime.split(":");
	t1.setHours(parts[0],parts[1]);
	var t2 = new Date();
	parts = endTime.split(":");
	t2.setHours(parts[0],parts[1]);
	
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+'-'+mm+'-'+dd;
	

	if(demoName==""&&startDate==""&&endDate==""){
		document.getElementById("error").innerHTML="Please enter Demo Details";
		return false;
	}
	if(demoName== null || demoName == "" || demoName.length < 4 || demoName.length > 10 || !/[^\s]/.test(demoName) ){
		document.getElementById("error").innerHTML="Please enter Demo Name between 4 to 10 characters";
		return false;
	}
	if(startDate==""){
		document.getElementById("error").innerHTML="Please Select Demo Start Date";
		return false;
	}
	if(endDate==""){
		document.getElementById("error").innerHTML="Please Select Demo End Date";
		return false;
	}
	if(startDate>endDate ){
		document.getElementById("error").innerHTML="End date must be greater than start date";
		return false;
	}
	if(startDate<today)
	{
		document.getElementById("error").innerHTML="Start date must be greater than or equal to today date";
		return false;
	}
	if(endDate<today)
	{
		document.getElementById("error").innerHTML="End date must be greater than or equal to today date";
		return false;
	}
	if(startTime=="Select"){
		document.getElementById("error").innerHTML="Please Select Demo Start Time";
		return false;
	}
	if(endTime=="Select"){
		document.getElementById("error").innerHTML="Please Select Demo end Time";
		return false;
	}
	if(t1.getTime()>=t2.getTime()){
		document.getElementById("error").innerHTML="Start time must be less than end time";
		return false;
	}
	
}

function onlyAlphabets(e, t) {
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		}
		else if (e) {
			var charCode = e.which;
		}
		else { return true; }
		if (charCode == 32 || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
			return true;
		else
			return false;
	}
	catch (err) {
		alert(err.Description);
	}

}
