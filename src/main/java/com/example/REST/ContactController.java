package com.example.REST;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ContactController {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    ArrayList<Contact> contacts = new ArrayList<>();



    public ResponseEntity<List<Contact>> returnContacts() {
        return ResponseEntity.ok(contacts);
    }

    //     curl http://localhost:8080/contacts
    @GetMapping("contacts")
    public ResponseEntity<List<Contact>> getContacts() {

        return ResponseEntity.ok(contacts);
    }

    // curl http://localhost:8080/contacts/?text=123
    @GetMapping("contacts/textSearch")
    public ResponseEntity<List<String>> getContacts(@RequestParam(value = "text", defaultValue = "test") String text) {
        if (!Objects.equals(text, "test")) {
            ArrayList<String> contactsToPrint = new ArrayList<>();
            for (Contact contact : contacts) {
                if (contact.name.contains(text)) {
                    contactsToPrint.add(contact.name);
                }
            }
            return ResponseEntity.ok(contactsToPrint);
        }
        return ResponseEntity.badRequest().build();
    }

    // curl http://localhost:8080/contacts/sort
    @GetMapping("contacts/sort")
    public ResponseEntity<List<String>> getSortedContacts(){
        ArrayList<String> namesToPrint = new ArrayList<>();
        for (Contact contact : contacts){
            namesToPrint.add(contact.name);
        }
        Collections.sort(namesToPrint);
        return ResponseEntity.ok(namesToPrint);
    }

    // curl http://localhost:8080/contacts/page?number=12&limit=20

    @GetMapping("contacts/page")
    public ResponseEntity<List<Contact>> getPagedContacts(@RequestParam(name = "number", value = "number", required = true) int number, @RequestParam(name = "limit", value = "limit", required = true) int limit){
        if (number < 0 || limit <= 0){
            return ResponseEntity.badRequest().build();
        }
        else{
            ArrayList<Contact> contactsToReturn = new ArrayList<>();
            for (int i = limit * number; i < limit * number + limit; i ++){
                contactsToReturn.add(contacts.get(i));
            }
            return ResponseEntity.ok(contactsToReturn);
        }
    }
    // curl -X DELETE http://localhost:8080/contacts/1
    @DeleteMapping("contacts/{index}")
    public ResponseEntity<Void> deleteContact(@PathVariable("index") Integer
                                                      index) {
        if (index > 0 && index < contacts.size()) {
            contacts.remove((int) index);
        }
        return ResponseEntity.noContent().build();
    }

    // curl http://localhost:8080/contacts/1
    @GetMapping("contacts/{index}")
    public ResponseEntity<Contact> getContact(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(contacts.get(index));
    }

    // curl -X PUT -H "Content-Type: application/json" -d "{\"phoneNumber\" : \"8921\"}" http://localhost:8080/contacts/1
    @PutMapping("contacts/{index}")
    public ResponseEntity<Void> updateContact(@PathVariable("index") Integer index, @RequestBody Contact contact) {
        contacts.get(index).update(contact);
        return ResponseEntity.accepted().build();
    }

    // curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"Max\" , \"phoneNumber\" : \"8999111\", \"mail\" : \"mail@gmail.com\"}" http://localhost:8080/contacts
    @PostMapping("contacts")
    public ResponseEntity<Void> addContact(@RequestBody Contact contact) {
        contacts.add(contact);
        return ResponseEntity.accepted().build();
    }
}
