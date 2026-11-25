module programmaticdemo.week09.async.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens programmaticdemo.week09.async.javafx to javafx.fxml;
    exports programmaticdemo.week09.async.javafx;

    exports com.example;
}
