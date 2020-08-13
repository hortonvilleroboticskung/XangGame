package XangGames;

public class Barb extends Hero {

    public Barb() {
        super("Barb");
    }

    @Override
    public void activateAbility(){
        usedAbility();
        for(Hero h : DungeonDivers.players){
            if(h.getMonster()!=null && whirlWindKilled(h.getMonster())){
                h.getMonster().isDead();
            }
        }
    }

    public boolean whirlWindKilled(Monster m){
        if( (m.getAttack().equals(DungeonDivers.ATTACKS[0]) && getAttack().equals(DungeonDivers.ATTACKS[1]))
                || (m.getAttack().equals(DungeonDivers.ATTACKS[1]) && getAttack().equals(DungeonDivers.ATTACKS[2]))
                || (m.getAttack().equals(DungeonDivers.ATTACKS[2]) && getAttack().equals(DungeonDivers.ATTACKS[0]))){
            return true;
        }
        return false;
    }

}
