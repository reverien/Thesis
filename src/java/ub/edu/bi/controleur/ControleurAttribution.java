package ub.edu.bi.controleur;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import ub.edu.bi.Attribution;
import ub.edu.bi.Classe;
import ub.edu.bi.Cours;
import ub.edu.bi.Professeur;
import ub.edu.bi.Promotion;
import ub.edu.bi.Semestre;
import ub.edu.bi.dao.AttributionDAO;
import ub.edu.bi.dao.ImplementClasse;
import ub.edu.bi.dao.ImplementCours;
import ub.edu.bi.dao.ImplementPromotion;
import ub.edu.bi.dao.ImplementSemestre;

@ManagedBean(name = "attribution")
@SessionScoped
public class ControleurAttribution {

    private DataModel attributions;
    private Attribution newAttribution;
    private AttributionDAO dao;
    private ImplementCours coursDAO;
    private ImplementPromotion anneeDAO;
    private ImplementSemestre semestreDAO;
    private ImplementClasse classeDAO;
    private List<Attribution> listAttribution;
    private List<Attribution> selectAllAttribution;
    private List<Attribution> selectByCoursAndPromotion;
    private List<Attribution> selectByProfesseur;
    private Long idClasse, idSemestre, idAnnee;

    @PostConstruct
    public void init() {
        newAttribution = new Attribution();
        newAttribution.setAnnee(new Promotion());
        newAttribution.setCourse(new Cours());
//        newAttribution.getCourse().getCodeUnite().setClasseCode(new Classe());
//        newAttribution.getCourse().getCodeUnite().setSemestreCode(new Semestre());
        newAttribution.setProfessor(new Professeur());
        this.listAttribution = new ArrayList<>();
        selectAllAttribution = new ArrayList<>();
        selectByCoursAndPromotion = new ArrayList<>();
        selectByProfesseur = new ArrayList<>();
        dao = new AttributionDAO();
        coursDAO = new ImplementCours();
        anneeDAO = new ImplementPromotion();
        semestreDAO = new ImplementSemestre();
        classeDAO = new ImplementClasse();
    }

    public List<Attribution> createListAttribution() {
        listAttribution.add(newAttribution);
        return listAttribution;
    }

//        public List<Integer> getNombresSuivants() {
//        int nb = 8;
//        ArrayList<Integer> t = new ArrayList(nb);
//        for (int i = nombre; i < nombre + nb; i++) {
//            t.add(i);
//        }
//        return t;
//    }
    public String createAttribution() {
        try {
            dao.InsertAttribution(newAttribution);
        } catch (Exception e) {
        }
        Long idCours = newAttribution.getCourse().getId();
        System.out.println("--------------id cours---------------------" + idCours);
        Cours c = coursDAO.selectById(idCours);
        newAttribution.setCourse(c);
        System.out.println("-------cours----------" + c.getNomCours());

        Long idSemestre = newAttribution.getCourse().getCodeUnite().getSemestreCode().getId();
        Semestre s = semestreDAO.selectById(idSemestre);
        System.out.println("-------id semestre-----------" + s.getSemestre());
        newAttribution.getCourse().getCodeUnite().setSemestreCode(s);

        Long idClasse = newAttribution.getCourse().getCodeUnite().getClasseCode().getId();
        Classe cl = classeDAO.selectById(idClasse);
        newAttribution.getCourse().getCodeUnite().setClasseCode(cl);
        System.out.println("-------Classe-----------" + cl.getNomClasse());

        Long idAnne = newAttribution.getAnnee().getId();
        Promotion p = anneeDAO.selectById(idAnne);
        newAttribution.setAnnee(p);

        selectByCoursAndPromotion = dao.selectByClasseAndSemestreAndPromotion(idClasse, idSemestre, idAnne);
        System.out.println("-----------------------------------" + selectByCoursAndPromotion);
        newAttribution = new Attribution();
        newAttribution.setAnnee(new Promotion());
        newAttribution.setCourse(new Cours());
        newAttribution.setProfessor(new Professeur());
        return "/Vue/Operation/Attribution/FicheAttribution?faces-redirect=true";
    }

