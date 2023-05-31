package mini_project;

public class UserVO {
   
   // 멤버변수 정의 
   private String userName;
   private String userId;
   private String userRank;
   private String userBirth;
   private String userPw;
   private String userProgress;
   
   public UserVO() { // 생성자 두개 생성
      
   }
   
   // 생성자 정의
   public UserVO(String userName, String userId, String userRank, String userBirth, String userPw, String userProgress) {
      super();
      this.userName = userName;
      this.userId = userId;
      this.userRank = userRank;
      this.userBirth = userBirth;
      this.userPw = userPw;
      this.userProgress = userProgress;
   }

   
   // getter, setter 
   public String getUserBirth() {
      return userBirth;
   }

   public void setUserBirth(String userBirth) {
      this.userBirth = userBirth;
   }

   public String getUserPw() {
      return userPw;
   }

   public void setUserPw(String userPw) {
      this.userPw = userPw;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserRank() {
      return userRank;
   }

   public void setUserRank(String userRank) {
      this.userRank = userRank;
   }

   public String getUserProgress() {
      return userProgress;
   }

   public void setUserProgress(String userProgress) {
      this.userProgress = userProgress;
   }
   

}
