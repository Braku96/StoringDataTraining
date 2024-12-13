package com.example.storingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    Button button;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Veriyi uygulamada saklayabilmek için Butona tıkladığımızda girilen değeri saklayacak basit bir uygulama.
        textView = findViewById(R.id.editTextID); // Yaşın girileceği alan
        textView1 = findViewById(R.id.textView); // Yaşın gösterileceği alan
        button = findViewById(R.id.button);
        sharedPreferences = this.getSharedPreferences("com.example.storingdata", Context.MODE_PRIVATE);


        //On create metodu çalıştığında yani kapatmış olduğumuz uygulamayı tekrar açtığımızda, kaydetmiş olduğumuz veriyi gösteren kod bölümü.
        int storedAge =  sharedPreferences.getInt("StoredAge",0); //StoredAge girilen veriyi kaydettiğimiz değer için atadığımız anahtar kelimemizdir.

        if (storedAge == 0){
            textView1.setText("Your Age :");
        }else {

            textView1.setText("Saved Age : " + storedAge);
        }

        //Main aktivite içerisinde this kullanarak SharedPreferences objesine ulaşabiliriz.
    }





    public void Save(View view){

        //Girdiğimiz veriyi kaydetmemizi sağlayan kod bölümü
        if(!textView.getText().toString().matches("")){
            int age = Integer.parseInt(textView.getText().toString());
            textView1.setText("Age :" + age);

            sharedPreferences.edit().putInt("StoredAge",age).apply();

        }
    }

    public void Delete(View view){
        //Kaydedilmiş veriyi Delete butonu ile silmemizi sağlayan kod bölümü
        int storedData = sharedPreferences.getInt("StoredAge",0);

        if (storedData!=0){
            sharedPreferences.edit().remove("StoredAge").apply();
        }

    }

}