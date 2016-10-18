package old;
//Cedric Wong Period 3

import java.util.Scanner;

public class any11
{
	public static void main (String [] args)
	{
		double currentnum = 0;
		double sum = 0;
		int blankspace = 0;
		Scanner in = new Scanner(System.in);
		double[] barcode = new double[12];
		
		System.out.println("Enter 12 digits. Use -1 for the missing digit.");
		for (int i = 0; i < barcode.length; i++)
		{
			currentnum = in.nextInt();
			if (currentnum == -1)
			{
				blankspace = i;
			}
			else
			{
				barcode[i] = currentnum;
				if(i % 2 == 0)
				{
					sum = sum + (3 * currentnum);
				}
				else
				{
					sum = sum + currentnum;
				}
			}
		}
		
		int roundedsum = (int) (Math.ceil(sum / 10) * 10);
		double missingnum = roundedsum - sum;
		
		if(blankspace % 2 == 0)
		{
			while ((missingnum % 3) != 0)
			{
				missingnum += 10;
			}
			
			missingnum = missingnum / 3;
		}
		
		System.out.println(missingnum);
	}
}