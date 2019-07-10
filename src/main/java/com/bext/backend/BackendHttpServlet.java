package com.bext.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BackendHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 6014283639681920298L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		ObjectMapper mapper =new ObjectMapper();
		String mensaje = req.getParameter("mensaje");
		
		ResponseDTO response = new ResponseDTO();
		response.setMensaje(mensaje + "[, esto se agrega en BackendHttpServlet]");
		response.setTime(System.currentTimeMillis());
		response.setIp(getIp());
		
		PrintWriter out = resp.getWriter();
		mapper.writerWithDefaultPrettyPrinter().writeValue( out, response);
	}

	private String getIp() {
		String hostname = null;
		try {
		   hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		   hostname = "desconocido";
		} 
		
		return hostname;
	}
}
