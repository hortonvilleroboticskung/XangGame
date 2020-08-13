package XangGames;

import javax.swing.*;

public class Wiz extends Hero {

    public Wiz() {
        super("Wiz");
    }

    @Override
    public void activateAbility(){
        Hero selectedHero = (Hero)JOptionPane.showInputDialog(DungeonDivers.otherFrame,"Wizard: Pick a hero to help","Wiz's Ability",JOptionPane.INFORMATION_MESSAGE,
                null,DungeonDivers.players,DungeonDivers.players[0]);
        if(selectedHero!=null && selectedHero.getMonster()!=null && !selectedHero.getMonster().isDead()) {
            usedAbility();
            DungeonDivers.mobs.add((int)(Math.random()*DungeonDivers.mobs.size()),((Hero)selectedHero).getMonster());
            selectedHero.setMonster(null);
        } else {
            JOptionPane.showMessageDialog(DungeonDivers.otherFrame,"Sorry, Can't pick that one","Player Error",JOptionPane.INFORMATION_MESSAGE);
            Object usedAbility = JOptionPane.showConfirmDialog(DungeonDivers.frame,getTag()+": Do you want to use your Ability?","Ability Usage",JOptionPane.YES_NO_OPTION);
            if((int)usedAbility==0){
                activateAbility();
            }
        }
    }
}
