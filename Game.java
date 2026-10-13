package castle;
import java.util.Scanner;
public class Game {
    private Room currentRoom;
    public Game() 
    {
        createRooms();
    }
    private void createRooms()
    {
        Room[] Exits;
        Room outside, lobby, pub, study, bedroom;
      
        //	make room
        outside = new Room("outside the castle.");
        lobby = new Room("in the lobby");
        pub = new Room("in the pub.");
        study = new Room("in the study.");
        bedroom = new Room("in the bedroom.");
        
        //	loading the exit of the room
        Exits = new Room[] {null, lobby, study, pub};
        outside.setExits(Exits);
        Exits = new Room[] {null, null, null, outside};
        lobby.setExits(Exits);
        Exits = new Room[] {null, outside, null, null};
        pub.setExits(Exits);
        Exits = new Room[] {outside, bedroom, null, null};
        study.setExits(Exits);
        Exits = new Room[] {null, null, null, study};
        bedroom.setExits(Exits);
        currentRoom = outside;  //	the game begin outside the castle
    }
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
        Room nextRoom = currentRoom.getExit(direction);
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
            System.out.println(currentRoom.getExitDesc());
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