package DTO;
//CREATE TABLE COMMENT(NUM INT PRIMARY KEY AUTO_INCREMENT, PARAGRAPHNUM TEXT, PARENTNUM TEXT, ID TEXT, NAME TEXT, DATE TEXT, CONTENT TEXT, LIKE TEXT, VISIBLE TEXT)
public class Comment {
	private String num; // 댓글 고유 번호
	private String paragraphNum; // 해당 댓글이 포함된 글
	private String parentNum=""; // 대댓글 위한 것 : 초기값 "" 
	private String id; // 작성자 아이디
	private String name; // 작성자 이름
	private String date; // 작성날짜 
	private String content; // 작성 내용
	private String like; // 공감 수 
	private String visible; // 보이는 여부
	
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
