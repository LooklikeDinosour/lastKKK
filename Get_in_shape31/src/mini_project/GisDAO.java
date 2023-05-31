package mini_project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GisDAO { // Database Access Object

   // 멤버변수
   private String url = DBProperties.URL;
   private String uid = DBProperties.UID;
   private String upw = DBProperties.UPW;

   // 생성자
   public GisDAO() {

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
      } catch (Exception e) {
         System.out.println("CLASS FOR NAME ERR");
      }

   }

   // Return Data from table 메소드 : 유저데이터 불러오기
   public List<UserVO> getUserData() {

      List<UserVO> list = new ArrayList<>();

      String sql = "SELECT * FROM GIS_MEMBERS";
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            String member_id = rs.getString("MEMBER_ID");
            String member_name = rs.getString("MEMBER_NAME");
            String member_pw = rs.getString("MEMBER_PW");
            String member_birth = rs.getString("MEMBER_BIRTH");
            String member_progress = rs.getString("MEMBER_PROGRESS");

            UserVO uvo = new UserVO();

            uvo.setUserId(member_id);
            uvo.setUserName(member_name);
            uvo.setUserPw(member_pw);
            uvo.setUserBirth(member_birth);
            uvo.setUserProgress(member_progress);

            list.add(uvo);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return list;
   }

   // 특정 아이디

   // Return Data from table 메소드 : 훈련일지 데이터 불러오기
   public List<TrainingDiaryVO> getTrainingData() {

      List<TrainingDiaryVO> list = new ArrayList<>();

      String sql = "SELECT * FROM GIS_TRAINING_DIARY";
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            Date toDate = rs.getDate("TD_DATE");
            String userId = rs.getString("MEMBER_ID");
            String workoutName = rs.getString("WORKOUT_NAME");
            String bodyPart = rs.getString("BODY_PART");
            double workoutTime = rs.getDouble("WORKOUT_TIME");
            String toFeedback = rs.getString("TD_FEEDBACK");
            String difficulty = rs.getString("DIFFICULTY");

            TrainingDiaryVO td = new TrainingDiaryVO();

            td.setToDate(toDate);
            td.setUserId(userId);
            td.setWorkoutName(workoutName);
            td.setBodyPart(bodyPart);
            td.setWorkoutTime(workoutTime);
            td.setToFeedback(toFeedback);
            td.setDifficulty(difficulty);

            list.add(td);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return list;
   }

   // Return Data from tabel 메서드 : 특정 아이디의 훈련날짜 불러오기
   public List<TrainingDiaryVO> getTD_Date(String now_id) {
      List<TrainingDiaryVO> list = new ArrayList<>();

      String sql = "SELECT MAX(TD_DATE) AS RECENTDATE\r\n" + "FROM GIS_TRAINING_DIARY WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            Date toDate = rs.getDate("RECENTDATE");

            TrainingDiaryVO tvo = new TrainingDiaryVO();
            tvo.setToDate(toDate);

            list.add(tvo);

         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }

      return list;
   }

   // Return Data from table 메소드 : 계정 당 훈련일수 불러오기
   public int getCount(String now_id) {

      String sql = "SELECT TO_CHAR(TD_DATE, 'YY/MM/DD') FROM gis_training_diary \r\n" + "WHERE MEMBER_ID = ? \r\n"
            + "GROUP BY TO_CHAR(TD_DATE, 'YY/MM/DD')";
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int cnt = 0;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);

         rs = pstmt.executeQuery();

         while (rs.next()) {
            cnt++;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return cnt;
   }

   // Return Data from table 메소드 : 운동리스트 개수 불러오기
   public int getWorkOutListCnt() {

//      List<WorkOutListVO> list = new ArrayList<>();

      String sql = "SELECT * FROM GIS_WORKOUT";

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int count = 0;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            count++;
         }
      } catch (Exception e) {
         // TODO: handle exception
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return count;
   }

   // Return Data from table 메소드 : 운동 진행 설명 데이터 불러오기
   public List<WorkOutListVO> getWorkOutInfo(String workOut_name) {
      List<WorkOutListVO> list = new ArrayList<>();
      String sql = "SELECT BODY_PART, WORKOUT_EXPLAIN FROM GIS_WORKOUT\r\n" + "WHERE WORKOUT_NAME = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, workOut_name);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            String body_part = rs.getString("BODY_PART");
            String workOut_explain = rs.getString("WORKOUT_EXPLAIN");

            WorkOutListVO wvo = new WorkOutListVO();
            wvo.setBody_part(body_part);
            wvo.setWorkOut_explain(workOut_explain);
            list.add(wvo);

         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }

      return list;
   }


   
   // Return Data from table 메소드 : 운동 추천 데이터 불러오기 
      public List<WorkOutListVO> getTodayWorkOut(int num){
         List<WorkOutListVO> list = new ArrayList<>();
         String sql = "SELECT WORKOUT_NAME, WORKOUT_REPS, WORKOUT_LINK FROM GIS_WORKOUT \r\n"
               + "WHERE GIS_WORKOUT_SEQ = ?";
         
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         
         try {
            conn = DriverManager.getConnection(url, uid, upw);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
               String workOut_name = rs.getString("WORKOUT_NAME");
               int workOut_reps = rs.getInt("WORKOUT_REPS");
               String workOut_link = rs.getString("WORKOUT_LINK");
               
               WorkOutListVO wvo = new WorkOutListVO();
               wvo.setWorkOut_name(workOut_name);
               wvo.setWorkOut_reps(workOut_reps);
               wvo.setWorkOut_link(workOut_link);
               
               list.add(wvo);
               
            }
            
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }finally {
            try {
               conn.close();
               pstmt.close();
               rs.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
         }
         return list;
               
      }


   // Return Data from table 메소드 : 마이페이지 데이터 불러오기
   public List<MyPageVO> getMyPageData(String now_id) {
      List<MyPageVO> list = new ArrayList<>();
      String sql = "SELECT * FROM GIS_MYPAGE WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            String member_id = rs.getString("MEMBER_ID");
            int member_point = rs.getInt("MEMBER_POINT");
            int member_goalRate = rs.getInt("MEMBER_GOALRATE");
            Date startDate = rs.getDate("MEMBER_STARTDATE");
            Date endDate = rs.getDate("MEMBER_ENDDATE");

            MyPageVO mvo = new MyPageVO();
            mvo.setMember_id(member_id);
            mvo.setMember_point(member_point);
            mvo.setGoalRate(member_goalRate);
            mvo.setStartDate(startDate);
            mvo.setEndDate(endDate);

            list.add(mvo);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }

      return list;
   }

   // Return Data from table 메소드 : 내 정보 데이터 불러오기
   public List<UserVO> getMyInfo(String now_id) {
      List<UserVO> list = new ArrayList<>();

      String sql = "SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, TO_CHAR(MEMBER_BIRTH, 'YYYY/MM/DD') AS MEMBER_BIRTH, MEMBER_GRADE, MEMBER_PROGRESS \r\n"
            + "FROM GIS_MEMBERS WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            String member_id = rs.getString("MEMBER_ID");
            String member_name = rs.getString("MEMBER_NAME");
            String member_pw = rs.getString("MEMBER_PW");
            String member_birth = rs.getString("MEMBER_BIRTH");
            String member_rank = rs.getString("MEMBER_GRADE");
            String member_progress = rs.getString("MEMBER_PROGRESS");

            UserVO uvo = new UserVO();

            uvo.setUserId(member_id);
            uvo.setUserName(member_name);
            uvo.setUserPw(member_pw);
            uvo.setUserBirth(member_birth);
            uvo.setUserRank(member_rank);
            uvo.setUserProgress(member_progress);

            list.add(uvo);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }
      return list;
   }

   // Insert Data to table 메소드 : 유저 데이터 추가
   public int insertSighUp(UserVO ud) {
      int result = 0;

      Connection conn = null;
      PreparedStatement pstmt = null;

      String sql = "INSERT INTO GIS_MEMBERS VALUES (?, ?, ?, ?, 'BRONZE', '진행 중')";

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, ud.getUserId());
         pstmt.setString(2, ud.getUserPw());
         pstmt.setString(3, ud.getUserName());
         pstmt.setString(4, ud.getUserBirth());

         result = pstmt.executeUpdate();

      } catch (Exception e) {
         // TODO: handle exception
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } finally {
            try {
               conn.close();
               pstmt.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

         }
      }

      return result;
   }

   // Insert Data to table 메소드 : 훈련일지 기록
   public int insertTrainingLog(TrainingDiaryVO tvo) {
      int result = 0;

      Connection conn = null;
      PreparedStatement pstmt = null;

      String sql = "INSERT INTO GIS_TRAINING_DIARY\r\n" + "VALUES(TO_CHAR(SYSDATE, 'YY/MM/DD'), ?, ?, ?, ?, ?, ?)";

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, tvo.getUserId());
         pstmt.setString(2, tvo.getWorkoutName());
         pstmt.setString(3, tvo.getBodyPart());
         pstmt.setDouble(4, tvo.getWorkoutTime());
         pstmt.setString(5, tvo.getToFeedback());
         pstmt.setString(6, tvo.getDifficulty());

         result = pstmt.executeUpdate();

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }

      return result;
   }

   // Indert Data to table 메서드 : 초기 마이페이지 생성
   public int createMyPage(MyPageVO mvo) {
      int result = 0;

      String sql = "INSERT INTO GIS_MYPAGE \r\n"
            + "VALUES (?, 0, 0, TO_CHAR(SYSDATE, 'YY/MM/DD'), TO_CHAR(SYSDATE + 31 , 'YY/MM/DD'))";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, mvo.getMember_id());
         result = pstmt.executeUpdate();

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return result;
   }

   // Update Data 메소드 : 포인트와 목표달성률 갱신 메서드

   public int updateMypage(String now_id) {
      int result = 0;

      String sql = "UPDATE GIS_MYPAGE\r\n" + "SET MEMBER_POINT = ?, MEMBER_GOALRATE = ?\r\n" + "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, getMyPageData(now_id).get(0).getMember_point() + 1000);
         pstmt.setInt(2, getCount(now_id) * 100 / 31);
         pstmt.setString(3, now_id);
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }
      return result;
   }

   // Update Data 메소드 : 포인트 추가 메서드
   public int updateLastPoint(String now_id, int addPoint) {
      int result = 0;

      String sql = "UPDATE GIS_MYPAGE\r\n" + "SET MEMBER_POINT = ? + ?" + "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, getMyPageData(now_id).get(0).getMember_point());
         pstmt.setInt(2, addPoint);
         pstmt.setString(3, now_id);
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }
      return result;
   }

   // Update Data 메소드 : 회원등급 bronze에서 silver로 업뎃하는 메서드
   public int bronzeToSilver(String now_id) {
      int result = 0;

      String sql = "UPDATE GIS_MEMBERS\r\n" + "SET MEMBER_GRADE = 'SILVER'\r\n" + "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }
      return result;
   }

   // Update Data 메소드 : 회원등급 silver에서 gold로 업뎃하는 메서드
   public int silverToGold(String now_id) {
      int result = 0;

      String sql = "UPDATE GIS_MEMBERS\r\n" + "SET MEMBER_GRADE = 'GOLD'\r\n" + "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return result;
   }

   // Update Data 메소드 : 회원정보 수정 메서드
   public int modifyPw(String now_id, String newPw) {
      int result = 0;

      String sql = "UPDATE GIS_MEMBERS\r\n" + "SET MEMBER_PW = ?\r\n" + "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, newPw);
         pstmt.setString(2, now_id);
         result = pstmt.executeUpdate();

      } catch (SQLException e) {
         // TODO Auto-generated catch block

         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return result;
   }

   // Update Data 메소드: 완료여부 수정하는 메서드
   public int modifyProgress(String now_id) {
      int result = 0;

      String sql = "UPDATE GIS_MEMBERS\r\n" + "SET MEMBER_PROGRESS = '완료'\r\n" + "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, now_id);
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return result;
   }

   // 김정수 추가
   // shoppingmall data 불러오기
   public List<ShoppingMallVO> getShoppingMallItem() {

      List<ShoppingMallVO> list = new ArrayList<>();

      String sql = "SELECT * FROM GIS_SHOPPING";
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            int item_num = rs.getInt("ITEM_NUM");
            String item_brand = rs.getString("BRAND_NAME");
            String item_name = rs.getString("ITEM_NAME");
            int item_point = rs.getInt("ITEM_POINT");

            ShoppingMallVO smv = new ShoppingMallVO();

            smv.setItemNum(item_num);
            smv.setItemBrand(item_brand);
            smv.setItemName(item_name);
            smv.setItemPoint(item_point);

            list.add(smv);

         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return list;
   }

   // 김정수
   // 멤버포인트에서 상품포인트 차감
   public int buyItem(String now_id, int iNum) {
      int result = 0;

      String sql = "UPDATE GIS_MYPAGE \r\n"
      		+ "SET MEMBER_POINT = MEMBER_POINT - (\r\n"
      		+ "    SELECT ITEM_POINT \r\n"
      		+ "    FROM GIS_SHOPPING \r\n"
      		+ "    WHERE ITEM_NUM = ?)\r\n"
      		+ "WHERE MEMBER_ID = ?";

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = DriverManager.getConnection(url, uid, upw);
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, iNum);
         pstmt.setString(2, now_id);
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return result;
   }

}