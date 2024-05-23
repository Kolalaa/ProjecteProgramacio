module cat.boscdelacoma.sistemapersistent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens cat.boscdelacoma.sistemapersistent to javafx.fxml;
    exports cat.boscdelacoma.sistemapersistent;
}
