// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2022

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifiers;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameters;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Operator extends TokenSequence
{
	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(30) @OPT @NEWLINE TokenList<CSharp_MethodModifiers> modifiers;
	public @S(40) @OPT TokenList<CSharp_Comment> comment2;
	public @S(50) CSharp_Type returnType;
	public @S(60) CSharp_Keyword OPERATOR = new CSharp_Keyword("operator");
	public @S(70) CSharp_PunctuationChoice minus = new CSharp_PunctuationChoice(
			"+", "-", "<", ">", "<=", ">=", "==", "!=");
	public @S(80) @OPT CSharp_MethodParameters parameters;
	public @S(90) @NEWLINE CSharp_MethodBody body;
}
