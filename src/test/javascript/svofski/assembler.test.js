import { readFile, record, assertCall, assertAllRecords } from "./common";
import * as assembler from '../../../main/javascript/svofski/assembler';

describe('assembler', () => {
    const dir = '../../../resources/AssemblerTest/';
    const program = readFile('../../../main/resources/test/test/test.asm');

    test('assemble', () => {

        // when
        record(assembler.asm, 'evaluateExpression2', [ 'labels' ]);
        record(assembler.asm, 'resolveNumber');

        let data = assembler.assemble(program);

        // then
        assertAllRecords(dir);

        assertCall(dir + 'memory.json', data.mem);
        assertCall(dir + 'hex.json', data.hex);
        assertCall(dir + 'gutter.json', data.gutter);
        assertCall(dir + 'listing.json', data.listing);
        assertCall(dir + 'errors.json', data.errors);
        assertCall(dir + 'xref.json', data.xref);
        assertCall(dir + 'labels.json', data.labels);
        assertCall(dir + 'info.json', data.info);
    });
});