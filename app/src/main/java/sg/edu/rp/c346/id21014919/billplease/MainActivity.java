package sg.edu.rp.c346.id21014919.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText Amount;
    EditText Pax;
    EditText Discount;
    ToggleButton SVS;
    ToggleButton GST;
    TextView Total;
    TextView EachPaid;
    Button Split;
    Button Reset;
    TextView Cash;
    TextView PayNow;
    RadioGroup rgPayment;
    RadioButton rbCash;
    RadioButton rbPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Amount = findViewById(R.id.etAmount);
        Pax = findViewById(R.id.etAmtPax);
        SVS = findViewById(R.id.tbtnSVS);
        GST = findViewById(R.id.tbtnGST);
        Total = findViewById(R.id.totalAmt);
        EachPaid = findViewById(R.id.eachPays);
        Split = findViewById(R.id.btnSplit);
        Reset = findViewById(R.id.btnreset);
        rgPayment = findViewById(R.id.rgPayMethod);
        Discount = findViewById(R.id.editDiscount);
        PayNow = findViewById(R.id.rbPayNow);
        Cash = findViewById(R.id.rbCash);

        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = Amount.getText().toString();
                String data2 = Pax.getText().toString();

                double amount = Double.parseDouble(data1);
                double newAmount = 0;
                int pax = Integer.parseInt(data2);

                if(GST.isChecked() == true && SVS.isChecked() == true) {
                    newAmount = amount * 1.10 * 1.07;
                } else if(GST.isChecked() == false && SVS.isChecked() == true) {
                    newAmount = amount * 1.10;
                } else if(GST.isChecked() == true && SVS.isChecked() == false) {
                    newAmount = amount * 1.07;
                } else {
                    newAmount = amount;
                }

                if(Discount.getText().toString().length() != 0) {
                    newAmount *= 1 - Double.parseDouble(Discount.getText().toString()) / 100;
                }

                double eachPays = newAmount / pax;
                double newTotalAmt = newAmount;

                String msg1 = String.format("%.2f", newTotalAmt);
                String msg = String.format("%.2f", eachPays);

                String payMethod = new String(" is to be transferred by PayNow");
                String payMethod2 = new String(" is to be transferred by Cash");

                int selectedID = rgPayment.getCheckedRadioButtonId();

                String data3 = rbCash.getText().toString();

                if(rbCash.isChecked() == true) {
                        EachPaid.setText("Each pays: $" + msg + payMethod2);
                        Total.setText("Total amount: $" + msg1 + payMethod2);
                    } else {
                        EachPaid.setText("Each pays: $" + msg + payMethod);
                        Total.setText("Total amount: $" + msg1 + payMethod);
                    }

                Reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Amount.setText("");
                        Pax.setText("");
                        SVS.setChecked(false);
                        GST.setChecked(false);
                        Discount.setText("");
                    }
                });

            }
        }); {

        }


    }
}