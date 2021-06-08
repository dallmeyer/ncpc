import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.Scanner;


public class B_Connect {

	static int N, M;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		
		Connection 	A = new Connection(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt()),
					B = new Connection(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
		
		//check that a solution may be impossible
		if (checkImpossible(A,B))
		{
			System.out.println("IMPOSSIBLE");
		}
		//find solution
		else
		{
			int len = A.len() + B.len();
			int aMaxY = Math.max(A.y1, A.y2),
				aMinY = Math.min(A.y1, A.y2),
				aMaxX = Math.max(A.x1, A.x2),
				aMinX = Math.min(A.x1, A.x2),
				bMaxY = Math.max(B.y1, B.y2),
				bMinY = Math.min(B.y1, B.y2),
				bMaxX = Math.max(B.x1, B.x2),
				bMinX = Math.min(B.x1, B.x2);	
								
			if (A.blocksHoriz() && !((B.y1 < aMaxY && B.y2 < aMaxY) || (B.y1 > aMinY && B.y2 > aMinY)))
			{
				//must re-route A
				int goAbove = 2*(bMaxY - aMaxY + 1);
				int goBelow = 2*(bMinY - aMinY + 1);
				len += Math.min(goAbove, goBelow);
			}
			else if (A.blocksVert() && !((B.x1 < aMaxX && B.x2 < aMaxX) || (B.x1 > aMinX && B.x2 > aMinX)))
			{
				//must re-route A
				int goLeft = 2*(bMinX - aMinX + 1);
				int goRight = 2*(bMaxX - aMaxX + 1);
				len += Math.min(goLeft, goRight);
			}
			else if (B.blocksHoriz() && !((A.y1 < bMaxY && A.y2 < bMaxY) || (A.y1 > bMinY && A.y2 > bMinY)))
			{
				//must re-route B
				int goBbove = 2*(aMaxY - bMaxY + 1);
				int goAelow = 2*(aMinY - bMinY + 1);
				len += Math.min(goBbove, goAelow);
			}
			else if (B.blocksVert() && !((A.x1 < bMaxX && A.x2 < bMaxX) || (A.x1 > bMinX && A.x2 > bMinX)))
			{
				//must re-route B
				int goLeft = 2*(aMinX - bMinX + 1);
				int goRight = 2*(aMaxX - bMaxX + 1);
				len += Math.min(goLeft, goRight);
			}
			else if (bMinX >= aMinX && bMinX <= aMaxX && bMaxX >= aMinX && bMaxX <= aMaxX)
			{
				//must re-route B
				int goLeft = 2*(bMinX - aMinX + 1);
				int goRight = 2*(aMaxX - bMaxX + 1);
				len += Math.min(goLeft, goRight);
			}
			else if (bMinY >= aMinY && bMinY <= aMaxY && bMaxY >= aMinY && bMaxY <= aMaxY)
			{
				//must re-route B
				int goAbove = 2*(aMaxY - bMaxY + 1);
				int goBelow = 2*(bMinY - aMinY + 1);
				len += Math.min(goAbove, goBelow);
			}
			System.out.println(len);
		}
	}
	
	public static boolean checkImpossible(Connection A, Connection B)
	{
		
		
		return ((A.blocksHoriz() || A.blocksVert()) && (B.blocksHoriz() || B.blocksVert()) && A.intersects(B));
	}

	private static class Connection
	{
		int x1, y1, x2, y2;
		
		public Connection(int x1, int y1, int x2, int y2)
		{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		public boolean blocksHoriz()
		{
			return ((this.x1 == 0 && this.x2 == N) || (this.x2 == 0 && this.x1 == N));
		}
		
		public boolean blocksVert()
		{
			return ((this.y1 == 0 && this.y2 == M) || (this.y2 == 0 && this.y1 == M));
		}
		
		public boolean intersects(Connection B)
		{
			Line2D.Double 	l1 = new Line2D.Double(this.x1, this.y1, this.x2, this.y2),
							l2 = new Line2D.Double(B.x1, B.y1, B.x2, B.y2);
			
			return l1.intersectsLine(l2);
		}
		
		public int len()
		{
			return (x2-x1) + Math.abs(y2 - y1);
		}
	}
}
