package AllClasses;

public class Bill {

    private int bId;
    private int amount;
    private String payer_name;
    private int aId;
    private String date;

    public Bill() {
    }

    public Bill(int bId, int amount, String payer_name, int aId, String date) {
        this.bId = bId;
        this.amount = amount;
        this.payer_name = payer_name;
        this.aId = aId;
        this.date = date;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
