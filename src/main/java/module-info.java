module com.calc.calculadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;

    opens com.calc.calculadora to javafx.fxml;
    exports com.calc.calculadora;


}