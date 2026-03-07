// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class SQL_Type extends TokenChooser
{
	public @CHOICE static class SQL_TypeSet extends TokenSequence
	{
		public @S(10) SQL_Keyword SET = new SQL_Keyword("SET");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<SQL_Expression, PunctuationComma> setValues;
		public @S(40) PunctuationRightParen rightParen;
	}

	public @CHOICE static class SQL_BaseType extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice baseType = new SQL_KeywordChoice("BIGINT", "BLOB", "BOOL", "BOOLEAN", "CHAR",
				"DATE", "DATETIME", "DECIMAL", "DOUBLE", "FLOAT", "INT", "INTEGER", "INTEGER_NOT_NULL", // Huh?
				"LONGVARCHAR", "NCLOB", "NUMBER", "NUMERIC", "NVARCHAR2", "RAW", "SMALLINT", "TEXT", "TIME",
				"TIMESTAMP", "TINYINT", "UNSIGNED", "VARCHAR", "VARCHAR2");
		public @S(20) @OPT SQL_TypeSize size;
	}

	public @FIRST static class SQL_TypeUnisgnedLong extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice UNSIGNED = new SQL_KeywordChoice("UNSIGNED");
		public @S(20) SQL_KeywordChoice LONG = new SQL_KeywordChoice("LONG");
	}

	public @CHOICE static class SQL_TypeEnum extends TokenSequence
	{
		public @S(10) SQL_Keyword ENUM = new SQL_Keyword("ENUM");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<SQL_Expression, PunctuationComma> enumVal;
		public @S(40) PunctuationRightParen rightParen;
	}

	public @CHOICE static class SQL_TypeSize extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SQL_Number size;
		public @S(30) @OPT PunctuationComma comma;
		public @S(40) @OPT SQL_Number size2;
		public @S(50) PunctuationRightParen rightParen;
	}

	// Convert "double" to a SQL_Type representing a double
	public static SQL_Type newPrimitiveType(String name)
	{
		SQL_Type type = new SQL_Type();
		SQL_BaseType base = new SQL_BaseType();
		base.baseType = new SQL_KeywordChoice(name);
		type.setWhich(base);
		return type;
	}

	public static TypeEnum findTypeEnum(SQL_Type type)
	{
		if (type.getWhich() instanceof SQL_BaseType)
		{
			SQL_BaseType base = (SQL_BaseType) type.getWhich();
			switch (base.baseType.getValue().toUpperCase())
			{
			case "BOOL":
			case "BOOLEAN":
			case "TINYINT":
				return TypeEnum.BOOLEAN;
			case "INT":
			case "INTEGER":
				return TypeEnum.INTEGER;
			case "VARCHAR":
			case "VARCHAR2":
				return TypeEnum.STRING;
			case "FLOAT":
			case "DOUBLE":
			case "DECIMAL":
				return TypeEnum.DOUBLE;
			}
		}

		return TypeEnum.OTHER;
	}

	public static AbstractType findAbstractType(EagleGenerator generator, SQL_Type type)
	{
		TypeEnum newType = findTypeEnum(type);
		return generator.transformType(newType, null, null);
	}
}
