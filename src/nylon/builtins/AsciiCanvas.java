package nylon.builtins;

import nylon.NylonException;
import nylon.NylonObject;
import nylon.builtins.objects.BuiltinFunction;
import nylon.nylonobjects.NylonString;
import nylon.parser.Parser;
import nylon.parser.parsers.BuiltinParser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public final class AsciiCanvas {
    public static void build(ArrayList<Parser> parsers) {
        parsers.set('ð', BuiltinParser.getParser(new BuiltinFunction('ð') {
            @Override
            public void applyImpl(Stack<NylonObject> stack) throws NylonException {
                // The lines of the string to addTo
                String[] add = stack.pop().toString().split("\n");
                // The x and y position to addTo the string
                int x = ((int) stack.pop().toDouble(stack)), y = ((int) stack.pop().toDouble(stack));
                // The canvas to addTo the string to
                String canvasString = stack.pop().toString();
                ArrayList<ArrayList<Character>> canvas = new ArrayList<>();
                canvas.add(new ArrayList<>());

                // Create the canvas
                for (char c : canvasString.toCharArray()) {
                    if (c == '\n') {
                        canvas.add(new ArrayList<>());
                    } else {
                        canvas.get(canvas.size() - 1).add(c);
                    }
                }

                // Extend the canvas vertically to the required length
                for (int i = add.length + y - canvas.size(); i > 0; i--) {
                    canvas.add(new ArrayList<>());
                }

                // For each line in the string to addTo
                for (String s : add) {
                    char[] charArray = s.toCharArray();
                    // Extend the line to the required length
                    for (int i = s.length() + x - canvas.get(y).size(); i > 0; i--) {
                        canvas.get(y).add(' ');
                    }
                    // Overwrite the line with new characters, except spaces
                    for (int i = charArray.length - 1; i >= 0; i--) {
                        if (charArray[i] != ' ') {
                            canvas.get(y).set(x + i, charArray[i]);
                        }
                    }
                    // Go down one line
                    y++;
                }

                // Turn the canvas back into a.nl string
                String s = "";
                for (ArrayList<Character> chars : canvas) {
                    for (Character c : chars) {
                        s += c;
                    }
                    s += '\n';
                }
                s = s.substring(0, s.length() - 1);
                stack.add(new NylonString(s.toCharArray()));
            }
        }));
    }
}
