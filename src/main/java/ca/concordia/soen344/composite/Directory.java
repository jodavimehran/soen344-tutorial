package ca.concordia.soen344.composite;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class Directory extends AbstractFile {
    private List<AbstractFile> contents = new ArrayList<AbstractFile>();

    public Directory(java.io.File directory) {
        this(0, directory);
    }

    private Directory(int level, java.io.File directory) {
        super(level, directory.getName());
        java.io.File[] files = directory.listFiles();
        for (java.io.File file : files) {
            if (file.isDirectory()) {
                contents.add(new Directory(getLevel() + 1, file));
            } else {
                contents.add(new LeafFile(getLevel() + 1, file.getName(), file.length()));
            }
        }
    }

    @Override
    public String ls() {
        StringBuilder sb = new StringBuilder();
        sb.append(printTabs()).append(getName()).append("\n");
        for (AbstractFile file : contents) {
            sb.append(file.ls());
        }
        return sb.toString();
    }

    @Override
    public long size() {
        long size = 0;
        for (AbstractFile file : contents) {
            size += file.size();
        }
        return size;
    }

    @Override
    public int countFiles() {
        int count = 0;
        for (AbstractFile file : contents) {
            count += file.countFiles();
        }
        return count;
    }

    @Override
    public DefaultMutableTreeNode createNode() {
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(getName());
        for (AbstractFile content : contents) {
            parentNode.add(content.createNode());
        }
        return parentNode;
    }
}
