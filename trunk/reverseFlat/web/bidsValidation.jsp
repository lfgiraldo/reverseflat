    <div id="loading">
      <div class="loading-indicator"> <img src="img/loading.gif" alt="" 
                         style="margin-right:8px;float:left;vertical-align:top;"/>Snoits<br/>
        <span id="loading-msg">Cargando...</span></div>
    </div>
<script>

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
    var bidRangeForm = null;
	var singleBidForm = null;
	window.addEvent('domready', function(){
        Element.implement({
            //implement show
            fancyShow: function() {
                this.setStyle('display','block');
                this.fade('in');
            },
            //implement hide
            fancyHide: function() {
                this.fade('out');
            }
        });


		SqueezeBox.assign($$('a.boxed'), {
				parse: 'rel'
		});	
		bidRangeForm = new FormCheck('multipleForm');
		singleBidForm = new FormCheck('singleForm');				
		new SlideItMoo({overallContainer: 'SlideItMoo_info_outer',
			elementScrolled: 'SlideItMoo_info_inner',
			thumbsContainer: 'SlideItMoo_info_items',		
			itemsVisible:3,
			itemsSelector: '.info_item',
			itemWidth:270,
			showControls:0,
			autoSlide: 5000,
			transition: Fx.Transitions.Sine.easeIn,
			duration: 1800,
			direction:1});	

		Sexy = new SexyAlertBox();
        var myVerticalSlide = new Fx.Slide('vertical_slide');
        var status = {
            'true': '[+] M&aacute;s Informaci&oacute;n',
            'false': '[-] Menos Informaci&oacute;n'
        };

        var Tips1 = new Tips($$('.Tips1'));
            //store titles and text
        $$('.tipz').each(function(element,index) {
            var content = element.get('title').split('::');
            element.store('tip:title', content[0]);
            element.store('tip:text', content[1]);
        });

        //create the tooltips
        var tipz = new Tips('.tipz',{
            className: 'tipz',
            fixed: false,
            hideDelay: 50,
            showDelay: 50
        });


        myVerticalSlide.toggle();
        $('v_toggle').addEvent('click', function(e){
                e.stop();
                myVerticalSlide.toggle();
                $('vertical_status').set('html', status[myVerticalSlide.open]);
            });

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

	//Esta funci&oacute;n valida que el n&uacute;mero inicial no sea mayor que el n&uacute;mero final, que los n&uacute;meros sean positivos y que el n&uacute;mero solo tenga una posici&oacute;n decimal. 
	var avlock = false;
	function validateRange(el) {
		if( avlock )  return true;
		avlock = true;
	
		var elParent = el.getParent('div');
	
		var minEl = elParent.getElement("[name^=bidFrom]");
		var maxEl = elParent.getElement("[name^=bidTo]");
	
		if( bidRangeForm.validate( minEl ) && bidRangeForm.validate( maxEl ) &&
							Number(minEl.value) > Number(maxEl.value) ) {
			var errorMsg = (el == minEl ?
				"El n&uacute;mero inicial debe ser menor que el n&uacute;mero final" :
				"El n&uacute;mero inicial debe ser menor que el n&uacute;mero final" );
				el.errors.push( errorMsg );
		
				avlock = false;
				return false;
			}
		
		if( bidRangeForm.validate( minEl ) && bidRangeForm.validate( maxEl ) &&
							(Number(minEl.value) <= 0 || Number(maxEl.value) <= 0) ) {
			var errorMsg = "Los n&uacute;meros deben ser mayores que cero";
				el.errors.push( errorMsg );
		
				avlock = false;
				return false;
			}
		var test = minEl.value;
		var index = test.substring(test.lastIndexOf(".") + 1);

		if (index.length > 1 && index.length != test.length){
				var errorMsg = "Solo se admite una posici&oacute;n decimal.";
				el.errors.push( errorMsg );
		
				avlock = false;
				return false;
		}
		
		test = maxEl.value;
		index = test.substring(test.lastIndexOf(".") + 1);

		if (index.length > 1 && index.length != test.length){
				var errorMsg = "Solo se admite una posici&oacute;n decimal.";
				el.errors.push( errorMsg );
		
				avlock = false;
				return false;
		}
	
		avlock = false;
		return true;
	}
	
	var bvlock = false;			
	function validateSingle(el) {
		if( bvlock )  return true;
		bvlock = true;
	
		var elParent = el.getParent('div');
	
		var singleBidEl = elParent.getElement("[name^=singleBid]");
	
		
		if( singleBidForm.validate( singleBidEl ) &&
							(Number(singleBidEl.value) <= 0 ) ) {
			var errorMsg = "Los n&uacute;meros deben ser mayores que cero";
				el.errors.push( errorMsg );
		
				bvlock = false;
				return false;
			}
		var test = singleBidEl.value;
		var index = test.substring(test.lastIndexOf(".") + 1);

		if (index.length > 1 && index.length != test.length){
				var errorMsg = "Solo se admite una posici&oacute;n decimal.";
				el.errors.push( errorMsg );
		
				bvlock = false;
				return false;
		}
		
	
		bvlock = false;
		return true;
	}
</script>