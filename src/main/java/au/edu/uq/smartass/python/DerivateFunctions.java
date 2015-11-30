/**
 * This file is part of SmartAss and describes class DerivateFunctions - 
 * the utility class for DerivativesModule and other derivatives-related modules
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

package au.edu.uq.smartass.maths;






/**
 * Class DerivateFunctions - 
 * the utility class for DerivativesModule and other derivatives-related modules.
 * The design of this class is not very good - it is better to split each function 
 * into different class. But this was never intended to be more than just a
 * utility for DerivativeModule
 * 
 * First goal of this class - to hold expression a*F(vx,n), 
 * where a - is a constant, b is (an optional) constant parameter of F,
 * F is one of possible functions:
 * 1/(x power n), x power n, sin, cos, e power x, ln x
 *    
 * Second goal is to find the derivative of that expression (in form of 
 * other DerivativeFunctions instance)/
 * Third is to show all steps of solution as array of MathsOp 
 */

public class DerivateFunctions {

	public int cons;        // @TODO: accessor

	/*
	 * Encoding for functions:
	 *	0 - x power n, 1 - sin, 2 - cos, 3 - e power x, 4 - ln x 
	 */
	public int func;        // @TODO: accessor, enum type
	public int param;       // @TODO: accessor, enum type

	MathsOp vx;

	int num_a;

	/**
	 *  
	 */
	public DerivateFunctions(int func, int cons, MathsOp vx) {
		this.func = func;
		this.cons = cons;
		this.vx = (MathsOp) vx.clone();
	}

	public DerivateFunctions(int func, int cons, int param, MathsOp vx) {
		this(func, cons, vx);
		this.param = param;
	}

	public MathsOp toMathsOp() {
		return toMathsOp(0);
	}

	/*
	 * @param qn	if we have to transform question before start a solution,
	 * 				we shell tell toMathsOp a step in this transformation
	 * 
	 * @returns 	MathsOp representetion of DerivativesFunctions
	 */

	public MathsOp toMathsOp(int qn) {
		MathsOp fop;
		switch (func) {
		case 0:
			if(param<0 && qn==0) {
				if(param==-1)
					fop = vx;
				else
					fop = new Power(vx, new IntegerNumber(-param));
				if(cons>=0)
					fop = new FractionOp(new IntegerNumber(cons), fop);
				else
					fop = new UnaryMinus(new FractionOp(new IntegerNumber(-cons), fop));
				return fop;
			} else {
				if(param==0)
					return new IntegerNumber(cons);
				if(param==1)
					fop = vx;
				else
					fop = new Power(vx,	new IntegerNumber(param));
			}
			break;
		case 1:
			fop = new SmartFunction("\\sin", vx);
			break;
		case 2:
			fop = new SmartFunction("\\cos", vx);
			break;
		case 3:
			fop = new Power(new Variable("e"), vx);
			if(vx instanceof FractionOp)
				vx.setLocalTex(new String[]{"\\frac"});
			else if(vx instanceof Addition) {
				if(((Addition)vx).getOp(0) instanceof FractionOp)
					((BinaryOp)vx).getOp(0).setLocalTex(new String[]{"\\frac"});
				else if(((Addition)vx).getOp(0) instanceof UnaryMinus)
					((UnaryMinus)((BinaryOp)vx).getOp(0)).getOp().setLocalTex(new String[]{"\\frac"});
			}
			break;
		default: //ln
			fop = new Ln(vx);
		}
		if(func!=0 || vx instanceof Variable || vx instanceof Function)
			return MathsUtils.multiplyVarToConst(cons, fop);
		else if(cons==1)
			return fop;
		else if(cons==-1)
			return new UnaryMinus(fop);
		else if(fop instanceof Power)
			return new UnprintableMultiplication(new IntegerNumber(cons), fop);
		return new Multiplication(new IntegerNumber(cons), fop);
	}
	
	/**
	 * returns MathsOp represertation of DerivativesFunctions with 
	 * vx replaced by v 
	 * Typically use it to replace variable with its value
	 * (e.g. "x" with, say, "10")  
	 * 
	 * @param v		expression that replaces vx
	 * @return		MathsOp representation
	 */
	public MathsOp toMathsOp(MathsOp v) {
		MathsOp t = vx;
		vx = v;
		v = toMathsOp(0);
		vx = t;
		return v;
	}
	
