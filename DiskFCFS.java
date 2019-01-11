import java.util.Scanner;
class DiskFCFS {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of disk operations.");
		int num = scan.nextInt();
		System.out.println("Enter the current position.");
		int cp = scan.nextInt();
		int[] arr = new int[num];
		System.out.println("Enter the locations.");
		for(int i=0; i<num; i++)
			arr[i] = scan.nextInt();
		float netSeek=0, avgSeek;
		for(int i=0; i<num; i++) {
			netSeek += Math.abs(cp-arr[i]);
			cp = arr[i];
		}
		avgSeek = netSeek / num;
		System.out.println("Total seek time: " + netSeek);
		System.out.println("Average seek time: " + avgSeek);
	}
}