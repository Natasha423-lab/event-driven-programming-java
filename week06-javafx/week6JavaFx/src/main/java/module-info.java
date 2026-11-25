module com.riara.eventdriven.week6javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.riara.eventdriven.week6javafx to javafx.fxml;
    exports com.riara.eventdriven.week6javafx;
}
