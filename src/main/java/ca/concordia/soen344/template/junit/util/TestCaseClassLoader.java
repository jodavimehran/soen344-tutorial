// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.util;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;

public class TestCaseClassLoader extends ClassLoader
{
    private String[] fPathItems;
    private String[] fExcluded;
    static final String EXCLUDED_FILE = "excluded.properties";

    public TestCaseClassLoader() {
        this.fExcluded = new String[] { "com.sun.", "sun." };
        final String classPath = System.getProperty("java.class.path");
        final String separator = System.getProperty("path.separator");
        StringTokenizer st = new StringTokenizer(classPath, separator);
        int i = 0;
        while (st.hasMoreTokens()) {
            st.nextToken();
            ++i;
        }
        this.fPathItems = new String[i];
        st = new StringTokenizer(classPath, separator);
        i = 0;
        while (st.hasMoreTokens()) {
            this.fPathItems[i++] = st.nextToken();
        }
        final String[] excluded = this.readExcludedPackages();
        if (excluded != null) {
            this.fExcluded = excluded;
        }
    }

    public URL getResource(final String name) {
        return ClassLoader.getSystemResource(name);
    }

    public InputStream getResourceAsStream(final String name) {
        return ClassLoader.getSystemResourceAsStream(name);
    }

    protected boolean isExcluded(final String name) {
        if (name.startsWith("java.") || name.startsWith("junit.framework") || name.startsWith("junit.extensions") || name.startsWith("junit.util") || name.startsWith("junit.ui")) {
            return true;
        }
        for (int i = 0; i < this.fExcluded.length; ++i) {
            if (name.startsWith(this.fExcluded[i])) {
                return true;
            }
        }
        return false;
    }

    public synchronized Class loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        Class c = this.findLoadedClass(name);
        if (c != null) {
            return c;
        }
        if (this.isExcluded(name)) {
            try {
                c = this.findSystemClass(name);
                return c;
            }
            catch (ClassNotFoundException ex) {}
        }
        if (c == null) {
            final File file = this.locate(name);
            if (file == null) {
                throw new ClassNotFoundException();
            }
            final byte[] data = this.loadClassData(file);
            c = this.defineClass(name, data, 0, data.length);
        }
        if (resolve) {
            this.resolveClass(c);
        }
        return c;
    }

    private byte[] loadClassData(final File f) throws ClassNotFoundException {
        try {
            final FileInputStream stream = new FileInputStream(f);
            try {
                final byte[] b = new byte[stream.available()];
                stream.read(b);
                stream.close();
                return b;
            }
            catch (IOException e) {
                throw new ClassNotFoundException();
            }
        }
        catch (FileNotFoundException e2) {
            throw new ClassNotFoundException();
        }
    }

    private File locate(String fileName) {
        fileName = String.valueOf(fileName.replace('.', '/')) + ".class";
        File path = null;
        if (fileName != null) {
            for (int i = 0; i < this.fPathItems.length; ++i) {
                path = new File(this.fPathItems[i], fileName);
                if (path.exists()) {
                    return path;
                }
            }
        }
        return null;
    }

    private String[] readExcludedPackages() {
        final InputStream is = this.getClass().getResourceAsStream("excluded.properties");
        if (is == null) {
            return null;
        }
        final Properties p = new Properties();
        try {
            p.load(is);
        }
        catch (IOException e2) {
            return null;
        }
        final Vector v = new Vector(10);
        final Enumeration e = p.propertyNames();
        while (e.hasMoreElements()) {
            final Object key = e.nextElement();
            if (key.toString().startsWith("excluded.")) {
                String path = p.getProperty(key.toString());
                if (path.endsWith("*")) {
                    path = path.substring(0, path.length() - 1);
                }
                if (path.length() <= 0) {
                    continue;
                }
                v.addElement(path);
            }
        }
        final String[] excluded = new String[v.size()];
        for (int i = 0; i < v.size(); ++i) {
            excluded[i] = v.elementAt(i).toString();
        }
        return excluded;
    }
}