/**
 * 
 */
package org.apache.karaf.tooling.exam.container.internal.adaptions;

import org.osgi.framework.Version;

/**
 * @author scenglan
 *
 */
public class KarafManipulatorFactory {

    public static KarafManipulator createManipulator(String karafVersion) {
        int dots = 0;
        int i = 0;
        while ((i = karafVersion.indexOf('.', i)) != -1) {
            dots++;
            i++;
        }
        Version version;
        if (dots < 3) {           
            version = new Version(karafVersion.replaceFirst("-", "."));
        } else {
            version = new Version(karafVersion);
        }   
        if (version.getMajor() < 2 || version.getMajor() == 2 && version.getMinor() < 2) {
            throw new IllegalArgumentException("Karaf versions < 2.2.0 are not supported");
        }
        return new KarafManipulatorStartingFrom220();
    }
}
