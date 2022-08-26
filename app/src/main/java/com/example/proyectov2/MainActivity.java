package com.example.proyectov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etnombre;
    ImageView ivmodo1,ivmodo2,ivmodo3,ivmodo44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnombre=findViewById(R.id.etnombre);
        ivmodo1=findViewById(R.id.ivmodo1);
        ivmodo2=findViewById(R.id.ivmodo2);
        ivmodo3=findViewById(R.id.ivmodo3);
        ivmodo44=findViewById(R.id.ivmodo4x4);

        ivmodo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etnombre.getText().toString()))
                    invmodo1();
                else
                    Toast.makeText(getApplicationContext(),"Porfavor introduzca su nombre",Toast.LENGTH_SHORT).show();
            }
        });

        ivmodo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etnombre.getText().toString()))
                    invmodo2();
                else
                    Toast.makeText(getApplicationContext(),"Porfavor introduzca su nombre",Toast.LENGTH_SHORT).show();
            }
        });

        ivmodo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etnombre.getText().toString()))
                    invmodo3();
                else
                    Toast.makeText(getApplicationContext(),"Porfavor introduzca su nombre",Toast.LENGTH_SHORT).show();
            }
        });

        ivmodo44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etnombre.getText().toString()))
                    invmodo4x4();
                else
                    Toast.makeText(getApplicationContext(),"Porfavor introduzca su nombre",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void invmodo1 (){
        Intent i = new Intent(this, ActivityGame.class);
        Bundle bolsa =new Bundle();
        bolsa.putString("patron","12345678X");
        bolsa.putStringArray("color", new String[]{"#F44336","#9C27B0","#3F51B5","#03A9F4","#8BC34A","#FFEB3B","#FF9800","#964B00","#FFFFFF"});
        bolsa.putString("nombre",etnombre.getText().toString());
        i.putExtras(bolsa);
        startActivity(i);
    }
    public void invmodo2 (){
        Intent i = new Intent(this, ActivityGame.class);
        Bundle bolsa =new Bundle();
        bolsa.putString("patron","16725834X");
        bolsa.putStringArray("color", new String[]{"#F44336","#FFEB3B","#FF9800","#9C27B0","#8BC34A","#964B00","#3F51B5","#03A9F4","#FFFFFF"});
        bolsa.putString("nombre",etnombre.getText().toString());
        i.putExtras(bolsa);
        startActivity(i);
    }
    public void invmodo3 (){
        Intent i = new Intent(this, ActivityGame.class);
        Bundle bolsa =new Bundle();
        bolsa.putString("patron","13625847X");
        bolsa.putStringArray("color", new String[]{"#F44336","#3F51B5","#FFEB3B","#9C27B0","#8BC34A","#964B00","#03A9F4","#FF9800","#FFFFFF"});
        bolsa.putString("nombre",etnombre.getText().toString());
        i.putExtras(bolsa);
        startActivity(i);
    }

    public void invmodo4x4 (){
        Intent i = new Intent(this, ActivityGame4x4.class);
        Bundle bolsa =new Bundle();
        bolsa.putString("nombre",etnombre.getText().toString());
        i.putExtras(bolsa);
        startActivity(i);
    }
}