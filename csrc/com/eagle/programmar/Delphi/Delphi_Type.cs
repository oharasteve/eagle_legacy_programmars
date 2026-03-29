// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi
{
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using Delphi_Variable_Definition = com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using Delphi_Number = com.eagle.programmar.Delphi.Terminals.Delphi_Number;
	using Delphi_Punctuation = com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Delphi_Type : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Delphi_Punctuation caret = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation('^');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_BaseType baseType;
		public Delphi_BaseType baseType;

		public class Delphi_BaseType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_KeywordChoice XXbase = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Boolean", "Byte", "Double", "Integer", "LongInt", "Int64", "String", "Text");
			public Delphi_KeywordChoice XXbase = new Delphi_KeywordChoice("Boolean", "Byte", "Double", "Integer", "LongInt", "Int64", "String", "Text");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Identifier_Reference XXuserType;
			public Delphi_Identifier_Reference XXuserType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Class XXclassDefinition;
			public Delphi_Class XXclassDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Enum XXenumType;
			public Delphi_Enum XXenumType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Array XXarrayType;
			public Delphi_Array XXarrayType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Range XXrangeType;
			public Delphi_Range XXrangeType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Type_Record XXrecordType;
			public Delphi_Type_Record XXrecordType;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Delphi_String XXstringType;
			public Delphi_String XXstringType;
		}

		public class Delphi_Enum : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Delphi_EnumValue, com.eagle.tokens.punctuation.PunctuationComma> enumValues;
			public SeparatedList<Delphi_EnumValue, PunctuationComma> enumValues;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;

			public class Delphi_EnumValue : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition name;
				public Delphi_Variable_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationEquals equals;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Expression value;
				public  OPT;
			}
		}

		public class Delphi_Array : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword ARRAY = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Array");
			public Delphi_Keyword ARRAY = new Delphi_Keyword("Array");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Delphi_ArraySize size;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword OF = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Of");
			public Delphi_Keyword OF = new Delphi_Keyword("Of");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Delphi_Type type;
			public Delphi_Type type;

			public class Delphi_ArraySize : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Delphi_Expression, com.eagle.tokens.punctuation.PunctuationComma> subscripts;
				public SeparatedList<Delphi_Expression, PunctuationComma> subscripts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
			}
		}

		public class Delphi_Range : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Number low;
			public Delphi_Number low;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation dotDot = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation("..");
			public Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Terminals.Delphi_Number high;
			public Delphi_Number high;
		}

		public class Delphi_Type_Record : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword RECORD = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Record");
			public Delphi_Keyword RECORD = new Delphi_Keyword("Record");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Delphi_RecordEntry> entries;
			public TokenList<Delphi_RecordEntry> entries;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword END = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("End");
			public Delphi_Keyword END = new Delphi_Keyword("End");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Delphi_Comment comment;
			public  OPT;

			public class Delphi_RecordEntry : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> vars;
				public SeparatedList<Delphi_Variable_Definition, PunctuationComma> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Delphi_Type type;
				public Delphi_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;
			}
		}

		public class Delphi_String : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword STRING = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("String");
			public Delphi_Keyword STRING = new Delphi_Keyword("String");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Delphi_Expression expr;
			public Delphi_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public virtual AbstractType convertType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum newType = null;
			string userType = null;
			AbstractToken which = this.baseType.getWhich();
			if (which is Delphi_KeywordChoice)
			{
				Delphi_KeywordChoice kw = (Delphi_KeywordChoice) which;
				switch (kw.getValue().ToLower())
				{
				case "boolean":
					newType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "integer", "longint":
					newType = EagleGenerator.TypeEnum.INTEGER;
					break;
				case "string":
					newType = EagleGenerator.TypeEnum.STRING;
					break;
				case "double":
					newType = EagleGenerator.TypeEnum.DOUBLE;
					break;
				default:
					throw new Exception("Unable to convert type: " + kw.getValue());
				}
			}
			else if (which is Delphi_Range)
			{
				// TODO: limit values to the given range
				newType = EagleGenerator.TypeEnum.INTEGER;
			}
			else if (which is Delphi_Array)
			{
				Delphi_Array array = (Delphi_Array) which;
				AbstractToken which2 = array.type.baseType.getWhich();
				if (which2 is Delphi_KeywordChoice)
				{
					Delphi_KeywordChoice kw2 = (Delphi_KeywordChoice) which2;
					if (kw2.getValue().ToLower().Equals("string"))
					{
						newType = EagleGenerator.TypeEnum.ARRAY;
					}
				}
			}
			else if (which is Delphi_Identifier_Reference)
			{
				Delphi_Identifier_Reference id = (Delphi_Identifier_Reference) which;
				newType = EagleGenerator.TypeEnum.OTHER;
				userType = id.getValue();
			}

			if (newType == null)
			{
				throw new Exception("Can't handle type yet: " + which);
			}
			return generator.transformType(newType, userType, this);
		}
	}

}
