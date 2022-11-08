package com.application.vozyk.ui.ptsd;

public class ptsdQuestionModel {

    public String[] mQues = {
            "Had nightmares about the event(s) or thought about the event(s) when you did not want to?",
            "Tried hard not to think about the event(s) or went out of your way to avoid situations that reminded you of the event(s)?",
            "Been constantly on guard, watchful, or easily startled?",
            "Felt numb or detached from people, activities, or your surroundings?",
            "Felt guilty or unable to stop blaming yourself or others for the event(s) or any problems the event(s) may have caused?"
    };

    public String getQuestion(int i) {
        return mQues[i];
    }

    public int getLength(){
        return mQues.length;
    }
}
