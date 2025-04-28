import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 - Hash Tables
 * <p>
 * Program reads in a country file and saves the country (name and population density) into a hash table based on a hash function.
 * The user can search, delete from, insert into and print the hash tables along with its empty and collided cells.
 * 
 * @author Shirley Chen
 * @version 04/27/2025
 */
public class Project5
{
	/**
	 * Main method 
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		//Local variable declarations
		Scanner scnr = new Scanner(System.in);
		HashTable countryHash = new HashTable();
		File countryFile = null;
		Scanner fscnr = null;
		String name;
		long population;
		long area;
		int numCountries = 0;
		int userChoice = 0;
		
		//Display project title
		System.out.println("COP3530 Project 5");
		System.out.println("Hash Tables\n");
		
		do
		{
			//Get file name
			System.out.println("Enter the file name: ");
			String fileName = scnr.next();
			
			//Find file and create file scanner
			try
			{
				countryFile= new File(fileName);
				fscnr = new Scanner(new File(fileName));
			}
			catch(FileNotFoundException e)
			{
				System.out.println("File not found. Try again.\n");
				continue;
			}//end try-catch
			
		}while (!countryFile.exists()); //end do-while
		
		fscnr.nextLine(); //Skip header line
		
		while(fscnr.hasNext())
		{
			//Reads in each country's data
			String[] line = fscnr.nextLine().split(",");
			name = line[0];
			population = Long.parseLong(line[2]);
			area = Long.parseLong(line[4]);
			
			//Insert new country hash table
			countryHash.insert(name, population, area);
			numCountries++;	
			
		}//end while
		
		//Display number of country data read
		System.out.println("\nThere were " + numCountries + " country records read into the hash table.\n");
		
		do
		{
			//Display menu options
			System.out.println("1. Print hash table\n" + 
								"2. Delete a country of a given name\n" +
								"3. Insert a country of its name, population, and area\n" +
								"4. Search and print a country and its population density for a given name\n" +
								"5. Print numbers of empty cells and collided cells\n" +
								"6. Exit\n" +
								"Enter your choice: ");
			
			//Get user menu choice and validate the input
			if(scnr.hasNextInt())
			{ 
				userChoice = scnr.nextInt();
				scnr.nextLine();
					
				if(userChoice >= 1 && userChoice <= 6)
				{
					//Call corresponding methods or end program based on user menu choice
					switch(userChoice)
					{
						case 1: 
							countryHash.display();
							break;
						
						case 2:
							System.out.println("Enter country name: ");
							name = scnr.nextLine();
							countryHash.delete(name);
							break;
						
						case 3:
							System.out.println("Enter country name: ");
							name = scnr.nextLine();
							
							System.out.println("Enter country population: ");
							population = Long.parseLong(scnr.nextLine());
							
							System.out.println("Enter country area: ");
							area = Long.parseLong(scnr.nextLine());
							
							countryHash.insert(name, population, area);
							System.out.println("\n" + name + " is inserted to hash table");
							break;
						
						case 4:
							System.out.println("Enter country name: ");
							name = scnr.nextLine();
							countryHash.find(name);
							break;
							
						case 5: 
							countryHash.printEmptyAndCollidedCells();
							break;
						
						default:
							System.out.println("\nHave a good day!\n");	
							
					}//end switch
				}
				else
				{
					System.out.println("Invalid choice. Enter 1-6.");
				}//end inner if-else
			}
			else
			{
				scnr.next();
				System.out.println("Invalid choice. Enter 1-6.");
			}//end outer if-else
			
			System.out.println();
			
		}while(userChoice != 6); //end do-while
		
		//Close scanners
		fscnr.close();
		scnr.close();
		
	}//end main
	
}//end Project5 class
