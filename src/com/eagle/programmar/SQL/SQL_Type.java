// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_Type extends TokenChooser
{
	public @CHOICE static class SQL_TypeSet extends TokenSequence
	{
		public @S(10) SQL_Keyword SET = new SQL_Keyword("SET");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<SQL_Expression,PunctuationComma> setValues;
		public @S(40) PunctuationRightParen rightParen;
	}

	public @CHOICE static class SQL_TypeVarChar extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice charType = new SQL_KeywordChoice
		(
			"CHAR",
			"LONGVARCHAR",
			"NVARCHAR2",
			"VARCHAR",
			"VARCHAR2"
		);
		public @S(20) @OPT SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeBoolean extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice BOOLEAN = new SQL_KeywordChoice("BOOLEAN");
	}
	
	public @CHOICE static class SQL_TypeBigInt extends TokenSequence
	{
		public @S(10) SQL_Keyword BIGINT = new SQL_Keyword("BIGINT");
		public @S(20) SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeInt extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice INT = new SQL_KeywordChoice("INT", "INTEGER", "NUMBER", "TINYINT", "SMALLINT");
		public @S(20) @OPT SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeDouble extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice DOUBLE = new SQL_KeywordChoice("FLOAT", "DOUBLE");
	}
	
	public @CHOICE static class SQL_TypeRaw extends TokenSequence
	{
		public @S(10) SQL_Keyword RAW = new SQL_Keyword("RAW");
		public @S(20) SQL_TypeSize size;
	}

	public @CHOICE static class SQL_TypeBlob extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice BLOB = new SQL_KeywordChoice("BLOB", "NCLOB");
	}
	
	public @CHOICE static class SQL_TypeText extends TokenSequence
	{
		public @S(10) SQL_Keyword TEXT = new SQL_Keyword("TEXT");
	}

	public @CHOICE static class SQL_TypeDate extends TokenSequence
	{
		public @S(10) SQL_KeywordChoice DATE = new SQL_KeywordChoice("DATE", "TIME", "DATETIME");
	}

	public @CHOICE static class SQL_TypeTimeStamp extends TokenSequence
	{
		public @S(10) SQL_Keyword TIMESTAMP = new SQL_Keyword("TIMESTAMP");
	}

	public @CHOICE static class SQL_TypeDecimal extends TokenSequence
	{
		public @S(10) SQL_Keyword DECIMAL = new SQL_Keyword("DECIMAL");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SQL_Number size;
		public @S(40) PunctuationComma comma;
		public @S(50) SQL_Number size2;
		public @S(60) PunctuationRightParen rightParen;
	}

	public @CHOICE static class SQL_TypeEnum extends TokenSequence
	{
		public @S(10) SQL_Keyword ENUM = new SQL_Keyword("ENUM");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<SQL_Expression,PunctuationComma> enumVal;
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
}
