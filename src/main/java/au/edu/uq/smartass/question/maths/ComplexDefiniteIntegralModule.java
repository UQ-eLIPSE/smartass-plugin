/**
 * This file is part of SmartAss and describes class ComplexDefiniteIntegralModule 
 * that genarates questions with definite integral of  sum of terms a*f(x)  
 * where f(x) is one of x^n, e^x, ln x.   
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
 * Class ComplexDefiniteIntegralModule 
 * genarates questions with definite integral of  sum of terms a*f(x)  
 * where f(x) is one of x^n, e^x, 1/x.   
 */
public class ComplexDefiniteIntegralModule extends DefiniteIntegralModule {
	final int MAX_LIMIT = 3;
	int nlo, nhi;
	boolean was_power = false;

	/**
	 */
	public ComplexDefiniteIntegralModule() {
		super();
		
		boolean was_ln = false;
		for(int i=0;i<MAX_PARTS;i++) {
			int param = RandomChoice.randInt(0, MAX_NUM/2); 
			int func = RandomChoice.randInt(-1, 1);
			if(func==1)
				func = 3;  //e^x

			int a = RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign();
			if(func==0 && param!=-1)
				a *= (param+1);
			else if(func==-1)
				param = 1;
			
			was_ln |= func==-1 || func==0 && param==-1;
			was_power |= (func==0);
			f[i] = new DerivateFunctions(func, a, param, vx);
		}
			
		if(was_ln)
			lo = new IntegerNumber(nlo = RandomChoice.randInt(1, MAX_LIMIT / 2));
		else
			lo = new IntegerNumber(nlo = RandomChoice.randInt(-MAX_LIMIT, MAX_LIMIT / 2));
		hi = new IntegerNumber(nhi = RandomChoice.randInt(nlo + 1, MAX_LIMIT));
		
		generate();
	}

	protected void generate() {
		super.generate();
		
		if(was_power) {
			MathsOp slo, shi;
			slo = calc(fi[0], nlo);
			shi = calc(fi[0], nhi);
			for(int i=1;i<MAX_PARTS;i++) {
				slo = new Addition(slo, calc(fi[i], nlo));
				shi = new Addition(shi, calc(fi[i], nhi));
			}
			solution.add(answer = new Subtraction(new Brackets(shi), new Brackets(slo)));
		}
		
		int rpos = 0;
		DerivateFunctions[] rf = new DerivateFunctions[MAX_PARTS*2]; 
		
		for(int i=0;i<MAX_PARTS;i++) {
			int j;
			for(j=0;j<rpos;j++)
				if(rf[j].func==fi[i].func) {
					if(rf[j].func==0)
						rf[j].cons += (int)(fi[i].cons*Math.pow(nhi, fi[i].param));
					else
						rf[j].cons += fi[i].cons;
					break;
				}
			if(j==rpos) {
				if(fi[i].func==0)
					rf[rpos++] = new DerivateFunctions(fi[i].func, (int)(fi[i].cons*Math.pow(nhi, fi[i].param)), 0, hi);
				else
					rf[rpos++] = new DerivateFunctions(fi[i].func, fi[i].cons, hi);
			}
		}

		int last_rpos = rpos;
		for(int i=0;i<MAX_PARTS;i++) {
			int j;
			if(fi[i].func==0) {
				for(j=0;j<rpos;j++)
					if(rf[j].func==fi[i].func) {
						rf[j].cons -= (int)(fi[i].cons*Math.pow(nlo, fi[i].param));
						break;
					}
			} else {
				for(j=last_rpos;j<rpos;j++)
					if(rf[j].func==fi[i].func) {
						rf[j].cons -= fi[i].cons;
						break;
					}
			}
			if(j==rpos) 
				rf[rpos++] = new DerivateFunctions(fi[i].func, -fi[i].cons, lo);
		}
		
		
		answer = rf[0].toMathsOp();
		for(int i=1;i<rpos;i++)
			answer = new Addition(answer, rf[i].toMathsOp());
		
		solution.add(answer);
	}	
	
	protected MathsOp calc(DerivateFunctions f, int n) {
		switch(f.func) {
		case 0:
			return new IntegerNumber((int)(f.cons*Math.pow(n, f.param)));
		default:
			return f.toMathsOp(new IntegerNumber(n));
		}
	}
}

