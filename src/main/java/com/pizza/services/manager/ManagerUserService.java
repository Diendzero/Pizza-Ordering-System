package com.pizza.services.manager;

import java.util.List;
import com.pizza.models.PizzaUser;


public interface ManagerUserService {

    void insertUser(PizzaUser user);

    void deleteUser(Integer uid);

    void updateUser(PizzaUser user);

    List<PizzaUser> selectAllUser();

    List<PizzaUser> selectLike(String search);

}
