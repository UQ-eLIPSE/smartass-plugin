/* Generated By:JJTree: Do not edit this line. ASTAnyText.java */

package au.edu.uq.smartass.templates.texparser;

import java.util.Set;

import au.edu.uq.smartass.script.Script;

public class ASTAnyText extends SimpleNode {
	
	@Override
	public RAnyText execute(Set<String> sections, Script script) {
		RAnyText r = new RAnyText(this);
		r.setText(getText());
		return r;
	}

	public ASTAnyText(int id) {
		super(id);
	}

	public ASTAnyText(TexParser p, int id) {
		super(p, id);
	}

}
