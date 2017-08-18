<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
				<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

				<html xmlns="http://www.w3.org/1999/xhtml">
					<head>
						<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
							<title>Positions Search</title>
							<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/demo.css">
								<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style1.css">
									<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/mytable.css">
										<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/tablenew.css">
											<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
											<script type="text/javascript">
												function formSubmit(key,value)
												{   //alert("key==="+key+"===(key).value==="+document.getElementById(key).value+"===value==="+value);
												    document.getElementById(key).value = value;
												    document.getElementById("applyit").submit();
												}
											</script>
										</head>

										<body>
											<center>
											<br/><br/><br/><br/>
											 <h1>${greeting} </h1>
											<!--<h1>Positions Results</h1>-->
											<form class="form-wrapper" action="searchjobs" method="get" name="jobsearch" id="jobsearch">
											    <input type="text" id="what" name="q" placeholder="Job title, Company or Keywords">
											    <input type="text" id="where" name="l" placeholder="City, zip or state">
											    <input type="submit" value="Search&nbsp;Jobs" id="fj">
											</form>
											<!--<form class="form-wrapper" action="savedata" method="post" name="savedata" id="savedata">
											    <input type="text" id="what" name="q" placeholder="Job title, Company or Keywords">
											    <input type="text" id="where" name="l" placeholder="City, zip or state">
											    <input type="submit" value="Retrieve&nbsp;Data" id="sn">
											</form>-->
											</center>
						            </body>
                   </html>

