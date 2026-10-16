package castle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Game {
    private Room currentRoom;
    Map<String, CommandsResolver> commandresolvers = new HashMap<String, CommandsResolver>();
    public Game() 
    {
        createRooms();
    }
    //---------------------------------------------------------------------------------------------------------------
    private void createRooms()
    {
        //	make room
        Room outside, lobby, pub, study, bedroom;
        outside = new Room("outside the castle.");
        lobby = new Room("in the lobby");
        pub = new Room("in the pub.");
        study = new Room("in the study.");
        bedroom = new Room("in the bedroom.");
        //	loading the exit of the room
        List<Room[]> toAddLists = new ArrayList<Room[]>();
        Room[] toAddList;
        String[] directions = {null, "north", "east", "south", "west", "up", "down"}; 
        toAddList = new Room[] {outside, null, lobby, study, pub, null, null};
        toAddLists.add(toAddList);
        toAddList = new Room[] {lobby, null, null, null, outside, pub, null};
        toAddLists.add(toAddList);
        toAddList = new Room[] {pub, null, outside, null, null, null, lobby};
        toAddLists.add(toAddList);
        toAddList = new Room[] {study, outside, bedroom, null, null, null, null};
        toAddLists.add(toAddList);
        toAddList = new Room[] {bedroom, null, null, null, study, null, null};
        toAddLists.add(toAddList);
        AUTOloader(toAddLists, directions);
        currentRoom = outside;  //	the game begin outside the castle
    }
    private void AUTOloader(List<Room[]> toAddLists, String[] directions){
        for(Room[] toAddList : toAddLists){
            for(byte n = 1; n < toAddList.length; n++){
                toAddList[0].set1Exit(directions[n], toAddList[n]);
            }
        }
    }
    //---------------------------------------------------------------------------------------------------------------
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome!");
        System.out.println("It is a boring game.");
        System.out.println("If you need help,please input: help ");
        System.out.println();
        showPrompt();
    }

    // User command
    void printHelp() 
    {
        System.out.print("Do you lose? you can try 2 commands: go help");
        System.out.println("such as: \tgo east");
        System.out.println("input 'quit' or 'exit' to END this program");
    }
    void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.get1Exit(direction);
        if (nextRoom == null) {
            System.out.println("wrong direction");
        }else {
            currentRoom = nextRoom;
            showPrompt();
        }
    }    
    void showPrompt(){
        System.out.println("You are now " + currentRoom);
        System.out.print("Exits: ");
        System.out.println(currentRoom.getExistingExits());
        System.out.println();
    }
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Game game = new Game();
		game.commandresolvers.put("help", new Help(game));
		game.commandresolvers.put("go", new Go(game));
		game.printWelcome();
        while(true){
        	String line = in.nextLine();
        	String[] words = line.split(" ");
        	CommandsResolver cr = game.commandresolvers.get(words[0]);
        	if ( cr != null ) {
        		cr.Resolver(words);
        	}
        	if ( words[0].equals("exit") || words[0].equals("quit")) {
        		break;
        	}
        }
        System.out.println("see you again!");
        in.close();
	}
}