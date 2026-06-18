package ui;

import javax.swing.*;
import java.awt.*;

public class UIHelper {

    public static final Color PRIMARY = new Color(52, 152, 219);
    public static final Color SUCCESS = new Color(46, 204, 113);
    public static final Color PURPLE = new Color(155, 89, 182);
    public static final Color ORANGE = new Color(230, 126, 34);
    public static final Color TEAL = new Color(26, 188, 156);
    public static final Color FLAT = new Color(200, 200, 200);
    public static final Color BG = new Color(245, 245, 245);

    public static JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 38));
        return btn;
    }

    public static JButton createFlatButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(FLAT);
        btn.setForeground(new Color(60, 60, 60));
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 32));
        return btn;
    }

    public static JLabel createErrorLabel() {
        JLabel lbl = new JLabel("", SwingConstants.CENTER);
        lbl.setForeground(Color.RED);
        lbl.setFont(new Font("Arial", Font.PLAIN, 11));
        return lbl;
    }

    public static JLabel createTitleLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setForeground(new Color(60, 60, 60));
        return lbl;
    }

    public static JLabel createSubtitleLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setForeground(new Color(120, 120, 120));
        return lbl;
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG);
        return panel;
    }

    public static GridBagConstraints defaultGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 30, 8, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        return gbc;
    }
}
