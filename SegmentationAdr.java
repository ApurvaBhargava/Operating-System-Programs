import java.util.Scanner;
class SegmentationAdr {
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
		System.out.println("Enter maximum number of segments.");
		int sgNum = scan.nextInt();
		System.out.println("Enter maximum possible size of segment.");
		int sgSize = scan.nextInt();
		//bit representation conversion
		sgNum = indexPower(sgNum);
		sgSize = indexPower(sgSize);
		//create segment table for a process
		System.out.println("Enter number of segments.");
		int n = scan.nextInt();
		scan.nextLine();
		String[][] st = new String[n][3];
		System.out.println("Enter segment number, limit and base of the segment. " + sgSize + " is the number of bits in limit. The base has as many bits as instruction size.");
		for(int i=0; i<n; i++) {
			st[i][0] = scan.nextLine();
			st[i][1] = scan.nextLine();
			st[i][2] = scan.nextLine();
		}
		String adr, sgmt, offset, limit, base;
		while(true) {
			System.out.println("Enter the logical address to be translated. Enter 'exit' to close.");
			adr = scan.nextLine();
			if (adr.equals("exit"))
				return;
			sgmt = adr.substring(0, sgNum);
			offset = adr.substring(sgNum);
			for(int i=0; i<n; i++) {
				if (sgmt.equals(st[i][0])) {
					//check offset
					limit = st[i][1];
					base = st[i][2];
					if(Integer.parseInt(offset, 2) >= Integer.parseInt(limit, 2)) {
						System.out.println("Offset out of bounds.");
						continue;
					}
					else {
						adr =Integer.toBinaryString(Integer.parseInt(base, 2) + Integer.parseInt(offset, 2));
						break;
					}
				}
			}
			System.out.println("Physical address: " + adr);
		}
	}
}