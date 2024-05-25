module com.lamongan234.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    // requires com.google.gson;
    requires java.xml;
    requires org.yaml.snakeyaml;

    opens com.lamongan234.gui to javafx.fxml;
    exports com.lamongan234.gui;
}