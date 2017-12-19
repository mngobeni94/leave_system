<%@ page language="java" import="leave.system.Employee" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Employees List</title>
</head>
<body>
  <div class="container" style="height: 100%;">
    <div class="row" style="position: relative; top: 40%;">
      <div class="col-sm-3"></div>
      <div class="col-sm-6">
      	<% if(!(request.getAttribute("results") == (null))) {
      		out.println("<b style='color:red;'>" + request.getAttribute("results") + "</b>");
      	} else { %>
         <table class="table table-striped">
         <thead>
            <tr> 
               <th>Employee Number</th>
               <th>Firstname</th>
               <th>Lastname</th>
               <th>Action</th>
            </tr>
         </thead>
         <tbody>
            <% 
            	Employee [] emp = (Employee[])request.getAttribute("leaves");
               	for(int i = 0; i < emp.length; i++)
               	{
            %>
            	
               	<tr>
         			
               		<td><% out.print(emp[i].getEmpNumber()); %></td>
               		<td><% out.print(emp[i].getLastname()); %></td>
               		<td><% out.print(emp[i].getFirstname()); %></td>
               		<td>
               			<form action="historyServlet">
               				<input type="hidden" name="firstname" value="<% out.print(emp[i].getFirstname()); %>" />
               				<input type="hidden" name="lastname" value="<% out.print(emp[i].getLastname()); %>" />
               				<input type="hidden" name="empNumber" value="<% out.print(emp[i].getEmpNumber()); %>" />
               				<input type="hidden" name="startDate" value="<% out.print(emp[i].getStartDate()); %>"/>
               				<input type="hidden" name="minLeaveDays" value="<% out.print(emp[i].getMinLeaveDaysAllocated()); %>"/>
               				<input type="submit" class="btn btn-primary" name="submit" value="History" />
               			</form>
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