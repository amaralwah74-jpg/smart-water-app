import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {

    private EditText etExpenseName, etExpenseAmount, etEmployeeName, etEmployeeSalary;
    private Button btnAddExpense, btnAddSalary, btnShowReport;
    private TextView tvFinancialSummary;

    // متغيرات الحسابات المالية للمشروع
    private double totalRevenue = 150000; // إجمالي الدخل من تحصيل الفواتير (افتراضي)
    private double totalExpenses = 0;   // إجمالي المصروفات والخرج
    private double totalSalaries = 0;   // إجمالي رواتب الموظفين

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        etExpenseName = findViewById(R.id.etExpenseName);
        etExpenseAmount = findViewById(R.id.etExpenseAmount);
        etEmployeeName = findViewById(R.id.etEmployeeName);
        etEmployeeSalary = findViewById(R.id.etEmployeeSalary);
        
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnAddSalary = findViewById(R.id.btnAddSalary);
        btnShowReport = findViewById(R.id.btnShowReport);
        tvFinancialSummary = findViewById(R.id.tvFinancialSummary);

        // زر إضافة مصروف جديد (الخرج)
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etExpenseName.getText().toString();
                String amountStr = etExpenseAmount.getText().toString();

                if(name.isEmpty() || amountStr.isEmpty()) {
                    Toast.makeText(v.getContext(), "الرجاء إدخال بيانات المصروف بشكل صحيح", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);
                totalExpenses += amount;
                
                Toast.makeText(v.getContext(), "تم تسجيل المصروف بنجاح: " + name, Toast.LENGTH_SHORT).show();
                etExpenseName.setText("");
                etExpenseAmount.setText("");
            }
        });

        // زر إضافة راتب أو سلفة موظف
        btnAddSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empName = etEmployeeName.getText().toString();
                String salaryStr = etEmployeeSalary.getText().toString();

                if(empName.isEmpty() || salaryStr.isEmpty()) {
                    Toast.makeText(v.getContext(), "الرجاء إدخال اسم الموظف والراتب", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salary = Double.parseDouble(salaryStr);
                totalSalaries += salary;

                Toast.makeText(v.getContext(), "تم تسجيل راتب الموظف: " + empName, Toast.LENGTH_SHORT).show();
                etEmployeeName.setText("");
                etEmployeeSalary.setText("");
            }
        });

        // زر عرض التقرير المالي الشامل وصافي الدخل
        btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double netProfit = totalRevenue - (totalExpenses + totalSalaries);

                String report = "📊 === التقرير المالي للمشروع ===\n" +
                                "💰 إجمالي الإيرادات (الدخل): " + totalRevenue + " ريال\n" +
                                "📉 إجمالي المصروفات (الخرج): " + totalExpenses + " ريال\n" +
                                "👥 إجمالي رواتب الموظفين: " + totalSalaries + " ريال\n" +
                                "------------------------------------------\n" +
                                "💵 صافي أرباح الصندوق: " + netProfit + " ريال";

                tvFinancialSummary.setText(report);
            }
        });
    }
}
