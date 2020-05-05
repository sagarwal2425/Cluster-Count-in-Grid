import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements counting of clusters in a list
 * 
 * @author Shubham Agarwal
 *
 */
public class ClusterCount {
	
	/**
	 * Main method
	 * 
	 * @param args Strings[]
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		List<List<Integer>> grid = new ArrayList<List<Integer>>();
		List<Integer> innergrid = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		String[] sa = sc.nextLine().split(" ");
		int rows = Integer.parseInt(sa[0]);	//Number of rows in grid
		int column = Integer.parseInt(sa[1]);	//Number of columns in grid
		for(int i=0; i<rows; i++) {
				String[] s = sc.nextLine().split(" ");
				for(int k=0; k<s.length; k++) {
					innergrid.add(Integer.parseInt(s[k]));
				}
				
			grid.add(new ArrayList<Integer>(innergrid));
			innergrid.clear();
		}
		
		System.out.println("Total Number of Clusters in List are " + findNumClusters(grid, rows, column));
    }
	
	/**
	 * Cluster count
	 * 
	 * @param arr  List<List<Integer>>
	 * @param rows  int
	 * @param columns int
	 */
    private static int findNumClusters(List<List<Integer>> arr, int rows, int column){
        int num_clusters = 0;	//Count of clusters

        for(int i=0;i<rows;++i){
            for(int j=0;j<column;++j){
                if(arr.get(i).get(j) == 1){
                    dfs(arr,i,j,rows,column);
                    num_clusters++;
                }
            }
        }

        for(int i=0;i<rows;++i){
            for(int j=0;j<column;++j){
                if(arr.get(i).get(j) == -1) {
                	List<Integer> element = arr.get(i);
                	element.set(j, 1);
                	arr.set(i, element);
                }
            }
        }

        return num_clusters;
    }

    /**
	 * Depth First Search recursive Implementation
	 * 
	 * @param arr  List<List<Integer>>
	 * @param x  int
	 * @param y  int
	 * @param rows  int
	 * @param cols int
	 */
    private static void dfs(List<List<Integer>> arr,int x,int y,int rows,int cols){
        if(x < 0 || x == rows || y < 0 || y == cols || arr.get(x).get(y) != 1) return;
        List<Integer> element = arr.get(x);
    	element.set(y, -1);	//Mark index as flagged by making it to -1
    	arr.set(x, element);
        dfs(arr,x-1,y,rows,cols);
        dfs(arr,x+1,y,rows,cols);
        dfs(arr,x,y-1,rows,cols);
        dfs(arr,x,y+1,rows,cols);
    }
}
