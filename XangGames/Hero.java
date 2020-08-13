package XangGames;

import javax.swing.*;

public abstract class Hero {

    private int hp = 6;
    private Monster monster = null;
    private String attack = null;
    private boolean hasAbility = true;
    private String tag;

    private JPanel panel = new JPanel();
    private JLabel monPic;
    private JLabel monInfo;
    private JLabel playerPic;
    private JLabel playerInfo;

    public static final int WON_FIGHT = 0;
    public static final int LOST_FIGHT = 1;
    public static final int DRAW = 2;

    public Hero(String tag){
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        this.tag = tag;
        String dir = monster == null ? "none" : monster.getImgDirectory();
        monPic = new JLabel(new ImageIcon(".\\DungeonDivers\\"+dir+".png"));
        monInfo = new JLabel(monsterInfo());
        playerPic = new JLabel(new ImageIcon(".\\DungeonDivers\\"+tag+".png"));
        playerInfo = new JLabel(playerInfo());

        panel.add(monInfo);
        panel.add(monPic);

        panel.add(playerPic);
        panel.add(playerInfo);
    }

    public int wonFight(){
        if( (monster.getAttack().equals(DungeonDivers.ATTACKS[0]) && attack.equals(DungeonDivers.ATTACKS[1]))
            || (monster.getAttack().equals(DungeonDivers.ATTACKS[1]) && attack.equals(DungeonDivers.ATTACKS[2]))
            || (monster.getAttack().equals(DungeonDivers.ATTACKS[2]) && attack.equals(DungeonDivers.ATTACKS[0]))
            || (monster.isDead())){
            return 0;
        } else if (monster.getAttack().equals(attack)) {
            return 2;
        }
        return 1;
    }

    public abstract void activateAbility();
    public void usedAbility(){hasAbility=false;refreshPlayerLabel();}
    public void refreshAbility(){hasAbility=true;refreshPlayerLabel();}
    public boolean hasAbility(){return hasAbility;}

    public String getAttack(){return attack;}
    public void setAttack(String choice){attack=choice;refreshPlayerLabel();}

    public Monster getMonster(){return monster;}
    public void setMonster(Monster newMonster){monster = newMonster;refreshMonLabel();}
    public void showMonAtt(){monInfo.setText(monsterInfo());}
    public void incrementMonAtt(int att){
        if(getMonster()!=null) {
            getMonster().incrementAttack(att);
        }
        refreshMonLabel();
    }

    public int getHp(){return hp;}
    public boolean isDead(){return hp<=0;}
    public void takeDamage(int loss){ hp-=loss;refreshPlayerLabel();}
    public void setHp(int heal){hp=heal;refreshPlayerLabel();}

    public String getTag(){return tag;}

    public JPanel getPanel(){ return panel; }
    public void setPanel(JPanel p ){panel = p; }

    private void refreshPlayerLabel(){ playerInfo.setText(playerInfo());}
    private void refreshMonLabel(){
        String displayPic = monster == null ? "none" : monster.getImgDirectory();
        monInfo.setText(monsterInfo().substring(0,monsterInfo().length()-6));
        monPic.setIcon(new ImageIcon(".\\DungeonDivers\\"+displayPic+".png"));
        /*monPic.setIcon(new ImageIcon(monsterInfo().split(":")[0]));*/
    }

    private String playerInfo(){
        if(attack==null) attack = "None";
        if(hp<=0) return tag+": DEAD";
        return tag+": "+hp+" Hp, Abil: "+(""+hasAbility).toUpperCase()+", Att: "+attack;
    }

    private String monsterInfo(){
        return monster == null ? "Monster : None" : monster.toString();
    }

    public String showAllInfo(){
        return playerInfo()+", "+monsterInfo();
    }

    public String toString(){
        return monster!=null ? showAllInfo().substring(0,showAllInfo().length()-10) : showAllInfo();
    }
}
