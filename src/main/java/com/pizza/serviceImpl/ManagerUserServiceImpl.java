package com.pizza.serviceImpl;

import com.pizza.models.PizzaUser;
import com.pizza.repositories.UserRepo;
import com.pizza.services.manager.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ManagerUserServiceImpl implements ManagerUserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void insertUser(PizzaUser user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Integer uid) {
        userRepo.deleteById(uid);
    }

    @Override
    public void updateUser(PizzaUser user) {
        userRepo.saveAndFlush(user);
    }

    @Override
    public List<PizzaUser> selectAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<PizzaUser> selectLike(String search) {
        List<PizzaUser> list = new ArrayList<>();
        PizzaUser user = new PizzaUser();
        user.setUsername(search);
        try {
            Integer uid = Integer.parseInt(search);
            Optional<PizzaUser> optional = userRepo.findById(uid);
            if (!optional.isPresent()) {
                list = selectVague(user);
            } else {
                list.add(optional.get());
            }
        } catch (NumberFormatException e) {
            list = selectVague(user);
        }

        return list;
    }

    private List<PizzaUser> selectVague(PizzaUser user) {
        List<PizzaUser> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("uid");
        Example<PizzaUser> example = Example.of(user, matcher);
        list = userRepo.findAll(example);
        return list;
    }

}
