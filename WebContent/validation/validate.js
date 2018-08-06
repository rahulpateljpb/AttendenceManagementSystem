function validateReport(){
	var demoName=document.getElementById("demoName").value;
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;
	if(demoName=="Select"){
		if(fromDate==""){
			if(toDate==""){
 				document.getElementById("error").innerHTML="Please Select Demo or Dates";
				return false;
			}
			else 
				document.getElementById("error").innerHTML="Please Select From Date";
			return false;
		}
		else 
			if(toDate==""){
				document.getElementById("error").innerHTML="Please Select To Date";
				return false;
			}
	}

	if(demoName!="Select"){
		if(fromDate==""&&toDate!=""){
			document.getElementById("error").innerHTML="Please select From Date";
			return false;
		}

		if(fromDate!=""&&toDate==""){
			document.getElementById("error").innerHTML="Please select To Date";
			return false;
		}
	}
	if(fromDate>toDate){
		document.getElementById("error").innerHTML="To date must be greater than from Date";
		return false;
	}
}