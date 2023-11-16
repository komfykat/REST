package com.example.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private final List<String> messages = new ArrayList<>();


    //curl http://localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }

    //curl http://localhost:8080/messages/text
    @GetMapping("messages/{text}")
    public ResponseEntity<Void> getMessages(@PathVariable("text") String text) {
        if (!isInteger(text)){
            getMessage(Integer.parseInt(text));
        }
        else {
            ArrayList<String> messagesToReturn = new ArrayList<>();
            for (String message : messages) {
                if (message.contains(text)) {
                    messagesToReturn.add(message);
                }
            }
        }
    }

    @GetMapping("messages/{index}")
    private ResponseEntity<List<String>> getMessage(@PathVariable("text") String
                                                     text){
        return ResponseEntity.ok(messages.get(index));
    }



    //curl http://localhost:8080/messages/1
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index){
        return ResponseEntity.ok(messages.get(index));
    }

    //curl http://localhost:8080/messages/search/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> getMessage() {
        return ResponseEntity.ok(messages.size());
    }

    //curl http://localhost:8080/messages/search/text
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> getMessage(@PathVariable("text") String text) {
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            if (message.contains(text)) {
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.ok(-1);
    }

    //curl -X POST -H "Content-Type: text/plain" -d "message" http://localhost:8080/messages
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl -X POST -H "Content-Type: text/plain" -d "message" http://localhost:8080/messages
//    @PostMapping("messages/{index}/create")
//    public ResponseEntity<Void> addMessage(@RequestBody String text, @PathVariable("index") Integer index) {
//        if (index <= messages.size()){
//            messages.set(index, text);
//        }
//        else{
//            for (int i = 0; i < index - messages.size() - 1; i ++){
//                messages.add("");
//            }
//        }
//        return ResponseEntity.accepted().build();
//    }


    //curl -X DELETE http://localhost:8080/messages/1
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X DELETE http://localhost:8080/messages/search/text
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteText(@PathVariable("text") String text) {
        ArrayList<String> messagesToDelete = new ArrayList<>();
        for (String message : messages) {
            if (message.contains(text)) {
                messagesToDelete.add(message);
            }

        }
        if (!messagesToDelete.isEmpty()) {
            messages.removeAll(messagesToDelete);
        }
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: text/plain" -d "data" http://localhost:8080/messages/1
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
}
