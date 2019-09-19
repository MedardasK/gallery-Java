package com.gallery.gallery.service.implementations;

import com.gallery.gallery.DAO.IRoleRep;
import com.gallery.gallery.entity.Role;
import com.gallery.gallery.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRep roleRep;

    public Role findByRoleName(String name) {
        Role role = roleRep.findByName(name);
        if(role == null){
            role = new Role();
            role.setName(name);
            roleRep.save(role);
            return roleRep.findByName(name);
        } else {
            return role;
        }

    }
}
