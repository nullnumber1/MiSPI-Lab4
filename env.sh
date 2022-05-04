#! /bin/bash
PROPERTY_FILE=build.properties
ARTIFACT_NAME=MiSPILab3.war

function getProperty {
   PROP_KEY=$1
   PROP_VALUE=`cat $PROPERTY_FILE | grep "$PROP_KEY" | cut -d'=' -f2`
   echo $PROP_VALUE
}

export JAVA_OPTS=$(getProperty "vm.args")
export JAVA=$(getProperty "java.home")
cp $(getProperty "build.dir")/$ARTIFACT_NAME $(getProperty "wildfly.deployment")
export $(grep -v '^#' application.env | xargs)
$(getProperty "wildfly.launch")