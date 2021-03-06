package com.horstmann.violet.application.menu.util.UMLTransfrom;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class XMLUtils {
	/**
	 * 绫诲浘鏃剁�?瀹氭秷鎭笌鑺傜偣浣嶇疆
	 */
	public static List<Point> getEdgePontionList(Edge edge,List<Node> list){
		List<Point> staticList =new ArrayList<Point>();
		Point starRight =XMLUtils.getNode(edge.getStarNodeid(), list).getRightLocation();
		Point starLeft=XMLUtils.getNode(edge.getStarNodeid(), list).getLocation();
		Point endLeft=XMLUtils.getNode(edge.getEndNodeid(), list).getLocation();
		Point endRight =XMLUtils.getNode(edge.getEndNodeid(), list).getRightLocation();

		if(starRight.getX()<endLeft.getX()
				&&endLeft.getY()<0.5*(starLeft.getY()+starRight.getY())
				&&0.5*(starLeft.getY()+starRight.getY())<endRight.getY()){
			staticList.removeAll(staticList);
			staticList.add(new Point((int)starRight.getX(),(int)(0.5*(starLeft.getY()+starRight.getY()))));
			staticList.add(new Point((int)endLeft.getX(),(int)(0.5*(endLeft.getY()+endRight.getY()))));
			return staticList;

		}else if(starLeft.getX()<endRight.getX()
				&&endLeft.getY()<0.5*(starLeft.getY()+starRight.getY())
				&&0.5*(starLeft.getY()+starRight.getY())<endRight.getY()){
			staticList.removeAll(staticList);
			staticList.add(new Point((int)starLeft.getX(), (int)(0.5*(starLeft.getY()+starRight.getY()))));
			staticList.add(new Point((int)endRight.getX(),(int)(0.5*(endLeft.getY()+endRight.getY()))));
			return staticList;
		
		}else if(starRight.getY()<endLeft.getY()){
			staticList.removeAll(staticList);
			staticList.add(new Point((int)(0.5*(starRight.getX()+starLeft.getX())), (int)starRight.getY()));
			staticList.add(new Point((int)(0.5*(endLeft.getX()+endRight.getX())),(int)endLeft.getY()));
			return staticList;
	
		}else{
			staticList.removeAll(staticList);
			staticList.add(new Point((int)(0.5*(starRight.getX()+starLeft.getX())), (int)starLeft.getY()));
			staticList.add(new Point((int)(0.5*(endLeft.getX()+endRight.getX())),(int)endRight.getY()));
			return staticList;
		}		
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	public static String getMapId(Map map,String id){
		Iterator it=map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry=(Map.Entry) it.next();
			if(entry.getKey().equals(id)){
				return entry.getValue().toString();
			}
			
		}
		
		return null;
	}
	
	/**
	 * @param id
	 * @param list
	 * @return
	 */
	public static Node getNode(String id,List<Node> list){
		for(Node cn:list){
			if(cn.getId().trim().equals(id)){
				return cn;
			}
		}
		return null;
	} 
	/**
	 * @param name
	 * @return
	 */
	public  static  String getTypeStr(String id,List<Node> list){
		for(Node cn:list){
			if(cn.getId().trim().equals(id)){
				if(cn.getType().trim().equals("uml:Class")){
					return "ClassNode";
				}else{
					return "InterfaceNode";
					}
				}
			}
		return null;
	}
	
	
	/**
	 */
	private static  String  getEdgeIdByNodeId(String edgeId ,Map<String,String> edgeMap) {
		String valueId="";
		 Iterator iter=edgeMap.keySet().iterator();
		 while(iter.hasNext()){
			String key=iter.next().toString();
			 if(edgeId.equals(key)){
				 valueId=edgeMap.get(key);
			 } 
		 }
		 String value="EAID_"+ dealEAID(dealEAID(UUID.randomUUID().toString().toUpperCase()));	
		 edgeMap.put(edgeId, value);
		 valueId=value;
		 return valueId;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public static Document load(String name){
		Document d=null;
		try {
			SAXReader saxReader=new SAXReader();
			d=saxReader.read(new File(name));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * @return
	 */
	public static String getIndex(String str){
		Pattern p =Pattern.compile("(\\d+)");
		Matcher m =p.matcher(str);
			if(m.find()){
				return (m.group(1));
			}
		return null;
	}
	
	/**
	 * @return
	 */
	public static String dealEAID(String str){
		String[] s=str.split("-");
		StringBuffer sb =new StringBuffer();
		sb.append(s[0]);
		for (int i = 1; i < s.length; i++) {
			
			if(i==2&&i!=s.length-1){
			
					sb.append("_"+s[i].toLowerCase());	
			}else{
				sb.append("_"+s[i]);
			}
			
		}
		return sb.toString();
	}
	
	
	public static void AutoSave(String sourcePath,String aimPath,String fileName){
		 try {
				InputStream in =new FileInputStream(sourcePath);
				 File f =new File(aimPath);
				if (!f.exists()) {
					f.mkdirs();
				}
				FileOutputStream out =new FileOutputStream(aimPath+"/"+fileName);
				byte[] buffer = new byte[1024]; 
			    int b;
			    while ( ( b = in.read(buffer)) != -1) { 
	              out.write(buffer, 0, b); 
	            } 
	            in.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
