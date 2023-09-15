package com.tsl3060.open.extend.core.notify;

public class CarbonOrderNotifyAnswer implements IAnswer {
    private boolean answer;

    public CarbonOrderNotifyAnswer() {
    }

    public CarbonOrderNotifyAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
