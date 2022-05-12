package KW_2;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class ColumnColorRenderer extends DefaultTableCellRenderer {
    Color backgroundColor, foregroundColor;
    public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
        super();
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell.setBackground(backgroundColor);
        cell.setForeground(foregroundColor);
        return cell;
    }
}
