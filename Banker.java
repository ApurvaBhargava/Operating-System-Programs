import java.util.Scanner;
class Banker {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of processes.");
		int n = input.nextInt();
		System.out.println("Enter number of resource types.");
		int m = input.nextInt();
		//data structures
		int[] avail = new int[m];
		System.out.println("Enter the instances of each resource available.");
		for(int i=0; i<m; i++)
			avail[i] = input.nextInt();
		int[][] max = new int[n][m];
		System.out.println("Enter the maximum resource matrix.");
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				max[i][j] = input.nextInt();
		int[][] alloc = new int[n][m];
		System.out.println("Enter the current allocation matrix.");
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				alloc[i][j] = input.nextInt();
		int[][] need = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++)
				need[i][j] = max[i][j] - alloc[i][j];
		}
		safe(m, n, avail, alloc, need);
		System.out.println("Enter the process index requesting resources.");
		int p = input.nextInt();
		int[] request = new int[m];
		System.out.println("Enter resource request.");
		for(int k =0; k<m; k++)
			request[k] = input.nextInt();
		int check = checkRequest(p, m, n, avail, alloc, need, request);
		if (check == 1) {
			safe(m, n, avail, alloc, need);
			System.out.println("Allocation matrix:");
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++)
					System.out.print(alloc[i][j] + "  ");
				System.out.println();
			}
			System.out.println("Need matrix:");
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++)
					System.out.print(need[i][j] + "  ");
				System.out.println();
			}
			System.out.println("Available resources:");
			for(int i=0; i<m; i++)
				System.out.print(avail[i] + "  ");
		}
	}

	//safety algorithm
	static void safe(int m, int n, int[] avail, int[][] alloc, int[][] need) {
		int count = 0;
		int[] work = new int[m];
		for(int i=0; i<m; i++)
			work[i] = avail[i];
		boolean[] finish = new boolean[n];
		for(int i=0; i<n; i++)
			finish[i] = false;
		int index=0, flag=0;
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				flag=0;
				if(finish[i]==false) {
					for(int j=0; j<m; j++) {
						if(need[i][j]<=work[j])
							flag++;
					}
				}
				if(flag==m) {
					for(int j=0; j<m; j++)
						work[j] = work[j] + alloc[i][j]; //free resources
					finish[i] = true;
					System.out.println("Allocated process: " + i);
					count++;
				}
			}
		}
		if(count >= n)
			System.out.println("Safe state. All processes allocated.");
		else
			System.out.println("All processes could not be allocated safely.");
	}

	//resource allocation for a process
	static int checkRequest(int i, int m, int n, int[] avail, int[][]alloc, int[][] need, int[] request) {
		int flag = 0;
		for(int j=0; j<m; j++) {
			if(request[j] > need[i][j]) {
				System.out.println("Error. Request exceeds need.");
				return 0;
			}
			else
				flag++;
		}
		if(flag == m) {
			for(int j=0; j<m; j++) {
				if(request[j]<=avail[j]) {
					avail[j] -= request[j];
					alloc[i][j] += request[j];
					need[i][j] -= request[j];
				}
				else {
					System.out.println("Resources not available.");
					return 0;
				}
			}
		}
		return 1;
	}
}