import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class manages all functions relate to the product
 */
public class OperationToProduct {
    static final InputGetter input = new InputGetter();
    static final ConsolePrinter printer = new ConsolePrinter();

    /**
     * Searching and returning the index of product p in the list. If not found
     * return -1.
     *
     * @param p    Product for searching
     * @param list The Linked List
     * @return The index of product p in the list
     */
    public int index(Product p, MyList<Product> list) {
        int index = 0;
        for (Product product : list) {
            if (product.equals(p)) {
                return index;
            }

            index++;
        }

        return -1;
    }

    /**
     * Creating and returning a product with info input from keyboard.
     *
     * @return The product
     */
    public Product createProduct() {
        String id = input.getLine("Input new ID: ");
        String name = input.getLine("Input product's name: ");
        int quantity = Math.abs(input.getInt("Input product's quantity: "));
        double price = Math.abs(input.getDouble("Input product's price: "));

        return new Product(id, name, quantity, price);
    }

    /**
     * Creating and returning a product with from an array of product's properties.
     *
     * @return The product
     */
    public Product createProductFromArray(String[] properties) {
        Arrays.parallelSetAll(properties, (i) -> properties[i].strip());

        String bcode = (properties[0].isEmpty()) ? "###" : properties[0];
        String title = (properties[1].isEmpty()) ? "Unknown Product" : properties[1];
        int quantity = (properties[2].isEmpty()) ? 0 : Integer.parseInt(properties[2]);
        double price = (properties[3].isEmpty()) ? 0 : Double.parseDouble(properties[3].replace(",", ""));

        return new Product(bcode, title, quantity, price);
    }

    /**
     * Reading all products from the file and insert them to the list at tail.
     *
     * @param fileName The file name of the file
     * @param list     The Linked List contains all products that read from file
     * @throws IOException    if an error occurs when reading file
     * @throws ParseException if the data file is in incorrect format
     */
    public void getAllItemsFromFile(String fileName, MyList<Product> list) throws IOException, ParseException {
        Scanner reader = new Scanner(new File(fileName));

        // Tạo một danh sách tạm thời để tránh ghi vào danh sách chính
        // khi file dữ liệu bị lỗi
        MyList<Product> tempList = new MyList<>();

        try {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (!line.isBlank()) {
                    String[] properties = line.split("[|]");
                    tempList.insertToTail(createProductFromArray(properties));
                }
            }

            // Nối danh sách tạm thời vào danh sách chính khi file dữ liệu không bị lỗi
            list.append(tempList);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ParseException(fileName, -1);
        }

