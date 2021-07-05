package proguard.obfuscate;

import java.util.Random;

public class ONameFactory implements NameFactory {
    private static final Random RANDOM = new Random();

    private char c = 'o';
    private ONameFactory parent = null;
    private boolean mark = RANDOM.nextBoolean();

    ONameFactory() {
    }

    @Override
    public void reset() {
        c = 'o';
        parent = null;
    }

    @Override
    public String nextName() {
        String name = getName();
        next();
        return name;
    }

    private String getName() {
        return parent == null ? String.valueOf(c) : parent.getName() + c;
    }

    private void next() {
        if (mark)
            switch (c) {
                case '0':
                    c = 'o';
                    break;
                case 'o':
                    c = 'O';
                    break;
                case 'O':
                    c = '0';
                    if (parent == null)
                        parent = new ONameFactory();
                    else
                        parent.next();
                    break;
            }
        else
            switch (c) {
                case 'O':
                    c = 'o';
                    break;
                case 'o':
                    c = '0';
                    break;
                case '0':
                    c = 'O';
                    if (parent == null)
                        parent = new ONameFactory();
                    else
                        parent.next();
                    break;
            }
    }

}
