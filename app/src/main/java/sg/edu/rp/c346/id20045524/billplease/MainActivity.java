package sg.edu.rp.c346.id20045524.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText numAmount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBillDisplay;
    TextView eachPaysDisplay;
    TextView errorMsg;
    Button split;
    Button reset;
    EditText numDiscount;
    RadioGroup rgMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numAmount = findViewById(R.id.amtInput);
        numPax = findViewById(R.id.paxInput);
        totalBillDisplay = findViewById(R.id.displayTotal);
        eachPaysDisplay = findViewById(R.id.displayEach);
        errorMsg = findViewById(R.id.displayErrorMsg);
        svs = findViewById(R.id.toggleButtonSVS);
        gst = findViewById(R.id.toggleButtonGST);
        split = findViewById(R.id.btnSplit);
        reset = findViewById(R.id.btnReset);
        numDiscount = findViewById(R.id.discountInput);
        rgMode = findViewById(R.id.rgMode);


        split.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String amtData = numAmount.getText().toString();
                String paxData = numPax.getText().toString();
                String dstData = numDiscount.getText().toString();



                int checkedRadioId = rgMode.getCheckedRadioButtonId();

                if (amtData.trim().length() != 0 || paxData.trim().length() != 0) {
                    double amount = Double.parseDouble(amtData);
                    double newAmt = 0.0;
                    int pax = Integer.parseInt(paxData);

                    if (svs.isChecked() == true && gst.isChecked() == true) {
                        newAmt = amount * 1.10 * 1.07;
                    } else if (svs.isChecked() == true && gst.isChecked() == false) {
                        newAmt = amount * 1.10;
                    } else if (svs.isChecked() == false && gst.isChecked() == true) {
                        newAmt = amount * 1.07;
                    } else {
                        newAmt = amount;
                    }
                    if (dstData.trim().length() != 0){
                        double discount = Double.parseDouble(dstData);
                        double dstAmt = 0.0;
                        dstAmt = newAmt * (discount/100);
                        newAmt = newAmt - dstAmt;
                        
                    } else {
                        //leave blank, no action needed
                    }
                    double eachPays = newAmt / pax;
                    totalBillDisplay.setText("Total Bill: $" + newAmt + " ");
                    if(checkedRadioId == R.id.rbCash){
                        eachPaysDisplay.setText("Each Pay: $" + eachPays + " ");
                    } else {
                        eachPaysDisplay.setText("Each Pay: $" + eachPays + " via PayNow to 112");
                    }

                } else {
                    //show error message if null is inputted
                    errorMsg.setText("Invalid Number");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numAmount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                numDiscount.setText("");
                totalBillDisplay.setText("");
                eachPaysDisplay.setText("");
                errorMsg.setText("");

            }
        });

    }
}

