package XangGames;

import Games.GUI;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DungeonDivers {

    public static JFrame frame = new JFrame();
    public static JFrame otherFrame = new JFrame();
    public static JPanel panel = new JPanel(new BorderLayout());
    public static final String[] ATTACKS = {"R","P","S"};
    public static int[] dmgMobs = {5,5,7,2};
    public int guildBank = 0;
    public int killCount = 0;
    public int round = 1;
    public boolean bossDead = false;
    public static ArrayList<Monster> mobs = new ArrayList<>();
    public static Hero[] players ={new Wiz(),new Barb(),new Healer(),new Ranger(),new Rogue()};
    public JLabel header1 = new JLabel(getHeader(),JLabel.CENTER);
    public JPanel heroPanel = new JPanel();

    public DungeonDivers(){
        mobsSetup();
        playerSetup();
        while(mobs.size()>0 && playersStillAlive()!=0 && !bossDead) {
            if (guildBank >= 3) shopping();
            attackSetup();
            abilityPrompt();
            calculateFight();
            resetMobs();
        }

        JOptionPane.showMessageDialog(frame,"You guys killed "+killCount+" monsters","Kill Count",JOptionPane.PLAIN_MESSAGE);
    }

    private void mobsSetup(){
        for(int mobCount = 0; mobCount < dmgMobs.length; mobCount++){
            for(int i = 0; i < dmgMobs[mobCount]; i++){
                if(mobCount < 3){
                    mobs.add(new Monster(mobCount,i));
                } else {
                    mobs.add(new MiniBoss(i));
                }
            }
        }
        mobs.add(new Boss());
        shuffleMobs();
    }

    private void playerSetup(){
        panel.add(header1,BorderLayout.PAGE_START);
        resetMobs();
        for(Hero h : players){
            heroPanel.add(h.getPanel());
        }
        panel.add(heroPanel,BorderLayout.CENTER);
        frame.add(panel);

        GUI.defaultFrameWork(frame);
        frame.setLocation(300,150);
        frame.setResizable(true);
    }

    private void shopping(){
        Hero chosenHero = (Hero)JOptionPane.showInputDialog(otherFrame,
                "Would you like to buy an ability? Cost 3 gold", "Shop", JOptionPane.INFORMATION_MESSAGE, null, players, players[0]);
        if(chosenHero!=null) {
            if (chosenHero.isDead()) {
                JOptionPane.showMessageDialog(otherFrame, "Sorry, but that Hero is mo shinderu");
                shopping();
            } else if(chosenHero.hasAbility()){
                JOptionPane.showMessageDialog(otherFrame, "Sorry, Hero already has an ability");
                shopping();
            } else {
                chosenHero.refreshAbility();
                guildBank -= 3;
                header1.setText(getHeader());
            }
        }
    }

    private void attackSetup(){
        for(Hero h : players){
            if(!h.isDead() && h.getMonster()!=null && !h.getMonster().isDead()) {
                Object attack = JOptionPane.showInputDialog(frame,
                        h.getTag() + ": choose an attack", "Attack Chooser", JOptionPane.INFORMATION_MESSAGE, null, ATTACKS, ATTACKS[0]);
                h.setAttack(attack == null ? "R" : attack.toString());
                h.showMonAtt();
            }
        }
//        for(int i = 0; i < heroPanel.getComponentCount(); i++){
//            System.out.println(((JPanel)heroPanel.getComponent(i)).getComponent(2));
//        }
//        System.out.println("===");
    }

    private void abilityPrompt(){
        for(Hero h : players){
            if(h.hasAbility() && !h.isDead()){
                Object usedAbility = JOptionPane.showConfirmDialog(otherFrame,
                        h.getTag()+": Do you want to use your Ability?","Ability Usage",JOptionPane.YES_NO_OPTION);
                if((int)usedAbility==0){
                    h.activateAbility();
                }
            }
        }
    }

    private void calculateFight(){
        boolean areDraws = false;
        for(Hero h : players){
            if(h.getMonster()!=null) {
                int fightResult = h.wonFight();
                if (fightResult==Hero.WON_FIGHT) {
                    h.getMonster().setDead();
                    guildBank += h.getMonster().getGold();
                    killCount += 1;
                    h.setMonster(null);
                } else if(fightResult==Hero.LOST_FIGHT) {
                   //TODO: Add message to say they took dmg
                    h.takeDamage(h.getMonster().getDmg());
                    mobs.add((int) (Math.random() * mobs.size()), h.getMonster());
                    h.setMonster(null);
                } else {
                    areDraws = true;
                }
            }
        }
        if(areDraws){
            JOptionPane.showMessageDialog(otherFrame,"There were draws","Calculated Fights",JOptionPane.INFORMATION_MESSAGE);
            attackSetup();
        }
        round++;
        header1.setText(getHeader());
    }

    private void resetMobs(){
        ArrayList<MiniBoss> miniBosses = new ArrayList<>();
        for(Hero h : players){
            if(h.getHp()>0 && mobs.size()>0){
                Monster m = mobs.remove(0);
                if(m instanceof MiniBoss)  miniBosses.add((MiniBoss) m);
                h.setMonster(m);
            }
        }
        for(int i = 0; i < miniBosses.size(); i++) {
            MiniBoss.useAbility(miniBosses.get(i));
        }
    }

    private void shuffleMobs(){
        ArrayList<Monster> shuffled = new ArrayList<>();
        for(Monster m : mobs){
            Monster mobM = mobs.get((int)(Math.random()*mobs.size()));
            shuffled.add(mobM);
        }
        mobs = shuffled;
    }

    private int playersStillAlive(){
        int count = 0;
        for (Hero h : players) {
            if(h.getHp()>0)count++;
        }
        return count;
    }

    private String getHeader(){
        return "Round:"+round+"          The Team has "+guildBank+" Gold";
    }

    public static void main(String[] args){
        new DungeonDivers();
    }

}
