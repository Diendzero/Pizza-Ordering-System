package com.pizza.serviceImpl;

import com.pizza.models.Pizza;
import com.pizza.repositories.PizzaRepo;
import com.pizza.services.manager.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class pizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Override
    public void insertPizza(Pizza pizza) {
        pizzaRepo.save(pizza);
    }

    @Override
    public void deletePizza(Integer id) {
        pizzaRepo.deleteById(id);
    }

    @Override
    public void updatePizza(Pizza pizza) {
        pizzaRepo.saveAndFlush(pizza);
    }

    @Override
    public List<Pizza> selectAllPizza() {
        return pizzaRepo.findAll();
    }

    @Override
    public List<Pizza> selectLike(String search) {
        List<Pizza> list = new ArrayList<>();
        Pizza pizza = new Pizza();
        pizza.setName(search);
        pizza.setCategory(search);
        pizza.setStatus(search);
        try {
            Integer id = Integer.parseInt(search);
            Optional<Pizza> optional = pizzaRepo.findById(id);
            if (!optional.isPresent()) {
                list = selectVague(pizza);
            } else {
                list.add(optional.get());
            }
        } catch (NumberFormatException e) {
            list = selectVague(pizza);
        }

        return list;
    }

    private List<Pizza> selectVague(Pizza pizza) {
        List<Pizza> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("pid")
                .withIgnoreCase("price")
                .withIgnoreCase("description")
                .withIgnoreCase("base")
                .withIgnoreCase("size");
        Example<Pizza> example = Example.of(pizza, matcher);
        list = pizzaRepo.findAll(example);
        return list;
    }

}
