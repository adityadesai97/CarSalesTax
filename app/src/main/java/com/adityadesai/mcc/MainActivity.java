package com.adityadesai.mcc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String RS_SYMBOL;
    private int FLAG;

    private LinearLayout netPriceInputLinearLayout;
    private LinearLayout netPriceLinearLayout;
    private LinearLayout salesTaxLinearLayout;
    private LinearLayout grossPriceLinearLayout;
    private TextInputLayout netPriceTextInputLayout;
    private EditText netPriceEditText;
    private TextView netPriceTextView;
    private TextView salesTaxTextView;
    private TextView grossPriceTextView;

    private float netPrice;
    private float salesTax;
    private float grossPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        netPriceInputLinearLayout = (LinearLayout)findViewById(R.id.netPriceInputLinearLayout);
        netPriceLinearLayout = (LinearLayout)findViewById(R.id.netPriceLinearLayout);
        salesTaxLinearLayout = (LinearLayout)findViewById(R.id.salesTaxLinearLayout);
        grossPriceLinearLayout = (LinearLayout)findViewById(R.id.grossPriceLinearLayout);
        netPriceTextInputLayout = (TextInputLayout)findViewById(R.id.netPriceTextInputLayout);
        netPriceEditText = (EditText)findViewById(R.id.netPriceEditText);
        netPriceTextView = (TextView)findViewById(R.id.netPriceTextView);
        salesTaxTextView = (TextView)findViewById(R.id.salesTaxTextView);
        grossPriceTextView = findViewById(R.id.grossPriceTextView);

        RS_SYMBOL = getString(R.string.rs);
        FLAG = 0;

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FLAG == 0) {
                    fab.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_done_white_24px));
                    netPriceEditText = (EditText)netPriceTextInputLayout.getEditText();
                    try {
                        netPrice = Float.parseFloat(netPriceEditText.getText().toString());

                        netPriceInputLinearLayout.setVisibility(View.GONE);
                        netPriceLinearLayout.setVisibility(View.VISIBLE);
                        salesTaxLinearLayout.setVisibility(View.VISIBLE);
                        grossPriceLinearLayout.setVisibility(View.VISIBLE);

                        if(netPrice < 2000) {
                            salesTax = netPrice*15/100;
                        }
                        else if(netPrice < 3000) {
                            salesTax = 300;
                        }
                        else {
                            salesTax = netPrice*10/100;
                        }

                        grossPrice = netPrice + salesTax;

                        netPriceTextView.setText(RS_SYMBOL + netPrice);
                        salesTaxTextView.setText(RS_SYMBOL + salesTax);
                        grossPriceTextView.setText(RS_SYMBOL + grossPrice);

                        fab.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_refresh_white_24px));
                        FLAG = 1;
                    }
                    catch(NumberFormatException e) {
                        Toast.makeText(view.getContext(), "Please enter the net price.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    netPriceEditText.setText("");
                    netPriceInputLinearLayout.setVisibility(View.VISIBLE);
                    netPriceLinearLayout.setVisibility(View.GONE);
                    salesTaxLinearLayout.setVisibility(View.GONE);
                    grossPriceLinearLayout.setVisibility(View.GONE);
                    FLAG = 0;
                    fab.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_done_white_24px));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            netPriceEditText.setText("");
            netPriceInputLinearLayout.setVisibility(View.VISIBLE);
            netPriceLinearLayout.setVisibility(View.GONE);
            salesTaxLinearLayout.setVisibility(View.GONE);
            grossPriceLinearLayout.setVisibility(View.GONE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
