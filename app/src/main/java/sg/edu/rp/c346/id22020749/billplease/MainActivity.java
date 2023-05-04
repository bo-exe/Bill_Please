package sg.edu.rp.c346.id22020749.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText Amount;
    EditText Pax;
    EditText Discount;
    EditText PayNowNumber;
    ToggleButton SVS;
    ToggleButton GST;
    TextView TotalBill;
    TextView AmountEachPays;
    RadioGroup PaymentMethod;
    Button Split;
    Button Reset;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billplease_activity_main);

        Amount = findViewById(R.id.amountText);
        Pax = findViewById(R.id.paxText);
        Discount = findViewById(R.id.discountText);
        PayNowNumber = findViewById(R.id.PayNowNumberText);
        SVS = findViewById(R.id.toggleButtonSVS);
        GST = findViewById(R.id.toggleButtonGST);
        TotalBill = findViewById(R.id.textViewDisplayBill);
        AmountEachPays = findViewById(R.id.textViewDisplayEachPays);
        PaymentMethod = findViewById(R.id.RadioGroupPayment);
        Split = findViewById(R.id.buttonSplit);
        Reset = findViewById(R.id.buttonReset);

        //MADAKONOSEKAIWABOKUWOKAINARASHITETAIMITAIDA WHY IS THIS SO TEDIOUS

        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(Amount.getText().toString()) !=0 && Integer.parseInt(Pax.getText().toString()) !=0) {
                    int Amount2 = 0;

                    if(!SVS.isChecked() && !GST.isChecked()) {
                        Amount2 = Integer.parseInt(Amount.getText().toString());
                    }

                    else if(SVS.isChecked() && GST.isChecked()) {
                        Amount2 = (int) (Integer.parseInt(Amount.getText().toString()) * 1.18);
                    }

                    else if(SVS.isChecked() && !GST.isChecked()) {
                        Amount2 = (int) (Integer.parseInt(Amount.getText().toString()) * 1.1);
                    }

                    else {
                        Amount2 = (int) (Integer.parseInt(Amount.getText().toString()) * 1.08);
                    }

                    if(Integer.parseInt(Discount.getText().toString()) != 0) {
                        Amount2 = Amount2 * (1-Integer.parseInt(Discount.getText().toString()) / 100);
                    }

                    String stringResponse = (String.format("%.2f", Amount2));

                    int checkedRadioID = PaymentMethod.getCheckedRadioButtonId();

                    if(checkedRadioID == R.id.radioButtonCash) {
                        String CashPay = stringResponse + " in cash";
                    }

                    else {
                        String PayNow = stringResponse + " via PayNow to " + PayNowNumber;
                    }

                    int NumOfPax = Integer.parseInt(Pax.getText().toString());

                    if(NumOfPax > 1) {
                        AmountEachPays.setText(String.format("%.2f", Amount2/NumOfPax));
                    }

                    else {
                        AmountEachPays.setText(String.format("%.2f", Amount2));
                    }

                    Reset.setOnClickListener((new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Amount.setText("");
                            Pax.setText("");
                            SVS.setChecked(false);
                            GST.setChecked(false);
                            Discount.setText("");
                        }
                    }));
                }
            }
        });

    }
}
