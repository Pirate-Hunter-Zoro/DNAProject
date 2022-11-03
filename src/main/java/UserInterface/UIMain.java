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

import FileScanner.StringConverterCounter;
import SolvingProblem.SolverLCS;
import SolvingProblem.SolverNeedlemanWunsch;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

//imports to analyze sequence
import FileScanner.Reader;
import SolvingProblem.SolverSubstring;

public class UIMain extends Application{
    private Button btnAnalyze;
    private Button btnQuery;
    private Button btnDatabase;

    private Label lblAnalyzeChoice;
    private Label lblQuery;
    private Label lblDatabase;
    private Label lblResult;
    private Label lblResultNum;
    private Label lblResultExtra;
    private Label lblResultExtra2;


    private String bestMatch;
    File queryFile = null;
    File databaseFile = null;
    private ChoiceBox<String> AnalyzeMethodBox;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        //root node for scene graph
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        Insets inset = new Insets(10,20,10,20);
        root.setPadding(inset);

        //Init the controls for our graph
        initSceneGraph(root);

        //add scene graph to stage
        primaryStage.setScene(new Scene(root, 800, 600));

        //set minimum size
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);

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
        //create hboxs for side by side elements
        HBox DropDownBoxNode = new HBox(10);
        DropDownBoxNode.setAlignment(Pos.CENTER);

        HBox QuerySelectNode = new HBox(10);
        QuerySelectNode.setAlignment(Pos.CENTER);

        HBox DatabaseSelectNode = new HBox(10);
        DatabaseSelectNode.setAlignment(Pos.CENTER);

        VBox OutputNode = new VBox(30);
        OutputNode.setAlignment(Pos.CENTER_LEFT);

        //Create our labels
        lblQuery = new Label("!NO SOURCE SELECTED!");
        lblDatabase = new Label("!NO DATABASE SELECTED!");
        lblAnalyzeChoice = new Label();
        lblAnalyzeChoice.setText("Select an Analysis Method:");
        lblResult = new Label();
        lblResultNum = new Label();
        lblResultExtra = new Label();
        lblResultExtra2 = new Label();

        lblResult = new Label();

        //create our buttons
        btnQuery = new Button();
        btnQuery.setText("Select Query File");
        btnDatabase = new Button();
        btnDatabase.setText("Select Database File");
        btnAnalyze = new Button();
        btnAnalyze.setText("Analyze!");


        //Creating our dropdown List
        AnalyzeMethodBox = new ChoiceBox<String>();
        AnalyzeMethodBox.getItems().addAll("Longest Common Substring",
                "Longest Common Subsequence",
                "Needle-Wunsch Algorithm",
                "Shortest Edit Distance",
                "ALL METHODS"
        );
        AnalyzeMethodBox.setValue("Longest Common Substring"); //setting default value

        //add ChoiceBox + Label to DropDownBoxNode
        DropDownBoxNode.getChildren().addAll(lblAnalyzeChoice, AnalyzeMethodBox);

        FileChooser fileChooser = new FileChooser();
        ExtensionFilter txtExtFilter = new ExtensionFilter("Text Files", "*.txt");
        ExtensionFilter allExtFilter = new ExtensionFilter("All Files", "*.txt");
        fileChooser.getExtensionFilters().addAll(txtExtFilter,allExtFilter);

        //Source Select Node
        QuerySelectNode.getChildren().addAll(btnQuery, lblQuery);
        btnQuery.setOnAction(event -> {
            fileChooser.setTitle("Choose a valid Query File");
            queryFile = fileChooser.showOpenDialog(null);
            if (queryFile != null){
                lblQuery.setText(String.valueOf(queryFile));
            }
        });
        //Database Select Node
        DatabaseSelectNode.getChildren().addAll(btnDatabase,lblDatabase);
        btnDatabase.setOnAction(event -> {
            fileChooser.setTitle("Choose a valid Database File");
            databaseFile = fileChooser.showOpenDialog(null);
            if (databaseFile != null){
                lblDatabase.setText(String.valueOf(databaseFile));
            }
        });

        //OutputNode
        OutputNode.getChildren().add(lblResult);
        OutputNode.getChildren().add(lblResultNum);
        OutputNode.getChildren().add(lblResultExtra);
        OutputNode.getChildren().add(lblResultExtra2);

        //add elements to root
        root.getChildren().add(DropDownBoxNode);
        root.getChildren().add(QuerySelectNode);
        root.getChildren().add(DatabaseSelectNode);
        root.getChildren().add(btnAnalyze);
        root.getChildren().add(OutputNode);


        btnAnalyze.setOnAction(event -> {
            //THIS WILL RUN OUR ANALYZER, with specified value
            lblResultExtra.setText("");
            if (queryFile == null || databaseFile == null){
                lblResult.setText("!PLEASE SELECT VALID INPUT FILES!");
            }
            else if (AnalyzeMethodBox.getValue() == "Longest Common Substring"){
                Reader reader = new Reader(new SolverSubstring(),  queryFile, databaseFile);
                lblResult.setText("Best Match: " +  reader.findBestMatch());
                lblResultNum.setText("Length: " + reader.getCountOfClosestMatch());
            }
            else if (AnalyzeMethodBox.getValue() == "Longest Common Subsequence"){
                Reader reader = new Reader(new SolverLCS(),  queryFile, databaseFile);
                lblResult.setText("Best Match: " +  reader.findBestMatch());
                lblResultNum.setText("Length: " + reader.getCountOfClosestMatch());
            }
            else if (AnalyzeMethodBox.getValue() == "Needle-Wunsch Algorithm"){
                Reader reader = new Reader(new SolverNeedlemanWunsch(),  queryFile, databaseFile);
                lblResult.setText("Best Match: " +  reader.findBestMatch());
                lblResultNum.setText("Length: " + reader.getCountOfClosestMatch());
            }
            else if (AnalyzeMethodBox.getValue() == "Shortest Edit Distance"){
                StringConverterCounter counter = new StringConverterCounter(queryFile, databaseFile);
                lblResult.setText("Best Match: " +  counter.findBestMatch());
                lblResultNum.setText("Number of Edits Required: " + counter.getNumConversionsForClosestMatch());
            }
            else if (AnalyzeMethodBox.getValue() == "ALL METHODS"){
                Reader reader = new Reader(new SolverSubstring(),  queryFile, databaseFile);
                lblResult.setText("Longest Common Substring Best Match:\n" +  reader.findBestMatch() + "\n Length: " + reader.getCountOfClosestMatch());

                reader = new Reader(new SolverLCS(),  queryFile, databaseFile);
                lblResultNum.setText("Longest Common Subsequence Best Match:\n" + reader.findBestMatch() + "\n Length: " + reader.getCountOfClosestMatch());

                reader = new Reader(new SolverNeedlemanWunsch(),  queryFile, databaseFile);
                lblResultExtra.setText("Needle-Wunsch Algorithm Best Match:\n" + reader.findBestMatch() + "\n Length: " + reader.getCountOfClosestMatch());

                StringConverterCounter counter = new StringConverterCounter(queryFile, databaseFile);
                lblResultExtra2.setText("Shortest Edit Distance Best Match:\n" + counter.findBestMatch() + "\n Number of Edits Required: " + counter.getNumConversionsForClosestMatch());

            }
        });

    }
}