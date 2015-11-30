/** @(#)IndefiniteIntegralSimpleModule.java
 *
 * This file is part of SmartAss and describes class IndefiniteIntegralSimpleModule for 
 * question "Find the antiderivative of the function (ax^k+bx+c)" 
 *   
 * Copyright (C) 2007 Department of Mathematics, The University of Queensland
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
import au.edu.uq.smartass.engine.QuestionModule;
import au.edu.uq.smartass.maths.FractionOp;
import au.edu.uq.smartass.maths.IntegerNumber;
import au.edu.uq.smartass.maths.Ln;
import au.edu.uq.smartass.maths.MathsOp;
import au.edu.uq.smartass.maths.MathsUtils;
import au.edu.uq.smartass.maths.Power;
import au.edu.uq.smartass.maths.SmartFunction;
import au.edu.uq.smartass.maths.UnaryMinus;
import au.edu.uq.smartass.maths.UnprintableMultiplication;
import au.edu.uq.smartass.maths.Variable;

import java.util.TreeSet;
import java.util.Iterator;


/**
 * Class IndefiniteIntegralSimpleModule for 
 * question "Find the antiderivative of
 * the function (ax^k+bx+c)
 * 
 */
public class IndefiniteIntegralSimpleModule implements QuestionModule {
	final int MAX_NUM = 10;
	Variable vx = new Variable("x");//(var_names[RandomChoice.randInt(0,var_names.length-1)]); 
	int[] cc=new int [3];
	int pwr=RandomChoice.randInt(2,6);
	MathsOp solution, question;

