package present.hack2innovate.demo.hack2innovatedemoapp.models;

import io.realm.RealmObject;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class SmsResponse extends RealmObject {
    private boolean relevant,credit_category,personal_account;
    private String currency,type;
    private float expense;
    public SmsResponse() {

    }
    public SmsResponse(boolean relevant, boolean credit_category, boolean personal_account, String currency, String type, float expense) {
        this.relevant = relevant;
        this.credit_category = credit_category;
        this.personal_account = personal_account;
        this.currency = currency;
        this.type = type;
        this.expense = expense;
    }

    public boolean isRelevant() {

        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public boolean isCredit_category() {
        return credit_category;
    }

    public void setCredit_category(boolean credit_category) {
        this.credit_category = credit_category;
    }

    public boolean isPersonal_account() {
        return personal_account;
    }

    public void setPersonal_account(boolean personal_account) {
        this.personal_account = personal_account;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }
}
