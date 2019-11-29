package com.nabin.hotelbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner Spin;
    private AutoCompleteTextView Autocom;
    private TextView Checkin, Checkout, Country, Cost, tvaddt, tvsc, tvnettotal, Roomcost, Roomno, Totaldays;
    private EditText Room, Child, Adult, Name;
    private Button btncalc;
    int y1, y2, m1, m2, d1, d2, diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.Name);
        Spin = findViewById(R.id.Spin);
        Autocom = findViewById(R.id.Autocom);
        Checkin = findViewById(R.id.Checkin);
        Checkout = findViewById(R.id.Checkout);
        Country = findViewById(R.id.Country);
        Cost = findViewById(R.id.Cost);
        tvaddt = findViewById(R.id.tvaddt);
        tvnettotal = findViewById(R.id.tvnettotal);
        Roomcost = findViewById(R.id.Roomcost);
        Roomno = findViewById(R.id.Roomno);
        Totaldays = findViewById(R.id.Totaldays);
        Room = findViewById(R.id.Room);
        Child = findViewById(R.id.Child);
        Adult = findViewById(R.id.Adult);
        btncalc = findViewById(R.id.btncalc);

        //passing array to countries in spinner
        String countries[] = {"Nepal", "China", "USA", "LONDON", "QATAR", "GERMANY"};

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,countries);

        Spin.setAdapter(arrayAdapter);

        //passing array to room type spinner
        final String roomType[]={"Deluxe","Suite","King size","Double Bed"};

        final ArrayAdapter arrayAdapter1= new ArrayAdapter(this, android.R.layout.select_dialog_item,roomType);

        Autocom.setAdapter(arrayAdapter1);
        Autocom.setThreshold(1);//will start working form first character
        //auto.setText(room.getText().toString());

        //setting onclick event to check in date
        Checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });



        //setting onclick to check out date
        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDate();
            }
        });


        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(Name.getText())){
                    Name.setError("Please enter your name");
                    return;
                }else if(TextUtils.isEmpty(Adult.getText()))
                {
                    Adult.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(Child.getText()))
                {
                    Child.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(Room.getText()))
                {
                    Room.setText("Please enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(Checkin.getText())){
                    Checkin.setText("Please enter check in Date");
                    return;
                }
                else if(TextUtils.isEmpty(Checkout.getText())){
                    Checkout.setText("Please enter check out Date");
                    return;
                }
                //calculate number of days

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(y1,m1,d1);
                cal2.set(y2,m2,d2);
                long milis1= cal1.getTimeInMillis();
                long milis2 = cal2.getTimeInMillis();
                long diff = milis1-milis2;
                long diffDays=(diff / (86400000));

                int numRoom = Integer.parseInt(Room.getText().toString());

                //calculate net total

                double roomprice, totalprice;
                double vat, servicecharge, nettotal;

                String roomtype;
                roomtype = arrayAdapter1.getItem(1).toString();

                if(roomtype=="Deluxe"){
                    roomprice=2500;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    Roomcost.setText("Cost of room is:" + roomprice);
                    Cost.setText("Total cost is:" +totalprice);
                    tvaddt.setText("Price after VAT inclusion:" +vat);
                    tvnettotal.setText("Overall Price:" +nettotal);


                }

                else if(roomtype=="Suite"){
                    roomprice=2000;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    Roomcost.setText("Cost of room is:" + roomprice);
                    Cost.setText("Total cost is:" +totalprice);
                    tvaddt.setText("Price after VAT inclusion:" +vat);
                    tvnettotal.setText("Overall Price:" +nettotal);

                }
                else if(roomtype=="King size"){
                    roomprice=2200;
                    totalprice= roomprice * numRoom * diffDays;
                    vat=(0.13 * totalprice) + totalprice;
                    nettotal= servicecharge = (0.10 * vat) + vat;
                    Roomcost.setText("Cost of room is:" + roomprice);
                    Cost.setText("Total cost is:" +totalprice);
                    tvaddt.setText("Price after VAT inclusion:" +vat);
                    tvnettotal.setText("Overall Price:" +nettotal);

                }

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "CheckIn Date:" + (month+1) + "/" + dayOfMonth + "/" + year;
        y1= year;
        m1= month;
        d1= dayOfMonth;

        Checkin.setText(date);

    }

    private void loadDatePicker()
    {
        final Calendar c = Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }

    private void loadDate(){
        final Calendar c1= Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH);
        final int day = c1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "CheckIn Date:" + (month+1) + "/" + dayOfMonth + "/" + year;
                y2= year;
                m2= month;
                d2= dayOfMonth;

                Checkout.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
