/**
 *
 */
 
package au.edu.uq.smartass.question.maths;

import au.edu.uq.smartass.engine.QuestionModule;
 
public class HelloWorld implements QuestionModule {
	public HelloWorld() 
	{
	}
	public String printQuestion()
	{
		return "Hello, world?";
	};
	public String printSolution()
	{
		return "Hello, world!";
	};
	//public void generate(String param) {};

    @Override
    public String getSection(String section) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
