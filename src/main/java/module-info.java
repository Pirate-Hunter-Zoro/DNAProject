module DNAProject {

    requires javafx.controls;
    requires javafx.fxml;

    exports UserInterface;
    opens UserInterface to javafx.fxml;

}