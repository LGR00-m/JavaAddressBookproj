package pj01;

import java.util.regex.Pattern;

public class InitialConsonant {
	
	// 초성 추출용 메소드
	public static char getInitialConsonant(String key) {
		
		if(!Pattern.matches("^[가-힣]{2,}$", key.trim())) {
			System.out.println("올바른 한글 이름을 입력하세요");
			return 0; // 이름이 한글과 일치하지 않으면 0 반환
		}
		else { //일치하면 해당되는 초성 반환
			char lastName=key.trim().charAt(0);
			int index=(lastName-'가')/28/21;
			char[] initialConsonant= {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
			return initialConsonant[index];
		}
	}
}
