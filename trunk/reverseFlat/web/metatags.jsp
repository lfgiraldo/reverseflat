    
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    <meta name="title" content="Snoits - Subastas Estrat&eacute;gicas">
    <meta name="description" content="Subastas estrat&eacute;gicas, gana la puja &uacute;nica m&aacute;s baja. Productos nuevos por menos de la mitad de su precio.">
<%
    List<Auction> allAuctions = (List<Auction>) request.getSession().getAttribute("activeAuctions");
    String keywords = "";
    for(Auction currentAuction : allAuctions ){
        userLocale = (String) request.getSession().getAttribute("locale");
        List<Auctiontexttranslation> translatedTexts = currentAuction.getAuctiontexttranslationCollection();
        Auctiontexttranslation currentText = new Auctiontexttranslation();

        for(Auctiontexttranslation text : translatedTexts){
            if (text.getLanguageCode().equalsIgnoreCase(userLocale)){
                currentText = text;
                break;
            }
        }
        keywords += currentText.getTitle().replaceAll("\"","&quot;");
        keywords += ", ";
    }
%>
    <meta name="keywords" content="<%=keywords%>">
<%--    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="Pragma" content="no-cache" />--%>
    <meta http-equiv="Expires" content="0" />
    <meta name="author" content="Snoits">
    <meta name="publisher" content="Snoits" />
    <meta name="copyright" content="Snoits" />
    <meta name="subject" content="Subastas inversas, subastas estrat&eacute;gicas" />
    <meta name="generator" content="snoits.com" />
    <meta name="Language" content="<%=request.getLocale().getLanguage()%>" />
    <meta http-equiv="content-language" content="<%=request.getLocale().getCountry()%>" />
    <meta name="distribution" content="Global">
    <meta name="robots" content="index,follow" />
    <meta name="revisit-after" content="3 days" />
    <meta name="verify-v1" content="m24mKOnWgvM6th4iesyQOZz7aWrIJatfmYBfnvXyk1o=" />
    <meta name="page-topic" content="Subastas estrat&eacute;gicas, Subastas inversas, Subastas a la baja" />
    <meta name="audience" content="all" />