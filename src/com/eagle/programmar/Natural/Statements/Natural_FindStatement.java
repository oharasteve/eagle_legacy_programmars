// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Condition;
import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_FindStatement extends TokenSequence
{
	public @S(10) @OPT Natural_Label label;
	public @S(20) @DOC("sm/find.htm") Natural_Keyword FIND = new Natural_Keyword("FIND");
	public @S(30) Natural_FindType findType;
	
	public static class Natural_FindType extends TokenChooser
	{
		public @CHOICE static class Natural_FindNoBlock extends TokenSequence
		{
			public @S(10) Natural_KeywordChoice howMany = new Natural_KeywordChoice(
					"FIRST", "NUMBER", "UNIQUE");
			public @S(20) @OPT Natural_Find_Number_Records numberRecords;
			public @S(30) Natural_Identifier_Reference viewName;
			public @S(40) @OPT Natural_KeywordChoice device = new Natural_KeywordChoice(
					"PHYSICAL", "LOGICAL");
			public @S(50) TokenList<Natural_Find_Clause> clauses;
		}

		public @CHOICE static class Natural_FindWithBlock extends TokenSequence
		{
			public @S(10) @OPT Natural_Find_Number_Records numberRecords;
			public @S(20) Natural_Identifier_Reference viewName;
			public @S(30) @OPT Natural_KeywordChoice device = new Natural_KeywordChoice(
					"PHYSICAL", "LOGICAL");
			public @S(40) TokenList<Natural_Find_Clause> clauses;
			public @S(50) TokenList<Natural_Statement> statements;
			public @S(60) Natural_Keyword ENDFIND = new Natural_Keyword("END-FIND");
		}
	}
	
	public static class Natural_Find_Clause extends TokenChooser
	{
		public @CHOICE Natural_Find_By_Condition findByCond;
		public @CHOICE Natural_Find_By_ISN findByISN;
		public @CHOICE Natural_Find_From findFrom;
		public @CHOICE Natural_Find_Coupled coupled;
		public @CHOICE Natural_Find_Sorted_By sortedBy;
		public @CHOICE Natural_Find_Where findWhere;
	}
	
	public static class Natural_Find_Number_Records extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Natural_Number number;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static class Natural_Find_By_ISN extends TokenSequence
	{
		public @S(10) Natural_Keyword BY = new Natural_Keyword("BY");
		public @S(20) Natural_Identifier_Reference isn;
	}
	
	public static class Natural_Find_Coupled extends TokenSequence
	{
		public @S(10) Natural_KeywordChoice andOr = new Natural_KeywordChoice(
				"AND", "OR");
		public @S(20) Natural_Keyword COUPLED = new Natural_Keyword("COUPLED");
		public @S(30) @OPT Natural_Keyword TO = new Natural_Keyword("TO");
		public @S(40) @OPT Natural_Keyword FILE = new Natural_Keyword("FILE");
		public @S(50) Natural_Identifier_Reference viewName;
		public @S(60) @OPT Natural_Find_Coupled_Via via;
		
		public static class Natural_Find_Coupled_Via extends TokenSequence
		{
			public @S(10) Natural_Keyword VIA = new Natural_Keyword("VIA");
			public @S(20) Natural_Identifier_Reference var1;
			public @S(30) Natural_Find_Via_Equals equals;
			public @S(40) @OPT Natural_Keyword TO = new Natural_Keyword("TO");
			public @S(50) Natural_Identifier_Reference var2;
			
			public static class Natural_Find_Via_Equals extends TokenChooser
			{
				public @CHOICE PunctuationEquals equals;
				public @CHOICE Natural_KeywordChoice EQUALS = new Natural_KeywordChoice(
						"EQ", "EQUAL");
			}
		}
	}
	
	public static class Natural_Find_Sorted_By extends TokenSequence
	{
		public @S(10) @OPT Natural_Keyword SORTED = new Natural_Keyword("SORTED");
		public @S(20) Natural_Keyword BY = new Natural_Keyword("BY");
		public @S(30) TokenList<Natural_Variable> vars;
	}

	public static class Natural_Find_By_Condition extends TokenSequence
	{
		public @S(10) Natural_KeywordChoice byWith = new Natural_KeywordChoice(
				"BY", "WITH");
		public @S(20) @OPT Natural_Find_With_Limit findWithLimit;
		public @S(30) Natural_Condition cond;
		public @S(40) @OPT Natural_Find_Retain retain;
		
		public static class Natural_Find_With_Limit extends TokenSequence
		{
			public @S(10) Natural_Keyword LIMIT = new Natural_Keyword("LIMIT");
			public @S(20) Natural_Find_Number_Records limit;
		}
		
		public static class Natural_Find_Retain extends TokenSequence
		{
			public @S(10) Natural_Keyword RETAIN = new Natural_Keyword("RETAIN");
			public @S(20) Natural_Keyword AS = new Natural_Keyword("AS");
			public @S(30) Natural_Literal literal;
		}
	}
	
	public static class Natural_Find_From extends TokenSequence
	{
		public @S(10) @OPT Natural_Keyword STARTING = new Natural_Keyword("STARTING");
		public @S(20) Natural_Keyword FROM = new Natural_Keyword("FROM");
		public @S(30) Natural_Literal literal;
	}
	
	public static class Natural_Find_Where extends TokenSequence
	{
		public @S(10) Natural_Keyword WHERE = new Natural_Keyword("WHERE");
		public @S(20) Natural_Condition cond;
	}
}
