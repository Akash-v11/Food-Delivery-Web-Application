package daoInterface; 
 
import java.util.List;

import model.Menu;

public interface MenuDaoInterface 
{
    void insert(Menu menu);
    List<Menu> fetchAll();
    List<Menu> fetchSpecificRestaurantid(int menuId);
    int update(Menu menu);
    int delete(int menuId);
  
     Menu FecthMenuId(int id);
    
}
