module br.edu.fapce.view {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.management;
	requires org.testfx.junit5;
	requires javafx.graphics;

    opens br.com.pickshow.view to javafx.fxml;
    exports br.com.pickshow.view;
    exports br.com.pickshow.controller;
    exports br.com.pickshow.model;
}