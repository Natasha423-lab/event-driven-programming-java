module programmaticdemo.week08.javafx.bindings {
    requires javafx.controls;
    requires javafx.fxml;

    opens programmaticdemo.week08.javafx.bindings to javafx.fxml;
    exports programmaticdemo.week08.javafx.bindings;
    opens com.example to javafx.fxml;
    exports com.example;
}
