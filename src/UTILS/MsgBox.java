/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Hân Mai
 */
public class MsgBox {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,"EduConnect", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void alertLoi(Component parent, String message) {
        String htmlMessage = "<html><font color='red'>" + message + "</font></html>";
        JOptionPane.showMessageDialog(parent, htmlMessage, "EduConnect", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "EduConnect", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "EduConnect", JOptionPane.INFORMATION_MESSAGE);
    }
}
