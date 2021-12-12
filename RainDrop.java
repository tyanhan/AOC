import java.util.Stack;

public class RainDrop {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Stack<Integer> level = new Stack<Integer>();
        int max = height[0];
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {     //if new block is taller than the current tallest block, start popping
                while (!level.empty()) {
                    water += max - level.pop();
                }
                max = height[i];
            }
            if (i == height.length - 1) {    //if last block, reverse the popping
                max = height[i];
                while (!level.empty()) {
                    int h = level.pop();
                    if(h>max){
                        max = h;
                    } else {
                        water += max - h;
                    }
                }
            }
            level.push(height[i]);
        }
        System.out.println(water);
    }
}

