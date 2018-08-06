function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
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
function trim(e1)
{
	el.value = el.value.
	replace(/(^\s*)|(\s*$)/gi, ""). 
	replace(/[ ]{2,}/gi, " ").  
	replace(/\n +/, "\n"); 
	return e1;
}

/* for js validation

function validate(){
	var id=document.getElementById("associateId").value;
	var name=document.getElementById("text").value;
	var contact=document.getElementById("contactNumber").value;
	var email=document.getElementById("myEmail").value;
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	var mail= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var phoneno =/^\(?([7-9]{1})\)?[-. ]?([0-9]{5})[-. ]?([0-9]{4})$/;


	if(id==0&& name==""&& contact=="" && email=="")
	{
		document.getElementById("error").innerHTML="Please enter associate details";
		return false;
	}
	if(id ==0 || id < 10000 || id > 9999999)
	{
		document.getElementById("error").innerHTML="Please enter Associate Id";
		return false;
	}
	if(name == null || name == "" || name.length < 4 || name.length > 30 || !/[^\s]/.test(name))
	{
		document.getElementById("error").innerHTML="Please enter Associate name between 4 to 30 characters";
		return false;
	}

	if((contact== null || contact == "") || contact.length != 10 || !(phoneno.test(contact)))
	{
		document.getElementById("error").innerHTML="Please enter Mobile Number in numerics";
		return false;
	}
	if((email== null || email == "") || !(mail.test(email)))
	{
		document.getElementById("error").innerHTML="Please enter proper email id";
		return false;

	}
	 return true;
}
*/