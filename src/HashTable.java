/**
 * Create, search from, insert into, delete from, and print the hash table and the number of empty and collided cells.
 * 
 * @author Shirley Chen
 * @version 04/27/2025
 */
public class HashTable
{
	//Class variable declaration
	private CountryList[] countryHash;
	
	/**
	 * Constructor to create the country hash table using an array
	 */
	public HashTable()
	{
		countryHash = new CountryList[257];
	}//end HashTable constructor
	
	/**
	 * Create and insert a country node into the hash table based on the index value it was hashed to using the hash function
	 * 
	 * @param country The name of the country to be inserted
	 * @param population The population of the country to be inserted
	 * @param area The area of the country to be inserted
	 */
	public void insert(String country, long population, long area)
	{
		//Local variable declarations
		Node newCountry = new Node(country, population, area);
		int i = hashFunction(country);
		
		//Create new linked list if index is empty
		if(countryHash[i] == null)
		{
			countryHash[i] = new CountryList();
		}
		
		//Insert country at index 
		countryHash[i].insertLast(newCountry);
		
	}//end insert
	
	/**
	 * Find a country node within the hash table and print its population density
	 * 
	 * @param country The name of country to find
	 * @return result The index where country was found or -1 if not found
	 */
	public int find(String country)
	{
		//Local variable declarations
		int i = hashFunction(country);
		int result = -1;
		
		//Try to find country at index based on hash function
		if(countryHash[i] != null)
		{
			Node found = countryHash[i].find(country);
		
			if(found != null)
			{
				result = i;
				System.out.printf("\n%s is found at index %d with population density of %.4f\n", country, i, found.popDensity);
			}
			else
			{
				System.out.println(country + " not found in hash table");
			}
		}

		return result;
		
	}//end find
	
	/**
	 * Calculates the index value by summing up the Unicode values of country's name and modulus the result with 257
	 * @param countryName The name of the country
	 * @return result The computed index value
	 */
	private int hashFunction(String countryName)
	{
		int result = 0;
		
		for(int i = 0; i < countryName.length(); i++)
		{
			result += countryName.charAt(i);
		}//end for 
		
		return result % 257;
		
	}//end computeIndex
	
	/**
	 * Delete a country node from hash table
	 * 
	 * @param country The name of country to delete
	 */
	public void delete(String country)
	{
		//Local variable declarations
		int i = hashFunction(country);
		boolean deleted = false;
		
		//Try to delete country from index if index is not empty
		if(countryHash[i] != null)
		{
			Node deletedCountry = countryHash[i].delete(country);
			
			if(deletedCountry != null)
			{
				deleted = true;
			}//end inner if
			
		}//end outer if
		
		//Display whether country was successfully deleted
		if(deleted)
		{
			System.out.println("\n" + country + " is deleted from hash table");
		}
		else
		{
			System.out.println("\n" + country + " is not a country in the hash table");
		}//end if-else
		
	}//end delete
	
	/**
	 * Display all countries within hash table
	 */
	public void display()
	{
		for(int i = 0; i < countryHash.length; i++)
		{
			if(countryHash[i] == null)
			{
				System.out.printf("%3d. Empty\n", i);
			}
			else
			{
				System.out.printf("%3d. ", i);
				countryHash[i].printList();
				System.out.println();
			}//end if-else
			
		}//end for
		
	}//end display
	
	/**
	 * Print number of empty and collided cells in hash table
	 */
	public void printEmptyAndCollidedCells()
	{
		//Local variable declarations
		int empty = 0;
		int collided = 0;
		
		//Loop through hash table array to count number of empty and collided cells
		for(int i = 0; i < countryHash.length; i++)
		{
			if(countryHash[i] == null)
			{
				empty++;
			}
			else if(countryHash[i].length > 1)
			{
				collided++;
			}
		}//end for
		
		//Display results
		System.out.println("\nThere are " + empty + " empty cells and " + collided + " collisions in the hash table");
		
	}//end printEmptyAndCollidedCells
	
	/**
	 * Creates, calculates, stores, and prints a country's name and population density
	 * 
	 * @author Shirley Chen
	 * @version 04/27/2025
	 */
	private class Node 
	{
		//Class variable declarations
		String name;
		double popDensity;
		Node nextNode;
		
		/**
		 * Constructor to initialize the country node
		 * 
		 * @param name The name of the country
		 * @param population The population of the country
		 * @param area The area of the country
		 */
		public Node(String name, long population, long area) 
		{
			this.name = name;
			this.popDensity = (double) population / area;
		}//end Node constructor
		
		/**
		 * Print the country name and population density
		 */
		public void printNode() 
		{
			System.out.printf("%-35s%.4f", name, popDensity);	
		}//end printNode
		
	}//end Node class
	
	/**
	 * Creates, finds, inserts into, deletes from, and prints the country linked list
	 * 
	 * @author Shirley Chen
	 * @version 04/27/2025
	 */
	private class CountryList
	{
		//Class variable declarations
		Node first;
		Node last;
		int length;
		
		/**
		 * Constructor to initialize the lsit variables
		 */
		public CountryList()
		{
			this.first = null;
			this.last = null;
			length = 0;
			
		}//end CountryList constructor
		
		/**
		 * Inserts node into end of linked list
		 * 
		 * @param newNode The country node to be inserted
		 */
		public void insertLast(Node newNode)
		{
			//Insert into list based on if the list was previously empty
			if(first == null)
			{
				first = last = newNode;
			}
			else
			{
				last.nextNode = newNode;
				last = newNode;
			}//end if-else
			
			//Increment list length
			length++;
			
		}//end insertLast
		
		/**
		 * Find and return the country node within linked list or null if not found
		 * 
		 * @param name The name of the country to find
		 * @return current The node of country found or null if not found
		 */
		public Node find(String name)
		{
			//Local variable declaration
			Node current = first;
			
			//Traverse the full list or until the country node was found
			while(current != null)
			{
				//Return current country node if country was found
				if(current.name.equals(name))
				{
					return current;
				}//end if
				
				current = current.nextNode;		
			}//end while
			
			return null;
			
		}//end find method
		
		/**
		 * Delete country node from linked list assuming list is not empty
		 * 
		 * @param name The name of country to delete
		 * @return current The deleted country node or null if not found
		 */
		public Node delete(String name)
		{
			//Local variable declarations
			Node previous = first;
			Node current = first;

			//Traverse full list or until country to be deleted is found
			while(!name.equals(current.name))
			{
				//Return null if end of list is reach or continue to traverse list
				if(current.nextNode == null)
				{
					return null;
				}
				else
				{
					previous = current;
					current = current.nextNode;
				}//end if-else
				
			}//end while
			
			//Remove found country from list based on its location in list
			if(current == first)
			{
				first = current.nextNode;
			}
			else if(current == last)
			{
				last = previous;
				previous.nextNode = null;
			}
			else
			{
				previous.nextNode = current.nextNode;
			}//end if-else if-else

			//Decrement list length
			length--;
		
			return current;
			
		}//end delete
		
		/**
		 * Print country linked list
		 */
		public void printList()
		{
			//Local variable declaration
			Node current = first;
			
			while(current != null)
			{
				current.printNode();
				
				if(current.nextNode == null)
				{
					return;
				}
				else
				{
					current = current.nextNode;
					System.out.print("\n     ");
				}//end if-else
			}//end while 
		}//end printList
		
	}//end CountryList class
	
}//end HashTable