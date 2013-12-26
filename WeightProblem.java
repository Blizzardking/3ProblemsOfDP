import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WeightProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> batch = null;
		try {
			//make a 'file' object   
			//File file = new File("weights.in");  
			File file = new File("weights.in");  
			//Get data from this file using a file reader.   
			//To store the contents read via File Reader  
			BufferedReader input = new BufferedReader(new FileReader(file));                                                   
			batch = readLines( input );
		} catch(Exception e) {
			e.printStackTrace();
		}
	//	System.out.println(batch);
		int[] result = new int[batch.size()];
		int i = 0;
		for(List<Integer> list: batch) {
			result[i++] = getNumberOfWeights(list); 
		}
		for(int c = 0; c < result.length; c++) {
			System.out.println(result[c]);
		}
	}
	public static int getNumberOfWeights (List<Integer> list) {
		int totalWeight = 0;
		for(int stone: list) {
			totalWeight += stone;
		}
		int maxWeightIndex = totalWeight/50;
		int maxNumberOfStones = list.size();
		boolean[][] M = new boolean[maxNumberOfStones + 1][maxWeightIndex + 1];
		for(int i = 0; i <= maxNumberOfStones; i++ ) {
			M[i][0] = true;
		//	M[i][maxWeightIndex] = true;
		}
		for( int i = 1; i <= maxNumberOfStones; i++) {
			int weightIndex = list.get(i - 1)/50;
			for(int j = 0; j <= maxWeightIndex; j++){
				if(M[i-1][j ]) {
					M[i][j] = true;
					M[i][j + weightIndex] = true;
					if(j< weightIndex) {
						M[i][weightIndex -j] = true;
					}
					else if(j > weightIndex) {
						M[i][j - weightIndex] = true;
					}
				}
				//M[i][j] = M[i-1][j] || M[i-1][j - weightIndex];
			}
		}
		int res = 0;
		for(int j = 1; j <= maxWeightIndex; j++) {
			if(M[maxNumberOfStones][j]) {
			//	System.out.print(j + " ");
				res++;
			}
		}
	//	System.out.println();
		return res;
		
	}
	public static List<List<Integer>> readLines( BufferedReader in ) throws IOException
    {
            String line;
            List<List<Integer>> lst = new ArrayList<>( );
            
            while( ( line = in.readLine( ) ) != null  && !line.equals("0")) {
            		line = in.readLine();
            		List<String> items= Arrays.asList(line.split("\\s+"));
            		List<Integer> li = new ArrayList<Integer>();
                    for(String item: items) {	
                    	li.add( Integer.parseInt(item));
                    }
                    lst.add(li);	
            }
            return lst;
    }
}
