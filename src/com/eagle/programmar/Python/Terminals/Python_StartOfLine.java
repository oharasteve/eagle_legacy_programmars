// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_MultilineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_SameLineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Statements.Python_IfStatement.Python_IfElif;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.terminals.TerminalStartOfLine;

public class Python_StartOfLine extends TerminalStartOfLine
{
	private static final String TAB = "  ";
	private static final int TABLEN = TAB.length();
	
	protected static final boolean DEBUG = false;

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (DEBUG) System.out.println("**** Parent is " + (parent.getStartLine()+1) + "/" + (parent.getStartChar()+1));
			// Find the enclosing TokenList of statements
			if (parent instanceof Python_MultilineStatement) ////// TokenList && !(parent instanceof SeparatedList))
			{
				Python_MultilineStatement multi = (Python_MultilineStatement) parent;
				if (DEBUG) System.out.println("**** Parent is a Python_MultilineStatement");
				TokenList<? extends AbstractToken> tokenList = multi.statements; /////////(TokenList<? extends AbstractToken>) parent;
				if (tokenList.size() == 0) break; // First entry always matches
				if (DEBUG) System.out.println("**** size is not zero");
				
				// The 'elif' clause is an irrelevant TokenList on an 'if' statement
				AbstractToken firstToken = tokenList._elements.get(0);
				if (!(firstToken instanceof Python_IfElif))
				{
					// Find first non-comment statement
					for (AbstractToken token : tokenList._elements)
					{
						if (DEBUG) System.out.println("**** Token is " + (token.getStartLine()+1) + "/" + (token.getStartChar()+1));
						if (token instanceof Python_Comment)
						{
							continue; // Doesn't matter what columns comments are in
						}

						if (token instanceof Python_Statement)
						{
							Python_Statement firstStmt = (Python_Statement) token;
							AbstractToken child = firstStmt.statementOrComment.getWhich();
							if (DEBUG) System.out.println("**** Comparing with " + (child.getStartLine()+1) + "/" + (child.getStartChar()+1));
							if (child instanceof Python_SameLineStatement)
							{
								Python_SameLineStatement stmtList = (Python_SameLineStatement) child;
								Python_Simple_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
								/////// The KEY Line /////// Who doesn't like Key Lime pie?
								if (_currentChar != otherStmt.getStartChar())
								{
									if (DEBUG) System.out.println("******* FAIL: Comparing " + (_currentLine+1) + "/" + (_currentChar+1) + " to " + (otherStmt.getStartLine()+1) + "/" + (otherStmt.getStartChar()+1));
									return false;
								}
								if (DEBUG) System.out.println("******* MATCH: Comparing " + (_currentLine+1) + "/" + (_currentChar+1) + " to " + (otherStmt.getStartLine()+1) + "/" + (otherStmt.getStartChar()+1));
								break; // Does another break below, and drops into foundIt()
							}
						}
						else
						{
							throw new RuntimeException("Expected a Python_Statement, not " + token);
						}
					}
					break;
				}
			}
			parent = parent.getParent();
		}

		foundIt(_currentLine, _currentChar - 1);
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
				if (tokenList.size() > 0 && !(tokenList.first() instanceof Python_IfElif))
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
}
