import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class D_DirtyDriving {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt(),
			p = in.nextInt();
		
		int[] dists = new int[n];
		for (int i = 0; i < n; i++)
		{
			dists[i] = in.nextInt();
		}
		Arrays.sort(dists);
		
		int maxProperDist = p;
		for (int i = 1; i < n; i++)
		{
			int properDistToI = p*(i+1);
			int newProperDist = properDistToI - (dists[i] - dists[0]);
			maxProperDist = Math.max(maxProperDist, newProperDist);
		}
		
		System.out.println(maxProperDist);
	}

}
