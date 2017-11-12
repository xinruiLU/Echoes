/*
 * Copyright (c) Team cmput301f17t19, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta
 */

package com.example.cmput301f17t19.echoes;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by taijieyang on 2017/10/22.
 */

public class HabitEvent implements Comparable<HabitEvent> {
    private String Title;
    private Date StartDate;
    private String Comments;
    private byte[] EventPhoto;


    public HabitEvent(String title, Date startDate){
        this.Title = title;
        this.StartDate = startDate;
        this.Comments = "";
    }


    public String getTitle(){
        return this.Title;
    }

    public Date getStartDate(){
        return this.StartDate;
    }

    public String getComments(){
        return this.Comments;
    }

    public byte[] getEventPhoto(){
        return this.EventPhoto;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public void setStartDate(Date startDate){
        this.StartDate = startDate;
    }

    public void setComments(String comments) throws ArgTooLongException {
        if (comments.length() > 20)
            throw new ArgTooLongException();
        else
            this.Comments = comments;

    }

    public void setEventPhoto(byte[] eventPhoto){
        this.EventPhoto = eventPhoto;
    }

    @Override
    /**
     * Compare the date of this HabitEvent to the input habitEvent object
     *
     * @param habitEvent: HabitEvent
     * @return positive integer: if the date of this HabitEvent is after the date of input
     *         0: if the date of this HabitEvent is equal to the date of input
     *         negative integer: if the date of this HabitEvent is before the date of input
     */
    public int compareTo(@NonNull HabitEvent habitEvent) {
        return this.StartDate.compareTo(habitEvent.getStartDate());
    }
}
