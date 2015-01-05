package eu.smeo.javafx.trading;

import javafx.collections.ListChangeListener;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by smeo on 1/1/15.
 */
public class BestPriceButtonSkin extends SkinBase<BestPriceButtonControl> {
    private static final double PREFERRED_WIDTH = 132;
    private static final double PREFERRED_HEIGHT = 48;
    private static final double MINIMUM_WIDTH = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private static double aspectRatio = PREFERRED_HEIGHT / PREFERRED_WIDTH;

    private static final DropShadow FOREGROUND_SHADOW = new DropShadow();

    private Region main;

    private Text currencyCoupleText;

    private Pane pane;
    private double width;
    private double height;

    private Font titleFont;
    private SidePane leftSidePane;
//    private Group shadowGroup;


    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected BestPriceButtonSkin(BestPriceButtonControl control) {
        super(control);
        System.out.println("BestPriceButton Skin created");
        init();
        initGraphics();
        updateGraphics();
        registerListeners();

    }

    private void registerListeners() {
        getSkinnable().widthProperty().addListener(observable -> resize());
        getSkinnable().heightProperty().addListener(observable -> resize());
        getSkinnable().currencyCoupleProperty().addListener(observable -> updateGraphics());
        getSkinnable().getStyleClass().addListener((ListChangeListener.Change<? extends String> c) -> {
            resize();
            updateGraphics();
        });

        addChangeListenersForSide(getSkinnable().getLeftSide());
        addChangeListenersForSide(getSkinnable().getRightSide());
    }

    private void addChangeListenersForSide(BestPriceButtonControl.SingleSideControl leftSide) {
        leftSide.actionProperty().addListener(observable -> updateGraphics());
        leftSide.bigFigureProperty().addListener(observable -> updateGraphics());
        leftSide.pipsProperty().addListener(observable -> updateGraphics());
    }

    private void initGraphics() {

        main = new Region();
        main.getStyleClass().setAll("main");
        //  main.setOpacity(getSkinnable().isBackgroundVisible() ? 1 : 0);

        currencyCoupleText = new Text(getSkinnable().getCurrencyCouple());


        leftSidePane = new SidePane(titleFont, 0.1666666667);


//        title.getStyleClass().setAll("fg");
//        title.setOpacity(getSkinnable().isTitleVisible() ? 1 : 0);

//        shadowGroup = new Group();
//        shadowGroup.setEffect(getSkinnable().isForegroundShadowVisible() ? FOREGROUND_SHADOW : null);
//        shadowGroup.getChildren().setAll(title);

        pane = new Pane();
        pane.getChildren().setAll(leftSidePane, currencyCoupleText);
        pane.getStyleClass().setAll("main");

//

        getChildren().setAll(pane);

        resize();
    }


    private void updateFonts() {
        titleFont = Font.font(getSkinnable().getTitleFont(), FontWeight.BOLD, (0.1666666667 * height));
    }


    private void updateGraphics() {
        // Update the title
        currencyCoupleText.setText(getSkinnable().getCurrencyCouple());
        leftSidePane.setValue("1.23", "99", "023");

        //currencyCoupleText.setX((width - currencyCoupleText.getLayoutBounds().getWidth()) * 0.5);
    }


    private void resize() {
        width = getSkinnable().getWidth();
        height = getSkinnable().getHeight();
        if (getSkinnable().isKeepAspect()) {
            if (aspectRatio * width > height) {
                width = 1 / (aspectRatio / height);
            } else if (1 / (aspectRatio / height) > width) {
                height = aspectRatio * width;
            }
        }
        updateFonts();

        if (width > 0 && height > 0) {
            main.setPrefSize(width, height);

            // Setup the font for the lcd title, number system, min measured, max measure and former value
            // Title
            currencyCoupleText.setFont(titleFont);
            currencyCoupleText.setTextOrigin(VPos.BASELINE);
            currencyCoupleText.setTextAlignment(TextAlignment.CENTER);
            currencyCoupleText.setText(getSkinnable().getCurrencyCouple());
            currencyCoupleText.setX((width + currencyCoupleText.getLayoutBounds().getWidth()) * 0.5);
            currencyCoupleText.setY(main.getLayoutY() + currencyCoupleText.getLayoutBounds().getHeight() - 0.01 * height + 2);
        }

        this.leftSidePane.relocate(0,0, width, height);
    }

