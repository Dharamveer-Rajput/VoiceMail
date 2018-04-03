package dvr.com.voicemail.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import dvr.com.voicemail.R;
import dvr.com.voicemail.Response.LoginResponse.ResponseLogin;
import dvr.com.voicemail.RestClient.RestClient;
import dvr.com.voicemail.Util.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btLogin;
    EditText edEmail, edPassword;
    TextView tvForgotPass, tvSignUp;
    CheckBox checkBox;
    int save = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_login);
        findViewByIds();
        setOnClickListeners();
        setdefaultValues();



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    save = 1;
                }
                else
                {
                    save = 0;
                    Utility.ClearLoginCredential(getApplicationContext());

                }

            }
        });
    }

    private void setdefaultValues() {


        String email =   Utility.getLoginEmail(getApplicationContext());
        String pass =  Utility.getLoginPass(getApplicationContext());
        if(!email.equalsIgnoreCase("") && !pass.equalsIgnoreCase(""))
        {
            checkBox.setChecked(true);
            edEmail.setText(email);
            edPassword.setText(pass);

        }




    }

    private void setOnClickListeners() {
        btLogin.setOnClickListener(this);
        edEmail.setOnClickListener(this);
        edPassword.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }

    private void findViewByIds() {
        btLogin = (Button) findViewById(R.id.btLogin);
        ///////////////////////////////////////////////
        edEmail = (EditText) findViewById(R.id.edemail);
        edPassword = (EditText) findViewById(R.id.edPass);
        //////////////////////////////////////////////
        tvForgotPass = (TextView) findViewById(R.id.tvForgot);
        tvSignUp = (TextView) findViewById(R.id.tvsignup);
        checkBox = (CheckBox)findViewById(R.id.checkbox);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btLogin:
                if (edEmail.getText().toString().isEmpty()) {
                    edEmail.setError("Invalid");
                    return;
                } else if (edPassword.getText().toString().isEmpty()) {
                    edPassword.setError("Invalid");
                    return;
                } else
                    serverLogin(edEmail.getText().toString(), edPassword.getText().toString());
                break;
            case R.id.tvsignup:
                signup();
                break;
            case R.id.tvForgot:

                break;
        }
    }

    private void serverLogin(final String email, final String password) {
        HashMap<String, String> stringStringHashMap1 = new HashMap<>();
        stringStringHashMap1.put("email", email);
        stringStringHashMap1.put("password", password);
        RestClient.GitApiInterface restClient = RestClient.getClient();
        restClient.LoginInterface(stringStringHashMap1).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if(response.body().getIsSuccess())
                {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    if(save==1)
                    {
                        Utility.setLoginCredential(getApplicationContext(),email,password);

                    }
                    else
                    {
                        Utility.ClearLoginCredential(getApplicationContext());

                    }

                    startActivity(new Intent(getApplicationContext(),MainCategoryActivity.class));

                }
                else
                {
                    Utility.setLoginCredential(getApplicationContext(),email,password);
                    Toast.makeText(LoginActivity.this, "Invalid Credential ", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }

    private void signup() {
        Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(i);
    }




}
