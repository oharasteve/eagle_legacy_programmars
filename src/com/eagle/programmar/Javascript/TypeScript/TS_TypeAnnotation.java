// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenChooser;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.tokens.punctuation.PunctuationColon;

/** : T, after a name, a parameter list or a signature. */
public class TS_TypeAnnotation extends TokenSequence
{
	public @S(10) @NOSPACE PunctuationColon colon;
	public @S(20) TS_AnnotationBody body;

	public static class TS_AnnotationBody extends TokenChooser
	{
		public @CHOICE TS_AssertsPredicate XXasserts;
		public @CHOICE TS_IsPredicate XXis;
		public @CHOICE TS_Type XXtype;
	}

	/** asserts x, asserts x is T */
	public static class TS_AssertsPredicate extends TokenSequence
	{
		public @S(10) Javascript_Keyword ASSERTS = new Javascript_Keyword("asserts");
		public @S(20) Javascript_Field_Definition name;
		public @S(30) @OPT TS_IsType is;
	}

	/** x is T; the `is` is required, or every plain type name would be taken for a predicate. */
	public static class TS_IsPredicate extends TokenSequence
	{
		public @S(10) Javascript_Field_Definition name;
		public @S(20) TS_IsType is;
	}

	public static class TS_IsType extends TokenSequence
	{
		public @S(10) Javascript_Keyword IS = new Javascript_Keyword("is");
		public @S(20) TS_Type type;
	}
}
