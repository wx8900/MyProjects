<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Spring MVC Resource example</title>
 
 
<script type="text/javascript"
    src="${pageContext.request.contextPath}/scripts/common.js"></script>
 
 
<%-- <link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/styles/common.css">
 
</head>
<body>
<pre> --%>
<!-- Config: /styles/** ==> /WEB-INF/resources/css/
----------------------------------------------
        /styles/common.css ==> /WEB-INF/resources/css/common.css
        /styles/path1/abc.css ==> /WEB-INF/resources/css/path1/abc.css
        /styles/path1/path2/abc.css ==> /WEB-INF/resources/css/path1/path2/abc.css -->
 
       
<h2>Access link :</h2> 
        
        http://localhost:8080/SendResume/staticResourceTest
        
        http://localhost:8080/SendResume/scripts/common.js
        
        http://localhost:8080/SendResume/styles/common.css
</pre>
 
 
    <div class="red-text">Red text</div>
    <br>
    <div class="green-text">Green text</div>
    <br>
 
    <input type="button" class="button" onclick="sayHello();"
        value="Click me!">
 
</body>
</html>