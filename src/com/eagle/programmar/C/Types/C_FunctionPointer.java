// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2024

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.C_Function.C_FunctionParameter;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Function.C_MoreParameterDefs;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_FunctionPointer extends TokenSequence
{
	public @S(10) @OPT C_KeywordChoice scope = new C_KeywordChoice(C_Program.getModifiers());
	public @S(20) C_Type jtype;
	public @S(30) PunctuationLeftParen leftParen1;
	public @S(40) PunctuationStar star;
	public @S(50) @OPT C_KeywordChoice NULLABLE = new C_KeywordChoice("_Nullable", "_Nonnull");
	public @S(60) @OPT C_Field_Definition id;
	public @S(70) @OPT C_Function_ParameterDefs weirdExtraParameters;
	public @S(80) PunctuationRightParen rightParen1;
	public @S(90) PunctuationLeftParen leftParen2;
	public @S(100) @OPT C_Comment comment;
	public @S(110) @OPT C_FunctionParameter param;
	public @S(120) @OPT TokenList<C_MoreParameterDefs> moreParams;
	public @S(130) PunctuationRightParen rightParen2;
	public @S(140) @OPT PunctuationSemicolon semicolon;
}
