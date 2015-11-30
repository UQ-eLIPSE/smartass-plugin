/**
 * @(#)FractionEquationModule.java
 *
 *
 * @author 
 * @version 1.00 2007/1/21
 */

package au.edu.uq.smartass.question.maths;

import au.edu.uq.smartass.engine.QuestionModule;
import au.edu.uq.smartass.maths.IntegerNumber;
import au.edu.uq.smartass.maths.MathsOp;
import au.edu.uq.smartass.maths.UnaryMinus;
import au.edu.uq.smartass.maths.UnprintableMultiplication;
import au.edu.uq.smartass.maths.Variable;

import java.util.*;


/**
 * class FractionEquationModule is the base class for FractionEquation1Module and FractionEquation2Module classes
 */
public class FractionEquationModule implements QuestionModule {
	final static String[] varss = {"x","y","z"};
	ArrayList<MathsOp> sol = new ArrayList<MathsOp>();
	Variable vx;
	MathsOp question, answer;

    public FractionEquationModule() {

   }
    
	MathsOp composeVarOp(int num0, MathsOp vx) {
		if(num0==1) 
			return vx;
		else if(num0==-1)
			return new UnaryMinus(vx);
		else
			return new UnprintableMultiplication(new IntegerNumber(num0), vx);
	}
    
	public String getSection(String name) {
		if(name.equals("question"))
			return "\\ensuremath{" + question.toString() + "}";
		else if (name.equals("solution"))
			return composeSolution(sol);
		else if (name.equals("shortanswer"))
			return "\\ensuremath{" + answer.toString() + "}";
		else if (name.equals("varname"))
			return "\\ensuremath{" + vx.toString() + "}";
		return "Section " + name + " NOT found!";
	}
    
	private String composeSolution(ArrayList<MathsOp> solution) {
		String res = "\\ensuremath{" + sol.get(0).toString() + "}";
		for(int i=1;i<sol.size()-1;i++)
			res = res + ", so \\ensuremath{" + sol.get(i).toString() + "}";
		return res;
	}
}
