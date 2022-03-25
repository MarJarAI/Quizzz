package client.scenes.multiplayer;

import commons.Answer;
import commons.player.Player;
import commons.player.SimpleUser;

public interface QuestionCtrl {

    public void postQuestion(Answer answer);

    public void resetUI();

    public void showDisconnect(SimpleUser playerDisconnect);
}
