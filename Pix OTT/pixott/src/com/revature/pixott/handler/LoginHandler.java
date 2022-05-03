package com.revature.pixott.handler;

import com.revature.pixott.app.App;
import com.revature.pixott.dao.UserDao;
import com.revature.pixott.model.UserVO;

public class LoginHandler {
	public static int userid;
	
	public static void displayLogin() {
		System.out.println("\n ----------\n|Login Menu|\n ----------\n");
		System.out.print("Enter Mobile Number : ");
		String mobileNumber = App.scanner.next();
		System.out.print("Enter Password : ");
		String password = App.scanner.next();
		if(mobileNumber.equals("99999")&&password.equals("password")) {
			AdminHandler.displayAdmin();
		}
		/*String nameofuser = UserDao.welcomeUser(mobileNumber);
        boolean checkLogin = UserDao.login(mobileNumber, password);
        if(mobileNumber==99999&&password.equals("password")) {
			AdminHandler.displayAdmin();
		}
        else if (checkLogin) {
        	userid = UserDao.userId(mobileNumber);		
			System.out.println("\n****************\nLogin Successful\n****************\n");
			System.out.println("\nWelcome back "+nameofuser+"\n");
			MovieHandler.displayMovies();
			
		} else {
			System.out.println("\n***********************************\nIncorrect Mobile NUmber or Password\n***********************************\n");
			MainMenuHandler.displayMenu();
		}*/
		UserVO user = new UserVO();
		UserDao userdao =new UserDao();
		user=userdao.login(mobileNumber, password);
		System.out.println("\nWelcome Back "+user.getName());
		userid = user.getId();
		MovieHandler.displayMovies();

	}

}
