package DataSet;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import Database.database;

import java.sql.*;

public class CampingInfo{
	// 해당 클래스는 데이터 베이스 세팅을 위한 기초 실행 클래스
	// mySql 환경으로 세팅 가능 
	// 경로는 Web-DataSet 의 파일을 기준으로 함
	// 상대 경로가 안되면 파일 > properties 에서 절대 경로 복사 후에 붙여넣기 
	// 데이터 수가 7천줄 내외로 실행 완료에 시간이 소요될 수 있음 
	
	public CampingInfo() {
		
		//CampingInfoSetting("campsite"); 
		// campsite: 캠핑장 정보 (제작 당시 기준 : 2238개)
		
		//CampingInfoSetting("C:\\Users\\frees\\OneDrive\\바탕 화면\\프로그래밍\\Java\\.JSP\\Campoo\\WebContent\\Web-Pages\\Web-DataSet\\forest.xml", "forest");
		// forest: 전국 휴양림 정보 (제작 당시 기준 : 195개)
		
		//CampingInfoSetting("C:\\Users\\frees\\OneDrive\\바탕 화면\\프로그래밍\\Java\\.JSP\\Campoo\\WebContent\\Web-Pages\\Web-DataSet\\museum.xml", "museum");
		// museum: 전국 박물관 미술관 정보 (제작 당시 기준 : 2805개)
		
		//CampingInfoSetting("C:\\Users\\frees\\OneDrive\\바탕 화면\\프로그래밍\\Java\\.JSP\\Campoo\\WebContent\\Web-Pages\\Web-DataSet\\tour.xml", "tour");
		// tour: 전국 표준 관광지 정보 (제작 당시 기준 : 187개)
		
		//CampingInfoSetting("C:\\Users\\frees\\OneDrive\\바탕 화면\\프로그래밍\\Java\\.JSP\\Campoo\\WebContent\\Web-Pages\\Web-DataSet\\market.xml", "market");
		// market: 전국 표준 전통시장 정보 (제작 당시 기준 : 318개)
		
		//CampingInfoSetting("C:\\Users\\frees\\OneDrive\\바탕 화면\\프로그래밍\\Java\\.JSP\\Campoo\\WebContent\\Web-Pages\\Web-DataSet\\village.xml", "village");
		// village: 전국 농어촌 체험 휴양 마을 정보 (제작 당시 기준 : 1378개)
		
	}
	
	public void CampingInfoSetting(String Path, String dbname){	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		
		File reader = null;
		ArrayList<String> arrNames = new ArrayList<String>();
		 
		database db = database.getInstance();
		
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;
			
			reader = new File(Path);
			DocumentBuilderFactory dombf = DocumentBuilderFactory.newInstance();
			dombf.setIgnoringElementContentWhitespace(true);
			DocumentBuilder domb = dombf.newDocumentBuilder();
			
			Document dom = domb.parse(reader);
			dom.getDocumentElement().normalize();
			NodeList ndlist = dom.getElementsByTagName("record");
			System.out.println(reader);
			System.out.println(ndlist);
			
			Node namesNode = ndlist.item(0);
			Element namesElement = (Element) namesNode;
			String arr = "";
			
			sql = "CREATE TABLE IF NOT EXISTS "
				+ dbname	
				+ " (NUM INT PRIMARY KEY AUTO_INCREMENT, ";
			
			for(int i = 0; i < namesElement.getChildNodes().getLength(); i++) {
				
				if(namesNode.getNodeType()==Node.ELEMENT_NODE) {
					arrNames.add(((NodeList) namesElement).item(i).getNodeName());
					
					if(i==namesElement.getChildNodes().getLength()-1) {
						arr+=arrNames.get(i);
						sql+=arrNames.get(i)+" TEXT";
					}
					else {
						arr+=arrNames.get(i)+", ";
						sql+=arrNames.get(i)+" TEXT, ";
					}
				}
				System.out.println(arrNames.get(i));
			}
			sql+=");";
			System.out.println(sql);
			stmt.execute(sql);
			
			for(int i = 0; i < ndlist.getLength(); i++) {
				Node node = ndlist.item(i);
				
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					System.out.println("Contents"+node.getTextContent());
					Element element = (Element) node;
					
					sql = "INSERT INTO "
							+ dbname
							+ " ("
							+ arr 
							+ ") VALUES (";
					
					System.out.println(sql);
					for(int j = 0; j <arrNames.size(); j++) {
						String str = element.getElementsByTagName(arrNames.get(j)).item(0).getTextContent();
						
						if(str.equals("")) {
							str="null";
						}
						
						if(j==arrNames.size()-1) {
							sql+="'"+str+"'";;
						}
						else {
							sql+="'"+str+"', ";;
						}
					}
					sql += ");";
					System.out.println(sql);
					stmt.execute(sql);	
				}
			}					
		}
		catch(Exception e) {
			System.out.println("File Reader~DB Error** "+e);
		}
		finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}
			catch(Exception e) {
				System.out.println("close Error** "+e);
			}
		}
	}
}
//alter table campsite add fulltext index index_address_road (소재지도로명주소) visible;