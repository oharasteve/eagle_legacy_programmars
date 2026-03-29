// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2015

namespace com.eagle.preprocess.CMacro
{

	using EagleProject = com.eagle.core.EagleProject;
	using EagleSymbolTable = com.eagle.math.EagleSymbolTable;
	using EagleToken = com.eagle.math.EagleToken;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using EagleTracer = com.eagle.parsers.EagleTracer;
	using ParserManager = com.eagle.parsers.ParserManager;
	using EagleInclude = com.eagle.preprocess.EagleInclude;
	using FindIncludeFile = com.eagle.preprocess.FindIncludeFile;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Program = com.eagle.programmar.CMacro.CMacro_Program;
	using CMacro_CommentLine = com.eagle.programmar.CMacro.CMacro_Program.CMacro_CommentLine;
	using CMacro_Element = com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Define_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
	using CMacro_Param = com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement.CMacro_Parameters.CMacro_Param;
	using CMacro_Parameter_Definition = com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using TerminalEndOfLine = com.eagle.tokens.terminals.TerminalEndOfLine;

	public class CMacro_Preprocess : EagleInclude
	{
		private const bool DEBUG = false;
		private const bool VERBOSE = false;

		public FindIncludeFile _findInclude;
		public ParserManager _parser;

		public CMacro_Preprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable, EagleTracer tracer) : base(project, symbolTable, tracer)
		{
			_findInclude = findInclude;
		}

		public CMacro_Preprocess(CMacro_Preprocess preprocessor) : this(preprocessor._project, preprocessor._findInclude, preprocessor._symbolTable, preprocessor._tracer)
		{
		}

		public override EagleFileReader preprocessFile(ParserManager parser, EagleFileReader lines)
		{
			_parser = parser;

			if (VERBOSE)
			{
				StringBuilder sb = new StringBuilder("*** Pre-processing ");
				for (int i = 0; i < _depth; i++)
				{
					sb.Append(". ");
				}
				sb.Append(lines.getFileName()).Append(" lines=").Append(lines.numberLines());
				Console.WriteLine(sb);
			}

			if (_depth > 0)
			{
				if (_project != null)
				{
					// The outermost #include file has already been repaired -- don't try to do it
					// twice
					_project.performRepairs(lines.getFileName(), lines);
				}
			}

			_oldLines = lines;
			if (VERBOSE)
			{
				for (int i = 0; i < _depth; i++)
				{
					Console.Write("  ");
				}
				Console.WriteLine("*** Starting to read " + lines.getFileName());
			}

			// Parse the include file
			parser._parser.setTracer(_tracer); // For debugging
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
				Console.Error.WriteLine("Unable to parse " + lines.getFileName());
				return null;
			}

	//		DumpTree dump = new DumpTree();
	//		dump.dump(System.out, pgm, DumpTree.Width.WIDE, 0, true);

			bool changed = false;

			// Look for all the #if's and #include's etc.
			foreach (CMacro_Program.CMacro_Element element in pgm.elements._elements)
			{
				try
				{
					if (preprocessCMacroElement(parser, element))
					{
						changed = true;
					}
				}
				catch (Exception ex)
				{
					StringBuilder msg = (new StringBuilder("Failed preprocessing ")).Append(element.getWhich()).Append('\n');
					msg.Append("File ").Append(lines.getFileName());
					msg.Append(", line ").Append(Convert.ToString(element.getStartLine() + 1)).Append('\n');
					msg.Append(lines.get(element.getStartLine()).ToString()).Append('\n');

					for (int i = 0; i < element.getStartChar(); i++)
					{
						msg.Append(' ');
					}
					msg.Append("^ ");
					msg.Append(ex.Message);
					// System.err.println(msg.toString());
					ex.printStackTrace(System.err);
					return lines; // Can't preprocess it -- leave it alone
				}
			}

			if (!changed)
			{
				return lines;
			}

			// Some multiline comments still there and should be split apart
			_newLines.splitApartMultilineStrings();

			// Save the pre-processed file into the project artifact folder
			if (_depth == 0 && _project != null)
			{
				string srcFile = lines.getFileName();
				string baseDir = _project._sourceBase.replaceAll("/", "\\\\");
				// System.out.println("src=" + srcFile + " base=" + baseDir);
				if (srcFile.StartsWith(baseDir, StringComparison.Ordinal))
				{
					int pathLen = baseDir.Length;
					string prepName = srcFile.Substring(pathLen + 1);
					try
					{
						_savePreprocessedFile.saveHtml(_project._artifactBase, prepName, _newLines);
					}
					catch (IOException ex)
					{
						throw new Exception("Unable to write preprocessed version of " + prepName, ex);
					}
				}
			}

