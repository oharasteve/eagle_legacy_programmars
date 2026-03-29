// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 28, 2026

namespace com.eagle.programmar.Rust.Expressions
{

	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Type = com.eagle.programmar.Rust.Rust_Type;
	using Rust_Comment = com.eagle.programmar.Rust.Terminals.Rust_Comment;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Rust_ClassCreationExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Keyword NEW = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("new");
		public Rust_Keyword NEW = new Rust_Keyword("new");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Type rstype;
		public Rust_Type rstype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE @OPT TokenList<com.eagle.programmar.Rust.Terminals.Rust_Comment> comments;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE @OPT SeparatedList<com.eagle.programmar.Rust.Rust_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

	//	@Override
	//	public AbstractExpression transformExpression(EagleTransformer transformer,
	//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	//	{
	//		String className = "Unknown";
	//		AbstractType type = generator.transformType(TypeEnum.OTHER, className, null);
	//
	//		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
	//		if (this.argList != null && this.argList.isPresent())
	//		{
	//			for (int i = 0; i < argList.size(); i++)
	//			{
	//				args.add(transformer.transformExpression(generator, argList.getPrimaryElement(i)));
	//			}
	//		}
	//
	//		return generator.newClassCreation(type, args, this);
	//	}

		public static Rust_Expression generateCreation(Rust_Type type, List<Rust_Expression> args, AbstractToken source)
		{
			Rust_ClassCreationExpression creat = new Rust_ClassCreationExpression();
			creat.rstype = type;
			creat.leftParen = new PunctuationLeftParen();
			if (args != null && args.Count > 0)
			{
				creat.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
				for (int i = 0; i < args.Count; i++)
				{
					if (i > 0)
					{
						creat.argList.addSecondaryElement(new PunctuationComma());
					}
					creat.argList.addPrimaryElement(args[i]);
				}
				creat.argList.setPresent(true);
			}
			creat.rightParen = new PunctuationRightParen();

			creat.setTransformationSource(source);
			return Rust_Generator.wrapExpression(creat);
		}
	}

}
