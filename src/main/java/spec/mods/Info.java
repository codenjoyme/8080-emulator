package spec.mods;

import spec.assembler.Command;
import spec.math.Bites;

import static spec.math.WordMath.*;

public class Info {

    public int access;
    public WhereIsData.Type type;
    public Command command;
    public String asm;
    public int data;
    public int code;
    public String labelTo;
    public String label;

    public Info(int access, WhereIsData.Type type, Command command) {
        this.access = access;
        this.type = type;
        this.command = command;
    }

    public Info(Info info) {
        this(info.access, info.type, info.command);
    }

    public void increase() {
        access++;
    }

    public void type(WhereIsData.Type type) {
        this.type = type;
    }

    public void command(Command command) {
        this.command = command;
    }

    public void asm(String asm) {
        this.asm = asm;
    }

    public void data(int word) {
        data = word;
    }

    public void code(int bite) {
        code = bite;
    }

    public String data(boolean canonical) {
        String hex = command.size() == 3
                ? hex16(data)
                : hex8(data);
        return canonical
                ? canonical(hex)
                : hex;
    }

    public String printCommand(boolean canonical) {
        Bites bites = new Bites(command.size());
        bites.set(0, code);
        if (command.size() >= 2) {
            bites.set(1, lo(data));
        }
        if (command.size() == 3) {
            bites.set(2, hi(data));
        }
        return command.print(bites, canonical);
    }

    public String asm(boolean canonical) {
        return asm == null
                ? asm = printCommand(canonical)
                : asm;
    }
}
