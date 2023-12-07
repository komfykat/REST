package com.example.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TopicController {
    ArrayList<Topic> topics = new ArrayList<Topic>();
    ArrayList<User> users = new ArrayList<User>();


    // curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"Math\"}" http://localhost:8080/topics
    @PostMapping("topics")
    public ResponseEntity<Void> addTopic(@RequestBody Topic topic) {
        if(topic.comments == null){
            topic.setComments(new ArrayList<Comment>());
        }
        topics.add(topic);
        return ResponseEntity.accepted().build();
    }


    // curl -X DELETE http://localhost:8080/topics/2
    @DeleteMapping("topics/{index}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("index") Integer
                                                   index) {
        topics.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    // curl http://localhost:8080/topics
    @GetMapping("topics")
    public ResponseEntity<List<Topic>> getTopics() {
        return ResponseEntity.ok(topics);
    }

    // curl http://localhost:8080/topics/2
    @GetMapping("topics/{index}")
        public ResponseEntity<Topic> getTopic(@PathVariable("index") int index){
        return ResponseEntity.ok(topics.get(index));
    }


    // curl -X PUT -H "Content-Type: application/json" -d "{\"name\" : \"LearnProgramming\"}" http://localhost:8080/topics/1
    @PutMapping("topics/{index}")
    public ResponseEntity<Void> updateTopic(@PathVariable("index") Integer index, @RequestBody Topic topic) {
        topics.set(index, topic);
        topics.get(index).setComments(new ArrayList<Comment>());
        return ResponseEntity.accepted().build();
    }

    // curl http://localhost:8080/topics/count
    @GetMapping("topics/count")
    public ResponseEntity<Integer> countTopics(){
        return ResponseEntity.ok(topics.size());
    }

    // curl -X DELETE http://localhost:8080/topics
    @DeleteMapping("topics")
    public ResponseEntity<Void> deleteTopics(){
        topics.clear();
        return ResponseEntity.noContent().build();
    }

    // curl -X POST -H "Content-Type: application/json" -d "{\"text\" : \"I love math!\", \"username\" : \"DostoevskyFM\"}" http://localhost:8080/topics/comment/0
    @PostMapping("topics/comment/{index}")
    public ResponseEntity<Void> addTopic(@RequestBody Comment comment, @PathVariable("index") int index) {
        topics.get(index).getComments().add(comment);
        boolean newUser = true;
        int userIndex = -1;
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if (Objects.equals(comment.username, user.username)) {
                newUser = false;
                userIndex = i;
                break;
            }
        }
        if (newUser){
            User user = new User(comment.username, new ArrayList<String>(List.of(comment.text)));
            users.add(user);
        }
        else{
            users.get(userIndex).comments.add(comment.text);
        }
        return ResponseEntity.accepted().build();
    }

    // curl -X DELETE http://localhost:8080/topics/0/1
    @DeleteMapping("topics/{topicIndex}/{commentIndex}")
    public ResponseEntity<Void> deleteComment(@PathVariable("topicIndex") int topicIndex, @PathVariable("commentIndex") int commentIndex){
        String text = topics.get(topicIndex).getComments().get(commentIndex).text;
        String username = topics.get(topicIndex).getComments().get(commentIndex).username;
        int userIndex = -1;
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if (Objects.equals(username, user.username)) {
                userIndex = i;
                break;
            }
        }
        users.get(userIndex).getComments().remove(text);
        topics.get(topicIndex).getComments().remove(commentIndex);
        return ResponseEntity.noContent().build();

    }

    // curl -X PUT -H "Content-Type: application/json" -d "{\"text\" : \"I do not love math!\"}" http://localhost:8080/topics/0/0
    @PutMapping("topics/{topicIndex}/{commentIndex}")
    public ResponseEntity<Void> updateComment(@PathVariable("topicIndex") int topicIndex, @PathVariable("commentIndex") int commentIndex, @RequestBody Comment comment) {
        comment.username = topics.get(topicIndex).getComments().get(commentIndex).username;
        int userIndex = -1;
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if (Objects.equals(comment.username, user.username)) {
                userIndex = i;
                break;
            }
        }
        int index = users.get(userIndex).getComments().indexOf(comment.text);
        users.get(userIndex).getComments().set(index, comment.text);
        topics.get(topicIndex).getComments().remove(commentIndex);
        topics.get(topicIndex).getComments().add(commentIndex, comment);

        return ResponseEntity.accepted().build();
    }


    // curl http://localhost:8080/topics/Math/comments
    @GetMapping("topics/{topicName}/comments")
    public ResponseEntity<ArrayList<Comment>> getComments(@PathVariable("topicName") String topicName){
        int topicIndex = -1;
        for (int i = 0; i < topics.size(); i++){
            if (Objects.equals(topics.get(i).getName(), topicName)){
                topicIndex = i;
                break;
            }
        }
        if (topicIndex > -1) {
            return ResponseEntity.ok(topics.get(topicIndex).getComments());
        }
        else{
            return ResponseEntity.ok(null);
        }
    }

    // curl http://localhost:8080/topics/userComments/DostoevskyFM
    @GetMapping("topics/userComments/{username}")
    public ResponseEntity<ArrayList<String>> getUserComments(@PathVariable("username") String username){
        int userIndex = -1;
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if (Objects.equals(user.getUsername(), username)){
                userIndex = i;
                break;
            }
        }
        if (userIndex > -1){
            return ResponseEntity.ok(users.get(userIndex).getComments());
        }
        else{
            return ResponseEntity.ok(null);
        }
    }

}
