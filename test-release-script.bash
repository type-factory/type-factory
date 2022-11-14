#!/bin/bash

mvn clean verify &&

CURRENT_VERSION="$(mvn --batch-mode --quiet -Dexpression=project.version -DforceStdout help:evaluate)" &&
RELEASE_VERSION="${CURRENT_VERSION%-SNAPSHOT}" &&
CURRENT_PATCH_VERSION="${RELEASE_VERSION##*.}" &&
NEXT_SNAPSHOT_VERSION="${RELEASE_VERSION%.*}.$((CURRENT_PATCH_VERSION + 1))-SNAPSHOT" &&

if [ -z "${RELEASE_VERSION_NUMBER_OVERRIDE}" ] ; then
  RELEASE_VERSION="${RELEASE_VERSION_NUMBER_OVERRIDE%-SNAPSHOT}"
fi &&

if [ -z "${NEXT_VERSION_NUMBER_OVERRIDE}" ] ; then
  NEXT_SNAPSHOT_VERSION="${NEXT_VERSION_NUMBER_OVERRIDE%-SNAPSHOT}-SNAPSHOT"
fi &&

mvn --batch-mode --quiet versions:set -DnewVersion="${RELEASE_VERSION}" &&

git commit --message "Update version for release to ${RELEASE_VERSION} – version was ${CURRENT_VERSION}" &&

git tag "v${RELEASE_VERSION}" &&

mvn install:install &&

git push origin --follow-tags &&

mvn --batch-mode --quiet versions:set -DnewVersion="${NEXT_SNAPSHOT_VERSION}" &&

git commit --message "Update to next snapshot version ${NEXT_SNAPSHOT_VERSION} – previous version was ${CURRENT_VERSION}" &&

git push origin
