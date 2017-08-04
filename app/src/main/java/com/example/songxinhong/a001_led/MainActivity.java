package com.example.songxinhong.a001_led;

import android.os.RemoteException;
import android.os.ServiceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.ILedService;

public class MainActivity extends AppCompatActivity {
    private boolean led_flag=false;
    private ILedService iLedService = null;
    private Button button = null;
    private  CheckBox led1 = null;
    private  CheckBox led2 = null;
    private  CheckBox led3 = null;
    private  CheckBox led4 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iLedService = ILedService.Stub.asInterface(ServiceManager.getService("led"));
        button = (Button) findViewById(R.id.button);
        led1 = (CheckBox) findViewById(R.id.led1);
        led2 = (CheckBox) findViewById(R.id.led2);
        led3 = (CheckBox) findViewById(R.id.led3);
        led4 = (CheckBox) findViewById(R.id.led4);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click.
                led_flag = !led_flag;
                if (led_flag == false) {
                    button.setText("ALL LED ON");
                    led1.setChecked(false);
                    led2.setChecked(false);
                    led3.setChecked(false);
                    led4.setChecked(false);
                    try {                             /*ctrl+alt+t*/
                        for (int i = 0; i < 4; i++)
                            iLedService.ledCtrl(i,0);
                    } catch (RemoteException e) {     /*ctrl+alt+t*/
                        e.printStackTrace();
                    }
                }
                else {
                    button.setText("ALL LED OFF");
                    led1.setChecked(true);
                    led2.setChecked(true);
                    led3.setChecked(true);
                    led4.setChecked(true);
                    try {
                        for (int i = 0; i < 4; i++)
                            iLedService.ledCtrl(i,1);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        try {
            switch(view.getId()) {
                case R.id.led1:
                    if (checked){
                        Toast.makeText(MainActivity.this,"led1 on",Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0,1);}
                     else {
                        Toast.makeText(MainActivity.this,"led1 off",Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0,0);}
                    break;
                case R.id.led2:
                    if (checked){
                        Toast.makeText(MainActivity.this,"led2 on",Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1,1);}
                    else {
                        Toast.makeText(MainActivity.this, "led2 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1,0);}
                    break;
                case R.id.led3:
                    if (checked){
                        Toast.makeText(MainActivity.this,"led3 on",Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2,1);}
                    else {
                        Toast.makeText(MainActivity.this, "led3 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2,0);}
                    break;
                case R.id.led4:
                    if (checked){
                        Toast.makeText(MainActivity.this,"led4 on",Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3,1);}
                    else {
                        Toast.makeText(MainActivity.this,"led4 off",Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3,0);}
                    break;
                // TODO: Veggie sandwich
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
