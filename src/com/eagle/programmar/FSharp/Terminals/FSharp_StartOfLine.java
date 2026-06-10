// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.FSharp.FSharp_Element;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
import com.eagle.programmar.FSharp.Statements.FSharp_IfStatement.FSharp_IfElif;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.terminals.TerminalStartOfLine;

public class FSharp_StartOfLine extends TerminalStartOfLine
{
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
                TokenList<? extends AbstractToken> tokenList = (TokenList<?>) parent;
                if (tokenList.size() == 0) break; // First entry always matches

                // The 'elif' clause is an irrelevant TokenList on an 'if' statement
                AbstractToken firstToken = tokenList.first();
                if (!(firstToken instanceof FSharp_IfElif))
                {
                    for (AbstractToken token : tokenList._elements)
                    {
                        if (token instanceof FSharp_Comment)
                        {
                            continue; // Doesn't matter what columns comments are in
                        }

                        if (token instanceof FSharp_Element)
                        {
                            FSharp_Element firstStmt = (FSharp_Element) token;
                            AbstractToken child = firstStmt.statementOrComment.getWhich();
                            if (child instanceof FSharp_Statement_List)
                            {
                                FSharp_Statement_List stmtList = (FSharp_Statement_List) child;
                                FSharp_Statement otherStmt = stmtList.statements.getPrimaryElement(0);
                                if (_currentChar != otherStmt.getStartChar()) return false;
                                break;
                            }
                        }
                        else
                        {
                            throw new RuntimeException("Expected an FSharp_Statement, not " + token);
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
        if (parent instanceof TokenList && !(parent instanceof SeparatedList))
        {
            TokenList<? extends AbstractToken> tokenList = (TokenList<?>) parent;

            // The 'elif' clause is an irrelevant TokenList on an 'if' statement
            if (tokenList.size() > 0 && !(tokenList.first() instanceof FSharp_IfElif))
            {
                return true;
            }
        }
        return false;
	}
}
