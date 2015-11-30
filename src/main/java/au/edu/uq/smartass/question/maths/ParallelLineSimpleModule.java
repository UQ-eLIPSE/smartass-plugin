/* @(#) ParallelLineSimpleModule.java
 *
 * This file is part of SmartAss and describes class ParallelLineModule.
 * Find the equation of the line parallel to ax+by+c=0 and passing through the point (x1,y1).
 * 3 terms only allowed in the original equation.
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
import au.edu.uq.smartass.engine.QuestionModule;
import au.edu.uq.smartass.maths.Addition;
import au.edu.uq.smartass.maths.Equality;
import au.edu.uq.smartass.maths.FractionOp;
import au.edu.uq.smartass.maths.IntegerNumber;
import au.edu.uq.smartass.maths.MathsOp;
import au.edu.uq.smartass.maths.MathsUtils;
import au.edu.uq.smartass.maths.Multiplication;
import au.edu.uq.smartass.maths.Subtraction;
import au.edu.uq.smartass.maths.UnaryMinus;
import au.edu.uq.smartass.maths.UnprintableMultiplication;
import au.edu.uq.smartass.maths.Variable;

import java.util.*;

/**
* Class ParallelLineSimpleModule describes the question:  
* Find the equation of the line parallel to the ax+by=c.
* All generated numbers are integers.   
* Unlike ParallelLineModule, ParallelLineSimpleModule
* allowes 3 non-zero terms only in the original equation.
*
* @version 1.0 25.05.2007
*/
public class ParallelLineSimpleModule implements QuestionModule{
 private final int MAX_ELEMENT=10;
 int m,c,x1,y1;
 Vector <MathsOp> workings;
 MathsOp equation;
 String findingc;
 
/**
* Constructor ParallelLineSimpleModule initialises the question.
* 
*/
 public ParallelLineSimpleModule() 
	{
				String[] v={"nothing"};
				this.generate(v);			
	} //constructor
	
/**
* Constructor ParallelLineSimpleModule initialises the question.
*
* @param      params  if params[0]=="horizontal" the module will generate 
* the horizontal lines 
* if params[0]=="vertical" the module will generate the vertical lines.
* In case of "vertical" use only sections x1, y1, findc , originalequation, 
* and equation.
*/
 public ParallelLineSimpleModule(String[] params) 
	{
				this.generate(params);			
	} //constructor	
	
/**
 * getSection method typesets a question and solution 
 * @return a String containing Latex code for the section
 */
 public String getSection(String name) {
 
		if(name.equals("m")) 		
			return Integer.toString(m);											 	
 		if(name.equals("x1")) 
			return Integer.toString(x1);											 												
 		if(name.equals("y1")) 		
			return Integer.toString(y1);		
 		if(name.equals("originalequation")) {		
			return "\\ensuremath{ "+workings.get(0)+" }";													
 		} 
 		if (name.equals("newequation")){
 			return "\\ensuremath{ "+(new Equality(new Variable("y"),MathsUtils.addTwoTermsNoZeros( 
 				MathsUtils.multiplyVarToConst(m,new Variable("x")), new Variable("c")))).toString()+" }"; 
 		}	  	
 		if(name.equals("rewriteoriginalequation")) {
 			String solution="\\begin{align*}"+workings.get(0).toString().replace("=","&=");
 			if (workings.size()>1) 
 			{solution+=", \\mbox{  so} \\\\ \n";	
			for (int i=1; i<workings.size()-1; i++)
				solution+=workings.get(i).toString().replace("=","&=")+"\\\\";
			solution+=workings.get(workings.size()-1).toString().replace("=","&=");	
 			}	
			solution+="\\end{align*}"; //closing ensuremath				
			return solution;							
 		} 	
 		if (name.equals("findc")){
 			return findingc;
 		}	
	    if (name.equals("equation")){
 			return "\\ensuremath{ "+equation.toString()+" }";
 		}	
	    
		return "Section " + name + " NOT found!";
	}
	
	
/**
 * Method generates coefficient for the equation of a straight line:
 * y=mx+c, m - gradient, c - y-intercept
 * and coordinates for the point (x1,y1).
 *
 * Then this method also generates Latex-code for solution.
 *
 * @param      String[] horizontal  if horizontal[0]=="horizontal" the module 
 * will generate  the horizontal lines 
 * if horizontal[0]=="vertical" the module will generate the vertical lines
 */
 
