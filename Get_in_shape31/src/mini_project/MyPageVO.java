package mini_project;

import java.sql.Date;

public class MyPageVO {
	
	private String member_id;
	private int member_point;
	private int goalRate;
	private Date startDate;
	private Date endDate;
	
	public MyPageVO() {
		
	}

	public MyPageVO(String member_id, int member_point, int goalRate, Date startDate, Date endDate) {
		super();
		this.member_id = member_id;
		this.member_point = member_point;
		this.goalRate = goalRate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getMember_point() {
		return member_point;
	}

	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}

	public int getGoalRate() {
		return goalRate;
	}

	public void setGoalRate(int goalRate) {
		this.goalRate = goalRate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
