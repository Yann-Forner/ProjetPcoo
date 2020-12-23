package Model;

import Controller.Arbre;
import Controller.Dialog;
import View.Home;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public class HierarchieCreation {
    private Home home;

    public HierarchieCreation(Home home){
        this.home=home;
        setDialogBox();
    }

    public HierarchieCreation(Home home,ArrayList<String> filtreUE){
        this.home=home;
        if(filtreUE.size()>0){
            setFiltreUE(filtreUE);
        }
    }

    private JTree ArbreProgrammesSelectiones(){
        ArrayList<Programme> programList = home.getXml().getProgramList();
        DefaultMutableTreeNode arbre=new DefaultMutableTreeNode("Programmes");
        for(Programme p:programList){
            HierarchieSelection hierarchie = new HierarchieSelection(p);
            arbre.add(hierarchie.getArbre());
        }
        return new JTree(arbre);
    }

    private void setDialogBox(){
        JTree arbre=ArbreProgrammesSelectiones();

        JDialog dialog = new JDialog(home.getFrame(), "Hierarchie");
        JScrollPane scroll=new JScrollPane(arbre);

        arbre.addTreeExpansionListener(new Arbre(dialog,arbre));
        dialog.addWindowListener(new Dialog(home,arbre));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dialog.add(scroll);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private void setFiltreUE(ArrayList<String> filtreUE){
        ProgramSwitch programSwitch = new ProgramSwitch(home);
        if(filtreUE.size()>0 && filtreUE.contains("Programmes")){
            programSwitch.Switch(-1);
        }
        else{
            HashMap<Cours,String> FiltreCoursHashMap = new HashMap<>();
            ArrayList<Programme> programmeArrayList=home.getXml().getProgramList();
            for(Programme p:programmeArrayList){
                for(Bloc b:p.getBlocs()){
                    for(UE u:b.getUE()){
                        if(filtreUE.contains(p.getNom()) || filtreUE.contains(b.getNom()) || filtreUE.contains(u.getNom())){
                            FiltreCoursHashMap.put((Cours)u,u.getNom());
                        }
                    }
                }
            }
            ArrayList<Cours> cours=new ArrayList<>();
            FiltreCoursHashMap.forEach((cour,nom)->cours.add(cour));
            programSwitch.Switch(cours);
        }
    }
}