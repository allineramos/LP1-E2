module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires org.postgresql.jdbc;
    requires javafx.graphics;
    requires javafx.base;
    opens com.example to javafx.fxml;
    exports com.example;
}