    private void init() {
        // load the fonts
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digital.ttf"), (0.5833333333 * PREFERRED_HEIGHT));         // "Digital-7"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadout.ttf"), (0.5833333333 * PREFERRED_HEIGHT));  // "Digital Readout Upright"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadoutb.ttf"), (0.5833333333 * PREFERRED_HEIGHT)); // "Digital Readout Thick Upright"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/elektra.ttf"), (0.58333333 * PREFERRED_HEIGHT));           // "Elektra"
        Font.loadFont(getClass().getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-semibold.ttf"), (0.58333333 * PREFERRED_HEIGHT)); // "OpenSans"

        BestPriceButtonControl control = getSkinnable();
        double prefWidth = control.getPrefWidth();
        double prefHeight = control.getPrefHeight();

        if (eqZeroOrSmaller(prefWidth) || eqZeroOrSmaller(prefHeight) ||
                eqZeroOrSmaller(control.getWidth()) || eqZeroOrSmaller(control.getHeight())) {
            if (prefWidth > 0 && prefHeight > 0) {
                control.setPrefSize(prefWidth, prefHeight);
            } else {
                control.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (eqZeroOrSmaller(control.getMinWidth()) || eqZeroOrSmaller(control.getMinHeight())) {
            control.setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (eqZeroOrSmaller(control.getMaxWidth()) || eqZeroOrSmaller(control.getMaxHeight())) {
            control.setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }

        if (prefWidth != PREFERRED_WIDTH || prefHeight != PREFERRED_HEIGHT) {
            aspectRatio = prefHeight / prefWidth;
        }
    }

    private boolean eqZeroOrSmaller(double value) {
        return Double.compare(value, 0.0) <= 0;
    }

    private class SidePane extends Pane {
        Font font;
        Text bigFigure = new Text();
        Text pips = new Text();
        Text smallFigure = new Text();
        private double fontSizeFactor;

        public SidePane(Font font, double fontSizeFactor) {
            super();
            this.fontSizeFactor = fontSizeFactor;
            this.font = font;
            bigFigure.setFont(font);
            pips.setFont(font);
            smallFigure.setFont(font);

            bigFigure.setText("--");
            pips.setText("--");
            smallFigure.setText("--");

            getChildren().addAll(bigFigure, pips, smallFigure);
        }

        public void setValue(String bigFigure, String pips, String smallFigure) {
            this.bigFigure.setText(bigFigure);
            this.pips.setText(pips);
            this.smallFigure.setText(smallFigure);
        }

        public void relocate(double x, double y, double width, double height) {
            double bigFigureFontSize = height * 0.15;
            double pipFontSize = height * 0.65;
            double smallFigureFontSize = bigFigureFontSize;

            double onePercentHeight = height / 100.0;
            double onePercentWidth = width / 100.0;

            bigFigure.setFont(Font.font(getSkinnable().getTitleFont(), FontWeight.BOLD, bigFigureFontSize));
            pips.setFont(Font.font(getSkinnable().getTitleFont(), FontWeight.BOLD, pipFontSize));
            smallFigure.setFont(Font.font(getSkinnable().getTitleFont(), FontWeight.BOLD, smallFigureFontSize));

            bigFigure.getLayoutBounds().getWidth();
            bigFigure.getLayoutBounds().getHeight();

            bigFigure.setX(x+onePercentWidth);
            double separator = (bigFigure.getLayoutBounds().getWidth() * 0.1);
            pips.setX(x+bigFigure.getX() + (bigFigure.getLayoutBounds().getWidth() + separator));
            smallFigure.setX(x+pips.getX() + (pips.getLayoutBounds().getWidth() + separator));

            bigFigure.setY(y);
            pips.setY(y + bigFigure.getLayoutBounds().getHeight());
            smallFigure.setY(y + pips.getY());
        }
    }

}
