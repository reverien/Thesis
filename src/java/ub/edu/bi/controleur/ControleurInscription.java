package ub.edu.bi.controleur;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletResponse;
import ub.edu.bi.Classe;
import ub.edu.bi.Etudiant;
import ub.edu.bi.Inscription;
import ub.edu.bi.Promotion;
import ub.edu.bi.TypeInscription;
import ub.edu.bi.dao.ImplementClasse;
import ub.edu.bi.dao.ImplementPromotion;
import ub.edu.bi.dao.ImplementTypeInscription;
import ub.edu.bi.dao.InscriptionDAO;
import ub.edu.bi.dao.implementOption;

@ManagedBean(name = "inscription")
@SessionScoped
public class ControleurInscription implements Serializable {

    private DataModel inscriptions;
    private Inscription newInscription;
    private InscriptionDAO dao;
    private ImplementClasse ClasseDAO = new ImplementClasse();
    private ImplementPromotion promotionDAO = new ImplementPromotion();
    private ImplementTypeInscription sessionDAO = new ImplementTypeInscription();
    private implementOption optionDAO = new implementOption();
     private List<Inscription> selectAllInscription;
    private List<Inscription> selectByClasseAndSession;
    private List<Inscription> selectByClasseAndSessionAndPromotion;
    private List<Inscription> selectByClasseSessionAnnee;

    @PostConstruct
    public void init() {
        newInscription = new Inscription();
        newInscription.setCl(new Classe());
        newInscription.setEt(new Etudiant());
        newInscription.setProm(new Promotion());
        newInscription.setTypeInscr(new TypeInscription());
        dao = new InscriptionDAO();
        selectByClasseAndSession = new ArrayList<>();
        selectByClasseAndSessionAndPromotion = new ArrayList<>();
        selectByClasseSessionAnnee = new ArrayList<>();
        selectAllInscription = new ArrayList<>();
    }

    public void exportExcel() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        String reportName = "Inscription";
        response.setHeader("Content-disposition", "attachment;filename=" + reportName);
        ArrayList<String> colonne = new ArrayList<String>();

        colonne.add("Univeraite du burundi");
        colonne.add("\n");
        colonne.add("FSI");
        colonne.add("\n");

        colonne.add("Matricule");
        colonne.add("\t");
        colonne.add("Nom");
        colonne.add("\t");
        colonne.add("Prenom");
        colonne.add("\n");
        List<Inscription> listeExcel = new ArrayList<>();

        Long idCl = newInscription.getCl().getId();
        Classe c = ClasseDAO.selectById(idCl);
        newInscription.setCl(c);

        Long idSes = newInscription.getProm().getId();
        TypeInscription t = sessionDAO.selectById(idSes);
        newInscription.setTypeInscr(t);

        Long idAn = newInscription.getProm().getId();
        Promotion p = promotionDAO.selectById(idAn);
        newInscription.setProm(p);

        selectByClasseAndSessionAndPromotion = dao.selectByClasseAndSessionAndPromotion(idCl, idSes, idAn);

        listeExcel = selectByClasseAndSessionAndPromotion;
        System.out.println("-----++++++++++++++++++++++++++++++-----------------------+++++++++++++++++" + listeExcel);

