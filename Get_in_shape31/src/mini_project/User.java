package mini_project;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

// User 클래스
public class User implements Color {

   Scanner scan = new Scanner(System.in);
   GisDAO da = new GisDAO();

   String now_id = "";
   String now_name = "";
   int progress = 1;
   double goalRate = 0;
   boolean status = true;
   int lastLogin = 0;

   // 회원가입 메소드
   public void signUp() {
      /*
       * 1. 이름, 아이디, pw, 생년월일 등을 입력받기 2. user 테이블에 추가 --> DB 3. userData 객체 생성하여
       * userList에 저장 3. 회원가입 완료 시, SignIn() 호출
       */

      boolean status = true;

      while (status) {

         status = false;
         System.out.print("이름 : ");
         String name = scan.nextLine();
         System.out.print("아이디 : ");
         String id = scan.nextLine();
         System.out.print("비밀번호 : ");
         String pw = scan.nextLine();
         System.out.print("생년월일 : ");
         String birth = scan.nextLine();

         for (int i = 0; i < da.getUserData().size(); i++) {
            if (da.getUserData().get(i).getUserId().equals(id)) {
               System.out.println(red + "이미 존재하는 아이디입니다." + exit);
               status = true;
            }
         }

         UserVO iUd = new UserVO();
         if (!status) {
            iUd.setUserName(name);
            iUd.setUserId(id);
            iUd.setUserPw(pw);
            iUd.setUserBirth(birth);

            if (da.insertSighUp(iUd) == 1) {
               System.out.println("회원가입이 완료되었습니다.");

               // 마이페이지 생성
               MyPageVO mvo = new MyPageVO();
               mvo.setMember_id(id);
               da.createMyPage(mvo);
            }
         }
      }

      signIn();
   }

   // 로그인 메소드
   public void signIn() {
      /*
       * 1. 아이디, pw 입력받기 2. 로그인 성공할 때까지 반복 ('q'입력하면 종료 -> SignOut()호출 ) 3. userList에서
       * 입력받은 id 있는지 확인 -> 없으면, "회원 정보가 없습니다." -> 있으면, 해당 id의 pw와 입력한 pw 비교 4. 로그인 성공
       * 또는 실패에 따른 출력
       */

      while (true) {

         System.out.println("--------------------------------------------------------------");
         System.out.print("ID를 입력해주세요(종료하고 싶다면 'q'입력): ");
         String id = scan.next();
         if (id.equals("q")) { // 완전종료
            signOut();
         }

         boolean temp = false;

         for (int i = 0; i < da.getUserData().size(); i++) {
            if (da.getUserData().get(i).getUserId().equals(id)) {

               temp = true;

               System.out.print("비밀번호를 입력해주세요: ");
               String pw = scan.next();

               if (da.getUserData().get(i).getUserPw().equals(pw)) {
                  System.out.println(red + "접속완료" + exit);
                  now_id = id;
                  now_name = da.getUserData().get(i).getUserName();

                  Date currentDate = new Date();
                  Date d = da.getMyPageData(this.now_id).get(0).getEndDate();
                  SimpleDateFormat stringDate = new SimpleDateFormat("yyyyMMdd");
                  String sd = stringDate.format(d);
                  String cd = stringDate.format(currentDate);

                  if (sd.equals(cd) || currentDate.compareTo(d) > 0) {
                     if (lastLogin == 0) {
                        status = false;
                        if (da.getMyInfo(this.now_id).get(0).getUserProgress().equals("진행 중")) {
                           if (da.modifyProgress(this.now_id) == 1) {

                              System.out.println("챌린지가 종료되었습니다");
                           }
                           if (da.getMyPageData(this.now_id).get(0).getGoalRate() == 100) {

                              if (da.updateLastPoint(this.now_id, 10000) == 1) {
                                 System.out.println("포인트 10000점 적립");
                              }
                           } else if (da.getMyPageData(this.now_id).get(0).getGoalRate() >= 70) {
                              if (da.updateLastPoint(this.now_id, 7000) == 1) {
                                 System.out.println("포인트 7000점 적립");
                              }
                           } else if (da.getMyPageData(this.now_id).get(0).getGoalRate() >= 50) {
                              if (da.updateLastPoint(this.now_id, 5000) == 1) {
                                 System.out.println("포인트 5000점 적립");
                              }
                           } else {
                              System.out.println("더 열심히 하셨어야죠..");
                              if (da.updateLastPoint(this.now_id, 3000) == 1) {
                                 System.out.println("그래도 포인트 3000점 드릴게요");
                              }
                           }

                        }

                        lastLogin++;
                     }
                  }

                  return;
               } else {
                  System.out.println(red + "비밀번호가 일치하치 않습니다." + exit);
                  break;
               }
            }
         }
         if (!temp)
            System.out.println(red + "일치하는 회원정보가 없습니다." + exit);
      }
   }

