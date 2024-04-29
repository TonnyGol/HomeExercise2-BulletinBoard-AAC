import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    Scanner scanner = new Scanner(System.in);

    private static final String[] CITY_REGION_BANK = {"South", "Center", "North"};
    private static final String[] STREET_NAMES_BANK = {"Ort", "Namir","Arlozorov", "Rova", "Benyehuda","Agamim", "Admon","Dolphin", "Amos", "Agmon"};
    private static final String[] CITY_NAME_BANK = {"Ashkelon", "Tel Aviv", "Haifa", "Ashdod", "Jerusalem",
            "Netanya", "Eilat", "Bat Yam", "Petah Tikva", "Rehovot"};

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


    //Complexity - O(1)
    public RealEstate(){
        this.users = new User[0];
        this.properties = new Property[0];
        this.cities = new City[10];
        int regionBankIndex = 0;
        for (int i = 0; i < CITY_NAME_BANK.length; i++){
            String region = CITY_REGION_BANK[regionBankIndex];
            this.cities[i] = new City(CITY_NAME_BANK[i], region, STREET_NAMES_BANK[i]);
            regionBankIndex++;
            if (regionBankIndex == CITY_REGION_BANK.length){
                regionBankIndex = 0;
            }
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
                case LOGIN -> {
                    User loggedInUser = login();
                    showUserMenu(loggedInUser);
                }
                case END_PROGRAM -> System.out.println("Ending program...");
                default -> System.out.println("Wrong input, try again");
            }
        }while (!userInput.equals(END_PROGRAM));
    }
    //Complexity - O(n^2)
    private void createUser(){
        String username, password, phone, brokerStatus;
        boolean isBroker = false;

        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
        }while (isUsernameTaken(username));

        do {
            System.out.print("Enter a strong password: ");
            password = scanner.nextLine();
        }while (!isStrongPassword(password));

        do {
            System.out.print("Enter phone number: ");
            phone = scanner.nextLine();
        }while (!isValidPhoneNumber(phone));

        System.out.println("Are you broker? YES/NO (Wrong input by Default is No.)");
        brokerStatus = scanner.nextLine();
        if (brokerStatus.toUpperCase().startsWith("Y")){
            isBroker = true;
            System.out.println("Your account is Real Estate Broker.");
        }else {
            System.out.println("Your account is Regular user.");
        }

        User user = new User(username, password, phone, isBroker);
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
        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        System.out.print("Enter your password: ");
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
        if (isTaken){System.out.println("Username already exists, Try another username");}
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
                        break;
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
    private void showUserMenu(User user){
        if (user == null){
            System.out.println("Wrong username/password.");
        } else {
            String userInput;
            do{
                System.out.println("Welcome "+user.getUserName()+" to the Bulletin Board!");
                System.out.println("1- Publish Property");
                System.out.println("2- Remove property advertisement");
                System.out.println("3- Show all properties");
                System.out.println("4- Show all user properties");
                System.out.println("5- Search for property");
                System.out.println("6- Logout");
                userInput = scanner.nextLine();
                switch (userInput){
                    case POST_PROPERTY -> {
                        boolean isPropertySaved = postNewProperty(user);
                        validatePost(isPropertySaved);
                    }
                    case REMOVE_PROPERTY_AD -> removeProperty(user);
                    case SHOW_ALL_PROPERTIES -> printAllProperties();
                    case ALL_USER_PROPERTIES -> printUserProperties(user);
                    case SEARCH_FOR_PROPERTY -> {
                        Property[] filteredProperties = search();
                        printFilteredProperties(filteredProperties);
                    }
                    case LOGOUT -> System.out.println("Logged out successfully, See you later ;) "+user.getUserName());
                    default -> System.out.println("Wrong input, try again");
                }
            }while (!userInput.equals(LOGOUT));
        }

    }
    //Complexity - O(n)
    private boolean postNewProperty(User user){
        boolean canPost = true;
        final int postLimit = user.getPostLimit();
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
                validCity.printStreets();
                System.out.println("Enter street name out of list:");
                String streetChoice = scanner.nextLine();
                if (validCity.checkIfStreetExists(streetChoice)){
                    System.out.println("What is the property type?");
                    System.out.println("1. Regular Apartment ");
                    System.out.println("2. Penthouse");
                    System.out.println("3. Private House");
                    String propertyType = scanner.nextLine();
                    int floor = 0;
                    if (propertyType.equals("3")){
                        createProperty(floor, user, validCity, streetChoice, HOUSE);
                    } else if (propertyType.equals("1") || propertyType.equals("2")) {
                        propertyType = propertyType.equals("1") ? APARTMENT : PENTHOUSE;
                        System.out.println("Enter floor number:");
                        floor = Integer.parseInt(scanner.nextLine());
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
        System.out.println("Is the property for sale or rent? R- Rent / S- Sale (Wrong input by Default is for Sale.)");
        rentOrSale = scanner.nextLine();
        isForRent = rentOrSale.toUpperCase().startsWith("R") ? "for rent" : "for sale";
        System.out.println("Enter the price for the property:");
        price = Integer.parseInt(scanner.nextLine());
        Property property = new Property(city, street, rooms, price, type, isForRent, propertyNumber, floor, publisher);
        this.properties = addPropertyToArray(this.properties, property);
    }
    //Complexity - O(1)
    private void validatePost(boolean isPropertySaved){
        if (isPropertySaved){
            System.out.println("Property post saved successfully");
        }else {
            System.out.println("Failed to save property");
        }
    }
    //Complexity - O(n)
    private Property[] addPropertyToArray(Property[] arr, Property toAdd){
        Property[] temp = new Property[arr.length+1];
        for (int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
        }
        temp[arr.length] = toAdd;
        return temp;
    }
    //Complexity - O(n)
    private void removeProperty(User user){
        Property[] userProperties = new Property[0];
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getPublisher().getUserName().equals(user.getUserName())){
                userProperties = addPropertyToArray(userProperties, this.properties[i]);
            }
        }
        if (userProperties.length == 0){
            System.out.println("No properties to remove");
        }else {
            int userChoice;
            printUserProperties(user);
            System.out.println("Choose from list to remove:");
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userProperties.length >= userChoice && userChoice >= 1) {
                this.properties = removePropertyFromArray(this.properties, userProperties[userChoice-1]);
                System.out.println("Property Removed successfully");
            } else {
                System.out.println("Choice doesn't exist");
            }
        }
    }
    //Complexity - O(n)
    private Property[] removePropertyFromArray(Property[] arr, Property toRemove){
        Property[] temp;
        if (arr.length != 0) {
            temp = new Property[arr.length - 1];
        } else {
            temp = new Property[0];
        }
        int tempIndex = 0;
        if (arr.length != 1) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != toRemove) {
                    temp[tempIndex] = arr[i];
                    tempIndex++;
                }
            }
        }
        return temp;
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
        int userProperties = 0;
        System.out.println("List of properties by user - "+user.getUserName());
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getPublisher().getUserName().equals(user.getUserName())){
                System.out.println(userProperties+1+".");
                System.out.println(this.properties[i]);
                System.out.println("------------------------");
                userProperties++;
            }
        }
    }
    //Complexity - O(n)
    private void printAllCities(){
        System.out.println("List of all cities:");
        for (int i = 0; i < this.cities.length; i++){
            System.out.println(i+1 +". " + this.cities[i]);
        }
    }
    //Complexity - O(n)
    private City checkIfCityExists(String city){
        City cityExists = null;
        for (int i = 0; i < this.cities.length; i++){
            if (city.equalsIgnoreCase(this.cities[i].getName())){
                cityExists = this.cities[i];
                break;
            }
        }
        return cityExists;
    }
    //Complexity - O(n^2)
    private Property[] search (){
        Property[] search;
        Property[] temp = new Property[this.properties.length];
        for (int i = 0; i < this.properties.length; i++){
            temp[i] = this.properties[i];
        }
        String propertyType, rentOrSale, isForRent, propertyIndicator;
        int rooms, minPrice, maxPrice;
        System.out.println("Is the property for sale or rent? (R- Rent / S- Sale / -999 -> ignore), Wrong input filters for sale.");
        rentOrSale = scanner.nextLine();
        if (!rentOrSale.equals(IGNORE_SEARCH)) {
            isForRent = rentOrSale.toUpperCase().startsWith("R") ? "for rent" : "for sale";
        }else {
            isForRent = IGNORE_SEARCH;
        }
        System.out.println("What is the property type? (1. Regular Apartment / 2. Penthouse / 3. Private House / -999 -> ignore)");
        propertyIndicator = scanner.nextLine();
        switch (propertyIndicator){
            case "1" -> propertyType = APARTMENT;
            case "2" -> propertyType = PENTHOUSE;
            case "3" -> propertyType = HOUSE;
            default -> propertyType = IGNORE_SEARCH;
        }
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
    //Complexity - O(n^2)
    private Property[] filterProperties(Property[] toFilter, String type, String isForRent, int rooms, int minPrice, int maxPrice){
        for (int i = 0; i < toFilter.length; i++){
            if ((!type.equals(IGNORE_SEARCH) && !type.equals(toFilter[i].getType()))
                    || (!isForRent.equals(IGNORE_SEARCH) && !isForRent.equals(toFilter[i].getIsForRent()))
                    || (rooms != Integer.parseInt(IGNORE_SEARCH) && rooms != toFilter[i].getRooms())
                    || (minPrice != Integer.parseInt(IGNORE_SEARCH) && toFilter[i].getPrice() < minPrice)
                    || (maxPrice != Integer.parseInt(IGNORE_SEARCH) && toFilter[i].getPrice() > maxPrice)){
                toFilter = removePropertyFromArray(toFilter, toFilter[i]);
                i--;
            }
        }
        return toFilter;
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
