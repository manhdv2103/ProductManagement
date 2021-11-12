import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Lớp ghi chuỗi lên console và file một cách đồng thời
 */
public class ConsolePrinter {
    private PrintStream printer;
    private boolean fileAvailable;

    /**
     * Hàm khởi tạo mặc định
     */
    ConsolePrinter() {
    }

    /**
     * Hàm khởi tạo với tên file để ghi vào
     *
     * @param fileName Tên file
     */
    ConsolePrinter(String fileName) {
        setFile(fileName);
    }

    /**
     * Hàm lấy tên file để ghi vào
     *
     * @param fileName Tên file
     */
    public void setFile(String fileName) {
        try {
            printer = new PrintStream(new FileOutputStream(fileName, true));
            fileAvailable = true;
        } catch (FileNotFoundException e) {
            // Nếu không tìm thấy file thì thông báo lên màn hình
            // và tắt khả năng ghi file
            System.out.println("File not found. Please create file \"" + fileName
                    + "\" and restart the program to write output to file.");
            fileAvailable = false;
        }
    }

    /**
     * Hàm hiển thị và ghi vào file chuỗi truyền vào
     *
     * @param line Chuỗi sẽ được hiển thị và ghi vào file
     */
    public void print(String line) {
        System.out.print(line);
        if (fileAvailable)
            printer.print(line);
    }

    /**
     * Hàm hiển thị và ghi vào file chuỗi truyền vào rồi xuống dòng
     *
     * @param line Chuỗi sẽ được hiển thị và ghi vào file
     */
    public void println(String line) {
        System.out.println(line);
        if (fileAvailable)
            printer.println(line);
    }

    /**
     * Hàm xuống dòng trong console và file
     */
    public void println() {
        System.out.println();
        if (fileAvailable)
            printer.println();
    }

    /**
     * Hàm hiển thị và ghi vào file chuỗi truyền vào đã được format
     *
     * @param line Chuỗi sẽ được format rồi hiển thị và ghi vào file
     * @param args Các tham số để format vào file
     */
    public void printf(String line, Object... args) {
        System.out.printf(line, args);
        if (fileAvailable)
            printer.printf(line, args);
    }

    /**
     * Hàm đóng PrintStream
     */
    public void close() {
        if (fileAvailable) {
            printer.close();
            fileAvailable = false;
        }
    }
}
