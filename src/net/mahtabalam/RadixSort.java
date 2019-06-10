package net.mahtabalam;

import java.util.*;

class RadixSort {

	public static void main(String[] args) {
		int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
		int n = arr.length;
		radixsort(arr, n);
	}

	// The main function that sorts arr[] of size n using Radix Sort
	public static void radixsort(int arr[], int n) {
		// Find the maximum number to know number of digits
		int m = getMax(arr, n);

		// Do counting sort for every digit. Note that instead
		// of passing digit number, exp is passed. 
		System.out.println("Input Array :"+Arrays.toString(arr));
		for (int exp = 1; m / exp > 0; exp *= 10) {
			// Do count sort for each digit, moving from Least Significant Digit(LSD) to Most Significant Digit(MSD)  
			countSort(arr, n, exp);
		}
			
	}

	// A utility function to get maximum value in arr[]
	static int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	public static void countSort(int arr[], int n, int exp) {
		int output[] = new int[n]; // output array
		int i;
		// count array size is 10, as we are dealing with base 10 numbers, so a number will only contain digits 0,1,2,3,4,5,6,7,8,9 
		int count[] = new int[10]; 
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10] = count[(arr[i] / exp) % 10] + 1;
		
		
		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++)
			count[i] = count[i] + count[i - 1];
		

		// Build the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}
		
		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to current digit
		for (i = 0; i < n; i++)
			arr[i] = output[i];
		
		System.out.println("Array :"+Arrays.toString(arr));
	}

}
