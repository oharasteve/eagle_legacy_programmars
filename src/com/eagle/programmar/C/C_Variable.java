// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) @OPT TokenList<C_VariableStar> stars;
	public @S(20) C_VariableIdentifier firstId;
	public @S(30) @OPT TokenList<C_ExtendedIdentifier> moreIds;
	public @S(40) @OPT TokenList<C_Subscript> subscript;

	public static class C_VariableStar extends TokenSequence
	{
		public @S(10) PunctuationStar star;
	}

	public static class C_VariableIdentifier extends TokenChooser
	{
		public @CHOICE C_CastedVariable XXcastedVariable;
		public @CHOICE C_IndirectVariable XXindirectVariable;
		public @CHOICE C_SubscriptedVariable XXsubscriptedVariable;
		public @LAST C_Identifier_Reference XXid;
	}

	public static class C_CastedVariable extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen1;
		public @S(20) PunctuationLeftParen leftParen2;
		public @S(30) C_Type jtype;
		public @S(40) PunctuationRightParen rightParen1;
		public @S(50) C_Identifier_Reference id;
		public @S(60) PunctuationRightParen rightParen2;
	}

	public static class C_IndirectVariable extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) C_Variable var;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class C_SubscriptedVariable extends TokenSequence
	{
		public @S(10) C_Identifier_Reference id;
		public @S(20) TokenList<C_Subscript> subscripts;
	}

	public static class C_ExtendedIdentifier extends TokenChooser
	{
		public @CHOICE static class C_DotIdentifier extends TokenSequence
		{
			public @S(10) PunctuationPeriod dot;
			public @S(20) C_Identifier_Reference id;
		}

		public @CHOICE static class C_ArrowIdentifier extends TokenSequence
		{
			public @S(10) C_Punctuation arrow = new C_Punctuation("->");
			public @S(20) C_Identifier_Reference id;
		}

		public @CHOICE static class C_ColonColonIdentifier extends TokenSequence
		{
			public @S(10) C_Punctuation colonColon = new C_Punctuation("::");
			public @S(20) @OPT C_Punctuation tilde = new C_Punctuation("~");
			public @S(30) C_Identifier_Reference id;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = firstId.getWhich();
		if (which instanceof C_Identifier_Reference)
		{
			C_Identifier_Reference id = (C_Identifier_Reference) which;
			EagleValue value = interpreter._symbolTable.findSymbol(id.getValue());
			interpreter.pushEagleValue(value);
		}
		else if (which instanceof C_SubscriptedVariable)
		{
			C_SubscriptedVariable id = (C_SubscriptedVariable) which;
			EagleArray value = (EagleArray) interpreter._symbolTable.findSymbol(id.id.getValue());
			C_Subscript subscr = id.subscripts._elements.get(0);
			int sub = interpreter.getIntValue(subscr.expr);
			interpreter.pushEagleValue(value.getValue(sub));
		}
	}
}
