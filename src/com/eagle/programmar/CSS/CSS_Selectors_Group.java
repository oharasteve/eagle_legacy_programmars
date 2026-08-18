// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 17, 2026

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Symbols.CSS_Identifier_Reference;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

// Complete rewrite from this BNF provided by W3Schools

//<selectors_group>          ::= <selector> [ <comma> <selectors_group> ]
//
//<selector>                 ::= <compound_selector> [ <combinator> <selector> ]
//
//<compound_selector>        ::= <type_selector> <subsequent_selector>*
//                             | <universal_selector> <subsequent_selector>*
//                             | <subsequent_selector>+
//
//<subsequent_selector>      ::= <id_selector>
//                             | <class_selector>
//                             | <attribute_selector>
//                             | <pseudo_class>
//
//<type_selector>            ::= [ <namespace_prefix> ] <element_name>
//
//<universal_selector>       ::= [ <namespace_prefix> ] "*"
//
//<namespace_prefix>         ::= [ <ident> | "*" ] "|"
//
//<element_name>             ::= <ident>
//
//<id_selector>              ::= "#" <ident>
//
//<class_selector>           ::= "." <ident>
//
//<attribute_selector>       ::= "[" <ws>* [ <namespace_prefix> ] <ident> <ws>* [ <attr_matcher> <ws>* [ <ident> | <string> ] <ws>* ] "]"
//
//<attr_matcher>             ::= "=" | "~=" | "|=" | "^=" | "$=" | "*="
//
//<pseudo_class>             ::= ":" [ ":" ] <ident> [ "(" <ws>* <pseudo_argument> <ws>* ")" ]
//
//<pseudo_argument>          ::= <ident> | <string> | <integer> | <nth_expression> | <selectors_group>
//
//<combinator>               ::= <ws>+ 
//                             | <ws>* ">" <ws>* 
//                             | <ws>* "+" <ws>* 
//                             | <ws>* "~" <ws>* 
//
//                             | <ws>* "||" <ws>*
//
//<comma>                    ::= <ws>* "," <ws>*
//
//<ident>                    ::= [ "-" ] [ <alpha> | "_" ] [ <alpha> | <digit> | "-" | "_" ]*


//<nth>          ::= <even-odd> | <integer> | <combinable-an-b>
//<even-odd>     ::= "even" | "odd"
//<an-plus-b>    ::= <an> [ <sign> <integer> ]? | <an> | <sign>? <integer>
//<combinable-an-b> ::= <an-plus-b>
//<an>           ::= <integer>? "n"
//<sign>         ::= "+" | "-"
//<integer>      ::= [0-9]+


//pseudo-class ::= 
//    | functional-pseudo-class
//    | other-standard-pseudo-classes  /* e.g., :hover, :focus, :nth-child() */
//
//functional-pseudo-class ::=
//    | is-pseudo
//    | where-pseudo
//    | not-pseudo
//    | has-pseudo                     /* Included for reference (:has()) */
//
//is-pseudo ::= ':is' '(' S* selector-list S* ')'
//where-pseudo ::= ':where' '(' S* selector-list S* ')'
//not-pseudo ::= ':not' '(' S* selector-list S* ')'
//has-pseudo ::= ':has' '(' S* relative-selector-list S* ')'
//
//selector-list ::= 
//    | complex-selector ( S* ',' S* complex-selector )*
//
//relative-selector-list ::= 
//    | relative-selector ( S* ',' S* relative-selector )*
//
//relative-selector ::= 
//    | combinator? complex-selector
//
//complex-selector ::= 
//    | compound-selector ( combinator compound-selector )*
//
//compound-selector ::= 
//    | type-selector? sub-class-selector* pseudo-element* 
//    | universal sub-class-selector* pseudo-element*
//
//sub-class-selector ::= 
//    | ID-selector 
//    | class-selector 
//    | attribute-selector 
//    | pseudo-class
//
//S ::= [ \t\r\n\f]


 
public class CSS_Selectors_Group extends TokenSequence
{
	public @S(10) SeparatedList<CSS_Selector,PunctuationComma> selectors;
	
	public static class CSS_Selector extends TokenSequence
	{
		public @S(10) CSS_CompoundSelector selector;
		public @S(20) @OPT TokenList<CSS_MoreCompoundSelectors> more;
		
		public static class CSS_MoreCompoundSelectors extends TokenSequence
		{
			public @S(10) @OPT CSS_PunctuationChoice combinator = new CSS_PunctuationChoice(">", "+", "~", "||");
			public @S(20) CSS_CompoundSelector selector;
		}
		
		public static class CSS_CompoundSelector extends TokenChooser
		{
			public @CHOICE static class CSS_Compound_Type extends TokenSequence
			{
				public @S(10) @OPT CSS_Punctuation at = new CSS_Punctuation('@');
				public @S(20) CSS_TypeSelector type;
				public @S(30) @OPT TokenList<CSS_SubsequentSelector> more;
			}
			
