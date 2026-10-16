package castle;
public class Help extends CommandsResolver{
    Game game;
    Help(Game game){
        this.game = game;
    }
    protected void Resolver(String[] words){
        if ( words[0].equals("help") ) {
        		game.printHelp();
        }
    }
}
