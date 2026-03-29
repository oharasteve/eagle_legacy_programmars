// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{

	using Java_ArgumentList = com.eagle.programmar.Java.Java_ArgumentList;
	using Java_MoreArguments = com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
	using Java_ClassElement = com.eagle.programmar.Java.Java_Class.Java_ClassElement;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_IdList = com.eagle.programmar.Java.Java_Type.Java_IdList;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_ClassCreationExpression : PrimaryOperator, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword NEW = new com.eagle.programmar.Java.Terminals.Java_Keyword("new");
		public Java_Keyword NEW = new Java_Keyword("new");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Java_Type jtype;
		public Java_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE @OPT Java_ArgumentList argList;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Java_ClassOverride override;
		public  OPT;

		public class Java_ClassOverride : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Java.Java_Class.Java_ClassElement> elementList;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = this.jtype.typeName.getWhich();
			if (which is Java_Type.Java_IdList)
			{
				Java_Type.Java_IdList ids = (Java_Type.Java_IdList) which;
				string className = ids.typeName.getValue();
				AbstractType type = generator.transformType(EagleGenerator.TypeEnum.OTHER, className, ids);

				List<AbstractExpression> args = new List<AbstractExpression>();
				if (this.argList != null && this.argList.isPresent())
				{
					args.Add(transformer.transformExpression(generator, this.argList.arg));
					if (this.argList.moreArgs != null && this.argList.moreArgs.isPresent())
					{
						foreach (Java_ArgumentList.Java_MoreArguments arg in this.argList.moreArgs._elements)
						{
							args.Add(transformer.transformExpression(generator, arg.arg));
						}
					}
				}

				return generator.newClassCreation(type, args, this);
			}
			throw new Exception("Can't handle: " + this);
		}

		public static Java_Expression generateCreation(Java_Type type, List<Java_Expression> args, AbstractToken source)
		{
			Java_ClassCreationExpression creat = new Java_ClassCreationExpression();
			creat.jtype = type;
			creat.leftParen = new PunctuationLeftParen();
			if (args != null && args.Count > 0)
			{
				creat.argList = Java_ArgumentList.createArgumentList(args);
				creat.argList.setPresent(true);
			}
			creat.rightParen = new PunctuationRightParen();

			creat.setTransformationSource(source);
			return Java_Generator.wrapExpression(creat);
		}
	}

}
