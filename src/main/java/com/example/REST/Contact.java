package com.example.REST;

public class Contact {
    String name;
    int phoneNumber;
    String mail;

    public Contact(String name, int phoneNumber, String mail) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", mail='" + mail + '\'' +
                '}';
    }

    public void update(Contact contact){
        if (contact.name != null){
            this.name = contact.name;
        }
        if (contact.phoneNumber > 0){
            this.phoneNumber = contact.phoneNumber;
        }


//        if (contact.email != null){
//            this.email = contact.email;
//        }
    }
}
