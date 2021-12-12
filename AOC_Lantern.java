import java.util.HashMap;
import java.util.Scanner;

public class AOC_Lantern {
    public static void main(String[] args) {
        int[] fishes = {4,3,3,5,4,1,2,1,3,1,1,1,1,1,2,4,1,3,3,1,1,1,1,2,3,1,1,1,4,1,1,2,1,2,2,1,1,1,1,1,5,1,1,2,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,5,1,4,2,1,1,2,1,3,1,1,2,2,1,1,1,1,1,1,1,1,1,1,4,1,3,2,2,3,1,1,1,4,1,1,1,1,5,1,1,1,5,1,1,3,1,1,2,4,1,1,3,2,4,1,1,1,1,1,5,5,1,1,1,1,1,1,4,1,1,1,3,2,1,1,5,1,1,1,1,1,1,1,5,4,1,5,1,3,4,1,1,1,1,2,1,2,1,1,1,2,2,1,2,3,5,1,1,1,1,3,5,1,1,1,2,1,1,4,1,1,5,1,4,1,2,1,3,1,5,1,4,3,1,3,2,1,1,1,2,2,1,1,1,1,4,5,1,1,1,1,1,3,1,3,4,1,1,4,1,1,3,1,3,1,1,4,5,4,3,2,5,1,1,1,1,1,1,2,1,5,2,5,3,1,1,1,1,1,3,1,1,1,1,5,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,1,3,3,1,1,5,1,3,5,5,1,1,1,2,1,2,1,5,1,1,1,1,2,1,1,1,2,1};
        Scanner sc = new Scanner(System.in);
        int days = sc.nextInt();
        long total = 0;
        long[] arr = new long[7];
        for(int i=0; i<7; i++) {
            arr[i] = simulate(days, i);
        }
        for(int fish: fishes) {
            total += arr[fish];
        }
        System.out.println(total);
    }

    public static long simulate(int days, int initial) {
        long fishes = 0;
        int[] arr = new int[7];
        HashMap<Integer, Long> hm = new HashMap<Integer, Long>();
        HashMap<Integer, Long> newfish = new HashMap<Integer, Long>();
        for(int i=0; i<7; i++) {
            if(i==initial) {
                hm.put(i, Long.valueOf(1));
            } else {
                hm.put(i, Long.valueOf(0));
            }
        }
        for(int i=0; i<7; i++) {
            newfish.put(i, Long.valueOf(0));
        }
        for(int day=0; day<days; day++) {
            //add 1 week old fishes to breedable gang
            hm.put((day+2)%7, hm.get((day+2)%7)+ newfish.get((day+2)%7));
            //new fishes cannot breed for 1 week, store separately
            newfish.put((day+2)%7, hm.get(day%7));

        }
        for(long fish: hm.values()) {
            fishes += fish;
        }
        for(long fish: newfish.values()) {
            fishes += fish;
        }
        return fishes;
    }
}

