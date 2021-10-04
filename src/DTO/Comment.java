package DTO;
//CREATE TABLE COMMENT(NUM INT PRIMARY KEY AUTO_INCREMENT, PARAGRAPHNUM TEXT, PARENTNUM TEXT, ID TEXT, NAME TEXT, DATE TEXT, CONTENT TEXT, LIKE TEXT, VISIBLE TEXT)
public class Comment {
	private String num; // ��� ���� ��ȣ
	private String paragraphNum; // �ش� ����� ���Ե� ��
	private String parentNum=""; // ���� ���� �� : �ʱⰪ "" 
	private String id; // �ۼ��� ���̵�
	private String name; // �ۼ��� �̸�
	private String date; // �ۼ���¥ 
	private String content; // �ۼ� ����
	private String like; // ���� �� 
	private String visible; // ���̴� ����
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getParagraphNum() {
		return paragraphNum;
	}
	public void setParagraphNum(String paragraphNum) {
		this.paragraphNum = paragraphNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visiable) {
		this.visible = visiable;
	}
	
	public String getParentNum() {
		return parentNum;
	}
	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public enum Visible{HIDDEN, VISIBLE}
}
