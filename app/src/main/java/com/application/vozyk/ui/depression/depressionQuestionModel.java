package com.application.vozyk.ui.depression;

public class depressionQuestionModel {

    public final String[] mQues = {
            "1. Little interest or pleasure in doing things",
            "2. Feeling down, depressed, or hopeless",
            "3. Trouble falling or staying asleep, or sleeping too much",
            "4. Feeling tired or having little energy",
            "5. Poor appetite or overeating",
            "6. Feeling bad about yourself – or that you are a failure or have let yourself or your family down",
            "7. Trouble concentrating on things, such as reading the newspaper or watching television",
            "8. Moving or speaking so slowly that other people could have noticed? Or the opposite – being so fidgety or restless that you have been moving around a lot more than usual",
            "9. Thoughts that you would be better off dead or of hurting yourself in some way"
    };

    private final String[][] mChoices = {
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
            {"Not at all sure", "Several days", "Over half the days", "Nearly every day"},
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
