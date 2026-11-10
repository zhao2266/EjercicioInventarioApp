module org.example.ejercicioinventarioapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.ejercicioinventarioapp to javafx.fxml;
    exports org.example.ejercicioinventarioapp;
}