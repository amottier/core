## Contributing
### Coding Rules
Follow these Java coding guidelines:
* [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html), except braces must follow the Allman style instead of K & R style;
* *Effective Java, Second Edition*, by Joshua Bloch;
* [Oracle Secure Coding Guidelines for Java SE](http://www.oracle.com/technetwork/java/seccodeguide-139067.html).

### Testing
For every new major functionality, there must be unit tests added to some unit test class that is part of the automated test suite of [pdp-engine's MainTest.java](pdp-engine/src/test/java/org/ow2/authzforce/core/pdp/impl/test/MainTest.java). If the functionality has any impact on XACML - any Request/Response/Policy(Set) element - processing and/or change XACML standard conformance in anyway, make sure you add relevant integration and/or conformance tests to the test suite run by [pdp-testutils's MainTest.java](pdp-testutils/src/test/java/org/ow2/authzforce/core/pdp/testutil/test/MainTest.java).

You may run the tests as follows from your local copy of the repository:
<pre><code>
    $ mvn test
</code></pre>

### Building the project

You may build the project and generate the JAR as follows from your local copy of the repository:
<pre><code>
    $ mvn package
</code></pre>

### Dependency management
1. No SNAPSHOT dependencies on "develop" and obviously "master" branches

### Releasing
1. From the develop branch, prepare a release (example using a HTTP proxy):
<pre><code>
    $ mvn -Dhttps.proxyHost=proxyhostname -Dhttps.proxyPort=80 jgitflow:release-start
</code></pre>
1. Update the CHANGELOG according to keepachangelog.com.
1. To perform the release (example using a HTTP proxy):
<pre><code>
    $ mvn -Dhttps.proxyHost=proxyhostname -Dhttps.proxyPort=80 jgitflow:release-finish
</code></pre>
    If, after deployment, the command does not succeed because of some issue with the branches. Fix the issue, then re-run the same command but with 'noDeploy' option set to true to avoid re-deployment:
<pre><code>
    $ mvn -Dhttps.proxyHost=proxyhostname -Dhttps.proxyPort=80 -DnoDeploy=true jgitflow:release-finish
</code></pre>
1. Connect and log in to the OSS Nexus Repository Manager: https://oss.sonatype.org/
1. Go to Staging Profiles and select the pending repository authzforce-*... you just uploaded with `jgitflow:release-finish`
1. Click the Release button to release to Maven Central.

More info on jgitflow: http://jgitflow.bitbucket.org/
