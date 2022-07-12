// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.FSharp.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_Simple_Statement;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_Statement_List;
import com.eagle.programmar.FSharp.Statements.FSharp_IfStatement.FSharp_IfElif;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TokenList;

public class FSharp_StartOfLine extends TerminalLiteralToken
{
	private static final String TAB = "  ";
	private static final int TABLEN = TAB.length();
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			// Find the enclosing TokenList of statements
			if (parent instanceof TokenList && !(parent instanceof SeparatedList))
			{
				@SuppressWarnings("unchecked")
				TokenList<? extends AbstractToken> tokenList = (TokenList<? extends AbstractToken>) parent;
				if (tokenList.size() == 0) break; // First entry always matches

				// The 'elif' clause is an irrelevant TokenList on an 'if' statement
				AbstractToken firstToken = tokenList.first();
				if (! (firstToken instanceof FSharp_IfElif))
				{
					for (AbstractToken token : tokenList._elements)
					{
//						if (token instanceof FSharp_StartOfLine)
//						{
//							if (_currentLine == token._currentLine) return false;	// Cannot have two SOLN's on the same line
//						}
						
						if (token instanceof FSharp_Comment)
						{
							continue;	// Doesn't matter what columns comments are in
						}
	
						if (token instanceof FSharp_Statement)
						{
							FSharp_Statement firstStmt = (FSharp_Statement) token;
							AbstractToken child = firstStmt.statementOrComment.getWhich();
							if (child instanceof FSharp_Statement_List)
							{
								FSharp_Statement_List stmtList = (FSharp_Statement_List) child;
								FSharp_Simple_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
								//if (_currentLine == otherStmt._currentLine) return false;	// Cannot have two SOLN's on the same line
								if (_currentChar != otherStmt._currentChar) return false;
								break;
							}
						}
						else
						{
							throw new RuntimeException("Expected a FSharp_Statement, not " + token);
						}
					}
					break;
				}
			}
			parent = parent.getParent();
		}
		
//		// This is an error -- the python statement was not inside a TokenList
//		if (parent == null)
//		{
//			throw new RuntimeException("Never found the parent TokenList, at line " + _currentLine);
//		}

		foundIt(_currentLine, _currentChar-1);
		return true;
	}
	
	@Override
	public String toString()
	{
		int depth = 0;
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			// Find the enclosing TokenList of statements
			if (parent instanceof TokenList && !(parent instanceof SeparatedList))
			{
				@SuppressWarnings("unchecked")
				TokenList<? extends AbstractToken> tokenList = (TokenList<? extends AbstractToken>) parent;

				// The 'elif' clause is an irrelevant TokenList on an 'if' statement
				if (tokenList.size() > 0 && ! (tokenList.first() instanceof FSharp_IfElif))
				{
					depth++;
				}
			}
			parent = parent.getParent();
		}

		StringBuffer sb = new StringBuffer(TABLEN * depth);
		for (int i = 1; i < depth; i++) sb.append(TAB);
		return sb.toString();
	}
	
	@Override
	public String showString()
	{
		return "SOLN";
	}

	@Override
	public String getValue()
	{
		return "";
	}
	
	@Override
	public String description()
	{
		return "Start of line";
	}
}
