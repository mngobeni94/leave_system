package leave.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.management.RuntimeErrorException;

public class Employee {

	private String empNumber;
	private String idNumber;
	private String firstname;
	private String lastname;
	private String startDate;
	private String status;
	private int allocatedLeaveDays;
	private int minLeaveDaysAllocated;
	private Accrual [] accrual;

	public Employee() throws ClassNotFoundException, SQLException {
		generateEmpNumber();
	}

	public Employee(String empNumber, String idNumber, String firstname, String lastname, String startDate,
			String status, int minLeaveDaysAllocated) {
		this.empNumber = empNumber;
		this.idNumber = idNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		setStartDate(startDate);
		this.startDate = getStartDate();
		this.status = status;
		this.minLeaveDaysAllocated = minLeaveDaysAllocated;
	}

	public Employee(String empNumber, String idNumber, String firstname, String lastname, Date startDate, String status,
			int minLeaveDaysAllocated) {
		this.empNumber = empNumber;
		this.idNumber = idNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		setStartDate(startDate);
		this.startDate = getStartDate();
		this.status = status;
		this.minLeaveDaysAllocated = minLeaveDaysAllocated;
	}

	public Employee(String idNumber, String firstname, String lastname, Date startDate, String status,
			int minLeaveDaysAllocated) throws ClassNotFoundException, SQLException {
		generateEmpNumber();
		this.idNumber = idNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		setStartDate(startDate);
		this.startDate = getStartDate();
		this.status = status;
		this.minLeaveDaysAllocated = minLeaveDaysAllocated;
	}

	public Employee(String idNumber, String firstname, String lastname, String startDate, String status,
			int minLeaveDaysAllocated) throws ClassNotFoundException, SQLException {
		generateEmpNumber();
		this.idNumber = idNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		setStartDate(startDate);
		this.startDate = getStartDate();
		this.status = status;
		this.minLeaveDaysAllocated = minLeaveDaysAllocated;
	}

	public Employee(String idNumber, String firstname, String lastname, String status, int minLeaveDaysAllocated)
			throws ClassNotFoundException, SQLException {
		generateEmpNumber();
		this.idNumber = idNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		setStartDate(new Date());
		this.startDate = getStartDate();
		this.status = status;
		this.minLeaveDaysAllocated = minLeaveDaysAllocated;
	}

	public void generateEmpNumber() throws ClassNotFoundException, SQLException {
		DBConnection dbc = new DBConnection();
		Connection conn = null;
		try {

			conn = dbc.getConnection();
			String query = "select empNumber from employee order by empNumber desc limit 1";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			String newEmpNumber = null;
			if (res.next()) {
				String lastEmpNumber = res.getString("empNumber");
				lastEmpNumber = lastEmpNumber.substring(lastEmpNumber.length() - 5, lastEmpNumber.length());
				if (!lastEmpNumber.equals(null)) {
					int newNumber = Integer.parseInt(lastEmpNumber);
					newNumber++;
					newEmpNumber = "emp" + ("" + cal.get(Calendar.YEAR)).substring(2, 4) + "" + newNumber;
					setEmpNumber(newEmpNumber);
				} else {
					newEmpNumber = "emp" + ("" + cal.get(Calendar.YEAR)).substring(2, 4) + "" + 10000;
					setEmpNumber(newEmpNumber);
				}

			} else {
				newEmpNumber = "emp" + ("" + cal.get(Calendar.YEAR)).substring(2, 4) + "" + 10000;
				setEmpNumber(newEmpNumber);
			}

		} catch (SQLException e) {
			if (conn != (null))
				conn.rollback();
		}
		dbc.closeConnect();
	}

	public boolean addEmployee() throws ClassNotFoundException, SQLException {
		DBConnection dbc = new DBConnection();
		Connection conn = null;

		try {
			conn = dbc.getConnection();
			String query = "insert into employee(empNumber, idNumber, firstname, lastname, startdate, status, minleavedaysallocated) values('"
					+ empNumber + "'," + "'" + idNumber + "', '" + firstname + "', '" + lastname + "', '" + startDate
					+ "', '" + status + "', '" + minLeaveDaysAllocated + "')";
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(query) == 1) {
				conn.commit();
				dbc.closeConnect();
				return true;
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			if (conn != null)
				conn.rollback();
		}
		dbc.closeConnect();
		return false;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public int getAllocatedLeaveDays() {
		return allocatedLeaveDays;
	}

	public void setAllocatedLeaveDays(int allocatedLeaveDays) {
		this.allocatedLeaveDays = allocatedLeaveDays;
	}

	public int getMinLeaveDaysAllocated() {
		return minLeaveDaysAllocated;
	}

	public void setMinLeaveDaysAllocated(int minLeaveDaysAllocated) {
		this.minLeaveDaysAllocated = minLeaveDaysAllocated;
	}

	

	public static double daysAccrued(String empNumber) throws ClassNotFoundException {
		DBConnection dbc = new DBConnection();
		Connection conn = null;
		try {
			conn = dbc.getConnection();
			String query = "select startDate, minLeaveDaysAllocated from employee where empNumber='" + empNumber + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			if (results.next()) {
				Date startDate = results.getDate("startDate");
				int minLeaveDaysAllocated = results.getInt("minLeaveDaysAllocated");

				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);

				Date endDate = new Date();
				Calendar endCal = Calendar.getInstance();
				endCal.setTime(endDate);
				
				double daysAccrued = 0;
				cal.add(Calendar.MONTH, 1);
				while (cal.get(Calendar.YEAR) <= endCal.get(Calendar.YEAR) || cal.get(Calendar.YEAR) <= endCal.get(Calendar.MONTH)) {
					daysAccrued += (minLeaveDaysAllocated / 12);
					cal.add(Calendar.MONTH, 1);
				}
				dbc.closeConnect();
				return daysAccrued;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.closeConnect();
		}

		return 0;
	}

	public Accrual [] getAccrual() {
		return accrual;
	}

	public void setAccrual(Accrual [] accrual) {
		this.accrual = accrual;
	}

}
