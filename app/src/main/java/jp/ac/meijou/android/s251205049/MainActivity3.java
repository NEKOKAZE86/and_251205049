package jp.ac.meijou.android.s251205049;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205049.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s251205049.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    //intentの取得
    Intent intent = getIntent();
    String sentText = intent.getStringExtra("editText");
    binding.total.setText(sentText);

    binding.button0.setOnClickListener(view->{

    });
    //okボタン
    binding.buttonOk.setOnClickListener(view->{
        var ok_intent = new Intent();
        ok_intent.putExtra("ret","OK");
        setResult(RESULT_OK, ok_intent);
        finish();
    });
    //cancelボタン
    binding.buttonCancel.setOnClickListener(view->{
        setResult(RESULT_CANCELED);
        finish();
    });

    }
}