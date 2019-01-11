import java.util.Scanner;

public class BestFit {
	public static void main(String[] args) {
		int flag, min, netFrag = 0;
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
			min = -1;
			flag = 0;
			for (int j=0; j<f; j++) {
				if (ms[j]>=p[i] && flag==0) {
					min = j;
					flag = 1;
				}
				if (ms[j] >= p[i] && ms[j] <= ms[min] && flag == 1)
					min = j;
			}
			if (min != -1) {
				System.out.println(p[i] + " \t\t "+ (min+1) + " \t\t "+ ms[min]);
				ms[min] -= p[i];
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