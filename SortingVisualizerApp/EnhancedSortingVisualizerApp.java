import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.*;

public class EnhancedSortingVisualizerApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private SortingVisualizer sorter1;
    private SortingVisualizer sorter2;
    private JTextField arrayInputField;
    private JComboBox<String> algorithmDropdown1;
    private JComboBox<String> algorithmDropdown2;
    private JSlider widthSlider;
    private String selectedAlgorithm1;
    private String selectedAlgorithm2;
    private JButton startSortButton;
    private JButton pauseResumeButton;
    private JButton nextStepButton;
    // User credentials storage
    private HashMap<String, String> userCredentials = new HashMap<>();

    public EnhancedSortingVisualizerApp() {
        frame = new JFrame("Enhanced Sorting Visualizer");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add initial user credentials for testing
        userCredentials.put("Prathmesh0411", "0411");
        userCredentials.put("Umesh17", "1709");
        userCredentials.put("Swayam10", "1010");
        userCredentials.put("Sai08", "0808");
        userCredentials.put("Atharv01", "0101");

        // Create About Page
        createAboutPage();

        // Create Login Page
        createLoginPage();

        // Create Input Page
        createInputPage();

        // Create Visualization Page
        createVisualizationPage();

        frame.add(mainPanel);
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    // Page 0: About Page
    private void createAboutPage() {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new GridBagLayout());
        aboutPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Project Team");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        aboutPanel.add(titleLabel, gbc);

        String[] students = {
                "Sai Patil 08",
                "Prathmesh Rathod 13",
                "Swayam Pawar 10",
                "Umesh Rupnar 17",
                "Atharv Panchwagh 01"
        };

        for (int i = 0; i < students.length; i++) {
            JLabel studentLabel = new JLabel(students[i]);
            studentLabel.setForeground(Color.WHITE);
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            aboutPanel.add(studentLabel, gbc);
        }

        JLabel guideLabel = new JLabel("Project Guide: Prof. Barkha Shahaji");
        guideLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = students.length + 1;
        aboutPanel.add(guideLabel, gbc);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "LoginPage"));
        gbc.gridx = 0;
        gbc.gridy = students.length + 2;
        gbc.gridwidth = 2;
        aboutPanel.add(nextButton, gbc);

        mainPanel.add(aboutPanel, "AboutPage");
    }

    // Page 1: Login Page
    private void createLoginPage() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JLabel userIdLabel = new JLabel("User   ID: ");
        userIdLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userIdLabel, gbc);

        JTextField userIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(userIdField, gbc);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String userId = userIdField.getText();
            String password = new String(passwordField.getPassword());
            if (userCredentials.containsKey(userId) && userCredentials.get(userId).equals(password)) {
                cardLayout.show(mainPanel, "InputPage");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        // Separate the buttons with a new row
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            String userId = userIdField.getText();
            String password = new String(passwordField.getPassword());
            if (userId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both User ID and Password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (userCredentials.containsKey(userId)) {
                JOptionPane.showMessageDialog(frame, "User  ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                userCredentials.put(userId, password);
                JOptionPane.showMessageDialog(frame, "Sign up successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        loginPanel.add(signUpButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.addActionListener(e -> {
            String userId = userIdField.getText();
            if (userCredentials.containsKey(userId)) {
                String newPassword = JOptionPane.showInputDialog(frame, "Enter new password for " + userId + ":");
                if (newPassword != null && !newPassword.isEmpty()) {
                    userCredentials.put(userId, newPassword);
                    JOptionPane.showMessageDialog(frame, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "User  ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginPanel.add(forgotPasswordButton, gbc);

        mainPanel.add(loginPanel, "LoginPage");
    }

    // Page 2: Input Page
    private void createInputPage() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Enhanced Sorting Visualizer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(titleLabel, gbc);

        JLabel arrayLabel = new JLabel("Enter Numbers (comma separated): ");
        arrayLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(arrayLabel, gbc);

        arrayInputField = new JTextField("5,3,8,1,4,7,9,6,2", 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(arrayInputField, gbc);

        JLabel algoLabel1 = new JLabel("Select Sorting Algorithm 1: ");
        algoLabel1.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(algoLabel1, gbc);

        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort", "Shell Sort"};
        algorithmDropdown1 = new JComboBox<>(algorithms);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(algorithmDropdown1, gbc);

        JLabel algoLabel2 = new JLabel("Select Sorting Algorithm 2: ");
        algoLabel2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(algoLabel2, gbc);

        algorithmDropdown2 = new JComboBox<>(algorithms);
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(algorithmDropdown2, gbc);

        JButton startButton = new JButton("Start Visualization");
        startButton.addActionListener(e -> handleStartButton());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(startButton, gbc);

        mainPanel.add(inputPanel, "InputPage");
    }

    // Page 3: Visualization Page
    private void createVisualizationPage() {
        sorter1 = new SortingVisualizer(new int[]{5, 3, 8, 1, 4, 7, 9, 6, 2}, "Algorithm 1");
        sorter2 = new SortingVisualizer(new int[]{5, 3, 8, 1, 4, 7, 9, 6, 2}, "Algorithm 2");

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel widthLabel = new JLabel("Bar Width:");
        widthSlider = new JSlider(3, 50, 10);
        widthSlider.addChangeListener(e -> {
            sorter1.setBarWidth(widthSlider.getValue());
            sorter2.setBarWidth(widthSlider.getValue());
        });

        startSortButton = new JButton("Start Sorting");
        startSortButton.addActionListener(e -> {
            sorter1.runSelectedSort(selectedAlgorithm1);
            sorter2.runSelectedSort(selectedAlgorithm2);
        });

        pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addActionListener(e -> {
            sorter1.togglePause();
            sorter2.togglePause();
        });

        nextStepButton = new JButton("Next Step");
        nextStepButton.addActionListener(e -> {
            sorter1.nextStep();
            sorter2.nextStep();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "InputPage"));

        controlPanel.add(widthLabel);
        controlPanel.add(widthSlider);
        controlPanel.add(startSortButton);
        controlPanel.add(pauseResumeButton);
        controlPanel.add(nextStepButton);
        controlPanel.add(backButton);

        JPanel visualizationPage = new JPanel(new GridLayout(1, 2));
        visualizationPage.add(sorter1);
        visualizationPage.add(sorter2);
        visualizationPage.add(controlPanel);

        mainPanel.add(visualizationPage, "VisualizerPage");
    }

    // Handle Start Button to Switch Page
    private void handleStartButton() {
        String input = arrayInputField.getText();
        if (input == null || input.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter some numbers to sort!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Parse user input
        String[] tokens = input.split(",");
        int[] userArray = new int[tokens.length];
        try {
            for (int i = 0; i < tokens.length; i++) {
                userArray[i] = Integer.parseInt(tokens[i].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid number format! Use commas to separate numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set selected algorithms
        selectedAlgorithm1 = (String) algorithmDropdown1.getSelectedItem();
        selectedAlgorithm2 = (String) algorithmDropdown2.getSelectedItem();
        sorter1.resetArray(userArray);
        sorter2.resetArray(userArray);
        cardLayout.show(mainPanel, "VisualizerPage");
    }

    // Main Method to Launch App
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EnhancedSortingVisualizerApp::new);
    }
}

// Sorting Visualizer Panel
class SortingVisualizer extends JPanel {
    int[] array;
    int barWidth = 10;
    int comparisons = 0;
    int swaps = 0;
    String timeComplexity = "";
    Thread sortThread;
    boolean isSorted = false;
    String pseudoCode = "";
    private volatile boolean paused = false;
    private volatile boolean stepByStep = false;
    private int currentIndex1 = -1; // Index of the first element being compared
    private int currentIndex2 = -1; // Index of the second element being compared
    private String algorithmName;

    public SortingVisualizer(int[] inputArray, String algorithmName) {
        this.array = Arrays.copyOf(inputArray, inputArray.length);
        this.algorithmName = algorithmName;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int barSpacing = 2;

        int totalWidth = array.length * (barWidth + barSpacing) - barSpacing;
        int startX = (width - totalWidth) / 2;

        g.setColor(Color.BLACK);
        String stats = String.format("Algorithm: %s | Comparisons: %d | Swaps: %d | Time Complexity: %s", algorithmName, comparisons, swaps, timeComplexity);
        g.drawString(stats, 10, 20);

        // Draw Pseudocode
        g.setColor(Color.BLUE);
        drawPseudoCode(g, width - 250, 50);

        // Draw Bars
        for (int i = 0; i < array.length; i++) {
            int barHeight = array[i] * 4;
            int x = startX + i * (barWidth + barSpacing);
            int y = height - barHeight - 70;

            // Highlight the bars being compared
            if (i == currentIndex1 || i == currentIndex2) {
                g.setColor(Color.RED); // Highlight color for comparison
            } else {
                g.setColor(Color.GREEN); // Normal color
            }
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);

            g.setColor(Color.BLACK);
            String value = String.valueOf(array[i]);
            int stringWidth = g.getFontMetrics().stringWidth(value);
            g.drawString(value, x + (barWidth - stringWidth) / 2, y - 5);
        }
    }

    public void setBarWidth(int width) {
        this.barWidth = width;
        repaint();
    }

    public void runSelectedSort(String algorithm) {
        if (algorithm.equals("Bubble Sort")) {
            bubbleSort();
        } else if (algorithm.equals("Selection Sort")) {
            selectionSort();
        } else if (algorithm.equals("Insertion Sort")) {
            insertionSort();
        } else if (algorithm.equals("Merge Sort")) {
            mergeSort();
        } else if (algorithm.equals("Quick Sort")) {
            quickSort();
        } else if (algorithm.equals("Heap Sort")) {
            heapSort();
        } else if (algorithm.equals("Shell Sort")) {
            shellSort();
        } else {
            JOptionPane.showMessageDialog(this, "Unknown Algorithm: " + algorithm);
        }
    }

    public void resetArray(int[] newArray) {
        array = Arrays.copyOf(newArray, newArray.length);
        comparisons = 0;
        swaps = 0;
        isSorted = false;
        stepByStep = false;
        repaint();
    }

    public void nextStep() {
        stepByStep = true;
        if (sortThread != null && sortThread.isAlive()) {
            synchronized (this) {
                notify();
            }
        }
    }

    public void quickSort() {
        pseudoCode = "Quick Sort:\nchoose pivot\npartition array\nrecursively quicksort partitions";
        updateTimeComplexity("O(n log n)", "O(n log n)", "O(n²)");
        sortThread = new Thread(() -> {
            quickSortHelper(array, 0, array.length - 1);
            isSorted = true;
        });
        sortThread.start();
    }

    private void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            currentIndex1 = j; // First element being compared
            currentIndex2 = high; // Second element being compared
            repaint();
            sleep();
            if (arr[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    public void heapSort() {
        pseudoCode = "Heap Sort:\nbuild max heap\nextract elements one by one\nheapify to maintain heap";
        updateTimeComplexity("O(n log n)", "O(n log n)", "O(n log n)");
        sortThread = new Thread(() -> {
            int n = array.length;

            // Build max heap
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i);
            }

            // Extract elements from heap one by one
            for (int i = n - 1; i >= 0; i--) {
                swap(0, i);
                heapify(array, i, 0);
            }
            isSorted = true;
        });
        sortThread.start();
    }

    // Heapify a subtree rooted at index i
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is larger
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if right child is larger
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            swap(i, largest);
            heapify(arr, n, largest);
        }
    }

    public void shellSort() {
        pseudoCode = "Shell Sort:\ngap = n/2\nwhile gap > 0\n  for i = gap to n\n    insert element in correct position\n  gap = gap/2";
        updateTimeComplexity("O(n log n)", "O(n²)", "O(n)");
        sortThread = new Thread(() -> {
            int n = array.length;

            // Start with a big gap, reduce it by half each iteration
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = array[i];
                    int j;

                    // Shift elements until the right position is found
                    for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                        array[j] = array[j - gap];
                        comparisons++;
                        repaint();
                        sleep();
                    }
                    array[j] = temp;
                    swaps++;
                    repaint();
                    sleep();
                }
            }
            isSorted = true;
        });
        sortThread.start();
    }

    private void updateTimeComplexity(String best, String average, String worst) {
        timeComplexity = String.format("Best: %s   |   Average: %s   |   Worst: %s", best, average, worst);
        repaint();
    }

    private void drawPseudoCode(Graphics g, int x, int y) {
        String[] lines = pseudoCode.split("\\n");
        for (String line : lines) {
            g.drawString(line, x, y);
            y += 15;
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
        repaint();
        sleep();
    }

    private void sleep() {
        try {
            if (paused) {
                synchronized (this) {
                    wait();
                }
            } else if (stepByStep) {
                synchronized (this) {
                    wait();
                }
            } else {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void togglePause() {
        paused = !paused;
        if (!paused) {
            notifyAll();
        }
    }

    public void bubbleSort() {
        pseudoCode = "Bubble Sort:\nfor i = 0 to n-1\n  for j = 0 to n-i-1\n    if arr[j] > arr[j+1]\n      swap(arr[j], arr[j+1])";
        updateTimeComplexity("O(n)", "O(n²)", "O(n²)");
        sortThread = new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    comparisons++;
                    currentIndex1 = j; // First element being compared
                    currentIndex2 = j + 1; // Second element being compared
                    repaint();
                    sleep();
                    if (array[j] > array[j + 1]) {
                        swap(j, j + 1);
                    }
                }
            }
            isSorted = true;
        });
        sortThread.start();
    }

    public void selectionSort() {
        pseudoCode = "Selection Sort:\nfor i = 0 to n-1\n  find minIdx\n  swap(arr[i], arr[minIdx])";
        updateTimeComplexity("O(n²)", "O(n²)", "O(n²)");
        sortThread = new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < array.length; j++) {
                    comparisons++;
                    currentIndex1 = j; // Current element being compared
                    currentIndex2 = minIdx; // Current minimum element
                    repaint();
                    sleep();
                    if (array[j] < array[minIdx]) {
                        minIdx = j;
                    }
                }
                swap(i, minIdx);
                sleep();
            }
            isSorted = true;
        });
        sortThread.start();
    }

    public void insertionSort() {
        pseudoCode = "Insertion Sort:\nfor i = 1 to n\n  key = arr[i]\n  while j >= 0 and arr[j] > key\n    arr[j+1] = arr[j]\n  arr[j+1] = key";
        updateTimeComplexity("O(n)", "O(n²)", "O(n²)");
        sortThread = new Thread(() -> {
            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    comparisons++;
                    currentIndex1 = j; // Current element being compared
                    currentIndex2 = j + 1; // Element being shifted
                    array[j + 1] = array[j];
                    j--;
                    repaint();
                    sleep();
                }
                array[j + 1] = key;
                repaint();
                sleep();
            }
            isSorted = true;
        });
        sortThread.start();
    }

    public void mergeSort() {
        pseudoCode = "Merge Sort:\nsplit array in half\nrecursively sort each half\nmerge sorted halves";
        updateTimeComplexity("O(n log n)", "O(n log n)", "O(n log n)");
        sortThread = new Thread(() -> {
            mergeSortHelper(array, 0, array.length - 1);
            isSorted = true;
        });
        sortThread.start();
    }

    private void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        // Initial indexes of left and right subarrays
        int i = 0, j = 0, k = left;

        // Merge the temp arrays back into arr[left...right]
        while (i < n1 && j < n2) {
            comparisons++;
            currentIndex1 = left + i; // Current element from left array
            currentIndex2 = mid + 1 + j; // Current element from right array
            repaint();
            sleep();
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr[] if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
            repaint();
            sleep();
        }

        // Copy remaining elements of rightArr[] if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
            repaint();
            sleep();
        }
    }
}
