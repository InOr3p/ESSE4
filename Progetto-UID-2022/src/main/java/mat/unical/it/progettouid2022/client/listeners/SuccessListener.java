package mat.unical.it.progettouid2022.client.listeners;


import mat.unical.it.progettouid2022.client.DatabaseReference;

@FunctionalInterface
public interface SuccessListener {

    /**
     * Method that is called when an operation required to the server has been successfully completed.
     *
     * @param databaseReference contains the result of the operation.
     * */
    void onSuccess(DatabaseReference databaseReference) throws Exception;
}
