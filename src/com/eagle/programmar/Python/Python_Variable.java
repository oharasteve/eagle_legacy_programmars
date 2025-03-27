// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Python_Variable extends TokenSequence implements AbstractVariable, EagleRunnable
{
	public @S(10) Python_SelfOrVariable var;

	public static class Python_SelfOrVariable extends TokenChooser
	{
		public @CHOICE Python_Keyword XXSELF = new Python_Keyword("self");
		public @CHOICE Python_Identifier_Reference XXid;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Python_Identifier_Reference which = (Python_Identifier_Reference) var.getWhich();
		EagleValue value = interpreter.findSymbol(which.toString());
		interpreter.pushEagleValue(value);
	}
	
	public static Python_Variable newVariable(String name)
	{
		Python_Variable var = new Python_Variable();
		var.var = new Python_SelfOrVariable();
		Python_Identifier_Reference id = new Python_Identifier_Reference();
		id.setValue(name);
		var.var.setWhich(id);
		return var;
	}
}
