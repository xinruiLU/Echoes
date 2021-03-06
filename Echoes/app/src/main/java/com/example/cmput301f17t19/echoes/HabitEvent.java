/*
 * Class Name: HabitEvent
 *
 * Version: Version 1.0
 *
 * Date: October 22nd, 2017
 *
 * Copyright (c) Team cmput301f17t19, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta
 */

package com.example.cmput301f17t19.echoes;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * HabitEvent object class
 *
 * @author Taijie Yang
 * @version 1.0
 * @since 1.0
 */
public class HabitEvent implements Comparable<HabitEvent> {
    private String Title;
    private Date StartDate;
    private String Comments;
    private byte[] EventPhoto;

    /**
     * Constructor for the HabitEvent object
     *
     * @param title: String, the title of the HabitEvent
     * @param startDate: Date, the date of the HabitEvent
     */
    public HabitEvent(String title, Date startDate){
        this.Title = title;
        this.StartDate = startDate;
        this.Comments = "";
    }

    /**
     * Get the title of the HabitEvent
     *
     * @return String: the title of the HabitEvent
     */
    public String getTitle(){
        return this.Title;
    }

    /**
     * Get the event date of the HabitEvent
     *
     * @return Date: the date that the HabitEvent done
     */
    public Date getStartDate(){
        return this.StartDate;
    }

    /**
     * Get the comment of the HabitEvent
     *
     * @return String: the comment of the HabitEvent
     */
    public String getComments(){
        return this.Comments;
    }

    /**
     * Get the photo of the HabitEvent
     *
     * @return byte[]: the byte array of the photo of the HabitEvent
     */
    public byte[] getEventPhoto(){
        return this.EventPhoto;
    }

    /**
     * Set the type of the HabitEvent
     *
     * @param title: String, the type of the HabitEvent
     */
    public void setTitle(String title){
        this.Title = title;
    }

    /**
     * Set the event date of the HabitEvent
     *
     * @param startDate: Date, the event date of the HabitEvent
     */
    public void setStartDate(Date startDate){
        this.StartDate = startDate;
    }

    /**
     * Set the comment of the HabitEvent
     *
     * @param comments: String, the comment of the HabitEvent
     * @throws ArgTooLongException: the comment exceeds 20 characters
     */
    public void setComments(String comments) throws ArgTooLongException {
        if (comments.length() > 20)
            throw new ArgTooLongException();
        else
            this.Comments = comments;

    }

    /**
     * Set the event photo of the HabitEvent
     *
     * @param eventPhoto: byte[], the Photo stored in bytes array
     */
    public void setEventPhoto(byte[] eventPhoto){
        this.EventPhoto = eventPhoto;
    }

    /**
     * Compare the date of this HabitEvent to the input habitEvent object
     *
     * @param habitEvent: HabitEvent
     * @return positive integer: if the date of this HabitEvent is after the date of input
     *         0: if the date of this HabitEvent is equal to the date of input
     *         negative integer: if the date of this HabitEvent is before the date of input
     */
    @Override
    public int compareTo(@NonNull HabitEvent habitEvent) {
        return this.StartDate.compareTo(habitEvent.getStartDate());
    }
}
