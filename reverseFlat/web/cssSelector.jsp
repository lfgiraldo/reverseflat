<%
String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
boolean isSafari = ( ua != null && ua.indexOf( "Safari/" ) != -1 );
response.setHeader( "Vary", "User-Agent" );

if( isFirefox ){ %>
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
    <link href="css/slideItStyles.css" rel="stylesheet" type="text/css" />
<% } else if( isMSIE ){ %>
	<link href="css/css2ie.css" rel="stylesheet" type="text/css" />
    <link href="css/slideItStylesIE.css" rel="stylesheet" type="text/css" />
<% } else if( isSafari ){ %>
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
    <link href="css/slideItStyles.css" rel="stylesheet" type="text/css" />
<% } else { %>
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
    <link href="css/slideItStyles.css" rel="stylesheet" type="text/css" />
<% } %>
