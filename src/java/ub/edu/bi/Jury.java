package ub.edu.bi;

import javax.persistence.OneToMany;

public class Jury {
    private Long id;
    @OneToMany
    private Role rol;
    
}
