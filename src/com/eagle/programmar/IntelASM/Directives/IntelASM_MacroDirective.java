// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Macro_Definition;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class IntelASM_MacroDirective extends TokenSequence
{
	public @S(10) IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
	public @S(20) IntelASM_Keyword MACRO = new IntelASM_Keyword("macro");
	public @S(30) IntelASM_Macro_Definition name;
	public @S(40) IntelASM_Number parameterCount;
	public @S(50) IntelASM_EndOfLine eoln;
	
	public TokenList<IntelASM_Line> lines;

	public IntelASM_EndMacro endMacro;
	
	public static class IntelASM_EndMacro extends TokenSequence
	{
		public @S(10) IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
		public @S(20) IntelASM_Keyword ENDMACRO = new IntelASM_Keyword("endmacro");
	}
}
