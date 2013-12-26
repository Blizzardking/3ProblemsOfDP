import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Discord2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> batch = null;
		try {
			//make a 'file' object   
			//File file = new File("weights.in");  
			File file = new File("discord.in");  
			//Get data from this file using a file reader.   
			//To store the contents read via File Reader  
			BufferedReader input = new BufferedReader(new FileReader(file));                                                   
			batch = readInput( input );
		} catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println(batch);
		int[] result = new int[batch.size()];
		int i = 0;
		List<List<Integer>> partition = new ArrayList<>();
		for(List<String> list: batch) {
			partition.add(new ArrayList<Integer>());
			int N = Integer.parseInt(list.get(0));
			int K = Integer.parseInt(list.get(1));
			result[i] = minimizeDiscord(N, K, list.get(2), partition.get(i)); 
			i++;
		}
		for(int c = 0; c < result.length; c++) {
			System.out.print(result[c] + " ");
			int index1 = 0;
			for(int index2: partition.get(c)) {
				System.out.print((index2 - index1) + " ");
				index1  = index2;
			}
			System.out.println();
			
		}
		
	}
	static int MIN;
	static int MAX;
	public static int minimizeDiscord( int N, int K, String str, List<Integer> pt) {
		//int min = 3;
		//System.out.println(str);
		MIN = (int)Math.ceil((double)(2*N)/(3*K));
		//System.out.println((2*N)/(3*K));
		//int max = 5;
		MAX = (int)Math.floor((double)(4*N)/(3*K));
		//System.out.println(MIN + " " + MAX);
		int[][] M = new int[N+1][K+1];
		int[][] S = new int[N+1][K+1];
		for(int i = 1; i <= N; i++) {
			M[i][0] = Integer.MAX_VALUE/2;
		}
		for(int j = 1; j <= K; j++) {
			M[0][j] = Integer.MAX_VALUE/2;
		}
		for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= N; i++) {
                M[i][k] = Integer.MAX_VALUE/2;
                for (int j = 0; j < i; j++) {
                    int q = M[j][k-1] + getDiscord(str, j+1, i);
                    if (q < M[i][k]) {
                        M[i][k] = q;
                        S[i][k] = j;
                    }
                }
            }
        }
		
		getPartition(S,N,K,pt);
		return M[N][K];
	}
	public static void getPartition(int[][] S, int n, int k, List<Integer> pt) {
	
		int j = S[n][k];
		if(j==0) return;
		getPartition(S, j, k-1, pt);
		pt.add(j);
	}
	public static int getDiscord(String str, int start, int end ) {
		//System.out.println(MIN + " " + MAX);
		if((end -start + 1) > MAX || (end -start + 1) < MIN) {
			return Integer.MAX_VALUE/2;
		}
		int[] count = new int[2];
		for(int i = start; i <= end; i++){
			if(str.charAt(i - 1) == 'R') 
				count[0]++;
			else if(str.charAt(i - 1) == 'D')
				count[1]++;
			else {
				System.err.println("Unexpected character!");
				System.exit(-1);
			}
			
		}
		return (int)Math.pow(count[0] - count[1], 2);
	}
	public static List<List<String>> readInput( BufferedReader in ) throws IOException
    {
            String line;
            List<List<String>> lst = new ArrayList<>( );
            
            while( ( line = in.readLine( ) ) != null  && line.charAt(0) != '0') {
            		List<String> items= Arrays.asList(line.split("\\s+"));
            		List<String> li = new ArrayList<String>();
            		line = in.readLine();
            		for(String item: items) {
            			li.add(item);
            		}
            		li.add(line);
            		lst.add(li);
            }
            return lst;
    }
}
