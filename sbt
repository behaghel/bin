#! /bin/sh

export MAXPERMSIZE="256m"
export JAVA_OPTS="-Dfile.encoding=UTF8 -Xss1M -Xmx512M -server" 
while getopts ndrl opt
do
  case "$opt" in
    # Remove colors from sbt output. 
    # When working on a terminal that does not support them.
    n) SBTOPTS="$SBTOPTS -Dsbt.log.noformat=true"; shift;;
    # Activating Debug
    d) export JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=1773,server=y,suspend=n"; shift;;
    # Activating JRebel.
    r) export JAVA_OPTS="$JAVA_OPTS -noverify -javaagent:$JREBEL_HOME/jrebel.jar"; export MAXPERMSIZE="512m"; shift;;
    # long sbt session should start with -l
    l) export JAVA_OPTS="$JAVA_OPTS -XX:+CMSClassUnloadingEnabled"; shift;;
  esac
done

export JAVA_OPTS="$JAVA_OPTS -XX:MaxPermSize=$MAXPERMSIZE"
#echo "opts" $SBTOPTS
#echo "param" $@
java $JAVA_OPTS $SBTOPTS -jar `dirname $0`/sbt-launch.jar "$@"
