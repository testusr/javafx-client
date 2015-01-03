package eu.smeo.javafx.trading;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Created by smeo on 1/1/15.
 */
public class BestPriceButtonControl extends Control {
    private StringProperty titleFont;
    private StringProperty titleProperty = new SimpleStringProperty("title", "title");

    public BestPriceButtonControl() {
        getStyleClass().add("bestprice-button");
    }

    public boolean isBackgroundVisible() {
        return true;
    }

    public boolean isKeepAspect() {
        return true;
    }

    public boolean isTitleVisible() {
        return true;
    }

    public boolean isForegroundShadowVisible() {
        return true;
    }

    public String getTitleFont() {
        return "Open Sans";
    }

    public StringProperty titleProperty() {
        return titleProperty;
    }

    public void setTitle(String title){
        titleProperty.setValue(title);
    }

    public String getTitle() {
        return titleProperty.getValue();
    }


    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new BestPriceButtonSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
