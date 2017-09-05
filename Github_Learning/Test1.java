import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.text.*;

public class Test1 {

	public static void main (String[] args) {
		Date date = null;
		SimpleDateFormat input = new SimpleDateFormat("yyyy MM dd");
		SimpleDateFormat output = new SimpleDateFormat("EEE", Locale.ENGLISH);
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		try {
			date = input.parse(s);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		int i = 0;
		System.out.println(output.format(date.getTime()).toString());
		switch (output.format(date.getTime()).toString()) {
		case "Sun": i = 0; break; 
		case "Mon": i = 1; break; 
		case "Tue": i = 2; break; 
		case "Wed": i = 3; break; 
		case "Thu": i = 4; break; 
		case "Fri": i = 5; break; 
		case "Sat": i = 6; break; 
		}
		System.out.println(i);
		scanner.close();
	}
	
}
