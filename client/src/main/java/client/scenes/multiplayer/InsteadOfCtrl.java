package client.scenes.multiplayer;

import client.scenes.MainCtrl;
import client.utils.ServerUtils;
import commons.Answer;
import commons.QuestionInsteadOf;
import commons.player.SimpleUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class InsteadOfCtrl implements QuestionCtrl {

    @FXML
    private Text questionTitle, timer, score, points, answer, option4, correct_guess, questionCount, disconnect;

    @FXML
    private AnchorPane emoji;

    @FXML
    private ImageView timerImage;

    @FXML
    private RadioButton answer1, answer2, answer3;

    @FXML
    private Button heart, cry, laugh, angry, glasses;

    @FXML
    private ImageView image4, heartPic, cryPic, laughPic, angryPic, glassesPic;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Pane confirmationExit;

    private Image timerImageSource;

    private TimerTask scheduler;

    private QuestionInsteadOf question;
    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private final GameCtrl gameCtrl;

    @Inject
    public InsteadOfCtrl(ServerUtils server, MainCtrl mainCtrl, GameCtrl gameCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
        this.gameCtrl = gameCtrl;
        try {
            timerImageSource = new Image(new FileInputStream("client/src/main/resources/images/timer.png"));
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find timer image.");
        }
    }

    /**
     * Initiates the Instead Of question, sets the scene and starts the timer
     *
     * @param question
     */
    public void init(QuestionInsteadOf question){
        this.question = question;
        timerImage.setImage(timerImageSource);
        disablePopUp(null);
        questionTitle.setText(question.getTitle());
        questionCount.setText("Question " + question.getNumber() + "/20");
        option4.setText(question.getActivity().getTitle());
        progressBar.setProgress(question.getNumber() / 20.0d + 0.05);
        answer1.setText(question.getAnswers()[0]);
        answer2.setText(question.getAnswers()[1]);
        answer3.setText(question.getAnswers()[2]);
        answer1.setDisable(false);
        answer2.setDisable(false);
        answer3.setDisable(false);
        try {
            Image image = new Image(server.getImage(question.getActivity()));
            image4.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find image");
        }
        scheduler = new TimerTask() {
            @Override
            public void run () {
                int timeLeft = server.getTimeLeft(gameCtrl.getPlayer());
                Platform.runLater(() -> {
                    timer.setText(String.valueOf(Math.round(timeLeft / 1000d)));
                });
            }
        };
        new Timer().scheduleAtFixedRate(scheduler, 0, 100);
    }

    public void answer3Selected(ActionEvent actionEvent) {
        gameCtrl.submitAnswer(new Answer((long) 3));
    }

    public void answer2Selected(ActionEvent actionEvent) {
        gameCtrl.submitAnswer(new Answer((long) 2));
    }

    public void answer1Selected(ActionEvent actionEvent) {
        gameCtrl.submitAnswer(new Answer((long) 1));
    }

    public void disablePopUp(ActionEvent actionEvent) {
        confirmationExit.setVisible(false);
        confirmationExit.setDisable(true);
    }

    public void leaveGame(ActionEvent actionEvent) {
        scheduler.cancel();
        gameCtrl.disconnect();
        mainCtrl.showSplash();
    }

    public void enablePopUp(ActionEvent actionEvent) {
        confirmationExit.setVisible(true);
        confirmationExit.setDisable(false);
        confirmationExit.setStyle("-fx-background-color: #91e4fb; ");
    }

    /**
     * Sends the answer to the server and starts a 5-second countdown
     *
     * @param answer
     */
    @Override
    public void postQuestion(Answer answer) {
        switch (answer.getAnswer().intValue()) {
            case 1:
                answer1.setDisable(true);
                answer2.setDisable(true);
                answer3.setDisable(true);
                answer1.setStyle("-fx-background-color: green");
                answer2.setStyle("-fx-background-color: red");
                answer3.setStyle("-fx-background-color: red");
                break;
            case 2:
                answer1.setDisable(true);
                answer2.setDisable(true);
                answer3.setDisable(true);
                answer1.setStyle("-fx-background-color: red");
                answer2.setStyle("-fx-background-color: green");
                answer3.setStyle("-fx-background-color: red");
                break;
            case 3:
                answer1.setDisable(true);
                answer2.setDisable(true);
                answer3.setDisable(true);
                answer1.setStyle("-fx-background-color: red");
                answer2.setStyle("-fx-background-color: red");
                answer3.setStyle("-fx-background-color: green");
                break;
            default:
                System.out.println(answer.getAnswer().intValue());
                throw new IllegalStateException();
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                scheduler.cancel();
                resetUI();
            }
        }, 5000);
    }

    @Override
    public void resetUI() {
        answer1.setStyle("");
        answer2.setStyle("");
        answer3.setStyle("");
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
    }

    /**
     * Displays a message when another player disconnects
     * @param disconnectPlayer
     */
    @Override
    public void showDisconnect(SimpleUser disconnectPlayer) {
        disconnect.setText(disconnectPlayer.getName() + " disconnected");
        disconnect.setVisible(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> disconnect.setVisible(false));
            }
        }, 5000);
    }
}
