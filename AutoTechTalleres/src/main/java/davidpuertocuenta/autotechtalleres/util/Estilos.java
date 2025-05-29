/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

/**
 *
 * @author David Puerto Cuenca
 */
public class Estilos {
         public static void aplicarEstiloBoton(JButton boton) {
        // Ajustes estándar de AutoTech
        int ancho = 180;
        int alto = 40;
        int radio = 20;
        Color colorFondo = new Color(33, 150, 243); // Azul AutoTech
        Font fuente = new Font("Segoe UI", Font.BOLD, 14);

        // Aplicar estilo
        boton.setPreferredSize(new Dimension(ancho, alto));
        boton.setFont(fuente);
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorFondo);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Redondeado con UI personalizada
        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(boton.getBackground());
                g2.fillRoundRect(0, 0, boton.getWidth(), boton.getHeight(), radio, radio);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }
         
        public static void aplicarEstiloTextField(JTextField campo) {
        int radio = 20;
        int alto = 40;
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);
        Color colorFondo = Color.WHITE;
        Color colorBorde = new Color(33, 150, 243); // Azul AutoTech

        campo.setPreferredSize(new Dimension(180, alto));
        campo.setFont(fuente);
        campo.setForeground(Color.BLACK);
        campo.setBackground(colorFondo);
        campo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        campo.setOpaque(false); // para personalizar el fondo redondeado

        // UI personalizada para redondear y dibujar borde
        campo.setUI(new javax.swing.plaf.basic.BasicTextFieldUI() {
            @Override
            protected void paintSafely(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Antialias para bordes suaves
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo blanco redondeado
                g2.setColor(colorFondo);
                g2.fillRoundRect(0, 0, campo.getWidth(), campo.getHeight(), radio, radio);

                // Borde azul redondeado
                g2.setColor(colorBorde);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, campo.getWidth()-3, campo.getHeight()-3, radio, radio);

                g2.dispose();

                super.paintSafely(g);
            }
        });
    }
        
        public static void aplicarEstiloPasswordField(JPasswordField campo) {
        int radio = 20;
        int alto = 40;
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);
        Color colorFondo = Color.WHITE;
        Color colorBorde = new Color(33, 150, 243); // Azul AutoTech

        campo.setPreferredSize(new Dimension(180, alto));
        campo.setFont(fuente);
        campo.setForeground(Color.BLACK);
        campo.setBackground(colorFondo);
        campo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        campo.setOpaque(false); // para personalizar el fondo redondeado

        // UI personalizada para redondear y dibujar borde
        campo.setUI(new BasicPasswordFieldUI() {
            @Override
            protected void paintSafely(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Antialias para bordes suaves
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo blanco redondeado
                g2.setColor(colorFondo);
                g2.fillRoundRect(0, 0, campo.getWidth(), campo.getHeight(), radio, radio);

                // Borde azul redondeado
                g2.setColor(colorBorde);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, campo.getWidth() - 3, campo.getHeight() - 3, radio, radio);

                g2.dispose();

                super.paintSafely(g);
            }
        });
    }
}
