#!/bin/bash

export CURRENT_VERSION="$(mvn help:evaluate --batch-mode --quiet -Dexpression=project.version -DforceStdout)" &&
export RELEASE_VERSION="${CURRENT_VERSION%-SNAPSHOT}" &&
export CURRENT_PATCH_VERSION="${RELEASE_VERSION##*.}" &&
export NEXT_SNAPSHOT_VERSION="${RELEASE_VERSION%.*}.$((CURRENT_PATCH_VERSION + 1))-SNAPSHOT" &&

if [[ "${RELEASE_VERSION_NUMBER_OVERRIDE}" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]] ; then
  export RELEASE_VERSION="${RELEASE_VERSION_NUMBER_OVERRIDE}"
fi &&

if [[ "${NEXT_VERSION_NUMBER_OVERRIDE}" =~ ^[0-9]+\.[0-9]+\.[0-9]+(-SNAPSHOT)?$ ]] ; then
  export NEXT_SNAPSHOT_VERSION="${NEXT_VERSION_NUMBER_OVERRIDE%-SNAPSHOT}-SNAPSHOT"
fi &&

echo "CURRENT_VERSION=${CURRENT_VERSION}" &&
echo "RELEASE_VERSION=${RELEASE_VERSION}" &&
echo "NEXT_SNAPSHOT_VERSION=${NEXT_SNAPSHOT_VERSION}" &&

echo "Update version for release to ${RELEASE_VERSION} – version was ${CURRENT_VERSION}" &&
mvn versions:set --batch-mode --quiet -DnewVersion="${RELEASE_VERSION}" -DprocessAllModules=true &&

echo "Building, packaging and verifying with Maven" &&
mvn clean verify &&

echo "Committing to local branch" &&
git commit --all --message "Update version for release to ${RELEASE_VERSION} – version was ${CURRENT_VERSION}" &&

echo "Adding Git tag v${RELEASE_VERSION}" &&
git tag "v${RELEASE_VERSION}" &&

echo "Deploying the application" &&
mvn install:install &&

echo "Pushing to GitHub" &&
git push origin --follow-tags &&

echo "Update to next snapshot version ${NEXT_SNAPSHOT_VERSION} – from release version of ${RELEASE_VERSION}" &&
mvn versions:set --batch-mode --quiet -DnewVersion="${NEXT_SNAPSHOT_VERSION}" -DprocessAllModules=true &&

echo "Committing to local branch" &&
git commit --all --message "Update to next snapshot version ${NEXT_SNAPSHOT_VERSION} – from release version of ${RELEASE_VERSION}" &&

echo "Pushing to GitHub" &&
git push origin
