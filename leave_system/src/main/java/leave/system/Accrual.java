package leave.system;

public class Accrual {
	private int year;
	private String month;
	private int day;
	private double daysAccrued;
	private int intMonth;
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public double getDaysAccrued() {
		return daysAccrued;
	}
	public void setDaysAccrued(double daysAccrued) {
		this.daysAccrued = daysAccrued;
	}
	public int getIntMonth() {
		return intMonth;
	}
	public void setIntMonth(int intMonth) {
		this.intMonth = intMonth;
	}
	
}