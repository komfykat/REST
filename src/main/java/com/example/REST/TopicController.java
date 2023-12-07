package com.example.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopicController {
    ArrayList<Topic> topics = new ArrayList<Topic>();
    ArrayList<User> users = new ArrayList<User>();


    // curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"Math\"}" http://localhost:8080/topics
    @PostMapping("topics")
    public ResponseEntity<Void> addTopic(@RequestBody Topic topic) {
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


    // curl -X PUT -H "Content-Type: application/json" -d "{\"name\" : \"Programming\"}" http://localhost:8080/topics/1
    @PutMapping("topics/{index}")
    public ResponseEntity<Void> updateTopic(@PathVariable("index") Integer index, @RequestBody Topic topic) {
        topics.set(index, topic);
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

    // curl -X POST -H "Content-Type: application/json" -d "{\"text\" : \"I love math!\", \"username\" : \"DostoevskyFM"\}" http://localhost:8080/topics/comment
    @PostMapping("topics/comment/{index}")
    public ResponseEntity<Void> addTopic(@RequestBody Comment comment, @PathVariable("index") int index) {
        topics.get(index).getComments().add(comment);
        return ResponseEntity.accepted().build();
    }

}
