package XangGames;

public class Boss extends Monster {

    private String secondAttack = secondAttack();

    public Boss() {
        super(5,0);
    }

    private String secondAttack(){
        String att = DungeonDivers.ATTACKS[(int)Math.random()*DungeonDivers.ATTACKS.length];
        while(att.equals(super.getAttack())){
            att = DungeonDivers.ATTACKS[(int)Math.random()*DungeonDivers.ATTACKS.length];
        }
        return att;
    }

//    public static int bossFight(String att){
//
//    }
//    @Override
//    public String getImgDirectory(){return "50";}

    @Override
    public String toString(){
        return "Boss: "+super.toString() + " and " + secondAttack;
    }
}
