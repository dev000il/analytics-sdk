#!/bin/bash

# ----- Constants -----

SCRIPT_NAME="Version Bumper"
SNAPSHOT_SUFFIX="-SNAPSHOT"

# ----- Function definitions ----- 
function joinStrings { local IFS="$1"; shift; echo "$*"; }

function printHelp
{ 
  if [[ -n $1 ]]
    then echo $1; echo;
  fi

  echo "This script will bump the version number, and make it a SNAPSHOT.";
  echo "To produce RELEASE builds, use in combination with 'perform-release.sh'.";
  echo "";
  echo "-> Usage Help:"; 
  echo "     Use '--major' to bump '1.2.4' to '2.0.0-SNAPSHOT'"; 
  echo "     Use '--minor' to bump '1.2.4' to '1.3.0-SNAPSHOT'";
  echo "     Use '--rev'   to bump '1.2.4' to '1.2.5-SNAPSHOT'";
  exit 1;
}

function printError
{
  echo "-> Error occurred:";
  echo "-> $1";
  exit 1;
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
    --help)
    printHelp;;
  esac

  shift # to the next option
done

# Verify that only a single option was selected
if [[ -z $MAJOR ]] && [[ -z $MINOR ]] && [[ -z $REV ]]; then
  printHelp "Required options were not specified";
elif [[ -n $MAJOR ]] && ([[ -n $MINOR ]] || [[ -n $REV ]]) || ([[ -n $MINOR ]] && [[ -n $REV ]]); then
  printError "Multiple bump options specified, not sure what to use!";        
fi

# Check that the version file exists
if [[ ! -f ../version.txt ]]; then
  printError "'version.txt' file was not found";
elif [[ ! -r ../version.txt ]] || [[ ! -w ../version.txt ]]; then
  printError "'version.txt' file should have read and write access";
fi

# ----- Main -----
echo $SCRIPT_NAME

# Note: Assume that the contents of version.txt are correct.
CURRENT_VERSION=`cat ../version.txt`

IFS='. ' read -a array <<< "$CURRENT_VERSION"
if [[ -n $MAJOR ]]; then
  let array[0]=array[0]+1; 
  let array[1]="0"; 
  let array[2]="0";
elif [[ -n $MINOR ]]; then
  let array[1]=array[1]+1; 
  let array[2]="0";
else 
  # Must be REV
  let array[2]=array[2]+1;
fi

# Always create SNAPSHOT versions.
NEW_VERSION=$(joinStrings '.' "${array[@]}")$SNAPSHOT_SUFFIX
echo "-> Updating from version '$CURRENT_VERSION' --> '$NEW_VERSION'"

echo "-> Updating the pom.xml files now"
mvn versions:set -DnewVersion=$NEW_VERSION -DgenerateBackupPoms=false -f ../source/analytics-sdk-parent/pom.xml
RESULT=$?

if [[ $RESULT -eq 0 ]]; then
  echo $NEW_VERSION > ../version.txt
  echo "-> $SCRIPT_NAME Result: Success"
else
  printError "$SCRIPT_NAME Result: Failed"
fi



