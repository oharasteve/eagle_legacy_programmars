// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

namespace com.eagle.programmar.Python.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Statement = com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
	using Python_StatementBlock = com.eagle.programmar.Python.Statements.Python_StatementBlock;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_SameLineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TerminalStartOfLine = com.eagle.tokens.terminals.TerminalStartOfLine;

	public class Python_StartOfLine : TerminalStartOfLine
	{
		private const string TAB = "  ";
		private static readonly int TABLEN = TAB.Length;

		private const bool DEBUG = false;

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (DEBUG)
				{
					Console.WriteLine("**** Parent is " + (parent.getStartLine() + 1) + "/" + (parent.getStartChar() + 1));
				}
				// Find the enclosing statement block
				if (parent is Python_StatementBlock)
				{
					Python_StatementBlock block = (Python_StatementBlock) parent;
					if (block.getWhich() is Python_StatementBlock.Python_MultilineStatement)
					{
						Python_StatementBlock.Python_MultilineStatement multi = (Python_StatementBlock.Python_MultilineStatement) block.getWhich();
						if (DEBUG)
						{
							Console.WriteLine("**** Found a Python_MultilineStatement");
						}
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken> tokenList = multi.statements;
						TokenList<AbstractToken> tokenList = multi.statements;
						if (tokenList.size() == 0)
						{
							break; // First entry always matches
						}

						// Find first non-comment statement
						foreach (AbstractToken token in tokenList._elements)
						{
							if (DEBUG)
							{
								Console.WriteLine("**** Token is " + (token.getStartLine() + 1) + "/" + (token.getStartChar() + 1));
							}
							if (token is Python_Comment)
							{
								continue; // Doesn't matter what columns comments are in
							}

							Python_ComplexStatement firstStmt = (Python_ComplexStatement) token;
							AbstractToken child = firstStmt.statementOrComment.getWhich();
							if (child is Python_StatementBlock.Python_SameLineStatement)
							{
								Python_StatementBlock.Python_SameLineStatement stmtList = (Python_StatementBlock.Python_SameLineStatement) child;
								Python_ComplexStatement.Python_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
								/////// The KEY Line /////// Who doesn't like Key Lime pie?
								if (_currentChar != otherStmt.getStartChar())
								{
									if (DEBUG)
									{
										Console.WriteLine("**** FAIL: Comparing " + (_currentLine + 1) + "/" + (_currentChar + 1) + " to " + (otherStmt.getStartLine() + 1) + "/" + (otherStmt.getStartChar() + 1));
									}
									return false;
								}
								if (DEBUG)
								{
									Console.WriteLine("**** MATCH: Comparing " + (_currentLine + 1) + "/" + (_currentChar + 1) + " to " + (otherStmt.getStartLine() + 1) + "/" + (otherStmt.getStartChar() + 1));
								}
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

		public override string ToString()
		{
			int depth = 0;
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (DEBUG)
				{
					Console.WriteLine("**** Parent " + parent.GetType().Name + " at " + (parent.getStartLine() + 1) + "/" + (parent.getStartChar() + 1));
				}

				// Find the enclosing statement block(s)
				if (parent is Python_StatementBlock.Python_MultilineStatement)
				{
					depth++;
				}
				if (DEBUG)
				{
					Console.WriteLine("     Token " + this.GetType().Name + " at " + (getStartLine() + 1) + "/" + (getStartChar() + 1) + " depth=" + depth);
				}
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
			StringBuilder sb = new StringBuilder(TABLEN * depth);
			for (int i = 0; i < depth; i++)
			{
				sb.Append(TAB);
			}
			return sb.ToString();
		}
	}

}
