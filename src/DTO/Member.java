package DTO;
//CREATE TABLE MEMBER(NUM INT PRIMARY KEY AUTO_INCREMENT, 
public class Member {
	
	private String id; //아이디
	private String pw; //비밀번호
	private String name; //이름
	private String birth; //생일
	private String gender; //성별
	private String email; //이메일
	
	private String grade = GRADE.MEMBER.toString(); // 기본 등급
	private String registerDate; // 가입일
	
	private String Statement; // 정지 여부
	
	private String stopDay; // 정지 시작일
	private String stopTerm; // 정지 기간
	private String likeThing; // 좋아하는 장소
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getStopday() {
		return stopDay;
	}
	public void setStopday(String stopDay) {
		this.stopDay = stopDay;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getStopTerm() {
		return stopTerm;
	}
	public void setStopTerm(String stopTerm) {
		this.stopTerm = stopTerm;
	}

	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLikeThing() {
		return likeThing;
	}
	public void setLikeThing(String likeThing) {
		this.likeThing = likeThing;
	}

	public String getStatement() {
		return Statement;
	}
	public void setStatement(String statement) {
		Statement = statement;
	}
	public enum GRADE {MEMBER, ADMIN}
	public enum STATEMENT {NONE, STOP, BREAK}
}

