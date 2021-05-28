package ecommerce.system.api.models;

public class CreditCardModel extends PaymentMethodModel {

    private String cardNumber;
    private String ownerName;
    private String expirationDate;
    private String securityCode;

    public CreditCardModel(String cardNumber, String ownerName, String expirationDate, String securityCode) {
        super(0, null, 0, false);
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
