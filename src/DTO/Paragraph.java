package DTO;
//CREATE TABLE PARAGRAPH(NUM INT PRIMARY KEY AUTO_INCREMENT, 
public class Paragraph {
	private String num; // �۹�ȣ
	private String category; // ī�װ�, ������ enum Ÿ�Կ��� ������
	private String id; // �ۼ��� ���̵�
	private String name; // �ۼ��� �̸�
	private String date; // �ۼ� ��¥
	private String title; // �� ����
	private String content; // �� ����
	private String file; // �� ���� ������
	
	private String tag; // �±� 
	private String area; // ��� ���� 
	 
	private String dayView; // ���� ��ȸ��
	private String weekView; // �� ��ȸ��
	private String monthView; // �� ��ȸ�� 
	private String view; // ��ü ��ȸ��
	
	private String like; // ���ƿ� ���� 
	private String visible; // ���̴� ����
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDayView() {
		return dayView;
	}
	public void setDayView(String dayView) {
		this.dayView = dayView;
	}
	public String getWeekView() {
		return weekView;
	}
	public void setWeekView(String weekView) {
		this.weekView = weekView;
	}
	public String getMonthView() {
		return monthView;
	}
	public void setMonthView(String monthView) {
		this.monthView = monthView;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
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

	public enum Category{CAMPREVIEW, ITEMREVIEW, INFORMATION, QUESTION, ARTICLE, CONTACT, NOTICE, PICTURE}
	public enum Visible{HIDDEN, VISIBLE}
}
