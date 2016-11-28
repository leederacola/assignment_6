
/**
 * @author Ryan
 *
 * Assigimnent 6
 * 11/28
 * When running use......
 * args[0] int for no. of cities
 * args[1] file name
 */
import java.util.*;
import java.io.*;

public class ass_6
{

    public int CITI;  //number of cities entered
    public int[][] adjacency;// = new int[CITI][CITI];
    public int bestCost = Integer.MAX_VALUE; //start with the highest possivbile int

    // constructor of ass6 object   required n (no of cities)
    public ass_6(int N)
    {
        CITI = N;
        adjacency = new int[CITI][CITI];
    }

	/**
	open file passed in through args......reads file and populated adj matrix
	*/
    public void populateMatrix(String fName) // file to be passed in....
    {
        File f = new File(fName);
        try
        {
            Scanner input = new Scanner(f);
            int value, i, j;
            for (i = 0; i < CITI && input.hasNext(); i++)
            {
                for (j = i; j < CITI && input.hasNext(); j++)
                {
                    if (i == j)
                    {
                        adjacency[i][j] = 0;
                    } else
                    {
                        value = input.nextInt();
                        //these cover the half matrix that will be passed thrugh
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    }
                }
            }
        }
        // read up on try catch and errors IOExecption
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
	caculates cost of path
	*/
    public int cost(ArrayList<Integer> path)
    {
        int cost = 0;
        for (int i = 0; i < path.size()-1; i++)  //size method on arraylist??????
        {
            cost += adjacency[path.get(i)][path.get(i + 1)]; // cost of partial path	
        }
        if (path.size() == CITI) //tour completed if
        {
            cost += adjacency[path.get(path.size() - 1)][0];
        }
        return cost;
    }

    public void tspOutput(ArrayList<Integer> tour)
    {
        //System.out.println(bestCost, bestPath);
        for (int i = 0; i < tour.size(); i++)
        {
            System.out.print(tour.get(i) + " ");
        }
		System.out.println("cost = " + bestCost);
    }

    public void tspDSF(ArrayList<Integer> partialTour, ArrayList<Integer> remainingCities)
    {
        if (remainingCities.isEmpty())
        {
            int partTour = cost(partialTour);
            if (partTour < bestCost)
            {
                bestCost = partTour;
                tspOutput(partialTour);
            }
        } else
        {
            for (int i = 0; i < remainingCities.size(); i++)
            {
                ArrayList<Integer> newPartialTour = new ArrayList<>(partialTour); // could pass partialTour here inplace of Integer!!!!
                newPartialTour.add(remainingCities.get(i)); //adds i city of remaing to newpartial
                int partCost = cost(newPartialTour);
                if (partCost < bestCost)  //pruning
                {
                    ArrayList<Integer> newRemainingCities = new ArrayList<>(remainingCities);
                    newRemainingCities.remove(remainingCities.get(i)); //removes i city of remeingincities
                    tspDSF(newPartialTour, newRemainingCities);
                }

            }
        }

    }
	
	
	public void tspStack
	{
		boolean minFlag = false;
		int closestCity;
		Stack<int> stackPath = new Stack<>();
		int[] visitedCities = new int[CITI];
		 int currentCity;
		//assume start city is city 0?  ok
		//set city 0 as visitedCities array?
		visitedCities[0] = 0;
		//push city 0 to stack.
		stackPath.push(0);
		//output start city.
		System.out.println(0);
		
		while (!stackPath.empty())
		{
			//set currentCity with top value of pathStack
			currentCity = 0;
			//set min to Integer.MAX_VALUE //minimum distance
			int min = Integer.MAX_VALUE;
			//for all the remaining cities starting city 1 to N do
			for (int i = 1; i < N; i++)
			{
				//if (distance from currentCity to city i is not 0 AND city i is not visited)
				if( adjacency[currentCity][i] != 0 && visitedCities[i] = null)
				{	
					//if (distance from currentCity to city i is less than min)
					if( adjacency[currentCity][i] < min)
					{
						min = adjacency[currentCity][i];
						closestCity = i;
						minFlag = true;
					}
				}		
			}
			if(minFlag)//is true
			{
				visitedCities[closestCity] = 1;
				stackPath.push(closestCity);
				System.out.println(closestCity);
				minFlag = true;
			}
			//pop the top element from pathStack
			pathStack.pop();	
		}
		
		
		
		
	}

}

/**
 * moved main to client class
 *
 * public static void main(String[] args) { Lab5 tsp = new
 * Lab5(Integer.parseInt(args[0])) //takes first element of args srring ....get
 * n for CITIS in constucrotr tsp.populateMatrix(args[1]);	//pass file name for
 * constructroer ArrayList<Integer> partialT = new ArrayList<>(); //make your 2
 * Array Lists partialT.add(0); ArrayList<Integer> remaining = new
 * ArrayList<>(); for (int i = 1; i < <Integer> {
 *
 * }
 * .
 * parseInt(args[0]); i++
 *
 *
 *
 *
 * )
 * {
 * remainingT.add(i); } //start clock tsp.tspDSF(partialt, remainingT); //end
 * clock tspOutput(); } }
 *
 * }
 */
