// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 1, 2026: ES2015 destructuring patterns,
// const { a, b = 1, c: d, ...rest } = obj;  const [x, , y, ...more] = arr;

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Javascript_Pattern
{
	// The engine does not try a chooser whose choice is itself a chooser, so every chooser
	// below lists the object and array patterns directly rather than through one shared rule.

	/** { a, b = 1, c: d, ...rest } */
	public static class Javascript_ObjectPattern extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT SeparatedList<Javascript_PatternProperty, PunctuationComma> properties;
		public @S(30) @OPT PunctuationComma trailingComma;
		public @S(40) PunctuationRightBrace rightBrace;
	}

	public static class Javascript_PatternProperty extends TokenChooser
	{
		public @CHOICE Javascript_PatternRest XXrest;
		public @CHOICE Javascript_PatternRenamed XXrenamed;
		public @CHOICE Javascript_PatternBinding XXbinding;
	}

	/** ...rest */
	public static class Javascript_PatternRest extends TokenSequence
	{
		public @S(10) Javascript_Punctuation ellipsis = new Javascript_Punctuation("...");
		public @S(20) Javascript_Variable_Definition id;
	}

	/** key: target, or "key": target, each with an optional default */
	public static class Javascript_PatternRenamed extends TokenSequence
	{
		public @S(10) Javascript_PatternKey key;
		public @S(20) PunctuationColon colon;
		public @S(30) Javascript_PatternTarget target;
		public @S(40) @OPT Javascript_PatternDefault initial;

		public static class Javascript_PatternKey extends TokenChooser
		{
			public @CHOICE Javascript_Identifier_Reference XXid;
			public @CHOICE Javascript_Literal XXliteral;
		}
	}

	/** id, or id = default */
	public static class Javascript_PatternBinding extends TokenSequence
	{
		public @S(10) Javascript_Variable_Definition id;
		public @S(20) @OPT Javascript_PatternDefault initial;
	}

	public static class Javascript_PatternDefault extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Javascript_Expression expr;
	}

	/** A nested pattern, or the name that receives the value. */
	public static class Javascript_PatternTarget extends TokenChooser
	{
		public @CHOICE Javascript_ObjectPattern XXobject;
		public @CHOICE Javascript_ArrayPattern XXarray;
		public @CHOICE Javascript_Variable_Definition XXid;
	}

	/** [a, , b = 2, [c], ...rest] */
	public static class Javascript_ArrayPattern extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT TokenList<Javascript_ArrayPatternElement> elements;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static class Javascript_ArrayPatternElement extends TokenChooser
	{
		public @CHOICE Javascript_ArrayPatternItem XXitem;
		public @CHOICE PunctuationComma XXhole;
	}

	public static class Javascript_ArrayPatternItem extends TokenSequence
	{
		public @S(10) Javascript_ArrayPatternTarget target;
		public @S(20) @OPT Javascript_PatternDefault initial;
		public @S(30) @OPT PunctuationComma comma;

		public static class Javascript_ArrayPatternTarget extends TokenChooser
		{
			public @CHOICE Javascript_PatternRest XXrest;
			public @CHOICE Javascript_ObjectPattern XXobject;
			public @CHOICE Javascript_ArrayPattern XXarray;
			public @CHOICE Javascript_Variable_Definition XXid;
		}
	}
}
