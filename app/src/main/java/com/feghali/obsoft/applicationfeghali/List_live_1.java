package com.feghali.obsoft.applicationfeghali;

/**
 * Created by leilalelou on 8/14/2015.
 */
public class List_live_1 {
    String participant;
    String duree;
    int position;

    public List_live_1(String participant,String duree,int position)
    {
        this.participant=participant;
        this.duree=duree;
        this.position=position;

    }

    public List_live_1(){}

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
