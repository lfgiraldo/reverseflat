

function language() {
    var lct="es";
    if (navigator.language) {
       lct=navigator.language.toLowerCase().substring(0, 2);
    } else if (navigator.userLanguage) {
       lct=navigator.userLanguage.toLowerCase().substring(0, 2);
    } else if (navigator.userAgent.indexOf("[")!=-1) {
       var inicio=navigator.userAgent.indexOf("[");
       var fin=navigator.userAgent.indexOf("]");
       lct=navigator.userAgent.substring(inicio+1, fin).toLowerCase();
	}
	
    return lct;
 }
 document.getElementById("locale").value = language();
