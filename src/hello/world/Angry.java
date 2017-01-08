package hello.world;

// Arup Guha
// 4/1/2016
// Solution to USACO Silver January Problem: Angry Cows

import java.util.*;
import java.io.*;

public class Angry {

	public static void main(String[] args) throws Exception {

		// Read in data.
		BufferedReader stdin = new BufferedReader(new FileReader("angry.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int k = Integer.parseInt(tok.nextToken());
		int[] arr = new int[n];
		for (int i=0; i<n; i++)
			arr[i] = Integer.parseInt(stdin.readLine().trim());

		// Sort it.
		Arrays.sort(arr);

		// Do binary search.
		int low = arr[0], high = arr[n-1];
		while (low < high-1) {
			int mid = (low+high)/2;
			if (!valid(arr, k, mid))
				low = mid+1;
			else
				high = mid;
		}

		if (!valid(arr, k, low)) low++;

		// Write result.
		PrintWriter out = new PrintWriter(new FileWriter("angry.out"));
		System.out.println(low);
		out.close();
		stdin.close();
	}

	public static boolean valid(int[] arr, int numCows, int range) {

		int curBale = 0;

		// Simulate using each cow.
		for (int i=0; i<numCows; i++) {

			// Note - I am just going to linearly walk this instead of doing another binary search,
			// since this array is small.
			int start = arr[curBale];
			while (curBale < arr.length && arr[curBale]-start <= 2*range) curBale++;

			// We made it!
			if (curBale == arr.length) return true;
		}

		// If we get here, we can't do it.
		return false;
	}

}