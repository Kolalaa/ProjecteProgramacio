module cat.boscdelacoma.sistemapersistent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.persistence;
    opens cat.boscdelacoma.sistemapersistent to javafx.fxml;
    opens cat.boscdelacoma.sistemapersistent.model.business.entities to javafx.base;
    
    opens cat.boscdelacoma.sistemapersistent.controller to javafx.fxml;
    exports cat.boscdelacoma.sistemapersistent;
    exports cat.boscdelacoma.sistemapersistent.controller;
    exports cat.boscdelacoma.sistemapersistent.model.persistence.daos;
    exports cat.boscdelacoma.sistemapersistent.model.business.entities;
}
