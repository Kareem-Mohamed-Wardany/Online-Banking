package com.example.onlinebanking;

public class convertcurr {
    private float Currency;

    public void setCurrency(float currency) {
        Currency = currency;
    }

    public float getCurrency() {
        return Currency;

    }
    public float convertToUSD ()
    {
        return (Currency *15);
    }
    public float convertToEUR ()
    {
        return (float) (Currency *0.05);
    }
    public float convertToJPY ()
    {
        return (float) (Currency *6.9);

    }
    public float convertToINR ()
    {
        return (float) (Currency *4.72);
    }

}
