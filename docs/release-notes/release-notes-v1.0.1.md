# Release notes &ndash; version v1.0.1

## Using this release version with Maven / Gradle

<details name="import-coordinates" open>
<summary>Maven</summary>

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.typefactory</groupId>
      <artifactId>type-factory-bom</artifactId>
      <version>1.0.1</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-core</artifactId>
  </dependency>
  <dependency>
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-language</artifactId>
  </dependency>
</dependencies>
```
</details>

<details name="import-coordinates">
<summary>Gradle</summary>

```groovy
dependencies {
  implementation platform("org.typefactory:type-factory-bom:1.0.1")

  // Required: the core Type Factory module. 
  implementation "org.typefactory:type-factory-core"

  // Optional: predefined language related data.
  implementation "org.typefactory:type-factory-language"
}
```

</details>


## What's Changed

### Fixes:
* Issue #197 – Added the ability to detect corrupt char sequences with incomplete supplementary code points. PR https://github.com/type-factory/type-factory/pull/198
* Issue #263 – Fixed bug parsing blank string to a null. PR https://github.com/type-factory/type-factory/pull/264

### Build and continuous integration updates

* Issue #267 – Fix maven-publish Github Actions Workflow In pull request https://github.com/type-factory/type-factory/pull/268
* Update version to 1.0.1-SNAPSHOT – version was 1.0.0 In pull request https://github.com/type-factory/type-factory/pull/174
* Updated readme to provide maven coordinates to the latest release. In pull request https://github.com/type-factory/type-factory/pull/175
* Create scorecard.yml In pull request https://github.com/type-factory/type-factory/pull/178
* Updated readme and the scorecard workflow In pull request https://github.com/type-factory/type-factory/pull/179
* Update dependencies In pull request https://github.com/type-factory/type-factory/pull/180
* Updated scorecard workflow In pull request https://github.com/type-factory/type-factory/pull/181
* Updated README.md and .gitignore In pull request https://github.com/type-factory/type-factory/pull/182
* Updated README.md In pull request https://github.com/type-factory/type-factory/pull/183
* Bump maven-javadoc-plugin from 3.4.1 to 3.5.0 by @dependabot in https://github.com/type-factory/type-factory/pull/184
* Bump flatten-maven-plugin from 1.2.7 to 1.5.0 by @dependabot in https://github.com/type-factory/type-factory/pull/195
* Bump maven-surefire-plugin from 3.0.0-M8 to 3.1.0 by @dependabot in https://github.com/type-factory/type-factory/pull/194
* Bump mockito-bom from 5.1.1 to 5.3.1 by @dependabot in https://github.com/type-factory/type-factory/pull/193
* Bump maven-compiler-plugin from 3.10.1 to 3.11.0 by @dependabot in https://github.com/type-factory/type-factory/pull/187
* Issue #199 – Update Maven dependency and plugin versions. In pull request https://github.com/type-factory/type-factory/pull/200
* set sonar-maven-plugin version In pull request https://github.com/type-factory/type-factory/pull/209
* Bump com.sun.xml.bind:jaxb-impl from 4.0.2 to 4.0.4 by @dependabot in https://github.com/type-factory/type-factory/pull/208
* update github actions versions In pull request https://github.com/type-factory/type-factory/pull/210
* Bump jakarta.xml.bind:jakarta.xml.bind-api from 4.0.0 to 4.0.1 by @dependabot in https://github.com/type-factory/type-factory/pull/207
* Bump org.apache.maven.plugins:maven-gpg-plugin from 3.0.1 to 3.1.0 by @dependabot in https://github.com/type-factory/type-factory/pull/206
* Bump junit-jupiter.version from 5.9.1 to 5.10.0 by @dependabot in https://github.com/type-factory/type-factory/pull/204
* Issue #222 Update maven dependency versions and workflow action versions In pull request https://github.com/type-factory/type-factory/pull/223
* Dependabot doesn't have access to GitHub secrets In pull request https://github.com/type-factory/type-factory/pull/230
* Bump com.sun.xml.bind:jaxb-impl from 4.0.4 to 4.0.5 by @dependabot in https://github.com/type-factory/type-factory/pull/229
* Bump jakarta.xml.bind:jakarta.xml.bind-api from 4.0.1 to 4.0.2 by @dependabot in https://github.com/type-factory/type-factory/pull/228
* Bump org.codehaus.mojo:exec-maven-plugin from 3.1.0 to 3.3.0 by @dependabot in https://github.com/type-factory/type-factory/pull/225
* Bump org.assertj:assertj-core from 3.21.0 to 3.26.0 by @dependabot in https://github.com/type-factory/type-factory/pull/227
* Bump icu4j.version from 75.1 to 75.1 by @dependabot in https://github.com/type-factory/type-factory/pull/226
* Bump junit-jupiter.version from 5.10.0 to 5.10.2 by @dependabot in https://github.com/type-factory/type-factory/pull/235
* Bump org.apache.maven.plugins:maven-surefire-plugin from 3.2.5 to 3.3.0 by @dependabot in https://github.com/type-factory/type-factory/pull/236
* Bump org.apache.maven.plugins:maven-gpg-plugin from 3.1.0 to 3.2.4 by @dependabot in https://github.com/type-factory/type-factory/pull/233
* Bump org.codehaus.mojo:flatten-maven-plugin from 1.5.0 to 1.6.0 by @dependabot in https://github.com/type-factory/type-factory/pull/232
* Bump org.codehaus.mojo:jaxb2-maven-plugin from 3.1.0 to 3.2.0 by @dependabot in https://github.com/type-factory/type-factory/pull/231
* Bump junit-jupiter.version from 5.10.2 to 5.10.3 by @dependabot in https://github.com/type-factory/type-factory/pull/237
* Bump org.assertj:assertj-core from 3.26.0 to 3.26.3 by @dependabot in https://github.com/type-factory/type-factory/pull/238
* Bump org.codehaus.mojo:exec-maven-plugin from 3.3.0 to 3.4.1 by @dependabot in https://github.com/type-factory/type-factory/pull/242
* Bump junit-jupiter.version from 5.10.3 to 5.11.0 by @dependabot in https://github.com/type-factory/type-factory/pull/243
* Bump org.apache.maven.plugins:maven-javadoc-plugin from 3.7.0 to 3.10.1 by @dependabot in https://github.com/type-factory/type-factory/pull/247
* Bump org.apache.maven.plugins:maven-surefire-plugin from 3.3.0 to 3.5.1 by @dependabot in https://github.com/type-factory/type-factory/pull/248
* Updated OSSF scorecard workflow In pull request https://github.com/type-factory/type-factory/pull/249
* Bump icu4j.version from 75.1 to 76.1 by @dependabot in https://github.com/type-factory/type-factory/pull/256
* Bump junit-jupiter.version from 5.11.0 to 5.11.3 by @dependabot in https://github.com/type-factory/type-factory/pull/255
* Bump org.mockito:mockito-bom from 5.12.0 to 5.14.2 by @dependabot in https://github.com/type-factory/type-factory/pull/254
* Bump org.apache.maven.plugins:maven-deploy-plugin from 3.1.2 to 3.1.3 by @dependabot in https://github.com/type-factory/type-factory/pull/253
* Bump org.apache.maven.plugins:maven-gpg-plugin from 3.2.4 to 3.2.7 by @dependabot in https://github.com/type-factory/type-factory/pull/252
* build: Update version for release to 1.0.1 – version was 1.0.1-SNAPSHOT In pull request https://github.com/type-factory/type-factory/pull/269

### Full Changelog

* https://github.com/type-factory/type-factory/compare/v1.0.0...v1.0.1