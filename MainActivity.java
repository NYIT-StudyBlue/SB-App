//MainActivity.java (creating flashcard)//

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    String word,definition;


    //Create Flashcard
    EditText wordInput;
    EditText definitionInput;

    Button createButton;
    Button logout;

    FirebaseAuth auth;
    DatabaseReference reff;
    Flashcards flashcards;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        auth = FirebaseAuth.getInstance();

        wordInput = (EditText) findViewById(R.id.wordInput);
        definitionInput = (EditText) findViewById(R.id.definitionInput);
        createButton = (Button) findViewById(R.id.createButton);

        flashcards=new Flashcards();

        reff= FirebaseDatabase.getInstance().getReference().child("Flashcards");


        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                flashcards.setWord(wordInput.getText().toString().trim());
                flashcards.setDefinition(definitionInput.getText().toString().trim());

                reff.push().setValue(flashcards);

                Toast.makeText(MainActivity.this, "Flashcard Added to Set",Toast.LENGTH_LONG).show();
            }
        });


        Toast.makeText(MainActivity.this, "Firebase Connection Successful",Toast.LENGTH_LONG).show();
    }
    public void logout (View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }

}
