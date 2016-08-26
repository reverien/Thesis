//package user;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
//import javax.faces.model.SelectItem;
//import javax.servlet.http.HttpSession;
//
//
//
//public class session implements Serializable {
//	
//	private String login;
//	private String passWd;
//	private boolean connected;
//	private String message = "Veuillez vous identifier :";
//	private String info;
//	private String infoConnexion;
//	private String role;
//	private String nom;
//	private String prenom;
//	private String email;
//	//private String status;
//	
//	
//	private boolean modif=true;
//	private boolean supprim=true;
//	private boolean ajoutPrix=true;
//	private boolean ajoutProduit=true;
//	private boolean ajoutClient=true;
//	private boolean ajoutEmploye=true;
//	private boolean fourniture=true;
//	private boolean consommation=true;
//	private String etatUtilisateur;
//	
//	
//	public String getEtatUtilisateur() {
//		return etatUtilisateur;
//	}
//
//
//
//	public void setEtatUtilisateur(String etatUtilisateur) {
//		this.etatUtilisateur = etatUtilisateur;
//	}
//	private List<session> listEmploye;
//	
//	private int sexedId;
//	private List<SelectItem> listSexes;
//	
//	private String  rePassword;
//	
//	private String confirmePassword;
//	
//	
//public String getConfirmePassword() {
//		return confirmePassword;
//	}
//
//
//
//	public void setConfirmePassword(String confirmePassword) {
//		this.confirmePassword = confirmePassword;
//	}
//
//
//
//
//public String getRePassword() {
//		return rePassword;
//	}
//
//
//
//	public void setRePassword(String rePassword) {
//		this.rePassword = rePassword;
//	}
//
//
//
//
//
//public String addUser(){
//		
//		
//	connectDatabase connect=new connectDatabase();
//		
//		
//	if(this.login==null||this.login.equals("")){
//			
//		this.info="Tapez le login!";
//		
//		return null;
//	}
//	
//	
//	
//	
//	if(this.passWd==null||this.passWd.equals("")){
//			
//			this.info="Tapez le mot de passe!";
//			return null;
//		}
//		
//	if(this.rePassword==null||this.rePassword.equals("")){
//		
//		this.info="Confirmer votre mot de passe!";
//		return null;
//	}
//	
//	
//	
//	if(this.rePassword.equals(this.passWd)){
//		
//		
//	}
//
//	else {
//		  this.info="Votre mot de passe est incorrect!";
//		return null;
//	}
//	
//	if(existeLogin(login))
//	{
//		this.info="Login existe deja ..";
//		//System.out.println("vv    : "+verificationLogin);
//		verificationLogin=false;
//		return null;
//	}
//	
//		connect.updateData("insert into employesystem(idemploye,login,password,etat)values("+this.idEmploye+",'"+this.login+"','"+getEncodedPassword(this.passWd.trim())+"','Valide')");
//		initialiserdonnees();
//	
//		this.info="Insertion reussi ...";
//	  return null;
//		
//	}
//
//boolean  existeLogin(String loginn)
//{
//	connectDatabase conn=new connectDatabase();
//	ResultSet res=conn.extaireData("select *  from employeSystem  where login='"+loginn+"' ");
//	
//	System.out.println("Verifie Login : "+"select *  from employeSystem  where login='"+loginn+"' ");
//	
//	
//	
//	if(res !=null)
//		try {
//			while(res.next())
//			{
//				System.out.println(res.getString("login")+"  "+res.getString("etat"));
//				verificationLogin=true;
//			}
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//
//
//return verificationLogin;
//}
//
//
//private boolean  verificationLogin=false;
//	
//public boolean isVerificationLogin() {
//	return verificationLogin;
//}
//
//
//
//public void setVerificationLogin(boolean verificationLogin) {
//	this.verificationLogin = verificationLogin;
//}
//private List<SelectItem>  selectEmploys =new ArrayList<SelectItem>();
//
//
//
//public List<SelectItem> getSelectEmploys() {
//	
//	connectDatabase conn=new connectDatabase();
//	
//	ResultSet res= conn.extaireData("select *  from employe where  roleEmploye <>'Bar Man' and roleEmploye <>'Restaurant' order by idemploye asc");
//	if(res !=null )
//		try {
//			this.selectEmploys.add(new SelectItem(0,"--Choix Employe--"));
//			while(res.next())
//			{
//			this.selectEmploys.add(new SelectItem(res.getInt("IdEmploye"),res.getString("nomEmploye")+"  "+res.getString("prenomEmploye")));	
//				
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	
//	
//	
//	return selectEmploys;
//}
//
//
//
//public String modifierUtilisateur(){
//	
//	connectDatabase connect=new connectDatabase();
//	
//	
//	if(this.login==null||this.login.equals("")){
//		this.info="Tapez le login!";
//		return null;
//	}
//	
//	if(this.passWd==null||this.passWd.equals("")){
//		this.info="Tapez l'ancien mot de passe!";
//		return null;
//	}
//	if(this.rePassword==null||this.rePassword.equals("")){
//		this.info="Tapez le nouveau mot de passe!";
//		return null;
//	}
//	if(this.confirmePassword==null||this.confirmePassword.equals("")){
//		this.info="Confirmer votre mot de passe!";
//		return null;
//	}
//	
//	if(!this.rePassword.equals(this.confirmePassword)){
//		
//		this.info="Mot de passe confirmé ne correspond au nouveau!";
//		return null;
//	}
//	
//
//	HttpSession session=LoginUtilisateur.getSessionUtilisateur();
//	
//	         connect.updateData("update employeSystem set password ='"+this.getEncodedPassword(this.rePassword).trim()+"' where login = '"+session.getAttribute("login"));
//	         this.info=connect.getState();
//			
//	
//	return null;
//	
//}
//
//public String dd(){
//	((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
//		//return "Quitter";
//	
//	 return "/identification.xhtml?faces-redirect=true";
//}
//
//
//
///*
//public String logout() {
//    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//    return "/home.xhtml?faces-redirect=true";
//}
//*/
//
//public static HttpSession getSessionUtilisateur()
//{
//	FacesContext fcontext= FacesContext.getCurrentInstance();
//	
//	return (HttpSession) fcontext.getExternalContext().getSession(true);
//}
//
//
//	public void initialiserdonnees()
//	{
//		this.nom=this.prenom=this.login=this.passWd=this.rePassword=this.role=this.login=null;
//	}
//	
//	
//	public String detecterNoNullPointerException(){
//			
//			FacesContext fcontext = FacesContext.getCurrentInstance();
//			HttpSession session = (HttpSession) fcontext.getExternalContext().getSession(true);
//			
//			String log="Quitter";
//			
//			try{
//				if(session.getAttribute("login").equals(""))
//					log="Quitter";
//				else log= (String)session.getAttribute("login");
//			}
//			catch(NullPointerException e){
//				
//				log="Quitter";
//			}
//			return log;
//		}
//	
//
//	public String deconnectione(){
//			
//		  connectDatabase   user=new connectDatabase();
//		    String util=detecterNoNullPointerException();
//		    
//		    if(user.getState()==null)
//		    	{
//		    	   return "Quitter";
//		    	}
//		    
//		    if(user!=null)
//		    {
//		    	if(!util.equals("Quitter"))
//		    	{
//		    		HttpSession sess=LoginUtilisateur.getSessionUtilisateur();
//		    		Ident users=null;
//		    		if(sess.getAttribute("ident") !=null)
//		    		{
//		    		users=(Ident) sess.getAttribute("ident");
//		    		//user.updateData("update employe SET connectivite='no' where login ='"+util+"'");
//		    		user.updateData("insert into heureSorti (emplSystId,heureSorti) values('"+users.getPersonneID()+"',now())");
//		    	user.closeData();
//		    	}
//		    	}
//		    }
//		    
//			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
//			
//			return "Quitter";
//			
//
//	}
//		public void modifierEtatCompte()
//		{
//			
//			if(this.login==null || this.login.equals("") )
//			{
//				this.info="Taper le login..";
//				return ;
//			}
//			
//			
//			connectDatabase con=new connectDatabase();
//			con.updateData("update employe set etat ='"+this.etatUtilisateur+"' where login='"+this.login+"' ");
//			
//			
//			
//			
//			
//		}
//
//		/*public boolean existance(String loginn)
//		{
//			boolean existe=false;
//			
//			connectDatabase con=new connectDatabase();
//			ResultSet res=con.extaireData("select *  from employe where login ='"+this.loginn);
//			
//			if(res!=null)
//				try {
//					while(res.next())
//					{
//						existe=true;
//						
//					}
//				} catch (SQLException e) {
//					
//					e.printStackTrace();
//				}
//				
//			
//			
//			return existe;
//		}*/
//		
//		
//		
//		
//
//	public String afficheprofile()
//	{
//		String valeur ="profil";
//		ResultSet res=null;
//		
//		
//		HttpSession session=getSessionUtilisateur();
//		String log=(String) session.getAttribute("login");
//		connectDatabase con=new connectDatabase();
//		
//		res=con.extaireData("select login,nomEmploye,prenomEmploye,roleEmploye  from employe where login ='"+log);	
//		
//		if(res !=null)
//		{
//			try {
//				while(res.next())
//				{
//					this.login=res.getString("login");
//					this.nom=res.getString("nomEmploye");
//					this.prenom=res.getString("prenomEmploye");
//					this.role=res.getString("roleEmploye");
//					
//					
//					
//				}
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//			
//			
//		}
//		
//		
//		return valeur;
//	}
//
//
//	
//
//
//	public int getSexedId() {
//		return sexedId;
//	}
//
//
//	public void setSexedId(int sexedId) {
//		this.sexedId = sexedId;
//	}
//
//
//	public List<LoginUtilisateur> getListEmploye() {
//		return extractionEmploye();
//	}
//	LoginUtilisateur util=null;
//	
//	public LoginUtilisateur getUtil() {
//		return util;
//	}
//
//
//
//	public void setUtil(LoginUtilisateur util) {
//		this.util = util;
//	}
//
//
//	private String sexes;
//	
//
//	public String getSexes() {
//		return sexes;
//	}
//
//
//	public void setSexes(String sexes) {
//		this.sexes = sexes;
//	}
//
//
//
//private int idEmploye;
//
//
//
//
//
//
//	public int getIdEmploye() {
//	return idEmploye;
//}
//
//
//
//public void setIdEmploye(int idEmploye) {
//	this.idEmploye = idEmploye;
//}
//
//
//
//private List<LoginUtilisateur>	listEmployeSystem;
//
//
//
//
//
//
//
//
//
//
//
//public List<LoginUtilisateur> getListEmployeSystem() {
//	
//	connectDatabase con=new connectDatabase ();
//	ResultSet res=con.extaireData("select  emplSystId,nomemploye,prenomemploye,roleEmploye,login,etat  from employe,employeSystem  where employe.Idemploye=employeSystem.Idemploye  order by employeSystem.emplSystId ");
//	
//	if(this.listEmployeSystem==null) this.listEmployeSystem=new ArrayList<LoginUtilisateur>();
//	else this.listEmployeSystem.clear();
//	
//	if( res !=null)
//		try {
//			while(res.next()){
//			util=new LoginUtilisateur();
//			
//			util.setIdEmploye(res.getInt("emplSystId"));
//		//	util.setIdemployeSystem(res.getInt("emplSystId"));
//			util.setNom(res.getString("nomemploye"));
//			util.setPrenom(res.getString("prenomemploye"));
//			util.setRole(res.getString("roleEmploye"));
//			util.setLogin(res.getString("login"));
//			util.setEtatUtilisateur(res.getString("etat"))	;
//			
//            this.listEmployeSystem.add(util);
//            
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	
//	
//	return listEmployeSystem;
//}
//
//
//
//private List<LoginUtilisateur> extractionEmploye()
//	
//	{
//		connectDatabase con= new connectDatabase();
//		
//		ResultSet res=null;
//		
//		
//		if(con.getCon()!=null)
//		{
//			res=con.extaireData("select *  from employe   where  roleemploye='Restaurant' or roleemploye='Bar Man' ");
//			if(res !=null)
//			{
//				if(this.listEmploye==null) this.listEmploye=new ArrayList<LoginUtilisateur>();
//				else this.listEmploye.clear();
//				
//				try {
//					while(res.next())
//					{
//						util=new LoginUtilisateur();
//						
//						util.setIdEmploye(res.getInt("idemploye"));
//						/*util.setLogin(res.getString("login"));*/
//						util.setNom(res.getString("nomEmploye"));
//						util.setPrenom(res.getString("prenomEmploye"));
//						util.setRole(res.getString("roleEmploye"));
//						//util.setPassWd(res.getString("password"));
//						this.listEmploye.add(util);
//						
//					}
//				} catch (SQLException e) {
//					
//					e.printStackTrace();
//				}
//				
//				
//			}
//			
//			
//		}
//		
//		
//		return this.listEmploye;
//		
//	}
//
//	public boolean isFourniture() {
//		return fourniture;
//	}
//
//
//	public void setFourniture(boolean fourniture) {
//		this.fourniture = fourniture;
//	}
//
//
//	public boolean isConsommation() {
//		return consommation;
//	}
//
//	public void setConsommation(boolean consommation) {
//		this.consommation = consommation;
//	}
//
//
//	public boolean isAjoutEmploye() {
//		return ajoutEmploye;
//	}
//
//
//	public void setAjoutEmploye(boolean ajoutEmploye) {
//		this.ajoutEmploye = ajoutEmploye;
//	}
//
//
//	public boolean isModif() {
//		return modif;
//	}
//
//	public void setModif(boolean modif) {
//		this.modif = modif;
//	}
//
//
//	public boolean isSupprim() {
//		return supprim;
//	}
//
//
//	public void setSupprim(boolean supprim) {
//		this.supprim = supprim;
//	}
//
//
//	public boolean isAjoutPrix() {
//		return ajoutPrix;
//	}
//
//
//	public void setAjoutPrix(boolean ajoutPrix) {
//		this.ajoutPrix = ajoutPrix;
//	}
//
//
//	public boolean isAjoutProduit() {
//		return ajoutProduit;
//	}
//
//	public void setAjoutProduit(boolean ajoutProduit) {
//		this.ajoutProduit = ajoutProduit;
//	}
//
//
//	public boolean isAjoutClient() {
//		return ajoutClient;
//	}
//
//	public void setAjoutClient(boolean ajoutClient) {
//		this.ajoutClient = ajoutClient;
//	}
//
//
//	public String getPrenom() {
//		return prenom;
//	}
//
//	public void setPrenom(String prenom) {
//		this.prenom = prenom;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	
//
//
//	public String getNom() {
//		return nom;
//	}
//
//	public void setNom(String nom) {
//		this.nom = nom;
//	}
//
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//
//	public String getInfoConnexion() {
//		return infoConnexion;
//	}
//
//	public void setInfoConnexion(String infoConnexion) {
//		this.infoConnexion = infoConnexion;
//	}
//
//	public String getInfo() {
//		return info;
//	}
//
//
//	public void setInfo(String info) {
//		this.info = info;
//	}
//
//	public  String getEncodedPassword(String key) {
//		 
//		 byte[] uniqueKey = key.getBytes();
//		 byte[] hash = null;
//		 
//		 try {
//			 
//			 hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
//			 
//		 } catch (NoSuchAlgorithmException e) {
//			 
//			 throw new Error("no MD5 support in this VM");
//		 }
//		 
//		 StringBuffer hashString = new StringBuffer();
//		 
//		 for ( int i = 0; i < hash.length; ++i ) {
//			 
//			 String hex = Integer.toHexString(hash[i]);
//			
//			 if ( hex.length() == 1 ) {
//				 
//				 hashString.append('0');
//				 hashString.append(hex.charAt(hex.length()-1));
//			 } else {
//				 
//				 hashString.append(hex.substring(hex.length()-2));
//			 }
//		 }
//		 return hashString.toString();
//	}
//	
//	
//	
//	
//
//	
//	
//	public String getLogin() {
//		return login;
//	}
//
//
//
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//
//
//
//	public String getPassWd() {
//		return passWd;
//	}
//
//
//
//
//	public void setPassWd(String passWd) {
//		this.passWd = passWd;
//	}
//
//
//
//
//	public boolean isConnected() {
//		return connected;
//	}
//
//
//
//
//	public void setConnected(boolean connected) {
//		this.connected = connected;
//	}
//
//
//
//
//	public String getMessage() {
//		return message;
//	}
//
//
//
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//
//
//
//	public LoginUtilisateur() {
//		
//	}
//	
//	public void supprimerUtilisateur(ActionEvent ev)
//	{
//		connectDatabase con=new connectDatabase();
//		if(util ==null)
//		{
//			this.info="Pas de personne à supprimer";
//			return ;
//		}
//		
//		int m=util.getIdEmploye();
//		 int n=0;
//		 n=con.updateData("delete  from employe where Idemploye="+m);
//		
//		 if(n==0)
//		 {
//			 this.info="Impossible de le supprimer";
//			 return;
//		 }
//		 if(n==1)
//		 {
//			 this.info="suppression reussie..";
//			 return ;
//		 }
//		
//		util=null;
//	}
//	
//	
//	
//	
//	
//	public void supprimerUtilSystem(ActionEvent ev)
//	{
//		connectDatabase con=new connectDatabase();
//		if(util ==null)
//		{
//			this.info="Pas de d'employe..";
//			return ;
//		}
//		
//		int m=util.getIdEmploye();
//		int n=0;
//		n=con.updateData("delete  from employeSystem where  emplSystId="+m);
//		
//		if(n==0)
//		{
//			this.info="Impossible de le supprimer ..";
//			return ;
//		}
//		
//		if(n==1)
//		{
//			this.info="Suppression reussie..";
//			
//			return ;
//		}
//		
//		util=null;
//		
//		
//		
//		
//	}
//	
//private String nomConnect;
//private String prenomConnect;
//	public String getNomConnect() {
//	return nomConnect;
//}
//
//
//public void setNomConnect(String nomConnect) {
//	this.nomConnect = nomConnect;
//}
//
//
//public String getPrenomConnect() {
//	return prenomConnect;
//}
//
//public void setPrenomConnect(String prenomConnect) {
//	this.prenomConnect = prenomConnect;
//}
//
//
//boolean pasEnregistrer=false;
//
//	public boolean isPasEnregistrer() {
//	return pasEnregistrer;
//}
//
//
//
//public void setPasEnregistrer(boolean pasEnregistrer) {
//	this.pasEnregistrer = pasEnregistrer;
//}
///*
//private boolean delaiDonnes;
//
//
//	public boolean isDelaiDonnes() {
//	return delaiDonnes;
//}
//
//
//
//public void setDelaiDonnes(boolean delaiDonnes) {
//	this.delaiDonnes = delaiDonnes;
//}*/
//
//
//
//	public String ident(){
//		int k=0;
//		//String returnValue=null;
//		connectDatabase connect=null;
//		
//		connect=new connectDatabase();
//		ResultSet res=connect.extaireData("select (to_days(now())-to_days('2014-05-10')) as nn,now() as nnn");
//		  if(this.login.length()==0)
//		  {
//			  this.info="le login est vide";
//		  return null; 
//		  }
//		  if(this.passWd.length()==0)
//		  {
//			  this.info="pass word est vide";
//		  return null;
//		  }
//		  
//		  
//		  
//		  if(res !=null)
//			try {
//				if(res.next())
//				  {
//					if(res.getInt("nn")>=30)
//					  { 
//						 k=res.getInt("nn")-30;
//						this.info="Période d'essai de 30 jours  à été expirée !! Il y a  "+k+"  jours. De "+" 2014-04-10  "+"  à  "+res.getDate("nnn"); 
//						 return null;
//					  }
//					/* 
//					{
//					
//						int k=30-res.getInt("nn");
//					return	this.info="Vous êtes dans la période d'essai de 30 jours !! Il reste "+k+"  jours.";
//						
//					}*/
//					
//				  }
//			} catch (SQLException e1) {
//				
//				e1.printStackTrace();
//			}
//			
//		
//		Ident user=null;
//		ResultSet result=null;
//        result=connect.extaireData("select emplSystId,employe.IdEmploye,employesystem.idemploye,nomEmploye,prenomEmploye,roleEmploye,etat,login,password from employe,employesystem  where login='"+this.login+"' and password='"+this.getEncodedPassword(this.passWd.trim())+"' and employe.Idemploye= employesystem.Idemploye   ");
//       
//		HttpSession sess=LoginUtilisateur.getSessionUtilisateur();
//		
//		
//					
//					
//					
//					
//			
//		
//		if(result!=null){
//			try {
//				if(result.next())
//				{	user=new Ident(result.getInt("emplSystId"),result.getString("nomEmploye"),result.getString("prenomEmploye"),result.getString("login"),result.getString("roleEmploye"));
//				    
//				this.role=result.getString("roleEmploye");
//				this.etatUtilisateur=result.getString("etat");
//				
//				sess.setAttribute("nomConnect",user.getNom());
//				sess.setAttribute("prenomConnect",user.getSurname());
//				  
//				sess.setAttribute("login",user.getLog());
//				sess.setAttribute("role",user.getRol());
//				
//				
//				
//				//System.out.println("Nom Connect :"+this.nomConnect);
//				sess.setAttribute("ident",user);
//			    sess.setMaxInactiveInterval(-1);
//			 
//			    
//			    
//			    if(this.etatUtilisateur.equalsIgnoreCase("delete"))
//			    {
//			    	this.info="You are deleted !!";
//			    	return null;
//			    }
//			    if(this.etatUtilisateur.equalsIgnoreCase("suspendu"))
//			    {
//			    	this.info="Tu as été suspendu !!";
//			    	return null;
//			    }
//			    
//			    if(this.etatUtilisateur.equalsIgnoreCase("valide") ||result.getString("login").equalsIgnoreCase(this.login) || result.getString("password").equalsIgnoreCase(this.passWd)  )
//			   
//			    {
//			    	
//			    	if(result.getString("roleEmploye").equalsIgnoreCase("receptionniste") )
//			    {	
//			    	sess.setAttribute("pasEnregistrer",true);	
//			   		sess.setAttribute("heureConnectDeconnect",true);
//			   		sess.setAttribute("changerEtatCpte",true);
//			   		sess.setAttribute("ajouter",true);
//			   		sess.setAttribute("gestStock",true);
//			   	  	sess.setAttribute("reservSejour",true);	
//			   	  	sess.setAttribute("modifSupprim",true);	
//				   	
//			    	if(sess.getAttribute("ident") !=null)
//			    	{
//			    		user=(Ident) sess.getAttribute("ident");
//			    	connect.updateData("insert into heureEntre(emplSystId,heureEntre) values("+user.getPersonneID()+",now())");
//			    	
//			    	}
//			    	/*this.resClient=true;
//			    	this.fournitur=false;*/
//			    	return "ok";
//			    }
//			    
//			    if(result.getString("roleEmploye").equalsIgnoreCase("caissier"))
//			    { 
//			    	sess.setAttribute("changerEtatCpte",true);
//			    	sess.setAttribute("gestStock",false);
//			    	sess.setAttribute("reservSejour",false);	
//		   			sess.setAttribute("pasEnregistrer",true);
//			    	sess.setAttribute("enregistrementCliEse",true);
//			    	sess.setAttribute("heureConnectDeconnect",true);
//			   		sess.setAttribute("ajouter",true);
//			   		sess.setAttribute("modifSupprim",true);	
//				   	
//			   		
//			   		
//			   		
//			    	if(sess.getAttribute("ident") !=null)
//			    	{
//			    		user=(Ident) sess.getAttribute("ident");
//			    	connect.updateData("insert into heureEntre(emplSystId,heureEntre) values("+user.getPersonneID()+",now())");
//			    	
//			    	}
//			    	
//			  	return "ok";
//			    }
//			    
//			    
//			    if(result.getString("roleEmploye").equalsIgnoreCase("Gestionnaire Stock"))
//			    {
//			    	sess.setAttribute("changerEtatCpte",true);
//			    	sess.setAttribute("pasEnregistrer",true);
//			    	sess.setAttribute("ajouter",true);
//			    	sess.setAttribute("heureConnectDeconnect",true);
//			    	sess.setAttribute("modifSupprim",true);	
//			    	sess.setAttribute("enregistrementCliEse",true);
//				 	
//			    	if(sess.getAttribute("ident") !=null)
//			    	{
//			    		user=(Ident) sess.getAttribute("ident");
//			    	connect.updateData("insert into heureEntre(emplSystId,heureEntre) values("+user.getPersonneID()+",now())");
//			    	
//			    	}
//			    	
//			    	
//			    	return "ok";	
//			    }
//			    
//			    if(result.getString("roleEmploye").equalsIgnoreCase("Directeur Gerant"))
//			    {
//			    	sess.setAttribute("pasEnregistrer",false);
//			    	sess.setAttribute("enregistrementCliEse",false);
//			    	sess.setAttribute("ajouter",false);
//			    	sess.setAttribute("heureConnectDeconnect",false);
//			    	sess.setAttribute("changerEtatCpte",false);
//			    	sess.setAttribute("gestStock",false);
//			    	sess.setAttribute("reservSejour",false);	
//			    	sess.setAttribute("modifSupprim",false);	
//				   	
//			    	if(sess.getAttribute("ident") !=null)
//			    	{
//			    		user=(Ident) sess.getAttribute("ident");
//			    	connect.updateData("insert into heureEntre(emplSystId,heureEntre) values("+user.getPersonneID()+",now())");
//			    	
//			    	}
//			    	
//			    	
//			    	return "ok";	
//			    }
//			    
//			   
//			 			}
//				}
//			    
//				//}
//				else this.info="Identifier vous bien!!";
//				
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		initialiserdonnees();
//		
//		
//		return null;
//	}
//	
//	private boolean changerEtatCpte;
//	public boolean isModifSupprim() {
//		return modifSupprim;
//	}
//
//
//
//	public void setModifSupprim(boolean modifSupprim) {
//		this.modifSupprim = modifSupprim;
//	}
//	private boolean enregistrementCliEse;
//	private boolean heureConnectDeconnect;
//	private boolean gestStock;
//	private boolean reservSejour;
//	private boolean modifSupprim;
//	public boolean isReservSejour() {
//		return reservSejour;
//	}
//
//
//
//	public void setReservSejour(boolean reservSejour) {
//		this.reservSejour = reservSejour;
//	}
//
//
//
//	public boolean isGestStock() {
//		return gestStock;
//	}
//
//
//
//	public void setGestStock(boolean gestStock) {
//		this.gestStock = gestStock;
//	}
//
//
//
//	public boolean isHeureConnectDeconnect() {
//		return heureConnectDeconnect;
//	}
//
//
//
//	public void setHeureConnectDeconnect(boolean heureConnectDeconnect) {
//		this.heureConnectDeconnect = heureConnectDeconnect;
//	}
//
//
//
//	public boolean isChangerEtatCpte() {
//		return changerEtatCpte;
//	}
//
//
//
//	public void setChangerEtatCpte(boolean changerEtatCpte) {
//		this.changerEtatCpte = changerEtatCpte;
//	}
//	private boolean ajouter;
//	
//	
//	
//	public boolean isAjouter() {
//		return ajouter;
//	}
//
//
//
//	public void setAjouter(boolean ajouter) {
//		this.ajouter = ajouter;
//	}
//
//
//
//	public boolean isEnregistrementCliEse() {
//		return enregistrementCliEse;
//	}
//
//
//
//	public void setEnregistrementCliEse(boolean enregistrementCliEse) {
//		this.enregistrementCliEse = enregistrementCliEse;
//	}
//	private Date heureConnecte;
//	private Date heureDeconnecte;
//
//
//	public Date getHeureConnecte() {
//		return heureConnecte;
//	}
//
//
//
//	public void setHeureConnecte(Date heureConnecte) {
//		this.heureConnecte = heureConnecte;
//	}
//
//
//
//	public Date getHeureDeconnecte() {
//		return heureDeconnecte;
//	}
//
//
//
//	public void setHeureDeconnecte(Date heureDeconnecte) {
//		this.heureDeconnecte = heureDeconnecte;
//	}
//	
//	
//	
//	
//
//	
//	LoginUtilisateur connect=null;
//	
//	public LoginUtilisateur getConnect() {
//		return connect;
//	}
//
//
//
//	public void setConnect(LoginUtilisateur connect) {
//		this.connect = connect;
//	}
//	private List<LoginUtilisateur> listDeconnectConnect;
//
//
//	public List<LoginUtilisateur> getListDeconnectConnect() {
//		
//		connectDatabase con=new connectDatabase();
//		//ResultSet res=con.extaireData("select nomemploye,prenomemploye,heureEntre   from employe,heureEntre  where  employe.Idemploye=heureEntre.Idemploye");
//		ResultSet res=con.extaireData("select nomemploye,prenomemploye,heureEntre,heureSorti   from employe,employeSystem,heureSorti,heureEntre  where employe.Idemploye=employeSystem.Idemploye and employeSystem.emplSystId=heureEntre.emplSystId  and  employeSystem.emplSystId=heureSorti.emplSystId order by  idheureEntre and idheureSorti asc");
//
//		if(listDeconnectConnect ==null) this.listDeconnectConnect=new ArrayList<LoginUtilisateur>();
//		else this.listDeconnectConnect.clear();
//		
//		if(res !=null)
//			try {
//				while(res.next())
//				{
//					connect=new LoginUtilisateur();
//					connect.setNom(res.getString("nomemploye"));
//					connect.setPrenom(res.getString("prenomemploye"));
//					/*connect.setHeureConnecte(res.getDate("heureEntre"));
//					connect.setHeureDeconnecte(res.getDate("heureSorti"));*/
//					connect.setRecDate(res.getString("heureEntre"));
//					connect.setRecDateDeconnecte(res.getString("heureSorti"));
//					this.listDeconnectConnect.add(connect);
//				}
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//		
//		
//		return listDeconnectConnect;
//	}
//	
//	
//	
//	private String recDate;
//	private String recDateDeconnecte;
//
//	
//public String getRecDateDeconnecte() {
//		return recDateDeconnecte;
//	}
//
//
//
//	public void setRecDateDeconnecte(String recDateDeconnecte) {
//		this.recDateDeconnecte = recDateDeconnecte;
//	}
//
//
//
//public String getRecDate() {
//		return recDate;
//	}
//
//
//
//	public void setRecDate(String recDate) {
//		this.recDate = recDate;
//	}
//	
//	private List<LoginUtilisateur> heureSortEntre;
//
//
//	public List<LoginUtilisateur> getHeureSortEntre() {
//		return heureSortEntre;
//	}
//
//
//
//	public void setHeureSortEntre(List<LoginUtilisateur> heureSortEntre) {
//		this.heureSortEntre = heureSortEntre;
//	}
//	
//
//	public String modifierUser(){
//		
//		/* if(GestionID.detecterNoNullPointerException().equals("Quitter")){
//			return"Quitter";
//		}*/
//		connectDatabase connect=new connectDatabase();
//		
//		
//	
//		if(this.login==null||this.login.equals("")){
//			this.info="Tapez le login!";
//			return null;
//		}
//		
//		if(this.passWd==null || this.passWd.equals(""))
//		{
//			this.info="Tapez l'ancien mot de passe!";
//			return null;
//		}
//		if(this.rePassword==null||this.rePassword.equals("")){
//			this.info="Tapez le nouveau mot de passe!";
//			return null;
//		}
//		if(this.confirmePassword==null||this.confirmePassword.equals("")){
//			this.info="Confirmer votre mot de passe!";
//			return null;
//		}
//		
//		if(!this.rePassword.equals(this.confirmePassword)){
//			
//			this.info="Mot de passe confirmé ne correspond au nouveau!";
//			return null;
//		}
//		
//		
//	//	ResultSet result=connect.extaireData(requete(3));
//	
//		
//		 
//		 HttpSession sess=LoginUtilisateur.getSessionUtilisateur();
//		 
//		 
//		 
//		   // req="update employe SET password='"+this.getEncodedPassword(this.rePassword)+"' where login='"+sess.getAttribute("login")+"'";
//		         connect.updateData("update employeSystem SET password='"+this.getEncodedPassword(this.rePassword)+"' where login='"+sess.getAttribute("login")+"'");
//		         this.info="reussie ..";
//				
//		
//		return null;
//		
//	}
//	
//	
//	/*public boolean loginPassExiste()
//	{
//		boolean passLogExiste=false;
//		connectDatabase connect=new connectDatabase();
//		ResultSet res=connect.extaireData("select login,password  from employe");
//		if(res !=null)
//			try {
//				while(res.next())
//				{
//					passLogExiste=true;
//					
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		
//		
//		
//		return passLogExiste;
//	}*/
//	
//	
//	public void addSimpleUser()
//	{
//		
//		connectDatabase con=new connectDatabase();
//		if(this.nom==null || this.nom.equals(""))
//		{
//			this.info="Le nom ..";
//			return ;
//		}
//		
//		if(this.prenom==null || this.prenom.equals(""))
//		{
//			this.info="Le prenom ..";
//			return ;
//		}
//		
//		
//		if(this.role==null || this.role.equals(""))
//		{
//			this.info="Le role ..";
//			return ;
//		}
//		
//		con.updateData("insert into employe (nomemploye,prenomemploye,roleemploye)values('"+this.nom+"','"+this.prenom+"','"+this.role+"') ");
//		
//		initialiserdonnees();
//		this.info="Reussie..";
//		
//	}
//	
//	private boolean showmessage11=false;
//
//
//	public boolean isShowmessage11() {
//		return showmessage11;
//	}
//
//
//
//	public void setShowmessage11(boolean showmessage11) {
//		this.showmessage11 = showmessage11;
//	}
//	
//
//	public void listernInPutLogin(ActionEvent e)
//	{Controleur c=new Controleur();
//	this.showmessage11=c.isStringOfCharAndNumbers(this.login);
//	
//		}
//	
//	
//	public void modifierUtilSimple()
//	{
//		connectDatabase con=new connectDatabase();
//		
//		if(util ==null)
//		{
//			
//		this.info="Pas utilisateur a modifier ..";
//		
//		return;
//		}
//		
//		int n=-1;
//		n=con.updateData("update employe set idemploye="+this.idEmploye+" , nomEmploye='"+this.nom+"', prenomEmploye='"+this.prenom+"',roleEmploye='"+this.role+"'  where  idemploye="+this.idEmploye);
//		
//	//	System.out.println("Modification : "+"update employe set idemploye="+this.idEmploye+" , nomEmploye='"+this.nom+"', prenomEmploye='"+this.prenom+"',roleEmploye='"+this.role+"'  where  idemploye="+this.idEmploye);	
//		
//		
//		if(n==-1)
//		{
//			this.info="Non Reussie ..";
//			return ;
//		}
//		
//		
//
//		if(n==1)
//		{
//			this.info="Reussie ..";
//			return ;
//		}
//		
//		
//	}
//	
//	public void maFonction(ActionEvent  ev)
//	{
//		if(util !=null)
//		{
//			this.idEmploye=this.util.idEmploye;
//			//this.
//			this.nom=this.util.nom;
//			this.prenom=this.util.prenom;
//			this.role=this.util.role;
//			
//			
//		System.out.println(idEmploye+"  "+nom+"  "+prenom+"  "+role);	
//			
//		}
//		
//	}
//	
//	private int  idemployeSystem;
//	
//	public int getIdemployeSystem() {
//		return idemployeSystem;
//	}
//
//
//
//	public void setIdemployeSystem(int idemployeSystem) {
//		this.idemployeSystem = idemployeSystem;
//	}
//
//
//
//	public void maFonctionEmplSyst(ActionEvent  ev)
//	{
//		if(util !=null)
//		{
//			this.idEmploye=this.util.idEmploye;
//			//this.
//			this.nom=this.util.nom;
//			this.prenom=this.util.prenom;
//			this.role=this.util.role;
//			this.login=this.util.login;
//			this.etatUtilisateur=this.util.etatUtilisateur;
//			
//		System.out.println(idEmploye+"  "+nom+"  "+prenom+"  "+role+"  "+login+"  "+etatUtilisateur);	
//			
//		}
//		
//	}
//	
//public void	modifierUtilSystem()
//	{
//	connectDatabase con=new connectDatabase();
//	
//	if(util ==null)
//	{
//		
//	this.info="Pas utilisateur a modifier ..";
//	
//	return;
//	}
//	
//	int n=-1;
//	int m=-1;
//		
//	n=con.updateData("update employe set idemploye="+this.idEmploye+" , nomEmploye='"+this.nom+"', prenomEmploye='"+this.prenom+"',roleEmploye='"+this.role+"'  where  idemploye="+this.idEmploye);
//	m=con.updateData("update employeSystem set login='"+this.login+"',etat='"+this.etatUtilisateur+"'  where emplSystId ="+this.idEmploye);
//	
//	System.out.println("Update 1 :"+"update employe set idemploye="+this.idEmploye+" , nomEmploye='"+this.nom+"', prenomEmploye='"+this.prenom+"',roleEmploye='"+this.role+"'  where  idemploye="+this.idEmploye);
//	System.out.println("Update 2  : "+"update employeSystem set login='"+this.login+"',etat='"+this.etatUtilisateur+"'  where emplSystId ="+this.idEmploye);
//	
//	if(n==-1 || m==-1)
//	{
//		this.info="Non Reussie ..";
//		return ;
//	}
//	
//	
//
//	if(n==1 || m==-1)
//	{
//		this.info="Reussie ..";
//		return ;
//	}
//	
//	
//	}
///*
//public void logout() throws IOException {
//    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//    ec.in
//    ec.redirect(ec.getRequestContextPath() + "/home.xhtml");
//}
//*/
//	
//
//}
//
