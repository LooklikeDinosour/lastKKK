package mini_project;
import java.sql.Date;
import java.util.Random;
import java.util.Scanner;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Training extends User implements Color {

	Scanner scan = new Scanner(System.in);
	
	GisDAO gdao = new GisDAO();
	
	String workOut_Name;
	String workOut_Link;
	int workOut_Reps;
	String body_part;
	int rewardPoint;
	String now_id;
	String now_name;
	double workOut_time;
	
	// 오늘의 운동을 확인하는 메소드
	public void TodayTraining() {
		/*
		 * 1. Training 테이블 count범위(가능하면)의 랜덤 수 생성 (우선, 미리 입력한 운동 종류 수에 맞춰 만들기) 
		 * 2. 랜덤 번호에 해당하는 운동 종류와 개수 출력 //변수에도 저장 필요 --> DB 
		 * "오늘의 운동은 '스쿼트 30'회 입니다."
		 * "운동을 시작하겠습니까?(y/n)" 
		 * 3. y 입력 시, "지금부터 운동을 시작하세요!"출력 후, 
		 * StartTraining()호출 n 입력 시, "내일은 꼭 하세요! 그럼 안녕!"출력 후, 종료
		 */

		Random rd = new Random();
		int num = rd.nextInt(gdao.getWorkOutListCnt()) + 1;
		
		gdao.getTodayWorkOut(num);
		
		this.workOut_Name = gdao.getTodayWorkOut(num).get(0).getWorkOut_name();
		this.workOut_Reps = gdao.getTodayWorkOut(num).get(0).getWorkOut_reps();
		this.workOut_Link = gdao.getTodayWorkOut(num).get(0).getWorkOut_link();
		
		System.out.printf("오늘의 운동은 %s %d회 입니다.", this.workOut_Name, this.workOut_Reps);
		
		while(true) {
			
			System.out.print("\n운동을 시작하시겠습니까? \n(Y) or (N)를 입력해주세요! > ");
			String yn = scan.nextLine();
			if(yn.equals("Y") || yn.equals("y")) {
				System.out.println("지금부터 운동을 시작합니다");
				startTraining();
				break;
			} else if(yn.equals("N") || yn.equals("n")) {
				System.out.println("내일 꼭 다시 뵈어요! 그럼 안녕!");
				System.exit(0);
			} else {
				System.out.println("Y/y나 N/n를 입력하세요");
			}
		}
	}

	// 운동 시작 메소드
	public void startTraining() {
		/*
		 * 오늘 운동에 대한 정보 불러오기 --> DB
		System.out.println("운동이 끝나면 아무 키나 입력하세요.");
		/*
		 * 아무 키 입력받으면 WriteLog() 호출
		 */
		
		this.body_part = gdao.getWorkOutInfo(this.workOut_Name).get(0).getBody_part();
		String workOut_explain = gdao.getWorkOutInfo(this.workOut_Name).get(0).getWorkOut_explain();
		
		double start = System.currentTimeMillis();
		System.out.println("==============================================================");
		System.out.println("운동명 : "+ this.workOut_Name);
		System.out.println("수행할 횟수 : "+ this.workOut_Reps);
		System.out.println("자극부위 : "+ this.body_part);
		System.out.println("운동설명 : "+ workOut_explain);
		System.out.println("==============================================================");
		
		viewTraining();
		
		System.out.print("운동이 끝나면 아무 키나 입력해주세요.\n>> ");
		String s = scan.nextLine();
		
		double end = System.currentTimeMillis();
	    this.workOut_time = (end - start);
	    
		writeLog();
		
		
	}

	// 운동방법 링크 연결 메소드
	public void viewTraining() {
	String urlLink = this.workOut_Link;
		
		try {
			Desktop.getDesktop().browse(new URI(urlLink));
		} catch(IOException e) {
			e.printStackTrace();
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	// 훈련일지 작성 메소드
	public void writeLog() {
		/*
		 * 1. 운동시간, 난이도 및 다짐 각각 입력받기 
		 * 2. 오늘 날짜 불러오기 
		 * 3. 오늘 날짜, 운동시간, 난이도, 다짐 등과 함께 운동 정보를
		 * 훈련일지 테이블에 추가 --> DB 4. Mypage() 함수 호출
		 */
		
		System.out.print("오늘의 운동시간 : " );
		System.out.println(Math.ceil(this.workOut_time * 0.001) + "분 입니다.");
		System.out.print("오늘의 체감난이도 : ");
		String difficulty = scan.next();
		System.out.print("오늘의 한줄 피드백 : ");
		scan.nextLine();
		String feedback = scan.nextLine();
		
		TrainingDiaryVO tvo = new TrainingDiaryVO();
		
		tvo.setUserId(this.now_id);
		tvo.setWorkoutName(workOut_Name);
		tvo.setBodyPart(body_part);
		tvo.setWorkoutTime(Math.ceil(this.workOut_time * 0.001));
		tvo.setToFeedback(feedback);
		tvo.setDifficulty(difficulty);
		System.out.println("==============================================================");
		
		if(gdao.insertTrainingLog(tvo) == 1) {
			System.out.println("훈련일지 등록이 완료되었습니다. 수고하셨습니다.");
			plusPoint();
		}
		
		updateMemberRank();
	}
	
	//마이페이지 업데이트 메서드
	public void plusPoint() {
		
		if(gdao.updateMypage(this.now_id) == 1) {
			System.out.println("포인트와 목표 달성률이 갱신되었습니다. 마이페이지에서 확인해보세요");
		} 
	}
	
	// 회원등급 갱신 메서드
	public void updateMemberRank() {
		if(gdao.getCount(this.now_id) == 10) {
			if(gdao.bronzeToSilver(this.now_id) == 1) {
				System.out.println("SILVER로 등급이 상승되었습니다");
			}
			
			
		} else if(gdao.getCount(this.now_id) == 20) {
			if(gdao.silverToGold(this.now_id) == 1) {
				System.out.println("GOLD로 등급이 상승되었습니다");
			}
		}
	}

}
