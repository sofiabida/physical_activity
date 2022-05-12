package KW_2;
import javax.swing.JTextField;
import java.awt.event.*;

public class FancyTextField extends JTextField {

    public void setPlaceholder(String placeholder) {
        FancyTextField textfield = this;
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textfield.getText().equals(placeholder)) {
                    textfield.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textfield.getText().isEmpty()) {
                    textfield.setText(placeholder);
                }
            }
        });
    }

    FancyTextField(String text) {
        super(text);
    }


}
