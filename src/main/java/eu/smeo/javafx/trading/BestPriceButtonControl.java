package eu.smeo.javafx.trading;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Created by smeo on 1/1/15.
 */
public class BestPriceButtonControl extends Control {
    private StringProperty currencyCouple = new SimpleStringProperty("title", "title");

    private SingleSideControl leftSide = new SingleSideControl();
    private SingleSideControl rightSide = new SingleSideControl();


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

    public StringProperty currencyCoupleProperty() {
        return currencyCouple;
    }

    public void setCurrencyCouple(String title){
        currencyCouple.setValue(title);
    }

    public String getCurrencyCouple() {
        return currencyCouple.getValue();
    }

    public SingleSideControl getLeftSide() {
        return leftSide;
    }

    public SingleSideControl getRightSide() {
        return rightSide;
    }

    // ******************** Style related *************************************
    @Override protected Skin createDefaultSkin() {
        return new BestPriceButtonSkin(this);
    }

    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }

    public static class SingleSideControl {
        private StringProperty action;
        private StringProperty bigFigure;
        private StringProperty pips;
        private StringProperty smallFigure;

        SingleSideControl(){
            action = new SimpleStringProperty("action", "---");
            bigFigure = new SimpleStringProperty("bigFigure", "--");
            pips = new SimpleStringProperty("pips", "--");
            smallFigure = new SimpleStringProperty("smallFigure", "--");
        }

        public String getAction() {
            return action.get();
        }

        public StringProperty actionProperty() {
            return action;
        }

        public void setAction(String action) {
            this.action.set(action);
        }

        public String getBigFigure() {
            return bigFigure.get();
        }

        public StringProperty bigFigureProperty() {
            return bigFigure;
        }

        public void setBigFigure(String bigFigure) {
            this.bigFigure.set(bigFigure);
        }

        public String getPips() {
            return pips.get();
        }

        public StringProperty pipsProperty() {
            return pips;
        }

        public void setPips(String pips) {
            this.pips.set(pips);
        }

        public String getSmallFigure() {
            return smallFigure.get();
        }

        public StringProperty smallFigureProperty() {
            return smallFigure;
        }

        public void setSmallFigure(String smallFigure) {
            this.smallFigure.set(smallFigure);
        }
    }
}
