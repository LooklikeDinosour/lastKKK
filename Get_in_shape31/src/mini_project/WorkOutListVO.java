package mini_project;

public class WorkOutListVO {

	private String workOut_name; 
	private String body_part;
	private String workOut_explain;
	private int workOut_reps;
	private String workOut_link;
	
	public WorkOutListVO() {
		
	}
	
	public WorkOutListVO(String workOut_name, String body_part, String workOut_explain, int workOut_reps, String workOut_link) {
		super();
		this.workOut_name = workOut_name;
		this.body_part = body_part;
		this.workOut_explain = workOut_explain;
		this.workOut_reps = workOut_reps;
		this.workOut_link =workOut_link;
	}

	public String getWorkOut_name() {
		return workOut_name;
	}

	public void setWorkOut_name(String workOut_name) {
		this.workOut_name = workOut_name;
	}

	public String getBody_part() {
		return body_part;
	}

	public void setBody_part(String body_part) {
		this.body_part = body_part;
	}

	public String getWorkOut_explain() {
		return workOut_explain;
	}

	public void setWorkOut_explain(String workOut_explain) {
		this.workOut_explain = workOut_explain;
	}

	public int getWorkOut_reps() {
		return workOut_reps;
	}

	public void setWorkOut_reps(int workOut_reps) {
		this.workOut_reps = workOut_reps;
	}

	public String getWorkOut_link() {
		return workOut_link;
	}

	public void setWorkOut_link(String workOut_link) {
		this.workOut_link = workOut_link;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "오늘의 운동: "+workOut_name +", "+ workOut_reps+"회";
	}
	
	
}
