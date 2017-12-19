package leave.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pr = resp.getWriter();
		//String empNumber = req.getParameter("empNumber");
		String responseMsg = null;
		try {
			req.setAttribute("leaves", getAllEmployees());
		} catch (Exception e) {
			responseMsg= "An internal error occured, please try agin later";
		}
		req.setAttribute("results", responseMsg);
		req.getRequestDispatcher("employeeList").forward(req, resp);
	}
	
	public Employee[] getAllEmployees() throws ClassNotFoundException, SQLException
	{
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.getConnection();
		
		String query = "select empNumber, firstname, lastname, startDate, minLeaveDaysAllocated from employee";
		ArrayList<Employee> employeeArrayList = new ArrayList<Employee>();
		
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) { 
				Employee emp = new Employee();
				emp.setEmpNumber(rs.getString("empNumber"));
				emp.setFirstname(rs.getString("firstname"));
				emp.setLastname(rs.getString("lastname"));
				emp.setStartDate(rs.getDate("startDate"));
				emp.setMinLeaveDaysAllocated(rs.getInt("minLeaveDaysAllocated"));
				employeeArrayList.add(emp);
			}
			dbc.closeConnect();
			return employeeArrayList.toArray(new Employee[employeeArrayList.size()]);
	}
	
	

}
