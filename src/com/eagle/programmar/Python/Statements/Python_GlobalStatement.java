// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_GlobalStatement extends TokenSequence
{
	public @S(10) @DOC("simple_stmts.html#the-global-statement") Python_KeywordChoice GLOBAL =
			new Python_KeywordChoice("global", "nonlocal");
	public @S(20) SeparatedList<Python_Identifier_Reference, PunctuationComma> vars;
	
	public Python_ComplexStatement generateGlobal(String variableName, AbstractToken source)
	{
		GLOBAL.setValue("global");
		vars = new SeparatedList<Python_Identifier_Reference, PunctuationComma>();
		Python_Identifier_Reference id = new Python_Identifier_Reference();
		id.setValue(variableName);
		vars.addPrimaryElement(id);
		
		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
