package XangGames;

import javax.swing.*;

public class Ranger extends Hero {

    public Ranger() {
        super("Ranger");
    }

    @Override
    public void activateAbility(){
        Hero selectedHero = (Hero)JOptionPane.showInputDialog(DungeonDivers.otherFrame,"Ranger: Pick a hero to help","Ranger's Ability",JOptionPane.INFORMATION_MESSAGE,
                null,DungeonDivers.players,DungeonDivers.players[0]);
        if(selectedHero.getMonster()!=null && !((Hero)selectedHero).getMonster().isDead()) {
            usedAbility();
            if ( (selectedHero.getMonster().getAttack().equals(DungeonDivers.ATTACKS[0]) && getAttack().equals(DungeonDivers.ATTACKS[1]))
                    || (selectedHero.getMonster().getAttack().equals(DungeonDivers.ATTACKS[1]) && getAttack().equals(DungeonDivers.ATTACKS[2]))
                    || (selectedHero.getMonster().getAttack().equals(DungeonDivers.ATTACKS[2]) && getAttack().equals(DungeonDivers.ATTACKS[0])) ) {
                selectedHero.getMonster().setDead();
            }
        } else {
            JOptionPane.showMessageDialog(DungeonDivers.otherFrame,"Sorry, Can't pick that one","Player Error",JOptionPane.INFORMATION_MESSAGE);
            Object usedAbility = JOptionPane.showConfirmDialog(DungeonDivers.otherFrame,getTag()+": Do you want to use your Ability?","Ability Usage",JOptionPane.YES_NO_OPTION);
            if((int)usedAbility==0){
                activateAbility();
            }
        }
    }

}
