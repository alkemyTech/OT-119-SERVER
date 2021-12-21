package com.alkemy.ong.service;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.IGetUserService;
import com.alkemy.ong.service.abstraction.IPostActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActivityServiceImpl implements IPostActivityService {

    @Autowired
    private IActivityRepository activitiesRepository;

    @Autowired
    private IGetUserService getUserService;

    @Override
    public void add(String name, String content, String authorizationHeader) throws OperationNotAllowedException {
        User user = getUserService.getBy(authorizationHeader);
        if (isAdmin(user) && Strings.areValidStrings(name,content)){
            Activity activity = new Activity();
            activity.setName(name);
            activity.setContent(content);
            activitiesRepository.save(activity);
        } else {
            String message = "OPERATION NOT ALLOWED FOR USERS";
            throw new OperationNotAllowedException(message);
        }
    }


    private boolean hasRole(String nameRole, List<Role> roles) {
        return roles.stream().anyMatch(role -> nameRole.equals(role.getName()));
    }

    private boolean isAdmin(User user){
        boolean isRoleAdmin = hasRole(ApplicationRole.ADMIN.getFullRoleName(), user.getRoles());
        return isRoleAdmin;
    }

}

class Strings {
    public static boolean isNotNullAndNotEmpty(String string){
        boolean isValidString = false;
        if (string != null && !string.isEmpty()){
            isValidString = true;
        }
        return isValidString;
    }

    public static boolean areValidStrings(String... strings){
        boolean areValid = true;
        for (String string : strings) {
            areValid = areValid && Strings.isNotNullAndNotEmpty(string);
        }
        return areValid;
    }
}