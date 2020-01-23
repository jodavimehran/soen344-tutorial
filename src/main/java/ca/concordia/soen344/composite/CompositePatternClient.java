package ca.concordia.soen344.composite;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class CompositePatternClient {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();

            AbstractFile topDir = new Directory(selectedDirectory);
            System.out.println(topDir.ls());
            System.out.println(topDir.size());
            System.out.println(topDir.countFiles());
            
        }
    }

    /**
     * This method provides a sample TreeNode
     *
     * @return a sample TreeNode which contains five parent. Each single parent is contains some childes that is vary from one to five.
     */
    private static DefaultMutableTreeNode createSampleTreeNode() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        for (int i = 1; i <= 5; i++) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode("Parent" + i);
            rootNode.add(parentNode);
            for (int j = 1; j <= i; j++) {
                parentNode.add(new DefaultMutableTreeNode("Child" + i + j));
            }
        }
        return rootNode;
    }
}