 protected void generate(String[] horizontal) {
 	          	workings =new Vector <MathsOp> ();
 				int[] coefs=new int[6];
 				int[] places=new int[3];
 				int  hcf1, num, denom;
 				MathsOp [] solSteps=new MathsOp[6];  
 				MathsOp tempOp, mop, cop;	
 			    Variable y=new Variable("y");
 			    Variable x=new Variable("x");
 			    boolean simple=false;
 			    
 				//forming coef0y+coef1x+coef2=coef3y+coef4x+coef5;
 				if  ( (horizontal.length>0) && horizontal[0].equals("vertical") ){
 					m=-1000;
 			    	coefs[0]=0;
 			    	coefs[3]=0;
 			    if (RandomChoice.randSign()==-1){
 	            	coefs[1]=RandomChoice.randInt(1,MAX_ELEMENT)*RandomChoice.randSign();
 	            	coefs[4]=0;
 	            } else {
 	            	coefs[4]=RandomChoice.randInt(1,MAX_ELEMENT)*RandomChoice.randSign();
 	            	coefs[1]=0;
 	            }
 	            if (RandomChoice.randSign()==-1){
 	            	coefs[2]=RandomChoice.randInt(-MAX_ELEMENT,MAX_ELEMENT);
 	            	coefs[5]=0;
 	            } else {
 	            	coefs[5]=RandomChoice.randInt(-MAX_ELEMENT,MAX_ELEMENT);
 	            	coefs[2]=0;
 	            }
 	            solSteps[0]=MathsUtils.multiplyVarToConst(coefs[0],y);
 	            solSteps[1]=MathsUtils.multiplyVarToConst(coefs[1],x);
 	            solSteps[2]=new IntegerNumber(coefs[2]);
 	            solSteps[3]=MathsUtils.multiplyVarToConst(coefs[3],y);
 	            solSteps[4]=MathsUtils.multiplyVarToConst(coefs[4],x);
 	            solSteps[5]=new IntegerNumber(coefs[5]);
 	            places=RandomChoice.randPerm(3);
 	            tempOp=MathsUtils.addTwoTermsNoZeros(
 	            	       	MathsUtils.addTwoTermsNoZeros(solSteps[places[0]], solSteps[places[1]]),
 	            	       	solSteps[places[2]]);
 	            places=RandomChoice.randPerm(3);
 	            tempOp=new Equality(tempOp,
 	               MathsUtils.addTwoTermsNoZeros(
 	            	       	MathsUtils.addTwoTermsNoZeros(solSteps[3+places[0]], solSteps[3+places[1]]),
 	            	       	solSteps[3+places[2]]));
 	            workings.add(tempOp);
 	            x1=RandomChoice.randInt(-MAX_ELEMENT, MAX_ELEMENT);
                y1=RandomChoice.randInt(-MAX_ELEMENT, MAX_ELEMENT);
                equation=new Equality(x,new IntegerNumber(x1)); 	
                findingc="The original line has an infinite gradient; it is vertical and parallel to the \\ensuremath{y}-axis.  "+
                	"Therefore the new line is vertical and has the form \\ensuremath{x=c}, "+
                		 "where c is a constant.\\\\ \n "+
                "The point \\ensuremath{("+x1+","+y1+")} lies on the new line, so the equation of the "+
                		"new line is \\hspace{2mm} \\ensuremath{"+equation.toString()+"}.";             	
 			     return;	
 			    } 	
 	           
 	            if (RandomChoice.randSign()==-1) {
 	            	coefs[3]=RandomChoice.randInt(1,MAX_ELEMENT)*RandomChoice.randSign();
 	            	coefs[0]=0;
 	            } else {
 	            	coefs[0]=RandomChoice.randInt(1,MAX_ELEMENT)*RandomChoice.randSign();
 	            	coefs[3]=0;
 	            }
 	            if ((horizontal.length>0) && horizontal[0].equals("horizontal"))
 	            	m=0;
 	            else 	
 	            	m=RandomChoice.randInt(-MAX_ELEMENT/2, MAX_ELEMENT/2);	
 	            if (RandomChoice.randSign()==-1){
 	            	coefs[1]=-(coefs[0]-coefs[3])*m;
 	            	coefs[4]=0;
 	            } else {
 	            	coefs[4]=(coefs[0]-coefs[3])*m;
 	            	coefs[1]=0;
 	            }
 	            if (RandomChoice.randSign()==1){
 	            	coefs[2]=(coefs[0]-coefs[3])*RandomChoice.randInt(-MAX_ELEMENT, MAX_ELEMENT);
 	            	coefs[5]=0;
 	            } else {
 	            	coefs[5]=(coefs[0]-coefs[3])*RandomChoice.randInt(-MAX_ELEMENT, MAX_ELEMENT);
 	            	coefs[2]=0;
 	            }

                c=RandomChoice.randInt(-MAX_ELEMENT, MAX_ELEMENT);
                if (c==((coefs[5]-coefs[2])/(coefs[0]-coefs[3])))
                	c++;
                x1=RandomChoice.randInt(-MAX_ELEMENT, MAX_ELEMENT);
                y1=m*x1+c;
               
                   	            
 	            solSteps[0]=MathsUtils.multiplyVarToConst(coefs[0],y);
 	            solSteps[1]=MathsUtils.multiplyVarToConst(coefs[1],x);
 	            solSteps[2]=new IntegerNumber(coefs[2]);
 	            solSteps[3]=MathsUtils.multiplyVarToConst(coefs[3],y);
 	            solSteps[4]=MathsUtils.multiplyVarToConst(coefs[4],x);
 	            solSteps[5]=new IntegerNumber(coefs[5]);
 	            
 	            places=RandomChoice.randPerm(3);
 	            tempOp=MathsUtils.addTwoTermsNoZeros(
 	            	       	MathsUtils.addTwoTermsNoZeros(solSteps[places[0]], solSteps[places[1]]),
 	            	       	solSteps[places[2]]);
 	            places=RandomChoice.randPerm(3);
 	            tempOp=new Equality(tempOp,
 	               MathsUtils.addTwoTermsNoZeros(
 	            	       	MathsUtils.addTwoTermsNoZeros(solSteps[3+places[0]], solSteps[3+places[1]]),
 	            	       	solSteps[3+places[2]]));
 	            	       	
 	          
 	            workings.add(tempOp);

//REWRITING ORIGINAL EQUATION 	            
 	            // do we need to take terms from one part of equation to another? 
 	            
 	            if ((coefs[3]!=0) || (coefs[1]!=0) || (coefs[2]!=0) ) {
                    solSteps[3]=MathsUtils.multiplyVarToConst(-coefs[3], y);
                    solSteps[1]=MathsUtils.multiplyVarToConst(-coefs[1], x);
                    solSteps[2]=new IntegerNumber(-coefs[2]);	            
 	            	tempOp=new Equality(MathsUtils.addTwoTermsNoZeros(solSteps[0], solSteps[3]),
 	            	 MathsUtils.addTwoTermsNoZeros(
 	            	 	 MathsUtils.addTwoTermsNoZeros(MathsUtils.addTwoTermsNoZeros(solSteps[4], solSteps[1]), solSteps[5]), solSteps[2]));      	
 	            	 	 	
 	            	workings.add(tempOp);
 	            	
 	            // 3 coefficients are remaining, we are going to write equation in the form: newcoef0y=newcoef4x+newcoef5;
 	           		if (((coefs[0]*coefs[3])==0) && ((coefs[1]*coefs[4])==0) && ((coefs[2]*coefs[5])==0) )
 	             		simple=true; 
 	             	
 	            	coefs[0]-=coefs[3];
 	            	coefs[4]-=coefs[1];
 	            	coefs[5]-=coefs[2];
 	            	solSteps[0]=MathsUtils.multiplyVarToConst(coefs[0],y);
 	            	solSteps[1]=MathsUtils.multiplyVarToConst(coefs[4], x);
 	            	solSteps[2]=new IntegerNumber(coefs[5]);
 	            	
 	            	tempOp=new Equality(solSteps[0], MathsUtils.addTwoTermsNoZeros(solSteps[1], solSteps[2]));
 	            	if (!simple)  
 	            		workings.add(tempOp);
 	            		
 	            }  	
 	            	            
 	            if (coefs[0]==1)
 	            {
 	                mop=new IntegerNumber(coefs[4]);
 	                cop=new IntegerNumber(coefs[5]);
 	                
 	            } else {
 	            
 	            if (coefs[0]==-1){
 	              
 	            
 	            	solSteps[1]=MathsUtils.multiplyVarToConst(-coefs[4], x);
 	            	solSteps[2]=new IntegerNumber(-coefs[5]);
 	            	tempOp=new Equality(y, MathsUtils.addTwoTermsNoZeros(solSteps[1], solSteps[2]));
 	            	
 	            	workings.add(tempOp);	
 	            	
 	            	mop=new IntegerNumber(-coefs[4]);
 	            	cop=new IntegerNumber(-coefs[5]);	
 	            } else {
 	            	if ((coefs[4]%coefs[0])==0){
 	            		coefs[4]/=coefs[0];
 	            		mop=new IntegerNumber(coefs[4]);
 	            		solSteps[1]=MathsUtils.multiplyVarToConst(coefs[4],x);
 	            	} else {    
							hcf1=HCFModule.hcf(coefs[4],coefs[0]);			
	  				  	    num=coefs[4]/hcf1;
	  				  	    denom=coefs[0]/hcf1;
	  				  	    mop=new FractionOp(new IntegerNumber(Math.abs(num)), new IntegerNumber(Math.abs(denom)));
	  				  	    if ((num*denom)<0)
	  				  	    	mop=new UnaryMinus(mop);  	
	  				  	    solSteps[1]=new UnprintableMultiplication(mop,x);		
 	            	}
 	            	if ((coefs[5]%coefs[0])==0){
 	            		coefs[5]/=coefs[0];
 	            		cop=new IntegerNumber(coefs[5]);
 	            	} else {    
							hcf1=HCFModule.hcf(coefs[5],coefs[0]);			
	  				  	    num=coefs[5]/hcf1;
	  				  	    denom=coefs[0]/hcf1;
	  				  	    cop=new FractionOp(new IntegerNumber(Math.abs(num)), new IntegerNumber(Math.abs(denom)));
	  				  	    if ((num*denom)<0)
	  				  	    	cop=new UnaryMinus(cop);  	
	  				  	    	
 	            	}
 	            
 	            	solSteps[2]=cop;
 	            	tempOp=new Equality(y, MathsUtils.addTwoTermsNoZeros(solSteps[1], solSteps[2]));
 	            	workings.add(tempOp);
 	            }
 		} 
//FINDING c 
     findingc="\\ensuremath{ ";           	            	
 	 if (m==0) {
 	 	findingc+=(new Equality(new IntegerNumber(y1),new Variable("c"))).toString() +"}.  \n";
 	 }	else{	
 	 tempOp=new Equality(new IntegerNumber(y1), new Addition(new Multiplication(new IntegerNumber(m), new IntegerNumber(x1)), new Variable("c")));
 	 findingc+=tempOp.toString()+"\\mbox{, so   }";
 	 if (x1==0){
 	 	findingc+=(new Equality(new IntegerNumber(y1),new Variable("c"))).toString() +"}.  \n";
 	 } else{
 	 
 	
 	 tempOp=new Equality(new IntegerNumber(y1), new Addition(new IntegerNumber(m*x1), new Variable("c")));
 	 findingc+=tempOp.toString()+"\\mbox{. Hence }";
     
 	 tempOp=new Equality (new Equality(new Variable("c"), new Subtraction(new IntegerNumber(y1),new IntegerNumber(m*x1))),
 	 new IntegerNumber(c));
 	 findingc+=tempOp.toString()+"}.  \n ";
 	 }
 	 }
 	 equation=new Equality(new Variable("y"),MathsUtils.addTwoTermsNoZeros(MathsUtils.multiplyVarToConst(m, new Variable("x")),
 	 new IntegerNumber(c)));       	
 	                                           	   		 
	}//generate
 } 
