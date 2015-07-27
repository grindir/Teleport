package com.it_lab.teleport;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends Activity {

    String emailS;
    EditText emailViev, passViev;
    Editable emailE, passwordE;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailViev = (EditText) findViewById(R.id.editText2);
        passViev = (EditText) findViewById(R.id.editText3);
        textView = (TextView) findViewById(R.id.textView4);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void work(){
    }

    public void clickLogin(View view){
        testing();
    }

    public void testing() {
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
            if (emailTest()) {
                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(getResources().getColor(R.color.Ok));
                textView.setText("Ok");
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setTextColor(getResources().getColor(R.color.Error));
                textView.setText("Неверно введен Email");
            }




    }

    boolean emailTest(){
        int k=0;
        emailS = emailE.toString();
        for(int i=0;i<emailS.length();i=i+1){
            if (k==2) k = 3;
            if (emailS.charAt(i) == '@'){
                k=1;
            }
            if (k==1 && emailS.charAt(i)=='.'){
                k=2;
            }
        }
        if (k==3) return true;
        else return false;

    }
}
