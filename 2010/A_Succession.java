import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;


public class A_Succession {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt(),
			M = in.nextInt();
		
		HashMap<String,Node> nameLookup = new HashMap<String,Node>();
		String founderName = in.next();
		Node founder = new Node(founderName);
		founder.blood1 = 0.5;
		founder.blood2 = 0.5;
		nameLookup.put(founderName, founder);
		
		for (int n = 0; n < N; n++)
		{
			String 	childName = in.next(),
					parent1Name = in.next(),
					parent2Name = in.next();
			
			Node child, parent1, parent2;
			if (nameLookup.containsKey(childName))
			{
				child = nameLookup.get(childName);
			}
			else
			{
				child = new Node(childName);
				nameLookup.put(childName, child);
			}
			if (nameLookup.containsKey(parent1Name))
			{
				parent1 = nameLookup.get(parent1Name);
			}
			else
			{
				parent1 = new Node(parent1Name);
				nameLookup.put(parent1Name, parent1);
			}
			if (nameLookup.containsKey(parent2Name))
			{
				parent2 = nameLookup.get(parent2Name);
			}
			else
			{
				parent2 = new Node(parent2Name);
				nameLookup.put(parent2Name, parent2);
			}
			
			child.parent1 = parent1;
			child.parent2 = parent2;
			parent1.children.add(child);
			parent2.children.add(child);
		}
		
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(founder);
		while (!q.isEmpty())
		{
			Node current = q.poll();
			for (Node childNode : current.children)
			{
				if (current == childNode.parent1)
				{
					childNode.blood1 = (current.blood1+current.blood2)/2.0;
				}
				else
				{
					childNode.blood2 = (current.blood1+current.blood2)/2.0;
				}
				q.add(childNode);
			}
		}
		
		String heir = "";
		double maxBlood = 0;
		for (int m = 0; m < M; m++)
		{
			String lookup = in.next();
			if (nameLookup.containsKey(lookup))
			{
				Node potentialHeir = nameLookup.get(lookup);
				double blood = potentialHeir.blood1 + potentialHeir.blood2;
				if (blood > maxBlood)
				{
					maxBlood = blood;
					heir = lookup;
				}
			}
		}
		
		System.out.println(heir);
	}

	private static class Node
	{
		Node parent1, parent2;
		String name;
		double blood1, blood2;
		ArrayList<Node> children;
		
		public Node(String name)
		{
			this.name = name;
			this.blood1 = 0;
			this.blood2 = 0;
			this.children = new ArrayList<Node>();
		}
	}
	
}
