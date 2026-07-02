// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Picture;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class PLI_Type extends TokenSequence
{
	public @S(10) PLI_BaseType base;
	
	public static class PLI_BaseType extends TokenChooser
	{
		public @CHOICE PunctuationStar XXstar;
		public @CHOICE PLI_KeywordChoice XXbase = new PLI_KeywordChoice(
				"COMPLEX", "FILE", "PRINT", "UNION", "VARYING");

		public @CHOICE PLI_TypePointer XXtypePointer;
		public @CHOICE PLI_TypeCharacter XXtypeCharacter;
		public @CHOICE PLI_TypePicture XXtypePicture;
		public @CHOICE PLI_TypeFixedBinary XXtypeFixedBinary;
		public @CHOICE PLI_TypeFloat XXtypeFloat;
		public @CHOICE PLI_TypeBit XXtypeBit;
		public @CHOICE PLI_TypeGraphic XXtypeGraphic;
		public @CHOICE PLI_TypeEntry XXtypeEntry;
	}

	public static class PLI_TypePointer extends TokenSequence
	{
		public @S(10) PLI_Keyword PTR = new PLI_Keyword("PTR");
		public @S(20) @OPT PLI_Keyword BASED = new PLI_Keyword("BASED");
	}

	public static class PLI_TypeCharacter extends TokenSequence
	{
		public @S(10) @OPT PLI_TypeSize size1;
		public @S(20) PLI_KeywordChoice CHARACTER = new PLI_KeywordChoice("CHAR", "CHARACTER", "WIDECHAR");
		public @S(30) @OPT PLI_TypeSize size2;
		public @S(40) @OPT PLI_KeywordChoice varyingOrStatic = new PLI_KeywordChoice("BASED", "STATIC", "VARYING");
		public @S(50) @OPT PLI_CharInitial initialValue;

		public static class PLI_CharInitial extends TokenSequence
		{
			public @S(10) PLI_KeywordChoice INITIAL = new PLI_KeywordChoice("INITIAL", "INIT");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) PLI_Literal initialValue;
			public @S(40) PunctuationRightParen rightParen;
		}
	}

	public static class PLI_TypePicture extends TokenSequence
	{
		public @S(10) PLI_Keyword PIC = new PLI_Keyword("PIC");
		public @S(20) PLI_Picture picture;
	}

	public static class PLI_TypeFixedBinary extends TokenSequence
	{
		public @S(10) PLI_KeywordChoice FIXED = new PLI_KeywordChoice("FIXED", "FLOAT");
		public @S(20) PLI_KeywordChoice BINARY = new PLI_KeywordChoice("BINARY", "BIN");
		public @S(30) @OPT PLI_TypeSize size;
		public @S(40) @OPT PLI_Keyword COMPLEX = new PLI_Keyword("COMPLEX");
	}

	public static class PLI_TypeFloat extends TokenSequence
	{
		public @S(10) PLI_KeywordChoice FIXED = new PLI_KeywordChoice("FIXED", "FLOAT");
		public @S(20) @OPT PLI_KeywordChoice DECIMAL = new PLI_KeywordChoice("DECIMAL", "DEC");
		public @S(30) @OPT PLI_TypeSize size;
		public @S(40) @OPT PLI_Keyword COMPLEX = new PLI_Keyword("COMPLEX");
	}

	public static class PLI_TypeBit extends TokenSequence
	{
		public @S(10) @OPT PLI_TypeSize size1;
		public @S(20) PLI_Keyword BIT = new PLI_Keyword("BIT");
		public @S(30) @OPT PLI_TypeSize size2;
		public @S(40) @OPT PLI_KeywordChoice alignedOrStatic = new PLI_KeywordChoice("STATIC", "VARYING");
		public @S(50) @OPT PLI_Keyword ALIGNED = new PLI_Keyword("ALIGNED");
		public @S(60) @OPT PLI_BitInitial initialValue;

		public static class PLI_BitInitial extends TokenSequence
		{
			public @S(10) PLI_Keyword INITIAL = new PLI_Keyword("INITIAL");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) PLI_BitLiteral initialValue;
			public @S(40) PunctuationRightParen rightParen;
		}
	}

	public static class PLI_TypeGraphic extends TokenSequence
	{
		public @S(10) PLI_Keyword GRAPHIC = new PLI_Keyword("GRAPHIC");
		public @S(20) @OPT PLI_TypeSize size;
		public @S(30) @OPT PLI_KeywordChoice varyingOrStatic = new PLI_KeywordChoice("VARYING");
	}

	public static class PLI_TypeEntry extends TokenSequence
	{
		public @S(10) PLI_Keyword ENTRY = new PLI_Keyword("ENTRY");
		public @S(20) @OPT TokenList<PLI_TypeEntryDetail> details;

		public static class PLI_TypeEntryDetail extends TokenChooser
		{
			public @CHOICE static class PLI_TypeEntryPtr extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) PLI_Keyword PTR = new PLI_Keyword("PTR");
				public @S(30) PunctuationRightParen rightParen;
			}

			public @CHOICE static class PLI_TypeEntryReturns extends TokenSequence
			{
				public @S(10) PLI_Keyword RETURNS = new PLI_Keyword("RETURNS");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) PLI_Type type;
				public @S(40) PunctuationRightParen rightParen;
			}

			public @CHOICE static class PLI_TypeEntryOptionss extends TokenSequence
			{
				public @S(10) PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
				public @S(20) PunctuationLeftParen leftParen1;
				public @S(30) PLI_Keyword ASM = new PLI_Keyword("ASM");
				public @S(40) PLI_Keyword LINKAGE = new PLI_Keyword("LINKAGE");
				public @S(50) PunctuationLeftParen leftParen2;
				public @S(60) PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
				public @S(70) PunctuationRightParen rightParen2;
				public @S(80) PunctuationRightParen rightParen1;
			}

			public @CHOICE static class PLI_TypeEntryExternal extends TokenSequence
			{
				public @S(10) PLI_Keyword EXTERNAL = new PLI_Keyword("EXTERNAL");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) PLI_Literal literal;
				public @S(40) PunctuationRightParen rightParen;

			}
		}
	}

	public static class PLI_TypeSize extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<PLI_TypeSizeContents, PunctuationComma> typeSizeContents;
		public @S(30) PunctuationRightParen rightParen;

		public static class PLI_TypeSizeContents extends TokenChooser
		{
			public @CHOICE PunctuationStar XXstar;

			public @CHOICE static class PLI_TypeSizeNormal extends TokenSequence
			{
				public @S(10) PLI_Expression size1;
				public @S(20) @OPT PunctuationComma comma;
				public @S(30) @OPT PLI_Expression size2;
			}
		}
	}
	
	public TypeEnum findType()
	{
		AbstractToken which = base.getWhich();
		if (which instanceof PLI_TypeCharacter) return TypeEnum.STRING;
		if (which instanceof PLI_TypeFixedBinary) return TypeEnum.INTEGER;
		return TypeEnum.VOID;
	}
}
