package nylon.parser;

import nylon.InlineFunction;
import nylon.parser.parsers.*;
import parser.LinkedParser;
import parser.StringIterator;

/**
 * Created by Aedan Smith.
 */

public class NylonParser extends LinkedParser<StringIterator, InlineFunction> {
    public static NylonParser nylonParser = new NylonParser();

    public NylonParser() {
        super(new DefaultParser(),
                new CharacterParser(),
                new StringParser(),
                new NylonFunctionParser(),
                new WhileLoopParser(),
                new CastParser(),
                new CaptureParser(),
                new CommentParser(),
                new DoubleParser(),
                new ForLoopParser(),
                new IfStatementParser(),
                new BuiltinParser(),
                new LibraryParser()
        );
    }

    public static InlineFunction parse(String s) {
        InlineFunction inlineFunction = new InlineFunction();
        nylonParser.parse(new StringIterator(s), inlineFunction);
        return inlineFunction;
    }
}
