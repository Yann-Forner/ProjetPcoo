package Model;

import Exceptions.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant un étudiant
 */
public class Etudiant {
    private final String id;
    private final String nom;
    private final String prenom;
    private Programme p;
    private HashMap<Cours, Note> notes = new HashMap<Cours,Note>();

    public Etudiant(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        if(id.equals("") || !id.chars().allMatch( Character::isDigit ) )throw new IdEtudiantInvalidException(this);
        if(nom.equals(""))throw new NameEtudiantInvalidException(this);
        if(prenom.equals(""))throw new SurnameEtudiantInvalidException(this);

    }
    /**
     * Retourne le programme auquel l'élève est inscris
     * @return Programme
     */
    public Programme getP() {
        return p;
    }


    /**
     * Getter de l'id d'un étudiant
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter du nom d'un étudiant
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter du prenom d'un étudiant
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Inscris un étudiant à un programme
     * @param p le programme concerné
     */
    public void inscris(Programme p){
        this.p =p;
    }

    /**
     * Teste si un étudiant est inscris à un programme passé en paramètre
     * @param p le programme
     * @return boolean
     */
    public boolean estInscris(Programme p){
        return p.getId().equals(this.p.getId());
    }

    /**
     * Getter de la Hashmap des notes d'un etudiant
     * @return notes
     */
    public HashMap<Cours, Note> getNotes() {
        return notes;
    }

    /**
     * Ajout d'une note à un étudiant
     * @param cours le cours de la note à ajouter
     * @param n la note
     */
    public void addNote(Cours cours , Note n){
        notes.put(cours,n);
    }

    /**
     * Teste si un étudiant a été noté dans un cours
     * @param cours le cours
     * @return boolean
     */
    public boolean isPresent(Cours cours){
        return this.notes.get(cours) !=null;
    }

    /**
     * Retourne une version XML d'un étudiant
     * @param sb le StringBuilder auquel ajouter l'étudiant
     */
    public void toXml(StringBuilder sb){
        sb.append("    <student>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n")
                .append("        <surname>").append(this.getPrenom()).append("</surname>\n")
                .append("        <program>").append(this.getP().getNom()).append("</program>\n");
        for(Map.Entry<Cours, Note> entry : this.getNotes().entrySet()) {
            Cours key = entry.getKey();
            Note value = entry.getValue();
            sb.append("        <grade>\n")
                    .append("            <item>").append(key.getId()).append("</item>\n")
                    .append("            <value>").append(value.getNote()).append("</value>\n")
                    .append("        </grade>\n");

        }
                sb.append("    </student>\n");
    }

    @Override
    public String toString() {
        String program = "aucun";
        if(this.p != null) program = this.getP().getId();
        return this.getId()+" : "+this.getNom()+" "+this.getPrenom()+" -> Programe :"+ program;
    }
}
