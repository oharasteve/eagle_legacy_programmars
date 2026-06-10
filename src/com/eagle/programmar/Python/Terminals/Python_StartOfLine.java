// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.terminals.TerminalStartOfLine;

public class Python_StartOfLine extends TerminalStartOfLine
{
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
			if (parent instanceof Python_StatementBlock)
			{
				Python_StatementBlock block = (Python_StatementBlock) parent;
				if (block.getWhich() instanceof Python_MultilineStatement)
				{
					Python_MultilineStatement multi = (Python_MultilineStatement) block.getWhich();
					if (DEBUG) System.out.println("**** Found a Python_MultilineStatement");
					TokenList<? extends AbstractToken> tokenList = multi.statements;
					if (tokenList.size() == 0) break; // First entry always matches

					// Find first non-comment statement
					for (AbstractToken token : tokenList._elements)
					{
						if (DEBUG) System.out.println(
								"**** Token is " + (token.getStartLine() + 1) + "/" + (token.getStartChar() + 1));
						if (token instanceof Python_Comment)
						{
							continue; // Doesn't matter what columns comments are in
						}

						Python_ComplexStatement firstStmt = (Python_ComplexStatement) token;
						AbstractToken child = firstStmt.statementOrComment.getWhich();
						if (child instanceof Python_SameLineStatement)
						{
							Python_SameLineStatement stmtList = (Python_SameLineStatement) child;
							Python_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
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
	protected boolean goDeeper(AbstractToken parent)
	{
		return parent instanceof Python_MultilineStatement;
	}
}
