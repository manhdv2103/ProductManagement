import java.util.Scanner;

/**
 * Lớp lấy input từ người dùng với một số giới hạn đối với những gì người dùng
 * nhập vào
 */
public class InputGetter {
    private final Scanner input = new Scanner(System.in);

    /**
     * Hàm khởi tạo mặc định
     */
    InputGetter() {
    }

    /**
     * Hàm lấy chuỗi từ người dùng
     *
     * @param displayText Chuỗi hiển thị để gợi ý thứ người dùng cần nhập
     * @return Chuỗi lấy được từ người dùng
     */
    public String getLine(String displayText) {
        while (true) {
            System.out.print(displayText);

            String result = input.nextLine().strip();

            // Người dùng không được bỏ trống
            if (result.isEmpty()) {
                System.out.println("Input cannot be empty!");
            } else {
                return result;
            }
        }
    }

    /**
     * Hàm lấy số nguyên từ người dùng
     *
     * @param displayText Chuỗi hiển thị để gợi ý thứ người dùng cần nhập
     * @return Số nguyên lấy được từ người dùng
     */
    public int getInt(String displayText) {
        while (true) {
            System.out.print(displayText);
            Scanner line = new Scanner(input.nextLine());

            if (line.hasNext()) {
                if (line.hasNextInt()) {
                    int result = line.nextInt();

                    line.close();
                    return result;
                } else {
                    // Người dùng phải nhập số nguyên
                    System.out.println("Input must be a whole number!");
                    line.nextLine();
                }
            } else {
                // Người dùng không được bỏ trống
                System.out.println("Input cannot be empty!");
            }
        }
    }

    /**
     * Hàm lấy số thập phân từ người dùng
     *
     * @param displayText Chuỗi hiển thị để gợi ý thứ người dùng cần nhập
     * @return Số thập phân lấy được từ người dùng
     */
    public double getDouble(String displayText) {
        while (true) {
            System.out.print(displayText);
            Scanner line = new Scanner(input.nextLine());

            if (line.hasNext()) {
                if (line.hasNextDouble()) {
                    double result = line.nextDouble();

                    line.close();
                    return result;
                } else {
                    // Người dùng phải nhập số thập phân
                    System.out.println("Input must be a number!");
                    line.nextLine();
                }
            } else {
                // Người dùng không được bỏ trống
                System.out.println("Input cannot be empty!");
            }
        }
    }

    /**
     * Hàm đóng Scanner
     */
    public void close() {
        input.close();
    }
}
