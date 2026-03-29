// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada
{
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
	using Ada_KeywordChoice = com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Ada_Type : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Ada_Keyword CONSTANT = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("constant");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Ada_WhichType which;
		public Ada_WhichType which;

		public class Ada_WhichType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_KeywordChoice XXprimitives = new com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice("Boolean", "Integer", "Float", "String", "Unbounded_String");
			public Ada_KeywordChoice XXprimitives = new Ada_KeywordChoice("Boolean", "Integer", "Float", "String", "Unbounded_String");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_ArrayType XXarrayType;
			public Ada_ArrayType XXarrayType;
		}

		public class Ada_ArrayType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword ARRAY = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("array");
			public Ada_Keyword ARRAY = new Ada_Keyword("array");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Ada_Expression range;
			public Ada_Expression range;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Ada.Terminals.Ada_Keyword OF = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("of");
			public Ada_Keyword OF = new Ada_Keyword("of");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) Ada_Type baseType;
			public Ada_Type baseType;
		}

		public virtual AbstractType convertType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum newType = null;
			string userType = null;
			AbstractToken whichType = which.getWhich();
			if (whichType is Ada_KeywordChoice)
			{
				Ada_KeywordChoice kw1 = (Ada_KeywordChoice) whichType;
				switch (kw1.getValue())
				{
				case "Boolean":
					newType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "Integer":
					newType = EagleGenerator.TypeEnum.INTEGER;
					break;
				case "Unbounded_String":
					newType = EagleGenerator.TypeEnum.STRING;
					break;
				case "Float":
					newType = EagleGenerator.TypeEnum.DOUBLE;
					break;
				default:
					throw new Exception("Unable to convert type: " + kw1.getValue());
				}
			}
			else if (whichType is Ada_ArrayType)
			{
				Ada_ArrayType array = (Ada_ArrayType) whichType;
				if (array.baseType.which.getWhich() is Ada_KeywordChoice)
				{
					Ada_KeywordChoice kw2 = (Ada_KeywordChoice) array.baseType.which.getWhich();
					if (kw2.getValue().Equals("Unbounded_String"))
					{
						newType = EagleGenerator.TypeEnum.ARRAY;
					}
				}
			}

			if (newType == null)
			{
				throw new Exception("Can't handle type yet: " + whichType);
			}
			return generator.transformType(newType, userType, this);
		}
	}

}
