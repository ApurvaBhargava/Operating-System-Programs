import java.util.Scanner;
class DiskSCAN {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of tracks.");
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
		int index=0, temp=0, min = size+1;
		for(int i=0; i<num; i++) {
			temp = Math.abs(cp-arr[i]);
			if(temp<min && cp > arr[i]) {
				min = temp;
				index = i;
			}
		}
		visit[index] = 1;
		netSeek += min;
		cp = arr[index];
		for(int j=0; j<num; j++) {
			min = size+1;
			for(int i=0; i<num; i++) {
				temp = Math.abs(cp-arr[i]);
				if(visit[i]==0 && arr[i] < cp && temp<min) {
					min = temp;
					index = i;
				}
			}
			if(min != size + 1) {
				visit[index] = 1;
				netSeek += min;
				cp = arr[index];
			}
		}
		if(cp != 0) {
			netSeek += cp;
			cp = 0;
		}
		for(int j=0; j<num; j++) {
			min = size+1;
			for(int i=0; i<num; i++) {
				temp = Math.abs(cp-arr[i]);
				if(visit[i]==0 && arr[i] > cp && temp<min) {
					min = temp;
					index = i;
				}
			}
			if(min != size + 1) {
				visit[index] = 1;
				netSeek += min;
				cp = arr[index];
			}
		}
		avgSeek = netSeek / num;
		System.out.println("Total seek time: " + netSeek);
		System.out.println("Average seek time: " + avgSeek);
	}
}