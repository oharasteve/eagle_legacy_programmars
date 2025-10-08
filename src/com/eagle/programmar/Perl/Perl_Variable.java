// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Number;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_Variable extends TokenChooser
{
	public @CHOICE Perl_Identifier_Reference XXvariable;

	public @CHOICE static class Perl_UserVariable extends TokenSequence
			implements EagleRunnable
	{
		public @S(10) Perl_Identifier_Reference id;
		public @S(20) @OPT TokenList<Perl_Subscript> subscript;
		public @S(30) @OPT Perl_ClassField fld;
		public @S(40) @OPT Perl_VarFunctionCall fnCall;
		public @S(50) @OPT Perl_ExpressionList braces;

		public static class Perl_ClassField extends TokenSequence
		{
			public @S(10) Perl_Punctuation arrow = new Perl_Punctuation("->");
			public @S(20) Perl_Identifier_Reference fld;
			public @S(30) @OPT TokenList<Perl_Subscript> subscripts;
		}

		public static class Perl_VarFunctionCall extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @OPT Perl_Comment comment;
			public @S(30) @OPT SeparatedList<Perl_Expression, PunctuationComma> parameters;
			public @S(40) PunctuationRightParen rightParen;
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(id.getValue());
			if (subscript != null && subscript.size() > 0)
			{
				EagleArray array = (EagleArray) value;
				int sub = interpreter.getIntValue(subscript.first().expr);
				EagleValue val = array.getValue(sub);
				interpreter.pushEagleValue(val);
			}
			else
			{
				interpreter.pushEagleValue(value);
			}
		}
	}

	public @LAST static class Perl_DollarBraceVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(20) Perl_ExpressionList braces;
	}

	public @CHOICE static class Perl_DollarNumberVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(20) Perl_Number number;
	}

	public @LAST static class Perl_DollarUnderscoreVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(20) Perl_Punctuation underscore = new Perl_Punctuation('_');
	}

	public @CHOICE static class Perl_DollarBarVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(20) Perl_Punctuation bar = new Perl_Punctuation('|');
	}

	public @CHOICE static class Perl_DollarSignalVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(20) Perl_Keyword SIG = new Perl_Keyword("SIG");
		public @S(30) PunctuationLeftBrace leftBrace;
		public @S(40) Perl_Expression signal;
		public @S(50) PunctuationRightBrace rightBrace;
	}

	public @CHOICE static class Perl_SpecialVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(20) Perl_Punctuation caret = new Perl_Punctuation('^');
		public @S(30) Perl_KeywordChoice special = new Perl_KeywordChoice("O");
	}

	public @CHOICE static class Perl_PercentUTFVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation percent = new Perl_Punctuation('%');
		public @S(20) Perl_Keyword UTF8 = new Perl_Keyword("utf8");
		public @S(30) Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public @S(40) Perl_Identifier_Reference id;
	}

	public @CHOICE static class Perl_PercentVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation percent = new Perl_Punctuation('%');
		public @S(20) Perl_Identifier_Reference id;
	}

	public @CHOICE static class Perl_AmpersandVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation ampersand = new Perl_Punctuation('&');
		public @S(20) Perl_Identifier_Reference id;
	}

	public @CHOICE static class Perl_AtVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation at = new Perl_Punctuation('@');
		public @S(20) Perl_Identifier_Reference id;
	}

	public @CHOICE static class Perl_AtEachVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation at = new Perl_Punctuation('@');
		public @S(20) Perl_Keyword EACH = new Perl_Keyword("each");
	}

	public @LAST static class Perl_AtUnderscoreVariable extends TokenSequence
	{
		public @S(10) Perl_Punctuation at = new Perl_Punctuation('@');
		public @S(20) Perl_Punctuation underscore = new Perl_Punctuation('_');
	}

	public @CHOICE static class Perl_NamespaceVariable extends TokenSequence
	{
		public @S(10) Perl_Identifier_Reference id1;
		public @S(20) @OPT TokenList<Perl_More_NamespaceVars> more;
		public @S(30) Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public @S(40) @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(50) Perl_Identifier_Reference id2;
		public @S(60) @OPT Perl_NamespaceArrow arrow;

		public static class Perl_More_NamespaceVars extends TokenSequence
		{
			public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public @S(20) Perl_Identifier_Reference id;
		}

		public static class Perl_NamespaceArrow extends TokenSequence
		{
			public @S(10) Perl_Punctuation arrow = new Perl_Punctuation("->");
			public @S(20) Perl_Keyword NEW = new Perl_Keyword("new");
			public @S(30) @OPT TokenList<Perl_Subscript> subscripts;
		}
	}

	public @CHOICE static class Perl_ListVariable extends TokenSequence
	{
		public @S(10) Perl_Keyword LIST = new Perl_Keyword("list");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT PunctuationComma comma;
		public @S(40) SeparatedList<Perl_Expression, PunctuationComma> args;
		public @S(50) PunctuationRightParen rightParen;
	}
}
