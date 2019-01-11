import java.util.Scanner;
public class PageReplacement {
	
	static void displayFrames(int[] pageQueue) {
		for (int i=0; i<pageQueue.length; i++)
			System.out.print(pageQueue[i] + "  ");
	}
	
	static int fifo(int num, int[] pg) {
		int flag, ptr = 0, pageFault = 0;
		int[] pageQueue = new int[num];
		for(int i=0; i<num; i++)
			pageQueue[i] = -1;
		for(int i=0; i<pg.length; i++) {
			flag = 0;
			for(int j=0; j<num; j++) {
				if(pg[i] == pageQueue[j]) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				pageQueue[ptr] = pg[i];
				pageFault++;
				ptr++;
				if(ptr >= num)
					ptr = 0;
			}
			displayFrames(pageQueue);
			System.out.println();
		}
		return pageFault;
	}

	static int lru(int num, int[] pg) {
		int flag, min, count=0, ptr = 0, pageFault = 0;
		int[] pageQueue = new int[num];
		int[] score = new int[num];
		for(int i=0; i<num; i++)
			pageQueue[i] = -1;
		for(int i=0; i<num; i++)
			score[i] = -1;
		for(int i=0; i<pg.length; i++) {
			flag = 0;
			for(int j=0; j<num; j++) {
				if(pg[i] == pageQueue[j]) {
					score[j] = count++;
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				min = 0;
				for(int k=0; k<num; k++) {
					if(score[k] < score[min])
						min = k;
				}
				ptr = min;
				pageQueue[ptr] = pg[i];
				score[ptr] = count++;
				pageFault++;
			}
			displayFrames(pageQueue);
			System.out.println();
		}
		return pageFault;
	}
	
	static void updateScore(int[] pageQueue, int[] score, int[] pg, int val) {
		for(int i=0; i<score.length; i++) {
			score[i] = 0;
			for (int j=val; j<pg.length; j++) {
				if(pageQueue[i] != pg[j])
					score[i]++;
				else
					break;
			}
		}
	}
	
	static int opt(int num, int[] pg) {
		int flag, max, ptr = 0, pageFault = 0;
		int[] pageQueue = new int[num];
		int[] score = new int[num];
		for(int i=0; i<num; i++)
			pageQueue[i] = -1;
		for(int i=0; i<pg.length; i++) {
			flag = 0;
			for(int j=0; j<num; j++) {
				if(pg[i] == pageQueue[j]) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				updateScore(pageQueue, score, pg, i+1);
				max = 0;
				for(int k=0; k<num; k++) {
					if(score[k] > score[max])
						max = k;
				}
				ptr = max;
				pageQueue[ptr] = pg[i];
				pageFault++;
			}
			displayFrames(pageQueue);
			System.out.println();
		}
		return pageFault;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int userInput;
		System.out.println("Enter the number of frames.");
		int num = input.nextInt();
		System.out.println("Enter number of pages.");
		int p = input.nextInt();
		System.out.println("Enter page sequence.");
		int[] pg = new int[p];
		for(int i=0; i<p; i++)
			pg[i] = input.nextInt();
		mainloop:
		while(true) {
			input.nextLine();
			System.out.println("Press 1 for FIFO, 2 for LRU, 3 for Optimal page replacement. Press 0 for exiting.");
			userInput = input.nextInt();
			switch (userInput) {
				case 0:
					break mainloop;
				case 1:
					System.out.println("Page fault count = " + fifo(num, pg));
					break;
				case 2:
					System.out.println("Page fault count = " + lru(num, pg));
					break;
				case 3:
					System.out.println("Page fault count = " + opt(num, pg));
					break;
				default:
					System.out.println("Enter a number between 0 and 4.");
			}
		}
	}
}