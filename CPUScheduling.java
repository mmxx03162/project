// استيراد الحزم اللازمة
import javax.swing.*; // لإنشاء الواجهة الرسومية
import javax.swing.table.DefaultTableModel; // لإدارة الجداول
import java.awt.*; // لإنشاء التصاميم
import java.awt.event.ActionEvent; // لمعالجة الأحداث
import java.awt.event.ActionListener; // الاستماع للأحداث


public class CPUScheduling {

    private JFrame frame;
    private JTable inputTable, outputTable;
    private DefaultTableModel inputModel, outputModel;
    private JComboBox<String> schedulingMethod;
    private JTextField avgTurnaroundField, avgWaitingField, throughputField;

    public CPUScheduling() {
        // إعداد الإطار الرئيسي
        frame = new JFrame("CPU Scheduling Simulator");
        frame.setSize(900, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ألوان وتنسيق رئيسي
        Color primaryColor = new Color(52, 152, 219);
        Color secondaryColor = new Color(236, 240, 241);
        Color buttonColor = new Color(46, 204, 113);

        // لوحة العنوان
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        JLabel headerLabel = new JLabel("CPU Scheduling Simulator");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // اللوحة الرئيسية
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(secondaryColor);
        frame.add(mainPanel, BorderLayout.CENTER);

        // قسم المدخلات
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputModel = new DefaultTableModel(new String[]{"Process ID", "Arrival Time", "Burst Time", "Priority"}, 0);
        inputTable = new JTable(inputModel);
        inputPanel.add(new JScrollPane(inputTable), BorderLayout.CENTER);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Section"));
        inputPanel.setBackground(secondaryColor);

        // لوحة إدخال العمليات
        JPanel inputControlPanel = new JPanel();
        inputControlPanel.setBackground(secondaryColor);
        JTextField processField = new JTextField(5);
        JTextField arrivalField = new JTextField(5);
        JTextField burstField = new JTextField(5);
        JTextField priorityField = new JTextField(5);
        JButton addProcessButton = new JButton("Add Process");
        JButton deleteProcessButton = new JButton("Delete Process");
        addProcessButton.setBackground(buttonColor);
        addProcessButton.setForeground(Color.WHITE);
        deleteProcessButton.setBackground(Color.RED);
        deleteProcessButton.setForeground(Color.WHITE);

        inputControlPanel.add(new JLabel("Process ID:"));
        inputControlPanel.add(processField);
        inputControlPanel.add(new JLabel("Arrival Time:"));
        inputControlPanel.add(arrivalField);
        inputControlPanel.add(new JLabel("Burst Time:"));
        inputControlPanel.add(burstField);
        inputControlPanel.add(new JLabel("Priority:"));
        inputControlPanel.add(priorityField);
        inputControlPanel.add(addProcessButton);
        inputControlPanel.add(deleteProcessButton);

        inputPanel.add(inputControlPanel, BorderLayout.SOUTH);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // قسم النتائج
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputModel = new DefaultTableModel(new String[]{"Process ID", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time"}, 0);
        outputTable = new JTable(outputModel);
        outputPanel.add(new JScrollPane(outputTable), BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output Section"));
        outputPanel.setBackground(secondaryColor);

        // لوحة النتائج العامة
        JPanel resultPanel = new JPanel(new GridLayout(3, 2));
        resultPanel.setBackground(secondaryColor);
        avgTurnaroundField = new JTextField(10);
        avgWaitingField = new JTextField(10);
        throughputField = new JTextField(10);
        avgTurnaroundField.setEditable(false);
        avgWaitingField.setEditable(false);
        throughputField.setEditable(false);
        resultPanel.add(new JLabel("Average Turnaround Time:"));
        resultPanel.add(avgTurnaroundField);
        resultPanel.add(new JLabel("Average Waiting Time:"));
        resultPanel.add(avgWaitingField);
        resultPanel.add(new JLabel("Throughput:"));
        resultPanel.add(throughputField);

        outputPanel.add(resultPanel, BorderLayout.SOUTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        // قسم التحكم
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(secondaryColor);
        schedulingMethod = new JComboBox<>(new String[]{"FCFS", "SJF", "Round Robin", "Priority"});
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(buttonColor);
        calculateButton.setForeground(Color.WHITE);
        controlPanel.add(new JLabel("Select Scheduling Method:"));
        controlPanel.add(schedulingMethod);
        controlPanel.add(calculateButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // أزرار الإدخال
        addProcessButton.addActionListener(e -> {
            String process = processField.getText();
            String arrival = arrivalField.getText();
            String burst = burstField.getText();
            String priority = priorityField.getText();
            if (!process.isEmpty() && !arrival.isEmpty() && !burst.isEmpty() && !priority.isEmpty()) {
                inputModel.addRow(new Object[]{process, Integer.parseInt(arrival), Integer.parseInt(burst), Integer.parseInt(priority)});
                processField.setText("");
                arrivalField.setText("");
                burstField.setText("");
                priorityField.setText("");
            }
        });

        deleteProcessButton.addActionListener(e -> {
            int selectedRow = inputTable.getSelectedRow();
            if (selectedRow != -1) {
                inputModel.removeRow(selectedRow);
            }
        });

        // زر تنفيذ الخوارزميات
        calculateButton.addActionListener(e -> {
            String method = (String) schedulingMethod.getSelectedItem();
            if (method != null) {
                switch (method) {
                    case "FCFS":
                        runFCFS();
                        break;
                    case "SJF":
                        runSJF();
                        break;
                    case "Round Robin":
                        runRR();
                        break;
                    case "Priority":
                        runPriority();
                        break;
                }
            }
        });

        frame.setVisible(true);
    }

    private void runFCFS() {
        // تنفيذ خوارزمية FCFS
    }

    private void runSJF() {
        // تنفيذ خوارزمية SJF
    }

    private void runRR() {
        // تنفيذ خوارزمية Round Robin
    }

    private void runPriority() {
        // تنفيذ خوارزمية Priority
    }

    public static void main(String[] args) {
        new CPUScheduling();
    }
}

class CPUScheduling2 {

    // الحقول الرئيسية للواجهة
    private JFrame frame; // الإطار الرئيسي
    private JTable inputTable, outputTable; // جدول المدخلات والمخرجات
    private DefaultTableModel inputModel, outputModel; // لإدارة محتوى الجداول
    private JComboBox<String> schedulingMethod; // اختيار خوارزمية الجدولة
    private JTextField avgTurnaroundField, avgWaitingField, throughputField; // حقول النتائج

    public CPUScheduling2() {
        // إنشاء الإطار الرئيسي
        frame = new JFrame("CPU Scheduling Simulator");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // إنشاء اللوحة الرئيسية
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // قسم المدخلات
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputModel = new DefaultTableModel(new String[]{"Process ID", "Arrival Time", "Burst Time", "Priority"}, 0);
        inputTable = new JTable(inputModel);
        inputPanel.add(new JScrollPane(inputTable), BorderLayout.CENTER);

        // إضافة أزرار الإدخال
        JPanel inputControlPanel = new JPanel();
        JTextField processField = new JTextField(5);
        JTextField arrivalField = new JTextField(5);
        JTextField burstField = new JTextField(5);
        JTextField priorityField = new JTextField(5);
        JButton addProcessButton = new JButton("Add Process");
        JButton deleteProcessButton = new JButton("Delete Process");

        inputControlPanel.add(new JLabel("Process ID:"));
        inputControlPanel.add(processField);
        inputControlPanel.add(new JLabel("Arrival Time:"));
        inputControlPanel.add(arrivalField);
        inputControlPanel.add(new JLabel("Burst Time:"));
        inputControlPanel.add(burstField);
        inputControlPanel.add(new JLabel("Priority:"));
        inputControlPanel.add(priorityField);
        inputControlPanel.add(addProcessButton);
        inputControlPanel.add(deleteProcessButton);

        inputPanel.add(inputControlPanel, BorderLayout.SOUTH);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // قسم النتائج
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputModel = new DefaultTableModel(new String[]{"Process ID", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time"}, 0);
        outputTable = new JTable(outputModel);
        outputPanel.add(new JScrollPane(outputTable), BorderLayout.CENTER);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(3, 2));
        avgTurnaroundField = new JTextField(10);
        avgWaitingField = new JTextField(10);
        throughputField = new JTextField(10);
        resultPanel.add(new JLabel("Average Turnaround Time:"));
        resultPanel.add(avgTurnaroundField);
        resultPanel.add(new JLabel("Average Waiting Time:"));
        resultPanel.add(avgWaitingField);
        resultPanel.add(new JLabel("Throughput:"));
        resultPanel.add(throughputField);

        outputPanel.add(resultPanel, BorderLayout.SOUTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        // قسم اختيار الخوارزمية
        JPanel controlPanel = new JPanel();
        schedulingMethod = new JComboBox<>(new String[]{"FCFS", "SJF", "Round Robin", "Priority"});
        JButton calculateButton = new JButton("Calculate");
        controlPanel.add(new JLabel("Select Scheduling Method:"));
        controlPanel.add(schedulingMethod);
        controlPanel.add(calculateButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // استجابة الأزرار
        addProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String process = processField.getText();
                String arrival = arrivalField.getText();
                String burst = burstField.getText();
                String priority = priorityField.getText();
                if (!process.isEmpty() && !arrival.isEmpty() && !burst.isEmpty() && !priority.isEmpty()) {
                    inputModel.addRow(new Object[]{process, Integer.parseInt(arrival), Integer.parseInt(burst), Integer.parseInt(priority)});
                    processField.setText("");
                    arrivalField.setText("");
                    burstField.setText("");
                    priorityField.setText("");
                }
            }
        });

        deleteProcessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inputTable.getSelectedRow();
                if (selectedRow != -1) {
                    inputModel.removeRow(selectedRow);
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String method = (String) schedulingMethod.getSelectedItem();
                if (method != null) {
                    switch (method) {
                        case "FCFS":
                            runFCFS();
                            break;
                        case "SJF":
                            runSJF();
                            break;
                        case "Round Robin":
                            runRR();
                            break;
                        case "Priority":
                            runPriority();
                            break;
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    private void runFCFS() {
        // تنفيذ خوارزمية FCFS
        // هنا توضع الخوارزمية وتعبئة جدول المخرجات
    }

    private void runSJF() {
        // تنفيذ خوارزمية SJF
        // هنا توضع الخوارزمية وتعبئة جدول المخرجات
    }

    private void runRR() {
        // تنفيذ خوارزمية Round Robin
        // هنا توضع الخوارزمية وتعبئة جدول المخرجات
    }

    private void runPriority() {
        // تنفيذ خوارزمية Priority
        // هنا توضع الخوارزمية وتعبئة جدول المخرجات
    }

    public static void main(String[] args) {
        new CPUScheduling();
    }
}
