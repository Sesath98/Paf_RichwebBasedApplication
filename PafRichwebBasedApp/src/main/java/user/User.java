package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	
	private Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "");
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public String readUsers()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for reading.";
	
	}
	// Prepare the HTML table to be displayed
	output = "<table border='1'><tr> <th>User name</th>"+
			"<th>Phone number</th>"+
			"<th>Address</th>"+
		   "<th>Designation</th>"+
	"<th>Update</th><th>Remove</th></tr>";
	
	
	String query = "select * from users";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	
	while (rs.next())
	{
	String userID = Integer.toString(rs.getInt("userId"));
	String username = rs.getString("username");
	String phone_no = rs.getString("phone_no");
	String address = rs.getString("address");
	String designation = rs.getString("designation");
	
	// Add into the HTML table
	
	output += "<tr><td><input id='hidItemIDUpdate'"+
	"name='hidItemIDUpdate'"+
	"type='hidden' value='" + userID
	+ "'>" + username + "</td>";
	output += "<td>" + phone_no + "</td>";
	output += "<td>" + address + "</td>";
	output += "<td>" + designation + "</td>";
	// buttons
	
	output += "<td><input name='btnUpdate' type='button' value='Update' "
			+ "class='btnUpdate btn btn-secondary'data-itemid='" + userID +"'> </td>"
			+ "<td><input name='btnRemove' type='button' value='Remove' "
			+ "class='btnRemove btn btn-danger' data-itemid='" + userID + "'></td></tr>";
	}
	con.close();
	
	// Complete the HTML table
	
	output += "</table>";
	
	}
	catch (Exception e)
	{
	output = "Error while reading the users.";
	System.err.println(e.getMessage());
	}
	return output;
	}

	public String insertUsers(String username, String phone_no, String address, String designation)
			
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{
				
			return "Error while connecting to the database for inserting .";
			
			}
			// create a prepared statement
			String query = "insert into users (`userId`,`username`,`phone_no`,`address`,`designation`)"
							+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, phone_no);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, designation);
			// execute the statement
			
			
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +
					newUsers + "\"}";
			
			}
			catch (Exception e)
			{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Users.\"}";
			
			System.err.println(e.getMessage());
			}
			return output;
			}
	
	
	

	public String updateUsers(String ID, String username, String phone_no, String address, String designation)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{
			return "Error while connecting to the database for updating ";
			
			}
			// create a prepared statement
			String query = "UPDATE users SET username=?,phone_no=?,address=?,designation=? WHERE userId=?";
					
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, phone_no);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, designation);
			preparedStmt.setInt(5, Integer.parseInt(ID));
		
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +
					newUsers + "\"}";
			}
			catch (Exception e)
			{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			
			System.err.println(e.getMessage());
			}
			return output;
			}
	
	
	

	public String deleteUsers(String userId)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{
			return "Error while connecting to the database for deleting.";		
			}
			// create a prepared statement
			String query = "delete from users where userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +
					newUsers + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":Error while deleting the user.\"}";
			System.err.println(e.getMessage());
			}
			return output;
			}

	

}
