package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Role;
import ub.edu.bi.dao.ImplementRole;
@ManagedBean(name = "role")
@SessionScoped
public class ControleurRole implements Serializable{
    private static final long serialVersionUID = -1;

    private DataModel roles;
    private Role newRole;
    private Role editRole;
    private ImplementRole roleDAO;
    private List<Role> selectAllRole;
    
    @PostConstruct
    public  void init(){
        roleDAO = new ImplementRole();
        newRole = new Role();
        selectAllRole = new ArrayList<>();
        
    }

    public String createRole() {
        roleDAO.InsertRole(newRole);
        newRole = new Role("", "");
//        roleDAO.Actualiser(newRole);
        return "/Vue/Role/View?faces-redirect=true";
    }

    public String editRole() {
        editRole = (Role) roles.getRowData();
        return "Update_Role";
    }
    
    public String modifierRole(){
        roleDAO.UpdateRole(editRole);
        roles.setWrappedData(roleDAO.selectAllRole());
        return "View_role";
    }
    
    public void deleteRole(){
        Role r = (Role) roles.getRowData();
        roleDAO.deleteRole(r);
        roles.setWrappedData(roleDAO.selectAllRole());
        
    }

    public List<Role> getSelectAllRole() {
        selectAllRole = roleDAO.selectAllRole();
        return selectAllRole;
    }

    public void setSelectAllRole(List<Role> selectAllRole) {
        this.selectAllRole = selectAllRole;
    }
    
    

    public DataModel getRoles() {
        if (roles == null) {
            roles = new ListDataModel();
            roles.setWrappedData(roleDAO.selectAllRole());
        }
        return roles;
    }

    public void setRoles(DataModel roles) {
        this.roles = roles;
    }

    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }

    public Role getEditRole() {
        return editRole;
    }

    public void setEditRole(Role editRole) {
        this.editRole = editRole;
    }

    public ImplementRole getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(ImplementRole roleDAO) {
        this.roleDAO = roleDAO;
    }
}
