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
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import ub.edu.bi.Attribution;
import ub.edu.bi.Classe;
import ub.edu.bi.Cours;
import ub.edu.bi.Etudiant;
import ub.edu.bi.Inscription;
import ub.edu.bi.Note;
import ub.edu.bi.Promotion;
import ub.edu.bi.TypeInscription;
import ub.edu.bi.Unite;
import ub.edu.bi.dao.AttributionDAO;
import ub.edu.bi.dao.ImplementClasse;
import ub.edu.bi.dao.ImplementCours;
import ub.edu.bi.dao.ImplementPromotion;
import ub.edu.bi.dao.ImplementTypeInscription;
import ub.edu.bi.dao.ImplementUnite;
import ub.edu.bi.dao.InscriptionDAO;
import ub.edu.bi.dao.NoteDAO;

@ManagedBean(name = "note")
@SessionScoped
public class ControleurNote implements Serializable {

    private DataModel notes;
    private Note newNote;
    private Note evaluation;
    private NoteDAO dao;
    private ImplementUnite uniteDAO;
    private ImplementClasse classeDAO;
    private ImplementTypeInscription sessionDAO;
    private ImplementPromotion anneDAO;
    private AttributionDAO attribDAO;
    private InscriptionDAO inscrDAO;
    private ImplementCours coursDAO;
    private boolean activeUnite = true;
    private boolean activeCours = true;
    private List<Unite> listeUnitesByIdClasse;
    private Long postAttribution;
    private Long postSession;
    private String classe, cours, professeur, annee;
    private Long idClasse_grille, idSession_Grille, idAnne_Grille;
    private Attribution att;
    private List<Inscription> selectEtudiantInscrit;
    private List<Note> listeTemporelleNote;
    private List<Note> listeNoteClasseAnneeSession;
    private List<Attribution> listeAttributionClasseAnneeSession;
    private List<Inscription> listeInscriptionClasseAnneeSession;
    private String etatCours = "Non evalué";
    private List<Etudiant> listeEtudiantEvalue;
    private List<Cours> listeCoursEvalue;
    private List<Unite> listeUEDansUneClasseSemestre;
    private int nombreUEDansSemestre;
    private int nombreUEDansUneAnnee;
    private List<Cours> listeCoursDansUnite;
    private int nombreCoursDansUneUE;
    private int creditDuCours;
    private Double noteRecuDansUnCours;
    private Double pointsObtenuSurCreditDansUnCours;
    private Double pointsTotalObtenuDansUneUE;
    private int nombreCreditDansUneUE;
    private int nombreCreditDansUnSemestre;
    private Double pointsObtenuDansUnSemestre;
    private Double moyenneSur20UE;
    private String testValidationUE;
    private int nombreDesCoursNoteEstSousDix;
    private Double pointsSur20DansUnSemestre;
    private Double pointsObtenuDansUneAnnee;
    private Double pourcentageDansUnSemestre;
    private int nombreDeCreditDansUneAnnee;
    private String testValidationSemestre;
    private int nombreDesUEMoyenneEstSousDix;
    private Double moyenneAnnuelle;
    private Double pourcentageAnnuel;
    private String mention;
    private String decision;
    private int nombreCoursNonFaitParUnEtudiant;

    private List<Unite> listeUniteEnseignementDansUneClasseSemestre1;
    private int taileUESemestre1;
    private List<Unite> listeUniteEnseignementDansUneClasseSemestre2;
    private int taileUESemestre2;
    private List<Cours> listeCoursDansUniteSemestre1;
    private List<Long> listeCreditCoursSemestre1;
    private List<Cours> listeCoursDansUniteSemestre2;
    private List<Long> listeCreditCoursSemestre2;
    private int maximaSemestriel;
    private int[] tableauCreditUnite;
    private List<Note> listeNoteInscriptionClasseAnneeSession;
    private List<Note> listeConsulterFicheDePoints;
    private List<Classe> findClasseByProfesseur;
    private List<Promotion> findAnneByProfesseur;
    private List<TypeInscription> findSessionByProfesseur;
    private List<Cours> findCoursByProfesseur;
    private int nombreCoursDansUneClasse;
    private int nombreEtudiantInscritDansUneClasse;

