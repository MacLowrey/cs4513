
package view;

import controller.ImageFinder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import model.GameData;

public class BannerPanel extends JPanel {
    private JTextField txtLevel;
    
    public static int pwidth, pheight;
    
    public BannerPanel(int pwidth, int pheight)
    {
        BannerPanel.pwidth = pwidth;
        BannerPanel.pheight = pheight;
        
        initComponents();
    }

    private void initComponents(){
        setPreferredSize(new Dimension(BannerPanel.pwidth, BannerPanel.pheight));
        setBackground(Color.cyan);
        setBorder(BorderFactory.createLineBorder(Color.gray, 20));
        txtLevel = new JTextField("Welcome to Level "+String.valueOf(GameData.level));
        txtLevel.setFont(new Font("Tahoma", 0, 36));
        txtLevel.setHorizontalAlignment(JTextField.CENTER);
        txtLevel.setEditable(false);
        
        this.setLayout(new BorderLayout());
        this.add(txtLevel, BorderLayout.CENTER);
    }
}

