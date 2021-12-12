import java.util.*;
import java.util.stream.Collectors;

public class AOC_height {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        while(sc.hasNextLine()) {
            String row = sc.nextLine();
            List<Integer> rowArr = row.chars().map(c -> c-'0').boxed().collect(Collectors.toList());
            arr.add(rowArr);
        }
        int row_len = arr.size();
        int col_len = arr.get(1).size();
        int[][] visited = new int[row_len][col_len];
        for(int i=0; i<row_len; i++) {
            for(int j=0; j<col_len; j++) {
                if(arr.get(i).get(j)==9) {
                    visited[i][j] = 1;
                }
            }
        }
        HashMap<Integer, Integer> basinsize = new HashMap<Integer, Integer>();
        int basin_index = 0;
        Queue<IntegerPair> q = new LinkedList<IntegerPair>();
        for(int i=0; i<row_len; i++) {
            for(int j=0; j<col_len; j++) {
                if(visited[i][j]!=1) {
                    q.offer(new IntegerPair(i,j));
                    visited[i][j] = 1;
                    int size = 0;
                    while(!q.isEmpty()) {
                        IntegerPair ip = q.poll();
                        size += 1;
                        int row = ip.first;
                        int col = ip.second;
                        if(row!=row_len-1 && visited[row+1][col] != 1) {
                            q.offer(new IntegerPair(row+1, col));
                            visited[row+1][col] = 1;
                        }
                        if(col!=col_len-1 && visited[row][col+1] != 1) {
                            q.offer(new IntegerPair(row, col+1));
                            visited[row][col+1] = 1;
                        }
                        if(col!=0 && visited[row][col-1] != 1) {
                            q.offer(new IntegerPair(row, col-1));
                            visited[row][col-1] = 1;
                        }
                        if(row!=0 && visited[row-1][col] != 1) {
                            q.offer(new IntegerPair(row-1, col));
                            visited[row-1][col] = 1;
                        }
                    }
                    basinsize.put(basin_index, size);
                    basin_index += 1;
                }
            }
        }
        List<Integer> bs = basinsize.values().stream().collect(Collectors.toList());
        Collections.sort(bs);
        for(int i:bs) {
            System.out.println(i);
        }
        int res = 1;
        for(int i=0; i<3; i++) {
            res *= bs.get(bs.size()-1-i);
        }
        System.out.println(res);
        sc.close();
    }

    public static void dfs(int row, int col, List<List<Integer>> arr, int basin_index, int[][] visited, HashMap<Integer, Integer> basinsize) {
        visited[row][col] = 1;
        if(!basinsize.containsKey(basin_index)) {
            basinsize.put(basin_index, 1);
        } else {
            basinsize.put(basin_index, basinsize.get(basin_index) + 1);
        }
        int row_len = arr.size();
        int col_len = arr.get(1).size();
        if(row!=row_len-1) {
            dfs(row+1, col, arr, basin_index, visited, basinsize);
        }
        if(col!=col_len-1) {
            dfs(row, col+1, arr, basin_index, visited, basinsize);
        }
        if(col!=0) {
            dfs(row, col-1, arr, basin_index, visited, basinsize);
        }
    }
    public static int helper(int row, int col, List<List<Integer>> arr) {
        int row_len = arr.size();
        int col_len = arr.get(1).size();
        int top = Integer.MAX_VALUE, bot = Integer.MAX_VALUE, left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        int val = arr.get(row).get(col);
        if(row!=0) {
            top = arr.get(row-1).get(col);
        }
        if(row!=row_len-1) {
            bot = arr.get(row+1).get(col);
        }
        if(col!=0) {
            left = arr.get(row).get(col-1);
        }
        if(col!=col_len-1) {
            right = arr.get(row).get(col+1);
        }
        if(val<top && val<left && val<bot && val<right) {
            return val+1;
        }
        return 0;
    }
}

class IntegerPair {
    public int first;
    public int second;

    public IntegerPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
