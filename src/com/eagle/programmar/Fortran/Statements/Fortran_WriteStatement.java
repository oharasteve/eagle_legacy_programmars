// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.programmar.Fortran.Fortran_Format;
import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_Comment;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_WriteStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("6j4m0vnbs/index.html") Fortran_Keyword WRITE = new Fortran_Keyword("WRITE");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Fortran_Variable var;
	public @S(40) PunctuationComma comma;
	public @S(50) Fortran_Literal format;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters;
	public @S(80) @OPT Fortran_Comment comment;
	public @S(90) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Example: WRITE(numStr, '(I5)') numb
		// puts the number 'numb' into the string 'numStr' with format I5
		String formatted = Fortran_Format.format(interpreter, format.getValue(), parameters);
		EagleString val = new EagleString(formatted);
		interpreter.setSymbol(this, var.var.getValue(), val);
	}
}