			// Save origin information
			string oldFileName = lines.getFileName();
			if (VERBOSE)
			{
				for (int i = 0; i < _depth; i++)
				{
					Console.Write("  ");
				}
				Console.WriteLine("*** Finished reading " + oldFileName);
			}
			for (int i = 0; i < _newLines.numberLines(); i++)
			{
				EagleLineReader newLine = _newLines.get(i);
				string origFile = newLine.getOriginalFileName();
				if (string.ReferenceEquals(origFile, null))
				{
					// if (DEBUG) System.out.println("***** 1 Setting line# to " + origLine + " in "
					// + oldFileName + " for " + line.toString());
					newLine.setOriginalLocation(oldFileName);
				}
			}

			if (VERBOSE)
			{
				StringBuilder sb = new StringBuilder("***           done ");
				for (int i = 0; i < _depth; i++)
				{
					sb.Append(". ");
				}
				sb.Append(lines.getFileName()).Append(" lines=").Append(_newLines.numberLines());
				Console.WriteLine(sb);
			}

			return _newLines;
		}

		private bool letMacroHandleIt(AbstractToken token)
		{
			if (token is CMacro_Processable)
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

		// Returns true always, even if nothing was changed in the file (not including
		// the symbol table)
		public virtual bool preprocessCMacroElement(ParserManager parser, CMacro_Program.CMacro_Element element)
		{
			// Ignore all the rest of the stuff
			AbstractToken whichStatement = element.getWhich();
			if (whichStatement is CMacro_StatementOrComment)
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

		public override void copyElement(AbstractToken token)
		{
			if (token is TerminalEndOfLine)
			{
				return;
			}

			if (DEBUG)
			{
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				Console.WriteLine("******************* token = " + token.GetType().FullName);
			}

			string oldLine;
			string oldFileName = token.getFileName();
			int oldLineNumber = token.getStartLine() + 1;
			if (token is TerminalToken)
			{
				TerminalToken term = (TerminalToken) token;
				oldLine = term.getValue();
			}
			else if (token is CMacro_Program.CMacro_CommentLine)
			{
				CMacro_Program.CMacro_CommentLine comm = (CMacro_Program.CMacro_CommentLine) token;
				oldLine = comm.comment.getValue();
				if (DEBUG)
				{
					Console.WriteLine("******* Comment " + oldLine);
				}
			}
			else
			{
				int seq = token.getStartLine();
				oldLine = _oldLines.get(seq).ToString();
			}
			if (DEBUG)
			{
				Console.WriteLine("***** Copying " + oldLine);
			}

			// Returns null if nothing has changed
			string newLine = replaceWords(token.getStartLine(), oldFileName, oldLine, 0);

			if (string.ReferenceEquals(newLine, null))
			{
				_newLines.add(oldLine, oldFileName, oldLineNumber);
			}
			else
			{
				if (newLine.IndexOf('\n') < 0)
				{
					EagleLineReader line = new EagleLineReader(newLine, oldLineNumber);
					line.setOriginalLocation(token.getFileName());
					addLine(line);
				}
				else
				{
					// Must have been a multi-line macro in there
					foreach (string piece in newLine.Split("\\n", true))
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
		private string replaceWords(int lineNum, string fname, string oldLine, int depth)
		{
			// Don't ever do too many changes on any one line
			// Note that there are many multi-line "lines"
			if (depth > 500)
			{
				string prtLine = oldLine;
				if (oldLine.Length > 100)
				{
					prtLine = oldLine.Substring(0, 100) + " ...";
				}
				Console.Error.WriteLine("Exceeded maximum macro depth at line " + lineNum + ":  " + prtLine);
				return null; // Must be stuck in a loop ... bail out now
			}

			string newLine = null;

			int sc = 0;
			int len = oldLine.Length;
			bool inQuotes = false;
			while (sc < len)
			{
				// Find start of the next word
				char ch = oldLine[sc];
				if (ch == '"')
				{
					inQuotes = !inQuotes;
				}
				if (ch == '\n')
				{
					inQuotes = false;
				}

				if (!inQuotes && (char.IsLetter(ch) || ch == '_'))
				{
					// Found a word!
					int ec = sc;
					while (ec <= len)
					{
						ch = ' '; // Pretend there is a space at the end of the line
						if (ec < len)
						{
							ch = oldLine[ec];
						}
						if (!char.IsLetterOrDigit(ch) && ch != '_')
						{
							string word = oldLine.Substring(sc, ec - sc);
							// System.out.println("*** " + lineNum + " Checking '" + word + "' to see if it
							// is a macro");
							if (_symbolTable.isSymbolDefined(word))
							{
								// Yes, found a macro!
								EagleToken macroValue = (EagleToken) _symbolTable.findSymbol(word);
								AbstractToken macro = macroValue.getTokenValue();
								if (macro is CMacro_Define_Statement)
								{
									CMacro_Define_Statement defineStatement = (CMacro_Define_Statement) macro;
									if (defineStatement.value != null)
									{
										string newPiece = defineStatement.value.getValue();
										string changedLine;
										if (defineStatement.@params != null && defineStatement.@params.countTokens() > 0 && defineStatement.@params.isPresent())
										{
											// Macro function, ugh
											if (ec < len && oldLine[ec] == ' ')
											{
												ec++; // Trim leading space
											}
											changedLine = processDefineFunction(lineNum, sc, ec, word, oldLine, newPiece, defineStatement, fname);
										}
										else
										{
											if (VERBOSE)
											{
												for (int i = 0; i <= _depth; i++)
												{
													Console.Write("  ");
												}
												Console.WriteLine("****** " + fname + ":" + (lineNum + 1) + " Replacing " + word + " with '" + newPiece + "'");
											}

											// Apply the change
											changedLine = oldLine.Substring(0, sc) + newPiece + oldLine.Substring(ec);
										}

										if (string.ReferenceEquals(changedLine, null))
										{
											return null;
										}

										if (DEBUG)
										{
											Console.WriteLine("************ " + changedLine);
										}
										string moreChanges = replaceWords(lineNum, fname, changedLine, depth + 1); // Recursive
										if (!string.ReferenceEquals(moreChanges, null))
										{
											return moreChanges;
										}
										return changedLine;
									}
								}
								else
								{
									throw new Exception("Expected CMacro_Define_Statement, not " + macro);
								}
							}
							break;
						}
						ec++;
					}
					sc = ec; // Keep looking across the line for another word
				}

				sc++;
			}

			return newLine;
		}

		// Handle macro functions.
		private static string processDefineFunction(int line, int sc, int ec, string word, string oldLine, string newPiece, CMacro_Define_Statement defineStatement, string fname)
		{
			int nc = oldLine.Length;
			if (ec >= nc)
			{
				return null;
			}

			if (oldLine[ec] != '(')
			{
				return null; // Don't expand the macro -- it was supposed to be a function, but no params
								// were passed
				// throw new RuntimeException("Expected a left paren, not " +
				// oldLine.substring(ec));
			}

			// System.out.println("******* ec-sc=" + (ec-sc) + " oldLine = " +
			// oldLine.substring(sc));
			// int rparen = oldLine.indexOf(')', ec); // too simple! Might be embedded right
			// parens!
			int rparen = -1;
			int depth = 0;
			// Have to search for a matching right paren because there may be additional
			// left parens in there
			for (int i = ec + 1; i < nc; i++)
			{
				char ch = oldLine[i];
				if (ch == '(')
				{
					depth++;
				}
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
				Console.Error.WriteLine("*** Line " + (line + 1) + " is missing right paren in " + oldLine.Substring(ec));
				Console.Error.WriteLine("    Used at (or after) line " + (line + 1) + " of " + fname);
				Console.Error.WriteLine("    #define is at line " + (defineStatement.getStartLine() + 1) + " of " + defineStatement.getFileName());
				return null;
			}
			if (DEBUG)
			{
				Console.WriteLine("******* ec=" + ec + " rparen=" + rparen + "  remainder = " + oldLine.Substring(rparen));
			}

			string actualParamString = oldLine.Substring(ec + 1, rparen - (ec + 1)).Trim();
			string[] actualParams = fancySplit(actualParamString);

			SeparatedList<CMacro_Define_Statement.CMacro_Parameters.CMacro_Param, PunctuationComma> formalParams = defineStatement.@params.@params;
			int paramCount = 0;
			if (formalParams != null)
			{
				paramCount = formalParams.getPrimaryCount();
			}
			if (paramCount > 0)
			{
				CMacro_Define_Statement.CMacro_Parameters.CMacro_Param first = formalParams.first();
				if (first.getWhich() is CMacro_Punctuation) // ellipsis (...) is special
				{
					paramCount = 0;
				}
			}

			if (actualParams.Length != paramCount)
			{
				Console.Error.WriteLine("*** Number of parameters for " + word + " does not match, actual=" + actualParams.Length + ", expected=" + paramCount);
				if (actualParamString.Length > 0)
				{
					Console.Error.WriteLine("    Actual parameter string: " + actualParamString);
				}
				Console.Error.WriteLine("    Used at (or after) line " + (line + 1) + " of " + fname);

// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================
