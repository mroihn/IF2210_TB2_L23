package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

public class HelloController {
    private static int currTurn = 0;
    private static boolean currPlayer1 = false;
    private static boolean onLadangku = true;

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
    private Button b7Deck;

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
                //fillHandRandom();
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
        b0Next.setOnAction(event -> {
            nextTurn();
        });

        b1Ladangku.getStyleClass().add("button-style");
        b1Ladangku.getStyleClass().add("style-toggled");
        b1Ladangku.setOnAction(event -> {
            showLadangku();
            b1Ladangku.getStyleClass().add("style-toggled");
            b2LadangLawan.getStyleClass().remove("style-toggled");
        });

        b2LadangLawan.getStyleClass().add("button-style");
        b2LadangLawan.setOnAction(event -> {
            showLadangLawan();
            b1Ladangku.getStyleClass().remove("style-toggled");
            b2LadangLawan.getStyleClass().add("style-toggled");
        });

        b3Toko.getStyleClass().add("button-style");
        b4SaveState.getStyleClass().add("button-style");
        b5LoadState.getStyleClass().add("button-style");
        b6LoadPlugin.getStyleClass().add("button-style");

        b7Deck.getStyleClass().add("button-style");
        b7Deck.setOnAction(event -> {
            openPopup();
        });

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

    protected void nextTurn(){
        if (currTurn < 20){

            if (currPlayer1){
                if(currTurn <2){
                    fillHandRandom();
                }
                if (!onLadangku){
                    saveAll(handPlayer1, hand);
                    saveAll(ladangPlayer2, ladangA);
                    clearAll(hand);
                    clearAll(ladangA);
                    loadAll(handPlayer2, hand);
                    loadAll(ladangPlayer1, ladangA);
                }
                else {
                    saveAll(handPlayer1, hand);
                    saveAll(ladangPlayer1, ladangA);
                    clearAll(hand);
                    clearAll(ladangA);
                    loadAll(handPlayer2, hand);
                    loadAll(ladangPlayer2, ladangA);
                }
            }
            else{

                if (!onLadangku){
                    saveAll(handPlayer2, hand);
                    saveAll(ladangPlayer1, ladangA);
                    clearAll(hand);
                    clearAll(ladangA);
                    loadAll(handPlayer1, hand);
                    loadAll(ladangPlayer2, ladangA);
                }

                else {
                    saveAll(handPlayer2, hand);
                    saveAll(ladangPlayer2, ladangA);
                    clearAll(hand);
                    clearAll(ladangA);
                    loadAll(handPlayer1, hand);
                    loadAll(ladangPlayer1, ladangA);
                }
            }

            currPlayer1 = !currPlayer1;
            currTurn++;
            clockCounter.setText(currTurn+"/"+"20");
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
            //System.out.println(list[i]);
        }
    }

    protected void loadAll(Card[] list, GridPane Container){
        for (int i = 0; i < Container.getChildren().size(); i++) {
            if (list[i]!=null) {
                ((Pane) Container.getChildren().get(i)).getChildren().add(list[i]);
            }
        }
    }

    protected void showLadangku(){
        if (!currPlayer1){
            saveAll(ladangPlayer1,ladangA);
            clearAll(ladangA);
            loadAll(ladangPlayer2,ladangA);
        }
        else{
            saveAll(ladangPlayer2,ladangA);
            clearAll(ladangA);
            loadAll(ladangPlayer1,ladangA);
        }
        onLadangku=true;
    }

    protected void showLadangLawan(){
        if (currPlayer1){
            saveAll(ladangPlayer1,ladangA);
            clearAll(ladangA);
            loadAll(ladangPlayer2,ladangA);
        }
        else{
            saveAll(ladangPlayer2,ladangA);
            clearAll(ladangA);
            loadAll(ladangPlayer1,ladangA);
        }
        onLadangku=false;
    }

    @FXML
    private void openPopup(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shufflePopup.fxml"));
        try {

            Rectangle overlay = new Rectangle(anchor.getWidth(), anchor.getHeight());
            overlay.setFill(Color.rgb(0, 0, 0, 0.5));
            anchor.getChildren().add(overlay);

            Parent root = fxmlLoader.load();

            ShufflePopupController popupController = fxmlLoader.getController();
            popupController.setMainController(this);
            popupController.setFreeslot();
            popupController.setOverlay(overlay);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            //scene.setFill(null);
            popupStage.setScene(scene);
            popupStage.showAndWait();

            anchor.getChildren().remove(overlay);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void addToCurHand(Card[] cards){
        Card temp;
        for (int i = 0;i< 6;i++){
            //System.out.println("ini adalah idx: " + idx);
            if (((Pane) hand.getChildren().get(i)).getChildren().isEmpty()){
                temp = pop(cards);

                if (temp!=null) {
                    ((Pane) hand.getChildren().get(i)).getChildren().add(temp);
                }
            }

        }
    }

    protected int GetFreeSlotHand(){
        int cnt = 0;
        for (int i = 0; i < hand.getChildren().size(); i++) {
            if (((Pane) hand.getChildren().get(i)).getChildren().isEmpty()){
                cnt++;
            }
            System.out.println(cnt);
        }
        return cnt;
    }

    public Card pop(Card[] cards){
        for (int i = 0; i < cards.length; i++) {
            System.out.println("i = " + i);
            if (cards[i]!=null){
                Card temp = cards[i];
                cards[i] = null;
                return temp;
            }
        }
        return null;
    }
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}