package XangGames;

public class MiniBoss extends Monster {

    private int dmg = 4;

    public MiniBoss(int imgIndex){
        super(4,imgIndex);
    }

    public static void useAbility(MiniBoss m){
        //TODO: theres a bug where it increments by 2 for one of the monsters
        for(Hero ch : DungeonDivers.players){
            if(!(ch.getMonster()==m)){
                ch.incrementMonAtt(1);
            }
        }
    }

    @Override
    public String toString(){
        return "Mini-Boss: "+super.toString();
    }

}