    @PostConstruct
    public void init() {
        newNote = new Note();
        evaluation = new Note();
        evaluation.setNote(0.0);
        newNote.setAttrib(new Attribution());
        newNote.getAttrib().setCourse(new Cours());
        newNote.setInsc(new Inscription());
        newNote.getInsc().setEt(new Etudiant());
        newNote.setCl(new Classe());
        newNote.setPromo(new Promotion());
        newNote.setSess(new TypeInscription());

        dao = new NoteDAO();
        sessionDAO = new ImplementTypeInscription();
        anneDAO = new ImplementPromotion();
        attribDAO = new AttributionDAO();
        inscrDAO = new InscriptionDAO();
        uniteDAO = new ImplementUnite();
        classeDAO = new ImplementClasse();
        coursDAO = new ImplementCours();
        listeUnitesByIdClasse = new ArrayList<>();
        selectEtudiantInscrit = new ArrayList<>();
        listeTemporelleNote = new ArrayList<>();
        listeNoteClasseAnneeSession = new ArrayList<>();
        listeEtudiantEvalue = new ArrayList<>();
        listeCoursEvalue = new ArrayList<>();
        listeAttributionClasseAnneeSession = new ArrayList<>();
        listeUniteEnseignementDansUneClasseSemestre1 = new ArrayList<>();
        listeUniteEnseignementDansUneClasseSemestre2 = new ArrayList<>();
//        listeCoursDansUniteSemestre1 = new 
        listeCoursDansUniteSemestre1 = new ArrayList<>();
        listeCoursDansUniteSemestre2 = new ArrayList<>();
        tableauCreditUnite = new int[10];
        listeNoteInscriptionClasseAnneeSession = new ArrayList<>();
        listeConsulterFicheDePoints = new ArrayList<>();
    }

//debut implementation de l'algorithme
    public String grilleDeliberation() {
        System.out.println("---------------------debut implementation----------------------------");
        System.out.println(newNote.getCl().getId() + "-" + newNote.getPromo().getId() + "-" + newNote.getSess().getId());
        idClasse_grille = newNote.getCl().getId();
        idSession_Grille = newNote.getPromo().getId();
        idAnne_Grille = newNote.getSess().getId();
//        listeAttributionClasseAnneeSession = new ArrayList<>();
//         listeInscriptionClasseAnneeSession = new ArrayList<>();
        listeCoursDansGrille();
        listeEtudiantInscrit();
        for (Inscription i : listeInscriptionClasseAnneeSession) {//parcourir les etudiants
            System.out.println(i.getEt());
            nombreCoursNonFaitParUnEtudiant = 0;
            nombreUEDansUneAnnee = 0;
            pointsObtenuDansUneAnnee = 0.0;
            nombreDesUEMoyenneEstSousDix = 0;
            Long semestre;
            for (semestre = 1L; semestre <= 2L; semestre++) {//parcourir les semestres
                pointsObtenuDansUnSemestre = 0.0;
                moyenneSur20UE = 0.0;
                nombreCreditDansUnSemestre = 0;
                nombreDesCoursNoteEstSousDix = 0;
                pointsSur20DansUnSemestre = 0.0;
                System.out.println("Semestre 1 " + semestre);
                listeUEDansSemestre(idClasse_grille, semestre);
                System.out.println("nombre UE dans le semestre " + semestre + " est de " + nombreUEDansSemestre);

                for (Unite u : listeUEDansUneClasseSemestre) { //parcourir les unite d'enseignements
                    pointsTotalObtenuDansUneUE = 0.0;
                    nombreCreditDansUneUE = 0;

                    listeCoursDansUneUE(u.getId());
                    System.out.println("nombre cours" + nombreCoursDansUneUE + " liste cours  " + listeCoursDansUnite);
                    for (Cours c : listeCoursDansUnite) { // parcourir les cours dans chaque UE
                        for (Attribution at : listeAttributionClasseAnneeSession) {
                            if (c.getId() == at.getCourse().getId()) { //Tester si le cours a ete evalue
                                creditDuCours = c.getCodeCredit().getCredit();
                                evaluation = dao.findNoteByAllId(i.getId(), at.getId(), idClasse_grille, idAnne_Grille, idSession_Grille);
                                noteRecuDansUnCours = evaluation.getNote();
                                pointsObtenuSurCreditDansUnCours = noteRecuDansUnCours * creditDuCours;
                                pointsTotalObtenuDansUneUE = pointsTotalObtenuDansUneUE + pointsObtenuSurCreditDansUnCours;
                                nombreCreditDansUneUE = nombreCreditDansUneUE + creditDuCours;
                                if (noteRecuDansUnCours == null) {
                                    nombreCoursNonFaitParUnEtudiant = nombreCoursNonFaitParUnEtudiant + 1;
                                }
                                System.out.println("*********Note recu sur 20******* " + noteRecuDansUnCours + " soit " + pointsObtenuSurCreditDansUnCours);
                            }
                        }
                    }
                    System.out.println("++++++Points toatal dans une unite+++++++" + pointsTotalObtenuDansUneUE + " ---de credit---" + nombreCreditDansUneUE);
                    nombreCreditDansUnSemestre = nombreCreditDansUnSemestre + nombreCreditDansUneUE;
                    moyenneSur20UE = pointsTotalObtenuDansUneUE / nombreCreditDansUneUE;
                    pointsObtenuDansUnSemestre = pointsObtenuDansUnSemestre + pointsTotalObtenuDansUneUE;
                    System.out.println(u.getNomUnite() + " a une moyenne de: " + moyenneSur20UE);
                    testValidationUE = testUE(moyenneSur20UE);
                    System.out.println(u.getNomUnite() + " est " + testValidationUE);

                }
                System.out.println("points dans un semestre " + pointsObtenuDansUnSemestre);
                System.out.println("Nombre des UE ayant sous dix " + nombreDesCoursNoteEstSousDix);
                pointsSur20DansUnSemestre = pointsObtenuDansUnSemestre / nombreCreditDansUnSemestre;
                pointsObtenuDansUneAnnee = pointsObtenuDansUneAnnee + pointsSur20DansUnSemestre;
                pourcentageDansUnSemestre = pointsSur20DansUnSemestre * 5;
                nombreDeCreditDansUneAnnee = nombreDeCreditDansUneAnnee + nombreCreditDansUnSemestre;
                testValidationSemestre = testSemestre(nombreDesCoursNoteEstSousDix);
                System.out.println(" semestre " + semestre + " est " + testValidationSemestre);
                nombreDesUEMoyenneEstSousDix = nombreDesUEMoyenneEstSousDix + nombreDesCoursNoteEstSousDix;
            }
            System.out.println("nombre UE ayant sous dix " + nombreDesUEMoyenneEstSousDix);
            moyenneAnnuelle = pointsObtenuDansUneAnnee / 2;
            pourcentageAnnuel = moyenneAnnuelle * 5;
            System.out.println("?????????Points annuels??????????? " + moyenneAnnuelle + " soit " + pourcentageAnnuel + "%");
            mention = attributionMention(pourcentageAnnuel, nombreCoursNonFaitParUnEtudiant);
            System.out.println(mention);
            decision = prendreDecision(nombreDesUEMoyenneEstSousDix, nombreCoursNonFaitParUnEtudiant, nombreUEDansUneAnnee);
            System.out.println(decision);
        }
        System.out.println("nombre UE dans une annee est de " + nombreUEDansUneAnnee);

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("forme:display");
        requestContext.execute("PF('dlg').show()");

        System.out.println("--------------------Fin implementation------------------------------");
        return "/Vue/Operation/Deliberation/GenerationGrille2?faces-redirect=true";
    }

