<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get Item List From File</title>
</head>
<body>
<h1>Get Item List From File</h1>

<form method = "get" action="getItemListFromFile">
   
   <p>Имя файла: <br />
   <input type="text" name="file_name" pattern="^[А-Яа-яЁёa-zA-Z0-9]+$" maxlength=20 /><br /> </p>
   
   <p>Тип файла: <br />
   <select name="file_type">
  		<option value="xml" selected>xml</option>
  		<option value="json">json</option>
	</select>	
	</p>
	
	<input type="submit" value="PrintList">
</form>	
</body>
</html>