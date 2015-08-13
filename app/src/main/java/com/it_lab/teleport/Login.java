package com.it_lab.teleport;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends ActionBarActivity {

    String emailS;
    EditText emailViev, passViev;
    Editable emailE, passwordE;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        emailViev = (EditText) findViewById(R.id.editTextR1);
        passViev = (EditText) findViewById(R.id.editTextR2);
        textView = (TextView) findViewById(R.id.textView4);
        emailViev.setText(User.login);
        passViev.setText(User.password);
        emailE = emailViev.getText();
        passwordE = passViev.getText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.to_registr) {
            goToRegist();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void clickLogin(View view){
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
        } else
            if (emailTest(emailE)) {
                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(getResources().getColor(R.color.Ok));
                textView.setText("Ok");
                User.login=emailE.toString();
                User.password=passwordE.toString();
                HTTPClient.login(this);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(getResources().getColor(R.color.Error));
                textView.setText("Неверный Email");
            }




    }

    public boolean emailTest(Editable editable){
        int k=0;
        String string = editable.toString();
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
        else return false;

    }

    public void goToRegist(){
        Intent intentRegist =new Intent(this,Registration.class);
        startActivity(intentRegist);
    }
}
