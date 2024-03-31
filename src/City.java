public class City {
    private String name;
    private String region;
    private String[] streets;

    //Complexity - O(1)
    public City(String name, String region){
        this.name = name;
        this.region = region;
        this.streets = new String[0];
    }
    //Complexity - O(1)
    public City(String name, String region, String street){
        this.name = name;
        this.region = region;
        this.streets = new String[1];
        this.streets[0] = street;
    }

    //Complexity - O(1)
    public String toString(){
        return "Name: " + this.name + ", Region: " + this.region;
    }

    //Complexity - O(1)
    public String getName() {return this.name;}
    //Complexity - O(1)
    public String[] getStreets(){return this.streets;}
    //Complexity - O(n)
    public boolean checkIfStreetExists(String street){
        boolean isExist = false;
        String[] streets = this.getStreets();
        for (int i = 0; i < streets.length; i++){
            if (streets[i].equals(street)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    //Complexity - O(n)
    public void printStreets(){
        System.out.println("List of all streets:");
        for (int i = 0; i < this.streets.length; i++){
            System.out.println(i + 1 + ". " + this.streets[i]);
        }
    }
}

