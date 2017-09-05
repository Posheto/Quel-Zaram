import java.util.Date;
import java.util.Scanner;
import java.text.*;

public class Test1 {

	public static void main (String[] args) {
		Date date = null;
		SimpleDateFormat input = new SimpleDateFormat("yyyy MM dd");
		SimpleDateFormat output = new SimpleDateFormat("E");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		try {
			date = input.parse(s);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		int i = 0;
		switch (output.format(date.getTime()).toString()) {
		case "������": i = 0; break; 
		case "����һ": i = 1; break; 
		case "���ڶ�": i = 2; break; 
		case "������": i = 3; break; 
		case "������": i = 4; break; 
		case "������": i = 5; break; 
		case "������": i = 6; break; 
		}
		System.out.println(i);
		scanner.close();
	}
	
}
