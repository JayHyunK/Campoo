package DTO;
//CREATE TABLE MEMBER(NUM INT PRIMARY KEY AUTO_INCREMENT, 
public class Member {
	
	private String id; //���̵�
	private String pw; //��й�ȣ
	private String name; //�̸�
	private String birth; //����
	private String gender; //����
	private String email; //�̸���
	
	private String grade = GRADE.MEMBER.toString(); // �⺻ ���
	private String registerDate; // ������
	
	private String Statement; // ���� ����
	
	private String stopDay; // ���� ������
	private String stopTerm; // ���� �Ⱓ
	private String likeThing; // �����ϴ� ���
	
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

