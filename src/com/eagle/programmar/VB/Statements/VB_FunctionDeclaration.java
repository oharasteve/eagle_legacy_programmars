// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Parameters;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.VB_Type;
import com.eagle.programmar.VB.Symbols.VB_Sub_Definition;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_FunctionDeclaration extends TokenSequence
{
	public @S(10) @OPT VB_KeywordChoice modifier = new VB_KeywordChoice(
			"private", "public");
	public @S(20) VB_Keyword FUNCTION1 = new VB_Keyword("function");
	public @S(30) VB_Sub_Definition name;
	public @S(40) VB_Parameters params;
	public @S(50) @OPT VB_Keyword AS = new VB_Keyword("as");
	public @S(60) @OPT VB_Type type;
	public @S(70) VB_EndOfLine eoln;
	public @S(80) @OPT TokenList<VB_Statement> stmts;
	public @S(90) VB_Keyword END = new VB_Keyword("end");
	public @S(100) VB_Keyword FUNCTION2 = new VB_Keyword("function");
}
