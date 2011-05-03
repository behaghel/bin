#! /bin/sh

export JAVA_OPTS="-Xmx512M" 

while getopts ndr opt
do
  case "$opt" in
    # Remove colors from sbt output. 
    # When working on a terminal that does not support them.
    n) SBTOPTS="$SBTOPTS -Dsbt.log.noformat=true"; shift;;
    # Activating Debug
    d) export JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=1773,server=y,suspend=n"; shift;;
    # Activating JRebel.
    r) export JAVA_OPTS="$JAVA_OPTS -noverify -javaagent:$JREBEL_HOME/jrebel.jar"; shift;;
    # long sbt session should start with -l
    l) export JAVA_OPTS="$JAVA_OPTS -XX:+CMSClassUnloadingEnabled"; shift;;
  esac
done

#echo "opts" $SBTOPTS
#echo "param" $@
java $JAVA_OPTS $SBTOPTS -jar `dirname $0`/sbt-launch.jar "$@"