    public String rechercheParCoursPromotion() {
        Long idAn = this.idAnnee;
        Promotion p = anneeDAO.selectById(idAn);
        newAttribution.setAnnee(p);

        Long idCl = this.idClasse;
        Classe c = classeDAO.selectById(idCl);
        System.out.println("-----------------------------------" + idCl + " " + c);
//        newAttribution.getCourse().getCodeUnite().setClasseCode(c);

        Long idSem = this.idSemestre;
        Semestre s = semestreDAO.selectById(idSem);
//        newAttribution.getCourse().getCodeUnite().setSemestreCode(s);

//        Long idCours = newAttribution.getCourse().getId();
//        System.out.println("--------------id cours---------------------"+idCours);
//        Cours c = coursDAO.selectById(idCours);
//        newAttribution.setCourse(c);
//         System.out.println("-------cours----------"+c.getNomCours());
//        
//        Long idSemestre = newAttribution.getCourse().getCodeUnite().getSemestreCode().getId();
//        Semestre s = semestreDAO.selectById(idSemestre);
//        System.out.println("-------id semestre-----------"+s.getSemestre());
//        newAttribution.getCourse().getCodeUnite().setSemestreCode(s);
//        
//        Long idClasse = newAttribution.getCourse().getCodeUnite().getClasseCode().getId();
//        Classe cl = classeDAO.selectById(idClasse);
//        newAttribution.getCourse().getCodeUnite().setClasseCode(cl);
//         System.out.println("-------Classe-----------"+cl.getNomClasse());
//        
//        Long idAnne = newAttribution.getAnnee().getId();
//        Promotion p = anneeDAO.selectById(idAnne);
//        newAttribution.setAnnee(p);
        selectByCoursAndPromotion = dao.selectByClasseAndSemestreAndPromotion(idClasse, idSemestre, idAnnee);
        System.out.println("-----------------------------------" + selectByCoursAndPromotion);
        return "/Vue/Operation/Attribution/FicheAttribution?faces-redirect=true";
    }

    public String rechercheParCoursPromotion2() {
        Long idCours = newAttribution.getCourse().getId();
        System.out.println("--------------id cours---------------------" + idCours);
        Cours c = coursDAO.selectById(idCours);
        newAttribution.setCourse(c);
        System.out.println("-------cours----------" + c.getNomCours());

        Long idSemestre = newAttribution.getCourse().getCodeUnite().getSemestreCode().getId();
        Semestre s = semestreDAO.selectById(idSemestre);
        System.out.println("-------id semestre-----------" + s.getSemestre());
        newAttribution.getCourse().getCodeUnite().setSemestreCode(s);

        Long idClasse = newAttribution.getCourse().getCodeUnite().getClasseCode().getId();
        Classe cl = classeDAO.selectById(idClasse);
        newAttribution.getCourse().getCodeUnite().setClasseCode(cl);
        System.out.println("-------Classe-----------" + cl.getNomClasse());

        Long idAnne = newAttribution.getAnnee().getId();
        Promotion p = anneeDAO.selectById(idAnne);
        newAttribution.setAnnee(p);

        selectByCoursAndPromotion = dao.selectByClasseAndSemestreAndPromotion(idClasse, idSemestre, idAnne);
        System.out.println("-----------------------------------" + selectByCoursAndPromotion);
        return "/Vue/Operation/Attribution/FicheAttribution?faces-redirect=true";
    }

    public List<Attribution> getSelectByProfesseur() {
        HttpSession sess = ControleurConnection.findSessionUtilisateur();
        System.out.println("recupere session1 " + sess.getAttribute("user"));
        System.out.println("recupere session2 " + sess.getAttribute("et"));
        System.out.println("recupere session3 " + sess.getAttribute("prof"));
        System.out.println("recupere session4 " + sess.getAttribute("idEtudiant"));
        System.out.println("recupere session5 " + sess.getAttribute("idProfesseur"));
        Long id = (Long) sess.getAttribute("idProfesseur");
        selectByProfesseur = dao.selectByProf(id);
         System.out.println("liste des cours pour un professeur " + selectByProfesseur);
        return selectByProfesseur;
    }

    public void setSelectByProfesseur(List<Attribution> selectByProfesseur) {
        this.selectByProfesseur = selectByProfesseur;
    }

    public List<Attribution> getSelectByCoursAndPromotion() {
        return selectByCoursAndPromotion;
    }

    public void setSelectByCoursAndPromotion(List<Attribution> selectByCoursAndPromotion) {
        this.selectByCoursAndPromotion = selectByCoursAndPromotion;
    }

    public List<Attribution> getSelectAllAttribution() {
        selectAllAttribution = dao.selectAllAttribution();
        return selectAllAttribution;
    }

    public void setSelectAllAttribution(List<Attribution> selectAllAttribution) {
        this.selectAllAttribution = selectAllAttribution;
    }

    public DataModel getAttributions() {
        if (attributions == null) {
            attributions = new ListDataModel();
            attributions.setWrappedData(dao.selectAllAttribution());
        }
        return attributions;
    }

    public void setAttributions(DataModel attributions) {
        this.attributions = attributions;
    }

    public AttributionDAO getDao() {
        return dao;
    }

    public void setDao(AttributionDAO dao) {
        this.dao = dao;
    }

    public Attribution getNewAttribution() {
        return newAttribution;
    }

    public void setNewAttribution(Attribution newAttribution) {
        this.newAttribution = newAttribution;
    }

    public List<Attribution> getListAttribution() {
        return listAttribution;
    }

    public void setListAttribution(List<Attribution> listAttribution) {
        this.listAttribution = listAttribution;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public Long getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Long idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Long getIdAnnee() {
        return idAnnee;
    }

    public void setIdAnnee(Long idAnnee) {
        this.idAnnee = idAnnee;
    }

    public ImplementClasse getClasseDAO() {
        return classeDAO;
    }

    public void setClasseDAO(ImplementClasse classeDAO) {
        this.classeDAO = classeDAO;
    }
    
    
}
