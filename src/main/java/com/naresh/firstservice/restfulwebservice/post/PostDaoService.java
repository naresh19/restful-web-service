package com.naresh.firstservice.restfulwebservice.post;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PostDaoService {
  private static List<Post> posts = new ArrayList<>();
  private static int postCounter = 3;

  static {
      posts.add(new Post(1, "Post 1 user 1", 1));
      posts.add(new Post(2, "Post 2 user 1", 1));
      posts.add(new Post(3, "Post 3 user 3", 3));
  }

  public List<Post> getUserPosts(int userId){
      List<Post> userPosts = new ArrayList<>();
      for (Post post:posts){
          if(post.getUserId() == userId){
              userPosts.add(post);
          }
      }
      return userPosts;
  }

  public Post getUserPost(int userId, int id){
      for(Post post:posts){
          if(post.getUserId() == userId && post.getId() == id)
              return post;
      }
      return null;
  }

  public Post createUserPost(Post post){
      posts.add(post);
      return post;
  }

  public Post deleteUserPost(int id, int userId){
      Iterator<Post> iterator = posts.iterator();
      while (iterator.hasNext()){
          Post post = iterator.next();
          if(post.getUserId() == userId && post.getId() == id){
              iterator.remove();
              return post;
          }
      }
      return null;
  }

  public void deleteByUserId(int userId){
      Iterator<Post> iterator = posts.iterator();
      while (iterator.hasNext()){
          Post post = iterator.next();
          if(post.getUserId() == userId){
              iterator.remove();
          }
      }
  }
}
