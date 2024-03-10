import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    Scanner scanner = new Scanner(System.in);
    private static final int CREATE_ACCOUNT = 1;
    private static final int LOGIN = 2;
    private static final int END_PROGRAM = 3;

    public RealEstate(){
        this.users = new User[0];
        //this.properties = new Property[0];
        //this.cities = new City[0];
    }
    //Complexity - O(n)+O(k)+O(m)
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
                case LOGIN -> System.out.println("Working on it...");
                case END_PROGRAM -> System.out.println("Ending program...");
                default -> System.out.println("Wrong input, try again");
            }
        }while (userInput != END_PROGRAM);
    }
}
