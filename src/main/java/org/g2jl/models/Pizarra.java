package org.g2jl.models;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class Pizarra extends Canvas {
    public Pizarra() {
        setBackground(Color.WHITE);
//        setSize(this.getParent().getPreferredSize());
//        setSize(new Dimension(700, 200));
        System.out.println(this.getParent());
    }

    public void paint(Graphics g) {
        g.drawLine(20, 20, 100, 100);
    }

    public void paintGant(List<Process> processes) {
//        for (Process process : processes) {}
        Line2D linea = new Line2D.Double(20, 20, 100, 100);
    }
}
