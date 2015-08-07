package com.it_lab.teleport;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Registration extends ActionBarActivity {

    EditText emailViev, passViev, passViev2;
    String emailE, passwordE, passwordE2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailViev = (EditText) findViewById(R.id.editTextR1);
        passViev = (EditText) findViewById(R.id.editTextR2);
        passViev2 = (EditText) findViewById(R.id.editTextR3);
        textView = (TextView) findViewById(R.id.textView4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }


    public void clickRegist(View view){

        emailE = emailViev.getText().toString();
        passwordE = passViev.getText().toString();
        passwordE2 = passViev2.getText().toString();
        testing();
    }

    private void testing() {

        if(emailE.length()==0 && passwordE.length()==0){
            textView.setVisibility(View.VISIBLE);
            textView.setTextColor(getResources().getColor(R.color.Error));
            textView.setText(R.string.EmailAndPassNull);
        } else
        if(emailE.length()==0){
            textView.setVisibility(View.VISIBLE);
            textView.setTextColor(getResources().getColor(R.color.Error));
            textView.setText(R.string.EmailNull);
        } else
        if(passwordE.length()==0) {
            textView.setVisibility(View.VISIBLE);
            textView.setTextColor(getResources().getColor(R.color.Error));
            textView.setText(R.string.PassNull);
        }
        if (emailTest(emailE)) {
            if (passwordE.equals(passwordE2)) {

                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(getResources().getColor(R.color.Ok));
                textView.setText("Ok");
                User.login=emailE;
                User.password=passwordE;
                HTTPClient.registr(this);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(getResources().getColor(R.color.Error));
                textView.setText(getString(R.string.Pas1NoPas2));
            }

        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setTextColor(getResources().getColor(R.color.Error));
            textView.setText(getString(R.string.EmailError));
        }



        }


    public boolean emailTest(String str){
        int k=0;
        String string = str.toString();
        for(int i=0;i<string.length();i=i+1){
            if (k==2) k = 3;
            if (string.charAt(i) == '@'){
                k=1;
            }
            if (k==1 && string.charAt(i)=='.'){
                k=2;
            }
        }
        if (k==3) return true;
        else
            return false;


}
}
