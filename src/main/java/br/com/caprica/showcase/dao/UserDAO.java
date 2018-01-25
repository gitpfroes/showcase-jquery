package br.com.caprica.showcase.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.caprica.showcase.pojo.User;

public class UserDAO {
	public List<User> listUsers(){
		List<User> users = new ArrayList<User>();
		
		//Mock list of users
		users.add(new User(1, "Laura Roslin", "President of the Twelve Colonies"));
		users.add(new User(2, "William Adama ", "Admiral"));
		users.add(new User(3, "Leland Adama", "Viper Pilot"));
		users.add(new User(4, "Kara Thrace", "Viper Pilot"));
		users.add(new User(5, "Gaius Baltar", "Computer Scientist "));
		users.add(new User(6, "Number Six", "Cylon"));
		users.add(new User(7, "Saul Tigh", "Executive Officer"));
		users.add(new User(8, "Sharon Valerii", "Lieutenant Junior Grade"));
		users.add(new User(9, "Karl C. Agathon", "Electronic Countermeasures Officer"));
		users.add(new User(10, "Galen Tyrol", "Senior Chief Petty Officer"));
		users.add(new User(11, "Felix Gaeta", "Lieutenant Junior Grade"));
		users.add(new User(12, "Anastasia Dualla", "Lieutenant Junior Grade"));
		users.add(new User(13, "Samuel T. Anders", "Pyramid Player"));
		users.add(new User(14, "Tom Zarek", "Prisioner"));
		users.add(new User(15, "Helena Cain", "Rear Admiral"));
		users.add(new User(16, "Sherman Cottle", "Chief Medical Officer"));
		
		
		return users;
	}
}
