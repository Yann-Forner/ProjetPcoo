package Model;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class HierarchieSelection {
    private DefaultMutableTreeNode arbre;

    public HierarchieSelection(Programme programme){
        arbre=CreationArbre(programme);
    }

    private DefaultMutableTreeNode CreationArbre(Programme CurrentProgramme){
        ArrayList<Bloc> blocs=CurrentProgramme.getBlocs();
        DefaultMutableTreeNode Racine=new DefaultMutableTreeNode(CurrentProgramme.getNom());
        for(Bloc b:blocs){
            ArrayList<UE> ue=b.getUE();
            if(ue.size()==1){
                Racine.add(new DefaultMutableTreeNode(b.getNom()));
            }
            else{
                DefaultMutableTreeNode Branche=new DefaultMutableTreeNode(b.getNom());
                for(UE u:ue){
                    Branche.add(new DefaultMutableTreeNode(u.getNom()));
                }
                Racine.add(Branche);
            }
        }
        return Racine;
    }

    public DefaultMutableTreeNode getArbre() {
        return arbre;
    }

}