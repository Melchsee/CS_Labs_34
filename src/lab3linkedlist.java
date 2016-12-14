import java.io.*;
import java.util.*;

//creates a student class to hold the information of the students
class listelement{
	String lastName;
	String firstName;
	listelement next;
	
	//creates a constructor for the students
	listelement(String lastName, String firstName) 
	{
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	listelement(String lastName, String firstName, listelement next) 
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.next = next;
	}

	//converts this object to a string
	public String toString( ) {
      return " " + lastName + " " + firstName + " ";
      
      
  }
}

class list{
	listelement first;
	
	void insert(listelement element)
	{
		if (first == null)
		{
			first = element;
			return;
		}
		listelement current = first;
		if (current.lastName.compareTo(element.lastName) > 0)
		{
			element.next = current;
			first = element;
			return;
		}
		while(current.next != null)
		{
			if (current.lastName.compareTo(element.lastName) < 0 && current.next.lastName.compareTo(element.lastName) > 0
					|| current.lastName.equals(element.lastName))
			{
				element.next = current.next;
				current.next = element;
				return;
			}
			current = current.next;
		}
		current.next = element;
	}
	
	void printlist()
	{
		listelement current = first;
		while (current.next != null)
		{
			System.out.println(current.lastName + " " + current.firstName);
			current = current.next;
		}
	}
}

//first main class, sorts by last name
public class lab3linkedlist{
	public static void main (String args[]) 
	{
		list classlist = new list();
		
		
		File file = new File("Y:/workspace/CS_Labs_3_4/src/classlist.txt");
		try{
			Scanner scanner = new Scanner(file);
			String line = "";
			while (scanner.hasNextLine())
			{
				line = scanner.nextLine();
				//creates an array of strings so that each word gets its own string
				String[] splitLine = line.split(" ");	
				
				//associates each word with a variable
				String lastName = splitLine[2];
				String firstName = splitLine[3];
				
				//System.out.println(lastName + " " + firstName);
				
				listelement student = new listelement(lastName, firstName);
				classlist.insert(student);
			}
			classlist.printlist();
		}catch (IOException EXC)
		{
			EXC.printStackTrace();
		}
		
	}
}