	public DerivateFunctions derivate() {
		//-1 - 1/(x power n), 0 - x power n, 1 - sin, 2 - cos, 3 - e power x, 4 - ln x 
		switch (func) {
		case 1:
			return new DerivateFunctions(2, cons, vx);
		case 2:
			return new DerivateFunctions(1, - cons, vx);
		case 3:
			return new DerivateFunctions(3, cons, vx);
		case 4:
			return new DerivateFunctions(0, cons, -1,  vx);
		default: // 0:
			return new DerivateFunctions(0, cons*param, param-1, vx);
		}
	}

	public MathsOp[] solve() {
		MathsOp[] s = null;
		//0 - x power n, 1 - sin, 2 - cos, 3 - e power x, 4 - ln x 
		switch (func) {
		case 1:
			s = new MathsOp[] {
					MathsUtils.multiplyVarToConst(cons, new SmartFunction("\\cos", vx)) 
			};
			break;
		case 2:
			if(cons==1)
				s = new MathsOp[] {
					MathsUtils.multiplyVarToConst(-1*cons, new SmartFunction("\\sin", vx))
				};
			else
				s = new MathsOp[] {
						new Multiplication(
								new IntegerNumber(cons),
								new UnaryMinus(new SmartFunction("\\sin", vx))),
						MathsUtils.multiplyVarToConst(-1*cons, new SmartFunction("\\sin", vx)) 
			};
			break;
		case 3:
			s = new MathsOp[] {
					MathsUtils.multiplyVarToConst(cons, new Power(new Variable("e"), vx))
			};
			break;
		case 4:
			if(cons!=1) {
				s = new MathsOp[2];
				s[0] = new Multiplication(new IntegerNumber(cons), new FractionOp(new IntegerNumber(1), vx));
			} else
				s = new MathsOp[1];
			s[s.length-1] = new FractionOp(new IntegerNumber(Math.abs(cons)), vx);
			if(cons<0)
				s[s.length-1] = new UnaryMinus(s[s.length-1]);
			break;
		default: //0
			if(param==0)
				s = new MathsOp[]{new IntegerNumber(0)};
			else {
				if(param<0)
					s = new MathsOp[3];
				else
					s = new MathsOp[2];
				s[0] =	new Multiplication(
							new IntegerNumber(param),
							MathsUtils.multiplyVarToConst(cons, 
									new Power(vx, new Subtraction(
											new IntegerNumber(param), new IntegerNumber(1)))));
				switch(param-1) {
				case 0:
					s[1] = new IntegerNumber((param)*cons);
					break;
				case 1:
					s[1] = MathsUtils.multiplyVarToConst((param)*cons, vx);
					break;
				default:
					s[1] = MathsUtils.multiplyVarToConst((param)*cons, 
							new Power(vx, new IntegerNumber(param-1)));
				}
				if(param-1<0) {
					s[2] = new FractionOp(new IntegerNumber(Math.abs(param*cons)), new Power(vx, new IntegerNumber(-(param-1))));
					if(param*cons<0)
						s[2] = new UnaryMinus(s[2]);
				}
			}
		}
		return s;
	}

	public DerivateFunctions integrate() {
		//-1 - 1/(x power n), 0 - x power n, 1 - sin, 2 - cos, 3 - e power x, 4 - ln x 
		switch (func) {
		case 1:
			return new DerivateFunctions(2, -cons, vx);
		case 2:
			return new DerivateFunctions(1, cons, vx);
		case 3:
			return new DerivateFunctions(3, cons, vx);
		case 4:
			return null; //can't find integral of ln x
		case 0:
			if(param==-1)
				return new DerivateFunctions(4, cons, vx);
			else
				return new DerivateFunctions(0, cons/(param+1), param+1, vx);
		default: //-1
			if(param==1)
				return new DerivateFunctions(4, cons, vx);
			else 
				return new DerivateFunctions(0, -cons/(param-1), -(param-1), vx);
		}
	}
	
	public int getQuestionStepsNum() {
		if(func==0 && param<0)  
			return 2;
		else
			return 1;
	}


	public int getAnswerStepsNum() {
		return num_a;
	}
}

