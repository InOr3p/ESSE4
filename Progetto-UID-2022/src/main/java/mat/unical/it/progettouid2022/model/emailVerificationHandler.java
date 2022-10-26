package mat.unical.it.progettouid2022.model;

public class emailVerificationHandler {
    private static emailVerificationHandler instance = new emailVerificationHandler();

    public final static int firstVerification = 0;
    private int verificationNumber;
    private emailVerificationHandler() {
        verificationNumber = -1;
    }

    public static emailVerificationHandler getInstance() {
        return instance;
    }

    public void setVerificationNumber(int n) {
        verificationNumber = n;
    }

    public int getVerificationNumber() {
        return verificationNumber;
    }
}
