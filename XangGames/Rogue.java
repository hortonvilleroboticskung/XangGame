package XangGames;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public class Rogue extends Hero {

    public Rogue() {
        super("Rogue");
    }

    @Override
    public void activateAbility(){
        if(getMonster()!=null && !getMonster().isDead()) {
            usedAbility();
            getMonster().setDead();
        } else {
            JOptionPane.showMessageDialog(DungeonDivers.otherFrame,"They don't have a monster, or it's dead");
            activateAbility();
        }
    }
}