package au.edu.uq.smartass.question.chemistry;

import java.lang.reflect.Constructor;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 */
public class AvagadroGlucoseModuleTest {

    AvagadroGlucoseModule qsn = new AvagadroGlucoseModule(5.00D);

    /**
     * Check for 'public' default constructor.
     *
     * @throws Exception
     */
    @Test
    public void AvagadroGlucoseModuleDefaultTest() throws Exception {
        try {
            Constructor<AvagadroGlucoseModule> constructor
                    = AvagadroGlucoseModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void AvagadroGlucoseModuleParamTest() {
        avagadroGlucoseModuleParamHelper(0.4);
        avagadroGlucoseModuleParamHelper(50.1);
    }

    private void avagadroGlucoseModuleParamHelper(double grm) {
        try {
            new AvagadroGlucoseModule(grm);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void getQuestionTest() {
        String expected =
                "Calculate the number of molecules in 5.00g of glucose powder $(C_6H_{12}O_6)$.";
        assertEquals(expected, qsn.getSection("question"));
    }

    @Test
    public void getSolutionTest() {
        String expected =
				"The molar mass of glucose is $180.2$g mol$^{-1}$.\\\\\n" +
				"The number of moles $=$ mass $\\div$ molar mass $=\\dfrac{5.00}{180.2}=0.0278$ moles.\\\\\n" +
				"Therefore the number of molecules $=$ moles $\\times$ Avogadro’s number " +
                "$=0.0278\\times(" +
                "\\num[round-precision=4,round-mode=figures,scientific-notation=true]{6.022000e+23}" +
                ")=\\num[round-precision=4,round-mode=figures,scientific-notation=true]{1.671292e+22}$ molecules.";
        System.out.println(expected);
        assertEquals(expected, qsn.getSection("solution"));
    }

    @Test
    public void getAnswerTest() {
        String expected = "\\num[round-precision=4,round-mode=figures,scientific-notation=true]{1.671292e+22}";
        assertEquals(expected, qsn.getSection("answer"));
    }


}