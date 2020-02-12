// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.util;

public interface TestSuiteLoader
{
    Class load(final String p0) throws ClassNotFoundException;
    
    Class reload(final Class p0) throws ClassNotFoundException;
}
