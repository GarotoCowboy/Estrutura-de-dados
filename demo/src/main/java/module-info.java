module br.com.projeto.ed.avl {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens br.com.projeto.ed.avl to javafx.fxml;
    exports br.com.projeto.ed.avl;
    opens br.com.projeto.ed.avl.controller to javafx.fxml;
    exports br.com.projeto.ed.avl.controller;
}