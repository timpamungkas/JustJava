package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        display(quantity);
//        displayPrice(quantity * 5);
        displayMessage("Thank you");
    }

    private void displayMessage(String message) {
        TextView messageTextView = (TextView) findViewById(R.id.message_text_view);
        messageTextView.setText(message);
    }

    public void increment(View view) {
        this.quantity++;
        submitOrder(view);
    }

    public void decrement(View view) {
        if (this.quantity > 0) {
            this.quantity--;
        }
        submitOrder(view);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}