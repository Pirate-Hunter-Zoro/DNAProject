/* *****************************************
 * CSCI311 -Algorithm Design & Analysis
 * Fall2022
 * Instructor: Prof. Talmage
 *
 * Name: Alexander Luzetsky
 * Section: 9am
 * Date: 10/26/22
 * Time: 5:06 PM
 *
 * Project: DNAProject
 * Package: UserInterface
 * Class: UImain
 *
 * Description:
 *
 *
 ****************************************
 */

package UserInterface;

import javafx.application.Application;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//imports to analyze sequence
import FileScanner.Reader;
import SolvingProblem.SolverLCS;
import SolvingProblem.SolverNeedlemanWunsch;
import SolvingProblem.SolverSubstring;

public class UImain extends Application{
    private Button btn;
    private Label lblTime;
    private Label lblAnalyzeChoice;

    private ChoiceBox<String> AnalyzeMethodBox;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        //root node for scene graph
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);

        //Init the controls for our graph
        initSceneGraph(root);

        //add scene graph to stage
        primaryStage.setScene(new Scene(root, 600, 500));

        //set title for main stage
        primaryStage.setTitle("311 DNA Sequence Analyzer");

        //display scene
        primaryStage.show();
    }




    /** Initialize all controls and place them in the graph
     *
     * @param root root for our scene
     */
    private void initSceneGraph(VBox root) {
        //create vbox for
        HBox DropDownBoxNode = new HBox(10);
        DropDownBoxNode.setAlignment(Pos.CENTER);

        lblAnalyzeChoice = new Label();
        lblAnalyzeChoice.setText("Select an Analysis Method:");

        lblTime = new Label();

        //create date/time button
        btn = new Button();
        btn.setText("Analyze!");


        //Creating our dropdown List
        AnalyzeMethodBox = new ChoiceBox<String>();
        AnalyzeMethodBox.getItems().addAll("ALL METHODS",
                "Longest Common Substring",
                "Longest Common Subsequence",
                "Needle-Wunsch Algorithm"
        );
        AnalyzeMethodBox.setValue("ALL METHODS"); //setting default value

        //add ChoiceBox + Label to DropDownBoxNode
        DropDownBoxNode.getChildren().add(lblAnalyzeChoice);
        DropDownBoxNode.getChildren().add(AnalyzeMethodBox);

        //add elements to root
        root.getChildren().add(DropDownBoxNode);
        root.getChildren().add(btn);
        root.getChildren().add(lblTime);

        btn.setOnAction(event -> {
            lblTime.setText(AnalyzeMethodBox.getValue());
        });

    }
}
