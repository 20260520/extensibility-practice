package castle;
class Room {
    private String description;
    private Room[] Exits;
    {
    Room northExit = null;
    Room southExit = null;
    Room eastExit = null;
    Room westExit = null;
    Room[] tempExits = {northExit,  eastExit, southExit, westExit};
    Exits = tempExits;
    }
    private String[] directions = {"north", "east", "south", "west"};
    Room(String description) 
    {
        this.description = description;
    }
    void setExits(Room[] input) 
    {
        for (byte n = 0; n < (byte)Exits.length; n++) {
            Exits[n] = input[n];
        }
    }
    String getExitDesc(){
        StringBuffer sb =new StringBuffer();
        byte i = 0;
        for (Room exit : Exits) {
            if(exit != null){
                sb.append(directions[i]+" ");
            }
            i++;
        }
        return sb.toString();
    }
    Room getExit(String selectedDirection){
        Room nextroom = null;
        byte n = 0;
        for (String direction : directions) {
            if(selectedDirection.equals(direction)){
                nextroom = Exits[n];
            }
            n++;
        }
        return nextroom;
    }
    @Override
    public String toString()
    {
        return description;
    }
}