package castle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Game {
    private Room currentRoom;
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
        toAddList = new Room[] {study, outside, bedroom, null, null, null};
        toAddLists.add(toAddList);
        toAddList = new Room[] {bedroom, null, null, null, study, null};
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
    private void printHelp() 
    {
        System.out.print("Do you lose? you can try three commands: go bye help");
        System.out.println("such as: \tgo east");
    }
    private void goRoom(String direction) 
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
            System.out.println("现在你在" + currentRoom);
            System.out.print("出口有: ");
            System.out.println(currentRoom.getExistingExits());
            System.out.println();
        }
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Game game = new Game();
		game.printWelcome();
        while ( true ) {
        	String line = in.nextLine();
        	String[] words = line.split(" ");
        	if ( words[0].equals("help") ) {
        		game.printHelp();
        	} else if (words[0].equals("go") ) {
        		if ( words.length < 2 || words[1].isEmpty() ) {
        	    	System.out.println("go 后面需要跟方向,例如:go east");
        		}else{
        			game.goRoom(words[1]);
        		}
        	} else if ( words[0].equals("bye") ) {
        		break;
        	}
        }
        System.out.println("see you again!");
        in.close();
	}

}