        reader.close();
    }

    /**
     * Reading all products from the file and insert them to the stack.
     *
     * @param fileName The file name of the file
     * @param stack    The Stack contains all products that read from file
     * @throws IOException    if an error occurs when reading file
     * @throws ParseException if the data file is in incorrect format
     */
    public void getAllItemsFromFile(String fileName, MyStack<Product> stack) throws IOException, ParseException {
        Scanner reader = new Scanner(new File(fileName));

        try {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (!line.isBlank()) {
                    String[] properties = line.split("[|]");
                    stack.push(createProductFromArray(properties));
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ParseException(fileName, -1);
        }

        reader.close();
    }

    /**
     * Reading all products from the file and insert them to the queue.
     *
     * @param fileName The file name of the file
     * @param queue    The Queue contains all products that read from file
     * @throws IOException    if an error occurs when reading file
     * @throws ParseException if the data file is in incorrect format
     */
    public void getAllItemsFromFile(String fileName, MyQueue<Product> queue) throws IOException, ParseException {
        Scanner reader = new Scanner(new File(fileName));

        try {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (!line.isBlank()) {
                    String[] properties = line.split("[|]");
                    queue.enqueue(createProductFromArray(properties));
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ParseException(fileName, -1);
        }

        reader.close();
    }

    /**
     * Adding new product to head of Linked List. The info input from keyboard.
     *
     * @param list The Linked list
     */
    public void addFirst(MyList<Product> list) {
        list.insertToHead(createProduct());
    }

    /**
     * Adding a product to the list, info of the product input from keyboard.
     *
     * @param list The Linked list
     */
    public void addLast(MyList<Product> list) {
        list.insertToTail(createProduct());
    }

    /**
     * Printing a product to console screen
     *
     * @param product The product
     */
    public void display(Product product) {
        displayAll(new MyList<>(new Node<>(product)));
    }

    /**
     * Printing all products of the list to console screen
     *
     * @param list The Linked list
     */
    public void displayAll(MyList<Product> list) {
        String tableLine = String.format("%1$s%1$18s%1$43s%1$13s%1$23s", "+").replace(" ", "-");

        printer.println(tableLine);
        printer.printf("| %-15s | %-40s | %10s | %20s |%n", "ID", "Title", "Quantity", "Price");
        printer.println(tableLine);

        for (Product product : list) {
            printer.println("| " + product.toString() + " |");
        }
        printer.println(tableLine);
    }

    /**
     * Printing all products of the stack to console screen
     *
     * @param stack The stack
     */
    public void displayAll(MyStack<Product> stack) {
        String tableLine = String.format("%1$s%1$18s%1$43s%1$13s%1$23s", "+").replace(" ", "-");

        printer.println(tableLine);
        printer.printf("| %-15s | %-40s | %10s | %20s |%n", "ID", "Title", "Quantity", "Price");
        printer.println(tableLine);

        while (!stack.isEmpty()) {
            printer.println("| " + stack.pop().toString() + " |");
        }
        printer.println(tableLine);
    }

    /**
     * Printing all products of the queue to console screen
     *
     * @param queue The queue
     */
    public void displayAll(MyQueue<Product> queue) {
        String tableLine = String.format("%1$s%1$18s%1$43s%1$13s%1$23s", "+").replace(" ", "-");

        printer.println(tableLine);
        printer.printf("| %-15s | %-40s | %10s | %20s |%n", "ID", "Title", "Quantity", "Price");
        printer.println(tableLine);

        while (!queue.isEmpty()) {
            printer.println("| " + queue.dequeue().toString() + " |");
        }
        printer.println(tableLine);
    }

    /**
     * Writing all products from the list to the file
     *
     * @param fileName Input file name
     * @param list     Input Linked list
     * @throws IOException If an error occurs while writing file
     */
    public void writeAllItemsToFile(String fileName, MyList<Product> list) throws IOException {
        PrintStream writer = new PrintStream(fileName);

        for (Product product : list) {
            writer.println(product.toString());
        }

        writer.close();
    }

    /**
     * Searching product by ID input from keyboard from the list.
     *
     * @param list The Linked list
     */
    public void searchByCode(MyList<Product> list) {
        String searchId = input.getLine("Input the ID to search: ");

        for (Product product : list) {
            if (product.getBcode().equals(searchId)) {
                printer.println("Search result:");
                display(product);

                return;
            }
        }

        printer.println("Product with ID \"" + searchId + "\" does not exist (-1).");
    }

    /**
     * Deleting first product that has the ID input from keyboard from the list.
     *
     * @param list The Linked list
     */
    public void deleteByCode(MyList<Product> list) {
        String deleteId = input.getLine("Input the ID to delete: ");

        for (Product product : list) {
            if (product.getBcode().equals(deleteId)) {
                list.deleteElement(product);
                printer.println("Product deleted successfully!");
                display(product);

                return;
            }
        }

        printer.println("Product with ID \"" + deleteId + "\" does not exist to delete.");
    }

    /**
     * Sorting products in linked list by ID
     *
     * @param list The Linked list
     */
    public void sortByCode(MyList<Product> list) {
        sortByCode(list, 0);
    }

    /**
     * Sorting products in linked list by ID with the start sorting index
     *
     * @param list  The Linked list
     * @param start The start sorting index of the list
     */
    private void sortByCode(MyList<Product> list, int start) {
        // Dừng sắp xếp khi vị trí bắt đầu là vị trí cuối cùng
        if (start >= list.length() - 1) {
            return;
        }

        Node<Product> p = list.head;
        Node<Product> current = p;
        Node<Product> minimum = p;
        for (int i = 0; i < list.length(); i++) {
            if (i == start) {
                // Đặt sản phẩm hiện tại và nhỏ nhất tại vị trí bắt đầu
                current = minimum = p;
            } else if (i > start) {
                // Từ vị trí bắt đầu đến hết so sánh để tìm sản phẩm có ID nhỏ nhất
                if (p.info.getBcode().compareTo(minimum.info.getBcode()) < 0) {
                    minimum = p;
                }
            }

            p = p.next;
        }

        // Đổi sản phẩm có ID thấp nhất với sản phẩm hiện tại
        list.swap(current, minimum);

        // Xét tiếp sản phẩm ở vị trí tiếp theo
        sortByCode(list, start + 1);
    }

    /**
     * Convert a decimal to an array of binary. Example: input i = 18 -> Output =
     * {0, 1, 0, 0, 0, 1}
     *
     * @param i Input decimal number
     * @return Array of binary numbers
     */
    public int[] convertToBinary(int i) {
        // Phần cơ sở
        if (i == 0) {
            return new int[] { 0 };
        }

        int[] result = new int[1];

        // Nối thêm chữ số nhị phân vào kết quả đệ quy
        // Nếu kết quả đệ quy là của số 0 thì không nối thêm
        if (i / 2 != 0) {
            int[] recursionResult = convertToBinary(i / 2);
            result = Arrays.copyOf(recursionResult, recursionResult.length + 1);
        }
        result[result.length - 1] = i % 2;

        return result;
    }

    /**
     * Deleting the product at position
     *
     * @param list The Linked List
     * @param pos  The position of product to be deleted
     */
    public void deleteAtPosition(MyList<Product> list, int pos) {
        int i = 0;
        for (Product product : list) {
            if (i == pos) {
                list.deleteElement(product);
                return;
            }

            i++;
        }
    }

    /**
     * Set console output file
     *
     * @param fileName The file's name
     */
    public void setConsoleOutputFile(String fileName) {
        printer.setFile(fileName);
    }
}