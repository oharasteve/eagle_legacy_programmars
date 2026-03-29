// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class CSharp_SubfieldExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression left = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_Punctuation question = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('?');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE CSharp_Expression right = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public  NOSPACE;

		public virtual CSharp_Expression generateSubfield(CSharp_Expression leftExpr, CSharp_Expression rightExpr, AbstractToken source)
		{
			this.left = leftExpr;
			this.dot = new PunctuationPeriod();
			this.right = rightExpr;
			this.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(this);
		}
	}

}
