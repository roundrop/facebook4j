#!/bin/sh

OLD_VERSION=$1
NEW_VERSION=$2

sed -i "" s/${OLD_VERSION}/${NEW_VERSION}/g readme.md
sed -i "" s/${OLD_VERSION}/${NEW_VERSION}/g facebook4j-core/src/main/java/facebook4j/Version.java
