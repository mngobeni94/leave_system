package leave.system;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		SAPublicHoliday[] days = SAPublicHoliday.values();
		for(int i =0; i < days.length; i++ ) {
			System.out.println(days[i].getValue());
		}
		
		System.out.println();
		try {
			System.out.println(new Employee().daysAccrued("emp1710000"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * if (new Employee("gahsasj1", "mahlori", "Ngobeni", "2017-06-17", "approved",
		 * 20).addEmployee()) { System.out.println("Success!"); } else {
		 * System.out.println("Failed"); }
		 * 
		 */
			
			//System.out.println(new Leave().getLeaveDaysUsed("emp171001"));
			//Leave[] leave = new ViewHistoryServlet().viewLeavesTaken("emp171001");
			/* for(int i = 0; i < leave.length; i++)
			{
				System.out.println(leave[i].getStartDate() + " " + leave[i].getEndDate());
			}
			*/
		/*
		} else {
			System.out.println("Failed");
		}
		/*
		 * Date date; try { date = new
		 * SimpleDateFormat("yyyy-MM-dd").parse("2017-10-01"); Date date1 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse("2018-06-01"); new
		 */
		// ViewHistoryServlet.getDaysAccrued("emp171002"); //} catch (ParseException e)
		// {
		// e.printStackTrace(); }
		// */

	}
}
