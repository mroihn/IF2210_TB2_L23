package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private static int currTurn = 0;
    private static boolean currPlayer1 = false;

    private Card[] handPlayer1 = new Card[6];
    private Card[] handPlayer2 = new Card[6];

    private Card[] ladangPlayer1 = new Card[20];
    private Card[] ladangPlayer2 = new Card[20];

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox buttonContainer;

    @FXML
    private Pane turnClock;

    @FXML
    private Label player1Gold;

    @FXML
    private Label player2Gold;

    @FXML
    private Label clockCounter;

    @FXML
    private Label clockLabel;

    @FXML
    private Pane playerGoldContainer;

    @FXML
    private GridPane ladangA;

    @FXML
    private GridPane hand;

    @FXML
    private Button b0Next;

    @FXML
    private Button b1Ladangku;

    @FXML
    private Button b2LadangLawan;

    @FXML
    private Button b3Toko;

    @FXML
    private Button b4SaveState;

    @FXML
    private Button b5LoadState;

    @FXML
    private Button b6LoadPlugin;

    @FXML
    public void initialize() {
        anchor.getStyleClass().add("encompassing-style");

        // hand
        int i;
        i=0;
        for (Node pane : hand.getChildren()) {
            if (pane instanceof Pane) {
                pane.getStyleClass().add("card-style");
                pane.setId("d"+i);

//                Card card = new Card();
//                card.setId("c"+i);
//                ((Pane) pane).getChildren().add(card);
//                System.out.println(card.getName());
                fillHandRandom();
                makeDraggable((Pane) pane);

                i++;
            }
        }

        // ladangA
        i=0;
        for (Node pane : ladangA.getChildren()) {
            if (pane instanceof Pane) {
                pane.getStyleClass().add("card-style");
                pane.setId("l"+i);

                makeDraggable((Pane) pane);

                //pane.setOnDragDetected();
                i++;
            }
        }

        //labels and clock
        turnClock.getStyleClass().add("clock-style");
        clockLabel.getStyleClass().add("label-style");
        clockCounter.getStyleClass().add("label-style");
        player1Gold.getStyleClass().add("label-style");
        player2Gold.getStyleClass().add("label-style");
        playerGoldContainer.getStyleClass().add("container-style");

        //button style
        b0Next.getStyleClass().add("button-style");
        nextButtonFunc(b0Next);

        b1Ladangku.getStyleClass().add("button-style");
        b2LadangLawan.getStyleClass().add("button-style");
        b3Toko.getStyleClass().add("button-style");
        b4SaveState.getStyleClass().add("button-style");
        b5LoadState.getStyleClass().add("button-style");
        b6LoadPlugin.getStyleClass().add("button-style");

        buttonContainer.getStyleClass().add("container-style");
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);

    }

    protected void makeDraggable(Pane pane) {
        pane.setOnDragDetected(
                event -> {
                    if (!pane.getChildren().isEmpty()) {
                        System.out.println("Start Drag: " + pane.getId());

                        Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();

                        content.putString(pane.getId());
                        db.setContent(content);

                        Image snapshot = pane.getChildren().get(0).snapshot(null,null);
                        db.setDragView(snapshot);
                    }
                    event.consume();
                }
        );
        pane.setOnDragOver(
                event -> {
                    //System.out.println("Hovering Over: " + node.getId());
                    if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                }
        );
//        node.setOnDragExited(
//                event -> {
//                    System.out.println("Exited On: " + node.getId());
//                    event.consume();
//                }
//        );
        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String id = db.getString();
                Pane sourcePane = new Pane();
                Pane card = new Pane();
                int idx = Integer.parseInt(id.substring(1));
                Node outerNode;
                if (id.charAt(0) == 'l'){
                    outerNode = ladangA.getChildren().get(idx);}
                else{
                    outerNode = hand.getChildren().get(idx);}

                if (outerNode instanceof Pane) {
                    sourcePane = (Pane) outerNode;
                    Node innerNode = sourcePane.getChildren().get(0);
                    if (innerNode instanceof Pane) {
                        card = (Pane) innerNode;
                    }
                }
                sourcePane.getChildren().clear();
                pane.getChildren().add(card);
            }
            //event.setDropCompleted(success);
            System.out.println("Drag Dropped: " + pane.getId());
            event.consume();
        });
    }
    protected void fillHandRandom(){
        for(int i = 0; i<6; i++){
            if (((Pane) hand.getChildren().get(i)).getChildren().isEmpty()) {
                Card card = new Card();
                card.setId("cd"+i);
                ((Pane) hand.getChildren().get(i)).getChildren().add(card);
            }
        }
    }
    protected void nextButtonFunc(Button button){
        button.setOnAction(event -> {
            nextTurn();
        });
    }
    protected void nextTurn(){
        if (currTurn <= 20){
            clockCounter.setText(currTurn+"/"+"20");
            if (currPlayer1){
                saveAll(handPlayer1,hand);
                saveAll(ladangPlayer1,ladangA);
                clearAll(hand);
                clearAll(ladangA);
               loadAll(handPlayer2,hand);
                loadAll(ladangPlayer2,ladangA);
            }
            else{
                saveAll(handPlayer2,hand);
                saveAll(ladangPlayer2,ladangA);
                clearAll(hand);
                clearAll(ladangA);
                loadAll(handPlayer1,hand);
                loadAll(ladangPlayer1,ladangA);
            }
            currPlayer1 = !currPlayer1;
            currTurn++;
        }
    }

    protected boolean isValid(){
        return true;
    }

    protected void clearAll(GridPane Container){
        for (int i = 0; i < Container.getChildren().size(); i++) {
            ((Pane) Container.getChildren().get(i)).getChildren().clear();
        }
    }

    protected void saveAll(Card[] list, GridPane Container){
        for (int i = 0; i < Container.getChildren().size(); i++) {
            //System.out.println(Container.getChildren());

            if (!((Pane) Container.getChildren().get(i)).getChildren().isEmpty()) {
                Card card = (Card) ((Pane) Container.getChildren().get(i)).getChildren().get(0);
                list[i] = card;
            }
            System.out.println(list[i]);
        }
    }

    protected void loadAll(Card[] list, GridPane Container){
        for (int i = 0; i < Container.getChildren().size(); i++) {
            if (list[i]!=null) {
                ((Pane) Container.getChildren().get(i)).getChildren().add(list[i]);
            }
        }
    }
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}