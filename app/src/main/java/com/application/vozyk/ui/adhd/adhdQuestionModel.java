package com.application.vozyk.ui.adhd;

public class adhdQuestionModel {

    public final String[] mQues = {
            "1. How often do you have trouble wrapping up the final details of a project, once the challenging parts have been done?",
            "2. How often do you have difficulty getting things in order when you have to do a task that requires organization?",
            "3. How often do you have problems remembering appointments or obligations?",
            "4. When you have a task that requires a lot of thought, how often do you avoid or delay getting started?",
            "5. How often do you fidget or squirm with your hands or feet when you have to sit down for a long time?",
            "6. How often do you feel overly active and compelled to do things, like you were driven by a motor?"
    };

    private final String[][] mChoices = {
            {"Never", "Rarely", "Sometimes", "Often", "Very Often"},
            {"Never", "Rarely", "Sometimes", "Often", "Very Often"},
            {"Never", "Rarely", "Sometimes", "Often", "Very Often"},
            {"Never", "Rarely", "Sometimes", "Often", "Very Often"},
            {"Never", "Rarely", "Sometimes", "Often", "Very Often"},
            {"Never", "Rarely", "Sometimes", "Often", "Very Often"},
    };

    public String getQuestion(int i){
        return mQues[i];
    }

    public String getChoiceA(int i){
        return mChoices[i][0];
    }

    public String getChoiceB(int i){
        return mChoices[i][1];
    }

    public String getChoiceC(int i){
        return mChoices[i][2];
    }

    public String getChoiceD(int i){
        return mChoices[i][3];
    }

    public String getChoiceE(int i){
        return mChoices[i][4];
    }

    public int getLength(){
        return mQues.length;
    }
}
