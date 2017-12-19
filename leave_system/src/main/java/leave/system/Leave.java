package leave.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Leave implements Comparable<Leave> {
	private String empNumber;
	private String applicationDate;
	private String startDate;
	private String endDate;
	private String status;
	private int daysused;


	public Leave() {
		empNumber = "";
		setApplicationDate(new Date());
		startDate = "";
		endDate = "";
		status = "Approved";
	}

	public Leave(String empNumber, String applicationDate, String startDate, String endDate, String status) {
		this.empNumber = empNumber;
		setApplicationDate(applicationDate);
		this.applicationDate = getApplicationDate();
		setStartDate(startDate);
		this.startDate = getStartDate();
		setEndDate(endDate);
		this.endDate = getEndDate();
		this.status = status;
	}

	public Leave(String empNumber, Date applicationDate, Date startDate, Date endDate, String status) {
		this.empNumber = empNumber;
		setApplicationDate(applicationDate);
		this.applicationDate = getApplicationDate();
		setStartDate(startDate);
		this.startDate = getStartDate();
		setEndDate(endDate);
		this.endDate = getEndDate();
		this.status = status;
	}

	public boolean addApplication() throws SQLException, ClassNotFoundException {

		DBConnection dbc = new DBConnection();
		Connection conn = null;
		try {
			conn = dbc.getConnection();

			String query = "insert into leave(empNumber, startdate, applicationdate, enddate, status, daysused) values('"
					+ empNumber + "'," + "'" + startDate + "', '" + applicationDate + "', '" + endDate + "', '" + status
					+ "','"+daysused+"')";
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(query) == 1) {
				conn.commit();
				dbc.closeConnect();
				return true;
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
		dbc.closeConnect();

		return false;
	}

	public boolean checkLeaveDaysAvailability() throws ClassNotFoundException {
		try {
			Date tempStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date tempEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			System.out.println("Before get Leave Days");
			int numLeaveDays = getNumberOfLeaveDays(tempStartDate, tempEndDate);
			System.out.println("After get Leave Days");
			double daysAccrued = Employee.daysAccrued(empNumber);
			System.out.println("After get Leave Days Accrued");
			int leaveDaysUsed = getLeaveDaysUsed(empNumber);
			System.out.println("After get Leave Days Used");
			if (daysAccrued >= numLeaveDays + leaveDaysUsed && numLeaveDays > 0) {
				setDaysused(numLeaveDays);
				return true;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	public int getNumberOfLeaveDays(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		
		
		
		int leaveDaysCount = 0;
		
		while (cal.get(Calendar.YEAR) <= endCal.get(Calendar.YEAR) && cal.get(Calendar.MONTH) <= endCal.get(Calendar.MONTH) 
				&& cal.get(Calendar.DAY_OF_MONTH) <= endCal.get(Calendar.DAY_OF_MONTH)) {

			if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					&& cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && !isHoliday(cal.get(Calendar.DAY_OF_YEAR)) ) {
				leaveDaysCount++;
			}
			cal.add(Calendar.DATE, 1);
			//System.out.println(cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH));
		}
		return leaveDaysCount;
	}
	
	public boolean isHoliday(int dayOfYear) {
		SAPublicHoliday [] holidays = SAPublicHoliday.values();
		for(int i = 0; i < holidays.length; i++) {
			if(dayOfYear == holidays[i].getValue()) {
				return true;
			}
		}
		return false;
	}

	public boolean employeeExist() throws ClassNotFoundException, SQLException {
		DBConnection dbc = new DBConnection();
		Connection conn = null;

		conn = dbc.getConnection();

		String query = "select empNumber from employee where empNumber='" + empNumber + "'";

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		dbc.closeConnect();
		return false;
	}

	public int getLeaveDaysUsed(String empNumber) throws ClassNotFoundException {
		DBConnection dbc = new DBConnection();
		Connection conn = null;
		try {

			conn = dbc.getConnection();
			String query = "select sum(daysused) as total from leave where empNumber='" + empNumber
					+ "' GROUP BY empNumber";
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				int total = rs.getInt("total");
				System.out.println(total);
				dbc.closeConnect();
				return total;
			} else {
				dbc.closeConnect();
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.closeConnect();
		return 0;
	}

	
	
	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(applicationDate);
		this.applicationDate = date;
	}

	public void setApplicationDate(String applicationDate) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(applicationDate);
			this.applicationDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		this.startDate = date;
	}

	public void setStartDate(String startDate) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Date getStartDateFromString() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
		this.endDate = date;
	}

	public void setEndDate(String endDate) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public int getDaysused() {
		return daysused;
	}

	public void setDaysused(int daysused) {
		this.daysused = daysused;
	}

	@Override
	public int compareTo(Leave o) {
		Calendar date = Calendar.getInstance();
		Calendar date1 = Calendar.getInstance();
		try {
			date.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
			date1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(o.getStartDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(date.before(date1)) {
			return -1;
		}
		else if(date.after(date1)) {
			return 1;
		}
		return 0;
	}

}
