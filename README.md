Online Food Delivery Application

# Online Food Delivery Application

## Description
The Online Food Delivery Application is a Java-based web application designed to manage food orders, restaurants, menus, and user details. It offers functionalities for both users and administrators to manage orders, menus, and restaurant information.

## Features
- User registration and login.
- Browse menu items and add them to the cart.
- Place orders and view order history.
- Admin functionalities to manage restaurants, menus, and user details.
- Integration with MySQL database for data persistence.

## Project Structure
The project follows the MVC (Model-View-Controller) architecture. Below is the folder structure:

### Model
Contains 7 classes representing entities such as:
- `CartItem.java`
- `Menu.java`
- `Orders.java`
- `OrdersHistory.java`
- `OrderItem.java`
- `Restaurant.java`
- `User.java`

### View
Includes the user interface:
- `cart.jsp`
- `checkout.jsp`
- `editUser.jsp`
- `emailExists.jsp`
- `failure.jsp`
- `home.jsp`
- `index.jsp`
- `invaliduser.jsp`
- `loginpage.jsp`
- `menu.jsp`
- `orderConfirmation.jsp`
- `orders.jsp`
- `ordershistory.jsp`
- `ordersitem.jsp`
- `pwdincorrect.jsp`
- `registerpage.jsp`
- `styles.css`
- `success.jsp`
- `userdetails.jsp`
- `welcome.jsp`

### Controller
Handles the application logic and HTTP requests. The project includes 16 servlet files:
- `CartServlet.java`
- `FetchAllUserDetails.java`
- `FetchOne.java`
- `GetRestaurant.java`
- `Login.java`
- `LogoutServlet.java`
- `OrderConfirmationServlet.java`
- `OrderServlet.java`
- `Register.java`
- `Security.java`
- `ShowMenu.java`
- `UpdateUser.java`
- `Validation.java`
- `ViewOrders.java`
- `ViewOrdersItem.java`

### DAO Implementation and Interface
Provides data access logic:
- **Interfaces (6 Files):**
  - `MenuDaoInterface.java`
  - `OrdersDaoInterface.java`
  - `OrdersHistoryDaoInterface.java`
  - `OrdersItemDaoInterface.java`
  - `RestaurantDaoInterface.java`
  - `UserDaoInterface.java`
- **Implementations (7 Files):**
  - `CartDaoImplementation.java`
  - `MenuDaoImplementation.java`
  - `OrdersDaoImplementation.java`
  - `OrdersHistoryDaoImplementation.java`
  - `OrdersItemDaoImplementation.java`
  - `RestaurantDaoImplementation.java`
  - `UserDaoImplementation.java`

### Database Utilities
- `MyConnection.java`: Handles database connections.

### Configuration Files
- `web.xml`: Deployment descriptor.
- `pom.xml`: Maven configuration file.

## Tools and Technologies Used
- **Backend:** Java, Servlets, JSP
- **Frontend:** HTML, CSS
- **Database:** MySQL
- **Build Tool:** Maven
- **Server:** Apache Tomcat

## Installation
1. Clone the repository:
   ```bash
   https://github.com/Akash-v11/Online-Food-Delivery-Application/tree/main 
   ```
2. Import the project into your IDE (e.g., Eclipse or IntelliJ).
3. Configure the MySQL database:
   - Create a database and import the SQL schema provided.
   - Update database connection details in `MyConnection.java`.
4. Build the project using Maven:
   ```bash
   mvn clean install
   ```
5. Deploy the project on Apache Tomcat.

## Usage
1. Open the application in a web browser.
2. Register as a new user or log in.
3. Browse the menu and place orders.
4. Admin users can log in to manage restaurants, menus, and users.

## Screenshots
![1](https://github.com/user-attachments/assets/e10cb02c-3cd3-4f31-abb0-e0c98fe99b93)
![2](https://github.com/user-attachments/assets/cfc6fea0-0548-4685-8599-007203c0f33b)
![3](https://github.com/user-attachments/assets/7568f9cc-f4fa-47e5-9662-367991f2b583)

 
## License
This project is owned by Akash.v and licensed under the MIT License. See `LICENSE` for details.
 
 
