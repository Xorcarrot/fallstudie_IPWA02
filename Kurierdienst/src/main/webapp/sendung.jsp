<html>
<head>

<style type="text/css">
	table, tr, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
</style>

<script>
	function briefMaxWert() {
		var x = document.getElementById('laengeInput');
		x.pattern = "(([2][9][0-7])|([2][0-8][0-9])|([1][0-9][0-9])|([1-9][0-9])|([1-9]))";
		var y = document.getElementById('breiteInput');
		y.pattern = "(([2][0-1][0])|([2][0][0-9])|([1][0-9][0-9])|([1-9][0-9])|([1-9]))";
		var v = document.getElementById('hoeheInput');
		v.pattern = "(50|[1-4][0-9]|[0-9])";
		var g = document.getElementById('gewichtInput');
		g.pattern = "(2000|([1][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|[1-9])";
	}
	
	function paketMaxWert() {
		var x = document.getElementById('laengeInput');
		x.pattern = "(2000|([1][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|[1-9])";
		var y = document.getElementById('breiteInput');
		y.pattern = "(2000|([1][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|[1-9])";
		var v = document.getElementById('hoeheInput');
		v.pattern = "(2000|([1][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|[1-9])";
		var g = document.getElementById('gewichtInput');
		g.pattern = "(50000|([1-4][0-9][0-9][0-9][0-9])|([1-9][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|[1-9])";
	}
</script>

</head>
<body>

<form action="sendungErstellen">

	<h2>Sendung erstellen:</h2>
 	<table>
  		<tr>
    		<td><input type="radio" name="paket" value="paket" onclick="paketMaxWert()" required/> Paket</td>
    		<td><input type="radio" name="paket" value="brief" onclick="briefMaxWert()"/> Brief</td>
  		</tr>
  		
  		<tr id="laenge">
    		<td>Länge: </td>
    		<td><input id="laengeInput" type="text" name="laenge" maxlength="4" required title="Länge von 2000mm bei Paketen und 297mm bei Briefen möglich"/></td>
    		<td>mm</td>
  		</tr>
  		<tr id="breite">
    		<td>Breite: </td>
    		<td><input id="breiteInput" type="text" name="breite" maxlength="4" required title="Breite von 2000mm bei Paketen und 210mm bei Briefen möglich"/></td>
    		<td>mm</td>
  		</tr>
  		<tr id="hoehe">
    		<td>Höhe: </td>
    		<td><input id="hoeheInput" type="text" name="hoehe" maxlength="4" required title="Höhe von 2000mm bei Paketen und 50mm bei Briefen möglich"/></td>
    		<td>mm</td>
  		</tr>
  		<tr id="gewicht">
    		<td>Gewicht: </td>
    		<td><input id="gewichtInput" type="text" name="gewicht" required title="Gewicht von 50000g bei Paketen und 2000g bei Briefen möglich"/></td>
    		<td>Gramm</td>
  		</tr>
  		
	</table> 
	
	<h2>Empfänger erstellen:</h2>
	<table>
		<tr>
    		<td>Vorname: </td>
    		<td><input type="text" name="vorname" pattern="[a-zA-Z äÄüÜöÖß\-]*" required title="Ein Vorname kann alle Buchstaben enthalten"/></td>
  		</tr>
  		<tr>
    		<td>Nachname: </td>
    		<td><input type="text" name="nachname" pattern="[a-zA-Z äÄüÜöÖß\-]*" required title="Ein Nachname kann alle Buchstaben enthalten"/></td>
  		</tr>
  		<tr>
    		<td>Land: </td>
    		<td>
    			<input type="radio" name="land" value="Deutschland" checked/> Deutschland 
    			<input type="radio" name="land" value="Österreich"/> Österreich
    		</td>
  		</tr>
  		<tr>
    		<td>Postleitzahl: </td>
    		<td><input type="text" name="postleitzahl" pattern="[0-9]*" minlength="4" maxlength="5" required title="Eine Postleitzahl kann nur Zahlen enthalten"/></td>
  		</tr>
  		<tr>
    		<td>Wohnort: </td>
    		<td><input type="text" name="wohnort" pattern="[a-zA-Z äÄüÜöÖß\-]*" required title="Ein Wohnort kann alle Buchstaben enthalten"/></td>
  		</tr>
  		<tr>
    		<td>Adresse: </td>
    		<td><input type="text" name="adresse" pattern="[a-zA-Z0-9 äÄüÜöÖß\-\.]*" required title="Eine Adresse kann alle Buchstaben und Zahlen enthalten"/></td>
  		</tr>
	</table>
	
	<input style="margin: 10px" type="submit" value="Auftreig erteilen">
</form>

</body>
</html>
