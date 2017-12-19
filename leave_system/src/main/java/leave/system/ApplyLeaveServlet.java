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

public class ApplyLeaveServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String responseMsg = null;
		String errorMsg = null;
		try {
			Leave application = new Leave();
			application.setEmpNumber(req.getParameter("empNumber"));
			application.setStartDate(req.getParameter("startDate"));
			application.setEndDate(req.getParameter("endDate"));
			if (application.employeeExist()) {
				
				if (application.checkLeaveDaysAvailability()) {
					if (application.addApplication()) {
						responseMsg = "Application was successful";
					} else {
						errorMsg = "Something went wrong please try again";
					}
				} else {
					errorMsg = "<b>Failed:</b> You do not have enough days to apply for the specified number of days";
				}

			} else {
				errorMsg = "<b>Failed:</b> Employee Number \"" + application.getEmpNumber() + "\" does not exist";
			}
		} catch (ClassNotFoundException e) {
			errorMsg = "There was an internal error, please check input values or try again later";
		}
		catch(SQLException e) {
			errorMsg = "Unable to establish connection, please try again";
		}
		req.setAttribute("results", responseMsg);
		if(errorMsg != null) 
			req.setAttribute("errorMsg", errorMsg);
		else if(responseMsg != null)
			req.setAttribute("successMsg", responseMsg);
		
		RequestDispatcher rd = req.getRequestDispatcher("leaveApplication");
		rd.forward(req, resp);

	}
}
