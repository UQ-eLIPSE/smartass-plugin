/**

 * This file is part of SmartAss and describes class SimpleDefiniteIntegralModule 
 * that genarates questions with definite integral of  sum of terms a*x^n  
 * with random a, n   
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
 * 
 */
package au.edu.uq.smartass.question.maths;

import au.edu.uq.smartass.auxiliary.RandomChoice;
import au.edu.uq.smartass.maths.Addition;
import au.edu.uq.smartass.maths.Brackets;
import au.edu.uq.smartass.maths.DerivateFunctions;
import au.edu.uq.smartass.maths.IntegerNumber;
import au.edu.uq.smartass.maths.MathsOp;
import au.edu.uq.smartass.maths.Subtraction;

/**
 * Class SimpleDefiniteIntegralModule 
 * genarates questions with definite integral of sum of terms a*x^n     
 * with random a, n   
 */
public class SimpleDefiniteIntegralModule extends DefiniteIntegralModule {
	final int MAX_LIMIT = 4;
	int nlo, nhi;
	/**
	 * @param engine
	 */
	public SimpleDefiniteIntegralModule() {
		super();
		
		for(int i=0;i<MAX_PARTS;i++) {
			int param = RandomChoice.randInt(1, MAX_NUM/2); 
			f[i] = new DerivateFunctions(0, 
					(param+1) * RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign(),
					param,
					vx);
		}
		lo = new IntegerNumber(nlo = RandomChoice.randInt(-MAX_LIMIT, MAX_LIMIT / 2));
		hi = new IntegerNumber(nhi = RandomChoice.randInt(nlo + 1, MAX_LIMIT));
		
		generate();
	}

	protected void generate() {
		super.generate();
		
		MathsOp slo, shi;

		slo = new IntegerNumber((int)(fi[0].cons*Math.pow(nlo, fi[0].param)));
		shi = new IntegerNumber((int)(fi[0].cons*Math.pow(nhi, fi[0].param)));
		int res = (int)(fi[0].cons*Math.pow(nhi, fi[0].param)) - (int)(fi[0].cons*Math.pow(nlo, fi[0].param));
		for(int i=1;i<MAX_PARTS;i++) {
			slo = new Addition(slo, new IntegerNumber((int)(fi[i].cons*Math.pow(nlo, fi[i].param))));
			shi = new Addition(shi, new IntegerNumber((int)(fi[i].cons*Math.pow(nhi, fi[i].param))));
			res = res + (int)(fi[i].cons*Math.pow(nhi, fi[i].param)) -
				(int)(fi[i].cons*Math.pow(nlo, fi[i].param));
		}
		solution.add(new Subtraction(new Brackets(shi), new Brackets(slo)));
		solution.add(answer = new IntegerNumber(res));
	}	
}

