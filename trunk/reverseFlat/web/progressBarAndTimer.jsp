<%
            if (currentAuction.getActive() && currentAuction.getEndDate() == null){
%>
                <p><dd><span style="width:<%=(int)percentage%>px"></em></span></dd></p>
<%
            }else if (currentAuction.getActive() && currentAuction.getEndDate() != null){
                Date today = new Date();
                long remainingTime = currentAuction.getEndDate().getTime() - today.getTime();
                //Convierte de milisegundos a segundos
                remainingTime /= 1000;
%>
                <p id="<%=currentAuction.getIdAuction()%>" style="font-size:11px; font-weight:bold; color:black; letter-spacing: 1px;">
                  <script>
                                var name = 'auc<%=currentAuction.getIdAuction()%>';
                                window[name] = <%=remainingTime%>;
                                window.addEvent('domready',function(){window['timerauc<%=currentAuction.getIdAuction()%>']=setInterval("timer('<%=currentAuction.getIdAuction()%>', 'auc<%=currentAuction.getIdAuction()%>')",1000)});
                        </script>
                </p>
<%
            }
%>