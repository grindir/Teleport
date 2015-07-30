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
    Editable emailE, passwordE, passwordE2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailViev = (EditText) findViewById(R.id.editTextR1);
        passViev = (EditText) findViewById(R.id.editTextR2);
        passViev2 = (EditText) findViewById(R.id.editTextR3);
        textView = (TextView) findViewById(R.id.textView4);
        emailE = emailViev.getText();
        passwordE = passViev.getText();
        passwordE2 = passViev2.getText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickRegist(){

        testing();
    }

    private void testing() {
        Login login = new Login();
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
        if (login.emailTest(emailE)) {
            textView.setVisibility(View.VISIBLE);
            textView.setTextColor(getResources().getColor(R.color.Ok));
            textView.setText("Ok");
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setTextColor(getResources().getColor(R.color.Error));
            textView.setText(getString(R.string.EmailError));
        }




    }
}
