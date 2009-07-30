<%
    try{
%>

<%--IMPRESCINDIBLE INCLUIR commonVariables.jsp EN LAS PÁGINAS QUE INCLUYAN publicHeader.jsp --%>
    <%@ include file="commonVariables.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Minimorum - Pay for the minimum</title>
    <%@ include file="metatags.jsp" %>
    <%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <link href="css/SqueezeBox.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
    <%@ include file="commonScripts.jsp" %>
    <div id="contenido">
        <div id="cabecera">
            <div id="logo">
                <a href="index.htm"><img src="images/logotipo_header.png" border="0" /></a>
            </div>
            <div id="logueo">
                <form name="formlog" action="" method="post">
                    <input type="text" name="usuario" value="Usuario" class="usu" />
                    <input type="password" name="contrasena" value="Contraseña" class="usu" />
                    <a href="" title="Registrarse" class="link">Registrarse</a> <input type="submit" name="login" value="Login" class="botlog" />
                </form>
            </div>
        </div>
        <div id="centro">
            <%@ include file="publicClosing.jsp" %>
            <!--
            <div id="menu1">
                <ul>
                    <li class="texmenu">Subastas Activas</li>
                    <li class="texmenu">
                        <img src="images/bullet_circle_orange.png" />
                        <img src="images/bullet_circle_white.png" />
                        <img src="images/bullet_circle_white.png" />
                        Sony Ericsson w580i
                    </li>
                    <li>
                    	<img src="images/separador_01.png" />
                    </li>
                    <li class="texmenu">
                        <img src="images/bullet_circle_orange.png" />
                        <img src="images/bullet_circle_orange.png" />
                        <img src="images/bullet_circle_orange.png" />
                        ipod Touch 16GB
                    </li>
                    <li>
                    	<img src="images/separador_01.png" />
                    </li>
                    <li class="texmenu">
                        <img src="images/bullet_circle_orange.png" />
                        <img src="images/bullet_circle_orange.png" />
                        <img src="images/bullet_circle_white.png" />
                        Plasma Lg 42"
                    </li>
                    <li>
                    	<img src="images/separador_01.png" />
                    </li>
                </ul>
            </div>
            -->
            <div id="menu2">
                <ul>
                    <li><a href="">Inicio</a></li>
                    <li><img src="images/separador_02.png" /></li>
                    <li><a href="">Subastas Abiertas</a></li>
                    <li><img src="images/separador_02.png" /></li>
                    <li><a href="">Subastas Cerradas</a></li>
                    <li><img src="images/separador_02.png" /></li>
                    <li><a href="">Subastas Estratégicas</a></li>
                    <li><img src="images/separador_02.png" /></li>
                    <li><a href="">Fichas</a></li>
                    <li><img src="images/separador_02.png" /></li>
                    <li><a href="">Como Funciona</a></li>
                </ul>
            </div>
            <div id="productos">
            	<div id="combos">
            	<select name="categorias">
                	<option value="0">Categoria</option>
                </select>
                <select name="productos">
	                <option value="0">Producto</option>
                </select>
                </div>


<%
    List<Auction> auctionsList = (List<Auction>) request.getSession().getAttribute("activeAuctions");
	List<Auctioncategory> categories = (List<Auctioncategory>) request.getSession().getAttribute("categories");
    if (userLocale == null){
        userLocale = "es";
    }
if ( auctionsList != null)
{
	int i = 0;
    int half = Math.round(auctionsList.size()/2)-1; //Se resta 1 porque la i comienza en cero.
    for(Auction currentAuction : auctionsList ){
        List<Auctiontexttranslation> translatedTexts = currentAuction.getAuctiontexttranslationCollection();
        Auctiontexttranslation currentText = new Auctiontexttranslation();

        for(Auctiontexttranslation text : translatedTexts){
            if (text.getLanguageCode().equalsIgnoreCase(userLocale)){
                currentText = text;
                break;
            }
        }
        List<Bid> bids = (List<Bid>)currentAuction.getBidCollection();
        float percentage = 0;
        if ( bids != null){
            int minBids = currentAuction.getMinBids();
            int widthBar = 224;
            int totalBids = bids.size()*2;
            percentage = (float)totalBids/minBids;
            percentage = widthBar * percentage;
        }

        String link = "";
		if (user != null){
			link = "RestrictedArea?option=refresh&idAuction="+currentAuction.getIdAuction()+"&type="+request.getAttribute("type");
		}else{
			link = "PublicArea?option=info&idAuction="+currentAuction.getIdAuction();
		}
%>
                <div id="detalle">
                    <a href="<%=link%>"><img src="<%=currentAuction.getCircledFileName()%>" class="imgproducto" /></a>
<%
                    HashMap winnerBids = (HashMap)request.getSession().getAttribute("winnerBids");
                    Bid currentWinnerBid = (Bid) winnerBids.get(currentAuction.getIdAuction());
                    if(currentWinnerBid != null){
                        float winner = currentWinnerBid.getBidAmount();
                        int productCost = Math.round(winner/5);
                        productCost = (productCost + 1) * 5;
%>
                    <div id="detalle_texto">
                    	<p><span class="texto_titulo"><a href="<%=link%>"><%=currentText.getTitle()%></a></span></p>
                        <p>Cierre en: <span class="ctexto1">600 mn</span> <span class="reloj"><img src="images/time_out_icon.gif" /> 18:00:15</span></p>
                        <p>Pujas: <span class="ctexto2">2 fichas</span></span></p>
                        <p><img src="images/cargador_tiempo.jpg" /></p>
                        <p>Minimum ganador menor a <%=productCost%>pts <a href=""><img src="images/butt_puja_01.gif" border="0" style="vertical-align:middle;" /></a></p>
<%
                        }
%>                      
                    </div>
                </div>
                <div style="padding:10px"><img src="images/line_separate_product.jpg" /></div>
<%
        i++;
        if (i==3)
            break;
    }
}
%>

                <div id="buscador">
                	<input type="text" name="palabra" class="caja" maxlength="28" />
                    <input type="image" src="images/butt_buscar_inferior.gif" name="buscar" class="boton" />
                </div>
                <div id="paginador">
                	Ver m&aacute;s <a href="">1 - 2 - 3 - 4 - > >></a>
                    <span class="texto_busca">Buscar p&aacute;gina</span> <input type="text" name="numero" class="cajita" />
                    <a href=""><img src="images/martillo_01.gif" border="0" /></a>
                </div>
            </div>
                <div id="aprende">
                    <p>As&iacute; se gana en <span class="texto_naranja">mini</span><span class="texto_gris">morum</span><span class="texto_amarillo">!</span></p>
                    <img src="images/imagen_explicacion.jpg" />
                </div>
                <div id="pie">
                <ul>
                    <li><a href="">About us</a></li>
                    <li>|</li>
                    <li><a href="">Contact</a></li>
                    <li>|</li>
                    <li><a href="">Terms</a></li>
                    <li>|</li>
                    <li><a href="">Privacy</a></li>
                    <li>|</li>
                    <li><a href="index.htm"><span class="texto_naranja">mini</span><span class="texto_gris1">morum</span></a></li>
                </ul>
                </div>
            </div>
            
        </div>
</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en indexTest.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>