	/**
	 * constructor
	 * 
	 * 
	 */
	public IndefiniteIntegralSimpleModule() {
		super();
	  
        int [] pwrs=new int[3];	
        MathsOp[] termsQ=new MathsOp[3];
        MathsOp[] termsS=new MathsOp[3];
        
        cc[0]=RandomChoice.randInt(-MAX_NUM*2, MAX_NUM*2);
		cc[1]=(cc[0]==0)? RandomChoice.randSign()*RandomChoice.randInt(1, MAX_NUM):RandomChoice.randInt(-MAX_NUM, MAX_NUM);
        cc[2]=((cc[1]*cc[0])==0)? RandomChoice.randSign()*RandomChoice.randInt(1, MAX_NUM):RandomChoice.randInt(-MAX_NUM, MAX_NUM);	 
        pwrs[0]=pwr; pwrs[1]=1; pwrs[2]=0;	
        boolean[] presentFuncs={false, false, false , false , false, false}; //0 - x power n, 1 - sin, 2 - cos, 3 - e power x, 4 - ln x, 5 - x^-k 
        for (int i=0; i<3; i++){
        	if ((RandomChoice.randInt(0,9)==0) && !(presentFuncs[3]))
        	{
        		cc[i]=RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign();
        		termsQ[i]=MathsUtils.multiplyVarToConst(cc[i], new Power(new Variable("e"), vx));
        		termsS[i]=termsQ[i];
        		presentFuncs[3]=true;
        	} else if ((RandomChoice.randInt(0,9)==0) && !(presentFuncs[1]))
        	{
        		cc[i]=RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign();
        		termsQ[i]=MathsUtils.multiplyVarToConst(cc[i], new SmartFunction("\\sin", vx));
        		termsS[i]=MathsUtils.multiplyVarToConst(-cc[i], new SmartFunction("\\cos", vx));
        		presentFuncs[1]=true;
        	} else if ((RandomChoice.randInt(0,9)==0) &&!(presentFuncs[2]))
        	{
        		cc[i]=RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign();
        		termsQ[i]=MathsUtils.multiplyVarToConst(cc[i], new SmartFunction("\\cos", vx));
        		termsS[i]=MathsUtils.multiplyVarToConst(cc[i], new SmartFunction("\\sin", vx));
        		presentFuncs[2]=true;
        	} else if ((RandomChoice.randInt(0,9)==0) &&!(presentFuncs[5]))
        	{
        		cc[i]=RandomChoice.randInt(1, 2*MAX_NUM)*RandomChoice.randSign();
        		pwrs[i]=RandomChoice.randInt(-6, -2);
        		if (RandomChoice.randSign()==1){ //write question as a fraction
        		 if (cc[i]<0)
        			termsQ[i]=new UnaryMinus(new FractionOp(new IntegerNumber(-cc[i]), new Power(vx, new IntegerNumber(-pwrs[i]))));
				 else
				 	termsQ[i]=new FractionOp(new IntegerNumber(cc[i]), new Power(vx, new IntegerNumber(-pwrs[i])));        		
        		} else 
        			termsQ[i]=MathsUtils.multiplyConstToPower(cc[i],vx, pwrs[i]);
        		termsS[i]=termAntiDerivative(cc[i], vx, pwrs[i]);
        		presentFuncs[5]=true;
        	} else if ((RandomChoice.randInt(0,9)==0) &&!(presentFuncs[4]))
        	{
        		cc[i]=RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign();
        		pwrs[i]=-1;
        		if (RandomChoice.randSign()==1){ //write question as a fraction
        		 if (cc[i]<0)
        			termsQ[i]=new UnaryMinus(new FractionOp(new IntegerNumber(-cc[i]), vx));
				 else
				 	termsQ[i]=new FractionOp(new IntegerNumber(cc[i]), vx);        		
        		} else 
        			termsQ[i]=MathsUtils.multiplyConstToPower(cc[i],vx, pwrs[i]);
        		termsS[i]=MathsUtils.multiplyVarToConst(cc[i], new Ln(vx));
        		presentFuncs[4]=true;
        	}else {
        		termsQ[i]=MathsUtils.multiplyConstToPower(cc[i],vx, pwrs[i]);
        		termsS[i]=termAntiDerivative(cc[i], vx, pwrs[i]);
        	}
        }    
        question=MathsUtils.addTwoTermsNoZeros(
        	MathsUtils.addTwoTermsNoZeros(termsQ[0],termsQ[1]), termsQ[2]);		   	  	
        solution=MathsUtils.addTwoTermsNoZeros(
        	MathsUtils.addTwoTermsNoZeros(termsS[0],termsS[1]), termsS[2]);	
	} //constructor

		
	/**
     * Composes section with given name
     * "shortanswer", "solution", "question", "var" sections is recognized
     * 
     * @param name	section name
     **/
	public String getSection(String name) {
		if(name.equals("var"))
			return vx.toString();
		if(name.equals("question"))
			return "\\ensuremath{"+ question + "}";
		else if (name.equals("solution"))
			return "\\ensuremath{" + solution + " + C}";
		else if (name.equals("shortanswer")) 
			return "\\ensuremath{" + solution + " + C}";
	    else return "Section " + name + " NOT found!";
	}
	
//create a polynom
protected MathsOp typeSetPolynom (int[] coefs, Variable v1, int[] powers ){
	MathsOp resPol=MathsUtils.multiplyConstToPower(coefs[0],v1, powers[0]);
	for (int i=1; i<powers.length; i++)
		resPol=MathsUtils.addTwoTermsNoZeros(resPol, MathsUtils.multiplyConstToPower(coefs[i],v1,powers[i]));
	return resPol;		
}

//find antiderivative of a kx^n
protected MathsOp termAntiDerivative(int k, Variable v, int n){
	if (n==0)
		return MathsUtils.multiplyVarToConst(k,v);
	else {
		if (k%(n+1)==0)
			return MathsUtils.multiplyConstToPower(k/(n+1), v, n+1); 
		else {
			int hcf=HCFModule.hcf(k, n+1);
			MathsOp temp= new UnprintableMultiplication(new FractionOp(new IntegerNumber(Math.abs(k/hcf)), new IntegerNumber(Math.abs((n+1)/hcf))), 
				new Power(v, new IntegerNumber(n+1)));
			if (k*n>0) return temp;
			else return new UnaryMinus(temp);	
				
		}		
	}			
} 
//simplifies a polynom, for instance makes 3x^2+7+9x^2 into 12x^2+7
//returns int[0] - powers, int [1] -coefs

protected int[][] simplifyPolynom(int[] coefs, int[] powers){
	TreeSet<Integer> setOfPowers=new TreeSet<Integer>();
	for (int i=0; i<powers.length; i++)
		setOfPowers.add(new Integer(powers[i]));
	int[][] res=new int[2][setOfPowers.size()];
	Iterator itr = setOfPowers.iterator();
	int k=res[0].length;
    while(itr.hasNext()){
    	k--;
    	res[0][k]=((Integer)itr.next()).intValue();
    }
    for (int i=0; i<res[0].length; i++)
    	for (int j=0; j<coefs.length; j++)
    		if (powers[j]==res[0][i])
    			res[1][i]+=coefs[j];  
    return res;
  } 	
	
} 
 

