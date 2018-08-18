/**
 * 
 */
package com.amit.ooviu.controller;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amit.ooviu.crypto.VodacomEncryptionUtil;

/**
 * @author amitb
 *
 */
@Controller
public class OmanController {
	
	private static final Logger logger = LoggerFactory.getLogger(OmanController.class);
	

	
	@RequestMapping(value = {"/uinfo"}, method = { RequestMethod.POST, RequestMethod.GET })
	public String charge(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Request came to /uinfo");
		
		//date
		model.addAttribute("date", new Date());
		//request url
		model.addAttribute("requestUrl", request.getRequestURL());
		//ip
		logger.info("Request ip is: " + request.getRemoteAddr());
		model.addAttribute("ip", request.getRemoteAddr());
		
		//query params
		logger.info("Query params are: " + request.getQueryString());
		model.addAttribute("queryParams", request.getQueryString());
		
		//headers
		logger.info("Headers are: ");
		String headers = logRequestHeaders(request);
		headers = headers.replaceAll("\n", "</br>");
		model.addAttribute("headers", headers);
		
		try {
			model.addAttribute("decryptedmsisdn",VodacomEncryptionUtil.decrypt(request.getHeader("X-VC-ACR")) );
		   } catch (Exception e) {
			 logger.error("Exception in Key read from pem file :  "+ e);
			
		} 

		return "home";
	}
	
	/**
	 * prints request headers
	 * @param request
	 */
	public static String logRequestHeaders(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			sb.append(headerName + " : " + request.getHeader(headerName) + "\n");
		}
		logger.error("herders: " +sb.toString());
		return sb.toString();
	}
	

}
