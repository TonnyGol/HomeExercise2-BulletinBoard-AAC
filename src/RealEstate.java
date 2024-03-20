import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    Scanner scanner = new Scanner(System.in);

    private static final String[] CITY_REGION_BANK = {"South", "Center", "North"};
    private static final String[] ASHKELON_STREET_NAMES = {"Ort", "Eli Cohen", "Hanasi"};
    private static final String[] CITY_NAME_BANK = {"Ashkelon", "Tel Aviv", "Haifa", "Ashdod", "Jerusalem",
            "Bat Yam", "Eilat", "Netanya", "Petah Tikva", "Rehovot"};

    private static final String[] SPECIAL_CHARACTER_BANK = {"$", "_", "%"};
    private static final int[] BANK_OF_DIGITS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static final String HOUSE = "Private House";
    private static final String APARTMENT = "Regular Apartment";
    private static final String PENTHOUSE = "Penthouse";
    private static final String VALID_PHONE_PREFIX = "05";
    private static final String IGNORE_SEARCH = "-999";
    private static final int VALID_PASSWORD_LEN = 5;
    private static final int VALID_PHONE_NUMBER_LEN = 10;
    //Main Menu Options
    private static final String CREATE_ACCOUNT = "1";
    private static final String LOGIN = "2";
    private static final String END_PROGRAM = "3";
    //User Menu Options
    private static final String POST_PROPERTY= "1";
    private static final String REMOVE_PROPERTY_AD = "2";
    private static final String SHOW_ALL_PROPERTIES = "3";
    private static final String ALL_USER_PROPERTIES = "4";
    private static final String SEARCH_FOR_PROPERTY = "5";
    private static final String LOGOUT = "6";


    //Complexity - O(n)
    public RealEstate(){
        Random random = new Random();
        this.users = new User[0];
        this.properties = new Property[0];
        this.cities = new City[10];
        this.cities[0] = new City(CITY_NAME_BANK[0], CITY_REGION_BANK[0], ASHKELON_STREET_NAMES);
        this.cities[1] = new City(CITY_NAME_BANK[1], CITY_REGION_BANK[1]);
        this.cities[2] = new City(CITY_NAME_BANK[2], CITY_REGION_BANK[2]);
        for (int i = 3; i < CITY_NAME_BANK.length; i++){
            String region = CITY_REGION_BANK[random.nextInt(3)];
            this.cities[i] = new City(CITY_NAME_BANK[i], region);
        }
    }

    //Complexity - O(n^2)
    public void showMainMenu(){
        String userInput;
        do{
            System.out.println("Welcome to the main menu!");
            System.out.println("1- Create User");
            System.out.println("2- Login User");
            System.out.println("3- End Program");
            userInput = scanner.nextLine();
            switch (userInput){
                case CREATE_ACCOUNT -> createUser();
                case LOGIN -> showUserMenu(login());
                case END_PROGRAM -> System.out.println("Ending program...");
                default -> System.out.println("Wrong input, try again");
            }
        }while (!userInput.equals(END_PROGRAM));
    }
    //Complexity - O(n^2)
    private void createUser(){
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

        System.out.println("Are you mediator? YES/NO (Default is No.)");
        mediatorIndicate = scanner.nextLine();
        if (mediatorIndicate.toUpperCase().equals("YES")){
            isMediator = true;
            System.out.println("Your account is mediator.");
        }else {
            System.out.println("Your account is regular user.");
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
    private User login(){
        String username, password;
        User user = null;
        System.out.println("Enter your username");
        username = scanner.nextLine();
        System.out.println("Enter your password");
        password = scanner.nextLine();
        for (int i = 0; i < this.users.length; i++){
            if(this.users[i].checkCredentials(username, password)){
                user = this.users[i];
                System.out.println("Logged in as: "+username);
                break;
            }
        }
        return user;
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
        if (phone.length() == VALID_PHONE_NUMBER_LEN){
            if (phone.startsWith(VALID_PHONE_PREFIX)){
                for (int i = VALID_PHONE_PREFIX.length(); i < phone.length(); i++){
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
        if (password.length() >= VALID_PASSWORD_LEN){
            if (containsDigit(password)){
                for (int i = 0; i < SPECIAL_CHARACTER_BANK.length; i++){
                    if (password.contains(SPECIAL_CHARACTER_BANK[i])){
                        isStrong = true;
                    }
                }
            }
        }
        if(!isStrong){System.out.println("Not strong password, Try again");}
        return isStrong;
    }
    //Complexity - O(1)
    private boolean containsDigit(String password){
        boolean result = false;
        for (int i = 0; i <= BANK_OF_DIGITS.length; i++){
            if (password.contains(String.valueOf(BANK_OF_DIGITS[i]))){
                result = true;
                break;
            }
        }
        return result;
    }

    //Complexity - O(n^2)
    public void showUserMenu(User user){
        if (user == null){
            System.out.println("Wrong username/password.");
        } else {
            String userInput;
            do{
                System.out.println("Welcome to the Bulletin Board!");
                System.out.println("1- Publish Property");
                System.out.println("2- Remove property advertisement");
                System.out.println("3- Show all properties");
                System.out.println("4- Show all user properties");
                System.out.println("5- Search for property");
                System.out.println("6- Logout");
                userInput = scanner.nextLine();
                switch (userInput){
                    case POST_PROPERTY -> validatePost(postNewProperty(user));
                    case REMOVE_PROPERTY_AD -> removeProperty(user);
                    case SHOW_ALL_PROPERTIES -> printAllProperties();
                    case ALL_USER_PROPERTIES -> printUserProperties(user);
                    case SEARCH_FOR_PROPERTY -> printFilteredProperties(search());
                    case LOGOUT -> System.out.println("Logged out successfully");
                    default -> System.out.println("Wrong input, try again");
                }
            }while (!userInput.equals(LOGOUT));
        }

    }
    //Complexity - O(n)
    private boolean postNewProperty(User user){
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
            System.out.println("Enter city name out of list:");
            String cityChoice = scanner.nextLine();
            City validCity = checkIfCityExists(cityChoice);
            if (validCity != null){
                printAllCityStreets(validCity);
                System.out.println("Enter street name out of list:");
                String streetChoice = scanner.nextLine();
                if (streetChoice.equals(checkIfStreetExists(validCity,streetChoice))){
                    System.out.println("What is the property type?");
                    System.out.println("1. Regular Apartment ");
                    System.out.println("2. Penthouse");
                    System.out.println("3. Private House");
                    String propertyType = scanner.nextLine();
                    int floor = 0;
                    if (propertyType.equals("3")){
                        createProperty(floor, user, validCity, streetChoice, HOUSE);
                    } else if (propertyType.equals("1") || propertyType.equals("2")) {
                        System.out.println("Enter floor number:");
                        floor = Integer.parseInt(scanner.nextLine());
                        propertyType = propertyType.equals("1") ? APARTMENT : PENTHOUSE;
                        createProperty(floor, user, validCity, streetChoice, propertyType);
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
    private void createProperty(int floor, User publisher, City city, String street, String type){
        int propertyNumber, rooms, price;
        String rentOrSale, isForRent;
        System.out.println("Enter number of rooms:");
        rooms = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter property number:");
        propertyNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Is the property for sale or rent? R- Rent / S- Sale (Default is for Sale.)");
        rentOrSale = scanner.nextLine();
        isForRent = rentOrSale.toUpperCase().equals("R") ? "for rent" : "for sale";
        System.out.println("Enter the price for the property:");
        price = Integer.parseInt(scanner.nextLine());
        Property property = new Property(city, street, rooms, price, type, isForRent, propertyNumber, floor, publisher);
        addProperty(property);
    }
    //Complexity - O(n)
    private void addProperty(Property property){
        Property[] temp = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++){
            temp[i] = this.properties[i];
        }
        temp[temp.length - 1] = property;
        this.properties = temp;
    }
    //Complexity - O(1)
    private void validatePost(boolean isSaved){
        if (isSaved){
            System.out.println("Property post saved successfully");
        }else {
            System.out.println("Failed to save property");
        }
    }
    //Complexity - O(n)
    private void removeProperty(User user){
        int publishedProperties = 0;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getPublisher().getUserName().equals(user.getUserName())){
                publishedProperties++;
            }
        }
        if (publishedProperties == 0){
            System.out.println("No properties to remove");
        }else {
            String propertyNum;
            printUserProperties(user);
            System.out.println("Enter property number to remove:");
            propertyNum = scanner.nextLine();
            int tempIndex = 0;
            if (checkPropertyByNumber(propertyNum)) {
                Property[] temp = new Property[this.properties.length - 1];
                if (this.properties.length == 1) {
                    this.properties = temp;
                } else {
                    for (int i = 0; i < this.properties.length; i++) {
                        if (this.properties[i].getPropertyNumber() != Integer.parseInt(propertyNum)) {
                            temp[tempIndex] = this.properties[i];
                            tempIndex++;
                        }
                    }
                    this.properties = temp;
                }
            } else {
                System.out.println("Property number doesn't exist");
            }
        }
    }
    //Complexity - O(n)
    private boolean checkPropertyByNumber(String number){
        boolean isExists = false;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getPropertyNumber() == Integer.parseInt(number)){
                isExists = true;
                break;
            }
        }
        return isExists;
    }
    //Complexity - O(n)
    private void printAllProperties(){
        System.out.println("List of all properties");
        for (int i = 0; i < this.properties.length; i++){
            System.out.println(i+1+".");
            System.out.println(this.properties[i]);
            System.out.println("------------------------");
        }
    }
    //Complexity - O(n)
    private void printUserProperties(User user){
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getPublisher().getUserName().equals(user.getUserName())){
                System.out.println(i+1+".");
                System.out.println(this.properties[i]);
                System.out.println("------------------------");
            }
        }
    }
    //Complexity - O(n)
    private void printAllCities(){
        System.out.println("List of all cities: ");
        for (int i = 0; i < this.cities.length; i++){
            System.out.println(i+1 +". " + this.cities[i]);
        }
    }
    //Complexity - O(n)
    private void printAllCityStreets(City city){
        System.out.println("List of all streets: ");
        String[] streets = city.getStreets();
        for (int i = 0; i < streets.length; i++){
            System.out.println(i + 1 + ". " + streets[i]);
        }
    }
    //Complexity - O(n)
    private String checkIfStreetExists(City city, String street){
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
    private City checkIfCityExists(String city){
        City cityExists = null;
        for (int i = 0; i < this.cities.length; i++){
            if (city.equals(this.cities[i].getName())){
                cityExists = this.cities[i];
                break;
            }
        }
        return cityExists;
    }
    //Complexity - O(n)
    private Property[] search (){
        Property[] search;
        Property[] temp = new Property[this.properties.length];
        for (int i = 0; i < this.properties.length; i++){
            temp[i] = this.properties[i];
        }
        String propertyType, rentOrSale, isForRent;
        int rooms, minPrice, maxPrice;
        System.out.println("Is the property for sale or rent? (R- Rent / S- Sale / -999 -> ignore)");
        rentOrSale = scanner.nextLine();
        if (!rentOrSale.equals(IGNORE_SEARCH)) {
            isForRent = rentOrSale.toUpperCase().equals("R") ? "for rent" : "for sale";
        }else {
            isForRent = IGNORE_SEARCH;
        }
        System.out.println("What is the property type? (Private House / Regular Apartment / Penthouse / -999 -> ignore)");
        propertyType = scanner.nextLine();
        System.out.println("How many rooms for the property?");
        rooms = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter price range (min-max)");
        System.out.println("Enter min:");
        minPrice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter max:");
        maxPrice = scanner.nextInt();
        scanner.nextLine();
        search = filterProperties(temp, propertyType, isForRent, rooms, minPrice, maxPrice);
        return search;
    }
    //Complexity - O(n)
    private Property[] filterProperties(Property[] toFilter, String type, String isForRent, int rooms, int minPrice, int maxPrice){
        int invalidProperties = 0;
        for (int i = 0; i < toFilter.length; i++){
            if (!type.equals(IGNORE_SEARCH) && !type.equals(toFilter[i].getType())){
                toFilter[i] = null;
                invalidProperties++;
                continue;
            }
            if (!isForRent.equals(IGNORE_SEARCH) && !isForRent.equals(toFilter[i].getIsForRent())){
                toFilter[i] = null;
                invalidProperties++;
                continue;
            }
            if (rooms != Integer.parseInt(IGNORE_SEARCH) && rooms != toFilter[i].getRooms()){
                toFilter[i] = null;
                invalidProperties++;
                continue;
            }
            if (minPrice != Integer.parseInt(IGNORE_SEARCH) && toFilter[i].getPrice() < minPrice){
                toFilter[i] = null;
                invalidProperties++;
                continue;
            }
            if (maxPrice != Integer.parseInt(IGNORE_SEARCH) && toFilter[i].getPrice() > maxPrice){
                toFilter[i] = null;
                invalidProperties++;
            }
        }
        Property[] filtered = new Property[this.properties.length - invalidProperties];
        int index = 0;
        for (int i = 0; i < toFilter.length; i++){
            if (toFilter[i] != null){
                filtered[index] = toFilter[i];
                index++;
            }
        }
        return filtered;
    }
    //Complexity - O(n)
    private void printFilteredProperties(Property[] filtered){
        System.out.println("Search results:");
        for (int i = 0; i < filtered.length; i++){
            System.out.println(i+1+".");
            System.out.println(filtered[i]);
            System.out.println("------------------------");
        }
    }
}
