package selenium_api;

import java.util.Random;

public class CommonClass {
	
	  public static int randomNumberForEmail() {
			 Random random = new Random();
			 int random_num = random.nextInt(999);
			 System.out.println("Random number is: " + random_num);
			 return random_num;
		  }
	
}
