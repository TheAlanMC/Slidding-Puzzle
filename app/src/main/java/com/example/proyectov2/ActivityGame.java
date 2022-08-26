package com.example.proyectov2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

public class ActivityGame extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    TextView tvmoves, tvtime;
    Button btinicio,btsolver,btatras;
    ImageView ivpatron;
    int moves=0;
    ArrayList<Integer> steps = new ArrayList<Integer>();
    Vector<TextView> vTv=new Vector<>();
    String patron;
    String [] colores;
    String nombre;
    String time;
    Boolean estado;
    int seconds=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        tv6=findViewById(R.id.tv6);
        tv7=findViewById(R.id.tv7);
        tv8=findViewById(R.id.tv8);
        tv9=findViewById(R.id.tv9);
        vTv.add(tv1);
        vTv.add(tv2);
        vTv.add(tv3);
        vTv.add(tv4);
        vTv.add(tv5);
        vTv.add(tv6);
        vTv.add(tv7);
        vTv.add(tv8);
        vTv.add(tv9);
        ivpatron=findViewById(R.id.ivmodo);
        btinicio=findViewById(R.id.btincio);
        btsolver=findViewById(R.id.btsolver);
        btatras=findViewById(R.id.btatras);

        tvmoves=findViewById(R.id.tvmoves);
        tvtime=findViewById(R.id.tvtime);

        Bundle data = getIntent().getExtras();
        patron=data.getString("patron");
        colores=data.getStringArray("color");
        nombre=data.getString("nombre");


        new_game();
        runTimer();

        for(int i=0;i<vTv.size();i++){
            final int ii=i;
            final int jj=ii/3;
            final int kk=ii%3;
            vTv.elementAt(ii).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  System.out.println(ii+" msg" + vTv.elementAt(ii).getText().toString());
                    for(int j=0;j<3;j++) {
                        for (int k = 0; k < 3; k++) {
                            if (((Math.abs(jj-j)+Math.abs(kk-k)) == 1) && vTv.elementAt(j*3+k).getText().toString().equals("X")) {
                                swap(vTv.elementAt(ii), vTv.elementAt(j*3+k));
                                steps.add(ii);
                                moves+=1;
                                tvmoves.setText(Integer.toString(moves));
                                break;
                            }
                        }
                    }
                    if (check_win())
                    game_over();
                }
            });
        }

        btinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estado){
                btinicio.setText("REINICIAR");
                state_puzzle(true);
                btsolver.setEnabled(true);
                int last=0;
                int last2=8;
                for (int i=0; i<30; i++){
                    Boolean flag = true;
                    while (flag){
                        int ii=(int) (Math.random()*9);
                        int jj=ii/3;
                        int kk=ii%3;
                        for(int j=0;j<3;j++) {
                            for (int k = 0; k < 3; k++) {
                                if (last!=ii && ((Math.abs(jj-j)+Math.abs(kk-k)) == 1) && vTv.elementAt(j*3+k).getText().toString().equals("X")) {
                                    swap(vTv.elementAt(ii), vTv.elementAt(j*3+k));
                                    steps.add(ii);
                                    flag=false;
                                    last=last2;
                                    last2=ii;
                                    break;
                                }
                            }
                        }
                    }
                }
                estado=false;
            }
                else {
                    new_game();
                }
            }
        });

        btsolver.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length=steps.size();
                if(length>0){
                    state_button(false);
                    state_puzzle(false);
                    estado=true;
                    moves=0;
                    tvmoves.setText(Integer.toString(moves));
                    seconds=0;
                    tvtime.setText("00:00:00");
                }
                Handler myHandler = new Handler();
                for (int a = 1; a<=length ;a++) {
                  //  System.out.println(steps);
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(steps.size()>=2)
                                swap(vTv.elementAt(steps.get(steps.size()-2)), vTv.elementAt(steps.get(steps.size()-1)));
                            else
                                swap(vTv.elementAt(steps.get(steps.size()-1)),vTv.elementAt(8));
                            steps.remove(steps.size()-1);
                            if(steps.isEmpty()){
                                new_game();
                                Toast.makeText(getApplicationContext(),"ROMPECABEZAS RESUELTO",Toast.LENGTH_LONG).show();
                                myHandler.removeCallbacksAndMessages(null);
                            }
                        }
                    }, 500*a);
                }
            }
        }));

        btatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void swap (TextView tva, TextView tvb){
        final String txtTmp = tva.getText().toString();
        final Drawable colorTmp=tva.getBackground();
        tva.setText(tvb.getText().toString());
        tva.setBackground(tvb.getBackground());
        tvb.setText(txtTmp);
        tvb.setBackground(colorTmp);
    }

    private void new_game(){
        estado=true;
        steps.clear();
        btinicio.setText("INICIAR");

        moves=0;
        tvmoves.setText(Integer.toString(moves));
        seconds=0;
        tvtime.setText("00:00:00");
        btinicio.setEnabled(true);
        btsolver.setEnabled(false);
        state_puzzle(false);
        for(int i=0;i<vTv.size();i++){
            int col = Color.parseColor(colores[i]);
            vTv.elementAt(i).setBackgroundColor(col);
            vTv.elementAt(i).setText(Character.toString(patron.charAt(i)));
        }
        if(patron.equals("12345678X"))
            ivpatron.setImageResource(R.drawable.patron1);
        else if(patron.equals("16725834X"))
            ivpatron.setImageResource(R.drawable.patron2);
        else
            ivpatron.setImageResource(R.drawable.patron3);
    }

    private Boolean check_win (){
        String tab="";
        for(int i=0;i<9;i++){
            tab+=vTv.elementAt(i).getText().toString();
        }
        if(patron.equals(tab)){
            return true;
        }
        else
            return false;
    }

    private void state_button (Boolean estado){
        btinicio.setEnabled(estado);
        btsolver.setEnabled(estado);
    }

    private void state_puzzle (Boolean estado){
        for(int i=0;i<9;i++){
            vTv.elementAt(i).setEnabled(estado);
        }
    }

    void game_over(){
        estado=true;
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Juego Terminado");
        alerta.setMessage(  "Felicidades " + nombre + "\n" +
                            "Gano en: " + time + "\n"+
                            "Con " + moves + " movimientos");
        alerta.setCancelable(false);

        alerta.setPositiveButton("Nuevo Juego", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            new_game();
            }
        });
        alerta.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alerta.show();
    }

    private void runTimer()
    {
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                time=String.format(Locale.getDefault(),"%02d:%02d:%02d", hours,minutes, secs);
                tvtime.setText(time);
                if (!estado)
                    seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }

}
