package ca.concordia.soen344.composite;

public abstract class AbstractFile {
    private int level;
    private String name;

    public AbstractFile(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    protected String printTabs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    public abstract String ls();

    public abstract long size();

    public abstract int countFiles();
}
