package au.edu.uq.smartass.question;


import au.edu.uq.smartass.engine.QuestionModule;


/**
  * Class TestQuestion
  */
public class TestQuestion implements QuestionModule {

        public String getSection(final String section) {
                return "SECTION: " + section;
        }

}
