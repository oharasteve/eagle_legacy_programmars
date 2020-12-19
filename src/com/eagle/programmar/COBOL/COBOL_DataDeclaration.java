// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Index_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Picture;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationStar;

public class COBOL_DataDeclaration extends TokenSequence
{
	public @S(10) @OPT TokenList<COBOL_Comment> comments;
	public @S(20) COBOL_Level level;
	public @S(30) @OPT COBOL_DataFieldName fieldName;
	public @S(40) @OPT TokenList<COBOL_DataClause> clauses;
	public @S(50) PunctuationPeriod dot;
	public @S(60) @OPT COBOL_DataComment comment;
	
	// These are special -- context-sensitive, must have larger (deeper) Level numbers
	public @S(70) @OPT TokenList<COBOL_CopyOrDataDeclaration> children;

	public static class COBOL_DataClause extends TokenChooser
	{
		public @CHOICE COBOL_OccursClause occurs;
		public @CHOICE COBOL_Justified justified;
		public @CHOICE COBOL_KeywordChoice primitive = new COBOL_KeywordChoice(
				"BYTE",
				"CLIPFORMAT",
				"CLSID",
				"COMP",
				"COMP-0",
				"COMP-3",
				"COMP-5",
				"COMP-X",
				"DWORD",
				"FILETIME",
				"FORMATETC",
				"IID",
				"LONG",
				"POINTER",
				"PROCEDURE-POINTER",
				"ULARGE-INTEGER",
				"ULONG",
				"USHORT",
				"VARTYPE",
				"WORD");
		
		public @CHOICE static class COBOL_BlankWhenZero extends TokenSequence
		{
			public @S(10) COBOL_Keyword BLANK = new COBOL_Keyword("BLANK");
			public @S(20) @OPT COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
			public @S(30) COBOL_Keyword ZERO = new COBOL_Keyword("ZERO");
		}
		
		public @CHOICE static class COBOL_Sign extends TokenSequence
		{
			public @S(10) COBOL_Keyword SIGN = new COBOL_Keyword("SIGN");
			public @S(20) COBOL_Keyword TRAILING = new COBOL_Keyword("TRAILING");
			public @S(30) COBOL_Keyword SEPARATE = new COBOL_Keyword("SEPARATE");
		}
		
		public @CHOICE static class COBOL_ObjectReference extends TokenSequence
		{
			public @S(10) COBOL_Keyword OBJECT = new COBOL_Keyword("OBJECT");
			public @S(20) COBOL_Keyword REFERENCE = new COBOL_Keyword("REFERENCE");
		}
		
		public @CHOICE static class COBOL_Typedef extends TokenSequence
		{
			public @S(10) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(20) COBOL_Keyword TYPEDEF = new COBOL_Keyword("TYPEDEF");
		}
		
		public @CHOICE static class COBOL_Usage extends TokenSequence
		{
			public @S(10) COBOL_Keyword USAGE = new COBOL_Keyword("USAGE");
			public @S(20) COBOL_KeywordChoice type = new COBOL_KeywordChoice(
					"1-RECTL",
					"2SIZE",
					"DATA-POINTER",
					"LONG",
					"POINT",
					"TAGMSG",
					"TAGPOINT",
					"TAGRECT",
					"TAGSIZE",
					"UINT",
					"ULONG");
		}
		
		public @CHOICE static class COBOL_PictureClause extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice PIC = new COBOL_KeywordChoice("PIC", "PICTURE");
			public @S(20) COBOL_Picture picture;
		}
		
		public @CHOICE static class COBOL_ValueClause extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice VALUE = new COBOL_KeywordChoice("VALUE", "VALUES");
			public @S(20) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
			public @S(30) TokenList<COBOL_Picture_Value> values;
		}
		
		public @CHOICE static class COBOL_ThruClause extends TokenSequence
		{
			public @S(10) COBOL_Keyword THRU = new COBOL_Keyword("THRU");
			public @S(20) TokenList<COBOL_Picture_Value> values;
		}
		
		public @CHOICE static class COBOL_RedefinesClause extends TokenSequence
		{
			public @S(10) COBOL_Keyword REDEFINES = new COBOL_Keyword("REDEFINES");
			public @S(20) COBOL_Identifier_Reference id;
		}
	}
	
	public static class COBOL_Justified extends TokenSequence
	{
		public @S(10) COBOL_Keyword JUSTIFIED = new COBOL_Keyword("JUSTIFIED");
		public @S(20) COBOL_Keyword RIGHT = new COBOL_Keyword("RIGHT");
	}
	
	public static class COBOL_DataFieldName extends TokenChooser
	{
		public @CHOICE COBOL_Keyword FILLER = new COBOL_Keyword("FILLER");
		public @CHOICE COBOL_Data_Definition id;
	}
	
	public static class COBOL_OccursClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword OCCURS = new COBOL_Keyword("OCCURS");
		public @S(20) COBOL_Expression count;
		public @S(30) @OPT COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
		public @S(40) @OPT COBOL_OccursKey key;
		public @S(50) @OPT COBOL_IndexedBy indexedBy;
		
		public static class COBOL_OccursKey extends TokenSequence
		{
			public @S(10) COBOL_Keyword ASCENDING = new COBOL_Keyword("ASCENDING");
			public @S(20) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference index;
		}
		
		public static class COBOL_IndexedBy extends TokenSequence
		{
			public @S(10) COBOL_Keyword INDEXED = new COBOL_Keyword("INDEXED");
			public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(30) COBOL_Index_Definition index;
		}
	}
	
	public static class COBOL_DataComment extends TokenSequence
	{
		public @S(10) PunctuationStar star;
		public @S(20) COBOL_CommentToEndOfLine comment;
	}
}
