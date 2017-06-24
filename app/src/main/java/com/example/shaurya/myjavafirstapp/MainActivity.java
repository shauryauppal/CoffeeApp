package com.example.shaurya.myjavafirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    int quantity = 0;
    boolean hasBrownSugar = false, hasWhiteSugar = false;

    public void hasticked(View view) {
        CheckBox test = (CheckBox) findViewById(R.id.topping);
        hasBrownSugar = test.isChecked();
        /*Log.v("show"+hasBrownSugar,"value");*/ //used to check working or not
        CheckBox test_sugar = (CheckBox) findViewById(R.id.white_sugar);
        hasWhiteSugar = test_sugar.isChecked();
    }

    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, "You cannot have more than 100 Coffee cups", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, "You cannot have less than 1 Coffee cup", Toast.LENGTH_SHORT).show();
            return;
        } else quantity--;
        display(quantity);
    }

    /**
     * @param quantity for quantity of coffee
     */
    private int CalculatePrice(int quantity) {
        int price = 5;
        if (hasBrownSugar)
            price += 2;

        if (hasWhiteSugar)
            price += 1;


        return quantity * price;
    }

    /**
     * @param price
     * @param Sname is name of person placing order
     * @return message display message on click of order
     */
    private String OrderSummary(int price, String Sname) {
        String message = "My name is->" + Sname + "\nWould You like Brown Sugar->" + hasBrownSugar + "\nWould you like White Sugar->" + hasWhiteSugar +
                "\nTotal Billl:Rs." + price + " \nQuantity Ordered:" + quantity + "\n"+getString(R.string.thank_you);
        return message;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameinput = (EditText) findViewById(R.id.name);
        String Sname = nameinput.getText().toString();
        if (quantity != 0 && !Sname.isEmpty()) {
            int price = CalculatePrice(quantity);
            String priceMessage = OrderSummary(price, Sname);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java for "+ Sname);
            intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"Shauryauppal00111@gmail.com"});
            intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

//            displayMessage(priceMessage);
        }
    }


    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView orderTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderTextView.setText(message);
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}