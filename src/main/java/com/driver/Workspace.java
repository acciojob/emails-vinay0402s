package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId,Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
        // The inboxCapacity is equal to the maximum value an integer can store.
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
       calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int maxMeeting=0;
        int currMeeting=0;

        LocalTime currtime = LocalTime.of(0,0);
        // Sort the calendar by the end times of the meetings
        calendar.sort((m1, m2) -> m1.getEndTime().compareTo(m2.getEndTime()));//lambda funct

        for(Meeting meeting : calendar){
            // Check if the meeting starts after the current time, then attend the meeting
            if(meeting.getStartTime().isAfter(currtime)){
                // Update the current time after attending meeting
                currtime=meeting.getEndTime();
                // Increment the currmeetings
                currMeeting++;
            }
        }

        maxMeeting = Math.max(maxMeeting,currMeeting);
        return maxMeeting;
    }
}
