package castle;
import java.util.HashMap;
class Room {
    private String description;
    HashMap<String, Room> Exits = new HashMap<>();
    Room(String description) 
    {
        this.description = description;
    }
    void set1Exit(String direction, Room roomOnthedir) 
    {
        if (roomOnthedir != null) {
            Exits.put(direction, roomOnthedir);
        }
    }
    String getExistingExits(){
        StringBuffer sb = new StringBuffer();
        for (String key : Exits.keySet()) {
            sb.append(key+" ");
        }
        return sb.toString();
    }
    Room get1Exit(String selectedDirection){
        return Exits.get(selectedDirection);
    }
    @Override
    public String toString()
    {
        return description;
    }
}