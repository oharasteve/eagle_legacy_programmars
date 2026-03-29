// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2011

namespace com.eagle.programmar.COBOL.Terminals
{
	using COBOL_DataDeclaration = com.eagle.programmar.COBOL.COBOL_DataDeclaration;
	using COBOL_CopyOrDataDeclaration = com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TerminalLevelToken = com.eagle.tokens.terminals.TerminalLevelToken;

	public class COBOL_Level : TerminalLevelToken
	{
		protected internal override bool validateLevel()
		{
			// All of this used to be post-processed. No more.
			AbstractToken parent = this.getParent();

			if (parent is COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration decl = (COBOL_DataDeclaration) this.getParent();
				if (!(decl.getParent() is COBOL_CopyOrDataDeclaration))
				{
					throw new Exception("Expected COBOL_CopyOrDataDeclaration, not " + decl.getParent());
				}
				COBOL_CopyOrDataDeclaration copyData = (COBOL_CopyOrDataDeclaration) decl.getParent();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @SuppressWarnings("unchecked") com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken> siblings = (com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken>) copyData.getParent();
				TokenList<AbstractToken> siblings = (TokenList<AbstractToken>) copyData.getParent();
				if (DEBUG)
				{
					Console.WriteLine((_currentLine + 1) + " siblings.size() = " + siblings.size());
				}
				if (DEBUG)
				{
					Console.WriteLine((_currentLine + 1) + " parent of TokenList is " + siblings.getParent());
				}

				// Don't bother checking top-level elements. Doesn't matter about siblings with
				// no parents.
				if (DEBUG)
				{
					Console.WriteLine((_currentLine + 1) + " parent of copyData is " + copyData.getParent());
				}
				if (copyData.getParent() is COBOL_DataDeclaration)
				{
					if (siblings.size() > 0)
					{
						if (!validateLevelSiblings(siblings))
						{
							return false;
						}
					}
				}
				else
				{
					if (!validateLevelParents(copyData))
					{
						return false;
					}
				}
			}
			// Otherwise probably a Screen or Report entry. Ignore the test for now.

			// Passed all the tests!
			return true;
		}

		private bool validateLevelSiblings<T1>(TokenList<T1> siblings) where T1 : com.eagle.tokens.AbstractToken
		{
			foreach (AbstractToken sibling in siblings._elements)
			{
				// Context-sensitive check. Level must be = to a sister level
				COBOL_CopyOrDataDeclaration otherDeclParent = (COBOL_CopyOrDataDeclaration) sibling;
				AbstractToken which = otherDeclParent.getWhich();
				if (which is COBOL_DataDeclaration)
				{
					if (DEBUG)
					{
						Console.WriteLine((_currentLine + 1) + " found a sibling");
					}
					COBOL_DataDeclaration otherDecl = (COBOL_DataDeclaration) which;
					if (DEBUG)
					{
						Console.WriteLine((_currentLine + 1) + " same: if " + _level + " != " + otherDecl.level._level + " -> fail");
					}
					if (_level != otherDecl.level._level)
					{
						return false;
					}
					break; // Only need to check one -- all siblings have the same level
				}
			}
			return true;
		}

		private bool validateLevelParents(COBOL_CopyOrDataDeclaration copyData)
		{
			AbstractToken parent = copyData.getParent();
			while (parent != null)
			{
				if (parent is COBOL_CopyOrDataDeclaration)
				{
					if (DEBUG)
					{
						Console.WriteLine((_currentLine + 1) + " found a parent COBOL_CopyOrDataDeclaration");
					}
					if (_level == 77 || _level == 78)
					{
						return false; // 77 is always at the top
					}
					if (_level == 88)
					{
						return true; // 88 can be anyplace
					}

					// Context-sensitive check. Level cannot be <= a parent level
					COBOL_CopyOrDataDeclaration otherCopyDecl = (COBOL_CopyOrDataDeclaration) parent;
					AbstractToken which = otherCopyDecl.getWhich();
					if (which is COBOL_DataDeclaration)
					{
						COBOL_DataDeclaration otherDecl = (COBOL_DataDeclaration) which;
						if (otherDecl.level._level != 77 && otherDecl.level._level != 78) // 77 can be followed by an 01
						{
							if (otherDecl.level._level == 88)
							{
								return true; // Always match an 88
							}
							if (DEBUG)
							{
								Console.WriteLine((_currentLine + 1) + " under: if " + _level + " <= " + otherDecl.level._level + " -> fail");
							}
							if (_level <= otherDecl.level._level)
							{
								return false;
							}
						}
					}
					else
					{
						throw new Exception("Expected COBOL_DataDeclaration, not " + which);
					}
					break;
				}
				parent = parent.getParent();
			}
			return true;
		}

		public override string ToString()
		{
			if (_level >= 100 || _level <= 0)
			{
				return "Err";
			}
			if (_level >= 10)
			{
				return Convert.ToString(_level);
			}
			return '0' + Convert.ToString(_level);
		}

		public override string description()
		{
			return "COBOL level number, such as 01, 05 or 77.";
		}
	}

}
