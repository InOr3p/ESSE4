package mat.unical.it.progettouid2022.model;

public class oggettoSynchronized {  // oggetto utilizzato per sincronizzare i thread del package Client con il main thread
                                    // quando si chiama un thread del package Client, si mette in wait il main thread finchè il thread Client non termina la sua esecuzione

    private static oggettoSynchronized instance = new oggettoSynchronized();
    private oggettoSynchronized() {}

    public static oggettoSynchronized getInstance() {
        return instance;
    }
}
