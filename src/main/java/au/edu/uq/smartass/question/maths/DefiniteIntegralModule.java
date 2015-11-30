/**
 * 
 * This file is part of SmartAss and describes class DefiniteIntegralModule.  
 * This class contains functionality that is common for 
 * SimpleIndefiniteIntegralModule and ComplexIndefiniteIntegralModule
 *   
 * Copyright (C) 2006 Department of Mathematics, The University of Queensland
 * SmartAss is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2, or
 * (at your option) any later version.
 * GNU program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with program;
 * see the file COPYING. If not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 */
package au.edu.uq.smartass.question.maths;

import au.edu.uq.smartass.engine.QuestionModule; 
import au.edu.uq.smartass.maths.Addition;
import au.edu.uq.smartass.maths.Brackets;
import au.edu.uq.smartass.maths.DeltaAntiderivative;
import au.edu.uq.smartass.maths.DefiniteIntegral;
import au.edu.uq.smartass.maths.DerivateFunctions;
import au.edu.uq.smartass.maths.MathsOp;
import au.edu.uq.smartass.maths.MathsUtils;
import au.edu.uq.smartass.maths.Subtraction;
import au.edu.uq.smartass.maths.Variable;

import java.util.Vector;

/**
 * Class IndefiniteIntegralModule.  
 * This class contains functionality that is common for  
 * 
 */
public class DefiniteIntegralModule implements QuestionModule {

	final int MAX_PARTS = 3;
	final int MAX_NUM = 8;
	Variable vx = new Variable("x");
	MathsOp question, answer, lo, hi;
	Vector<MathsOp> solution = new Vector<MathsOp>();
	DerivateFunctions[] f = new DerivateFunctions[MAX_PARTS];
	DerivateFunctions[] fi = new DerivateFunctions[MAX_PARTS];
	

	/**
	 */
	public DefiniteIntegralModule() {
		super();
	}

	protected void generate() {
		for(int i=0;i<MAX_PARTS;i++) 
			fi[i] = f[i].integrate();
		question = f[0].toMathsOp();
		answer = fi[0].toMathsOp();
		for(int i=1;i<MAX_PARTS;i++) {
			question = new Addition(question, f[i].toMathsOp());
			answer = new Addition(answer, fi[i].toMathsOp());
		}
		question = new DefiniteIntegral(hi, lo, question, vx);
		//answer = ...
		solution.add(new DeltaAntiderivative(lo, hi, answer));

		MathsOp slo, shi;
		slo = fi[0].toMathsOp(lo);
		shi = fi[0].toMathsOp(hi);
		for(int i=1;i<MAX_PARTS;i++) {
			slo = new Addition(slo, fi[i].toMathsOp(lo));
			shi = new Addition(shi, fi[i].toMathsOp(hi));
		}
		solution.add(answer = new Subtraction(new Brackets(shi), new Brackets(slo)));
	}

	/**
     * Composes section with given name
     * "question", "solution" and "shortanswer" sections is recognized
     * 
     * @param name	section name
     **/
	public String getSection(String name) {
		if(name.equals("question"))
			return "\\ensuremath{" + question.toString() + "}";
		else if(name.equals("solution"))
			return "\\ensuremath{" + question.toString() + 
					" = " + MathsUtils.composeSolution(solution).toString() + "}";
		else if(name.equals("shortanswer"))
			return "\\ensuremath{" + question.toString()+ " = " + 
				answer.toString() + "}";
		else
                        return "Section "+name+" not found!";
	}
}

