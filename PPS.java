import java.util.*;
class Process implements Comparable<Process> {
	Process() {
		create();
	}
	String id;
	int cb, at, tat, wt, rt, pn;
	boolean finStatus = false;
	Scanner input = new Scanner(System.in);
	void create() {
		System.out.println("Enter process id, arrival time, cpu burst time, priority number.");
		this.id = input.nextLine();
		this.at = input.nextInt();
		this.cb = input.nextInt();
		this.pn = input.nextInt();
		rt = cb;
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

class PPS {
	//getNext method returns the index of the process with the highest priority
	static int getNext(ArrayList<Process> al, int timer) {
		int index = 0;
		for (int i=0; i<al.size(); i++) {
			if ((al.get(i).at <= timer) && (al.get(i).pn < al.get(index).pn) && (al.get(i).finStatus == false))
				index = i;
		}
		return index;
	}
	static void displayTaskScheduler(ArrayList<Process> al) {
		System.out.println("Process\tAT\tCBT\tPriority\tTAT\tWT");
		for (int i =0; i<al.size(); i++)
			System.out.println(al.get(i).id + "\t" + al.get(i).at + "\t" + al.get(i).cb + "\t" + al.get(i).pn + "\t\t" + al.get(i).tat + "\t" +al.get(i).wt);
	}
	public static void main(String[] args) {
		int n, x, timer=0, checker=0;
		float atat=0, awt=0;
		Scanner input = new Scanner(System.in);
		ArrayList<Process> al = new ArrayList<Process>();
		Process temp;
		System.out.println("How many processes would you like to enter?");
		try { n = input.nextInt(); }
		catch(Exception e) { System.out.println("Enter a whole number."); n = input.nextInt(); }
		for (int i=0; i<n; i++) {
			temp = new Process();
			al.add(temp);
		}
		Collections.sort(al);
		while (checker != n) {
			x = getNext(al, timer);
			al.get(x).rt--;
			if (timer < al.get(x).at) al.get(x).rt++;
			if (al.get(x).rt == 0) {
				al.get(x).tat = timer - al.get(x).at + 1;
				al.get(x).wt = al.get(x).tat - al.get(x).cb;
				al.get(x).finStatus = true;
				atat += al.get(x).tat;
				awt += al.get(x).wt;
				checker++;
			}
			timer++;
		}
		displayTaskScheduler(al);
		atat /= n;	
		awt /= n;
		System.out.println("The average turnaround time is " + atat + " units.");
		System.out.println("The average waiting time is " + awt + " units.");
	}
}