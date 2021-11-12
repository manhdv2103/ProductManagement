import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Chương trình Quản lý sản phẩm - ASM2 CSD201x
 */
public class Main {
    static final String CONSOLE_OUTPUT_FILE = "console_output.txt";
    static final String DATA_FILE = "data.txt";

    static final Scanner input = new Scanner(System.in);
    static final ConsolePrinter printer = new ConsolePrinter(CONSOLE_OUTPUT_FILE);

    public static void main(String[] args) {
        MyList<Product> myList = new MyList<>();
        OperationToProduct otp = new OperationToProduct();
        otp.setConsoleOutputFile(CONSOLE_OUTPUT_FILE);

        while (true) { // Cho phép người dùng chọn chức năng nhiều lần
            showMenu();

            try {
                // Xử lí lựa chọn của người dùng
                switch (getChoice(11)) {
                case 1 -> {
                    otp.getAllItemsFromFile(DATA_FILE, myList);

                    printer.println("File read successfully!");
                    otp.displayAll(myList);
                }

                case 2 -> {
                    otp.addLast(myList);
                    System.out.println();

                    printer.println("Product added successfully!");
                    otp.display(myList.tail.info);
                }

                case 3 -> {
                    printer.println("Products table:");
                    otp.displayAll(myList);
                }

                case 4 -> {
                    otp.writeAllItemsToFile(DATA_FILE, myList);
                    printer.println("Product list saved successfully!");
                }

                case 5 -> otp.searchByCode(myList);

                case 6 -> otp.deleteByCode(myList);

                case 7 -> {
                    otp.sortByCode(myList);
                    printer.println("List sorted successfully!");
                }

                case 8 -> {
                    if (!myList.isEmpty()) {
                        int firstQuantity = myList.head.info.getQuantity();
                        String result = Arrays.stream(otp.convertToBinary(firstQuantity)).mapToObj(String::valueOf)
                                .collect(Collectors.joining());

                        printer.println("Quantity = " + firstQuantity + " => (" + result + ")");
                    } else {
                        printer.println("The list = empty => (no first product's quantity)");
                    }
                }

                case 9 -> {
                    MyStack<Product> myStack = new MyStack<>();
                    otp.getAllItemsFromFile(DATA_FILE, myStack);

                    printer.println("File read to stack successfully!");
                    otp.displayAll(myStack);
                }

                case 10 -> {
                    MyQueue<Product> myQueue = new MyQueue<>();
                    otp.getAllItemsFromFile(DATA_FILE, myQueue);

                    printer.println("File read to queue successfully!");
                    otp.displayAll(myQueue);
                }

                case 0 -> {
                    System.out.print("Thank you for using the program!");

                    input.close();
                    printer.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice. Please try again.");
                }

                // Bắt lỗi liên quan đến file dữ liệu
            } catch (FileNotFoundException e) {
                printer.println("Error: Data file not found.");
            } catch (IOException e) {
                printer.println("There was an error while reading/writing the file. Please try again.");
            } catch (ParseException e) {
                printer.println("Error: Incorrect data file format.");
            }

            pause();
        }
    }

    // Hiển thị menu cho người dùng lựa chọn
    public static void showMenu() {
        System.out.println("\nChoose one of this options:");
        System.out.println("Product list:");
        System.out.println("  1.  Load data from file and display");
        System.out.println("  2.  Input & add to the end");
        System.out.println("  3.  Display data");
        System.out.println("  4.  Save product list to file");
        System.out.println("  5.  Search by ID");
        System.out.println("  6.  Delete by ID");
        System.out.println("  7.  Sort by ID");
        System.out.println("  8.  Convert to Binary");
        System.out.println("  9.  Load to stack and display");
        System.out.println("  10. Load to queue and display");
        System.out.println("  0.  Exit");
        System.out.println();
    }

    // Dừng chương trình tạm thời để người dùng xem thông tin hiển thị
    public static void pause() {
        printer.println();
        System.out.print("Press Enter to continue...");
        input.nextLine();
        input.nextLine();
    }

    // Lấy lựa chọn từ người dùng
    public static int getChoice(int numOfChoices) {
        while (true) {
            System.out.print("Choice: ");

            // Chỉ cho phép người dùng chọn bằng số nguyên
            if (input.hasNextInt()) {
                int choice = input.nextInt();

                // Chỉ cho phép người dùng chọn các lựa chọn khả thi
                if (choice < 0 || choice > numOfChoices - 1) {
                    continue;
                }

                System.out.println();
                return choice;
            } else {
                input.nextLine();
            }
        }
    }
}
