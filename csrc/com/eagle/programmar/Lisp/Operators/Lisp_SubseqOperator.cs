// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

namespace com.eagle.programmar.Lisp.Operators
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Lisp_SubseqOperator : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword SUBSEQ = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("SUBSEQ");
		public Lisp_Keyword SUBSEQ = new Lisp_Keyword("SUBSEQ");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Lisp.Lisp_Expression expr;
		public Lisp_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Lisp.Lisp_Expression scExpr;
		public Lisp_Expression scExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Lisp.Lisp_Expression ecExpr;
		public Lisp_Expression ecExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(expr);
			int sc = interpreter.getIntValue(scExpr);
			int ec = interpreter.getIntValue(ecExpr);
			interpreter.pushStr(str.Substring(sc, ec - sc));
		}
	}

}
