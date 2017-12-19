package leave.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.collections.MappingChange.Map;

public class AddEmployeeServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Employee emp = null;
		String responseMsg = null;
		String errorMsg = null;
		try {
			emp = new Employee();
			emp.setFirstname(req.getParameter("firstname"));
			emp.setLastname(req.getParameter("lastname"));
			emp.setIdNumber(req.getParameter("idNumber"));
			emp.setMinLeaveDaysAllocated(Integer.parseInt(req.getParameter("minLeaveDaysAllocated")));
			emp.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startDate")));
			emp.setStatus("working");
			
			if (emp.addEmployee()) {
				responseMsg = "User added successfully. The employee number is \"" + emp.getEmpNumber() + "\"";
				req.setAttribute("successMsg", responseMsg);
			} else {
				

				errorMsg = "Error adding user, check your input";
				
				req.setAttribute("errorMsg", errorMsg);
			}
		}
		catch (ParseException e) {
				errorMsg = "You entered an invalid startDate";
				req.setAttribute("errorMsg", errorMsg);
		
		}  catch (Exception ex) {
			errorMsg = "An internal error occcured, please try again later";
			req.setAttribute("errorMsg", errorMsg);
		} 
		if(errorMsg != null) {
			req.setAttribute("employee", emp);
		}

		RequestDispatcher rd = req.getRequestDispatcher("add");
		rd.forward(req, resp);

	}

}
