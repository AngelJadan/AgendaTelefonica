function valcedula() {
	var cad = document.getElementById("cedula").value.trim();
	var total = 0;
	var longitud = cad.length;
	var longcheck = longitud - 1;
	if (longitud > 10) {
		document.getElementById("smscc").style.display = "visible";
	}
	if (cad !== "" && longitud == 10) {
		for (i = 0; i < longcheck; i++) {
			if (i % 2 === 0) {
				var aux = cad.charAt(i) * 2;
				if (aux > 9) aux -= 9;
				total += aux;
			} else {
				total += parseInt(cad.charAt(i)); // parseInt o concatenará en lugar de sumar
			}
		}

		total = total % 10 ? 10 - total % 10 : 0;

		if (cad.charAt(longitud - 1) == total) {
			console.log("valido");
			document.getElementById("cedula").style.color = 'green';
			document.getElementById("smscc").style.display = "none";
		} else {
			console.log("valido")
			document.getElementById("smscc").style.display = "visible";
			document.getElementById("cedula").style.color = 'red';
		}
	}
}

/**
 * Valia email.
 */
function vemail() {
	var mail = document.getElementById("email").value;

	re = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
	if (!re.exec(mail)) {
		document.getElementById("email").style.color = 'red';
	}
	else {
		document.getElementById("email").style.color = 'green';		
	}
}