// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.Perl;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_Function_Parameters;
import com.eagle.programmar.Perl.Statements.Perl_ClassStatement;
import com.eagle.programmar.Perl.Statements.Perl_ExpressionStatement;
import com.eagle.programmar.Perl.Statements.Perl_ForEachStatement;
import com.eagle.programmar.Perl.Statements.Perl_ForStatement;
import com.eagle.programmar.Perl.Statements.Perl_IfStatement;
import com.eagle.programmar.Perl.Statements.Perl_NamespaceStatement;
import com.eagle.programmar.Perl.Statements.Perl_StatementBlock;
import com.eagle.programmar.Perl.Statements.Perl_SwitchStatement;
import com.eagle.programmar.Perl.Statements.Perl_TraitStatement;
import com.eagle.programmar.Perl.Statements.Perl_TryStatement;
import com.eagle.programmar.Perl.Statements.Perl_WhileStatement;
import com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_Statement extends TokenChooser
{
	public @FIRST Perl_Comment comment;
	public @CHOICE Perl_Include include;
	public @CHOICE Perl_FunctionDefinition function;
	public @CHOICE Perl_NamespaceStatement namespaceStatement;
	public @CHOICE Perl_ClassStatement classStatement;
	public @CHOICE Perl_Label label;
	public @CHOICE @CURIOUS("Empty statement") PunctuationSemicolon semicolon;
	public @LAST Perl_ExpressionStatement expressionStatement; 
	public @CHOICE Perl_StatementBlock statementBlock;

	public @CHOICE static class Perl_SubDeclaration extends TokenSequence
	{
		public @S(10) Perl_Keyword SUB = new Perl_Keyword("sub");
		public @S(20) @OPT Perl_SubMain main;
		public @S(30) Perl_Function_Definition fnName;
		public @S(40) @OPT Perl_Function_Parameters params;
		public @S(50) Perl_StatementBlock block;

		public static class Perl_SubMain extends TokenSequence
		{
			public @S(10) Perl_Identifier_Reference id;
			public @S(20) Perl_Punctuation quote = new Perl_Punctuation('\'');
		}
	}

	public @CHOICE static class Perl_SimpleStatement extends TokenSequence implements EagleRunnableWithResult
	{
		public @S(10) Perl_StatementList statement;
		public @S(20) @OPT Perl_StatementSuffixModifier modifier;
		public @S(30) @OPT PunctuationSemicolon semicolon;
		public @S(40) @OPT TokenList<Perl_Comment> comments;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			return interpreter.tryToInterpret(statement);
		}
	}

	public @CHOICE static class Perl_CompundStatement extends TokenChooser
	{
		public @CHOICE Perl_ForStatement forStatement;
		public @CHOICE Perl_ForEachStatement forEachStatement;
		public @CHOICE Perl_IfStatement ifStatement;
		public @CHOICE Perl_SwitchStatement switchStatement;
		public @CHOICE Perl_TraitStatement traitStatement;
		public @CHOICE Perl_TryStatement tryStatement;
		public @CHOICE Perl_WhileStatement whileStatement;
	}

}
