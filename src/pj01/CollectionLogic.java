package pj01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CollectionLogic {
	
	
	Map<Character,List<Address>> addressBook = new HashMap<>();
	List<Address> valueList = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
		
	
	// 메뉴 출력 메소드
	public void printMenu() {
		System.out.println("=============주소록 메뉴=============");
		System.out.println("1.입력 2.출력 3.수정 4.삭제 5.검색 9.종료");
		System.out.println("==================================");
		System.out.println("메뉴를 입력하세요?");
	}//printMenu
	
	// 번호 받기용 메소드
	public int getMenu() {
		Scanner sc = new Scanner(System.in);
		int menu=-1;
		
		try {
			menu=Integer.parseInt(sc.nextLine().trim());
		}
		catch(NumberFormatException e) {System.out.println("메뉴 번호만 입력하세요!"+e.getMessage());}
		
		return menu;
	}//getMenu
	
	// 번호 분기용 메소드
	public void seperateMenu(int menu) {
		switch(menu) {
		case 1 : //입력
			getData();		
			break;
		case 2 : //출력
			printData();
			break;
		case 3 : //수정
			updateData();
			break;
		case 4 : //삭제
			deleteData();
			break;
		case 5 : //검색
			searchData();
			break;
		case 9 : //종료
			System.out.println("프로그램 종료");
			System.exit(0);
		default : System.out.println("메뉴에 없는 번호 입니다");
			break;			
		}//switch

	}//seperateMenu

	

	

	// 입력용 메소드
	private void getData() {
		System.out.println("이름을 입력하세요?");
		String name = sc.nextLine().trim();
		char consonant = InitialConsonant.getInitialConsonant(name);
		if(consonant=='0') {
			return;
		}
			
		System.out.println("나이를 입력하세요?");
		try {
		int age = Integer.parseInt(sc.nextLine().trim());
			
		System.out.println("주소를 입력하세요?");
		String addr = sc.nextLine().trim();
		System.out.println("연락처를 입력하세요?");
		String tel = sc.nextLine().trim();
		
		if(!addressBook.containsKey(consonant)) { // 맵에 키 값 없는 경우 
			valueList = new Vector<>();
		}
		else { // 맵에 키 값 있는 경우
			valueList = addressBook.get(consonant);
		}
		
		valueList.add(new Address(name, age, addr, tel));
		addressBook.put(consonant, valueList);				
		System.out.println(name+"님의 정보가 입력되었습니다.");
		}
		catch(NumberFormatException e) {System.out.println("숫자만 입력하세요!"+e.getMessage()); return;}	
	} //getData
		
	// 출력용 메소드
	private void printData() {

		Set<Character> keys = addressBook.keySet();
		if(keys.isEmpty()) {
			System.out.println("주소록에 정보가 없습니다.");
			return;
		}
		for(Character key : keys) {
			System.out.println(String.format("[%c으로 시작하는 명단]", key));
			List<Address> values = addressBook.get(key);
			Collections.sort(values, (o1,o2)->o1.getName().compareTo(o2.getName()));
			for(Address value : values) {
				System.out.println(value);
			}		
		}
	} //printData
	
	// 수정,삭제,검색 메소드
	private Address findByName(String message) {
		System.out.println(message+"할 사람의 이름을 입력하세요?");
		String name = sc.nextLine().trim();
		
		Set<Character> kys= addressBook.keySet();
		for(Character ky : kys) {
			List<Address> vals= addressBook.get(ky);
			for(Address val : vals) {
				if(val.getName().equals(name)) {
					return val;
				}
			}
		}
		System.out.println(name+"님의 검색된 정보가 없습니다.");
		return null;
	}//findByName
	
	// 수정용 메소드
	private void updateData() {
		Address findPerson = findByName("수정");
		if(findPerson !=null) {			
			System.out.println(String.format("[현재 나이:%s] 수정을 원하시는 나이를 입력하세요?", findPerson.age));
			try {
			findPerson.age = Integer.parseInt(sc.nextLine().trim());
			}
			catch(NumberFormatException e) {System.out.println("숫자만 입력하세요!"+e.getMessage()); return;}
			System.out.println(String.format("[현재 주소:%s] 수정을 원하시는 주소를 입력하세요?", findPerson.addr));
			findPerson.addr = sc.nextLine().trim();
			System.out.println(String.format("[현재 연락처:%s] 수정을 원하시는 연락처를 입력하세요?", findPerson.tel));
			findPerson.tel = sc.nextLine().trim();
			System.out.println(String.format("%s님의 정보가 아래와 같이 수정되었습니다.\r\n%s",findPerson.name,findPerson));	
		}
	}//updateData
	
	// 삭제용 메소드
	private void deleteData() {
		Address findPerson = findByName("삭제");
		
		if(findPerson != null) {
			boolean isExist=false;
			Iterator<Address> iterator= valueList.iterator();
			while(iterator.hasNext()) {
				Address val= iterator.next();
				if(findPerson.equals(val)) {
					iterator.remove();
					isExist = true;
				}
			}
			if(isExist) {
				System.out.printf("%s가(이) 삭제되었습니다.%n",findPerson.name);
				return;
			}
			else {
				System.out.printf("%s가(이) 존재하지 않습니다.%n",findPerson.name);
				return;
			}
		}
	} //deleteData
	
	// 검색용 메소드
	private void searchData() {
		Address findPerson = findByName("검색");
		if(findPerson != null) {
			System.out.println(String.format("%s님의 검색 결과를 출력합니다.\r\n%s", findPerson.name,findPerson));
		}		
	}//searchData
	
}///////////////c
