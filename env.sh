#! /bin/zsh
PROPERTY_FILE=build.properties

function getProperty {
   PROP_KEY=$1
   PROP_VALUE=`cat $PROPERTY_FILE | grep "$PROP_KEY" | cut -d'=' -f2`
   echo $PROP_VALUE
}

export JAVA_OPTS=$(getProperty "vm.args")
export JAVA=$(getProperty "java.home")
cp $(getProperty "artifact.dir")/MiSPILab3.war $(getProperty "wildfly.deployment")
export $(grep -v '^#' application.env | xargs)
$(getProperty "wildfly.launch")