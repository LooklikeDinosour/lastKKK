package mini_project;

import java.sql.Date;

public class TrainingDiaryVO {

	private Date toDate;
	private String userId;
	private String workoutName;
	private String bodyPart;
	private double workoutTime;
	private String toFeedback;
	private String difficulty;
	private int diaryCnt;
	
	// 생성자
	public TrainingDiaryVO() {}
	
	public TrainingDiaryVO(Date toDate, String userId, String workoutName, String bodyPart,
			double workoutTime, String toFeedback, String difficulty) {
		super();
		this.toDate = toDate;
		this.userId = userId;
		this.workoutName = workoutName;
		this.bodyPart = bodyPart;
		this.workoutTime = workoutTime;
		this.toFeedback = toFeedback;
		this.difficulty = difficulty;
	}

	// getter, setter 
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public double getWorkoutTime() {
		return workoutTime;
	}

	public void setWorkoutTime(double workoutTime) {
		this.workoutTime = workoutTime;
	}

	public String getToFeedback() {
		return toFeedback;
	}

	public void setToFeedback(String toFeedback) {
		this.toFeedback = toFeedback;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getDiaryCnt() {
		return diaryCnt;
	}

	public void setDiaryCnt(int diaryCnt) {
		this.diaryCnt = diaryCnt;
	}
	
	
}
