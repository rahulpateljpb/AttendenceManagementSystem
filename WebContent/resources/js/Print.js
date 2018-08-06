$(window).load(function(){
	function printData()
	{var divToPrint =document.getElementById("printTable");



//	adding  css to table that we have to print 
	var htmlToPrint = '' +
	'<style type="text/css">' +
	'table, td, th {border: .5px solid  #cccccc; }'+
	'th {background-color: #7AA9DD  }'+
	'table {  border-collapse: collapse;width: 100%; text-transform: uppercase; font-size: 12px;}'+
	'th, td { padding: 0px;}'+
	'.footer { position: absolute; bottom:0;;padding: 1rem; }'+
	'</style>  '+

//	adding  header  to the table 
	
	'<img src="images/tata_h.png" ; alt="logo" align="right" height="50" width="80" /> ';
	htmlToPrint += divToPrint.outerHTML ;
	
//	adding  footer to the table 
	htmlToPrint+=' <div class="footer"> <img src="images/tata_f.png" ; alt="logo"  height="15" width="300" /> </div>';
	newWin = window.open("");
	newWin.document.write(htmlToPrint);
	newWin.print();
	newWin.close();
	}

	$('button').on('click',function(){
		printData();
	})
});//]]> 