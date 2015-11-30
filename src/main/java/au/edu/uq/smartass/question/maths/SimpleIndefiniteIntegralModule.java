/**
 * 
 */
package au.edu.uq.smartass.question.maths;

import au.edu.uq.smartass.auxiliary.RandomChoice;
import au.edu.uq.smartass.maths.DerivateFunctions;

/**
 * @author carnivore
 *
 */
public class SimpleIndefiniteIntegralModule extends IndefiniteIntegralModule {

	/**
	 * @param engine	an instance of SmartAss engine 
	 */
	public SimpleIndefiniteIntegralModule() {
		super();
		
		for(int i=0;i<MAX_PARTS;i++) {
			int param = RandomChoice.randInt(1, MAX_NUM/2); 
			f[i] = new DerivateFunctions(0, 
					(param+1) * RandomChoice.randInt(1, MAX_NUM)*RandomChoice.randSign(),
					param,
					vx);
		}
		
		generate();
	}
}
