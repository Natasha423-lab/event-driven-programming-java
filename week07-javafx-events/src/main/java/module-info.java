module programmaticdemo.week07.javafx.events {
    requires javafx.controls;
    requires javafx.fxml;

    opens programmaticdemo.week07.javafx.events to javafx.fxml;
    exports programmaticdemo.week07.javafx.events;
    opens com.example to javafx.fxml;
    exports com.example;

}
