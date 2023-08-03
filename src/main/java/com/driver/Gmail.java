package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    private int inboxCapacity; //maximum number of mails inbox can store
    private ArrayList<Mail> inbox;
    private ArrayList<Mail> trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        inbox = new ArrayList<Mail>();
        trash = new ArrayList<Mail>();
    }

    public Gmail(String emailId){
        super(emailId);
        this.inboxCapacity=Integer.MAX_VALUE;
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

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if(inbox.size() >= inboxCapacity){
            Mail oldestMail = inbox.get(0);
            trash.add(oldestMail);//mail go to trash
            inbox.remove(oldestMail);//mail remove from inbox
        }
        //add new mail
        inbox.add(new Mail(date, sender, message));
    }


    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail mail : inbox){
            if(mail.message.equals(message)){
                trash.add(mail);
                inbox.remove(mail);
                return;
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
      Mail oldestMail = inbox.get(0);
      return oldestMail.message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int cnt=0; //no of mails
        for(Mail mail : inbox){
            if(mail.date.equals(start) || mail.date.equals(end) || (mail.date.after(start) && mail.date.before(end))){
                cnt++;//found mail in range
            }
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
