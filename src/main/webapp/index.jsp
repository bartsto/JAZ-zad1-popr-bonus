<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wylicz raty kredytu</title>
</head>
<body>
<h1>Oblicz swoje raty kredytu!</h1><br/>

<form action="/calculate" method="post">
	<label for="kwota">Wnioskowana kwota kredytu: 
	<input type="number" id="kwota" name="kwota"/></label><br/>
	<label for="liczbaRat">Liczba rat:
	<input type="number" id="liczbaRat" name="liczbaRat"/>  </label><br/>
	<label for="oprocentowanie">Oprocentowanie: 
	<input type="number"  step="0.01" id="oprocentowanie" name="oprocentowanie" /></label><br/>
	<label for="oplataStala">Opłata stała: 
	<input type="number" step="0.01" id="oplataStala" name="oplataStala" /> </label><br/>
	<label for="rodzajRat">Rodzaj rat: <br/>
	<input type="radio" name="rodzajRat" value="malejaca"> malejąca<br>
    <input type="radio" name="rodzajRat" value="stala"> stała <br/></label>
	<input type="submit" name="action" value="Oblicz!"/>
	<input type="submit" name="action" value="Generuj PDF!"/>
</form>
</body>
</html>