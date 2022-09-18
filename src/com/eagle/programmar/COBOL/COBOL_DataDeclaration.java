// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Picture.COBOL_BlankWhenZero;
import com.eagle.programmar.COBOL.Picture.COBOL_ObjectReference;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Picture.COBOL_RedefinesClause;
import com.eagle.programmar.COBOL.Picture.COBOL_Sign;
import com.eagle.programmar.COBOL.Picture.COBOL_ThruClause;
import com.eagle.programmar.COBOL.Picture.COBOL_Typedef;
import com.eagle.programmar.COBOL.Picture.COBOL_Usage;
import com.eagle.programmar.COBOL.Picture.COBOL_ValueClause;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Index_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
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
		
		public @CHOICE COBOL_BlankWhenZero blankWhenZero; 
		public @CHOICE COBOL_Justified justified;
		public @CHOICE COBOL_ObjectReference objectReference;
		public @CHOICE COBOL_OccursClause occurs;
		public @CHOICE COBOL_PictureClause pictureClause;
		public @CHOICE COBOL_RedefinesClause redefinesClause;
		public @CHOICE COBOL_Sign sign;
		public @CHOICE COBOL_ThruClause thruClause;
		public @CHOICE COBOL_Type type;
		public @CHOICE COBOL_Typedef typedef;
		public @CHOICE COBOL_Usage usage;
		public @CHOICE COBOL_ValueClause valueClause;
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
	
	public static class COBOL_Type extends TokenChooser
	{
		public @CHOICE COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
		public @CHOICE COBOL_Literal type;
	}
	
	public static class COBOL_OccursClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword OCCURS = new COBOL_Keyword("OCCURS");
		public @S(20) COBOL_Expression count;
		public @S(30) @OPT COBOL_OccursTo to;
		public @S(40) @OPT COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
		public @S(50) @OPT COBOL_Depending depends;
		public @S(60) @OPT COBOL_OccursKey key;
		public @S(70) @OPT COBOL_IndexedBy indexedBy;
		
		public static class COBOL_Depending extends TokenSequence
		{
			public @S(10) COBOL_Keyword DEPENDING = new COBOL_Keyword("DEPENDING");
			public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
			public @S(30) COBOL_Identifier_Reference index;
		}
		
		public static class COBOL_OccursTo extends TokenSequence
		{
			public @S(10) COBOL_Keyword TO = new COBOL_Keyword("TO");
			public @S(20) COBOL_Expression count;
		}
		
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
