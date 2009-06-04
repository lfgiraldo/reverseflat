package com.reverse.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.services.NVPCallerServices;
import com.reverse.ejb.session.UserFacadeLocal;
import com.reverse.common.valueobjects.ChipsincomeVO;
import com.reverse.common.valueobjects.CreditCardVO;
import com.reverse.common.valueobjects.UserVO;
import com.reverse.ejb.commons.utils.PayPalUtil;
import com.reverse.server.commons.Commons;
import javax.ejb.EJB;

/**
 * Servlet encargado de hacer los pagos en Paypal por medio de ExpressCheckout.
 * @author Edward Gaviria
 * @author Luis Felipe Giraldo
 */
 public class ExpressCheckoutServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	private NVPCallerServices caller;
    @EJB
    private UserFacadeLocal userFacade;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			caller = PayPalUtil.getCaller();
		} catch (PayPalException e) {
			e.printStackTrace();
			request.getSession().setAttribute("exception", e);
			response.sendRedirect("error.jsp?message="+e.getMessage());
			return;
		}

		String action = request.getParameter("action");
		if(action.equals("doLogin")) {
			doLogin(request, response);
		} else if(action.equals("getDetails")) {
			getDetails(request, response);
		} else if(action.equals("doPayment")) {
			doPayment(request, response);
		}
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

		String moduleName = request.getParameter("moduleName");
		String currency = request.getParameter("currency");
		String amount = request.getParameter("amount");

		try {
			StringBuffer url = new StringBuffer();
			url.append("http://");
			url.append(request.getServerName());
			url.append(":");
			url.append(request.getServerPort());
			url.append("/SnoofWar");

			//url.append(moduleName);
			moduleName = moduleName.substring(moduleName.lastIndexOf('.') + 1) + ".jsp";

			String returnUrl = url.toString()+ "/ExpressCheckoutServlet?action=getDetails&locale=" + request.getSession().getAttribute("locale") + "&paymentAmount=" + amount + "&currencyCodeType=" + currency;
			String cancelUrl = url.toString() + "/" + moduleName + "?locale=" + request.getSession().getAttribute("locale");

            CreditCardVO data = new CreditCardVO();
            data.setAmount(amount);
            data.setCurrency(currency);

            ChipsincomeVO chipsDto = new ChipsincomeVO();
            int chips = 0;
            if (data.getAmount().equals("0.5")) {
                chips = 10;
            } else if (data.getAmount().equals("1.0")) {
                chips = 20;
            } else if (data.getAmount().equals("1.5")) {
                chips = 30;
            } else if (data.getAmount().equals("2.0")) {
                chips = 40;
            }
            chipsDto.setChipsAmmount(chips);
            chipsDto.setMoneyPaid(Float.parseFloat(data.getAmount()));
            //chipsDto.setTransactionNumber(transactionId);
            //@TODO: Utilizar el método de carga correcto.
            chipsDto.setChargeMethod(Short.parseShort("1"));


            UserVO userDto = (UserVO) request.getSession().getAttribute("user");
            userDto.setIdUser(userDto.getIdUser());

            chipsDto.setUserIduser(userDto.getIdUser());
            //userDto.setChipsBalance(chips);
            String paypalUrl = userFacade.doPaypalLogin(Commons.ChipsincomeVOtoChipsincome(chipsDto),
                    Commons.CreditCardVOtoCreditCardServer(data), returnUrl, cancelUrl);

            response.sendRedirect(paypalUrl);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("exception", e);
			response.sendRedirect("error.jsp?message="+e.getMessage());
			return;
		}
	}

	private void getDetails(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

		String token = request.getParameter("token");
		String paymentAmount = request.getParameter("paymentAmount");
		String currencyCodeType = request.getParameter("currencyCodeType");
		try {
			//NVPEncoder object is created and all the name value pairs are loaded into it.
            String locale = (String)request.getSession().getAttribute("locale");
			String url = userFacade.getPaypalDetails(token, paymentAmount, currencyCodeType, locale);
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("exception", e);
			response.sendRedirect("error.jsp?message="+e.getMessage());
			return;
		}
	}

	private void doPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currency = request.getParameter("currencyCodeType");
		String amount = request.getParameter("paymentAmount");
        String token = request.getParameter("token");
        String payerId = request.getParameter("PayerID");
        String url = "";

	    try {
            ChipsincomeVO chipsDto = new ChipsincomeVO();
            int chips = 0;
            if ("5".equals(amount)) {
                chips = 10;
            } else if ("10".equals(amount)) {
                chips = 20;
            } else if ("15".equals(amount)) {
                chips = 30;
            } else if ("20".equals(amount)) {
                chips = 40;
            }
            chipsDto.setChipsAmmount(chips);
            chipsDto.setMoneyPaid(Float.parseFloat(amount));
            //chipsDto.setTransactionNumber(transactionId);
            //@TODO: Utilizar el método de carga correcto.
            chipsDto.setChargeMethod(Short.parseShort("1"));
            UserVO userDto = (UserVO) request.getSession().getAttribute("user");
            userDto.setIdUser(userDto.getIdUser());

            chipsDto.setUserIduser(userDto.getIdUser());

            CreditCardVO data = new CreditCardVO();
            data.setAmount(amount);
            data.setCurrency(currency);

            String locale = (String)request.getSession().getAttribute("locale");
            url = userFacade.doPaypalPayment(Commons.UserVOtoUser(userDto),
                    Commons.ChipsincomeVOtoChipsincome(chipsDto),
                    Commons.CreditCardVOtoCreditCardServer(data),
                    token, payerId, locale);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("exception", e);
			response.sendRedirect("error.jsp?message="+e.getMessage());
			return;
		}

		response.sendRedirect(url);
	}

	
}