package Model;

import Exceptions.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Un programme est une liste de blocs à obtenir pour valider une étape.
 */
public class Programme {
    private ArrayList<Bloc> blocs = new ArrayList<>();
    private String nom;
    private String id;

    public Programme(String nom, String id) {
        this.nom = nom;
        this.id = id;
        if(id.equals(""))throw new IdProgramInvalidException(this);
        if(nom.equals(""))throw new NameProgramInvalidException(this);
    }

    /**
     * Getteur du nom d'un programme
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getteur de l'ID d'un programme
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getteur des blocs qui composent un programme
     * @return ArrayList de bloc
     */
    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }

    /**
     * Ajoute un bloc à un programme
     * @param b le bloc
     */
    public void add(Bloc b){
        this.blocs.add(b);
    }

    /**
     * Test si un étudiant a validé un programme
     * @param e l'étudiant
     * @return boolean
     */
    public boolean isValidated(Etudiant e){
        int somme=0;
        int total=0;
        for (Bloc b : this.blocs
             ) {

            somme += b.getCoef() * b.calcNote(e).getFloatNote();
            total += b.getCoef();
        }
        return (somme/total) >= 10;
    }
    /**
     * Retourne une version XML d'un Programme
     * @param sb le StringBuilder auquel ajouter le programme
     */
    public void toXml(StringBuilder sb){
        sb.append("    <program>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n");
        for (Bloc b: this.getBlocs()
             ) {
            b.toXml(sb);
        }
        sb.append("    </program>\n");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return Objects.equals(id, programme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.id+" : "+this.nom;
    }

}
