<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC Resource example</title>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/scripts/common.js"></script>
</head>
 
<body>       
<h2>Access link :</h2> 
        
       <!--  http://localhost:8080/SendResume/staticResourceTest
        
        http://localhost:8080/SendResume/scripts/common.js
        
        http://localhost:8080/SendResume/styles/common.css -->
 
 
    <div class="red-text">Red text</div>
    <br>
    <div class="green-text">Green text</div>
    <br>
 
    <input type="button" class="button" onclick="sayHello();"
        value="Click me!">
 
</body>
</html>