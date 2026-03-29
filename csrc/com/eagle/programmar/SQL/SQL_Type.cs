// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL
{
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using SQL_Number = com.eagle.programmar.SQL.Terminals.SQL_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class SQL_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_TypeSet extends com.eagle.tokens.TokenSequence
		public class SQL_TypeSet : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword SET = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SET");
			public SQL_Keyword SET = new SQL_Keyword("SET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<SQL_Expression, com.eagle.tokens.punctuation.PunctuationComma> setValues;
			public SeparatedList<SQL_Expression, PunctuationComma> setValues;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_BaseType extends com.eagle.tokens.TokenSequence
		public class SQL_BaseType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice baseType = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("BIGINT", "BLOB", "BOOL", "BOOLEAN", "CHAR", "DATE", "DATETIME", "DECIMAL", "DOUBLE", "FLOAT", "INT", "INTEGER", "INTEGER_NOT_NULL", "LONGVARCHAR", "NCLOB", "NUMBER", "NUMERIC", "NVARCHAR2", "RAW", "SMALLINT", "TEXT", "TIME", "TIMESTAMP", "TINYINT", "UNSIGNED", "VARCHAR", "VARCHAR2");
			public SQL_KeywordChoice baseType = new SQL_KeywordChoice("BIGINT", "BLOB", "BOOL", "BOOLEAN", "CHAR", "DATE", "DATETIME", "DECIMAL", "DOUBLE", "FLOAT", "INT", "INTEGER", "INTEGER_NOT_NULL", "LONGVARCHAR", "NCLOB", "NUMBER", "NUMERIC", "NVARCHAR2", "RAW", "SMALLINT", "TEXT", "TIME", "TIMESTAMP", "TINYINT", "UNSIGNED", "VARCHAR", "VARCHAR2");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_TypeSize size;
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class SQL_TypeUnisgnedLong extends com.eagle.tokens.TokenSequence
		public class SQL_TypeUnisgnedLong : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice UNSIGNED = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("UNSIGNED");
			public SQL_KeywordChoice UNSIGNED = new SQL_KeywordChoice("UNSIGNED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice LONG = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("LONG");
			public SQL_KeywordChoice LONG = new SQL_KeywordChoice("LONG");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_TypeEnum extends com.eagle.tokens.TokenSequence
		public class SQL_TypeEnum : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword ENUM = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ENUM");
			public SQL_Keyword ENUM = new SQL_Keyword("ENUM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<SQL_Expression, com.eagle.tokens.punctuation.PunctuationComma> enumVal;
			public SeparatedList<SQL_Expression, PunctuationComma> enumVal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_TypeSize extends com.eagle.tokens.TokenSequence
		public class SQL_TypeSize : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Number size;
			public SQL_Number size;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationComma comma;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SQL_Number size2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		// Convert "double" to a SQL_Type representing a double
		public static SQL_Type newPrimitiveType(string name)
		{
			SQL_Type type = new SQL_Type();
			SQL_BaseType @base = new SQL_BaseType();
			@base.baseType = new SQL_KeywordChoice(name);
			type.setWhich(@base);
			return type;
		}

		public static EagleGenerator.TypeEnum findTypeEnum(SQL_Type type)
		{
			if (type.getWhich() is SQL_BaseType)
			{
				SQL_BaseType @base = (SQL_BaseType) type.getWhich();
				switch (@base.baseType.getValue().ToUpper())
				{
				case "BOOL":
				case "BOOLEAN":
				case "TINYINT":
					return EagleGenerator.TypeEnum.BOOLEAN;
				case "INT":
				case "INTEGER":
					return EagleGenerator.TypeEnum.INTEGER;
				case "VARCHAR":
				case "VARCHAR2":
					return EagleGenerator.TypeEnum.STRING;
				case "FLOAT":
				case "DOUBLE":
				case "DECIMAL":
					return EagleGenerator.TypeEnum.DOUBLE;
				}
			}

			return EagleGenerator.TypeEnum.OTHER;
		}

		public static AbstractType findAbstractType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, SQL_Type type)
		{
			EagleGenerator.TypeEnum newType = findTypeEnum(type);
			return generator.transformType(newType, null, null);
		}
	}

}
