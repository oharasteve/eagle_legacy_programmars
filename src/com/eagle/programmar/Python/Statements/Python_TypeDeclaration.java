// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 19, 2022

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_TypeDeclaration extends TokenSequence implements AbstractStatement
{
	public @S(10) Python_Variable var;
	public @S(20) PunctuationColon colon;
	public @S(30) Python_Type type;
}
