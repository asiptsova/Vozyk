package com.application.vozyk.ui.anxiety;

public class anxietyQuestionModel {

    public String[] mQues = {
            "1. Feeling nervous, anxious, or on edge",
            "2. Not being able to stop or control worrying",
            "3. Worrying too much about different things",
            "4. Trouble relaxing",
            "5. Being so restless that it's hard to sit still",
            "6. Becoming easily annoyed or Irritable",
            "7. Feeling afraid as if something awful might happen"
    };

    private final String[][] mChoices = {
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"}
    };

     public String getQuestion(int i){
         return mQues[i];
     }

     public String getChoice1(int i){
         return mChoices[i][0];
     }

    public String getChoice2(int i){
        return mChoices[i][1];
    }

    public String getChoice3(int i){
        return mChoices[i][2];
    }

    public String getChoice4(int i){
        return mChoices[i][3];
    }

    public int getLength(){
        return mQues.length;
    }

}
