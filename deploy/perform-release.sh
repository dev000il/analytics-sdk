#!/bin/bash

# ----- Constants -----

SCRIPT_NAME="Perform Release"
ROOT_POM_PATH="../source/pom.xml"
PARENT_POM_PATH="../source/analytics-sdk-parent/pom.xml"

# ----- Function definitions ----- 
function joinStrings { local IFS="$1"; shift; echo "$*"; }

function printHelp
{ 
  if [[ -n $1 ]]
    then echo $1; echo;
  fi

  echo "This script will help you produce a 'release' of your artifact."
  echo "Remember to update all of your dependencies to 'release' as well, currently it verify for you."
  echo "" 
  echo "Steps taken:";
  echo "   1. Snap your version to a 'release' build.";
  echo "   2. Deploy the artifact to the nexus repository.";
  echo "   3. Update the ../version.txt";
  echo "   4. Create a git commit.";
  echo "   5. Create a git tag.";
  echo "You must check in the git commit and tag by yourself.";
  echo "";
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
    if [[ -n $2 ]]; then
      echo "-> $2";
    fi
    exit 1;
  fi
}

# ----- Validate Parameters -----

# Check if we need to print help
while [[ $# -gt 0 ]]
do
  key="$1"

  case $key in
    --help)
    printHelp;;
  esac

  shift # to the next option
done

# Check that the work directory is clean and has the latest source
STATUS=$(git status)
if [[ $STATUS != *"working directory clean"* || $STATUS == *"have diverged"* || $STATUS == *"Your branch is behind"* ]]; then
  printError "The working directory needs to be clean before '$SCRIPT_NAME'"
fi

# ----- Main -----
echo $SCRIPT_NAME

# Note: Assume that the contents of version.txt are correct.
CURRENT_VERSION=`cat ../version.txt`

# Strip away any suffix
NEW_VERSION=${CURRENT_VERSION%-*}
echo "-> Updating from version '$CURRENT_VERSION' --> '$NEW_VERSION'"

# Check if the version actually changed or not
if [[ "$CURRENT_VERSION" == "$NEW_VERSION" ]]; then
  echo "-> $SCRIPT_NAME Result: No-op, already on version $NEW_VERSION"
  exit 0
fi

echo "-> Updating the pom.xml files"
mvn versions:set -DnewVersion=$NEW_VERSION -DgenerateBackupPoms=false -f $PARENT_POM_PATH
checkForError $?

echo "-> Deploying to the nexus repository"
mvn clean deploy -f $ROOT_POM_PATH -Pprod
checkForError $? "Check that the release isn't already in nexus.office.sao.so"

echo "-> Updating the version.txt file"
echo $NEW_VERSION > ../version.txt
checkForError $?

echo "-> Check changes into scm"
git add ..
git commit -m "[perform-release] release '$NEW_VERSION' @bypass"

TAG=v$NEW_VERSION

echo "-> Creating and pushing an scm tag"
git tag -a -m "[perform-release] creating tag $TAG" $TAG
checkForError $?

# Finish
echo "-> $SCRIPT_NAME Result: Success"
echo "-> Commit your changes: 'git push && git push origin $TAG'"


