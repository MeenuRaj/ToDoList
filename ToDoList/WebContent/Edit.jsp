<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">
  <form role="form" action="EditServlet" method="post">
  <input type="hidden" name="action" value="edit">
    <div class="form-group">
      <label for="text">Task:</label>
      <input type="text" class="form-control" id="task" name ="task">
    </div>
    <div class="form-group">
      <label for="text">Due Date:</label>
      <input type="text" class="form-control" id="ddate" name ="ddate">
    </div>
    <div class="form-group">
      <label for="text">Date Completed:</label>
      <input type="text" class="form-control" id="cdate" name ="cdate">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>


</body>
</html>