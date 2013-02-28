package eu.hansolo.enzo.qlocktwo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;

import java.util.HashMap;
import java.util.Map;


public class QlockTwo extends Control {
    public enum Language {
        GERMAN(new String[]{"NULL", "EINS", "ZWEI", "DREI", "VIER", "FÜNF", "SECHS", "SIEBEN", "ACHT", "NEUN", "ZEHN", "ELF", "ZWÖLF"}),
        ENGLISH(new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE"}),
        DUTCH(new String[]{"NUL", "EEN", "TWEE", "DRIE", "VIER", "VIJF", "ZES", "ZEVEN", "ACHT", "NEGEN", "TIEN", "ELF", "TWAALF"}),
        FRENCH(new String[]{"ZERO", "UNE", "DEUX", "TROIS", "QUATRE", "CINQ", "SIX", "SEPT", "HUIT", "NEUF", "DIX", "ONZE", "DOUZE"}),
        SPANISH(new String[]{"CERO", "UNA", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE", "DIEZ", "ONCE", "DOCE"});


        private final Map<Integer, String> LOOKUP = new HashMap<>();

        private Language(final String[] NUMBERS) {
            int count = 0;
            for(String number : NUMBERS) {
                LOOKUP.put(count, number);
                count++;
            }
        }

        public Map<Integer, String> getLookup() {
            return LOOKUP;
        }
    }
    public enum QlockColor {
        BLACK_ICE_TEA("black-ice-tea"),
        CHERRY_CAKE("cherry-cake"),
        VANILLA_SUGAR("vanilla-sugar"),
        FROZEN_BLACKBERRY("frozen-blackberry"),
        LIME_JUICE("lime-juice"),
        DARK_CHOCOLATE("dark-chocolate"),
        BLUE_CANDY("blue-candy");

        public final String STYLE_CLASS;

        private QlockColor(final String STYLE_CLASS) {
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }
    private ObjectProperty<QlockColor> color;
    private ObjectProperty<Language>   language;
    private Qlock                      qlock;
    private BooleanProperty            highlightVisible;


    // ******************** Constructors **************************************
    public QlockTwo() {
        getStyleClass().add("qlocktwo");
        color            = new SimpleObjectProperty<>(QlockColor.BLACK_ICE_TEA);
        qlock            = new QlockGerman();
        language         = new SimpleObjectProperty<>(qlock.getLanguage());
        highlightVisible = new SimpleBooleanProperty(true);
    }


    // ******************** Methods *******************************************
    @Override public boolean isResizable() {
        return true;
    }

    public final Qlock getQlock() {
        return qlock;
    }

    public final QlockColor getColor() {
        return color.get();
    }
    public final void setColor(final QlockColor COLOR) {
        color.set(COLOR);
    }
    public final ObjectProperty<QlockColor> colorProperty() {
        return color;
    }

    public final Language getLanguage() {
        return language.get();
    }
    public final void setLanguage(final Language LANGUAGE) {
        switch(LANGUAGE) {
            case GERMAN:
                qlock = new QlockGerman();
                break;
            case ENGLISH:
                qlock = new QlockEnglish();
                break;
            case FRENCH:
                qlock = new QlockFrench();
                break;
            case DUTCH:
                qlock = new QlockDutch();
                break;
            case SPANISH:
                qlock = new QlockSpanish();
                break;
        }
        language.set(LANGUAGE);
    }
    public final ObjectProperty<Language> languageProperty() {
        return language;
    }

    public final boolean isHighlightVisible() {
        return highlightVisible.get();
    }
    public final void setHighlightVisible(final boolean HIGHLIGHT_VISIBLE) {
        highlightVisible.set(HIGHLIGHT_VISIBLE);
    }
    public final BooleanProperty highlightVisibleProperty() {
        return highlightVisible;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}
