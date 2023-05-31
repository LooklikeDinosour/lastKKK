package mini_project;

import java.util.Scanner;

public class EventClass {

	PageClass p = new PageClass();
	Scanner scan = new Scanner(System.in);

	public void startProgram(String s) {

		if(s.equals("false")) {
			System.out.println("다음 도전을 기다리겠습니다");
			System.exit(0);
		} else if(s.equals("true")) {

			System.out.println("==============================================================");

			System.out.println("<1.회원가입 / 2.로그인>");
			System.out.print(">> ");
			if(scan.nextInt() == 1) p.signUp();
			else p.signIn();

			if(p.status) {
				// 프로그램 시작
				p.firstPage();
			} else {
				p.lastPage();
			}

			// 프로그램 시작
			p.firstPage();
		}
	}

}