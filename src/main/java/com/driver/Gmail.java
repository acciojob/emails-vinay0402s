package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    ArrayList<Mail> inbox;
    ArrayList<Mail> trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        this.inbox = new ArrayList<Mail>();
        this.trash = new ArrayList<Mail>();
    }


    private static class Mail{
        private Date date;
        private String sender;
        private  String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if(inbox.size() >= inboxCapacity){
            Mail oldestMail = inbox.remove(0);
            trash.add(oldestMail);//mail go to trash
        }
        //add new mail
        inbox.add(new Mail(date, sender, message));
    }


    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0; i<inbox.size() ; i++){
            if(inbox.get(i).getMessage().equals(message)){
                trash.add(inbox.get(i));
                inbox.remove(i);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
       if(inbox.isEmpty())
           return null;

       Mail latestmail = inbox.get(inbox.size()-1);
       return latestmail.message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        if(inbox.isEmpty())
            return null;
        // Else, return the message of the oldest mail present in the inbox
      //Mail oldestMail = inbox.get(0);
      return inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int cnt=0; //no of mails
        for(int i=0; i<inbox.size() ;i++){
            Mail mail = inbox.get(i);
            if(mail.getDate().compareTo(start)>=0 && end.compareTo(mail.getDate())>=0)
                cnt++;
        }
        return cnt;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
      return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
     return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
         trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }


}
