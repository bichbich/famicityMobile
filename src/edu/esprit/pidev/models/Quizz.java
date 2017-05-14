/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

/**
 *
 * @author asmouna
 */
public class Quizz {
   
    private int idQuizz;
    private String quizz;
    private String question;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private int reponse;
    private String reponse_correcte;

    public Quizz() {
    }

    public Quizz(int idQuizz, String question, String reponse1, String reponse2, String reponse3, int reponse, String quizz, String reponse_correcte) {
        this.idQuizz = idQuizz;
        this.quizz = quizz;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse = reponse;
        this.reponse_correcte = reponse_correcte;
    }

    
    
    public Quizz(int idQuizz, String quizz, String question, String reponse1, String reponse2, String reponse3, int reponse) {
        this.idQuizz = idQuizz;
        this.quizz = quizz;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse = reponse;
    }

    public Quizz(String quizz, String question, String reponse1, String reponse2, String reponse3, int reponse, String reponse_correcte) {
        this.quizz = quizz;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse = reponse;
        this.reponse_correcte = reponse_correcte;
    }

    public Quizz(String reponse_correcte) {
        this.reponse_correcte = reponse_correcte;
    }
    
    
    
      public Quizz(String reponse1, String reponse2, String reponse3) {
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
    }
        public Quizz(int idQuizz,String question, String reponse1, String reponse2,String reponse3 ,int reponse, String quizz ) {
        this.idQuizz = idQuizz;
        this.quizz = quizz;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse = reponse;
    }

    public Quizz(String quizz, String question, String reponse1, String reponse2, String reponse3, int reponse) {
        this.quizz = quizz;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse = reponse;
    }
    

    public int getIdQuizz() {
        return idQuizz;
    }

    public void setIdQuizz(int idQuizz) {
        this.idQuizz = idQuizz;
    }

    public String getQuizz() {
        return quizz;
    }

    public void setQuizz(String quizz) {
        this.quizz = quizz;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getReponse_correcte() {
        return reponse_correcte;
    }

    public void setReponse_correcte(String reponse_correcte) {
        this.reponse_correcte = reponse_correcte;
    }

    @Override
    public String toString() {
        return "Quizz{" + "idQuizz=" + idQuizz + ", quizz=" + quizz + ", question=" + question + ", reponse1=" + reponse1 + ", reponse2=" + reponse2 + ", reponse3=" + reponse3 + ", reponse=" + reponse + ", reponse_correcte=" + reponse_correcte + '}';
    }
    
    

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idQuizz;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quizz other = (Quizz) obj;
        if (this.idQuizz != other.idQuizz){
        return false;
        }
        return true;
    
}
}