<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add Item To The File</title>
</head>
<body>
<h1>Add Item To The File</h1>

<form method = "get" action="addItemToFile">

  <p> Название: <br />
   <input type="text" name="name" pattern="^[А-Яа-яЁёa-zA-Z]+$" /><br /> </p>
   <p>Цена: <br />
   <input type="text" name="price" value="0.0" pattern="[0-9]+[\.]?[0-9]+"/><br /> </p>
   
   <p>Описание товара: <br />
   <textarea name="comment"></textarea> <br /></p>

   <input type="submit" value="Add Item To File">

</form>
  
</body>
</html>