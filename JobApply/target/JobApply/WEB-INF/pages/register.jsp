<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up Form</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/register.css">
    </head>
    <body>
    	<h1>Sign Up Your basic info</h1>
      <form action="register" method="post">
        <fieldset>
          <label for="name">Username:</label>
          <input type="text" id="name" name="username">
          <label for="password">Password:</label>
          <input type="password" id="password" name="password">
          <label for="mail">Email:</label>
          <input type="email" id="email" name="email">
          <label for="state">State:</label>
          <input type="text" id="state" name="state">
          <label for="city">City:</label>
          <input type="text" id="city" name="city">
          <label for="street">Street:</label>
          <input type="text" id="street" name="street">
          <label for="zipcode">Zipcode:</label>
          <input type="text" id="zipcode" name="zipcode">
        </fieldset>
        
        <button type="submit">Sign Up</button>
      </form>
      
    </body>
</html>