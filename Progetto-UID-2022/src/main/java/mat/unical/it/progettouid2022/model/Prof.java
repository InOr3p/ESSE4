package mat.unical.it.progettouid2022.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Prof {

    private final ObjectProperty<String> name = new SimpleObjectProperty<>();
    public Prof(String n) {
        this.name.set(n);
    }

    public final String getName() {
        return this.name.get();
    }

    public final void setName(String n) {
        this.name.set(n);
    }

    public final ObjectProperty<String> nameProperty() {
        return this.name;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