    public String prendreDecision(int a, int b, int c) {
        String dec = null;
        if (a > 0) {
            if (b == 0) {
                if (c == nombreDeCreditDansUneAnnee) {
                    dec = "AT";
                } else {
                    dec = "A";
                }
            } else {
                dec = "AA";
            }
        } else {
            dec = "R";
        }

        return dec;
    }

    public String attributionMention(double p, int n) {
        String mention = null;
        if (n == 0) {
            if (p >= 90) {
                mention = "Plus Grande Distinction";
            } else if (p >= 80) {
                mention = "Grande Distinction";
            } else if (p >= 70) {
                mention = "Distinction";
            } else if (p >= 60) {
                mention = "Satisfaction";
            } else if (p >= 50) {
                mention = "Passable";
            } else if (p >= 45) {
                mention = "Insatisfait";
            } else if (p >= 35) {
                mention = "Tres insuffisant";
            } else {
                mention = "Mediocre";
            }
        } else {
            mention = "Non Concernee";
        }
        return mention;
    }

    public String testSemestre(int sousDix) {
        String test = null;
        if (sousDix == 0) {
            test = "SV";
        } else {
            test = "SNV";
        }
        return test;
    }

    public String testUE(Double mean) {
        String test = null;
        if (mean >= 10) {
            test = "UV";
        } else {
            test = "UNV";
            nombreDesCoursNoteEstSousDix = nombreDesCoursNoteEstSousDix + 1;//Nombre d'UE ayant une note sous 10
        }
        return test;
    }

    public List<Cours> listeCoursDansUneUE(Long a) {
        listeCoursDansUnite = coursDAO.selectCoursByUnite(a);
        nombreCoursDansUneUE = listeCoursDansUnite.size();
        return listeCoursDansUnite;
    }

    public List<Unite> listeUEDansSemestre(Long a, Long b) {
        listeUEDansUneClasseSemestre = uniteDAO.selectByClasseAndSemestre(a, b);
        nombreUEDansSemestre = listeUEDansUneClasseSemestre.size();
        nombreUEDansUneAnnee = nombreUEDansUneAnnee + nombreUEDansSemestre;
        return listeUEDansUneClasseSemestre;
    }

    public String listeEtudiantInscrit() {
        System.out.println("----------Affichage Etudiant dans une grille de delibaertions--------------");
//        listeInscriptionClasseAnneeSession = new ArrayList<>();
        listeInscriptionClasseAnneeSession = dao.findInscriptionBy3Id(newNote.getCl().getId(), newNote.getPromo().getId(), newNote.getSess().getId());
        for (Inscription i : listeInscriptionClasseAnneeSession) {
            System.out.println(i.getEt());
        }
        nombreEtudiantInscritDansUneClasse = listeInscriptionClasseAnneeSession.size();
        System.out.println("Taille etudiant inscrit " + nombreEtudiantInscritDansUneClasse);
        return "/Vue/Operation/Deliberation/GrilleEtudiant?faces-redirect=true";
    }

    public List<Attribution> listeCoursDansGrille() {
        System.out.println("----------Affichage cours dans une grille de delibaertions--------------");
//        listeAttributionClasseAnneeSession = new ArrayList<>();
        listeAttributionClasseAnneeSession = dao.findAttributionBy3Id(newNote.getCl().getId(), newNote.getPromo().getId(), newNote.getSess().getId());
        for (Attribution a : listeAttributionClasseAnneeSession) {
            System.out.println(a);
        }
        nombreCoursDansUneClasse = listeAttributionClasseAnneeSession.size();
        System.out.println("Taille cours " + nombreCoursDansUneClasse);
        return listeAttributionClasseAnneeSession;
    }
//fin de l'implemetation

    public void clickOnUniteObjectActiveCours() {
        System.out.println(activeCours);
        activeCours = false;
        System.out.println(activeCours);
    }

