package KW_2;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;


public class Main extends JFrame implements ActionListener {

    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 400;

    private String[] optionsToChoose = {"Оберіть вид фізичної активності:", "Біг", "Хотьба", "Плавання", "Їзда на велосипеді"};
    private JComboBox<String> options = new JComboBox<>(optionsToChoose);
    private FancyTextField hour = new FancyTextField("год");
    private FancyTextField minute = new FancyTextField("хв");
    private JButton plus = new JButton("Додати");


    private String[][] data = {
            {}
    };
    private String[] columnNames = {"№", "Вид фізичної активності", "Год", "Хв"};
    private DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    private JTable table = new JTable(tableModel) {
        public boolean editCellAt(int row, int column, java.util.EventObject e) {
            return false;
        }
    };
    private JButton ready = new JButton("Готово");
    private int i = 0;


    public Main() {

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        hour.setPlaceholder("год");
        minute.setPlaceholder("хв");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(150, 217, 181));

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(30, 10, 0, 10);
        c.ipady = 10;
        c.weighty = 1;

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(options, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(hour, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(minute, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        panel.add(plus, c);

        c.insets = new Insets(0, 10, 0, 10);

        c.weightx = 0.5;
        c.weighty = 1.2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 80;
        c.gridwidth = 4;
        panel.add(new JScrollPane(table), c);

        c.weightx = 0.5;
        c.ipady = 10;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;

        panel.add(ready, c);

        plus.addActionListener(this);
        plus.setBackground(new Color(44, 153, 128));
        ready.addActionListener(this);
        ready.setBackground(new Color(44, 153, 128));
        options.setBackground(new Color(44, 153, 128));
        hour.setBackground(new Color(224, 175, 83));
        minute.setBackground(new Color(224, 175, 83));
        table.getTableHeader().setBackground(new Color(44, 153, 128));
        table.setBackground(new Color(150, 217, 181));
        table.setFillsViewportHeight(true);


        TableColumn tColumn = table.getColumnModel().getColumn(0);
        TableColumn tColumn1 = table.getColumnModel().getColumn(1);
        TableColumn tColumn2 = table.getColumnModel().getColumn(2);
        TableColumn tColumn3 = table.getColumnModel().getColumn(3);
        tColumn.setCellRenderer(new ColumnColorRenderer(new Color(224, 175, 83), Color.black));
        tColumn1.setCellRenderer(new ColumnColorRenderer(new Color(224, 175, 83), Color.black));
        tColumn2.setCellRenderer(new ColumnColorRenderer(new Color(224, 175, 83), Color.black));
        tColumn3.setCellRenderer(new ColumnColorRenderer(new Color(224, 175, 83), Color.black));

        this.add(panel);

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        if (buttonPressed == plus) {
            if (options.getSelectedItem() == "Оберіть вид фізичної активності:") {
                return;
            }

            double hours, minutes;

            try {
                hours = Double.parseDouble(hour.getText());
            } catch (Exception err) {
                hours = 0;
            }
            try {
                minutes = Double.parseDouble(minute.getText());
            } catch (Exception err) {
                minutes = 0;
            }

            if (minutes > 59) {
                hours += Math.floor(minutes / 60);
                minutes -= Math.floor(minutes / 60) * 60;
            }
            if (hours > 12) hours = 12;
            if (hours < 0) hours = 0;
            if (!(minutes == 0 & hours == 0)) {
                tableModel.setValueAt(i + 1, i, 0);
                tableModel.setValueAt(options.getSelectedItem(), i, 1);
                tableModel.setValueAt(hours, i, 2);
                tableModel.setValueAt(minutes, i, 3);
                this.i++;
                tableModel.addRow(new Object[]{i + 1, "", "", ""});
            }
            hour.setText("год");
            minute.setText("хв");
        } else {
            UIManager.put("OptionPane.yesButtonText", "Скачати");
            UIManager.put("OptionPane.noButtonText", "Повернутись");
            UIManager.put("OptionPane.cancelButtonText", "Вийти");

            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(44, 153, 128));
            UI.put("Panel.background", new Color(150, 217, 181));
            UIManager.put("Button.background", new Color(224, 175, 83));


            double totalCalories = 0;

            for (int i = 0; i < table.getRowCount() - 1; i++) {

                String name = (String) table.getValueAt(i, 1);
                double hours = (double) table.getValueAt(i, 2);
                double minutes = (double) table.getValueAt(i, 3);

                Sport sport = new Sport(name, hours, minutes);

                totalCalories += sport.CalculateCalories();
            }

            //double avgCalories = totalCalories / table.getRowCount() - 1;
            String totalActivity;
            if (totalCalories < 200) {
                totalActivity = "Низький";
            } else if (totalCalories > 600) {
                totalActivity = "Високий";
            } else {
                totalActivity = "Середній";
            }

            String additionalMessage = "Так тримати!";
            if (totalActivity.equals("Низький")) additionalMessage = "Наступного разу спробуйте краще!";

            String message = String.format("Ви витратили %.2f калорій!\nРівень фізичної активності: %s\n%s", totalCalories, totalActivity, additionalMessage);
            int selection = JOptionPane.showConfirmDialog(null, message, "Результати", JOptionPane.YES_NO_CANCEL_OPTION);
            if (selection == 0) {
                try {
                    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("активність.txt", true), "UTF-8"));
                    out.write("---------------------------------------\n");

                    for (int i = 0; i < table.getRowCount() - 1; i++) {

                        String name = (String) table.getValueAt(i, 1);
                        double hours = (double) table.getValueAt(i, 2);
                        double minutes = (double) table.getValueAt(i, 3);

                        Sport sport = new Sport(name, hours, minutes);
                        double calories = sport.CalculateCalories();
                        String activity = sport.CalculateActivity();

                        out.write(String.format("Вид активності: %s Години: %.2f Хвилини: %.2f Калорії: %.2f Рівень активності: %s\n", name, hours, minutes, calories, activity));
                    }

                    out.write(String.format("Загальні спалені калорії: %.2f Загальна активність: %s \n", totalCalories, totalActivity));
                    out.write(String.format("Дата:   "));
                    out.write(String.valueOf(LocalDate.now()));
                    out.write(String.format("    Час:   "));
                    out.write(String.valueOf(LocalTime.now()));
                    out.write(String.format("\n"));
                    out.write("---------------------------------------\n");
                    out.close();
                    System.exit(0);

                } catch (IOException err) {
                    System.out.println("An error occurred.");
                }
            }
            if (selection == 2) {
                System.exit(0);
            }
        }
    }
}
