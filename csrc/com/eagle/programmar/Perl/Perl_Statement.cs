// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

namespace com.eagle.programmar.Perl
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Perl_Function_Parameters = com.eagle.programmar.Perl.Perl_Function.Perl_Function_Parameters;
	using Perl_ClassStatement = com.eagle.programmar.Perl.Statements.Perl_ClassStatement;
	using Perl_ExpressionStatement = com.eagle.programmar.Perl.Statements.Perl_ExpressionStatement;
	using Perl_ForEachStatement = com.eagle.programmar.Perl.Statements.Perl_ForEachStatement;
	using Perl_ForStatement = com.eagle.programmar.Perl.Statements.Perl_ForStatement;
	using Perl_IfStatement = com.eagle.programmar.Perl.Statements.Perl_IfStatement;
	using Perl_NamespaceStatement = com.eagle.programmar.Perl.Statements.Perl_NamespaceStatement;
	using Perl_StatementBlock = com.eagle.programmar.Perl.Statements.Perl_StatementBlock;
	using Perl_SwitchStatement = com.eagle.programmar.Perl.Statements.Perl_SwitchStatement;
	using Perl_TraitStatement = com.eagle.programmar.Perl.Statements.Perl_TraitStatement;
	using Perl_TryStatement = com.eagle.programmar.Perl.Statements.Perl_TryStatement;
	using Perl_WhileStatement = com.eagle.programmar.Perl.Statements.Perl_WhileStatement;
	using Perl_Function_Definition = com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
	using Perl_Identifier_Reference = com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
	using Perl_Comment = com.eagle.programmar.Perl.Terminals.Perl_Comment;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Perl_Comment XXcomment;
		public Perl_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_Include XXinclude;
		public Perl_Include XXinclude;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_Function XXfunction;
		public Perl_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_NamespaceStatement XXnamespaceStatement;
		public Perl_NamespaceStatement XXnamespaceStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ClassStatement XXclassStatement;
		public Perl_ClassStatement XXclassStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_Label XXlabel;
		public Perl_Label XXlabel;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Empty statement") com.eagle.tokens.punctuation.PunctuationSemicolon XXsemicolon;
		public @CURIOUS("Empty statement") PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Perl_ExpressionStatement XXexpressionStatement;
		public Perl_ExpressionStatement XXexpressionStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_StatementBlock XXstatementBlock;
		public Perl_StatementBlock XXstatementBlock;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_SubDeclaration extends com.eagle.tokens.TokenSequence
		public static class Perl_SubDeclaration extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword SUB = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("sub");
			public Perl_Keyword SUB = new Perl_Keyword("sub");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_SubMain main;
			public @OPT Perl_SubMain main;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Symbols.Perl_Function_Definition fnName;
			public Perl_Function_Definition fnName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Perl_Function_Parameters params;
			public @OPT Perl_Function_Parameters @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Perl.Statements.Perl_StatementBlock block;
			public Perl_StatementBlock block;

			public static class Perl_SubMain extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
				public Perl_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation quote = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\'');
				public Perl_Punctuation quote = new Perl_Punctuation('\'');
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_SimpleStatement extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnableWithResult, com.eagle.transform.EagleTransformableStatementList
		public static class Perl_SimpleStatement extends TokenSequence implements EagleRunnableWithResult, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Perl_StatementList statement;
			public Perl_StatementList statement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_StatementSuffixModifier modifier;
			public @OPT Perl_StatementSuffixModifier modifier;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationSemicolon semicolon;
			public @OPT PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments;
			public @OPT TokenList<Perl_Comment> comments;

			public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				return interpreter.tryToInterpret(statement);
			}

			public List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				return transformer.transformStatement(generator, statement.getWhich());
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_CompundStatement extends com.eagle.tokens.TokenChooser
		public static class Perl_CompundStatement extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ForStatement XXforStatement;
			public Perl_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ForEachStatement XXforEachStatement;
			public Perl_ForEachStatement XXforEachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_IfStatement XXifStatement;
			public Perl_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_SwitchStatement XXswitchStatement;
			public Perl_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_TraitStatement XXtraitStatement;
			public Perl_TraitStatement XXtraitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_TryStatement XXtryStatement;
			public Perl_TryStatement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_WhileStatement XXwhileStatement;
			public Perl_WhileStatement XXwhileStatement;
		}
	}

}
