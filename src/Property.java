public class Property {
    private City city;
    private String street;
    private int rooms;
    private String price;
    private String type;
    private String isForRent;
    private int number;
    private int floor;
    private User publisher;

    public Property(City city, String street, int rooms, String price, String type, String isForRent,
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
    public String toString(){
        String propertyInfo = this.city.getName() +" - "+this.street+"."+"\nType: "+this.type+" - "+this.isForRent+
                ": "+this.rooms+" rooms";
        if (this.floor != 0){
            propertyInfo += ", floor "+this.floor;
        }
        propertyInfo += ".\nPrice: "+this.price+
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
}
