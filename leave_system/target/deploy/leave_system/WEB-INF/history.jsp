<%@ page language="java" import="leave.system.Leave, leave.system.Accrual, java.util.Arrays, java.util.Calendar" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<style type="text/css">
  html, body {
    width: 100%;
    height: 100%;
    padding: 0;
    margin: 0;
  }
</style>
<title>Leave Application</title>
</head>
<body>
  <div class="container" style="height: 100%;">
        <div class="col-sm-3">
      	<b>Employee Number: ${empNumber}</b><br>
      	<b>Firstname: ${firstname}</b><br>
      	<b>Lastname: ${lastname}</b><br>
      	<b>StartDate: ${startDate}<b/><br/>
      	<b></b>
      </div>
    <div class="row" style="position: relative; top: 40%;">
      <div class="col-sm-6">
      	<% if((request.getAttribute("results") != null)) {
      		out.println(request.getAttribute("<b style='color:red;'>" + request.getAttribute("results") + "</b>"));
      	} else { %>
      	
         <table class="table table-striped">
         <thead>
            <tr>
               <th>#</th>
               <th>Start Date</th>
               <th>End Date</th>
            </tr>
         </thead>
         <tbody>
         	<c:set var="count" value="0" ></c:set>
         	
         	<c:forEach var="leave" items="${leaves}">
         		<c:set var="count" value="${count + 1 }"></c:set>
				<tr>
         			
               		<td>${count }</td>
               		<td>${leave.getStartDate()}</td>
               		<td>${leave.getEndDate()}</td>
               </tr>
			</c:forEach>
         
            <% 
            	Leave [] leave = (Leave[])request.getAttribute("leaves");
            	Arrays.sort(leave);
           %>
         
         
         </tbody>
   		</table>
   		
   		<table class="table table-striped">
         <thead>
            <tr>
               <th>Date</th>
               <th>Days Accrued</th>
               <th>Leave Taken</th>
            </tr>
         </thead>
         <tbody>
            <% 
            	
            	Accrual [] accruals = (Accrual[])request.getAttribute("accruals");
            	int count = 0;
               	for(int i = 0; i < accruals.length; i++)
               	{
            %>
	               	<tr>
	         			
	               		<td><% out.print(accruals[i].getDay() + " " + accruals[i].getMonth() + " " + accruals[i].getYear()); %></td>
	               		<td><% out.print(accruals[i].getDaysAccrued()); %></td>
	               		<td>
	               			<% 
	               				while(count < leave.length) {
	               					Calendar date = Calendar.getInstance();
	               					date.setTime(leave[count].getStartDateFromString());
	               					if(accruals[i].getYear() == date.get(Calendar.YEAR) && accruals[i].getIntMonth() == date.get(Calendar.MONTH)) {
	               						out.println("<p style='font-size: 15px'>" + leave[count].getStartDate() + " <b>to</b> " + leave[count].getEndDate() + "</p>");
	               						count++;
	               						continue;
	               					}
	               					break;
	               				}
	               			%>
	               		</td>
	               </tr>
               
         <%
           		}
         %>
         </tbody>
   		</table>
   		
   		<% } %>
      </div>
   </div>
  </div>
</body>
</html>