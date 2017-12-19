<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<style type="text/css">
  html, body {
    width: 100%;
    height: 100%;
    padding: 0;
    margin: 0;
  }
</style>
<title>Results</title>
</head>
<body>
   <br>
   <br>
   ${results}
   <br />
   <br />
     <div class="container" style="height: 100%;">
    <div class="row" style="position: relative; top: 40%;">
      <div class="col-sm-3"></div>
      <div class="col-sm-6">
          <fieldset>
            <legend> Menu Page</legend>
              <div class="row">
                <div class="col-md-4">
                  <form method="post" action="add">
                    <div class="form-group">
                      <div class="col-sm-12">
                        <button type="submit" name="addButton" class="btn btn-primary" style="margin-top: 25px;">Add New Employee</button>
                      </div>
                    </div>
                  </form>
                </div>
                <div class="col-md-4">
                    <form method="post" action="leaveApplication">
                      <div class="form-group">
                        <div class="col-sm-12">
                          <input type = "submit" value ="Apply For Leave" name="applyButton" class="btn btn-primary" style="margin-top: 25px;">
                        </div>
                      </div>
                    </form>
                </div>
                <div class="col-md-4">
                    <form method="get" action="employees">
                      <div class="form-group">
                        <div class="col-sm-12">
                          <input type="submit" value="View Employees" class="btn btn-primary" style="margin-top: 25px;" />
                        </div>
                      </div> 
                    </form>
                </div>   
              </div>
          </fieldset>
        </div>
      </div>
  </div>
   
</body>
</html>