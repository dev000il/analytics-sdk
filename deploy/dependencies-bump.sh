#!/bin/bash

# ----- Constants -----

SCRIPT_NAME="Dependency Version Bumper"
ROOT_POM_PATH="../source/pom.xml"

# ----- Function definitions ----- 
function joinStrings { local IFS="$1"; shift; echo "$*"; }

function printHelp
{ 
  if [[ -n $1 ]]
    then echo $1; echo;
  fi

  echo "This script will bump your dependencies to a new version. Must specify one option.";
  echo "";
  echo "-> Usage Help:";
  echo "     Use '--rev'     to look for new versions: '1.2.4' to '1.2.5-SNAPSHOT'";
  echo "     Use '--minor'   to look for new versions, can include minor: '1.2.4' to '1.3.0-SNAPSHOT'";
  echo "     Use '--major'   to look for new versions, can include major: '1.2.4' to '2.0.0-SNAPSHOT'";
  echo "     Use '--release' to snap to a release build: '1.2.4-SNAPSHOT' to '1.2.4'"; 
  exit 1;
}

function printError
{
  echo "-> Error occurred:";
  echo "-> $1";
  exit 1;
}

function checkForError
{
  if [[ $1 -ne 0 ]]; then
    echo "-> $SCRIPT_NAME Result: Failed with error code '$1'";
    exit 1;
  fi
}

function parseVersionFromPomProperty
{
  PROP_NAME=$1
  POM_FILE_PATH=$2
  PROP_VERSION=$(awk -F"<|>" -v prop="<$PROP_NAME>" '$0 ~ prop {print $3}' $POM_FILE_PATH)
}

function updatePomProperty
{
  # Inputs
  # $1 = Property name
  # $2 = Allow snapshots
  # $3 = Pom file path
  
  # PROP_VERSION is from function 'parseVersionFromPomProperty'
  # MAJOR/MINOR are from the 'main' function
  # Example: 1.2.3
  # 'major' -> [1.2.3,3.0.0-!) | 'minor' -> [1.2.3,1.4.0-!) | 'rev' -> [1.2.3,1.3.0-!) | 'release' -> [1.2.3]
  # Example: 1.2.3-SNAPSHOT
  # 'major' -> [1.2.3-SNAPSHOT,3.0.0-!) | 'minor' -> [1.2.3-SNAPSHOT,1.4.0-!) | 'rev' -> [1.2.3-SNAPSHOT,1.3.0-!) | 'release' -> [1.2.3]
  
  if [[ -n $RELEASE ]]; then
    if [[ ${PROP_VERSION%-*} == $PROP_VERSION ]]; then
      echo "-> Version '$PROP_VERSION' hasn't changed, no-op."
      return 0
    fi
    VERSION_RANGE="[${PROP_VERSION%-*}]"
  else
    IFS='. ' read -a array <<< "$PROP_VERSION"
    if [[ -n $MAJOR ]]; then
      let array[0]=array[0]+2;
      let array[1]="0";
      let array[2]="0";
    elif [[ -n $MINOR ]]; then
      let array[1]=array[1]+2;
      let array[2]="0";
    elif [[ -n $REV ]]; then
      let array[1]=array[1]+1;
      let array[2]="0";
    fi
    
    UPPER_VERSION=$(joinStrings '.' "${array[@]}")
    VERSION_RANGE="[$PROP_VERSION,$UPPER_VERSION-!)" 
  fi

  echo "-> Updating property '$1' version from '$PROP_VERSION' --> '$VERSION_RANGE'"
  mvn versions:update-property -DallowSnapshots=$2 -DgenerateBackupPoms=false -DnewVersion=$VERSION_RANGE -Dproperty=$1 -f $3
}

# ----- Validate Parameters -----

# Check if we have enough parameters or too many
if [[ $# -lt 1 ]]; then
  printHelp "Required options were not specified";
fi

# Parse the options
while [[ $# -gt 0 ]] 
do
  key="$1"

  case $key in
    --major)
    MAJOR=TRUE;;
    --minor)
    MINOR=TRUE;;
    --rev)
    REV=TRUE;;
    --release)
    RELEASE=TRUE;;
    --help)
    printHelp;;
  esac

  shift # to the next option
done

# Verify that only a single option was selected
if [[ -z $MAJOR ]] && [[ -z $MINOR ]] && [[ -z $REV ]] && [[ -z $RELEASE ]]; then
  printHelp "Required options were not specified";
elif [[ -n $MAJOR ]] && ([[ -n $MINOR ]] || [[ -n $REV ]] || [[ -n $RELEASE ]]) \
     || ([[ -n $MINOR ]] && ([[ -n $REV ]] || [[ -n $RELEASE ]])) \
     || ([[ -n $REV ]] && [[ -n $RELEASE ]]); then
  printError "Specify only one option for 'major', 'minor', 'rev', 'release'.";
fi

# ----- Main -----
echo $SCRIPT_NAME

ALLOW_MINOR=false
ALLOW_MAJOR=false
if [[ -n $MAJOR ]]; then
  ALLOW_MAJOR=true
  ALLOW_MINOR=true
elif [[ -n $MINOR ]]; then
  ALLOW_MINOR=true
fi

# This will check the repository for newer updates to so.sao artifacts. 
if [[ -n $RELEASE ]]; then
  echo "-> Updating 'so.sao' dependencies to the release versions";

  mvn versions:use-releases -DgenerateBackupPoms=false -f $ROOT_POM_PATH
  checkForError $?
else 
  echo "-> Updating 'so.sao' dependencies to the latest versions, including snapshots";
 
  mvn versions:use-latest-versions -Dincludes=so.sao:* -DallowSnapshots=true -DallowIncrementalUpdates=true -DallowMinorUpdates=$ALLOW_MINOR -DallowMajorUpdates=$ALLOW_MAJOR -DgenerateBackupPoms=false -f $ROOT_POM_PATH 
  checkForError $?
fi

# Finish
echo "-> $SCRIPT_NAME Result: Success"



