package com.application.vozyk.ui.bipolar;

public class bipolarQuestionModel {

    public String[] mQues = {
            "1. At times I am much more talkative or speak much faster than usual.",
            "2. There have been times when I was much more active or did many more things than usual.",
            "3. I get into moods where I feel very speeded up or irritable.",
            "4. There have been times when I have felt both high (elated) and low (depressed) at the same time.",
            "5. At times I have been much more interested in sex than usual.",
            "6. My self-confidence ranges from great self- doubt to equally great overconfidence.",
            "7. There have been GREAT variations in the quantity or quality of my work.",
            "8. For no apparent reason I sometimes have been VERY angry or hostile.",
            "9. I have periods of mental dullness and other periods of very creative thinking.",
            "10. At times I am greatly interested in being with people and at other times I just want to be left alone with my thoughts.",
            "11. I have had periods of great optimism and other periods of equally great pessimism.",
            "12. I have had periods of tearfulness and crying and other times when I laugh and joke excessively."
    };

    private final String[][] mChoices = {
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"},
            {"Not at all", "Just a little", "Somewhat", "Moderately", "Quite a lot", "Very much"}
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

    public String getChoiceF(int i){
        return mChoices[i][5];
    }

    public int getLength(){
        return mQues.length;
    }
}
