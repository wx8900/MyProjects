package test;

import java.util.HashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		int[] array = {1, 2, 4, 6, 9};
		System.out.println(twoSum(array, 10));

	}
	
	public static boolean twoSum(int[] array, int target) {
        Set<Integer> visitedSet = new HashSet<Integer>();
        for (int num : array) {
            int newTarget = target - num;
            if (visitedSet.contains(newTarget)) {
                return true;
            }
            visitedSet.add(num);
        }
        return false;
    }

}
