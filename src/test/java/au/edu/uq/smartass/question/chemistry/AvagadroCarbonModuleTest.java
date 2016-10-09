package au.edu.uq.smartass.question.chemistry;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 */
public class AvagadroCarbonModuleTest {


    @Test
    public void AvagadroCarbonModuleDefault() {
        try {
            AvagadroCarbonModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void AvagadroCarbonModuleParam() {
        avagadroCarbonModuleParamHelper(0.09);
        avagadroCarbonModuleParamHelper(50.01);
    }

    private void avagadroCarbonModuleParamHelper(double carat) {
        try {
            new AvagadroCarbonModule(carat);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void getSection() throws Exception {
        AvagadroCarbonModule acm = new AvagadroCarbonModule(2.00);

        String expected;

        expected = "Calculate the number of carbon atoms that are in a $2.0$-carat diamond that weighs $0.4000$g.";
        System.out.println(expected);
        assertEquals(expected, acm.getSection("question"));

        expected =
				"\\newcommand{\\scientific}[1]" +
                "{\\num[round-precision=4,round-mode=figures,scientific-notation=true]{#1}}\n" +
				"The molar mass of carbon is $12.01$g mol$^{-1}$.\\\\\n" +
				"The number of moles $=$ mass $\\div$ molar mass $=\\dfrac{0.40}{12.0}=0.0333$ moles.\\\\\n" +
				"Therefore the number of molecules $=$ moles $\\times$ Avogadro's number \n" +
				"$=0.0333\\times(\\scientific{6.0220e+23})\n" +
				"=\\scientific{2.0057e+22}$ molecules.";
        System.out.println(expected);
        assertEquals(expected, acm.getSection("solution"));

        expected =
				"\\newcommand{\\scientific}[1]{\\num[round-precision=4,round-mode=figures,scientific-notation=true]{#1}}\n" +
				"\\scientific{2.0057e+22}";
        System.out.println(expected);
        assertEquals(expected, acm.getSection("answer"));

    }

}
