	<!-- <script type="text/javascript" src="js/browser.js"></script> -->
    <div id="loading">
      <div class="loading-indicator"> <img src="img/loading.gif" alt="" 
                         style="margin-right:8px;float:left;vertical-align:top;"/>Minimorum<br/>
        <span id="loading-msg">Cargando...</span></div>
    </div>
	<script type="text/javascript" src="js/formcheck/lang/<%=(String)request.getSession().getAttribute("locale")%>.js"></script>
	<script type="text/javascript" src="js/mootools-1.2.1-core-yc.js"></script>
    <script type="text/javascript" src="js/mootools-1.2-more.js"></script>
	<script type="text/javascript" src="js/SqueezeBox.js"></script>
	<script type="text/javascript" src="js/formcheck.js"></script> 
    
    <link rel="stylesheet" type="text/css" media="all" href="css/sexyalertbox.css"/>
    <script type="text/javascript" src="js/sexyalertbox.v1.2.moo.mini.js"></script> 
	<script type="text/javascript" src="js/slideitmoo-1.1.js"></script>
    <script type="text/javascript" src="js/mootools-1.2.2.1-more.slide.js"></script>
	
	<script type="text/javascript">

            function timer ( elementId, name ){
                //diffInSeconds = now.diff(closeTime,"second");
                imageName = 'img'+elementId;
                nameTimer = 'timer'+name;
                if (parseInt(window[name]) <= 0){
                    document.getElementById(elementId).innerHTML = "CERRADA";
                    clearInterval(window[nameTimer]);
                    return;
                }

                if ( document.getElementById(imageName) != null ){
                    anHour = 3600; //in seconds
                    percentageTime = parseFloat(window[name]) / anHour;
                    percentageTime *= 100;
                    percentageTime = 100 - percentageTime;
                    if (percentageTime < 100)
                        percentageTime = parseInt(percentageTime / 8.3);
                    else
                        percentageTime = 12;
                    document.getElementById(imageName).src = "img/swt0"+percentageTime+".gif";
                }
                text = "";
                hours = parseInt(window[name] / 3600);
                if (hours != 0 ){
                    if (hours > 0 && hours < 10)
                        text = text + "0";
                    text += hours + ":"
                }

                minutes = parseInt(window[name] / 60 ) - (hours * 60);
                if (minutes >= 0 && minutes < 10)
                    text = text + "0";
                text += minutes + ":"

                seconds = parseInt(window[name] % 60);
                if (seconds >= 0 && seconds < 10)
                    text = text + "0";
                text += seconds

                document.getElementById(elementId).innerHTML = text;
                window[name] = parseInt(window[name])-1;
            }

			window.addEvent('domready', function(){
				new FormCheck('myForm');
			 
				/**
				 * Assign SqueezeBox to all links with rel="boxed" attribute, the class then reads the "href".
				 */
				SqueezeBox.assign($$('a.boxed'), {
						parse: 'rel'
				});
				/*
				new SlideItMoo({overallContainer: 'SlideItMoo_info_outer',
					elementScrolled: 'SlideItMoo_info_inner',
					thumbsContainer: 'SlideItMoo_info_items',		
					itemsVisible:3,
					itemsSelector: '.info_item',
					itemWidth:270,
					showControls:0,
					autoSlide: 2500,
					transition: Fx.Transitions.Sine.easeIn,
					duration: 2500,
					direction:1});	
				*/
				Sexy = new SexyAlertBox();
                document.getElementById("loading").style.visibility = 'hidden';
		<%
				if (error != null){
		%>
					Sexy.error("<h1>Error</h1><p><%=error%></p>");
		<%
				}

				if (message != null){
		%>
					Sexy.alert("<h1>Mensaje</h1><p><%=message%></p>");
		<%
				}
		%>
				});
	</script>