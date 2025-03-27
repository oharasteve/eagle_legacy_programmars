// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Bash_DollarSubstring extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) Bash_Variable variable;
	public @S(40) PunctuationColon colon1;
	public @S(50) Bash_Expression start;
	public @S(60) PunctuationColon colon2;
	public @S(70) Bash_Expression len;
	public @S(80) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(variable.id.getValue());
		String str = val.forceStringValue();
		int nc = str.length();
		int sc = interpreter.getIntValue(start);
		int ec = sc + interpreter.getIntValue(len);
		if (ec > nc) ec = nc;
		String piece = str.substring(sc, ec);
		interpreter.pushStr(piece);
	}
}