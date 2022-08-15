/*
// * GUI.java
// * This is the GUI section
// * Author: Christian Mukuna Mbuyi (221478302)
// * 05/08/2022
 */
package za.ac.cput.caregiversregis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Christian Mukuna Mbuyi
 */
public class CaregiversGUI extends JFrame implements ActionListener {
    // STEP 2: Declare variables

    private JFrame mainFrame;
    private JPanel panelNorth, panelCenter, panelSouth, panelGender, panelEmpty;

    private JLabel lblLogo;
    private JLabel lblHeading;
    private JLabel lblLabel0, lblLabel01, lblLabel1, lblLabel2, lblLabel3, lblLabel4, lblLabel5, lblLabel6, lblLabel7;

    private JLabel lblCaregiverCode;
    private JTextField txtCaregiverCode;
    private JLabel lblErrorCaregiverCode;

    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JLabel lblErrorFirstName;

    private JLabel lblLasttName;
    private JTextField txtLastName;
    private JLabel lblErrorLastName;

    private JLabel lblCareEquip;
    private JPanel panelCareEquip;
    private JRadioButton radYes;
    private JRadioButton radNo;
    private ButtonGroup CareEquip;

//declare JCheckBoxess
    private JLabel lblCaregivers;
    private JComboBox cboCaregivers;

    private JButton btnSave;
    private JButton btnRest;
    private JButton btnExit;

    private Font ft1, ft2, ft3;

//Instantiate all the instance variables in the constructor
    public CaregiversGUI() {
        super("Caregivers Registration App version 1.0");

        // mainFrame = new JFrame();
        this.setPreferredSize(new Dimension(1000, 500));
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelGender = new JPanel();
        panelSouth = new JPanel();
        panelEmpty = new JPanel();

        lblLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("man.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        lblLogo.setIcon(imageIcon);
        lblHeading = new JLabel("Caregiver Registratiom System");

        lblCaregiverCode = new JLabel("Caregiver Code:");
        txtCaregiverCode = new JTextField(10);
        lblErrorCaregiverCode = new JLabel("Caregiver required");
        lblErrorCaregiverCode.setForeground(Color.red);
        lblErrorCaregiverCode.setVisible(false);

        lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField(15);
        lblErrorFirstName = new JLabel("*required* please fill in");
        lblErrorFirstName.setForeground(Color.red);
        lblErrorFirstName.setVisible(false);

        lblLasttName = new JLabel("Last Name:");
        txtLastName = new JTextField(15);
        lblErrorLastName = new JLabel("*required* please fill in");
        lblErrorLastName.setForeground(Color.red);
        lblErrorLastName.setVisible(false);

        UserCaregivers u = new UserCaregivers();
        String[] types = new String[u.getValue().size()];

        for (int i = 0; i < types.length; i++) {
            types[i] = u.getValue().get(i);
        }
// Get data and pass it into combox
        lblCaregivers = new JLabel("Caregiver Type:");
        String comboCaregiver[] = {"", "", ""};
        cboCaregivers = new JComboBox(types);

        lblCareEquip = new JLabel("Do you have caregiver equipment: ");
        panelCareEquip = new JPanel();
        radYes = new JRadioButton("YES");
        radNo = new JRadioButton("NO");

        CareEquip = new ButtonGroup();
        CareEquip.add(radYes);
        CareEquip.add(radNo);
        panelCareEquip.setLayout(new GridLayout(1, 2));
        //radMale.setSelected(true);
        panelCareEquip.add(radYes);
        panelCareEquip.add(radNo);

        ft1 = new Font("Arial", Font.BOLD, 33);
        ft2 = new Font("Arial", Font.PLAIN, 21);
        ft3 = new Font("Arial", Font.ITALIC, 15);

        lblLabel0 = new JLabel("");
        lblLabel01 = new JLabel("");
        lblLabel1 = new JLabel("");
        lblLabel2 = new JLabel("");
        lblLabel3 = new JLabel("");
        lblLabel4 = new JLabel("");
        lblLabel5 = new JLabel("");
        lblLabel6 = new JLabel("");
        lblLabel7 = new JLabel("");

        btnSave = new JButton("Save");
        btnRest = new JButton("Reset");
        btnExit = new JButton("Exit");
    }
//  Now lets set up the GUI

    public void setGUI() {
        this.setTitle("Caregivers Registration App version1.0");

        panelNorth.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(5, 3));
        panelSouth.setLayout(new GridLayout(1, 3));

        panelNorth.add(lblLogo);
        panelNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        panelNorth.setBackground(new Color(137, 27, 200));

        lblCaregiverCode.setFont(ft2);
        lblCaregiverCode.setHorizontalAlignment(JLabel.RIGHT);
        txtCaregiverCode.setFont(ft2);
        panelCenter.add(lblCaregiverCode);
        panelCenter.add(txtCaregiverCode);
        panelCenter.add(lblErrorCaregiverCode);
        panelCenter.add(lblLabel1);

        lblFirstName.setFont(ft2);
        lblFirstName.setHorizontalAlignment(JLabel.RIGHT);
        txtFirstName.setFont(ft2);
        panelCenter.add(lblFirstName);
        panelCenter.add(txtFirstName);
        panelCenter.add(lblErrorFirstName);
        panelCenter.add(lblLabel1);

        lblLasttName.setFont(ft2);
        lblLasttName.setHorizontalAlignment(JLabel.RIGHT);
        txtLastName.setFont(ft2);
        panelCenter.add(lblLasttName);
        panelCenter.add(txtLastName);
        panelCenter.add(lblErrorLastName);
        panelCenter.add(lblLabel1);

        lblCaregivers.setFont(ft2);
        lblCaregivers.setHorizontalAlignment(JLabel.RIGHT);
        lblCaregivers.setFont(ft2);
        cboCaregivers.setFont(ft2);
        panelCenter.add(lblCaregivers);
        panelCenter.add(cboCaregivers);
        panelCenter.add(lblLabel2);

        lblCareEquip.setFont(ft2);
        lblCareEquip.setHorizontalAlignment(JLabel.LEFT);
        radYes.setFont(ft2);
        radNo.setFont(ft2);

        panelCenter.add(lblCareEquip);
        panelCenter.add(panelCareEquip);
        panelCenter.add(lblLabel1);

        btnSave.setFont(ft2);
        btnRest.setFont(ft2);
        btnExit.setFont(ft2);

        panelSouth.add(btnSave);
        panelSouth.add(btnRest);
        panelSouth.add(btnExit);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Lets add action listeners onto our gui components
        btnSave.addActionListener(this);
        btnRest.addActionListener(this);
        btnExit.addActionListener(this);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void resetForm() {
        txtCaregiverCode.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        cboCaregivers.setSelectedIndex(0);
        CareEquip.clearSelection();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            UserCaregivers user = new UserCaregivers(txtCaregiverCode.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(), cboCaregivers.getSelectedItem().toString(), radYes.isSelected());

            if (user.isUnique()) {
                user.save();
            } else {
                JOptionPane.showMessageDialog(null, "caregiver code is not unique");

            }

        } else if (e.getSource() == btnRest) {
            resetForm();
        } else if (e.getSource() == btnExit) {
            int Option = JOptionPane.showConfirmDialog(this, "Do you want to Exit ", "Exit", JOptionPane.YES_NO_OPTION);
            if (Option == 0) {
                System.exit(0);
            }

        }

    }

}
