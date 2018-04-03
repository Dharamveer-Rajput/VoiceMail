package dvr.com.voicemail.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import dvr.com.voicemail.R;
import dvr.com.voicemail.Response.SignUpResponse.ResponseSignUp;
import dvr.com.voicemail.RestClient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edName,edEmail,edPassword,edConfirmPass,edPhnNo;
    Button btSignUp;
    TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_sign_up);
        findViewByIds();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btSignUp.setOnClickListener(this);
//        tvLogin.setOnClickListener(this);
    }

    private void findViewByIds() {
        edName = (EditText)findViewById(R.id.edname);
        edEmail= (EditText)findViewById(R.id.edemail);
        edPassword = (EditText)findViewById(R.id.edpass);
        edConfirmPass= (EditText)findViewById(R.id.edconfirmpass);
        edPhnNo= (EditText)findViewById(R.id.edphn);

        /////////////////////////////
        btSignUp = (Button)findViewById(R.id.btSignup);
        /////////////////////////////
        //  tvLogin = (TextView)findViewById(R.id.tvlogin);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id)
        {
            case R.id.btSignup:
                if(edName.getText().toString().isEmpty())
                {
                    edName.setError("Invalid");
                    return;
                }
                else if(edEmail.getText().toString().isEmpty())
                {
                    edEmail.setError("Invalid");
                    return;
                }
                else if(edPassword.getText().toString().isEmpty()  )
                {
                    edPassword.setError("Invalid");
                    return;
                }
                else if(edPassword.getText().toString().isEmpty())
                {
                    edConfirmPass.setError("Invalid");
                    return;
                }

                else if(edConfirmPass.getText().toString().isEmpty())
                {
                    edConfirmPass.setError("Invalid");
                    return;
                }
                else if(!(edConfirmPass.getText().toString().equals(edPassword.getText().toString())))
                {
                    edConfirmPass.setError("Invalid");
                    return;
                }

                else
                {
                    serverSignUp(edName.getText().toString(),edEmail.getText().toString(),edPassword.getText().toString(),edPhnNo.getText().toString());
                }

                break;
          /*  case R.id.tvlogin:
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));


                break;*/



        }
    }

    private void serverSignUp(String name, String email, String password,String phn) {

        HashMap<String, String> stringStringHashMap1 = new HashMap<>();
        stringStringHashMap1.put("name", name);
        stringStringHashMap1.put("email", email);
        stringStringHashMap1.put("phone", phn);
        stringStringHashMap1.put("password", password);

        RestClient.GitApiInterface restClient = RestClient.getClient();


        restClient.SignUp(stringStringHashMap1).enqueue(new Callback<ResponseSignUp>() {
            @Override
            public void onResponse(Call<ResponseSignUp> call, Response<ResponseSignUp> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (response.body().getIsSuccess())
                {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                   startActivity( new Intent(getApplicationContext(),MainCategoryActivity.class));
                }
                else
                {

                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseSignUp> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }


        });
    }





}
