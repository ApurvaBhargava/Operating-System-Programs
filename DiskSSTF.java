import java.util.Scanner;
class DiskSSTF {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter total number of tracks.");
		int size = scan.nextInt();
		System.out.println("Enter the number of disk operations.");
		int num = scan.nextInt();
		System.out.println("Enter the current position.");
		int cp = scan.nextInt();
		int[] arr = new int[num];
		int[] visit = new int[num];
		System.out.println("Enter the locations.");
		for(int i=0; i<num; i++) {
			arr[i] = scan.nextInt();
			visit[i] = 0;
		}
		float netSeek=0, avgSeek;
		int temp, index=0, min=0, count=0;
		while(count != num) {
			min = size + 1;
			for(int i=0; i<num; i++) {
				if(visit[i]==0) {
					temp = Math.abs(cp - arr[i]);
					if(temp < min) {
						min = temp;
						index = i;
					}
				}
			}
			netSeek += min;
			visit[index] = 1;
			count++;
			cp = arr[index];
		}
		avgSeek = netSeek / num;
		System.out.println("Total seek time: " + netSeek);
		System.out.println("Average seek time: " + avgSeek);
	}
}