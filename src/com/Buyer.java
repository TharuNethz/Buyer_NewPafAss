package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Buyer {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "123tharu#T");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	
	public String insertBuyer(String BuyerID,String Name, String Telno, String Address , String CompanyName, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into buyer  ('id', 'name', 'telno', 'address', 'companyname','email')"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,Integer.parseInt(BuyerID));
			preparedStmt.setString(2, Name);
			preparedStmt.setInt(3,Integer.parseInt(Telno));
			preparedStmt.setString(4,Address);
			preparedStmt.setString(5, CompanyName);
			preparedStmt.setString(6, Email);
			//execute the statement
			preparedStmt.execute();
			con.close();
			String newBuyer = readBuyer();
			output = "{'status:'success', 'data': '" + newBuyer + "'}";
		} catch (Exception e) {
			output =  "{'status':'error', 'data': 'Error while inserting the Buyer.'}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readBuyer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table table-dark\"><tr><th>Buyer ID</th><th>Buyer Name</th><th>Telno</th><th>Address</th><th>Company Name</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from buyer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String BuyerID = Integer.toString(rs.getInt("id"));
				String Name = rs.getString("name");
				String Telno = Integer.toString(rs.getInt("telno"));
				String Address = rs.getString("address");
				String CompanyName = rs.getString("companyname");
				String Email = rs.getString("email");
// Add into the html table
				output += "<tr><td><input id='hidBuyerIDupdate' name='hidBuyerIDupdate' type='hidden' value='"+ BuyerID 
						+"'>" +BuyerID  + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Telno + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + CompanyName + "</td>";
				output += "<td>" + Email + "</td>";

// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-BuyerID='"
						+  BuyerID + "'>" + "</td></tr>"; 
				
				
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Buyer details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updateBuyer(String BuyerID, String Name, String Telno, String Address,
			String CompanyName, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE buyer SET name=?, telno=?, address=?, companyname=?, email=?  WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, Name);
			preparedStmt.setInt(2, Integer.parseInt(Telno));
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, CompanyName);
			preparedStmt.setString(5, email);
			preparedStmt.setInt(6, Integer.parseInt(BuyerID));

// execute the statement
			preparedStmt.execute();
			con.close();
			String newBuyer = readBuyer();
			output = "{'status':'success', 'data': '" +newBuyer + "'}"; 
		} catch (Exception e) {
			output = "{'status':'error', 'data': 'Error while updating the Buyer details.'}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteBuyer(String BuyerID) {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }
// create a prepared statement
            String query = "delete from buyer where id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
            preparedStmt.setInt(1, Integer.parseInt(BuyerID));
// execute the statement
            preparedStmt.execute();
            con.close();
            String newBuyer = readBuyer();
             output = "{'status':'success', 'data: '" +newBuyer + "'}"; 
        } catch (Exception e) {
            output =  "{'status':'error', 'data': 'Error while deleting the buyer.'}";
            System.err.println(e.getMessage());
        }
        return output;
    }


	
}
	


