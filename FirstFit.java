import java.util.Scanner;

public class FirstFit {
	public static void main(String[] args) {
		int checker, netFrag = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of processes and free blocks.");
		int n = input.nextInt();
		int f = input.nextInt();
		int[] p =new int[n];
		int[] ms =new int[f];
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
			checker = 0;
			for (int j=0; j<f; j++) {
				if (ms[j] >= p[i]) {
					System.out.println(p[i] + " \t\t "+ (j+1) + " \t\t "+ ms[j]);
					ms[j] -= p[i];
					checker = 1;
					break;
				}
			}
			if (checker == 0)
				System.out.println(p[i] + " \t\t not assigned");
		}
		for (int i=0; i<f; i++) {
			netFrag+= ms[i];
		}
		System.out.println("The net external fragmentation is " + netFrag);
	}
}