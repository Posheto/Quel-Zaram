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
		case "星期日": i = 0; break; 
		case "星期一": i = 1; break; 
		case "星期二": i = 2; break; 
		case "星期三": i = 3; break; 
		case "星期四": i = 4; break; 
		case "星期五": i = 5; break; 
		case "星期六": i = 6; break; 
		}
		System.out.println(i);
		scanner.close();
	}
	
}
