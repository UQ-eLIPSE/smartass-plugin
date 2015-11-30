package au.edu.uq.smartass.question.script;

import au.edu.uq.smartass.engine.QuestionModule;

public class TestDSModule implements QuestionModule {
	String[] params;

	public TestDSModule() {
		params = new String[]{};
	}

	public TestDSModule(String[] params) {
		this.params = params;
	}
	
	@Override
	public String getSection(String name) {
		String s = "";
		for(int i=0;i<params.length;i++) {
			s = s + params[i] + " ";
			System.out.println("param["+Integer.toString(i) + "]:" + params[i]);
		}
		return s;
	}

}
