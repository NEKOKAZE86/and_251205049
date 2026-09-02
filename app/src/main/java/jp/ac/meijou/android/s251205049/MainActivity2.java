package jp.ac.meijou.android.s251205049;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s251205049.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //明示的
        binding.buttonA.setOnClickListener(view->{
            var intent = new Intent(this,MainActivity3.class);
            startActivity(intent);
        });
        //暗示的
        binding.buttonB.setOnClickListener(view->{
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });
        //intent
        binding.intentButton.setOnClickListener(view->{
            String sentText = binding.intentEditText.getText().toString();
            var intent = new Intent (this, MainActivity3.class);
            intent.putExtra("editText",sentText);
            startActivity(intent);
        });
        binding.resultButton.setOnClickListener(view->{
            var intent = new Intent (this, MainActivity3.class);
            getActivityResult.launch(intent);
        });
    }
    private final ActivityResultLauncher<Intent>getActivityResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result->{
                switch (result.getResultCode()){
                    case RESULT_OK->{
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.result.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.result.setText("Result : Canceld");
                    }
                    default -> {
                        binding.result.setText("Result : Unknowna("+result.getResultCode()+")");
                    }
                }
            }
    );
}