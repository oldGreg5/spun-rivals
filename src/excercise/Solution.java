package excercise;

import java.util.Scanner;

class Solution {

	static boolean win = false;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		// int arr[] = new int[t];

		for (int i = 0; i < t; i++) {
			int length = in.nextInt();
			int jump = in.nextInt();
			int arr[] = new int[length];

			for (int j = 0; j < length; j++) {
				arr[j] = in.nextInt();
			}

			checkGame(arr, jump, 0);
			System.out.println(win ? "YES" : "NO");
		}
	}

	private static void checkGame(int arr[], int jump, int pos) {
		// System.out.println(pos);

		// if (lastZeroPos + 1 == arr.length || lastZeroPos + jump >=
		// arr.length) {
		if (pos + 1 > arr.length - 1 || pos > arr.length - 1 || pos + jump > arr.length - 1) {
			// System.out.println("Win !!!!!!!!!!" + pos);
			win = true;
		}
		// mark as checked/vidited
		arr[pos] = 1;
		if (!win) {
			if (arr[pos + 1] == 0) {
				checkGame(arr, jump, pos + 1);
			}
			if (arr[pos + jump] == 0) {
				checkGame(arr, jump, pos + jump);
			}
			if (pos - 1 >= 0 && arr[pos - 1] == 0) {
				checkGame(arr, jump, pos - 1);
			}

		}
	}

	private static int checkLastZeroPosition(int[] arr) {
		return checkLastZeroPosition(arr, 0);
	}

	private static int checkLastZeroPosition(int[] arr, int start) {
		int lastZero = start;
		for (int i = start; i < arr.length; i++) {
			if (arr[i] == 0) {
				lastZero = i;
			} else {
				break;
			}
		}
		// return lastZero == 0 & arr[lastZero] == 1 ? -1 : lastZero;
		return lastZero;
	}
}
