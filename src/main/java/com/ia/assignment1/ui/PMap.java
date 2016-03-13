/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.ia.assignment1.map.CState;
import com.ia.assignment1.map.EDirection;
import com.ia.assignment1.map.EGridType;
import com.ia.assignment1.path.CPolicy;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

/**
 *
 * @author Bryden
 */
public class PMap extends javax.swing.JPanel {

    private CState[][] aryState;
    private CPolicy[][] aryPolicy;
    private DecimalFormat df = new DecimalFormat("0.000");

    /**
     * Creates new form PMap
     */
    public PMap() {
        initComponents();
        this.setBackground(Color.WHITE);

    }

    public void setState(CState[][] pAryState) {
        aryState = pAryState;
    }

    public void setPolicy(CPolicy[][] pAryPolicy) {
        aryPolicy = pAryPolicy;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (aryState == null) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F).deriveFont(Font.BOLD);
        g2d.setFont(newFont);

        int intWidth = this.getWidth() - 20;
        int intHeight = this.getHeight() - 20;

        int dblGridWidth = intWidth / aryState[0].length;
        int dblGridHeight = intHeight / aryState.length;

        g2d.setStroke(new BasicStroke(2));

        for (int intRow = 0; intRow <= aryState.length; intRow++) {

            int intX = dblGridWidth * intRow + 5;
            int intY = dblGridHeight * intRow + 5;

            g2d.setColor(Color.BLACK);
            g2d.drawLine(5, dblGridHeight * intRow + 5, intWidth, dblGridHeight * intRow + 5);

            g2d.drawLine(intX, 5, intX, intHeight + 5);

            if (intRow == aryState.length) {
                break;
            }

            for (int intCol = 0; intCol < aryState[intRow].length; intCol++) {

                CState objState = aryState[aryState.length - 1 - intRow][intCol];

                if (objState.getType() == EGridType.WALL) {
                    g2d.setColor(Color.BLACK);
                    //g2d.fillOval(WIDTH, WIDTH, WIDTH, WIDTH);
                    g2d.fillRect(dblGridWidth * intCol + 5 + 1, intY + 1, dblGridWidth - 2, dblGridHeight - 2);
                }
                if (objState.getType() == EGridType.GOOD) {
                    g2d.setColor(Color.GREEN);
                    //g2d.fillOval(WIDTH, WIDTH, WIDTH, WIDTH);
                    g2d.fillRect(dblGridWidth * intCol + 5 + 1, intY + 1, dblGridWidth - 2, dblGridHeight - 2);
                }
                if (objState.getType() == EGridType.BAD) {
                    g2d.setColor(Color.RED);
                    //g2d.fillOval(WIDTH, WIDTH, WIDTH, WIDTH);
                    g2d.fillRect(dblGridWidth * intCol + 5 + 1, intY + 1, dblGridWidth - 2, dblGridHeight - 2);
                }

                if (aryPolicy == null || aryPolicy[aryState.length - 1 - intRow][intCol] == null) {
                    continue;
                }
                g2d.setColor(Color.BLACK);
                g2d.drawString(df.format(aryPolicy[aryState.length - 1 - intRow][intCol].getUtility()), dblGridWidth * intCol + dblGridWidth / 2 - 20, intY + dblGridHeight / 2);
                //drawTriangle(g2d, dblGridWidth * intCol + dblGridWidth / 2 - 20, intY + dblGridHeight / 2, aryPolicy[aryState.length - 1 - intRow][intCol].getDirection());
                drawTriangle(g2d, dblGridWidth * intCol + dblGridWidth / 2 - 20, intY + dblGridHeight / 2, aryPolicy[aryState.length - 1 - intRow][intCol].getDirection());
            }

        }

    }

    private void drawTriangle(Graphics2D pObjGraphics, int pIntX, int pIntY, EDirection pObjDirection) {
        pObjGraphics.setColor(Color.GRAY);

        switch (pObjDirection) {
            case UP:
                pObjGraphics.fillPolygon(new int[]{pIntX + 20, pIntX + 30, pIntX + 40}, new int[]{pIntY - 15, pIntY - 35, pIntY - 15}, 3);
                break;
            case DOWN:
                pObjGraphics.fillPolygon(new int[]{pIntX + 20, pIntX + 30, pIntX + 40}, new int[]{pIntY + 10, pIntY + 30, pIntY + 10}, 3);
                break;
            case LEFT:
                pObjGraphics.fillPolygon(new int[]{pIntX - 30, pIntX - 20, pIntX - 20}, new int[]{pIntY, pIntY - 10, pIntY + 10}, 3);
                break;
            case RIGHT:
                pObjGraphics.fillPolygon(new int[]{pIntX + 70, pIntX + 60, pIntX + 60}, new int[]{pIntY, pIntY - 10, pIntY + 10}, 3);
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
