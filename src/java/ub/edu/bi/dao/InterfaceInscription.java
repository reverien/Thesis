package ub.edu.bi.dao;

import java.util.List;
import javax.faces.model.SelectItem;
import ub.edu.bi.Classe;
import ub.edu.bi.Etudiant;
import ub.edu.bi.Promotion;
import ub.edu.bi.Session;

public interface InterfaceInscription {
    public Classe InsertClasse(Classe cl);
    public List<Classe> selectClasse();
    public Classe updateClasse(Classe cl);
    public void deleteClasse(Classe cl);
    public List<SelectItem> listeClasse();
    //*************************************
    public Etudiant insertEtudiant(Etudiant et);
    public List<Etudiant> selectEtudiant();
    public Etudiant updateEtudiant(Etudiant et);
    public void deleteEtudiant(Etudiant et);
    //******************************************
    public Promotion insertPromotion(Promotion prom);
    public List<Promotion> selectPromotion();
    public Promotion updatePromotion(Promotion prom);
    public void deletePromotion(Promotion prom);
    //***********************************************
    public Session insertSession(Session sess);
    public List<Session> selectSession();
    public Session updateSession(Session sess);
    public void deleteSession(Session sess);
    //********************************************
    public void insertInscription(Long et, Long cl, Long prom, Long sess);
}