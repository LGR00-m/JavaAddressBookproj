
package pj01;

public class Address {

	// 필드
	String name;
	int age;
	String addr;
	String tel;
	
	
	//생성자
	public Address(String name, int age, String addr, String tel) {
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return String.format("이름:%s, 나이:%s, 주소:%s, 연락처:%s",name,age,addr,tel);
	}

	// 게터
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getAddr() {
		return addr;
	}

	public String getTel() {
		return tel;
	}


	
	
	
	
}
