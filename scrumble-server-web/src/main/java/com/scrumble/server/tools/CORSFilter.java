/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.tools;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author ArNo
 */
public class CORSFilter implements Filter {

	public CORSFilter() { }

	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(
		ServletRequest request, ServletResponse response, 
		FilterChain chain) throws IOException, ServletException {

                //doc sur les Access-Control.... (https://developer.mozilla.org/en-US/docs/HTTP_access_control#Access-Control-Allow-Credentials)
		((HttpServletResponse)response).addHeader(
			"Access-Control-Allow-Origin", "*"
		);
                ((HttpServletResponse)response).addHeader(
			"Access-Control-Allow-Headers", "Content-Type, Authorization, Accept"
		);
                ((HttpServletResponse)response).addHeader(
			"Access-Control-Allow-Methods", "GET, POST, DELETE, PUT"
		);
                /*((HttpServletResponse)response).addHeader(
			"Access-Control-Allow-Credentials", "true"
		);*/

		chain.doFilter(request, response);
	}
}