package Main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GetMessage gm = new GetMessage();
		for (int i = 0; i < 1; i--) {
			String sc_a = sc.nextLine();
			if (sc_a.length() == 5) {
				System.out.println(gm.GetMessage(sc_a));
			} else {
				System.out.println("������󣬸�ʽΪ����+����ţ���10101");
			}
		}
		
		
	}

}
