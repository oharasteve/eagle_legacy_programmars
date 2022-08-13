// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_DisplayParameter extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Natural_DisplayParameterContents contents;
	public @S(30) PunctuationRightParen rightParen;
	
	public static class Natural_DisplayParameterContents extends TokenChooser
	{
		public @CHOICE Natural_DisplayParametersAD parameterAD;
		public @CHOICE Natural_DisplayParametersCD parameterCD;

		public @CHOICE static class NaturalDisplayParameterFieldRepresentation extends TokenSequence
		{
			public @S(10) Natural_Keyword AD = new Natural_Keyword("AD");
			public @S(20) PunctuationEquals equals;
			public @S(30) Natural_DisplayParametersAD parameters;
		}

		public @CHOICE static class NaturalDisplayParameterColorDefinition extends TokenSequence
		{
			public @S(10) Natural_Keyword CD = new Natural_Keyword("CD");
			public @S(20) PunctuationEquals equals;
			public @S(30) Natural_DisplayParametersCD parameters;
		}

		public @CHOICE static class NaturalDisplayParameterPrintMode extends TokenSequence
		{
			public @S(10) Natural_Keyword PM = new Natural_Keyword("PM");
			public @S(20) PunctuationEquals equals;
			public @S(30) Natural_DisplayParametersPM parameters;
		}
	}
	
	public static class Natural_DisplayParametersAD extends TokenSequence
	{
		public @S(10) Natural_KeywordChoice param = new Natural_KeywordChoice(
				"B", "C", "D", "I", "N", "U", "V");
	}
	
	public static class Natural_DisplayParametersCD extends TokenSequence
	{
		public @S(10) Natural_KeywordChoice param = new Natural_KeywordChoice(
				"BL", "GR", "NE", "PI", "RE", "TU", "YE");
	}

	public static class Natural_DisplayParametersPM extends TokenSequence
	{
		public @S(10) Natural_KeywordChoice param = new Natural_KeywordChoice(
				"C", "D", "I", "N");
	}
}