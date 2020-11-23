import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    public Button(String text, int w, int h) {
        super(text);
        super.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        super.setPreferredSize(new Dimension(w, h));
        super.setOpaque(true);
        super.setBackground(new Color(0, 80, 157));
        super.setForeground(Color.white);
        super.setBorder(new EmptyBorder(0, 0, 0, 0));

    }
    public void setCursor(Cursor aCursor) {
        super.setCursor(aCursor);
    }

}
