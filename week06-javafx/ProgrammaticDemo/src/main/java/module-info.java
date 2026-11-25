module programmaticdemo {
    requires javafx.controls;
    requires javafx.fxml;

    opens programmaticdemo to javafx.fxml;
    exports programmaticdemo;
    
}
