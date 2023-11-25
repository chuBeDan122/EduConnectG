/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.*;

/**
 *
 * @author Hân Mai
 */
public class XImage {

    public static Image getAppIcon() {
        String file = "images\\fpt.png";
        return new ImageIcon(XImage.class.getResource(file)).getImage();
    }

    public static void save(File src) {
        File dst = new File("logo", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("logo", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static ImageIcon scaleImageToFitLabel(ImageIcon icon, JLabel label) {
        int labelWidth = label.getWidth();
        int labelHeight = label.getHeight();

        // Thay đổi kích thước hình ảnh để vừa với JLabel
        Image scaledImage = icon.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public static File saveExel(File src) {
        File dst = new File("storeFiles", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
