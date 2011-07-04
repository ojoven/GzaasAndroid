package com.gzaas.android;

//import com.gzaas.android.R;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StylesActivity extends Activity {
    /** Called when the activity is first created. */
    LinearLayout linear;
    TextView text;
    private StyleDataHelper dh;
    private int position;
    public String message;
    
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Recogemos los parámetros pasados desde el Activity que ha llamado a Styles
        Bundle extras = getIntent().getExtras();
        message = extras.getString("MESSAGE");
       
        // Creamos la estructura del layout
        linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundColor(Color.parseColor("#ffffff"));
        linear.setScrollContainer(true);
        
        // Insertamos el header y sus características
        text = new TextView(this);
        text.setText("Select your style");
        text.setTextSize(24);
        text.setGravity(Gravity.CENTER);
        text.setPadding(0, 10, 0, 10);
        text.setTextColor(Color.parseColor("#000000"));        
        text.setBackgroundColor(Color.parseColor("#ffffff"));
        
        linear.addView(text);
        
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        dh = new StyleDataHelper(this);
        /*
        //Borramos los elementos previos
        dh.deleteAll();
        // Insertamos los elementos por defecto
        dh.insertDefault();*/  
        
        // Recogemos los elementos de BD
        List<Style> styles = dh.selectAll();
        
        position = 0;
    	// Para cada estilo recogido        
        for (Style style : styles) {
        	

        	// Creamos un text view y le asignamos el nombre del estilo
            text = new TextView(this);
            text.setText(style.getName());
            
        	// Guardamos en un tag su posición en la "lista"
            PositionVO positionVO = new PositionVO();
            positionVO.setPosition(position);
        	text.setTag(positionVO);            
            
            // Características comunes (Tamaño de letra / Alineación)
            text.setTextSize(24);
            text.setGravity(Gravity.CENTER);
            text.setPadding(0, 20, 0, 20);
            
            // Características propias
            
            // Tipografía del estilo
            String font = style.getFont();
            Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/"+font+".ttf");
            text.setTypeface(tf);
            
            // Color de fuente del estilo
            String color = style.getColor();
            text.setTextColor(Color.parseColor(color));
            
            // Color de fondo del estilo
            String backcolor = style.getBackcolor();
            text.setBackgroundColor(Color.parseColor(backcolor));
            
            // Clickable
            text.setClickable(true);

            text.setOnClickListener(new OnClickListener() {
    			
    			public void onClick(View v) {
    				// TODO Auto-generated method stub

    				Intent intentPreview;
    				intentPreview = new Intent(StylesActivity.this, PreviewActivity.class);
    				
    				intentPreview.putExtra("MESSAGE", message);
    				PositionVO positionVO = (PositionVO) v.getTag();
    				intentPreview.putExtra("STYLE_POS", positionVO.getPosition());

    				
    		    	startActivity(intentPreview);
    			}
    		});
            
            linear.addView(text);
            position++;
         }


        setContentView(linear);

    }  
    
}