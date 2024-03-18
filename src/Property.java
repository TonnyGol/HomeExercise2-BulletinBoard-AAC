public class Property {
    private City city;
    private String street;
    private int rooms;
    private int price;
    private String type;
    private String isForRent;
    private int number;
    private int floor;
    private User publisher;

    public Property(City city, String street, int rooms, int price, String type, String isForRent,
                    int propertyNumber, int floor, User publisher){
        this.city = city;
        this.street = street;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.number = propertyNumber;
        this.floor = floor;
        this.publisher = publisher;
    }
    //Complexity - O(n)
    private String addCommasToPrice(String price) {
        String result = "";
        char digit;
        for (int i = 1; i <= price.length(); i++) {
            digit = price.charAt(price.length() - i);
            if (i % 3 == 1 && i > 1) {
                result = "," + result;
            }
            result = digit + result;
        }
        return result;
    }
    public String toString(){
        String propertyInfo = this.city.getName() +" - "+this.street+"."+"\nType: "+this.type+" - "+this.isForRent+
                ": "+this.rooms+" rooms";
        if (this.floor != 0){
            propertyInfo += ", floor "+this.floor;
        }
        propertyInfo += ".\nPrice: "+addCommasToPrice(String.valueOf(this.price))+
                "$."+"\nContact info: "+this.publisher.getUserName()+" "+this.publisher.getPhoneNumber();
        if (this.publisher.getIsMediator()){
            propertyInfo += " (real estate broker).";
        }else {
            propertyInfo += " (private user).";
        }
        return propertyInfo;
    }
    //Complexist - O(1)
    public User getPublisher() {return this.publisher;}
    //Complexity - O(1)
    public int getPropertyNumber(){return this.number;}
    public String getIsForRent(){return this.isForRent;}
    public String getType(){return this.type;}
    public int getRooms(){return this.rooms;}
    public int getPrice(){return this.price;}
}
