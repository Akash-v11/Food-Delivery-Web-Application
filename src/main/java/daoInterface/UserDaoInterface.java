package daoInterface; 

import java.util.List;

import model.User;

 
public interface UserDaoInterface 
{	
//	 Insert, FetchAll, FetchSpecfic, Update, Delete	
	
	
	void insert(User newUser);
	
	 List<User> fetchAll();

 	 User fetch_ID(int id);
 	 User getUserEmail(String email);
	
	 int update(User u);

	 int delete(int id);

	void updateLastLogin(int userId);
	
 
	  

}
