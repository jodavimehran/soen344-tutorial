// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.util;

public class ReloadingTestSuiteLoader implements TestSuiteLoader
{
    public Class load(final String suiteClassName) throws ClassNotFoundException {
        final TestCaseClassLoader loader = new TestCaseClassLoader();
        return loader.loadClass(suiteClassName, true);
    }
    
    public Class reload(final Class aClass) throws ClassNotFoundException {
        final TestCaseClassLoader loader = new TestCaseClassLoader();
        return loader.loadClass(aClass.getName(), true);
    }
}
