/*
 * Copyright 2012 FuseSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.fuse.examples.commons.itest;

import java.io.File;

import org.apache.karaf.tooling.exam.options.KarafDistributionBaseConfigurationOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.CoreOptions.maven;

/**
 * Support class for providing Fuse ESB pax-exam configuration; configured via
 * properties set in the maven-failsafe-plugin.
 */
public abstract class FusePaxExamTestSupport {

    // properties expected to be configured within the maven-failsafe-plugin

    private static final String FAILSAFE_BUILD_ARTIFACT_NAME = "failsafe.buildArtifactName";
    private static final String FAILSAFE_BUILD_DIRECTORY = "failsafe.buildDirectory";
    private static final String FAILSAFE_KARAF_GROUPID = "failsafe.karafGroupdId";
    private static final String FAILSAFE_KARAF_ARTIFACTID = "failsafe.karafArtifactId";
    private static final String FAILSAFE_KARAF_VERSION = "failsafe.karafVersion";
    private static final String FAILSAFE_KARAF_PACKAGING = "failsafe.karafPackaging";

    public static Logger log = LoggerFactory.getLogger(FusePaxExamTestSupport.class);

    /**
     * Fetches a Karaf container type based on the supplied location
     * instance defined in the project POM.
     * 
     * @param localDistro the Fuse ESB distribution
     * @return KarafDistributionBaseConfigurationOption
     */
    public static KarafDistributionBaseConfigurationOption fuseLocalFileDistributionConfiguration(String localDistro) {
        String karafVersion = System.getProperty(FAILSAFE_KARAF_VERSION);
        log.info("JBoss Fuse version: " + karafVersion);
        return karafDistributionConfiguration()
            .frameworkUrl(localDistro)
            .karafVersion(karafVersion)
            .name("JBoss Fuse")
            .unpackDirectory(new File("target/pax/unpack"));
    }

    /**
     * Fetches a Karaf configuration option preconfigured to use the ServiceMix
     * instance defined in the project POM.
     * 
     * @return KarafDistributionBaseConfigurationOption
     */
    public static KarafDistributionBaseConfigurationOption fuseMavenDistributionConfiguration() {
        String karafGroupId = System.getProperty(FAILSAFE_KARAF_GROUPID);
        String karafArtifactId = System.getProperty(FAILSAFE_KARAF_ARTIFACTID);
        String karafVersion = System.getProperty(FAILSAFE_KARAF_VERSION);
        String karafPackaging = System.getProperty(FAILSAFE_KARAF_PACKAGING);
        log.info("JBoss Fuse version: " + karafGroupId + "/" + karafArtifactId + "/" + karafVersion + "/" + karafPackaging);
         return karafDistributionConfiguration().frameworkUrl(
                   maven()
                       .groupId(karafGroupId).artifactId(karafArtifactId).type(karafPackaging).version(karafVersion))
                   .karafVersion(karafVersion)
                   .name("JBoss Fuse")
                   .unpackDirectory(new File("target/pax/unpack"));
    }

    /**
     * Obtains a <code>file:</code> path to the standard location of the
     * <code>features.xml</code> file.
     * 
     * @return A path
     */
    public static String featuresPath() {
        String featuresPath = "file:" + System.getProperty(FAILSAFE_BUILD_DIRECTORY) + "/features/features.xml";
        log.info("Features path: " + featuresPath);
        return featuresPath;
    }

    /**
     * Obtains a <code>file:</code> path to the build artifact of the project
     * under test.
     * 
     * @return A path
     */
    public static String bundlePath() {
        String bundlePath = "file:" + System.getProperty(FAILSAFE_BUILD_DIRECTORY) + System.getProperty(FAILSAFE_BUILD_ARTIFACT_NAME);
        log.info("Bundle path: " + bundlePath);
        return bundlePath;
    }
}
