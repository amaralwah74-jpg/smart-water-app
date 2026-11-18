import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfBillGenerator {

    public static void generateWaterBillPDF(Context context, String subscriberName, String village, int consumption, double totalAmount) {
        // إنشاء وثيقة PDF جديدة
        PdfDocument pdfDocument = new PdfDocument();
        
        // تحديد مقاس الصفحة (صفحة طباعة قياسية A4 مثلاً أو بحجم فاتورة)
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();

        // كتابة محتوى الفاتورة
        paint.setTextSize(14);
        canvas.drawText("=== مشروع مياه المحافظة ===", 50, 40, paint);
        
        paint.setTextSize(10);
        canvas.drawText("-------------------------------------------------", 20, 60, paint);
        canvas.drawText("اسم المشترك: " + subscriberName, 20, 90, paint);
        canvas.drawText("القرية: " + village, 20, 120, paint);
        canvas.drawText("الاستهلاك الكلي: " + consumption + " وحدة", 20, 150, paint);
        canvas.drawText("إجمالي المبلغ: " + totalAmount + " ريال", 20, 180, paint);
        canvas.drawText("-------------------------------------------------", 20, 210, paint);
        canvas.drawText("شكراً لالتزامكم بالسداد في الموعد", 50, 240, paint);

        // إنهاء الصفحة
        pdfDocument.finishPage(page);

        // حفظ الملف في ذاكرة الهاتف
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "WaterBill_" + subscriberName + ".pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "تم حفظ الفاتورة PDF بنجاح في التنزيلات!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "خطأ أثناء حفظ الفاتورة: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // إغلاق الوثيقة
        pdfDocument.close();
    }
}
