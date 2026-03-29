// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using FSharp_Element = com.eagle.programmar.FSharp.FSharp_Element;
	using FSharp_Statement = com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
	using FSharp_Statement_List = com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
	using FSharp_IfElif = com.eagle.programmar.FSharp.Statements.FSharp_IfStatement.FSharp_IfElif;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TerminalStartOfLine = com.eagle.tokens.terminals.TerminalStartOfLine;

	public class FSharp_StartOfLine : TerminalStartOfLine
	{
		private const string TAB = "  ";
		private static readonly int TABLEN = TAB.Length;

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				// Find the enclosing TokenList of statements
				if (parent is TokenList && !(parent is SeparatedList))
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @SuppressWarnings("unchecked") com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken> tokenList = (com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken>) parent;
					TokenList<AbstractToken> tokenList = (TokenList<AbstractToken>) parent;
					if (tokenList.size() == 0)
					{
						break; // First entry always matches
					}

					// The 'elif' clause is an irrelevant TokenList on an 'if' statement
					AbstractToken firstToken = tokenList.first();
					if (!(firstToken is FSharp_IfElif))
					{
						foreach (AbstractToken token in tokenList._elements)
						{
							if (token is FSharp_Comment)
							{
								continue; // Doesn't matter what columns comments are in
							}

							if (token is FSharp_Element)
							{
								FSharp_Element firstStmt = (FSharp_Element) token;
								AbstractToken child = firstStmt.statementOrComment.getWhich();
								if (child is FSharp_Element.FSharp_Statement_List)
								{
									FSharp_Element.FSharp_Statement_List stmtList = (FSharp_Element.FSharp_Statement_List) child;
									FSharp_Element.FSharp_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
									if (_currentChar != otherStmt.getStartChar())
									{
										return false;
									}
									break;
								}
							}
							else
							{
								throw new Exception("Expected a FSharp_Statement, not " + token);
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
				// Find the enclosing TokenList of statements
				if (parent is TokenList && !(parent is SeparatedList))
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @SuppressWarnings("unchecked") com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken> tokenList = (com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken>) parent;
					TokenList<AbstractToken> tokenList = (TokenList<AbstractToken>) parent;

					// The 'elif' clause is an irrelevant TokenList on an 'if' statement
					if (tokenList.size() > 0 && !(tokenList.first() is FSharp_IfElif))
					{
						depth++;
					}
				}
				parent = parent.getParent();
			}

			StringBuilder sb = new StringBuilder(TABLEN * depth);
			for (int i = 1; i < depth; i++)
			{
				sb.Append(TAB);
			}
			return sb.ToString();
		}
	}

}
