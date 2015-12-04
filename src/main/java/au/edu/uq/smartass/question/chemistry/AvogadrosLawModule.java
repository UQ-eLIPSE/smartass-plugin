/* @(#)AvogadrosLawModule.java
 *
 * This file is part of SmartAss and describes class AvogadrosLawModule for 
 * question on Avogadro's law. Given  V1(volume) and n1(number of moles), calculate the volume 
 * V2 of a new gas, if we know the proportion n1/n2 (this proportion passed into module as
 * parameters).
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
package au.edu.uq.smartass.question.chemistry;

import au.edu.uq.smartass.auxiliary.RandomChoice;
import au.edu.uq.smartass.engine.QuestionModule;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Describes the question on Avogadro's law:
 * Given  V1(volume) and n1(number of moles), calculate the volume 
 * V2 of a new gas, if we know that m1 molecules of the first gas 
 * produces m2 molecules of the second (m1 and m2 passed into modules as 
 * parameters).  
 *
 */
public final class AvogadrosLawModule implements QuestionModule {

    /** Class logger. */
    private static final Logger LOG = LoggerFactory.getLogger( AvogadrosLawModule.class );


    private static final String MIN_V="5.0";
    private static final String MAX_V="20.0";
    private static final String MIN_N="0.1";
    private static final String MAX_N="10.0";

        /** Gas pressure in Atmospheres. */
        private String pressure;
        
        /** Volume of 1st gas. */
        private double v1;
        
        /** Number of moles of 1st gas. */
        private double n1;

        /** Number of molecules of 1st gas. */
        private int m1;

        /** Number of molecules of 1st gas. */
        private int m2;
        
 
        /**
         * Constructor AvogadrosLawModule.
         * 
         * @param params List of String initialization parameters; either 5 or 2.
         *      params[0] - gas pressure,
         *      parmas[1] - v1 - volume of the first gas,
         *      params[2] - n1 - number of moles of the first gas, 
         *      params[3] - m1 - number of molecules in the formula for the first gas,
         *		params[4] - m2 - number of molecules in the formula for the second gas,
         *  or
         *      params[0] - m1 - number of molecules in the formula for the first gas,
         *		params[1] - m2 - number of molecules in the formula for the second gas,
         *  i.e. for oxygen into ozone 3O2->2O3, m1==3, m2==2
         */
        @Override
        public QuestionModule initialise(String[] params) {
            LOG.info( "::intialize( {} )[]", params.toString() );
            try {
                switch (params.length) {
                case 5:
                    initialise(params[0], params[1], params[2], params[3], params[4]);
                    break;
                case 2:
                    initialise(params[0], params[1]);
                    break;
                default:
                    // @TODO: presumably an error?
                }
                return this;

            } catch (NumberFormatException ex) {
                System.out.println("IllegalArgumentException while processing parameters passed into AvogadrosLawModule");
                throw ex;
            }
        }

    /**
     * 
     */
    public AvogadrosLawModule() {}
         
    /**
     * 
     * @param pressure
     * @param volume1
     * @param moles1
     * @param molecules1
     * @param molecules2
     */
    private void initialise(
            final String pressure, 
            final String volume1, final String moles1, 
            final String molecules1, final String molecules2
    ) {
        this.pressure = pressure;
        this.v1 = Double.parseDouble(volume1);
        this.n1 = Double.parseDouble(moles1);
        this.m1=Integer.parseInt(molecules1); 
        this.m2=Integer.parseInt(molecules2);
    }
	
    private void initialise(
            final String molecules1, final String molecules2
    ) {
        this.pressure = (
                new BigDecimal(0.5*RandomChoice.randInt(1,5))
                .setScale(1,BigDecimal.ROUND_HALF_UP)
                .stripTrailingZeros()
            ).toPlainString();  //pressure in atm        
        this.v1=Double.parseDouble(RandomChoice.makeChoice("["+MIN_V+".."+MAX_V+"]/1")[0]);
        this.n1=Double.parseDouble(RandomChoice.makeChoice("["+MIN_N+".."+MAX_N+"]/1")[0]); 		
        
        this.m1=Integer.parseInt(molecules1); 
        this.m2=Integer.parseInt(molecules2);
    }

    /**
          * getSection method typesets a question and solution 
          * @param name
          * @return a String containing Latex code for the section
          */
        @Override
        public String getSection(String name) {
            if(name.equals("pressure")) return pressure;
            if(name.equals("v1")) return (new BigDecimal(v1).setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros()).toPlainString();
            if(name.equals("v2")) return (new BigDecimal(v1*m2/m1).setScale(1, BigDecimal.ROUND_HALF_UP)).toString();
            if(name.equals("n1")) return (new BigDecimal(n1).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
            if(name.equals("n2")) return (new BigDecimal(n1*m2/m1).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();	
            if(name.equals("m1")) return Integer.toString(m1);
            if(name.equals("m2")) return Integer.toString(m2);			
            return "Section: " + name + " NOT found!";
	}
	
} 