    public void loadAttribution() {
        att = attribDAO.selectById(postAttribution);
        newNote.setAttrib(att); //
        System.out.println(postSession + " les valeurs dans le URL " + postAttribution);
        System.out.println(attribDAO.selectById(postAttribution));
        classe = att.getCourse().getCodeUnite().getClasseCode().getNomClasse();
        cours = att.getCourse().getNomCours();
        professeur = att.getProfessor().getNomProfesseur() + "" + att.getProfessor().getPrenomProfesseur();
        annee = att.getAnnee().getPromotion();
        Long idClasse = att.getCourse().getCodeUnite().getClasseCode().getId();
        newNote.setCl(classeDAO.selectById(idClasse));
        Long idAnnee = att.getAnnee().getId();
        newNote.setPromo(anneDAO.selectById(idAnnee));
        Long idSession = this.postSession;
        newNote.setSess(sessionDAO.selectById(idSession));

        System.out.println("affiiche les ids " + idClasse + "  " + idAnnee + "   " + idSession);
//        selectEtudiantInscrit = inscrDAO.selectByClasseAndSessionAndPromotion(idClasse, idSession, idAnnee);
        selectEtudiantInscrit = new ArrayList<>();
        List<Inscription> in = inscrDAO.selectByClasseAndSessionAndPromotion(idClasse, idSession, idAnnee);
        try {
            for (Inscription i : in) {
                Inscription insc = new Inscription();
                insc = i;
//                dao.findByAllId(insc.getId(), att.getId(), idClasse, idAnnee, idSession);
                System.out.println(insc);
                System.out.println("deja evalue " + dao.findByAllId(insc.getId(), att.getId(), idClasse, idAnnee, idSession));
                System.out.println("------ " + insc.getId() + "  " + att.getId() + "   " + idClasse + "   " + idAnnee + "   " + idSession);
                if (dao.findByAllId(insc.getId(), att.getId(), idClasse, idAnnee, idSession).isEmpty()) {
                    selectEtudiantInscrit.add(insc);
                } else {
//                    in.remove(insc);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("tous les etudiants" + inscrDAO.selectByClasseAndSessionAndPromotion(idClasse, idSession, idAnnee).size());
        System.out.println("Etudiant non evalue" + selectEtudiantInscrit.size());
//        System.out.println(inscrDAO.selectAllInscription());
    }

    public String createNote() {
        System.out.println("Avant l'enregistrement, liste des etudiants " + selectEtudiantInscrit);
        System.out.println("test de la liste temporelle avant l'enregistrement" + listeTemporelleNote);
        try {
            for (Note n : listeTemporelleNote) {
                newNote = new Note();
                newNote = n;
                dao.InsertNote(newNote);
                newNote = new Note();
            }
            listeTemporelleNote = new ArrayList<>();
            newNote = new Note();
            newNote.setInsc(new Inscription());
            newNote.setAttrib(new Attribution());
            newNote.setCl(new Classe());
            newNote.setPromo(new Promotion());
            newNote.setSess(new TypeInscription());
            return "/Vue/Operation/Evaluation/View?faces-redirect=true";
        } catch (Exception e) {
            return "/Vue/Operation/Evaluation/Error?faces-redirect=true";
        }

    }

    public String generationGrille() {
        int nombreCredit = 0;
        int creditUnite, i = 0;
        List<Note> listeNote = new ArrayList<>();
        listeAttributionClasseAnneeSession = new ArrayList<>();
        listeAttributionClasseAnneeSession = new ArrayList<>();
        listeNoteClasseAnneeSession = new ArrayList<>();
        listeUniteEnseignementDansUneClasseSemestre1 = new ArrayList<>();
        listeUniteEnseignementDansUneClasseSemestre2 = new ArrayList<>();
        listeNoteInscriptionClasseAnneeSession = new ArrayList<>();
        listeInscriptionClasseAnneeSession = new ArrayList<>();
        listeCoursDansUniteSemestre1 = new ArrayList<>();
        listeNoteInscriptionClasseAnneeSession = new ArrayList<>();
        System.out.println(this.idClasse_grille + " " + this.idAnne_Grille + " " + this.idSession_Grille);
        listeAttributionClasseAnneeSession = dao.findAttributionBy3Id(idClasse_grille, idAnne_Grille, idSession_Grille);
        listeInscriptionClasseAnneeSession = dao.findInscriptionBy3Id(idClasse_grille, idAnne_Grille, idSession_Grille);
        listeNoteClasseAnneeSession = dao.findBy3Id(idClasse_grille, idAnne_Grille, idSession_Grille);
        listeUniteEnseignementDansUneClasseSemestre1 = uniteDAO.selectByClasseAndSemestre(idClasse_grille, 1L);
        taileUESemestre1 = listeUniteEnseignementDansUneClasseSemestre1.size();
        listeUniteEnseignementDansUneClasseSemestre2 = uniteDAO.selectByClasseAndSemestre(idClasse_grille, 2L);
        taileUESemestre2 = listeUniteEnseignementDansUneClasseSemestre2.size();

        for (Unite u : listeUniteEnseignementDansUneClasseSemestre1) {
            creditUnite = 0;
            for (Cours c : coursDAO.selectCoursByUnite(u.getId())) {
                listeCoursDansUniteSemestre1.add(c);
                nombreCredit = nombreCredit + c.getCodeCredit().getCredit();
                maximaSemestriel = nombreCredit * 20;
                creditUnite = creditUnite + c.getCodeCredit().getCredit();
                System.out.println("---Maxima semestre---" + maximaSemestriel);
            }
            tableauCreditUnite[i] = creditUnite;
            System.out.println("---credit unite---" + tableauCreditUnite[i]);
            i++;
        }

        for (Unite u : listeUniteEnseignementDansUneClasseSemestre2) {
            listeCoursDansUniteSemestre2 = coursDAO.selectCoursByUnite(u.getId());
        }

        for (Inscription in : listeInscriptionClasseAnneeSession) {
            System.out.println(in);
            listeNote = dao.findNoteBy4Id(in.getId(), idClasse_grille, idAnne_Grille, idSession_Grille);
            for (Note n : listeNote) {
                listeNoteInscriptionClasseAnneeSession.add(n);
            }
//            System.out.println("************--***" + in.getEt().getPrenomEtudiant() + " a comme note" + listeNoteInscriptionClasseAnneeSession);
        }

        for (Note n : listeNoteInscriptionClasseAnneeSession) {
            System.out.println("************--*** a comme note" + n);
        }

        for (Attribution a : listeAttributionClasseAnneeSession) {
            System.out.println(a);
            a.getCourse().getCodeUnite().getId();

        }

        return "/Vue/Operation/Deliberation/GenerationGrille?faces-redirect=true";
    }

    public void chargerComboFiche() {
        HttpSession sess = ControleurConnection.findSessionUtilisateur();
        Long id = (Long) sess.getAttribute("idProfesseur");
        findClasseByProfesseur = new ArrayList<>();
        findAnneByProfesseur = new ArrayList<>();
        findSessionByProfesseur = new ArrayList<>();
        findCoursByProfesseur = new ArrayList<>();

        findClasseByProfesseur = dao.findClasseByProfesseur(id);
        findAnneByProfesseur = dao.findAnneByProfesseur(id);
        findSessionByProfesseur = dao.findSessionByProfesseur(id);
        findCoursByProfesseur = dao.findCoursByProfesseur(id);
    }

    public String consulterFiche() {
        listeConsulterFicheDePoints = dao.findNoteFicheBy4Id(newNote.getCl().getId(), newNote.getPromo().getId(), newNote.getSess().getId(), newNote.getAttrib().getCourse().getId());
        System.out.println(listeConsulterFicheDePoints);
        return "/Vue/Operation/Evaluation/ViewFiche?faces-redirect=true";
    }

    public void exportGrilleExcel() {
        System.out.println("Export en excel");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        String reportName = "Grille";
        response.setHeader("Content-disposition", "attachment;filename=" + reportName);
        ArrayList<String> colonne = new ArrayList<String>();

        colonne.add("\t");
        colonne.add("Universite du burundi");
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Faculte de Science de l'Ingenieur");
        colonne.add("\n");
        colonne.add("\t");

        if (listeAttributionClasseAnneeSession != null && listeInscriptionClasseAnneeSession != null) {
            for (Attribution a : listeAttributionClasseAnneeSession) {
                colonne.add("\t");
                colonne.add(String.valueOf(a.getCourse().getNomCours()));
            }
            colonne.add("\n");
            for (Inscription i : listeInscriptionClasseAnneeSession) {
                colonne.add("\t");
                colonne.add(String.valueOf(i.getEt().getNomEtudiant() + "" + i.getEt().getPrenomEtudiant()));
                
                 for (Attribution a : listeAttributionClasseAnneeSession) {
                    colonne.add("\t");
                    colonne.add(String.valueOf(a.getCourse().getCodeCredit().getCredit()));
                }                 
                 colonne.add("\n");
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
        }
        System.out.println("-----++++++++++++++++++++++++++++Grille de deliberation en excel++-----------------------+++++++++++++++++");

        try {
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createFicheExcel() {
        System.out.println("Export en excel");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        String reportName = listeConsulterFicheDePoints.get(0).getAttrib().getCourse().getNomCours() + "_Fiche de points";
        response.setHeader("Content-disposition", "attachment;filename=" + reportName);
        ArrayList<String> colonne = new ArrayList<String>();

        colonne.add("\t");
        colonne.add("Universite du burundi");
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Faculte de Science de l'Ingenieur");
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Departement" + "  " + listeConsulterFicheDePoints.get(0).getCl().getCodeOption().getCodeDepartement().getDepartement());
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Option" + "  " + listeConsulterFicheDePoints.get(0).getCl().getCodeOption().getNomOption());
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Classe" + "  " + listeConsulterFicheDePoints.get(0).getCl().getNomClasse());
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Semestre" + "  " + listeConsulterFicheDePoints.get(0).getAttrib().getCourse().getCodeUnite().getSemestreCode().getSemestre());
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("Classe" + "  " + listeConsulterFicheDePoints.get(0).getSess().getType());
        colonne.add("\n");
        colonne.add("\t");
        colonne.add("\n");
        colonne.add("\t");

        colonne.add("Palmales du cours de " + "  " + listeConsulterFicheDePoints.get(0).getAttrib().getCourse().getNomCours());
        colonne.add("\n");
        colonne.add("\n");

        colonne.add("\t");
        colonne.add("Matricule");
        colonne.add("\t");
        colonne.add("Nom");
        colonne.add("\t");
        colonne.add("Prenom");
        colonne.add("\t");
        colonne.add("Note /20");
        colonne.add("\n");
//        List<Inscription> listeExcel = new ArrayList<>();

        System.out.println("---Export en excel++++" + listeConsulterFicheDePoints);

        if (listeConsulterFicheDePoints != null) {
            try {
                for (int i = 0; i < listeConsulterFicheDePoints.size(); i++) {
                    colonne.add("\t");
                    colonne.add(String.valueOf(listeConsulterFicheDePoints.get(i).getInsc().getEt().getMatriculeEtudiant()));
                    colonne.add("\t");
                    colonne.add(String.valueOf(listeConsulterFicheDePoints.get(i).getInsc().getEt().getNomEtudiant()));
                    colonne.add("\t");
                    colonne.add(String.valueOf(listeConsulterFicheDePoints.get(i).getInsc().getEt().getPrenomEtudiant()));
                    colonne.add("\t");
                    colonne.add(String.valueOf(listeConsulterFicheDePoints.get(i).getNote()));
                    colonne.add("\n");
                }
                colonne.add("\n");
                colonne.add("\t");
                colonne.add("\t");
                colonne.add("\t");
                colonne.add("Fait a Bujumbura, le");
                colonne.add("\n");
                colonne.add("\t");
                colonne.add("\t");
                colonne.add("\t");
                colonne.add(listeConsulterFicheDePoints.get(0).getAttrib().getProfessor().getCodeGrade().getCode()
                        + " " + listeConsulterFicheDePoints.get(0).getAttrib().getProfessor().getNomProfesseur()
                        + " " + listeConsulterFicheDePoints.get(0).getAttrib().getProfessor().getPrenomProfesseur());
                colonne.add("\n");
                colonne.add("\t");
                colonne.add("\t");
                colonne.add("\t");
                colonne.add("Titulaire du cours");

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

        }
        System.out.println("-----++++++++++++++++++++++++++++Excel++-----------------------+++++++++++++++++");

        try {
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String createListeTemporelleNote() {
//        loadAttribution();
        //-----------------------------------------------------------
        att = attribDAO.selectById(postAttribution);
        newNote.setAttrib(att); //
        System.out.println(postSession + " les valeurs dans le URL " + postAttribution);
        System.out.println(attribDAO.selectById(postAttribution));
        classe = att.getCourse().getCodeUnite().getClasseCode().getNomClasse();
        cours = att.getCourse().getNomCours();
        professeur = att.getProfessor().getNomProfesseur() + "" + att.getProfessor().getPrenomProfesseur();
        annee = att.getAnnee().getPromotion();
        Long idClasse = att.getCourse().getCodeUnite().getClasseCode().getId();
        newNote.setCl(classeDAO.selectById(idClasse));
        Long idAnnee = att.getAnnee().getId();
        newNote.setPromo(anneDAO.selectById(idAnnee));
        Long idSession = this.postSession;
        newNote.setSess(sessionDAO.selectById(idSession));
        //-----------------------------------------------------------

        newNote.setInsc(inscrDAO.selectById(newNote.getInsc().getId()));
        listeTemporelleNote.add(newNote);
        System.out.println("afficher id de l'inscrit" + newNote.getInsc().getId());
        selectEtudiantInscrit.remove(inscrDAO.selectById(newNote.getInsc().getId()));
        System.out.println("verification de la liste" + listeTemporelleNote);
        newNote = new Note();
        newNote.setInsc(new Inscription());
        newNote.setAttrib(new Attribution());
        newNote.setCl(new Classe());
        newNote.setPromo(new Promotion());
        newNote.setSess(new TypeInscription());
        System.out.println("second, verification de la liste" + listeTemporelleNote);
        return "/Vue/Add2?faces-redirect=true";
    }

    public List<Note> getListeTemporelleNote() {

        return listeTemporelleNote;
    }

    public void setListeTemporelleNote(List<Note> listeTemporelleNote) {
        this.listeTemporelleNote = listeTemporelleNote;
    }

    public DataModel getNotes() {
        if (notes == null) {
            notes = new ListDataModel();
            notes.setWrappedData(dao.selectAllNote());
        } else {
            System.out.println("liste non vide");
        }
        return notes;
    }

    public void setNotes(DataModel notes) {
        this.notes = notes;
    }

    public Note getNewNote() {
        return newNote;
    }

    public void setNewNote(Note newNote) {
        this.newNote = newNote;
    }

    public NoteDAO getDao() {
        return dao;
    }

    public void setDao(NoteDAO dao) {
        this.dao = dao;
    }

    public ImplementUnite getUniteDAO() {
        return uniteDAO;
    }

    public void setUniteDAO(ImplementUnite uniteDAO) {
        this.uniteDAO = uniteDAO;
    }

    public ImplementClasse getClasseDAO() {
        return classeDAO;
    }

    public void setClasseDAO(ImplementClasse classeDAO) {
        this.classeDAO = classeDAO;
    }

    public boolean isActiveUnite() {
        return activeUnite;
    }

    public void setActiveUnite(boolean activeUnite) {
        this.activeUnite = activeUnite;
    }

    public boolean isActiveCours() {
        return activeCours;
    }

    public void setActiveCours(boolean activeCours) {
        this.activeCours = activeCours;
    }

    public List<Unite> getListeUnitesByIdClasse() {
        return listeUnitesByIdClasse;
    }

    public void setListeUnitesByIdClasse(List<Unite> listeUnitesByIdClasse) {
        this.listeUnitesByIdClasse = listeUnitesByIdClasse;
    }

    public Long getPostAttribution() {
        return postAttribution;
    }

    public void setPostAttribution(Long postAttribution) {
        this.postAttribution = postAttribution;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Long getPostSession() {
        return postSession;
    }

    public void setPostSession(Long postSession) {
        this.postSession = postSession;
    }

    public List<Inscription> getSelectEtudiantInscrit() {
        return selectEtudiantInscrit;
    }

    public void setSelectEtudiantInscrit(List<Inscription> selectEtudiantInscrit) {
        this.selectEtudiantInscrit = selectEtudiantInscrit;
    }

    public String getEtatCours() {
        return etatCours;
    }

    public void setEtatCours(String etatCours) {
        this.etatCours = etatCours;
    }

    public Long getIdClasse_grille() {
        return idClasse_grille;
    }

    public void setIdClasse_grille(Long idClasse_grille) {
        this.idClasse_grille = idClasse_grille;
    }

    public Long getIdSession_Grille() {
        return idSession_Grille;
    }

    public void setIdSession_Grille(Long idSession_Grille) {
        this.idSession_Grille = idSession_Grille;
    }

    public Long getIdAnne_Grille() {
        return idAnne_Grille;
    }

    public void setIdAnne_Grille(Long idAnne_Grille) {
        this.idAnne_Grille = idAnne_Grille;
    }

    public List<Note> getListeNoteClasseAnneeSession() {
        return listeNoteClasseAnneeSession;
    }

    public void setListeNoteClasseAnneeSession(List<Note> listeNoteClasseAnneeSession) {
        this.listeNoteClasseAnneeSession = listeNoteClasseAnneeSession;
    }

    public List<Etudiant> getListeEtudiantEvalue() {
        return listeEtudiantEvalue;
    }

    public void setListeEtudiantEvalue(List<Etudiant> listeEtudiantEvalue) {
        this.listeEtudiantEvalue = listeEtudiantEvalue;
    }

    public List<Cours> getListeCoursEvalue() {
        return listeCoursEvalue;
    }

    public void setListeCoursEvalue(List<Cours> listeCoursEvalue) {
        this.listeCoursEvalue = listeCoursEvalue;
    }

    public List<Attribution> getListeAttributionClasseAnneeSession() {
        return listeAttributionClasseAnneeSession;
    }

    public void setListeAttributionClasseAnneeSession(List<Attribution> listeAttributionClasseAnneeSession) {
        this.listeAttributionClasseAnneeSession = listeAttributionClasseAnneeSession;
    }

    public List<Inscription> getListeInscriptionClasseAnneeSession() {
        return listeInscriptionClasseAnneeSession;
    }

    public void setListeInscriptionClasseAnneeSession(List<Inscription> listeInscriptionClasseAnneeSession) {
        this.listeInscriptionClasseAnneeSession = listeInscriptionClasseAnneeSession;
    }

    public List<Unite> getListeUniteEnseignementDansUneClasseSemestre1() {
        return listeUniteEnseignementDansUneClasseSemestre1;
    }

    public void setListeUniteEnseignementDansUneClasseSemestre1(List<Unite> listeUniteEnseignementDansUneClasseSemestre1) {
        this.listeUniteEnseignementDansUneClasseSemestre1 = listeUniteEnseignementDansUneClasseSemestre1;
    }

    public int getTaileUESemestre1() {
        return taileUESemestre1;
    }

    public void setTaileUESemestre1(int taileUESemestre1) {
        this.taileUESemestre1 = taileUESemestre1;
    }

    public List<Unite> getListeUniteEnseignementDansUneClasseSemestre2() {
        return listeUniteEnseignementDansUneClasseSemestre2;
    }

    public void setListeUniteEnseignementDansUneClasseSemestre2(List<Unite> listeUniteEnseignementDansUneClasseSemestre2) {
        this.listeUniteEnseignementDansUneClasseSemestre2 = listeUniteEnseignementDansUneClasseSemestre2;
    }

    public int getTaileUESemestre2() {
        return taileUESemestre2;
    }

    public void setTaileUESemestre2(int taileUESemestre2) {
        this.taileUESemestre2 = taileUESemestre2;
    }

    public List<Cours> getListeCoursDansUniteSemestre1() {
        return listeCoursDansUniteSemestre1;
    }

    public void setListeCoursDansUniteSemestre1(List<Cours> listeCoursDansUniteSemestre1) {
        this.listeCoursDansUniteSemestre1 = listeCoursDansUniteSemestre1;
    }

    public List<Long> getListeCreditCoursSemestre1() {
        return listeCreditCoursSemestre1;
    }

    public void setListeCreditCoursSemestre1(List<Long> listeCreditCoursSemestre1) {
        this.listeCreditCoursSemestre1 = listeCreditCoursSemestre1;
    }

    public List<Cours> getListeCoursDansUniteSemestre2() {
        return listeCoursDansUniteSemestre2;
    }

    public void setListeCoursDansUniteSemestre2(List<Cours> listeCoursDansUniteSemestre2) {
        this.listeCoursDansUniteSemestre2 = listeCoursDansUniteSemestre2;
    }

    public List<Long> getListeCreditCoursSemestre2() {
        return listeCreditCoursSemestre2;
    }

    public void setListeCreditCoursSemestre2(List<Long> listeCreditCoursSemestre2) {
        this.listeCreditCoursSemestre2 = listeCreditCoursSemestre2;
    }

    public int getMaximaSemestriel() {
        return maximaSemestriel;
    }

    public void setMaximaSemestriel(int maximaSemestriel) {
        this.maximaSemestriel = maximaSemestriel;
    }

    public int[] getTableauCreditUnite() {
        return tableauCreditUnite;
    }

    public void setTableauCreditUnite(int[] tableauCreditUnite) {
        this.tableauCreditUnite = tableauCreditUnite;
    }

    public List<Note> getListeNoteInscriptionClasseAnneeSession() {
        return listeNoteInscriptionClasseAnneeSession;
    }

    public void setListeNoteInscriptionClasseAnneeSession(List<Note> listeNoteInscriptionClasseAnneeSession) {
        this.listeNoteInscriptionClasseAnneeSession = listeNoteInscriptionClasseAnneeSession;
    }

    public List<Note> getListeConsulterFicheDePoints() {
        return listeConsulterFicheDePoints;
    }

    public void setListeConsulterFicheDePoints(List<Note> listeConsulterFicheDePoints) {
        this.listeConsulterFicheDePoints = listeConsulterFicheDePoints;
    }

    public List<Classe> getFindClasseByProfesseur() {
        return findClasseByProfesseur;
    }

    public void setFindClasseByProfesseur(List<Classe> findClasseByProfesseur) {
        this.findClasseByProfesseur = findClasseByProfesseur;
    }

    public List<Promotion> getFindAnneByProfesseur() {
        return findAnneByProfesseur;
    }

    public void setFindAnneByProfesseur(List<Promotion> findAnneByProfesseur) {
        this.findAnneByProfesseur = findAnneByProfesseur;
    }

    public List<TypeInscription> getFindSessionByProfesseur() {
        return findSessionByProfesseur;
    }

    public void setFindSessionByProfesseur(List<TypeInscription> findSessionByProfesseur) {
        this.findSessionByProfesseur = findSessionByProfesseur;
    }

    public List<Cours> getFindCoursByProfesseur() {
        return findCoursByProfesseur;
    }

    public void setFindCoursByProfesseur(List<Cours> findCoursByProfesseur) {
        this.findCoursByProfesseur = findCoursByProfesseur;
    }

    public int getNombreCoursDansUneClasse() {
        return nombreCoursDansUneClasse;
    }

    public void setNombreCoursDansUneClasse(int nombreCoursDansUneClasse) {
        this.nombreCoursDansUneClasse = nombreCoursDansUneClasse;
    }

    public int getNombreEtudiantInscritDansUneClasse() {
        return nombreEtudiantInscritDansUneClasse;
    }

    public void setNombreEtudiantInscritDansUneClasse(int nombreEtudiantInscritDansUneClasse) {
        this.nombreEtudiantInscritDansUneClasse = nombreEtudiantInscritDansUneClasse;
    }

    public List<Unite> getListeUEDansUneClasseSemestre() {
        return listeUEDansUneClasseSemestre;
    }

    public void setListeUEDansUneClasseSemestre(List<Unite> listeUEDansUneClasseSemestre) {
        this.listeUEDansUneClasseSemestre = listeUEDansUneClasseSemestre;
    }

    public int getNombreUEDansSemestre() {
        return nombreUEDansSemestre;
    }

    public void setNombreUEDansSemestre(int nombreUEDansSemestre) {
        this.nombreUEDansSemestre = nombreUEDansSemestre;
    }

    public int getNombreUEDansUneAnnee() {
        return nombreUEDansUneAnnee;
    }

    public void setNombreUEDansUneAnnee(int nombreUEDansUneAnnee) {
        this.nombreUEDansUneAnnee = nombreUEDansUneAnnee;
    }

    public List<Cours> getListeCoursDansUnite() {
        return listeCoursDansUnite;
    }

    public void setListeCoursDansUnite(List<Cours> listeCoursDansUnite) {
        this.listeCoursDansUnite = listeCoursDansUnite;
    }

    public int getNombreCoursDansUneUE() {
        return nombreCoursDansUneUE;
    }

    public void setNombreCoursDansUneUE(int nombreCoursDansUneUE) {
        this.nombreCoursDansUneUE = nombreCoursDansUneUE;
    }

    public int getCreditDuCours() {
        return creditDuCours;
    }

    public void setCreditDuCours(int creditDuCours) {
        this.creditDuCours = creditDuCours;
    }

    public Double getNoteRecuDansUnCours() {
        return noteRecuDansUnCours;
    }

    public void setNoteRecuDansUnCours(Double noteRecuDansUnCours) {
        this.noteRecuDansUnCours = noteRecuDansUnCours;
    }

    public Note getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Note evaluation) {
        this.evaluation = evaluation;
    }

    public Double getPointsObtenuSurCreditDansUnCours() {
        return pointsObtenuSurCreditDansUnCours;
    }

    public void setPointsObtenuSurCreditDansUnCours(Double pointsObtenuSurCreditDansUnCours) {
        this.pointsObtenuSurCreditDansUnCours = pointsObtenuSurCreditDansUnCours;
    }

    public Double getPointsTotalObtenuDansUneUE() {
        return pointsTotalObtenuDansUneUE;
    }

    public void setPointsTotalObtenuDansUneUE(Double pointsTotalObtenuDansUneUE) {
        this.pointsTotalObtenuDansUneUE = pointsTotalObtenuDansUneUE;
    }

    public int getNombreCreditDansUneUE() {
        return nombreCreditDansUneUE;
    }

    public void setNombreCreditDansUneUE(int nombreCreditDansUneUE) {
        this.nombreCreditDansUneUE = nombreCreditDansUneUE;
    }

    public int getNombreCreditDansUnSemestre() {
        return nombreCreditDansUnSemestre;
    }

    public void setNombreCreditDansUnSemestre(int nombreCreditDansUnSemestre) {
        this.nombreCreditDansUnSemestre = nombreCreditDansUnSemestre;
    }

    public Double getPointsObtenuDansUnSemestre() {
        return pointsObtenuDansUnSemestre;
    }

    public void setPointsObtenuDansUnSemestre(Double pointsObtenuDansUnSemestre) {
        this.pointsObtenuDansUnSemestre = pointsObtenuDansUnSemestre;
    }

    public Double getMoyenneSur20UE() {
        return moyenneSur20UE;
    }

    public void setMoyenneSur20UE(Double moyenneSur20UE) {
        this.moyenneSur20UE = moyenneSur20UE;
    }

    public String getTestValidationUE() {
        return testValidationUE;
    }

    public void setTestValidationUE(String testValidationUE) {
        this.testValidationUE = testValidationUE;
    }

    public int getNombreDesCoursNoteEstSousDix() {
        return nombreDesCoursNoteEstSousDix;
    }

    public void setNombreDesCoursNoteEstSousDix(int nombreDesCoursNoteEstSousDix) {
        this.nombreDesCoursNoteEstSousDix = nombreDesCoursNoteEstSousDix;
    }

    public Double getPointsSur20DansUnSemestre() {
        return pointsSur20DansUnSemestre;
    }

    public void setPointsSur20DansUnSemestre(Double pointsSur20DansUnSemestre) {
        this.pointsSur20DansUnSemestre = pointsSur20DansUnSemestre;
    }

    public Double getPointsObtenuDansUneAnnee() {
        return pointsObtenuDansUneAnnee;
    }

    public void setPointsObtenuDansUneAnnee(Double pointsObtenuDansUneAnnee) {
        this.pointsObtenuDansUneAnnee = pointsObtenuDansUneAnnee;
    }

    public Double getPourcentageDansUnSemestre() {
        return pourcentageDansUnSemestre;
    }

    public void setPourcentageDansUnSemestre(Double pourcentageDansUnSemestre) {
        this.pourcentageDansUnSemestre = pourcentageDansUnSemestre;
    }

    public int getNombreDeCreditDansUneAnnee() {
        return nombreDeCreditDansUneAnnee;
    }

    public void setNombreDeCreditDansUneAnnee(int nombreDeCreditDansUneAnnee) {
        this.nombreDeCreditDansUneAnnee = nombreDeCreditDansUneAnnee;
    }

    public String getTestValidationSemestre() {
        return testValidationSemestre;
    }

    public void setTestValidationSemestre(String testValidationSemestre) {
        this.testValidationSemestre = testValidationSemestre;
    }

    public int getNombreDesUEMoyenneEstSousDix() {
        return nombreDesUEMoyenneEstSousDix;
    }

    public void setNombreDesUEMoyenneEstSousDix(int nombreDesUEMoyenneEstSousDix) {
        this.nombreDesUEMoyenneEstSousDix = nombreDesUEMoyenneEstSousDix;
    }

    public Double getMoyenneAnnuelle() {
        return moyenneAnnuelle;
    }

    public void setMoyenneAnnuelle(Double moyenneAnnuelle) {
        this.moyenneAnnuelle = moyenneAnnuelle;
    }

    public Double getPourcentageAnnuel() {
        return pourcentageAnnuel;
    }

    public void setPourcentageAnnuel(Double pourcentageAnnuel) {
        this.pourcentageAnnuel = pourcentageAnnuel;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getNombreCoursNonFaitParUnEtudiant() {
        return nombreCoursNonFaitParUnEtudiant;
    }

    public void setNombreCoursNonFaitParUnEtudiant(int nombreCoursNonFaitParUnEtudiant) {
        this.nombreCoursNonFaitParUnEtudiant = nombreCoursNonFaitParUnEtudiant;
    }

}
