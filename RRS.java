import java.util.*;
class Process implements Comparable<Process> {
	Process() {
		create();
	}
	String id;
	int at, cb, tat, wt, rt;
	boolean ready = false;
	Scanner input = new Scanner(System.in);
	void create() {
		System.out.println("Enter process id, arrival time, cpu burst time.");
		this.id = input.nextLine();
		this.at = input.nextInt();
		this.cb = input.nextInt();
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

class RRS {
	static void displayTaskScheduler(ArrayList<Process> al) {
		System.out.println("Process\tAT\tCBT\tTAT\tWT");
		for (int i =0; i<al.size(); i++)
			System.out.println(al.get(i).id + "\t" + al.get(i).at + "\t" + al.get(i).cb + "\t" + al.get(i).tat + "\t" +al.get(i).wt);
	}
	public static void main(String[] args) {
		int n, flag, timer=0, checker = 0, tq;
		float atat=0, awt=0;
		Scanner input = new Scanner(System.in);
		ArrayList<Process> al = new ArrayList<Process>();
		Process temp;
		System.out.println("How many processes would you like to enter?");
		n = input.nextInt();
		System.out.println("Enter the time quantum.");
		tq = input.nextInt();
		for(int i=0; i<n; i++) {
			temp = new Process();
			al.add(temp);
		}
		Collections.sort(al);
		while (checker != n) {
			for (int i=0; i<n; i++) {
				flag = 0;
				for(int j=0; j<n; j++) {
					if(al.get(j).ready==true)
						flag++;
				}
				//checking whether all the executing processes are completed
				//timer is set to the process whose execution has not been started
				if (flag==checker && checker!=n)
					timer = al.get(checker).at;
				if (al.get(i).rt !=0) {
					if (timer < al.get(i).at)
						continue;
					if (al.get(i).rt < tq) {
						al.get(i).ready = true;
						timer += al.get(i).rt;
						al.get(i).rt = 0;
					}
					else {
						al.get(i).ready = true;
						al.get(i).rt -= tq;
						timer += tq;
					}
					if (al.get(i).rt == 0) {
						al.get(i).tat = timer - al.get(i).at;
						al.get(i).wt = al.get(i).tat - al.get(i).cb;
						atat += al.get(i).tat;
						awt += al.get(i).wt;
						checker++;
					}
				}
			}
		}
		displayTaskScheduler(al);
		atat /= n;
		awt /= n;
		System.out.println("The average turnaround time is " + atat + " units.");
		System.out.println("The average waiting time is " + awt + " units.");
	}
}