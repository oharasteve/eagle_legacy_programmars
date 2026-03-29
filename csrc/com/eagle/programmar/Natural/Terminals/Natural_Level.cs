// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using Natural_DDM_Line = com.eagle.programmar.Natural.Natural_DDM.Natural_DDM_Line;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TerminalLevelToken = com.eagle.tokens.terminals.TerminalLevelToken;

	public class Natural_Level : TerminalLevelToken
	{
		protected internal override bool validateLevel()
		{
			if (this.getParent() is Natural_DDM_Line)
			{
				Natural_DDM_Line decl = (Natural_DDM_Line) this.getParent();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @SuppressWarnings("unchecked") com.eagle.tokens.TokenList<com.eagle.tokens.AbstractToken> previous = (com.eagle.tokens.TokenList<com.eagle.tokens.AbstractToken>) decl.getParent();
				TokenList<AbstractToken> previous = (TokenList<AbstractToken>) decl.getParent();
				if (previous.size() > 0)
				{
					// Context-sensitive check. Level must be <= a sister level
					Natural_DDM_Line otherDecl = (Natural_DDM_Line) previous.first();
					if (_level < otherDecl.Level._level)
					{
						return false;
					}
				}
				else
				{
					AbstractToken grandParent = previous.getParent();
					if (grandParent != null && grandParent is Natural_DDM_Line)
					{
						// Context-sensitive check. Level cannot be <= a parent level
						Natural_DDM_Line otherDecl = (Natural_DDM_Line) grandParent;
						if (_level <= otherDecl.Level._level)
						{
							return false;
						}
					}
				}
			}

			// Passed all the tests!
			return true;
		}

		public override string description()
		{
			return "Natural DDM level number.";
		}
	}

}
