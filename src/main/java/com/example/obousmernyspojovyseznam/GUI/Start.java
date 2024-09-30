package com.example.obousmernyspojovyseznam.GUI;

import javax.swing.*;

public class Start{
    public static void main(String[] args) {
        Gui okno = new Gui();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        okno.setSize(700, 300);
        JButton tlacitko = new JButton("zmáčni mě");
        okno.add(tlacitko);
    }
    
}
