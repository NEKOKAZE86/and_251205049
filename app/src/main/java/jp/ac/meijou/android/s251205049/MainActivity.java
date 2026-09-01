package jp.ac.meijou.android.s251205049;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205049.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefDataStore = prefDataStore.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.saveButton.setOnClickListener(view->{
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("text",text);
        });

        //TextView textView = findViewById(R.id.text_view);
        //textView.setText(R.string.text_2);
        // _は消えて次の文字が大文字になるtext_view　textView
        //binding.textView.setText(R.string.text_2);//textの表示
        //binding.imageView.setImageResource(R.drawable.outline_alarm);//imageの表示
        //binding.button.setOnClickListener(view->{
        //    String text = binding.editTextText.getText().toString();
        //    binding.textView.setText(text); //ボタンで文字を変える
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                binding.textView.setText(text);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


    }
}