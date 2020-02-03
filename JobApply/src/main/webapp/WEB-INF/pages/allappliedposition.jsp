<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="companyHost" value="https://www.indeed.com/" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Positions List</title>
<link rel="stylesheet" type="text/css"
	href="${context}/styles/demo.css">
<link rel="stylesheet" type="text/css"
	href="${context}/styles/style1.css">
<link rel="stylesheet" type="text/css"
	href="${context}/styles/mytable.css">
<link rel="stylesheet" type="text/css"
	href="${context}/styles/tablenew.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
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
		<!--<h1>Positions Results</h1>-->
		<!--<form class="form-wrapper" action="searchjobs" method="get" name="jobsearch" id="jobsearch">
		    <input type="text" id="what" name="q" placeholder="Job title, Company or Keywords">
		    <input type="text" id="where" name="l" placeholder="City, zip or state">
		    <input type="submit" value="Search&nbsp;Jobs" id="fj">
		</form>-->
	</center>
	<table class="table-fill" style="margin-top: 100px;">
		<thead>
			<tr style="height: 50px;">
				<th scope="col" class="text-left">Like</th>
				<th scope="col" class="text-left">Title</th>
				<th scope="col" class="text-left">Company</th>
				<th scope="col" class="text-left">Salary</th>
				<th scope="col" class="text-left">Location</th>
				<th scope="col" class="text-left">Description</th>
				<th scope="col" class="text-left">Reviews</th>
				<th scope="col" class="text-left">Sponsor</th>
				<!--<th scope="col" class="text-left">JobCreated</th>-->
				<th scope="col" class="text-left">Apply Date</th>
				<th scope="col" class="text-left">Apply Status</th>
			</tr>
		</thead>
		<tbody class="table-hover">
			<c:forEach items="${positions}" var="position">
				<tr style="align: center;">
					<td class="text-left">
						<!--${position.id}--> <c:choose>
							<c:when test="${position.likeit==1}">
								<img src="<%=request.getContextPath()%>/images/smile0.jpg"
									alt="Smile" height="35" width="38" align="middle">
							</c:when>
							<c:otherwise>
								<form action="likeit" method="get" name="likeit" id="likeit">
									<input type="hidden" id="likeId" name="likeId"
										value="${position.id}"> <input id="like" type="submit"
										value="Like&nbsp;it" class="likeitButton">
								</form>
							</c:otherwise>
						</c:choose> 
					<c:set var="title" value="${position.title}" />
					<td class="text-left">
						<c:choose>
							<c:when test="${position.titlehref!=null}">
								<a href="${companyHost}${position.titlehref}" target="_blank">${fn:substring(title, 0, 17)} </a>
							</c:when>
							<c:when test="${position.titlehref==null}">
								${fn:substring(title, 0, 18)}
							</c:when>
						</c:choose>
					</td>
					<c:set var="company" value="${position.company}" />
					<td class="text-left">
						<c:choose>
							<c:when test="${position.companyhref!=null && position.companyhref!=''}">
								<a href="${position.companyhref}" target="_blank">${fn:substring(company, 0, 15)} </a>
							</c:when>
							<c:when test="${position.companyhref==null || position.companyhref==''}">
								${fn:substring(company, 0, 15)}
							</c:when>
						</c:choose>
					</td>
					<td class="text-left">${position.salary}</td>
					<c:set var="location" value="${position.location}" />
					<td class="text-left">${fn:substring(location, 0, 18)}</td>
					<c:set var="description" value="${position.description}" />
					<td class="text-left"><a href="${companyHost}${position.titlehref}" target="_blank">${fn:substring(description, 0, 35)}</a></td>
					<td class="text-left">${position.reviews}</td>
					<!--<td class="text-left">${position.stars}</td>-->
					<c:choose>
						<c:when test="${position.sponsored==1}">
							<td class="text-left">sponsored</td>
						</c:when>
						<c:when test="${position.sponsored!=1}">
							<td class="text-left">&nbsp;</td>
						</c:when>
					</c:choose>
					<!--<td class="text-left">${position.jobcreated}</td>-->
					<td class="text-left"><c:choose>
							<c:when test="${position.jobapplydate!=null}">
							     ${position.jobapplydate}
							 </c:when>
						</c:choose></td>
					<!--<td class="text-left">${position.comments}</td>-->
					<td class="text-left"><c:choose>
							<c:when test="${position.jobapplydate==null}">
								<form action="apply" method="get" name="applyit" id="applyit">
									<input type="hidden" id="applyId" name="applyId"
										value="${position.id}"> <a href="javascript:;"
										class="a-btn" id="apply"
										onclick="formSubmit('applyId', '${position.id}');"> <span
										class="a-btn-slide-text">$99</span> <img
										src="<%=request.getContextPath()%>/images/applyit.png"
										alt="Maps"> <span class="a-btn-text"> <small>
										</small>Apply&nbsp;Now
									</span>
									</a>
								</form>
							</c:when>
							<c:otherwise>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Already Appiled
                            </c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<center>
		<form action="searchjobs" method="get" name="listAllPages"
			id="listAllPages">
			<input type="hidden" id="what" name="q" value=""> <input
				type="hidden" id="where" name="l" value=""> <input
				type="submit" value="Return&nbsp;Home&nbsp;Pages" class="backButton">
		</form>
	</center>
</body>
</html>