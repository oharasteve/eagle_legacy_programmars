// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_TemplateLiteral;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

/** (a: T, b?: U) => R, with optional type parameters. */
public class TS_FunctionType extends TokenSequence
{
	public @S(5) @OPT TS_Generics generics;
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT SeparatedList<TS_FunctionTypeParam, PunctuationComma> params;
	public @S(30) PunctuationRightParen rightParen;
	public @S(40) Javascript_Punctuation arrow = new Javascript_Punctuation("=>");
	public @S(50) TS_Type result;
}
