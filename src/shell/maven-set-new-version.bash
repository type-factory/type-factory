#!/usr/bin/env bash

NEW_VERSION="$1"

CURRENT_VERSION="$(mvn --batch-mode --quiet -Dexpression=project.version -DforceStdout help:evaluate)"

# Check current Git branch is main
CURRENT_BRANCH="$(git rev-parse --abbrev-ref HEAD)"
if [ "$CURRENT_BRANCH" != "main" ]; then
  echo "Error: Current branch is not 'main'. Please switch to the 'main' branch before running this script."
  exit 1
fi

# Check NEW_VERSION is not empty
if [ -z "$NEW_VERSION" ]; then
  echo "Error: NEW_VERSION is empty. Please provide a new version as the first argument."
  exit 1
fi

# Check NEW_VERSION is greater than the CURRENT_VERSION according to semantic versioning
# Combine versions, sort them, and check if the higher version is the new one
if [ "$(printf '%s\n%s' "$CURRENT_VERSION" "$NEW_VERSION" | sort -V | tail -n1)" = "$NEW_VERSION" ] && [ "$CURRENT_VERSION" != "$NEW_VERSION" ]; then
  echo "New version number ($NEW_VERSION) is greater than the current version number ($CURRENT_VERSION)"
else
  echo "Error: New version number ($NEW_VERSION) must be greater than the current version number ($CURRENT_VERSION)"
  exit 1
fi

# Checkout a new branch and update the version in the pom.xml files
BRANCH_NAME="set-new-version-${NEW_VERSION//\./-}" &&
echo &&
echo &&
echo "--------------------------------------------------------------" &&
echo "Creating and checking out new branch ${BRANCH_NAME}" &&
git checkout -b "${BRANCH_NAME}" &&
echo &&
echo &&
echo "--------------------------------------------------------------" &&
echo "Update version to ${NEW_VERSION} – version was ${CURRENT_VERSION}" &&
mvn --batch-mode --quiet -DnewVersion="${NEW_VERSION}" -DprocessAllModules=true versions:set &&
echo &&
echo &&
echo "--------------------------------------------------------------" &&
echo "Committing to local branch" &&
git commit --gpg-sign --all --message "Update version for release to ${NEW_VERSION} – version was ${CURRENT_VERSION}" &&
git verify-commit HEAD &&
echo &&
echo &&
echo "--------------------------------------------------------------" &&
echo "Verifying the application" &&
mvn --batch-mode -Dlogging.level.org.typefactory=WARNING verify &&
echo &&
echo &&
echo "--------------------------------------------------------------" &&
echo "Pushing commit to GitHub on branch ${BRANCH_NAME}" &&
git push --set-upstream origin "${BRANCH_NAME}" &&
echo &&
echo &&
echo "--------------------------------------------------------------" &&
echo "Next steps:" &&
echo "1. Create a pull request on GitHub for branch ${BRANCH_NAME} to merge into 'main'." &&
echo "   Approve and merge the pull request. Confirm the Java build workflow succeeded on 'main'." &&
echo "2. Run the 'Publish to Maven Central Repository' GitHub workflow. This will:" &&
echo "   - Publish the artifacts to the Sonatype Maven Central staging area." &&
echo "   - Create a new branch in GitHub with the new SNAPSHOT version applied You need to create a pull request." &&
echo "3. Login into Sonatype - https://central.sonatype.com/" &&
echo "4. " &&
echo "5. Create a new release in GitHub releases." &&
echo "   - Use GitHub to create release notes – edit them as necessary." &&