        if (listeExcel != null) {
            // listeExcel.get(1).getCl().getNomClasse();
            try {
                for (int i = 0; i < listeExcel.size(); i++) {
                    colonne.add(String.valueOf(listeExcel.get(i).getEt().getMatriculeEtudiant()));
                    colonne.add("\t");
                    colonne.add(String.valueOf(listeExcel.get(i).getEt().getNomEtudiant()));
                    colonne.add("\t");
                    colonne.add(String.valueOf(listeExcel.get(i).getEt().getPrenomEtudiant()));
                    colonne.add("\n");

//                    System.out.println(listeExcel.get(i).getEt().getPrenomEtudiant());
                }
            } catch (Exception e) {
            }
        }
        Iterator<String> iter = colonne.iterator();
        while (iter.hasNext()) {
            String outputString = (String) iter.next();

            //response.getOutputStream().print(outputString);
            try {
                response.getOutputStream().print(outputString);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newInscription = new Inscription();
        }
        System.out.println("-----++++++++++++++++++++++++++++++-----------------------+++++++++++++++++");

        try {
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String createInscription() {
        try {
            dao.insertInscription(newInscription);
        } catch (Exception e) {
        }
        Long idClasse = newInscription.getCl().getId();
        Classe c = ClasseDAO.selectById(idClasse);
        newInscription.setCl(c);

        Long idSession = newInscription.getTypeInscr().getId();
        TypeInscription t = sessionDAO.selectById(idSession);
        newInscription.setTypeInscr(t);

        Long idPromotion = newInscription.getProm().getId();
        Promotion p = promotionDAO.selectById(idPromotion);
        newInscription.setProm(p);

        System.out.println("------------------" + newInscription.getCl().getNomClasse() + "" + idClasse + " " + idSession + " " + idPromotion);
        selectByClasseAndSessionAndPromotion = dao.selectByClasseAndSessionAndPromotion(idClasse, idSession, idPromotion);
        System.out.println(selectByClasseAndSessionAndPromotion);
        newInscription = new Inscription();
        newInscription.setCl(new Classe());
        newInscription.setEt(new Etudiant());
        newInscription.setProm(new Promotion());
        newInscription.setTypeInscr(new TypeInscription());
        return "/Vue/Operation/Inscription/FicheListClasseSessionPromotion?faces-redirect=true";
    }

    public void clickOnClassOrSession() {
        getSelectByClasseAndSession();
    }

    public String rechercheByClasseSessionAnnee() {
        getSelectByClasseSessionAnnee();
        return "/Vue/Operation/Inscription/FicheListClasseSessionPromotion?faces-redirect=true";
    }

    public List<Inscription> getSelectAllInscription() {
        selectAllInscription = dao.selectAllInscription();
        return selectAllInscription;
    }

    public void setSelectAllInscription(List<Inscription> selectAllInscription) {
        this.selectAllInscription = selectAllInscription;
    }
    
    

    public List<Inscription> getSelectByClasseSessionAnnee() {
        Long idCl = newInscription.getCl().getId();
        Classe c = ClasseDAO.selectById(idCl);
        newInscription.setCl(c);

        Long idSes = newInscription.getProm().getId();
        TypeInscription t = sessionDAO.selectById(idSes);
        newInscription.setTypeInscr(t);

        Long idAn = newInscription.getProm().getId();
        Promotion p = promotionDAO.selectById(idAn);
        newInscription.setProm(p);
        if(selectByClasseAndSessionAndPromotion != null){
            System.out.println("liste non null");            
        }else{
            System.out.println("liste null");
        }
        selectByClasseAndSessionAndPromotion = new ArrayList<>();
        selectByClasseAndSessionAndPromotion = dao.selectByClasseAndSessionAndPromotion(idCl, idSes, idAn);
        return selectByClasseSessionAnnee;
    }

    public void setSelectByClasseSessionAnnee(List<Inscription> selectByClasseSessionAnnee) {
        this.selectByClasseSessionAnnee = selectByClasseSessionAnnee;
    }

    public List<Inscription> getSelectByClasseAndSession() {
        Long idClasse = newInscription.getCl().getId();
        Long idSession = newInscription.getTypeInscr().getId();
        System.out.println("------------------Recuperer id classe ---------------------" + idClasse);
        System.out.println("------------------Recuperer id classe ---------------------" + idSession);
        selectByClasseAndSession = dao.selectByClasseAndSession(idClasse, idSession);
        return selectByClasseAndSession;
    }

    public void setSelectByClasseAndSession(List<Inscription> selectByClasseAndSession) {
        this.selectByClasseAndSession = selectByClasseAndSession;
    }

    public List<Inscription> getSelectByClasseAndSessionAndPromotion() {
        return selectByClasseAndSessionAndPromotion;
    }

    public void setSelectByClasseAndSessionAndPromotion(List<Inscription> selectByClasseAndSessionAndPromotion) {
        this.selectByClasseAndSessionAndPromotion = selectByClasseAndSessionAndPromotion;
    }

    public DataModel getInscriptions() {
        if (inscriptions == null) {
            inscriptions = new ListDataModel();
            inscriptions.setWrappedData(dao.selectAllInscription());
        }
        return inscriptions;
    }

    public void setInscriptions(DataModel inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Inscription getNewInscription() {
        return newInscription;
    }

    public void setNewInscription(Inscription newInscription) {
        this.newInscription = newInscription;
    }

    public InscriptionDAO getDao() {
        return dao;
    }

    public void setDao(InscriptionDAO dao) {
        this.dao = dao;
    }
}
