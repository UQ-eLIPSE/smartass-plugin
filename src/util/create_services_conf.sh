#!/bin/bash

set -o errexit
set -o nounset

#+
#
#-

echo $0

#-------------------------------------------------------------------------------
#       . main .
#-------------------------------------------------------------------------------

        printf '\n> > > > >  SERVICES: au.edu.uq.smartass.engine.QuestionModule\n\n'

        find ./build/classes/main//au/edu/uq/smartass/question -type f -name *.class | 
        sed -e '{
                /\$/d
                /Abstract/d
                s|.*//||
                s|\.class||
                s|/|.|g
        }' | 
        tee ./src/main/resources/META-INF/services/au.edu.uq.smartass.engine.QuestionModule

        printf '\n< < < < <  SERVICES: au.edu.uq.smartass.engine.QuestionModule\n\n'

#-------------------------------------------------------------------------------
#       . la fin .
#-------------------------------------------------------------------------------

exit 0;

