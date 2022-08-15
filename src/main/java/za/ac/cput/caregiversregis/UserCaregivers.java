/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.caregiversregis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class UserCaregivers {

    private final String DB_URI = "jdbc:derby://localhost:1527/CaregiverUser";
    private final String username = "Administrator";
    private final String password = "password";

    private String caregiverCode;
    private String firstName;
    private String laastName;
    private String caregverTypeCode;
    private boolean hasResource;

    public UserCaregivers(String caregiverCode, String firstName, String laastName, String caregverTypeCode, boolean hasResource) {
        this.caregiverCode = caregiverCode;
        this.firstName = firstName;
        this.laastName = laastName;
        this.caregverTypeCode = caregverTypeCode;
        this.hasResource = hasResource;
    }

    public UserCaregivers() {
    }

    public String getCaregiverCode() {
        return caregiverCode;
    }

    public void setCaregiverCode(String caregiverCode) {
        this.caregiverCode = caregiverCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLaastName() {
        return laastName;
    }

    public void setLaastName(String laastName) {
        this.laastName = laastName;
    }

    public String getCaregverTypeCode() {
        return caregverTypeCode;
    }

    public void setCaregverTypeCode(String caregverTypeCode) {
        this.caregverTypeCode = caregverTypeCode;
    }

    public boolean isHasResource() {
        return hasResource;
    }

    public void setHasResource(boolean hasResource) {
        this.hasResource = hasResource;
    }

    public void save() {

        Connection conn = null;
        Statement st = null;
        int ok;

        try {
            conn = DriverManager.getConnection(DB_URI, username, password);
            st = conn.createStatement();
            ok = st.executeUpdate(String.format("INSERT INTO Caregivers VALUES('%s','%s','%s','%s','%s')", caregiverCode,
                    firstName,
                    laastName,
                    caregverTypeCode, hasResource));
            if (ok > 0) {
                JOptionPane.showMessageDialog(null, "Success! The information has been saved");
            } else {
                JOptionPane.showMessageDialog(null, "Failed");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Error: " + a.getMessage());

        } finally {
            try {
                conn.close();
            } catch (Exception d) {
                JOptionPane.showMessageDialog(null, "Error: " + d.getMessage());

            }

            try {
                st.close();
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error: " + f.getMessage());

            }
        }
    }

    public ArrayList<String> getValue() {
        ArrayList<String> values = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet result = null;

        try {
            conn = DriverManager.getConnection(DB_URI, username, password);
            st = conn.createStatement();
            result = st.executeQuery("SELECT caregivertypename FROM caregivertypes ");

            while (result.next()) {
                values.add(result.getObject(1).toString());
            }
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, "Error: " + sql.getMessage());

        } finally {
            try {
                conn.close();
            } catch (Exception d) {
                JOptionPane.showMessageDialog(null, "Error: " + d.getMessage());

            }

            try {
                st.close();
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error: " + f.getMessage());

            }
        }

        return values;

    }

    public boolean isUnique() {
        Connection conn = null;
        Statement st = null;
        ResultSet result = null;
        boolean unique = false;

        try {
            conn = DriverManager.getConnection(DB_URI, username, password);
            st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM caregivers WHERE caregiverCode = '" + caregiverCode + "'");

            if (!result.next()) {
                unique = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            unique = false;

        }
        return unique;

    }

}
