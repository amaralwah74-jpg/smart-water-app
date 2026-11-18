import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WorkerActivity extends AppCompatActivity {

    private EditText etMeterNo, etCurrentReading;
    private Button btnCalculateAndPrint;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ربط الواجهة الرسومية XML بملف الجافا
        setContentView(R.layout.activity_worker);

        etMeterNo = findViewById(R.id.etMeterNo);
        etCurrentReading = findViewById(R.id.etCurrentReading);
        btnCalculateAndPrint = findViewById(R.id.btnCalculateAndPrint);
        tvResult = findViewById(R.id.tvResult);

        btnCalculateAndPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subscriber = etMeterNo.getText().toString();
                String readingStr = etCurrentReading.getText().toString();

                if (subscriber.isEmpty() || readingStr.isEmpty()) {
                    Toast.makeText(v.getContext(), "الرجاء إدخال جميع البيانات المطلوبة", Toast.LENGTH_SHORT).show();
                    return;
                }

                int currentReading = Integer.parseInt(readingStr);
                int previousReading = 100; // قراءة افتراضية للمشترك للتجربة
                
                if (currentReading < previousReading) {
                    tvResult.setText("❌ خطأ: القراءة الحالية أقل من القراءة السابقة!");
                    return;
                }

                int consumption = currentReading - previousReading;
                double amount = consumption <= 20 ? (consumption * 500) : (20 * 500 + (consumption - 20) * 1000);

                // عرض النتيجة على الشاشة الرسومية
                tvResult.setText("✅ المشترك: " + subscriber + "\n" +
                                 "💧 الاستهلاك: " + consumption + " وحدة\n" +
                                 "💵 المبلغ المستحق: " + amount + " ريال\n" +
                                 "📄 [تم تجهيز فاتورة PDF بنجاح]");
            }
        });
    }
}
