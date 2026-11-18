public class WaterCalculator {

    // دالة لحساب قيمة استهلاك المياه بناءً على التسعيرة والشرائح
    public static double calculateBill(int previousReading, int currentReading) {
        int consumption = currentReading - previousReading;
        
        if (consumption < 0) {
            System.out.println("خطأ: القراءة الحالية أقل من القراءة السابقة!");
            return 0;
        }

        double totalAmount = 0;
        int baseLimit = 20;       // الحد الأقصى للوحدات بالسعر العادي
        double normalPrice = 500; // سعر الوحدة العادية (أقل من أو يساوي 20)
        double extraPrice = 1000; // سعر الوحدة المضاعف (لما زاد عن 20)

        if (consumption <= baseLimit) {
            totalAmount = consumption * normalPrice;
        } else {
            double baseCost = baseLimit * normalPrice;
            int excessUnits = consumption - baseLimit;
            double excessCost = excessUnits * extraPrice;
            totalAmount = baseCost + excessCost;
        }

        System.out.println("--- تفاصيل الفاتورة ---");
        System.out.println("الاستهلاك الكلي: " + consumption + " وحدة");
        System.out.println("إجمالي المبلغ المستحق: " + totalAmount + " ريال");
        return totalAmount;
    }

    public static void main(String[] args) {
        System.out.println("تجربة المشترك الأول (استهلاك 15 وحدة طبيعي):");
        calculateBill(100, 115); // القراءة السابقة 100، الحالية 115
        
        System.out.println("\n-------------------------\n");

        System.out.println("تجربة المشترك الثاني (استهلاك 30 وحدة - تجاوز الشرائح):");
        calculateBill(100, 130); // القراءة السابقة 100، الحالية 130
    }
}
