/* @(#)OperationsOnTwoSetsModule.java
 *
 * This file is part of SmartAss and describes class OperationsOnTwoSetsModule for 
 * finding an intersection, union and set differences of two given sets. 
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
import au.edu.uq.smartass.auxiliary.SmartGraph;
import au.edu.uq.smartass.engine.QuestionModule;
import au.edu.uq.smartass.maths.Intersection;
import au.edu.uq.smartass.maths.MathsSet;
import au.edu.uq.smartass.maths.SetDifference;
import au.edu.uq.smartass.maths.Union;

import java.util.*;

/**
* Class OperationsOnTwoSetsModule describes the question on 
* sets intersection, union and set differences.   
*
* @version 1.0 26.01.2007
*/
public class OperationsOnTwoSetsModule implements QuestionModule{
 private final int MIN_ELEMENT=-3;
 private final int MAX_ELEMENT=9;
 private final int MAX_SET_SIZE=10;
 private final int [] shares={2,3,3,2}; //proportions for typesetting a set into Venn diagram      
 MathsSet set1, set2;
 Intersection set1IntersectSet2;
 Union set1UnionSet2;
 SetDifference set1MinusSet2, set2MinusSet1;
 private String[][] strsForVenn; //strings for typesetting values into Venn diagram 
 
/**
* Constructor OperationsOnTwoSetsModule initialises the question.
* 
*/
 public OperationsOnTwoSetsModule() 
	{
				this.generate();
	              		
	} //constructor
/**
* Constructor OperationsOnTwoSetsModule initialises the question with parameters.
* @param      params  Sets to intersect in format {el, el, el ... el}{el, el, el ... el}
*					quotation marks are not allowed
* 
*/
 public OperationsOnTwoSetsModule(String[] params) 
	{
				this.generate(params);
	} //constructor

	
/**
 * getSection method typesets a question and solution 
 * @return a String containing Latex code for the section
 */
 public String getSection(String name) {
 	String addition;
		if(name.equals("name1")) {		
			return "\\ensuremath{"+set1.getName()+" }";									
 		}	 	
 		if(name.equals("name2")) {		
			return "\\ensuremath{"+set2.getName()+" }";									
 		}
 		if(name.equals("set1")) {		
			return "\\ensuremath{"+set1.toString()+" }";									
 		}	 	
 		if(name.equals("set2")) {		
			return "\\ensuremath{"+set2.toString()+" }";									
 		}	 				
 		if(name.equals("solutionintersection")) {       
 		    String solution= "\\ensuremath{"+set1IntersectSet2.getName()+" = ";
 		    solution+=set1IntersectSet2.toString();          //set1.toString()+" \\bigcap "+set2.toString()+" = " 
 		    solution+=" = "+(new MathsSet(set1IntersectSet2.calculate())).toString();
 		    return solution+"}";
 		}    
 		if(name.equals("solutionunion")) {       
 		    String solution= "\\ensuremath{"+set1UnionSet2.getName()+" = ";
 		    solution+=set1UnionSet2.toString();      
 		    solution+=" = "+(new MathsSet(set1UnionSet2.calculate())).toString();
 		    return solution+"}";
 		}    			    				
 		if(name.equals("solutionset1minusset2")) {       
 		    String solution= "\\ensuremath{"+set1MinusSet2.getName()+" = ";
 		    solution+=set1MinusSet2.toString();        
 		    solution+=" = "+(new MathsSet(set1MinusSet2.calculate())).toString();
 		    return solution+"}";
 		}    			
 		if(name.equals("solutionset2minusset1")) {       
 		    String solution= "\\ensuremath{"+set2MinusSet1.getName()+" = ";
 		    solution+=set2MinusSet1.toString();         
 		    solution+=" = "+(new MathsSet(set2MinusSet1.calculate())).toString();
 		    return solution+"}";
 		}    		
 		if(name.equals("shortanswerintersection")) {	
			return "\\ensuremath{"+(new MathsSet(set1IntersectSet2.calculate())).toString()+"}";												
 		}
 		if(name.equals("shortanswerunion")) {	
			return "\\ensuremath{"+(new MathsSet(set1UnionSet2.calculate())).toString()+"}";												
 		}
 		if(name.equals("shortanswerset1minusset2")) {	
			return "\\ensuremath{"+(new MathsSet(set1MinusSet2.calculate())).toString()+"}";												
 		}	
 		if(name.equals("shortanswerset2minusset1")) {	
			return "\\ensuremath{"+(new MathsSet(set2MinusSet1.calculate())).toString()+"}";												
 		}						
//OUTPUT FOR TYPESETTING VENN DIAGRAMS:			
 		if (name.equals("set1minusset2line0")) {
 			return strsForVenn[0][0];
 		}
 		if (name.equals("set1minusset2line1")) {
 			return strsForVenn[0][1];
 		}
 		if (name.equals("set1minusset2line2")) {
 			return strsForVenn[0][2];
 		}	
 		if (name.equals("set1minusset2line3")) {
 			return strsForVenn[0][3];
 		}				
 		if (name.equals("set1intersectset2line0")) {
 			return strsForVenn[1][0];
 		}
 		if (name.equals("set1intersectset2line1")) {
 			return strsForVenn[1][1];
 		}
 		if (name.equals("set1intersectset2line2")) {
 			return strsForVenn[1][2];
 		}	
 		if (name.equals("set1intersectset2line3")) {
 			return strsForVenn[1][3];
 		}
 		if (name.equals("set2minusset1line0")) {
 			return strsForVenn[2][0];
 		}
 		if (name.equals("set2minusset1line1")) {
 			return strsForVenn[2][1];
 		}
 		if (name.equals("set2minusset1line2")) {
 			return strsForVenn[2][2];
 		}	
 		if (name.equals("set2minusset1line3")) {
 			return strsForVenn[2][3];
 		}										
		return "Section " + name + " NOT found!";
	}
	
/**
 * Method generates two sets of random size
 * with random integers (from -3 to 9) as their elements. 
 * Max. size of set is 10 elements.
 */
 
// Generates sets
 protected void generate() {
 	            int size;
 	            String name1, name2;
 	            name1=RandomChoice.makeChoice("[A..G]/1")[0];
 	            do {
 	            	name2=RandomChoice.makeChoice("[A..G]/1")[0];	
 	            } while (name2.equals(name1));
 	            size=RandomChoice.randInt(0,MAX_SET_SIZE-1);	
 	         	set1=new MathsSet(name1);
 	         	for (int i=0; i<=size; i++)
 	         		set1.add(Integer.toString(RandomChoice.randInt(MIN_ELEMENT, MAX_ELEMENT)));
 	         	size=RandomChoice.randInt(0,MAX_SET_SIZE-1);	
 	         	set2=new MathsSet(name2);
 	         	for (int i=0; i<=size; i++)
 	         		set2.add(Integer.toString(RandomChoice.randInt(MIN_ELEMENT, MAX_ELEMENT)));	
 	         	set1IntersectSet2=new Intersection(set1, set2);
 	         	set1UnionSet2=new Union(set1,set2);
 	         	set1MinusSet2=new SetDifference(set1,set2);
 	         	set2MinusSet1=new SetDifference(set2,set1);
 	         	HashSet[] hss= new HashSet[3];
 	            hss[0]=(HashSet)set1MinusSet2.calculate();
 	            hss[2]=(HashSet)set2MinusSet1.calculate();
 	            hss[1]=(HashSet)set1IntersectSet2.calculate(); 	
 	         	strsForVenn=SmartGraph.break2Sets (hss, shares);
	}//generate 
	
/**
 * Method generates two sets of random size
 * with random integers as their elements.
 * @param      sets   Two sets in format name1, name2, {el, el, el ... el},{el, el, el ... el}
 *					  quotation marks are not allowed 
 */	
protected void generate(String[] sets) {
	            if (sets.length<4) {
	            	generate();
	            	return;
	            }
	            ListIterator iter;
	            Vector <String> firstSet=new Vector<String>();
	            Vector <String> secondSet=new Vector<String>();
	            boolean first=true;
	            String tempStr;
	            String name1, name2;
	            name1=new String(sets[0]);
	            name2=new String(sets[1]);
	            for (int k=2; k<sets.length; k++)
	            {	
	               tempStr=sets[k].replaceAll("[ {}]","");
	               if (tempStr.length()!=0){
	           	    if (first)	            	
	            	firstSet.add(tempStr);
	              	 else 
	            	secondSet.add(tempStr); 	
	               }	              
	               if (sets[k].contains("}")) first=false;
	            }
					
 	         	set1=new MathsSet(name1);
 	         	iter=firstSet.listIterator();
 	         	while (iter.hasNext())
 	         		set1.add(iter.next());
 	         	set2=new MathsSet(name2);
 	         	iter=secondSet.listIterator();
 	         	while (iter.hasNext())
 	         		set2.add(iter.next());	
 	         	set1IntersectSet2=new Intersection(set1, set2);
 	         	set1UnionSet2=new Union(set1,set2);
 	         	set1MinusSet2=new SetDifference(set1,set2);
 	         	set2MinusSet1=new SetDifference(set2,set1);
 	         	HashSet[] hss= new HashSet[3];
 	            hss[0]=(HashSet)set1MinusSet2.calculate();
 	            hss[2]=(HashSet)set2MinusSet1.calculate();
 	            hss[1]=(HashSet)set1IntersectSet2.calculate(); 	
 	         	strsForVenn=SmartGraph.break2Sets (hss, shares);
 	         
	}//generate 	
} 
