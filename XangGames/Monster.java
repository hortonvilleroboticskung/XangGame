package XangGames;

public class Monster {

    private int gold = 1;
    private int dmg;
    private String attack;
    private String imgDirectory;
    private boolean dead = false;

    public Monster(int dmg, int imageIndex){
        this.dmg = dmg;
        attack = DungeonDivers.ATTACKS[(int)(Math.random()*3)];
        imgDirectory = ""+dmg+imageIndex;
    }

    public int setDead(){dead = true;return getGold();}
    public boolean isDead(){return dead;}

    public void incrementAttack(int a){dmg+=a;}

    public int getGold(){return gold;}
    public String getAttack(){return attack;}
    public int getDmg(){return dmg;}

    public String getImgDirectory(){return imgDirectory;}
//    public void setTag(String s){tag=s;}
//    public String getTag(){return tag;}
//
//    public void setAttack(String s){attack=s;}

    public String toString(){
        return "Dmg: "+dmg+", Gold: "+gold+", Att: "+ attack;
    }

}
