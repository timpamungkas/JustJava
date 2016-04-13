package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    private int quantity;
    private final String LS = System.getProperty("line.separator");
    private boolean isWhippedCream = true;
    private boolean isChocolateCream = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String createOrderSummary(int cups) {
        StringBuilder sb = new StringBuilder();
        CheckBox chkWhippedCream = (CheckBox) findViewById(R.id.is_whipped_cream);
        CheckBox chkChocolateCream = (CheckBox) findViewById(R.id.is_chocolate_cream);
        EditText txtName = (EditText) findViewById(R.id.txt_name);

        this.isWhippedCream = chkWhippedCream.isChecked();
        this.isChocolateCream = chkChocolateCream.isChecked();

        sb.append("Name : " + txtName.getText());
        sb.append(LS);
        sb.append("Use whipped cream : " + isWhippedCream);
        sb.append(LS);
        sb.append("Use chocolate cream : " + isChocolateCream);
        sb.append(LS);
        sb.append("Quantity : " + cups);
        sb.append(LS);
        sb.append("Total : " + NumberFormat.getCurrencyInstance().format(calculatePrice(cups)));
        sb.append(LS);
        sb.append("Thank You");

        return sb.toString();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String summary = createOrderSummary(this.quantity);
//        displayMessage(summary);
        sendEmail(summary);
    }

    private void sendEmail(String summary) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"timotius.pamungkas@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayMessage(String message) {
        TextView messageTextView = (TextView) findViewById(R.id.order_summary_text_view);
        messageTextView.setText(message);
    }

    public void increment(View view) {
        if (this.quantity < 100) {
            this.quantity++;
        }

        display(this.quantity);
    }

    public void decrement(View view) {
        if (this.quantity > 0) {
            this.quantity--;
        }

        display(this.quantity);
    }

    private int calculatePrice(int cups) {
        int price = 5;

        if (this.isChocolateCream) {
            price += 2;
        }

        if (this.isWhippedCream) {
            price += 1;
        }

        return cups * price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void showMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:-6.415804, 106.762691"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}