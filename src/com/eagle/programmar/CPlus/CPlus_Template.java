// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Function;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class CPlus_Template extends TokenSequence
{
	public @S(10) C_Keyword TEMPLATE = new C_Keyword("template");
	public @S(20) C_Punctuation less = new C_Punctuation("<");
	public @S(30) SeparatedList<CPlus_TemplateElement, PunctuationComma> elements;
	public @S(40) C_Punctuation greater = new C_Punctuation(">");
	public @S(50) @OPT CPlus_TemplateWhat what;

	public static class CPlus_TemplateElement extends TokenSequence
	{
		public @S(10) C_KeywordChoice CLASS = new C_KeywordChoice("class", "typename");
		public @S(20) @OPT CPlus_TemplateEquals equals;
		public @S(30) TokenList<CPlus_TemplateClass> clsList;
	}

	public static class CPlus_TemplateEquals extends TokenSequence
	{
		public @S(10) @OPT C_Variable_Definition var;
		public @S(20) PunctuationEquals equals;
	}

	public static class CPlus_TemplateClass extends TokenChooser
	{
		public @CHOICE CPlus_Class_Definition XXcls;
		public @CHOICE C_Punctuation XXellipsis = new C_Punctuation("...");
		public @FIRST C_Type XXtype;
	}

	public static class CPlus_TemplateWhat extends TokenChooser
	{
		public @CHOICE C_Function XXfunc;
		public @CHOICE CPlus_Operator XXoperator;
		public @CHOICE CPlus_Class XXcls;
	}
}
