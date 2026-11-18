import java.util.Scanner;

public class MainApp {
    
    // متغيرات النظام العامة
    static double totalRevenue = 150000; // الإيرادات الافتراضية من تحصيل الفواتير
    static double totalExpenses = 0;   // المصروفات
    static double totalSalaries = 0;   // الرواتب

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("==========================================");
        System.out.println("  مرحباً بك في نظام إدارة مشروع المياه الذكي  ");
        System.out.println("==========================================");

        do {
            System.out.println("\n--- القائمة الرئيسية ---");
            System.out.println("1. حساب فاتورة مياه (تسعيرة تصاعدية)");
            System.out.println("2. واجهة العامل (إدخال القراءات وحساب الاستهلاك)");
            System.out.println("3. لوحة تحكم المدير (إدارة المصروفات والرواتب)");
            System.out.println("4. خروج من النظام");
            System.out.print("اختر الرقم المناسب (1-4): ");
            
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // قسم حساب الفاتورة
                    System.out.println("\n--- نظام حساب فاتورة المياه ---");
                    System.out.print("أدخل القراءة السابقة: ");
                    int prev = scanner.nextInt();
                    System.out.print("أدخل القراءة الحالية: ");
                    int curr = scanner.nextInt();
                    
                    calculateBillConsole(prev, curr);
                    break;

                case 2:
                    // قسم العامل الميداني
                    System.out.println("\n--- محاكاة واجهة العامل الميداني ---");
                    System.out.print("أدخل رقم العداد أو اسم المشترك: ");
                    scanner.nextLine(); // تنظيف Buffer
                    String subscriber = scanner.nextLine();
                    
                    int samplePrev = 100; // قراءة سابقة افتراضية للمشترك
                    System.out.println("اسم المشترك: " + subscriber + " | القراءة السابقة: " + samplePrev);
                    
                    System.out.print("أدخل القراءة الحالية التي سجلها العامل: ");
                    int workerCurr = scanner.nextInt();
                    
                    if(workerCurr >= samplePrev) {
                        int consumption = workerCurr - samplePrev;
                        System.out.println("✅ تم تسجيل القراءة بنجاح! الاستهلاك: " + consumption + " وحدة.");
                        System.out.println("📤 [جاهز للإرسال عبر الواتساب للمدير]");
                    } else {
                        System.out.println("❌ خطأ: القراءة الحالية أقل من القراءة السابقة!");
                    }
                    break;

                case 3:
                    // لوحة تحكم المدير
                    manageFinance(scanner);
                    break;

                case 4:
                    System.out.println("شكراً لاستخدامك نظام إدارة مياه المحافظة. إلى اللقاء!");
                    break;

                default:
                    System.out.println("خيار غير صحيح، حاول مرة أخرى.");
            }

        } while (choice != 4);
        
        scanner.close();
    }

    // دالة حساب الفاتورة تفصيلياً
    public static void calculateBillConsole(int previousReading, int currentReading) {
        int consumption = currentReading - previousReading;
        if (consumption < 0) {
            System.out.println("❌ خطأ: القراءة الحالية أقل من السابقة!");
            return;
        }

        double totalAmount = 0;
        int baseLimit = 20;       // الحد الأقصى بالسعر العادي
        double normalPrice = 500; // سعر الوحدة العادية
        double extraPrice = 1000; // سعر الوحدة المضاعف (> 20 وحدة)

        if (consumption <= baseLimit) {
            totalAmount = consumption * normalPrice;
        } else {
            double baseCost = baseLimit * normalPrice;
            int excessUnits = consumption - baseLimit;
            double excessCost = excessUnits * extraPrice;
            totalAmount = baseCost + excessCost;
        }

        System.out.println("--------------------------------");
        System.out.println("💧 الاستهلاك الكلي: " + consumption + " وحدة");
        System.out.println("💵 إجمالي المبلغ المستحق: " + totalAmount + " ريال");
        System.out.println("--------------------------------");
    }

    // دالة إدارة الشؤون المالية للمدير
    public static void manageFinance(Scanner scanner) {
        System.out.println("\n--- لوحة تحكم المدير والمالية ---");
        System.out.println("1. إضافة مصروف جديد (خرج)");
        System.out.println("2. تسجيل راتب موظف أو سلفة");
        System.out.println("3. عرض التقرير المالي وصافي الدخل");
        System.out.print("اختر عملية: ");
        int op = scanner.nextInt();

        if (op == 1) {
            System.out.print("أدخل قيمة المصروف (الخرج): ");
            double exp = scanner.nextDouble();
            totalExpenses += exp;
            System.out.println("✅ تم تسجيل المصروف بنجاح.");
        } else if (op == 2) {
            System.out.print("أدخل قيمة الراتب أو السلفة: ");
            double sal = scanner.nextDouble();
            totalSalaries += sal;
            System.out.println("✅ تم تسجيل الراتب بنجاح.");
        } else if (op == 3) {
            double netProfit = totalRevenue - (totalExpenses + totalSalaries);
            System.out.println("==================================");
            System.out.println("📊 إجمالي الإيرادات (الدخل): " + totalRevenue + " ريال");
            System.out.println("📉 إجمالي المصروفات (الخرج): " + totalExpenses + " ريال");
            System.out.println("👥 إجمالي الرواتب المسجلة: " + totalSalaries + " ريال");
            System.out.println("💵 صافي أرباح الصندوق: " + netProfit + " ريال");
            System.out.println("==================================");
        }
    }
}
