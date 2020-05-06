//Updated Register

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class register extends AppCompatActivity {

    EditText txt_email_reg, txt_password_reg;
    Button btn_reg;
    FirebaseAuth auth;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        txt_email_reg = (EditText) findViewById(R.id.email_register);
        txt_password_reg = (EditText) findViewById(R.id.password_register);
        btn_reg = (Button) findViewById(R.id.register);
        txt_login = (TextView) findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

      if(auth.getCurrentUser() != null){
          startActivity(new Intent(getApplicationContext(),MainActivity.class));
          finish();
      }

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email_reg.getText().toString().trim();
                String pass = txt_password_reg.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    txt_email_reg.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    txt_password_reg.setError("Password is Required.");
                    return;
                }
                // Register User

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(register.this, "Error Occurred" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
      txt_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(),login.class));
          }
      });
    }


}
