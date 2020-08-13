package XangGames;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public class Healer extends Hero {

    public Healer() {
        super("Healer");
    }

    @Override
    public void activateAbility(){
        usedAbility();
        Hero chosenHero = (Hero)JOptionPane.showInputDialog(DungeonDivers.otherFrame,"Pick a Hero to heal","Healing", JOptionPane.INFORMATION_MESSAGE,
                null,DungeonDivers.players,DungeonDivers.players[0]);
        if(chosenHero!=null && !chosenHero.isDead()) {
            chosenHero.setHp(chosenHero.getHp() + 3 > 6 ? 6 : chosenHero.getHp() + 3);
        } else {
            JOptionPane.showMessageDialog(DungeonDivers.otherFrame,"Sorry, Can't pick that one","Player Error",JOptionPane.INFORMATION_MESSAGE);
            Object usedAbility = JOptionPane.showConfirmDialog(DungeonDivers.otherFrame,getTag()+": Do you want to use your Ability?","Ability Usage",JOptionPane.YES_NO_OPTION);
            if((int)usedAbility==0){
                activateAbility();
            }
        }
    }
}
