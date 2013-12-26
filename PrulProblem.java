import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class PrulProblem {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> batch = null;
		try {
			//make a 'file' object   
			//File file = new File("weights.in");  
			File file = new File("p8e.in");  
			//Get data from this file using a file reader.   
			//To store the contents read via File Reader  
			BufferedReader input = new BufferedReader(new FileReader(file));                                                   
			process( input );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static int getMaxProfit(int N, int[] cost, List<Integer> number ) {
		int profit = 0;
		int maxProfit = -1;

		for(int i = 0; i < N; i++) {
			profit += 10 - cost[i];
			if(profit > maxProfit) {
				maxProfit = profit;
				number.clear();
				number.add(i + 1);
			}
			else if(profit == maxProfit) {
				number.add(i + 1);
			}
		}
		return maxProfit;
		
	}
	public static void process( BufferedReader in ) throws IOException
    {
            String line;
            int p = 0;
            line = in.readLine( );
            while(line != null ) {
            	if(line.charAt(0) == '0') 
        			break;
            	int numberOfWorkyard = 0;
            	numberOfWorkyard = Integer.parseInt(line);
            	p++;
            	int i = 0;
            	int[] maxProfit = new int[numberOfWorkyard];
            	int max = 0;
            	List<Integer>[] lists = new ArrayList[numberOfWorkyard] ;
            	while(i < numberOfWorkyard) {
            		line = in.readLine( );
            		List<String> tokens= Arrays.asList(line.split("\\s+"));
            		int N = Integer.parseInt(tokens.get(0));
            		int[] cost = new int[N];
            		for(int j = 1; j <= N; j++) {
            			cost[j-1] = Integer.parseInt(tokens.get(j));
            		}
            		lists[i] = new ArrayList<Integer>();
            		maxProfit[i] = getMaxProfit(N,cost,lists[i]);
            		max += maxProfit[i];
            		i++;
            	}
            	if(i == numberOfWorkyard) {
            		System.out.println("Problem " + p);
            		System.out.printf("Maximum profit is %d\n", max);
            		Set<Integer> pruls = new HashSet<Integer>();
            		handle(lists, pruls);
            		Set<Integer> sortedPruls = new TreeSet<Integer>(pruls);
            		System.out.print("Number of pruls to buy:");
            		int k = 0;
            		for(int num: sortedPruls) {
            			System.out.print(" " + num);
            			k++;
            			if(k == 10) break;
            		}
            		System.out.println();
            		
            	}
            	line = in.readLine( );
           }

    }
	public static void handle(List<Integer>[] lists, Set<Integer> pruls) {

		
		Set<Integer> temp = new HashSet<Integer>();
		
		for(int j = 0; j < lists[0].size(); j++) {
			temp.add(lists[0].get(j));
			pruls.add(lists[0].get(j));
		}
		for(int i = 1; i<lists.length; i++) {
			pruls.clear();
			if(lists[i].size() != 0) {				
				for(int j = 0; j < lists[i].size(); j++) {
					for(int k : temp) {
						pruls.add(k + lists[i].get(j));
					}

				}
				deepCopy(pruls, temp);
			}
			
		}
	}
	private static void deepCopy(Set<Integer> src, Set<Integer> dest) {
		dest.clear();
		for(int i: src) {
			dest.add(i);
		}
	}
}
