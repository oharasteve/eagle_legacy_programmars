// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2015

package com.eagle.preprocess.CMacro;

import java.io.IOException;

import com.eagle.core.EagleProject;
import com.eagle.math.TokenValue;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.parsers.EagleTracer;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_CommentLine;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement.CMacro_Parameters.CMacro_Param;
import com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TerminalEndOfLine;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CMacro_Preprocess extends EagleInclude
{
	private static final boolean DEBUG = false;
	private static final boolean VERBOSE = false;
	
	public FindIncludeFile _findInclude;
	public ParserManager _parser;
	
	public CMacro_Preprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable, EagleTracer tracer)
	{
		super(project, symbolTable, tracer);
		_findInclude = findInclude;
	}
	
	public CMacro_Preprocess(CMacro_Preprocess preprocessor)
	{
		this(preprocessor._project, preprocessor._findInclude, preprocessor._symbolTable, preprocessor._tracer);
	}
	
	@Override // Recursive
	public EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
	{
		_parser = parser;
		
		StringBuffer sb = new StringBuffer("*** Pre-processing ");
		for (int i = 0; i < _depth; i++) sb.append(". ");
		sb.append(lines.getFileName()).append(" lines=").append(lines.numberLines());
		System.out.println(sb);

		if (_depth > 0)
		{
			if (_project != null)
			{
				// The outermost #include file has already been repaired -- don't try to do it twice
				_project.performRepairs(lines.getFileName(), lines);
			}
		}
	
		_oldLines = lines;
		if (VERBOSE)
		{
			for (int i = 0; i < _depth; i++) System.out.print("  ");
			System.out.println("*** Starting to read " + lines.getFileName());
		}
		
		// Parse the include file
		parser._parser.setTracer(_tracer);		// For debugging
		CMacro_Program pgm = new CMacro_Program();
		if (_tracer != null)
		{
			if (_parser._parser.getFileName() == null)
			{
				_parser._parser.setFileName(lines.getFileName());
			}
			_tracer.header(_parser._parser, pgm);
		}

		if (!parser.parseLines(lines, pgm, pgm))
		{
			// String msg = parser._parser.getStoppingPoint(lines.getFileName());
			System.err.println("Unable to parse " + lines.getFileName());
			return null;
		}
		
//		DumpTree dump = new DumpTree();
//		dump.dump(System.out, pgm, DumpTree.Width.WIDE, 0, true);
		
		boolean changed = false;
		
		// Look for all the #if's and #include's etc.
		for (CMacro_Element element : pgm.elements._elements)
		{
			try
			{
				if (preprocessCMacroElement(parser, element)) changed = true;
			}
			catch (Exception ex)
			{
				StringBuffer msg = new StringBuffer("Failed preprocessing ").append(element.getWhich()).append('\n');
				msg.append("File ").append(lines.getFileName());
				msg.append(", line ").append(Integer.toString(element.getStartLine()+1)).append('\n');
				msg.append(lines.get(element.getStartLine()).toString()).append('\n');
				
				for (int i = 0; i < element.getStartChar(); i++) msg.append(' ');
				msg.append("^ ");
				msg.append(ex.getMessage());
				//System.err.println(msg.toString());
				ex.printStackTrace(System.err);
				return lines;	// Can't preprocess it -- leave it alone
			}
		}
		
		if (! changed) return lines;
		
		// Some multiline comments still there and should be split apart
		_newLines.splitApartMultilineStrings();

		// Save the pre-processed file into the project artifact folder
		if (_depth == 0 && _project != null)
		{
			String srcFile = lines.getFileName();
			String baseDir = _project._sourceBase.replaceAll("/", "\\\\");
			// System.out.println("src=" + srcFile + " base=" + baseDir);
			if (srcFile.startsWith(baseDir))
			{
				int pathLen = baseDir.length();
				String prepName = srcFile.substring(pathLen+1);
				try
				{
					_savePreprocessedFile.saveHtml(_project._artifactBase, prepName, _newLines);
				}
				catch (IOException ex)
				{
					throw new RuntimeException("Unable to write preprocessed version of " + prepName, ex);
				}
			}
		}
		
		// Save origin information
		String oldFileName = lines.getFileName();
		if (VERBOSE)
		{
			for (int i = 0; i < _depth; i++) System.out.print("  ");
			System.out.println("*** Finished reading " + oldFileName);
		}
		for (int i = 0; i < _newLines.numberLines(); i++)
		{
			EagleLineReader newLine = _newLines.get(i);
			String origFile = newLine.getOriginalFileName();
			if (origFile == null)
			{
				// if (DEBUG) System.out.println("***** 1 Setting line# to " + origLine + " in " + oldFileName + " for " + line.toString());
				newLine.setOriginalLocation(oldFileName);
			}
		}

		sb = new StringBuffer("***           done ");
		for (int i = 0; i < _depth; i++) sb.append(". ");
		sb.append(lines.getFileName()).append(" lines=").append(_newLines.numberLines());
		System.out.println(sb);
		
		return _newLines;
	}

	private boolean letMacroHandleIt(AbstractToken token)
	{
		if (token instanceof CMacro_Processable)
		{
			CMacro_Processable macro = ((CMacro_Processable) token);
	
			// Route it to its own controller
			if (macro.processMacro(this))
			{
				return true;
			}
		}
		return false;
	}
	
	// Returns true always, even if nothing was changed in the file (not including the symbol table)
	public boolean preprocessCMacroElement(ParserManager parser, CMacro_Element element)
	{
		// Ignore all the rest of the stuff
		AbstractToken whichStatement = element.getWhich();
		if (whichStatement instanceof CMacro_StatementOrComment)
		{
			CMacro_StatementOrComment statementContainer = (CMacro_StatementOrComment) whichStatement;
			if (letMacroHandleIt(statementContainer.stmt.getWhich()))
			{
				return true;
			}
		}

		// The macro didn't write anything on its own
		copyElement(whichStatement);
		return true;
	}
	
	// Returns true always, even if nothing was changed in the file (not including the symbol table)
	public boolean preprocessCStatement(C_StatementOrComment element)
	{
		AbstractToken whichStatement = element.getWhich();
		if (whichStatement instanceof C_Statement)
		{
			C_Statement cStatement = (C_Statement) whichStatement;
			if (letMacroHandleIt(cStatement.getWhich()))
			{
				return true;
			}
		}

		// The macro didn't write anything on its own
		copyElement(element);
		return true;
	}
	
	@Override
	public void copyElement(AbstractToken token)
	{
		if (token instanceof TerminalEndOfLine) return;
		
		if (DEBUG) System.out.println("******************* token = " + token.getClass().getName());
		
		String oldLine;
		String oldFileName = token.getFileName();
		int oldLineNumber = token.getStartLine() + 1;
		if (token instanceof TerminalToken)
		{
			TerminalToken term = (TerminalToken) token;
			oldLine = term.getValue();
		}
		else if (token instanceof CMacro_CommentLine)
		{
			CMacro_CommentLine comm = (CMacro_CommentLine) token;
			oldLine = comm.comment.getValue();
			if (DEBUG) System.out.println("******* Comment " + oldLine);
		}
		else
		{
			int seq = token.getStartLine();
			oldLine = _oldLines.get(seq).toString();
		}
		if (DEBUG) System.out.println("***** Copying " + oldLine);
		
		// Returns null if nothing has changed
		String newLine = replaceWords(token.getStartLine(), oldFileName, oldLine, 0);
		
		if (newLine == null)
		{
			_newLines.add(oldLine, oldFileName, oldLineNumber);
		}
		else
		{
			if (newLine.indexOf('\n') < 0)
			{
				EagleLineReader line = new EagleLineReader(newLine, oldLineNumber);
				line.setOriginalLocation(token.getFileName());
				addLine(line);
			}
			else
			{
				// Must have been a multi-line macro in there
				for (String piece : newLine.split("\\n"))
				{
					EagleLineReader line = new EagleLineReader(piece, oldLineNumber);
					addLine(line);
					oldLineNumber++;
				}
			}
		}
	}

	// Returns null if nothing changed
	// QUESTION: should we ignore comments? Currently: we don't check for it.
	// There is a simple test for string literals
	// Careful, this is recursive
	private String replaceWords(int lineNum, String fname, String oldLine, int depth)
	{
		// Don't ever do too many changes on any one line
		// Note that there are many multi-line "lines"
		if (depth > 500)
		{
			String prtLine = oldLine;
			if (oldLine.length() > 100) prtLine = oldLine.substring(0, 100) + " ...";
			System.err.println("Exceeded maximum macro depth at line " + lineNum + ":  " + prtLine);
			return null;	// Must be stuck in a loop ... bail out now
		}
		
		String newLine = null;
		
		int sc = 0;
		int len = oldLine.length();
		boolean inQuotes = false;
		while (sc < len)
		{
			// Find start of the next word
			char ch = oldLine.charAt(sc);
			if (ch == '"')
			{
				inQuotes = ! inQuotes;
			}
			if (ch == '\n') inQuotes = false;
			
			if (! inQuotes && (Character.isLetter(ch) || ch == '_'))
			{
				// Found a word!
				int ec = sc;
				while (ec <= len)
				{
					ch = ' ';	// Pretend there is a space at the end of the line
					if (ec < len) ch = oldLine.charAt(ec);
					if (! Character.isLetterOrDigit(ch) && ch != '_')
					{
						String word = oldLine.substring(sc, ec);
						// System.out.println("*** " + lineNum + " Checking '" + word + "' to see if it is a macro");
						if (_symbolTable.isDefined(word))
						{
							// Yes, found a macro!
							TokenValue macroValue = (TokenValue) _symbolTable.findSymbol(word);
							AbstractToken macro = macroValue.getTokenValue();
							if (macro instanceof CMacro_Define_Statement)
							{
								CMacro_Define_Statement defineStatement = (CMacro_Define_Statement) macro;
								if (defineStatement.value != null)
								{
									String newPiece = defineStatement.value.getValue();
									String changedLine;
									if (defineStatement.params != null &&
											defineStatement.params.countTokens() > 0 &&
											defineStatement.params.isPresent())
									{
										// Macro function, ugh
										if (ec < len && oldLine.charAt(ec) == ' ') ec++;	// Trim leading space
										changedLine = processDefineFunction(lineNum, sc, ec, word, oldLine, newPiece, defineStatement, fname);
									}
									else
									{
										if (VERBOSE)
										{
											for (int i = 0; i <= _depth; i++) System.out.print("  ");
											System.out.println("****** " + fname + ":" + (lineNum+1) + " Replacing " + word + " with '" + newPiece + "'");
										}
										
										// Apply the change
										changedLine = oldLine.substring(0, sc) + newPiece + oldLine.substring(ec);
									}
									
									if (changedLine == null) return null;

									if (DEBUG) System.out.println("************ " + changedLine);
									String moreChanges = replaceWords(lineNum, fname, changedLine, depth+1);		// Recursive
									if (moreChanges != null) return moreChanges;
									return changedLine;
								}
							}
							else
							{
								throw new RuntimeException("Expected CMacro_Define_Statement, not " + macro);
							}
						}
						break;
					}
					ec++;
				}
				sc = ec;	// Keep looking across the line for another word
			}

			sc++;
		}
		
		return newLine;
	}
	
	// Handle macro functions.
	private static String processDefineFunction(int line, int sc, int ec, String word, String oldLine, String newPiece,
			CMacro_Define_Statement defineStatement, String fname)
	{
		int nc = oldLine.length();
		if (ec >= nc) return null;
		
		if (oldLine.charAt(ec) != '(')
		{
			return null;	// Don't expand the macro -- it was supposed to be a function, but no params were passed
			//throw new RuntimeException("Expected a left paren, not " + oldLine.substring(ec));
		}
		
		// System.out.println("******* ec-sc=" + (ec-sc) + "  oldLine = " + oldLine.substring(sc));
		// int rparen = oldLine.indexOf(')', ec); // too simple! Might be embedded right parens!
		int rparen = -1;
		int depth = 0;
		// Have to search for a matching right paren because there may be additional left parens in there
		for (int i = ec + 1; i <nc; i++)
		{
			char ch = oldLine.charAt(i);
			if (ch == '(') depth++;
			else if (ch == ')')
			{
				if (depth <= 0)
				{
					rparen = i;
					break;
				}
				depth--;
			}
		}
		if (rparen < 0)
		{
			System.err.println("*** Line " + (line+1) + " is missing right paren in " + oldLine.substring(ec));
			System.err.println("    Used at (or after) line " + (line+1) + " of " + fname);
			System.err.println("    #define is at line " + (defineStatement.getStartLine()+1) + " of " + defineStatement.getFileName());
			return null;
		}
		if (DEBUG) System.out.println("******* ec=" + ec + " rparen=" + rparen + "  remainder = " + oldLine.substring(rparen));
		
		String actualParamString = oldLine.substring(ec+1, rparen).trim();
		String[] actualParams = fancySplit(actualParamString);

		SeparatedList<CMacro_Param,PunctuationComma> formalParams = defineStatement.params.params;
		int paramCount = 0;
		if (formalParams != null) paramCount = formalParams.getPrimaryCount();
		if (paramCount > 0)
		{
			CMacro_Param first = (CMacro_Param) formalParams.first();
			if (first.getWhich() instanceof CMacro_Punctuation) // ellipsis (...) is special
			{
				paramCount = 0;
			}
		}

		if (actualParams.length != paramCount)
		{
			System.err.println("*** Number of parameters for " + word + " does not match, actual=" +
				actualParams.length + ", expected=" + paramCount);
			if (actualParamString.length() > 0) System.err.println("    Actual parameter string: " + actualParamString);
			System.err.println("    Used at (or after) line " + (line+1) + " of " + fname);
			System.err.println("    #define is at line " + (defineStatement.getStartLine()+1) + " of " + defineStatement.getFileName());
			return null;
		}

		int index = 0;
		String changedPiece = newPiece;
		for (String actualParam : actualParams)
		{
			CMacro_Param formalParamElement = formalParams.getPrimaryElement(index);
			String formalParam = "Skippy";
			AbstractToken which = formalParamElement.getWhich();
			if (which instanceof CMacro_Parameter_Definition)
			{
				formalParam = ((CMacro_Parameter_Definition) which).toString();
			}
			if (! formalParam.equals(actualParam.trim()))		// Don't get stuck in a loop!
			{
				//System.out.println("Replacing " + formalParam + " with " + actualParam.trim() + " in " + changedPiece);
	
				int start = -1;
				while (true)
				{
					start = changedPiece.indexOf(formalParam, start+1);
					if (start < 0) break;
	
					if (start > 0)
					{
						char ch = changedPiece.charAt(start-1);
						if (ch == '_' || Character.isLetterOrDigit(ch)) continue;
					}
					int end = start + formalParam.length();
					if (end < changedPiece.length())
					{
						char ch = changedPiece.charAt(end);
						if (ch == '_' || Character.isLetterOrDigit(ch)) continue;
					}
	
					// Ok, make the change!
					String trimmedParam = actualParam.trim();
					changedPiece = changedPiece.substring(0, start) + trimmedParam + changedPiece.substring(end);
					start = start + trimmedParam.length() - formalParam.length();	// Don't look at it again
				}
			}
			
			index++;
		}
		
		// Now, toss all the ## entries
		int pound = -1;
		while (true)
		{
			pound = changedPiece.indexOf("##", pound+1);
			if (pound < 0) break;
			changedPiece = changedPiece.substring(0, pound) + changedPiece.substring(pound + 2);
		}

		return oldLine.substring(0, sc) + changedPiece + oldLine.substring(rparen+1);
	}
	
	public static String[] fancySplit(String line)
	{
		if (line.length() == 0) return new String[0];	// #define x() with no parameters. Odd, yes?
		
		if (line.indexOf('(') < 0 && line.indexOf('"') < 0)
		{
			return line.split(",");	// Normal case
		}
		char replace = '?';
		if (line.indexOf(replace) >= 0)
		{
			// Don't actually know what to do if there is already a ? in the line.
			// Try a different replace char?
			return line.split(",");
		}
		
		StringBuffer buff = new StringBuffer(line);
		int parenDepth = 0;
		boolean inQuotes = false;
		char prevCh = ' ';	// Anything but a backslash
		for (int i = 0; i < buff.length(); i++)
		{
			char ch = buff.charAt(i);
			if (prevCh != '\\' && ch == '"')
			{
				inQuotes = ! inQuotes;
			}
			else if (inQuotes && ch == ',')
			{
				buff.setCharAt(i, replace);  // Smash the comma for a moment
			}
			else if (! inQuotes)
			{
				if (ch == '(') parenDepth++;
				else if (ch == ')') parenDepth--;
				else if (ch == ',' && parenDepth > 0)
				{
					buff.setCharAt(i, replace);  // Smash the comma for a moment
				}
			}
			
			prevCh = ch;
		}
		
		String[] result = buff.toString().split(",");
		int numPieces = result.length;
		for (int i = 0; i < numPieces; i++)
		{
			if (result[i].indexOf(replace) >= 0)
			{
				result[i] = result[i].replaceAll("\\"+replace, ",");
			}
		}
		return result;
	}
	
	public void addLine(EagleLineReader line)
	{
		_newLines.addLine(line);
	}
}
