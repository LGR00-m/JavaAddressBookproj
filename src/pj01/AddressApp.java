package pj01;

public class AddressApp {

	public static void main(String[] args) {
		CollectionLogic logic = new CollectionLogic();
		
		while(true) {
			logic.printMenu();
			int menu = logic.getMenu();
			logic.seperateMenu(menu);
		}
		
	}

}
