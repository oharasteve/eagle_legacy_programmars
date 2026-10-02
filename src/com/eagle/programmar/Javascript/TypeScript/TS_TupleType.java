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

/** [T, U?, ...V[]], with optional labels [a: T]. */
public class TS_TupleType extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT SeparatedList<TS_TupleElement, PunctuationComma> elements;
	public @S(30) @OPT PunctuationComma trailingComma;
	public @S(40) PunctuationRightBracket rightBracket;

	public static class TS_TupleElement extends TokenSequence
	{
		public @S(10) @OPT Javascript_Punctuation rest = new Javascript_Punctuation("...");
		public @S(20) @OPT TS_TupleLabel label;
		public @S(30) TS_Type type;
		public @S(40) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
	}

	public static class TS_TupleLabel extends TokenSequence
	{
		public @S(10) Javascript_Field_Definition name;
		public @S(20) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
		public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
	}
}
