import java.util.*;
class Process implements Comparable<Process> {
	Process() {
		create();
	}
	String id;
	int at, cb, tat, wt;
	Scanner input = new Scanner(System.in);
	void create() {
		System.out.println("Enter process id, arrival time, cpu burst time.");
		this.id = input.nextLine();
		this.at = input.nextInt();
		this.cb = input.nextInt();
	}
	public int compareTo(Process p) {
		if (at > p.at)
			return 1;
		else if (at == p.at)
			return cb - p.cb;
		else
			return -1;
	}
}

class FCFS {
	static void displayTaskScheduler(ArrayList<Process> al) {
		System.out.println("Process\tAT\tCBT\tTAT\tWT");
		for (int i =0; i<al.size(); i++)
			System.out.println(al.get(i).id + "\t" + al.get(i).at + "\t" + al.get(i).cb + "\t" + al.get(i).tat + "\t" +al.get(i).wt);
	}
	public static void main(String[] args) {
		int n, timer=0, diff;
		float atat=0, awt=0;
		Scanner input = new Scanner(System.in);
		ArrayList<Process> al = new ArrayList<Process>();
		Process temp;
		System.out.println("How many processes would you like to enter?");
		n = input.nextInt();
		for (int i=0; i<n; i++) {
			temp = new Process();
			al.add(temp);
		}
		Collections.sort(al);
		for (int i=0; i<al.size(); i++) {
			if (timer < al.get(i).at) {
				//the difference between the timer and the next arrival is added to timer; alternatively, just increment timer by 1
				diff = al.get(i).at - timer;
				timer += diff;
			}
			timer += al.get(i).cb;
			al.get(i).tat = timer - al.get(i).at;
			al.get(i).wt = al.get(i).tat - al.get(i).cb;
			atat += al.get(i).tat;
			awt += al.get(i).wt;
		}
		displayTaskScheduler(al);
		atat /= n;
		awt /= n;
		System.out.println("The average turnaround time is " + atat + " units.");
		System.out.println("The average waiting time is " + awt + " units.");
	}
}