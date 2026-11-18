import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseLoader {

    // دالة للبحث عن مشترك برقم العداد وإرجاع بياناته
    public static void searchSubscriber(String targetMeterNo) {
        String csvFile = "subscribers.csv"; // اسم ملف البيانات
        String line = "";
        String cvsSplitBy = ",";
        boolean found = false;

        try (BufferedReader br = new BufferedReader(FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // تقسيم السطر بناءً على الفاصلة
                String[] data = line.split(cvsSplitBy);

                String meterNo = data[0].trim();
                String name = data[1].trim();
                String village = data[2].trim();
                int prevReading = Integer.parseInt(data[3].trim());

                // مطابقة رقم العداد المدخل مع الملف
                if (meterNo.equals(targetMeterNo)) {
                    System.out.println("✅ تم العثور على المشترك بنجاح!");
                    System.out.println("الاسم: " + name);
                    System.out.println("القرية: " + village);
                    System.out.println("القراءة السابقة: " + prevReading);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("❌ عذراً، رقم العداد غير موجود!");
            }

        } catch (IOException e) {
            System.out.println("خطأ في قراءة ملف البيانات: " + e.getMessage());
        }
    }
}
