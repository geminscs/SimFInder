package indi.tammy.simfinder;

public class Question {
	
	//Question表内字段
	private int id;
	private String content;
	private String analysis;
	private String answer;
	private int type;
	private boolean isFull;
	private int hardness;
	private long submit_time;
	private int subject_id;
	
	//联表查询得到的额外字段
	private String know_name;
	private String type_name;
	private String subject_name;
	private int wrong_type;
	private String wrong_type_name;
	
	//查询得到的总条目数
	private int total;
	private long similarity;
	
	
	
	
	public long getSimilarity() {
		return similarity;
	}
	public void setSimilarity(long similarity) {
		this.similarity = similarity;
	}
	public int getWrong_type() {
		return wrong_type;
	}
	public void setWrong_type(int wrong_type) {
		this.wrong_type = wrong_type;
	}
	public String getWrong_type_name() {
		return wrong_type_name;
	}
	public void setWrong_type_name(String wrong_type_name) {
		this.wrong_type_name = wrong_type_name;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getKnow_name() {
		return know_name;
	}
	public void setKnow_name(String know_name) {
		this.know_name = know_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	public int getHardness() {
		return hardness;
	}
	public void setHardness(int hardness) {
		this.hardness = hardness;
	}
	public long getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(long submit_time) {
		this.submit_time = submit_time;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Question(int id, long similarity) {
		super();
		this.id = id;
		this.similarity = similarity;
	}
	public Question(int id, String content, String analysis, String answer,
			int type, boolean isFull, int hardness, long submit_time,
			int subject_id, String know_name, String type_name,
			String subject_name, int wrong_type, String wrong_type_name,
			int total, long similarity) {
		super();
		this.id = id;
		this.content = content;
		this.analysis = analysis;
		this.answer = answer;
		this.type = type;
		this.isFull = isFull;
		this.hardness = hardness;
		this.submit_time = submit_time;
		this.subject_id = subject_id;
		this.know_name = know_name;
		this.type_name = type_name;
		this.subject_name = subject_name;
		this.wrong_type = wrong_type;
		this.wrong_type_name = wrong_type_name;
		this.total = total;
		this.similarity = similarity;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", analysis="
				+ analysis + ", answer=" + answer + ", type=" + type
				+ ", isFull=" + isFull + ", hardness=" + hardness
				+ ", submit_time=" + submit_time + ", subject_id=" + subject_id
				+ ", know_name=" + know_name + ", type_name=" + type_name
				+ ", subject_name=" + subject_name + ", wrong_type="
				+ wrong_type + ", wrong_type_name=" + wrong_type_name
				+ ", total=" + total + ", similarity=" + similarity + "]";
	}
	
	
}
