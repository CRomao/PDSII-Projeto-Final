module br.edu.fapce.view {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.pickshow.view to javafx.fxml;
    exports br.com.pickshow.view;
    exports br.com.pickshow.controller;
    exports br.com.pickshow.model;
}