   // 로그아웃 메소드
   public void signOut() {
      /* 프로그램 강제 종료 */
      System.out.println("프로그램을 종료합니다.");
      System.exit(0); // 0이 강제종료
   }

   // 훈련일지 확인 메소드
   public void openTrainingLog() {
      progress = 1;
      /* 훈련일지 모든 컬럼에 대한 데이터 불러오기 --> DB */
      boolean status = true;
      for (int i = 0; i < da.getTrainingData().size(); i++) {

         TrainingDiaryVO u = da.getTrainingData().get(i);

         if (u.getUserId().equals(now_id)) {
            status = false;
            System.out.println("--------------------------------------------------------------");
            System.out.println("\t[" + progress + "번째 훈련일지]\n");
            System.out.printf("▶ 운동 날짜 : " + u.getToDate()+"\n");
            System.out.printf("▶ 운동 종류 : " + u.getWorkoutName() + " \t| ▶ 운동 부위 : " + u.getBodyPart()+"\n");
            System.out.printf("▶ 운동 시간 : %.1f분"+"\t| ▶ 운동 난이도 : %s", u.getWorkoutTime(), u.getDifficulty()+"\n");
            System.out.println("▶ 의견 : " + u.getToFeedback());
            System.out.println("--------------------------------------------------------------");
            
            
            
            
            
            
//            System.out.println("--------------------------------------");
//            System.out.println("\t[" + progress + "번째 훈련일지]\n");
//            System.out.println("▶ 운동 날짜: " + u.getToDate());
//            System.out.println("▶ 운동 종류: " + u.getWorkoutName() + "\t| ▶ 운동 부위: " + u.getBodyPart());
//            System.out.println("▶ 운동 시간: " + u.getWorkoutTime() +"분"+"\t| ▶ 운동 난이도: " + u.getDifficulty());
//            System.out.println("▶ 의견\t: " + u.getToFeedback());
//            System.out.println("--------------------------------------");
            progress++;
         }
      }
      if (status) {
         System.out.println("등록된 훈련일지가 없습니다. 운동을 시작하세요!");
      }
   }

   // 정보확인 메서드
   public void viewMyInfo() {
//      System.out.println("<1.내 정보 확인 / 2.정보 수정>");
      /*
       * 입력받은 메뉴에 따라 처리 1. 내 정보 확인 : id, 이름, pw, 생년월일 등의 기본 정보 2. 정보 수정 : pw 변경
       */
      while (true) {

         System.out.print("비밀번호 확인 : ");
         String pw = scan.next();

         if (pw.equals(da.getMyInfo(now_id).get(0).getUserPw())) {
        	System.out.println("--------------------------------------------------------------");
            System.out.println("[내 정보 확인]");
            System.out.println("아이디 : " + da.getMyInfo(now_id).get(0).getUserId());
            System.out.println("비밀번호 : " + da.getMyInfo(now_id).get(0).getUserPw());
            System.out.println("이름 : " + da.getMyInfo(now_id).get(0).getUserName());
            System.out.println("생년월일 : " + da.getMyInfo(now_id).get(0).getUserBirth());
            System.out.println("회원등급 : " + da.getMyInfo(now_id).get(0).getUserRank());
            System.out.println("진행 여부 : " + da.getMyInfo(now_id).get(0).getUserProgress());
            System.out.println("--------------------------------------------------------------");
            break;
         } else {
            System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
         }
      }

   }

   // 정보 수정 메서드
   public void editMyInfo() {

      while (true) {
    	 System.out.println("--------------------------------------------------------------");
         System.out.print("변경할 비밀번호 : ");
         String newPw = scan.next();

         if (newPw.equals(da.getMyInfo(now_id).get(0).getUserPw())) {
            System.out.println("현재 비밀번호가 같습니다");
         } else {

            if (da.modifyPw(now_id, newPw) == 1) {
               System.out.println("비밀번호가 변경되었습니다.");
               System.out.println("로그인을 다시 하세요");
               System.out.println("--------------------------------------------------------------");
               signIn();
            }
            break;
         }
      }
   }
}