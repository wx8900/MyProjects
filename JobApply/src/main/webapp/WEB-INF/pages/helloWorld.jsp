<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring pagination using data tables</title>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script type="text/javascript">

    //Plug-in to fetch page data 
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
	{
		return {
			"iStart":         oSettings._iDisplayStart,
			"iEnd":           oSettings.fnDisplayEnd(),
			"iLength":        oSettings._iDisplayLength,
			"iTotal":         oSettings.fnRecordsTotal(),
			"iFilteredTotal": oSettings.fnRecordsDisplay(),
			"iPage":          oSettings._iDisplayLength === -1 ?
				0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
			"iTotalPages":    oSettings._iDisplayLength === -1 ?
				0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
		};
	};

$(document).ready(function() {

	$("#example").dataTable( {
        "bProcessing": true,
        "bServerSide": true,
        "sort": "position",
        //bStateSave variable you can use to save state on client cookies: set value "true" 
        "bStateSave": false,
        //Default: Page display length
        "iDisplayLength": 10,
        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
        "iDisplayStart": 0,
        "fnDrawCallback": function () {
            //Get page numer on client. Please note: number start from 0 So
            //for the first page you will see 0 second page 1 third page 2...
            //Un-comment below alert to see page number
        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
        },         
        "sAjaxSource": "springPaginationDataTables.web",
        "aoColumns": [
            { "mData": "name" },
            { "mData": "position" },
            { "mData": "office" },
            { "mData": "phone" },
            { "mData": "start_date" },
            { "mData": "salary" },
             
        ]
    } );

} );

</script>
</head>
<body>
<form:form action="" method="GET">
<h2 >Spring MVC pagination using data tables<br><br></h2>
<table width="70%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
	<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
     			<th>Position</th>
     			<th>Office</th>
     			<th>Phone</th>
     			<th>Start Date</th>
     			<th>Salary</th>
            </tr>
        </thead>       
    </table>
    </td></tr></table>
</form:form>
</body>
</html>