package leave.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

public class ViewHistoryServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pr = resp.getWriter();
		String responseMsg = null;
		try {
			String empNumber = req.getParameter("empNumber");
			req.setAttribute("firstname", req.getParameter("firstname"));
			req.setAttribute("lastname", req.getParameter("lastname"));
			req.setAttribute("empNumber", empNumber);
			//req.setAttribute("startDate",req.getParameter("startDate"));
			req.setAttribute("leaves", viewLeavesTaken(empNumber));
			req.getRequestDispatcher("history").forward(req, resp);
		} catch (ClassNotFoundException e) {
			responseMsg = "An internal error occcured, please try again later";
		} catch (SQLException e) {
			responseMsg = "An internal error occcured, please try again later";
		}
		req.setAttribute("results", responseMsg);
		req.getRequestDispatcher("history").forward(req, resp);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String responseMsg = null;
		try {
			String empNumber = req.getParameter("empNumber");
			req.setAttribute("firstname", req.getParameter("firstname"));
			req.setAttribute("lastname", req.getParameter("lastname"));
			req.setAttribute("empNumber", empNumber);
			req.setAttribute("startDate",req.getParameter("startDate"));
			req.setAttribute("leaves", viewLeavesTaken(empNumber));
			Date startDate =  new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startDate"));
			req.setAttribute("accruals", getDaysAccrued(startDate, Integer.parseInt(req.getParameter("minLeaveDays"))));
			
		} catch (Exception e) {
			responseMsg = "An internal error occcured, please try again later";
		}
		req.setAttribute("results", responseMsg);
		req.getRequestDispatcher("history").forward(req, resp);
	}

	public static String displayMonthFromDate(int month) {
		switch (month) {
		case Calendar.JANUARY:
			return "January";
		case Calendar.FEBRUARY:
			return "February";
		case Calendar.MARCH:
			return "March";
		case Calendar.APRIL:
			return "April";
		case Calendar.MAY:
			return "May";
		case Calendar.JUNE:
			return "June";
		case Calendar.JULY:
			return "July";
		case Calendar.AUGUST:
			return "August";
		case Calendar.SEPTEMBER:
			return "September";
		case Calendar.OCTOBER:
			return "October";
		case Calendar.NOVEMBER:
			return "November";
		case Calendar.DECEMBER:
			return "December";
		}
		return null;
	}

	public static Accrual[] getDaysAccrued(Date startDate, int minDaysPerYear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(new Date());
		
		ArrayList<Accrual> accruals = new ArrayList<Accrual>();
		
		double daysAccrued = 0;
		while (cal.get(Calendar.YEAR) <= endCal.get(Calendar.YEAR) && cal.get(Calendar.MONTH) <= endCal.get(Calendar.MONTH)) {
			Accrual accr = new Accrual();
			accr.setDay(01);
			accr.setMonth(displayMonthFromDate(cal.get(Calendar.MONTH)));
			accr.setIntMonth(cal.get(Calendar.MONTH));
			accr.setYear(cal.get(Calendar.YEAR));
			accr.setDaysAccrued(daysAccrued);
			daysAccrued = daysAccrued + (minDaysPerYear / 12.0);
			
			accruals.add(accr);
			cal.add(Calendar.MONTH, 1);
		}
		
		return accruals.toArray(new Accrual[accruals.size()]);
		
	}

	public Leave[] viewLeavesTaken(String empNumber) throws ClassNotFoundException, SQLException {
		DBConnection dbc = new DBConnection();
		Connection conn = null;

		conn = dbc.getConnection();

		String query = "select startDate, endDate, applicationDate from leave where empNumber='" + empNumber
				+ "'";
		ArrayList<Leave> leaveArrayList = new ArrayList<Leave>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			Leave leave = new Leave();
			leave.setStartDate(rs.getDate("startDate"));
			leave.setEndDate(rs.getDate("endDate"));
			leave.setApplicationDate(rs.getDate("applicationDate"));
			leaveArrayList.add(leave);
		}
		dbc.closeConnect();
		return leaveArrayList.toArray(new Leave[leaveArrayList.size()]);
	}
	
	
	

}