			public @CHOICE static class CSS_Compound_Subsequent extends TokenSequence
			{
				public @S(10) TokenList<CSS_SubsequentSelector> more;
			}
		}
		
		public static class CSS_IdentOrStar extends TokenChooser
		{
			public @CHOICE CSS_Identifier_Reference XXid;
			public @CHOICE PunctuationStar XXstar;
		}

		public static class CSS_TypeSelector extends TokenSequence
		{
			public @S(10) @OPT CSS_NameSpace_Prefix prefix;
			public @S(20) CSS_IdentOrStar id;
		}
		
		public static class CSS_SubsequentSelector extends TokenChooser
		{
			public @CHOICE CSS_Id_Selector XXid;
			public @CHOICE CSS_Class_Selector XXcls;
			public @CHOICE CSS_Attribute_Selector XXattr;
			public @CHOICE CSS_Pseudo_Class XXpseudo;
			public @CHOICE CSS_KeywordChoice XXMEDIA = new CSS_KeywordChoice(
					".media",
					".media-body");
		}
		
		public static class CSS_NameSpace_Prefix extends TokenSequence
		{
			public @S(10) @OPT CSS_IdentOrStar id;
			public @S(20) CSS_Punctuation bar = new CSS_Punctuation('|');
		}
		
		public static class CSS_Id_Selector extends TokenSequence
		{
			public @S(10) CSS_Punctuation hash = new CSS_Punctuation('#');
			public @S(20) CSS_Identifier_Reference id;
		}
		
		public static class CSS_Class_Selector extends TokenSequence
		{
			public @S(10) PunctuationPeriod dot;
			public @S(20) CSS_Identifier_Reference id;
		}
		
		public static class CSS_Attribute_Selector extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) @OPT CSS_NameSpace_Prefix prefix;
			public @S(30) CSS_Identifier_Reference id;
			public @S(40) @OPT CSS_Attribute_Matcher matcher;
			public @S(50) PunctuationRightBracket rightBracket;
		}
		
		public static class CSS_Attribute_Matcher extends TokenSequence
		{
			public @S(10) CSS_PunctuationChoice match = new CSS_PunctuationChoice(
					"=", "~=", "|=", "^=", "$=", "*=");
			public @S(20) CSS_Attr_Match what;
			
			public static class CSS_Attr_Match extends TokenChooser
			{
				public @CHOICE CSS_Identifier_Reference XXid;
				public @CHOICE CSS_Literal XXstring;
			}
		}
		
		public static class CSS_Pseudo_Class extends TokenSequence
		{
			public @S(10) PunctuationColon colon1;
			public @S(20) @OPT PunctuationColon colon2;
			public @S(30) CSS_Identifier_Reference id;
			public @S(40) @OPT CSS_Pseudo_Class_Arg arg;
			
// This piece is copied over from the original CSS_Taglist which is no longer used
// But it seems that this list might be useful
//			public static class CSS_ColonOption extends TokenSequence
//			{
//				public @S(10) PunctuationColon colon;
//				public @S(20) CSS_KeywordChoice option = new CSS_KeywordChoice("active", "after", "before", "checked",
//						"decrement", "default", "end", "first-child", "focus", "horizontal", "hover", "increment", "last-child",
//						"link", "-moz-any-link", "not", "nth-child", "-o-prefocus", "start", "vertical", "visited",
//						"webkit-any");
//				etc
//			}
			
			public static class CSS_Pseudo_Class_Arg extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) CSS_Pseudo_Argument argument;
				public @S(30) PunctuationRightParen rightParen;
			}
			
			public static class CSS_Pseudo_Argument extends TokenChooser
			{
				public @CHOICE CSS_Identifier_Reference XXid;
				public @CHOICE CSS_Literal XXstring;
				public @CHOICE CSS_Number XXinteger;
				public @CHOICE CSS_Nth_Expression XXnth;
				public @CHOICE CSS_Selectors_Group XXgroup;
			}
			
			public static class CSS_Nth_Expression extends TokenChooser
			{
				public @CHOICE CSS_KeywordChoice XXEVEN = new CSS_KeywordChoice("even", "odd");
				public @CHOICE CSS_Number XXnumber;
				
				public @CHOICE static class CSS_Nth_Expr extends TokenSequence
				{
					public @S(10) @OPT CSS_Number multiplier;
					public @S(20) CSS_Keyword N = new CSS_Keyword("n");
					public @S(30) @OPT CSS_PunctuationChoice PLUS = new CSS_PunctuationChoice("+", "-");
					public @S(40) @OPT CSS_Number number;
				}
			}
		}
	}
}
