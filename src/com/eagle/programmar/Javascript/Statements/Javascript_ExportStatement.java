// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 1, 2026: ES2015 export lists,
// export { a, b as default };  export { a } from "./x";  export * from "./y";  export * as ns from "./z";

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Javascript_ExportStatement extends TokenSequence
{
	public @S(10) Javascript_Keyword EXPORT = new Javascript_Keyword("export");
	public @S(15) @OPT Javascript_Keyword TYPE = new Javascript_Keyword("type"); // TypeScript
	public @S(20) Javascript_ExportWhat what;
	public @S(30) @OPT Javascript_ExportFrom from;
	public @S(40) @OPT PunctuationSemicolon semicolon;

	public static class Javascript_ExportWhat extends TokenChooser
	{
		public @CHOICE Javascript_ExportBraces XXbraces;
		public @CHOICE Javascript_ExportAll XXall;
		public @CHOICE Javascript_ExportDefault XXdefault;
	}

	/** { a, b as default, c as d } */
	public static class Javascript_ExportBraces extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT SeparatedList<Javascript_ExportSpecifier, PunctuationComma> names;
		public @S(30) @OPT PunctuationComma trailingComma;
		public @S(40) PunctuationRightBrace rightBrace;
	}

	public static class Javascript_ExportSpecifier extends TokenSequence
	{
		public @S(10) Javascript_Identifier_Reference id;
		public @S(20) @OPT Javascript_ExportAs exportAs;
	}

	/** as name, or as default */
	public static class Javascript_ExportAs extends TokenSequence
	{
		public @S(10) Javascript_Keyword AS = new Javascript_Keyword("as");
		public @S(20) Javascript_ExportAsWhat what;

		public static class Javascript_ExportAsWhat extends TokenChooser
		{
			public @CHOICE Javascript_KeywordChoice XXdefault = new Javascript_KeywordChoice("default");
			public @CHOICE Javascript_Identifier_Reference XXid;
		}
	}

	/** export default expr; (a default function or class is taken by its own rule first) */
	public static class Javascript_ExportDefault extends TokenSequence
	{
		public @S(10) Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
		public @S(20) Javascript_Expression expr;
	}

	/** * or * as ns, always followed by from */
	public static class Javascript_ExportAll extends TokenSequence
	{
		public @S(10) PunctuationStar star;
		public @S(20) @OPT Javascript_ExportAs exportAs;
	}

	public static class Javascript_ExportFrom extends TokenSequence
	{
		public @S(10) Javascript_Keyword FROM = new Javascript_Keyword("from");
		public @S(20) Javascript_Literal where;
	}
}
