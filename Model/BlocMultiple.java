package Model;

import java.util.ArrayList;

/**
 * Classe abstraite représentant un bloc multiple
 */
public abstract class BlocMultiple  extends Bloc {
    protected ArrayList<UE> listUe = new ArrayList<>();


    public BlocMultiple(String id, String nom) {
        super(id, nom);
    }

    /**
     * Ajoute un élément à la liste des UEs
     * @param ue à ajouter
     */
    public void add(UE ue){
        this.listUe.add(ue);
    }

    /**
     * Retourne une string comportant la nature du bloc multiple (composite ou option)
     * @return String
     */
    public abstract String nature ();

    @Override
    public String toString() {
        return nature()+" : "+this.getId()+" "+ getNom() +"{" +
                "listUe=" + listUe +
                '}';
    }

    @Override
    public ArrayList<UE> getUE(){
        return listUe;
    }
}
