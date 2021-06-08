import java.util.Scanner;


public class F_FuckingJoke {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		for (int i = 0; i< N; i++)
		{
			String s = in.next();
			if (s.charAt(0) == 'P')
			{
				System.out.println("skipped");
			}
			else
			{
				int plusIndex = s.indexOf('+');
				int a = Integer.parseInt(s.substring(0,plusIndex));
				int b = Integer.parseInt(s.substring(plusIndex+1));
				System.out.println((a+b));
			}
		}
	}

}
