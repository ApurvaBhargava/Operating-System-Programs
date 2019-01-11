import java.util.Scanner;

public class WorstFit {
	public static void main(String[] args) {
		int flag, max, netFrag = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of processes and free blocks.");
		int n = input.nextInt();
		int f = input.nextInt();
		int[] p = new int[n];
		int[] ms = new int[f];
		System.out.println("Enter the process sizes.");
		for(int i = 0; i<n; i++) {
			p[i] = input.nextInt();
		}
		System.out.println("Enter the free block sizes.");
		for(int i = 0; i<f; i++) {
			ms[i] = input.nextInt();
		}
		System.out.println("Process Size\tFree Block #\tFree Block Size");
		for (int i=0; i<n; i++) {
			max = -1;
			flag = 0;
			for (int j=0; j<f; j++) {
				if (ms[j]>=p[i] && flag==0) {
					max = j;
					flag = 1;
				}
				if (ms[j] >= p[i] && ms[j] >= ms[max] && flag == 1)
					max = j;
			}
			if (max != -1) {
				System.out.println(p[i] + " \t\t "+ (max+1) + " \t\t "+ ms[max]);
				ms[max] -= p[i];
			}
			else
				System.out.println(p[i] + " \t\t not assigned");
		}
		for (int i=0; i<f; i++) {
			netFrag+= ms[i];
		}
		System.out.println("The net external fragmentation is " + netFrag);
	}
}