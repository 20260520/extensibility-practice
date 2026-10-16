package castle;
public class Go extends CommandsResolver{
    Game game;
    Go(Game game){
        this.game = game;
    }
    protected void Resolver(String[] words){
        if (words[0].equals("go") ) {
            if ( words.length < 2 || words[1].isEmpty() ) {
                System.out.println("go needs a direction, e.g.: go east");
            }else{
                game.goRoom(words[1]);
            }
        }
    }
}
