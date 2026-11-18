import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenWorker, btnOpenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenWorker = findViewById(R.id.btnOpenWorker);
        btnOpenManager = findViewById(R.id.btnOpenManager);

        // الانتقال لواجهة العامل الميداني عند الضغط
        btnOpenWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkerActivity.class);
                startActivity(intent);
            }
        });

        // الانتقال لوحة تحكم المدير عند الضغط
        btnOpenManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
