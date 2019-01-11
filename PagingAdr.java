import java.util.Scanner;
class PagingAdr {
	static int indexPower(int num) {
		int count = 0;
		while(num != 1) {
			num /= 2;
			count++;
		}
		return count;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter RAM size."); //physical memory size
		int pm = scan.nextInt();
		System.out.println("Enter instruction size."); //logical memory size
		int lm = scan.nextInt();
		System.out.println("Enter page size."); //offset
		int pgSize = scan.nextInt();
		//bit representation conversion
		pm = indexPower(pm);
		pgSize = indexPower(pgSize);
		//create page table for a process
		System.out.println("Enter number of pages.");
		int n = scan.nextInt();
		scan.nextLine();
		String[][] pt = new String[n][2];
		System.out.println("Enter page number and frame number in page table.");
		for(int i=0; i<n; i++) {
			pt[i][0] = scan.nextLine();
			pt[i][1] = scan.nextLine();
		}
		String adr, page, offset, frame="";
		while(true) {
			System.out.println("Enter the logical address to be translated. Enter 'exit' to close.");
			adr = scan.nextLine();
			if (adr.equals("exit"))
				return;
			if (adr.length() != lm) {
				System.out.println("Address is invalid.");
				continue;
			}
			page = adr.substring(0, lm-pgSize);
			offset = adr.substring(lm-pgSize);
			for(int i=0; i<n; i++) {
				if (page.equals(pt[i][0])) {
					frame = pt[i][1];
					break;
				}
			}
			adr = frame + offset;
			System.out.println("Physical address: " + adr);
		}
	}
}