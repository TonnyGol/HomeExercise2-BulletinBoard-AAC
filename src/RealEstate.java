import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    Scanner scanner = new Scanner(System.in);
    private static final int CREATE_ACCOUNT = 1;
    private static final int LOGIN = 2;
    private static final int END_PROGRAM = 3;

    private static final int POST_PROPERTY= 1;
    private static final int REMOVE_PROPERTY_AD = 2;
    private static final int SHOW_ALL_PROPERTIES = 3;
    private static final int ALL_USER_PROPERTIES = 4;
    private static final int SEARCH_FOR_PROPERTY = 5;
    private static final int LOGOUT = 6;


    public RealEstate(){
        this.users = new User[0];
        //this.properties = new Property[0];
        //this.cities = new City[10];
    }
    //Complexity - O(n)
    private User login(){
        String username, password;
        User user = null;
        System.out.println("Enter your username");
        username = scanner.nextLine();
        System.out.println("Enter your password");
        password = scanner.nextLine();
        for (int i=0; i<this.users.length;i++){
            if(this.users[i].getUserName().equals(username)){
                if (this.users[i].getPassword().equals(password)){
                    user = this.users[i];
                    break;
                }
            }
        }
        return user;
    }
    //Complexity - O(n^2)
    void createUser(){
        String username, password, phone, mediatorIndicate;
        boolean isMediator = false;
        do {
            System.out.println("Enter username");
            username = scanner.nextLine();
        }while (isUsernameTaken(username));

        do {
            System.out.println("Enter a strong password");
            password = scanner.nextLine();
        }while (!isStrongPassword(password));

        do {
            System.out.println("Enter phone number");
            phone = scanner.nextLine();
        }while (!isValidPhoneNumber(phone));

        System.out.println("Are you mediator? Y/N (Wrong input equals No.)");
        mediatorIndicate = scanner.nextLine();
        if (mediatorIndicate.equals("Y") || mediatorIndicate.equals("y")){
            isMediator = true;
        }
        User user = new User(username, password, phone, isMediator);
        addUser(user);
    }


    //Complexity - O(n)
    private void addUser(User user){
        User[] tempUsers = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++){
            tempUsers[i] = this.users[i];
        }
        tempUsers[tempUsers.length - 1] = user;
        this.users = tempUsers;
    }
    //Complexity - O(n)
    private boolean isUsernameTaken(String username){
        boolean isTaken = false;
        for (int i = 0; i < this.users.length; i++){
            if (this.users[i].getUserName().equals(username)){
                isTaken = true;
                break;
            }
        }
        if (isTaken){System.out.println("Username already exists, Try another one");}
        return isTaken;
    }
    //Complexity - O(1)
    private boolean isValidPhoneNumber(String phone){
        boolean isValid = false;
        if (phone.length() == 10){
            if (phone.startsWith("05")){
                for (int i = 2; i < phone.length(); i++){
                    if (!containsDigit(String.valueOf(phone.charAt(i)))){
                        isValid = false;
                        break;
                    }else{
                        isValid = true;
                    }
                }
            }
        }
        if (!isValid){System.out.println("Not a valid phone number, Try again");}
        return isValid;
    }
    //Complexity - O(1)
    private boolean isStrongPassword(String password){
        boolean isStrong = false;
        if (password.length() >= 5){
            if (containsDigit(password)){
                if (password.contains("$") || password.contains("%") || password.contains("_")){
                    isStrong = true;
                }
            }
        }
        if(!isStrong){System.out.println("Not strong password, Try again");}
        return isStrong;
    }
    //Complexity - O(1)
    private boolean containsDigit(String password){
        boolean result = false;
        for (int i = 0; i <= 9; i++){
            if (password.contains(String.valueOf(i))){
                result = true;
                break;
            }
        }
        return result;
    }
    //Complexity - O(1)
    public void showMainMenu(){
        int userInput;
        do{
            System.out.println("Welcome to the main menu!");
            System.out.println("1- Create User");
            System.out.println("2- Login User");
            System.out.println("3- End Program");
            userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput){
                case CREATE_ACCOUNT -> createUser();
                case LOGIN -> showUserMenu(login());
                case END_PROGRAM -> System.out.println("Ending program...");
                default -> System.out.println("Wrong input, try again");
            }
        }while (userInput != END_PROGRAM);
    }

    boolean postNewProperty(User user){
        boolean canPost = true;
        final int postLimit = user.getIsMediator() ? 5 : 2;
        int postCount = 0;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getPublisher() == user){postCount++;}
            if (postCount >= postLimit){
                canPost = false;
                System.out.println("Post limit reached.");
                break;
            }
        }
        if (canPost){
            printAllCities();
            System.out.println("Enter city name:");
            String cityChoice = scanner.nextLine();
            City validCity = checkIfCityExists(cityChoice);
            if (validCity != null){
                printAllCityStreets(validCity);
                System.out.println("Enter street name:");
                String streetChoice = scanner.nextLine();
                if (streetChoice.equals(checkIfStreetExists(validCity,streetChoice))){
                    System.out.println("1. Apartment ");
                    System.out.println("2. Penthouse");
                    System.out.println("3. House");
                    String propertyType = scanner.nextLine();
                    if (propertyType.equals("1") || propertyType.equals("2") || propertyType.equals("3")){

                    } else {
                        System.out.println("Wrong input.");
                        canPost = false;
                    }
                } else {
                    System.out.println("Street doesn't exist.");
                    canPost = false;
                }
            } else {
                System.out.println("City doesn't exist.");
                canPost = false;
            }
        }
        return canPost;
    }

    //Complexity - O(n)
    public void printAllCities(){
        System.out.println("List of all cities: ");
        for (int i = 0; i < this.cities.length; i++){
            System.out.println(i +". " + this.cities[i]);
        }
    }
    //Complexity - O(n)
    public void printAllCityStreets(City city){
        System.out.println("List of all streets: ");
        String[] streets = city.getStreets();
        for (int i = 0; i < streets.length; i++){
            System.out.println(i + ". " + streets[i]);
        }
    }

    //Complexity - O(n)
    public String checkIfStreetExists(City city, String street){
        String streetExists = "";
        String[] streets = city.getStreets();
        for (int i = 0; i < streets.length; i++){
            if (streets[i].equals(street)){
                streetExists = streets[i];
                break;
            }
        }
        return streetExists;
    }

    //Complexity - O(n)
    public City checkIfCityExists(String city){
        City cityExists = null;
        for (int i = 0; i < this.cities.length; i++){
            if (city.equals(this.cities[i].getName())){
                cityExists = this.cities[i];
                break;
            }
        }
        return cityExists;
    }

    public void showUserMenu(User user){
        if (user == null){
            System.out.println("Wrong username/password.");
        } else {
            int userInput;
            do{
                System.out.println("Welcome to the Bulletin Board!");
                System.out.println("1- Publish Property");
                System.out.println("2- Remove property advertisement");
                System.out.println("3- Show all properties");
                System.out.println("4- Show all user properties");
                System.out.println("5- Search for property");
                System.out.println("6- Logout");
                userInput = scanner.nextInt();
                scanner.nextLine();
                switch (userInput){
                    case POST_PROPERTY -> System.out.println();
                    case REMOVE_PROPERTY_AD -> System.out.println();
                    case SHOW_ALL_PROPERTIES -> System.out.println();
                    case ALL_USER_PROPERTIES -> System.out.println();
                    case SEARCH_FOR_PROPERTY -> System.out.println();
                    case LOGOUT -> System.out.println();
                    default -> System.out.println("Wrong input, try again");
                }
            }while (userInput != END_PROGRAM);
        }

    }
}
