// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Haskell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement.Haskell_Statement;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock.Haskell_MultilineStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock.Haskell_SameLineStatement;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.terminals.TerminalStartOfLine;

public class Haskell_StartOfLine extends TerminalStartOfLine
{
	private static final String TAB = "  ";
	private static final int TABLEN = TAB.length();

	private static final boolean DEBUG = false;

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (DEBUG)
				System.out.println("**** Parent is " + (parent.getStartLine() + 1) + "/" + (parent.getStartChar() + 1));
			// Find the enclosing statement block
			if (parent instanceof Haskell_StatementBlock)
			{
				Haskell_StatementBlock block = (Haskell_StatementBlock) parent;
				if (block.getWhich() instanceof Haskell_MultilineStatement)
				{
					Haskell_MultilineStatement multi = (Haskell_MultilineStatement) block.getWhich();
					if (DEBUG) System.out.println("**** Found a Haskell_MultilineStatement");
					TokenList<? extends AbstractToken> tokenList = multi.statements;
					if (tokenList.size() == 0) break; // First entry always matches

					// Find first non-comment statement
					for (AbstractToken token : tokenList._elements)
					{
						if (DEBUG) System.out.println(
								"**** Token is " + (token.getStartLine() + 1) + "/" + (token.getStartChar() + 1));
						if (token instanceof Haskell_Comment)
						{
							continue; // Doesn't matter what columns comments are in
						}

						Haskell_ComplexStatement firstStmt = (Haskell_ComplexStatement) token;
						AbstractToken child = firstStmt.statementOrComment.getWhich();
						if (child instanceof Haskell_SameLineStatement)
						{
							Haskell_SameLineStatement stmtList = (Haskell_SameLineStatement) child;
							Haskell_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
							/////// The KEY Line /////// Who doesn't like Key Lime pie?
							if (_currentChar != otherStmt.getStartChar())
							{
								if (DEBUG) System.out.println("**** FAIL: Comparing " +
										(_currentLine + 1) + "/" + (_currentChar + 1) + " to " +
										(otherStmt.getStartLine() + 1) + "/" + (otherStmt.getStartChar() + 1));
								return false;
							}
							if (DEBUG) System.out.println("**** MATCH: Comparing " +
									(_currentLine + 1) + "/" + (_currentChar + 1) + " to " +
									(otherStmt.getStartLine() + 1) + "/" + (otherStmt.getStartChar() + 1));
							break;
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
			if (DEBUG) System.out.println("**** Parent " + parent.getClass().getSimpleName() +
					" at " + (parent.getStartLine() + 1) + "/" + (parent.getStartChar() + 1));

			// Find the enclosing statement block(s)
			if (parent instanceof Haskell_MultilineStatement)
			{
				depth++;
			}
			if (DEBUG) System.out.println("     Token " + this.getClass().getSimpleName() +
					" at " + (getStartLine() + 1) + "/" + (getStartChar() + 1) + " depth=" + depth);
			parent = parent.getParent();
		}

		// Might be a tad faster with the 'switch'. It is not needed.
		switch (depth)
		{
		case 0:
			return "";
		case 1:
			return TAB;
		case 2:
			return TAB + TAB;
		case 3:
			return TAB + TAB + TAB;
		case 4:
			return TAB + TAB + TAB + TAB;
		}
		StringBuffer sb = new StringBuffer(TABLEN * depth);
		for (int i = 0; i < depth; i++) sb.append(TAB);
		return sb.toString();
	}
}
