package com.naresh.firstservice.restfulwebservice.user;

import com.naresh.firstservice.restfulwebservice.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDoaService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    @Autowired
    PostDaoService postService;
    static {
        users.add(new User(1,"naresh",new Date()));
        users.add(new User(2,"Tannu",new Date()));
        users.add(new User(3,"Tanuesh",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        for(User user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public User save(User user){
        if(user.getId() == 0){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                postService.deleteByUserId(id);
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
