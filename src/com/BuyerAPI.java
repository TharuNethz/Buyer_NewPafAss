package com;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Buyer;

/**
 * Servlet implementation class BuyerAPI
 */
@WebServlet("/BuyerAPI")
public class BuyerAPI extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	
	Buyer BuyerObj = new Buyer();
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = BuyerObj.insertBuyer(
				request.getParameter("BuyerID"),
				request.getParameter("Name"),
				request.getParameter("Telno"),
				request.getParameter("Address"),
				request.getParameter("CompanyName"),
				request.getParameter("Email")
				
				);
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = BuyerObj.updateBuyer(
		paras.get("hiddenBuyerIDSave").toString(),
		paras.get("Name").toString(),
		paras.get("Telno").toString(),
		paras.get("address").toString(),
		paras.get("companyname").toString(),
		paras.get("Email").toString());
		response.getWriter().write(output);
		}
		
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = BuyerObj.deleteBuyer(paras.get("BuyerID").toString());
		response.getWriter().write(output); 
	}
	

	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

	

}