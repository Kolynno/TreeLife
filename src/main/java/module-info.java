module simulator.life.nick.pointlife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires static lombok;

    opens simulator.life.nick.pointlife to javafx.fxml;
    exports simulator.life.nick.pointlife;
}