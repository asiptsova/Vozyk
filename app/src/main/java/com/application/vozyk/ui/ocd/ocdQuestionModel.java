package com.application.vozyk.ui.ocd;

public class ocdQuestionModel {

    public final String[] mQues = {
            "1. I have saved up so many things that they get in the way.",
            "2. I check things more often than necessary.",
            "3. I get upset if objects are not arranged properly.",
            "4. I feel compelled to count while I am doing things.",
            "5. I find it difficult to touch an object when I know it has been touched by strangers or certain people.",
            "6. I find it difficult to control my own thoughts.",
            "7. I collect things I don’t need.",
            "8. I repeatedly check doors, windows, drawers, etc.",
            "9. I get upset if others change the way I have arranged things.",
            "10. I feel I have to repeat certain numbers.",
            "11. I sometimes have to wash or clean myself simply because I feel contaminated.",
            "12. I am upset by unpleasant thoughts that come into my mind against my will.",
            "13. I avoid throwing things away because I am afraid I might need them later.",
            "14. I repeatedly check gas and water taps and light switches after turning them off.",
            "15. I need things to be arranged in a particular way.",
            "16. I feel that there are good and bad numbers.",
            "17. I wash my hands more often and longer than necessary.",
            "18. I frequently get nasty thoughts and have difficulty in getting rid of them."
    };


    private final String[][] mChoices = {
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
            {"Not at all", "A little", "Moderately", "A lot", "Extremely"},
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
