
package ub.edu.bi;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_decision")
@NamedQueries({
    @NamedQuery(name = "Decision.findAll", query = "select d from Decision d"),
    @NamedQuery(name = "Decision.findById", query = "select d from Decision d where d.id = :idDecision"),
    @NamedQuery(name = "Decision.findByCode", query = "select d from Decision d where d.code = :codeDecision")
})
public class Decision implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String decision;
    private String code;

    public Decision() {
    }
    
    public Decision(String decision, String code) {
        this.decision = decision;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Decision{" + "id=" + id + ", decision=" + decision + ", code=" + code + '}';
    }
    
    
}
