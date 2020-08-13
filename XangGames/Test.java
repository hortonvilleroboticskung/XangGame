package XangGames;

import Games.GUI;

import javax.swing.*;

public class Test {

    String[] at = {"A","B"};
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JOptionPane op = new JOptionPane("Select att",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,null,DungeonDivers.players,null);
    JOptionPane opt = new JOptionPane("LMAO",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,null,DungeonDivers.players,null);
    JDialog dialog;

    public Test(){
        //frame.add(op);
        GUI.defaultFrameWork(frame);
        //JOptionPane.showInputDialog(frame,"Select att","Lol",JOptionPane.INFORMATION_MESSAGE,null,at,"S");
//        dialog = op.createDialog(null, "TEST");
//        dialog.setLocation(500, 200);
//        dialog.setVisible(true);
//        System.out.println(op.getValue());
//        dialog = opt.createDialog(null,"Other Test");
//        dialog.setLocation(500,200);
//        dialog.setVisible(true);
//        System.out.println(opt.getValue());
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


    }

    public static void main(String[] args){
        new Test();
    }

}
